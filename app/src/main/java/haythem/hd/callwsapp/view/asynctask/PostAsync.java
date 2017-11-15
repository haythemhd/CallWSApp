package haythem.hd.callwsapp.view.asynctask;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import haythem.hd.callwsapp.model.Post;
import haythem.hd.callwsapp.utils.Constantes;
import haythem.hd.callwsapp.utils.HttpHandler;

/**
 * Created by Haythem on 15/11/2017.
 */

public class PostAsync extends AsyncTask<Void, Void, Object> {

    private PostListener mListener;

    public PostAsync(PostListener mListener) {
        this.mListener = mListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Object doInBackground(Void... arg0) {
        return getArrayObject();
    }

    private Object getArrayObject() {
        ArrayList<Object> mPostList = new ArrayList<>();
        String jsonStr = HttpHandler.makeServiceCall(Constantes.URL_WS_POST);
        if (jsonStr != null) {
            try {
                JSONArray posts = new JSONArray(jsonStr);
                for (int i = 0; i < posts.length(); i++) {
                    JSONObject jsonPostObject = posts.optJSONObject(i);
                    int id = jsonPostObject.optInt("id");
                    String title = jsonPostObject.optString("title");
                    String body = jsonPostObject.optString("body");
                    int userId = jsonPostObject.optInt("userId");
                    Post post = new Post(id, title, body, userId);
                    mPostList.add(post);
                }
            } catch (final JSONException e) {
                return e;
            }
        }
        return mPostList;
    }

    @Override
    protected void onPostExecute(Object result) {
        if (result instanceof Exception) {
            mListener.onFail((Exception) result);
        } else {
            mListener.onSuccess((ArrayList<Post>) result);
            //mPostAdapter.notifyDataSetChanged();
        }

    }

    public void cancel() {
        cancel(true);
    }
}

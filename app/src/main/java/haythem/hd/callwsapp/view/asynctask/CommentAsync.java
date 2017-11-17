package haythem.hd.callwsapp.view.asynctask;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import haythem.hd.callwsapp.model.Comment;
import haythem.hd.callwsapp.utils.Constantes;
import haythem.hd.callwsapp.utils.HttpHandler;

/**
 * Created by Haythem on 15/11/2017.
 */

public class CommentAsync extends AsyncTask<Integer, Void, Object> {

    private CommentListener mListener;

    public CommentAsync(CommentListener mListener) {
        this.mListener = mListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Object doInBackground(Integer... integers) {
        return getObject(integers[0]);
    }

    @NonNull
    private Object getObject(Integer integer) {
        String jsonStr = HttpHandler.makeServiceCall(Constantes.URL_WS_COMMENT + integer);
        ArrayList<Object> mCommentList = new ArrayList<>();
        if (jsonStr != null) {
            try {
                JSONArray posts = new JSONArray(jsonStr);
                getCommentArray(posts);
            } catch (final JSONException e) {
                return e;
            }
        }
        return mCommentList;
    }


    @Override
    protected void onPostExecute(Object result) {

        if (result instanceof Exception) {
            mListener.onFail((Exception) result);
        } else {
            mListener.onSuccess((ArrayList<Comment>) result);
        }
    }


    private ArrayList<Object> getCommentArray(JSONArray posts) throws JSONException {
        ArrayList<Object> mCommentList = new ArrayList<>();
        for (int i = 0; i < posts.length(); i++) {
            JSONObject jsonPostObject = posts.getJSONObject(i);

            int postId = jsonPostObject.getInt("postId");
            int id = jsonPostObject.getInt("id");
            String name = jsonPostObject.getString("name");
            String email = jsonPostObject.getString("email");
            String body = jsonPostObject.getString("body");

            Comment comment = new Comment(postId, id, name, email, body);

            mCommentList.add(comment);
        }
        return mCommentList;
    }
    public void cancel() {
        cancel(true);
    }
}


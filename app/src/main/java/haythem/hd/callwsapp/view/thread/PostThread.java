package haythem.hd.callwsapp.view.thread;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

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

public class PostThread extends Thread {


    private Handler mPostHandler;

    public PostThread(Handler postHandler) {
        mPostHandler = mPostHandler;
    }

    @Override
    public void run() {
        String jsonStr = HttpHandler.makeServiceCall(Constantes.URL_WS_POST);
        ArrayList<Post> mPostList = new ArrayList<>();

        if (jsonStr != null) {
            try {
                JSONArray posts = new JSONArray(jsonStr);

                for (int i = 0; i < posts.length(); i++) {
                    JSONObject jsonPostObject = posts.getJSONObject(i);

                    int id = jsonPostObject.getInt("id");
                    String title = jsonPostObject.getString("title");
                    String body = jsonPostObject.getString("body");
                    int userId = jsonPostObject.getInt("userId");

                    Post post = new Post(id, title, body, userId);

                    mPostList.add(post);

                }
            } catch (final JSONException e) {
                Log.e("", "Json parsing error: " + e.getMessage());
               // mListener.onFail(e);
            }
        }

        Message msg = new Message();
       // mListener.onSuccess(mPostList);
        msg.obj = mPostList;
        mPostHandler.sendMessage(msg);
    }
}


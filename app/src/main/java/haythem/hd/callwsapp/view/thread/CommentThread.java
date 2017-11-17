package haythem.hd.callwsapp.view.thread;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

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

public class CommentThread extends Thread {


    private Handler mCommentHandler;
    private int mIdPost;

    public CommentThread(Handler commentHandler, int id) {
        mCommentHandler = commentHandler;
        mIdPost = id;
    }

    @Override
    public void run() {
        String jsonStr = HttpHandler.makeServiceCall(Constantes.URL_WS_COMMENT + mIdPost);
        ArrayList<Comment> mCommentList = new ArrayList<>();

        if (jsonStr != null) {
            try {
                JSONArray posts = new JSONArray(jsonStr);

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
            } catch (final JSONException e) {
                Log.e("", "Json parsing error: " + e.getMessage());
                // mListener.onFail(e);
            }
        }

        Message msg = new Message();
        // mListener.onSuccess(mPostList);
        msg.obj = mCommentList;
        mCommentHandler.sendMessage(msg);
    }
}

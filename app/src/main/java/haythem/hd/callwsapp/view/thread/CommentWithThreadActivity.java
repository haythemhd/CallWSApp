package haythem.hd.callwsapp.view.thread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import haythem.hd.callwsapp.R;
import haythem.hd.callwsapp.adapter.CommentAdapter;
import haythem.hd.callwsapp.model.Comment;
import haythem.hd.callwsapp.utils.Constantes;
import haythem.hd.callwsapp.utils.HttpHandler;


public class CommentWithThreadActivity extends AppCompatActivity {
    private int idPost;
    private RecyclerView mRecycleView;
    private ArrayList<Comment> mCommentList = new ArrayList<>();
    private Handler mHandler;
    private CommentAdapter mCommentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts_comment);
        idPost = getIntent().getIntExtra(Constantes.KEY_ID_POST, 0);

        mRecycleView = findViewById(R.id.recycleview_post);
        mRecycleView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mCommentAdapter = new CommentAdapter(mCommentList);
        mRecycleView.setAdapter(mCommentAdapter);

        getSupportActionBar().setTitle("Comments");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                HttpHandler sh = new HttpHandler();
                String jsonStr = sh.makeServiceCall(Constantes.URL_WS_COMMENT + idPost);
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
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),
                                        "Json parsing error: " + e.getMessage(),
                                        Toast.LENGTH_LONG)
                                        .show();
                            }
                        });
                    }

                    Message msg = new Message();
                    msg.obj = Constantes.LOG_WEB_SERVICE;
                    mHandler.sendMessage(msg);
                }
            }
        });

        thread.start();

        mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                mCommentAdapter.notifyDataSetChanged();
                return true;
            }
        });

    }


}
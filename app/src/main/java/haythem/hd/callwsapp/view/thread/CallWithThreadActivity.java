package haythem.hd.callwsapp.view.thread;

import android.content.Intent;
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
import haythem.hd.callwsapp.adapter.PostAdapter;
import haythem.hd.callwsapp.model.Post;
import haythem.hd.callwsapp.utils.Constantes;
import haythem.hd.callwsapp.utils.HttpHandler;

public class CallWithThreadActivity extends AppCompatActivity implements PostAdapter.OnItemClickListener {

    private RecyclerView mRecycleView;
    private ArrayList<Post> mPostList = new ArrayList<>();
    private Handler mHandler;
    private PostAdapter postAdapter;
    private PostAdapter.OnItemClickListener onItemClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts_comment);

        mRecycleView = findViewById(R.id.recycleview_post);
        mRecycleView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        getSupportActionBar().setTitle("Posts With Thread");

        postAdapter = new PostAdapter(mPostList, this);
        mRecycleView.setAdapter(postAdapter);


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                HttpHandler sh = new HttpHandler();
                String jsonStr = sh.makeServiceCall(Constantes.URL_WS_POST);
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
                postAdapter.notifyDataSetChanged();
                return true;
            }
        });

    }

    @Override
    public void onItemClick(int position) {
        Intent in = new Intent(this, CommentWithThreadActivity.class);
        in.putExtra(Constantes.KEY_ID_POST, position + 1);
        startActivity(in);
    }
}

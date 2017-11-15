package haythem.hd.callwsapp.view.thread;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import haythem.hd.callwsapp.R;
import haythem.hd.callwsapp.adapter.PostAdapter;
import haythem.hd.callwsapp.model.Post;
import haythem.hd.callwsapp.utils.Constantes;
import haythem.hd.callwsapp.view.asynctask.PostListener;

public class CallWithThreadActivity extends AppCompatActivity implements PostAdapter.OnItemClickListener,PostListener {

    private RecyclerView mRecycleView;
    private ArrayList<Post> mPostList = new ArrayList<>();
    private Handler mHandler;
    private PostAdapter mPostAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts_comment);

        mRecycleView = findViewById(R.id.recycleview_post);
        mRecycleView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        getSupportActionBar().setTitle("Posts With Thread");

        mPostAdapter = new PostAdapter(mPostList, this);
        mRecycleView.setAdapter(mPostAdapter);

        mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                Object list = (ArrayList<Object>) msg.obj;
                if (list instanceof Exception) {
                    onFail((Exception) msg.obj);
                } else {
                    onSuccess((ArrayList<Post>) list);
                    //mPostAdapter.notifyDataSetChanged();
                }                return true;
            }
        });

        PostThread postThread = new PostThread(mHandler);

        postThread.start();



    }

    @Override
    public void onItemClick(int position) {
        Intent in = new Intent(this, CommentWithThreadActivity.class);
        in.putExtra(Constantes.KEY_ID_POST, position + 1);
        startActivity(in);
    }

    @Override
    public void onSuccess(ArrayList<Post> posts) {
        mPostAdapter.onResult(posts);
    }

    @Override
    public void onFail(Exception e) {
        Log.i(Constantes.LOG,"Erreur");
    }
}

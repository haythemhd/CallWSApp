package haythem.hd.callwsapp.view.thread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import haythem.hd.callwsapp.R;
import haythem.hd.callwsapp.adapter.CommentAdapter;
import haythem.hd.callwsapp.model.Comment;
import haythem.hd.callwsapp.utils.Constantes;
import haythem.hd.callwsapp.view.asynctask.CommentListener;


public class CommentWithThreadActivity extends AppCompatActivity implements CommentListener {
    private int mIdPost;
    private RecyclerView mRecycleView;
    private ArrayList<Comment> mCommentList = new ArrayList<>();
    private Handler mHandler;
    private CommentAdapter mCommentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts_comment);
        mIdPost = getIntent().getIntExtra(Constantes.KEY_ID_POST, 0);

        mRecycleView = findViewById(R.id.recycleview_post);
        mRecycleView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mCommentAdapter = new CommentAdapter(mCommentList);
        mRecycleView.setAdapter(mCommentAdapter);

        getSupportActionBar().setTitle("Comments");

        mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                Object list = (ArrayList<Object>) msg.obj;
                if (list instanceof Exception) {
                    onFail((Exception) msg.obj);
                } else {
                    onSuccess((ArrayList<Comment>) list);
                    //mPostAdapter.notifyDataSetChanged();
                }                return true;
            }
        });

        CommentThread commentThread = new CommentThread(mHandler,mIdPost);

        commentThread.start();
    }


    @Override
    public void onSuccess(ArrayList<Comment> comments) {
        mCommentAdapter.onResult(comments);

    }

    @Override
    public void onFail(Exception e) {
        Log.i(Constantes.LOG,"Erreur");
    }
}
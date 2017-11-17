package haythem.hd.callwsapp.view.asynctask;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import haythem.hd.callwsapp.R;
import haythem.hd.callwsapp.adapter.CommentAdapter;
import haythem.hd.callwsapp.model.Comment;
import haythem.hd.callwsapp.utils.Constantes;


public class CommentWithAsyncActivity extends AppCompatActivity implements CommentListener {
    private int mIdPost;
    private RecyclerView mRecycleView;
    private ArrayList<Comment> mCommentList = new ArrayList<>();
    private CommentAdapter mCommentAdapter;
    private AlertDialog mDialog;
    private CommentAsync mCommentAsync;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts_comment);
        mIdPost = getIntent().getIntExtra(Constantes.KEY_ID_POST, 0);

        getSupportActionBar().setTitle("Comment With AsyncTask");

        mRecycleView = findViewById(R.id.recycleview_post);
        mRecycleView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        mCommentAdapter = new CommentAdapter(mCommentList);
        mRecycleView.setAdapter(mCommentAdapter);

        View progressView = getLayoutInflater().inflate(R.layout.progress, null);
        mDialog = new AlertDialog.Builder(this).setView(progressView).create();
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        mDialog.setCancelable(false);
        mDialog.show();


        mCommentAsync = new CommentAsync(this);
        mCommentAsync.execute(mIdPost);
     }


    @Override
    public void onSuccess(ArrayList<Comment> comments) {
        mCommentAdapter.onResult(comments);
        mDialog.dismiss();

    }

    @Override
    public void onFail(Exception e) {
        Log.i(Constantes.LOG,"Erreur");
        mDialog.dismiss();
    }


    @Override
    protected void onStart() {
        Log.e(Constantes.LOG,"OnStart");
        super.onStart();
    }

    @Override
    protected void onPause() {
        Log.e(Constantes.LOG,"OnPause");
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (mCommentAsync!=null)
            mCommentAsync.cancel();
        super.onDestroy();
    }
}
package haythem.hd.callwsapp.view.asynctask;

import android.content.Intent;
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
import haythem.hd.callwsapp.adapter.PostAdapter;
import haythem.hd.callwsapp.model.Post;
import haythem.hd.callwsapp.utils.Constantes;

public class CallWithAsyncActivity extends AppCompatActivity implements PostAdapter.OnItemClickListener,PostListener {
    private RecyclerView mRecycleView;
    private ArrayList<Post> mPostList = new ArrayList<>();
    private PostAdapter mPostAdapter;
    private AlertDialog mDialog;
    private PostAsync postAsync;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts_comment);

        getSupportActionBar().setTitle(R.string.post_async);

        mRecycleView = findViewById(R.id.recycleview_post);
        mRecycleView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        mPostAdapter = new PostAdapter(mPostList, this);
        mRecycleView.setAdapter(mPostAdapter);

        View progressView = getLayoutInflater().inflate(R.layout.progress, null);
        mDialog = new AlertDialog.Builder(this).setView(progressView).create();
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        mDialog.setCancelable(false);

        postAsync = new PostAsync(this);
        postAsync.execute();

    }

    @Override
    public void onItemClick(int position) {
        Intent in = new Intent(this, CommentWithAsyncActivity.class);
        in.putExtra(Constantes.KEY_ID_POST, position + 1);
        startActivity(in);
    }


    @Override
    public void onSuccess(ArrayList<Post> posts) {
        mPostAdapter.onResult(posts);
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
        if (postAsync!=null)
            postAsync.cancel();
        super.onDestroy();
    }
}

package haythem.hd.callwsapp.view.retrofit;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import haythem.hd.callwsapp.R;
import haythem.hd.callwsapp.adapter.PostAdapter;
import haythem.hd.callwsapp.model.ApiInterface;
import haythem.hd.callwsapp.model.Post;
import haythem.hd.callwsapp.utils.ApiClient;
import haythem.hd.callwsapp.utils.Constantes;
import haythem.hd.callwsapp.utils.utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallWithRetrofitActivity extends AppCompatActivity implements PostAdapter.OnItemClickListener {

    private ArrayList<Post> mPostList = new ArrayList<>();
    private PostAdapter mPostAdapter;
    private Call<ArrayList<Post>> mCall;
    private AlertDialog mDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts_comment);

        RecyclerView mRecycleView = findViewById(R.id.recycleview_post);
        mRecycleView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        getSupportActionBar().setTitle(R.string.post_retrofit);

        setProgressView(getString(R.string.nothing), false);
        mDialog.show();

        mPostAdapter = new PostAdapter(mPostList, this);
        mRecycleView.setAdapter(mPostAdapter);
        if (utils.isNetworkAvailable(this)) {
            ApiInterface apiService =
                    ApiClient.getClient().create(ApiInterface.class);

            mCall = apiService.getPosts();
            mCall.enqueue(new Callback<ArrayList<Post>>() {
                @Override
                public void onResponse(Call<ArrayList<Post>> call, Response<ArrayList<Post>> response) {
                    mPostList.addAll(response.body());
                    mPostAdapter.notifyDataSetChanged();
                    mDialog.dismiss();
                }

                @Override
                public void onFailure(Call<ArrayList<Post>> call, Throwable t) {
                    setProgressView(getString(R.string.load_problem), true);
                    mDialog.setOnCancelListener(onCancel());
                }
            });
        } else {
            setProgressView(getString(R.string.problem_connexion), true);
            mDialog.setOnCancelListener(onCancel());
        }

    }

    private void setProgressView(String txt, boolean cancel) {
        View progressView = getLayoutInflater().inflate(R.layout.progress, null);
        TextView textView = progressView.findViewById(R.id.text);
        textView.setText(txt);
        if (!txt.isEmpty())
            textView.setVisibility(View.VISIBLE);
        mDialog = new AlertDialog.Builder(this).setView(progressView).create();
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        mDialog.setCancelable(cancel);
    }

    @NonNull
    private DialogInterface.OnCancelListener onCancel() {
        return new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                finish();
            }
        };
    }

    @Override
    protected void onDestroy() {
        if (!mCall.isCanceled())
            mCall.cancel();
        super.onDestroy();
    }

    @Override
    public void onItemClick(int position) {
        Intent in = new Intent(this, CommentWithRetrofitActivity.class);
        in.putExtra(Constantes.KEY_ID_POST, position + 1);
        startActivity(in);
    }
}

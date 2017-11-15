package haythem.hd.callwsapp.view.retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import haythem.hd.callwsapp.R;
import haythem.hd.callwsapp.adapter.CommentAdapter;
import haythem.hd.callwsapp.model.ApiInterface;
import haythem.hd.callwsapp.model.Comment;
import haythem.hd.callwsapp.utils.ApiClient;
import haythem.hd.callwsapp.utils.Constantes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CommentWithRetrofitActivity extends AppCompatActivity {
    private int mIdPost;
    private RecyclerView mRecycleView;
    private ArrayList<Comment> mCommentList = new ArrayList<>();
    private CommentAdapter mCommentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts_comment);
        mIdPost = getIntent().getIntExtra(Constantes.KEY_ID_POST, 0);

        getSupportActionBar().setTitle("Comment With Retrofit");

        mRecycleView = findViewById(R.id.recycleview_post);
        mRecycleView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mCommentAdapter = new CommentAdapter(mCommentList);
        mRecycleView.setAdapter(mCommentAdapter);

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<ArrayList<Comment>> call = apiService.getCommentById(mIdPost);
        call.enqueue(new Callback<ArrayList<Comment>>() {
            @Override
            public void onResponse(Call<ArrayList<Comment>> call, Response<ArrayList<Comment>> response) {
                mCommentList.addAll(response.body());
                mCommentAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<Comment>> call, Throwable t) {

            }

        });
    }
}
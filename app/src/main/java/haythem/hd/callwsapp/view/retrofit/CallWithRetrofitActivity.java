package haythem.hd.callwsapp.view.retrofit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import haythem.hd.callwsapp.R;
import haythem.hd.callwsapp.adapter.PostAdapter;
import haythem.hd.callwsapp.model.ApiInterface;
import haythem.hd.callwsapp.model.Post;
import haythem.hd.callwsapp.utils.ApiClient;
import haythem.hd.callwsapp.utils.Constantes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallWithRetrofitActivity extends AppCompatActivity implements PostAdapter.OnItemClickListener {

    private RecyclerView mRecycleView;
    private ArrayList<Post> mPostList = new ArrayList<>();
    private PostAdapter postAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts_comment);

        mRecycleView = findViewById(R.id.recycleview_post);
        mRecycleView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        getSupportActionBar().setTitle("Posts With Retrofit");

        postAdapter = new PostAdapter(mPostList, this);
        mRecycleView.setAdapter(postAdapter);

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<ArrayList<Post>> call = apiService.getPosts();
        call.enqueue(new Callback<ArrayList<Post>>() {
            @Override
            public void onResponse(Call<ArrayList<Post>> call, Response<ArrayList<Post>> response) {
                mPostList.addAll(response.body());
                postAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<Post>> call, Throwable t) {

            }

        });

    }


    @Override
    public void onItemClick(int position) {
        Intent in = new Intent(this, CommentWithRetrofitActivity.class);
        in.putExtra(Constantes.KEY_ID_POST, position + 1);
        startActivity(in);
    }
}

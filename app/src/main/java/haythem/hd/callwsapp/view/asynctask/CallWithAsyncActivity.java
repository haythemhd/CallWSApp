package haythem.hd.callwsapp.view.asynctask;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
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

public class CallWithAsyncActivity extends AppCompatActivity implements PostAdapter.OnItemClickListener {
    private RecyclerView mRecycleView;
    private ArrayList<Post> mPostList = new ArrayList<>();
    private PostAdapter mPostAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts_comment);

        mRecycleView = findViewById(R.id.recycleview_post);

        mRecycleView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        getSupportActionBar().setTitle("Posts With AsyncTask");

        mPostAdapter = new PostAdapter(mPostList,this);
        mRecycleView.setAdapter(mPostAdapter);


        PostAsync postAsync=new PostAsync();
        postAsync.execute();

    }



    private class PostAsync extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {

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
                }};
            return null;

        }

        @Override
        protected void onPostExecute(Void result) {
            mPostAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public void onItemClick(int position) {
        Intent in = new Intent(this,CommentWithAsyncActivity.class);
        in.putExtra(Constantes.KEY_ID_POST,position+1);
        startActivity(in);
    }
}

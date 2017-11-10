package haythem.hd.callwsapp.model;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Haythem on 10/11/2017.
 */

public interface ApiInterface {

    @GET("posts/")
    Call<ArrayList<Post>> getPosts();

    @GET("comments")
    Call<ArrayList<Comment>> getCommentById(@Query("postId") int id);
}

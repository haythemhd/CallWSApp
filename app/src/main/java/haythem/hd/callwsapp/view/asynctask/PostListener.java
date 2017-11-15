package haythem.hd.callwsapp.view.asynctask;

import java.util.ArrayList;

import haythem.hd.callwsapp.model.Post;

/**
 * Created by Haythem on 15/11/2017.
 */

public interface PostListener {
    void onSuccess(ArrayList<Post> posts);
    void onFail(Exception e);
}

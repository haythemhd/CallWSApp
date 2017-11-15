package haythem.hd.callwsapp.view.asynctask;

import java.util.ArrayList;

import haythem.hd.callwsapp.model.Comment;

/**
 * Created by Haythem on 15/11/2017.
 */

public interface CommentListener {
    void onSuccess(ArrayList<Comment> comments);
    void onFail(Exception e);
}

package haythem.hd.callwsapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import haythem.hd.callwsapp.R;
import haythem.hd.callwsapp.model.Comment;

/**
 * Created by Haythem on 09/11/2017.
 */

public class CommentAdapter extends RecyclerView.Adapter<ViewHolder> {
    private ArrayList<Comment> mDataComment;

    public CommentAdapter(ArrayList<Comment> myDataset) {
        mDataComment = myDataset;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_post_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Comment comment = mDataComment.get(position);
        holder.setId(String.valueOf(comment.getPostId()));
        String title = comment.getmName() + " (" + comment.getmEmail() + ") " + comment.getmId();
        holder.setTitle(title);
        holder.setBody(comment.getmBody());
    }

    @Override
    public int getItemCount() {
        return mDataComment.size();
    }

    public void onResult(ArrayList<Comment> comments) {
        mDataComment.addAll(comments);
        notifyDataSetChanged();
    }

}

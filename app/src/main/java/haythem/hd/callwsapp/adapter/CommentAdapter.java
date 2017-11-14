package haythem.hd.callwsapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import haythem.hd.callwsapp.R;
import haythem.hd.callwsapp.model.Comment;

/**
 * Created by Haythem on 09/11/2017.
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.MyViewHolder> {
    private ArrayList<Comment> mDataComment;

    public CommentAdapter(ArrayList<Comment> myDataset) {
        mDataComment = myDataset;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_post_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Comment comment = mDataComment.get(position);
        holder.mId.setText(String.valueOf(comment.getmPostId()));
        String title = comment.getmName() + " (" + comment.getmEmail() + ") " + comment.getmId();
        holder.mTitle.setText(title);
        holder.mBody.setText(comment.getmBody());
    }

    @Override
    public int getItemCount() {
        return mDataComment.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mId, mTitle, mBody;

        public MyViewHolder(View view) {
            super(view);
            mId = view.findViewById(R.id.id);
            mTitle = view.findViewById(R.id.title);
            mBody = view.findViewById(R.id.body);
        }
    }

}

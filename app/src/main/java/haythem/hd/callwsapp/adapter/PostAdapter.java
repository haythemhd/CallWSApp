package haythem.hd.callwsapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import haythem.hd.callwsapp.R;
import haythem.hd.callwsapp.model.Post;


public class PostAdapter extends RecyclerView.Adapter<ViewHolder> {

    private ArrayList<Post> mDataPost;
    private OnItemClickListener onItemClickListener = null;

    public PostAdapter(ArrayList<Post> data, OnItemClickListener listener) {
        mDataPost = data;
        this.onItemClickListener = listener;
    }


    public void onResult(ArrayList<Post> posts) {
        mDataPost = posts;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_post_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Post post = mDataPost.get(position);
        holder.setId(String.valueOf(post.getmId()));
        holder.setTitle(post.getmTitle());
        holder.setBody(post.getmBody());
        holder.bind(position, onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return mDataPost.size();
    }


    public interface OnItemClickListener {
        void onItemClick(int position);
    }


}
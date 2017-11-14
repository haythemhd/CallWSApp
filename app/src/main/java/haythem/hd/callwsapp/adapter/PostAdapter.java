package haythem.hd.callwsapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import haythem.hd.callwsapp.R;
import haythem.hd.callwsapp.model.Post;


public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {

    private ArrayList<Post> mDataPost;
    private OnItemClickListener listener = null;

    public PostAdapter(ArrayList<Post> myDataset, OnItemClickListener listener) {
        mDataPost = myDataset;
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_post_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Post post = mDataPost.get(position);
        holder.id.setText(String.valueOf(post.getmId()));
        holder.title.setText(post.getmTitle());
        holder.body.setText(post.getmBody());
        holder.bind(position, listener);
    }

    @Override
    public int getItemCount() {
        return mDataPost.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView id, title, body;

        public MyViewHolder(View view) {
            super(view);
            id = view.findViewById(R.id.id);
            title = view.findViewById(R.id.title);
            body = view.findViewById(R.id.body);
        }

        public void bind(final int position, final OnItemClickListener listener) {

            body.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(position);
                }
            });
        }
    }

}
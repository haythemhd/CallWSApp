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
    private OnItemClickListener onItemClickListener = null;

    public PostAdapter(ArrayList<Post> myDataset, OnItemClickListener listener) {
        mDataPost = myDataset;
        this.onItemClickListener = listener;
    }


    public void onResult(ArrayList<Post> posts){
        mDataPost = posts;
        notifyDataSetChanged();
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
        holder.mId.setText(String.valueOf(post.getmId()));
        holder.mTitle.setText(post.getmTitle());
        holder.mBody.setText(post.getmBody());
        holder.bind(position, onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return mDataPost.size();
    }


    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView mId, mTitle, mBody;

        private MyViewHolder(View view) {
            super(view);
            mId = view.findViewById(R.id.id);
            mTitle = view.findViewById(R.id.title);
            mBody = view.findViewById(R.id.body);
        }

        private void bind(final int position, final OnItemClickListener listener) {

            mBody.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(position);
                }
            });
        }
    }

}
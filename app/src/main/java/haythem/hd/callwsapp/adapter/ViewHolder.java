package haythem.hd.callwsapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import haythem.hd.callwsapp.R;

/**
 * Created by Haythem on 17/11/2017.
 */

public class ViewHolder extends RecyclerView.ViewHolder {

    private TextView mId, mTitle, mBody;

    protected ViewHolder(View view) {
        super(view);
        mId = view.findViewById(R.id.id);
        mTitle = view.findViewById(R.id.title);
        mBody = view.findViewById(R.id.body);
    }

    protected void bind(final int position, final PostAdapter.OnItemClickListener listener) {

        mBody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(position);
            }
        });
    }

    public TextView getId() {
        return mId;
    }

    public void setId(String id) {
        mId.setText(id);
    }

    public TextView getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle.setText(title);
    }

    public TextView getBody() {
        return mBody;
    }

    public void setBody(String body) {
        mBody.setText(body);
    }

}
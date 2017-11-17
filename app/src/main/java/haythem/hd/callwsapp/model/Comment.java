package haythem.hd.callwsapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Haythem on 09/11/2017.
 */

public class Comment {
    @SerializedName("postId")
    private int mPostId;

    @SerializedName("id")
    private int mId;

    @SerializedName("name")
    private String mName;

    @SerializedName("email")
    private String mEmail;

    @SerializedName("body")
    private String mBody;


    public Comment() {
    }

    public Comment(int mPostId, int mId, String mName, String mEmail, String mBody) {
        mPostId = mPostId;
        mId = mId;
        mName = mName;
        mEmail = mEmail;
        mBody = mBody;
    }

    public int getPostId() {
        return mPostId;
    }

    public void setPostId(int postId) {
        mPostId = postId;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        mName = mName;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        mEmail = mEmail;
    }

    public String getmBody() {
        return mBody;
    }

    public void setmBody(String mBody) {
        mBody = mBody;
    }
}

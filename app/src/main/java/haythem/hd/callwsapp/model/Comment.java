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

    @SerializedName("post")
    private String mBody;


    public Comment() {
    }

    public Comment(int mPostId, int mId, String mName, String mEmail, String mBody) {
        this.mPostId = mPostId;
        this.mId = mId;
        this.mName = mName;
        this.mEmail = mEmail;
        this.mBody = mBody;
    }

    public int getmPostId() {
        return mPostId;
    }

    public void setmPostId(int mPostId) {
        this.mPostId = mPostId;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmBody() {
        return mBody;
    }

    public void setmBody(String mBody) {
        this.mBody = mBody;
    }
}

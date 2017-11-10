package haythem.hd.callwsapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Haythem on 09/11/2017.
 */

public class Post {
    @SerializedName("id")
    private int mId;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("body")
    private String mBody;

    @SerializedName("userId")
    private int mUserID;

    public Post() {
    }

    public Post(int mId, String mTitle, String mBody, int mUserID) {
        this.mId = mId;
        this.mTitle = mTitle;
        this.mBody = mBody;
        this.mUserID = mUserID;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmBody() {
        return mBody;
    }

    public void setmBody(String mBody) {
        this.mBody = mBody;
    }

    public int getmUserID() {
        return mUserID;
    }

    public void setmUserID(int mUserID) {
        this.mUserID = mUserID;
    }
}

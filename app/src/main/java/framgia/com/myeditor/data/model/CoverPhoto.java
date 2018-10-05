package framgia.com.myeditor.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CoverPhoto implements Parcelable {
    public static final Creator<CoverPhoto> CREATOR = new Creator<CoverPhoto>() {
        @Override
        public CoverPhoto createFromParcel(Parcel source) {
            return new CoverPhoto(source);
        }

        @Override
        public CoverPhoto[] newArray(int size) {
            return new CoverPhoto[size];
        }
    };
    @SerializedName("id")
    @Expose
    private String mId;
    @SerializedName("created_at")
    @Expose
    private String mCreatedAt;
    @SerializedName("updated_at")
    @Expose
    private String mUpdatedAt;
    @SerializedName("width")
    @Expose
    private Integer mWidth;
    @SerializedName("height")
    @Expose
    private Integer mHeight;
    @SerializedName("description")
    @Expose
    private String mDescription;
    @SerializedName("urls")
    @Expose
    private UrlImage mUrls;
    @SerializedName("links")
    @Expose
    private Links mLinks;
    @SerializedName("user")
    @Expose
    private User mUser;

    protected CoverPhoto(Parcel in) {
        mId = in.readString();
        mCreatedAt = in.readString();
        mUpdatedAt = in.readString();
        mWidth = (Integer) in.readValue(Integer.class.getClassLoader());
        mHeight = (Integer) in.readValue(Integer.class.getClassLoader());
        mDescription = in.readString();
        mUrls = in.readParcelable(UrlImage.class.getClassLoader());
        mLinks = in.readParcelable(Links.class.getClassLoader());
        mUser = in.readParcelable(User.class.getClassLoader());
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        mCreatedAt = createdAt;
    }

    public String getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        mUpdatedAt = updatedAt;
    }

    public Integer getWidth() {
        return mWidth;
    }

    public void setWidth(Integer width) {
        mWidth = width;
    }

    public Integer getHeight() {
        return mHeight;
    }

    public void setHeight(Integer height) {
        mHeight = height;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public UrlImage getUrls() {
        return mUrls;
    }

    public void setUrls(UrlImage urls) {
        mUrls = urls;
    }

    public Links getLinks() {
        return mLinks;
    }

    public void setLinks(Links links) {
        mLinks = links;
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mId);
        dest.writeString(mCreatedAt);
        dest.writeString(mUpdatedAt);
        dest.writeValue(mWidth);
        dest.writeValue(mHeight);
        dest.writeString(mDescription);
        dest.writeParcelable(mUrls, flags);
        dest.writeParcelable(mLinks, flags);
        dest.writeParcelable(mUser, flags);
    }
}

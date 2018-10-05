package framgia.com.myeditor.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Collection implements Parcelable {

    public static final Creator<Collection> CREATOR = new Creator<Collection>() {
        @Override
        public Collection createFromParcel(Parcel source) {
            return new Collection(source);
        }

        @Override
        public Collection[] newArray(int size) {
            return new Collection[size];
        }
    };
    @SerializedName("id")
    @Expose
    private Integer mId;
    @SerializedName("title")
    @Expose
    private String mTitle;
    @SerializedName("description")
    @Expose
    private String mDescription;
    @SerializedName("published_at")
    @Expose
    private String mPublishedAt;
    @SerializedName("updated_at")
    @Expose
    private String mUpdatedAt;
    @SerializedName("featured")
    @Expose
    private Boolean mFeatured;
    @SerializedName("total_photos")
    @Expose
    private Integer mTotalPhotos;
    @SerializedName("cover_photo")
    @Expose
    private CoverPhoto mCoverPhoto;

    protected Collection(Parcel in) {
        mId = (Integer) in.readValue(Integer.class.getClassLoader());
        mTitle = in.readString();
        mDescription = in.readString();
        mPublishedAt = in.readString();
        mUpdatedAt = in.readString();
        mFeatured = (Boolean) in.readValue(Boolean.class.getClassLoader());
        mTotalPhotos = (Integer) in.readValue(Integer.class.getClassLoader());
        mCoverPhoto = in.readParcelable(CoverPhoto.class.getClassLoader());
    }

    public CoverPhoto getCoverPhoto() {
        return mCoverPhoto;
    }

    public void setCoverPhoto(CoverPhoto coverPhoto) {
        mCoverPhoto = coverPhoto;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    public String getPublishedAt() {
        return mPublishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        mPublishedAt = publishedAt;
    }

    public String getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        mUpdatedAt = updatedAt;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public Boolean getFeatured() {
        return mFeatured;
    }

    public void setFeatured(Boolean featured) {
        mFeatured = featured;
    }

    public Integer getTotalPhotos() {
        return mTotalPhotos;
    }

    public void setTotalPhotos(Integer totalPhotos) {
        mTotalPhotos = totalPhotos;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(mId);
        dest.writeString(mTitle);
        dest.writeString(mDescription);
        dest.writeString(mPublishedAt);
        dest.writeString(mUpdatedAt);
        dest.writeValue(mFeatured);
        dest.writeValue(mTotalPhotos);
        dest.writeParcelable(mCoverPhoto, flags);
    }
}

package framgia.com.myeditor.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User implements Parcelable {

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
    @SerializedName("id")
    @Expose
    private String mId;
    @SerializedName("updated_at")
    @Expose
    private String mUpdatedAt;
    @SerializedName("username")
    @Expose
    private String mUsername;
    @SerializedName("name")
    @Expose
    private String mName;
    @SerializedName("first_name")
    @Expose
    private String mFirstName;
    @SerializedName("last_name")
    @Expose
    private String mLastName;
    @SerializedName("location")
    @Expose
    private String mLocation;
    @SerializedName("instagram_username")
    @Expose
    private String mInstagramUsername;

    protected User(Parcel in) {
        mId = in.readString();
        mUpdatedAt = in.readString();
        mUsername = in.readString();
        mName = in.readString();
        mFirstName = in.readString();
        mLastName = in.readString();
        mLocation = in.readString();
        mInstagramUsername = in.readString();
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        mUpdatedAt = updatedAt;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        mLocation = location;
    }

    public String getInstagramUsername() {
        return mInstagramUsername;
    }

    public void setInstagramUsername(String instagramUsername) {
        mInstagramUsername = instagramUsername;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mId);
        dest.writeString(mUpdatedAt);
        dest.writeString(mUsername);
        dest.writeString(mName);
        dest.writeString(mFirstName);
        dest.writeString(mLastName);
        dest.writeString(mLocation);
        dest.writeString(mInstagramUsername);
    }
}

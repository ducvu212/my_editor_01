package framgia.com.myeditor.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemViewPager implements Parcelable {

    private String mPath;
    public static final Creator<ItemViewPager> CREATOR = new Creator<ItemViewPager>() {
        @Override
        public ItemViewPager createFromParcel(Parcel source) {
            return new ItemViewPager(source);
        }

        @Override
        public ItemViewPager[] newArray(int size) {
            return new ItemViewPager[size];
        }
    };
    private String mUserName;
    private boolean mLikeByUser;

    public String getPath() {
        return mPath;
    }

    public void setPath(String path) {
        mPath = path;
    }

    private ItemViewPager(Builder builder) {
        setPath(builder.mPath);
        setUserName(builder.mUserName);
        setLikeByUser(builder.mLikeByUser);
    }

    protected ItemViewPager(Parcel in) {
        mPath = in.readString();
        mUserName = in.readString();
        mLikeByUser = in.readByte() != 0;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public boolean isLikeByUser() {
        return mLikeByUser;
    }

    public void setLikeByUser(boolean likeByUser) {
        mLikeByUser = likeByUser;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mPath);
        dest.writeString(mUserName);
        dest.writeByte(mLikeByUser ? (byte) 1 : (byte) 0);
    }

    public static final class Builder {
        private String mPath;
        private String mUserName;
        private boolean mLikeByUser;

        public Builder() {
        }

        public Builder mPath(String path) {
            mPath = path;
            return this;
        }

        public Builder mUserName(String val) {
            mUserName = val;
            return this;
        }

        public Builder mLikeByUser(boolean val) {
            mLikeByUser = val;
            return this;
        }

        public ItemViewPager build() {
            return new ItemViewPager(this);
        }
    }
}

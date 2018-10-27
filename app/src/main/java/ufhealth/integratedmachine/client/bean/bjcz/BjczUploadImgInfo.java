package ufhealth.integratedmachine.client.bean.bjcz;

import android.os.Parcel;
import android.os.Parcelable;

public class BjczUploadImgInfo implements Parcelable
{
    /**
     * path : 1540611738394-51/u=371772476,1548437417&fm=27&gp=0.jpg
     * name : u=371772476,1548437417&fm=27&gp=0.jpg
     * url : /1540611738394-51/u=371772476,1548437417&fm=27&gp=0.jpg
     */

    private String path;
    private String name;
    private String url;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.path);
        dest.writeString(this.name);
        dest.writeString(this.url);
    }

    public BjczUploadImgInfo() {
    }

    protected BjczUploadImgInfo(Parcel in) {
        this.path = in.readString();
        this.name = in.readString();
        this.url = in.readString();
    }

    public static final Creator<BjczUploadImgInfo> CREATOR = new Creator<BjczUploadImgInfo>() {
        @Override
        public BjczUploadImgInfo createFromParcel(Parcel source) {
            return new BjczUploadImgInfo(source);
        }

        @Override
        public BjczUploadImgInfo[] newArray(int size) {
            return new BjczUploadImgInfo[size];
        }
    };
}

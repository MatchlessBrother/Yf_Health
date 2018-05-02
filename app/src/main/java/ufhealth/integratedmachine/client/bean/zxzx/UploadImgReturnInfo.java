package ufhealth.integratedmachine.client.bean.zxzx;

import android.os.Parcel;
import android.os.Parcelable;

public class UploadImgReturnInfo implements Parcelable
{
    private String url;
    private String key;

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public int describeContents()
    {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(this.url);
        dest.writeString(this.key);
    }

    public UploadImgReturnInfo()
    {

    }

    protected UploadImgReturnInfo(Parcel in)
    {
        this.url = in.readString();
        this.key = in.readString();
    }

    public static final Creator<UploadImgReturnInfo> CREATOR = new Creator<UploadImgReturnInfo>()
    {
        public UploadImgReturnInfo createFromParcel(Parcel source)
        {
            return new UploadImgReturnInfo(source);
        }

        public UploadImgReturnInfo[] newArray(int size)
        {
            return new UploadImgReturnInfo[size];
        }
    };
}
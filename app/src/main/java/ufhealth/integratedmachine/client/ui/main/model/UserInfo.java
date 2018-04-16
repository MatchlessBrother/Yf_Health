package ufhealth.integratedmachine.client.ui.main.model;

import android.os.Parcel;
import android.os.Parcelable;

public class UserInfo implements Parcelable
{
    public Integer id;
    public String name;
    public String headImgPath;

    public UserInfo() {}

    public UserInfo(Integer id, String name, String headImgPath)
    {
        this.id = id;
        this.name = name;
        this.headImgPath = headImgPath;
    }

    public Integer getId()
    {
        return this.id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getHeadImgPath()
    {
        return this.headImgPath;
    }

    public void setHeadImgPath(String headImgPath)
    {
        this.headImgPath = headImgPath;
    }

    public int describeContents()
    {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeString(this.headImgPath);
    }

    protected UserInfo(Parcel in)
    {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
        this.headImgPath = in.readString();
    }

    public static final Creator<UserInfo> CREATOR = new Creator<UserInfo>()
    {
        public UserInfo createFromParcel(Parcel source)
        {
            return new UserInfo(source);
        }

        public UserInfo[] newArray(int size)
        {
            return new UserInfo[size];
        }
    };
}
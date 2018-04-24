package ufhealth.integratedmachine.client.bean.zxzx;

import android.os.Parcel;
import android.os.Parcelable;

public class HotDepartment implements Parcelable
{
    private Integer id;
    private String name;
    private String imgPath;

    public HotDepartment()
    {

    }

    public HotDepartment(Integer id, String name, String imgPath)
    {
        this.id = id;
        this.name = name;
        this.imgPath = imgPath;
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

    public String getImgPath()
    {
        return this.imgPath;
    }

    public void setImgPath(String imgPath)
    {
        this.imgPath = imgPath;
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeString(this.imgPath);
    }

    protected HotDepartment(Parcel in)
    {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
        this.imgPath = in.readString();
    }

    public static final Creator<HotDepartment> CREATOR = new Creator<HotDepartment>()
    {
        @Override
        public HotDepartment createFromParcel(Parcel source)
        {
            return new HotDepartment(source);
        }

        @Override
        public HotDepartment[] newArray(int size)
        {
            return new HotDepartment[size];
        }
    };
}
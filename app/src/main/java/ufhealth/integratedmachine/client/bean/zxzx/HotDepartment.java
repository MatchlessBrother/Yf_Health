package ufhealth.integratedmachine.client.bean.zxzx;

import android.os.Parcel;
import android.os.Parcelable;

public class HotDepartment implements Parcelable
{
    private Integer id;
    private String name;
    private String img_url;

    public HotDepartment()
    {

    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg_url() {
        return this.img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeString(this.img_url);
    }

    protected HotDepartment(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
        this.img_url = in.readString();
    }

    public static final Creator<HotDepartment> CREATOR = new Creator<HotDepartment>() {
        @Override
        public HotDepartment createFromParcel(Parcel source) {
            return new HotDepartment(source);
        }

        @Override
        public HotDepartment[] newArray(int size) {
            return new HotDepartment[size];
        }
    };
}
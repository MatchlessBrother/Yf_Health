package ufhealth.integratedmachine.client.bean.main;

import android.os.Parcel;
import android.os.Parcelable;

public class UserInfos implements Parcelable
{
    private String name;
    private String token;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.token);
    }

    public UserInfos() {
    }

    protected UserInfos(Parcel in) {
        this.name = in.readString();
        this.token = in.readString();
    }

    public static final Creator<UserInfos> CREATOR = new Creator<UserInfos>() {
        @Override
        public UserInfos createFromParcel(Parcel source) {
            return new UserInfos(source);
        }

        @Override
        public UserInfos[] newArray(int size) {
            return new UserInfos[size];
        }
    };
}
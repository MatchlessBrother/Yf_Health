package ufhealth.integratedmachine.client.bean.main;

import android.os.Parcel;
import android.os.Parcelable;

public class WifiMacAddress implements Parcelable {
    private int id;
    private String address;
    private String cardReaderId;
    private String jcDeviceIdent;
    private String androidDeviceIdent;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCardReaderId() {
        return this.cardReaderId;
    }

    public void setCardReaderId(String cardReaderId) {
        this.cardReaderId = cardReaderId;
    }

    public String getJcDeviceIdent() {
        return this.jcDeviceIdent;
    }

    public void setJcDeviceIdent(String jcDeviceIdent) {
        this.jcDeviceIdent = jcDeviceIdent;
    }

    public String getAndroidDeviceIdent() {
        return this.androidDeviceIdent;
    }

    public void setAndroidDeviceIdent(String androidDeviceIdent) {
        this.androidDeviceIdent = androidDeviceIdent;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.address);
        dest.writeString(this.cardReaderId);
        dest.writeString(this.jcDeviceIdent);
        dest.writeString(this.androidDeviceIdent);
    }

    public WifiMacAddress() {
    }

    protected WifiMacAddress(Parcel in) {
        this.id = in.readInt();
        this.address = in.readString();
        this.cardReaderId = in.readString();
        this.jcDeviceIdent = in.readString();
        this.androidDeviceIdent = in.readString();
    }

    public static final Parcelable.Creator<WifiMacAddress> CREATOR = new Parcelable.Creator<WifiMacAddress>() {
        @Override
        public WifiMacAddress createFromParcel(Parcel source) {
            return new WifiMacAddress(source);
        }

        @Override
        public WifiMacAddress[] newArray(int size) {
            return new WifiMacAddress[size];
        }
    };
}
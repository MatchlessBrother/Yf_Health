package ufhealth.integratedmachine.client.bean.zxzx;

import android.os.Parcel;
import android.os.Parcelable;

public class Billinfo implements Parcelable
{
    private String payOrderNumber;
    private String payQrcodeUrl;
    private String totalPrice;

    public String getPayOrderNumber() {
        return payOrderNumber;
    }

    public void setPayOrderNumber(String payOrderNumber) {
        this.payOrderNumber = payOrderNumber;
    }

    public String getPayQrcodeUrl() {
        return payQrcodeUrl;
    }

    public void setPayQrcodeUrl(String payQrcodeUrl) {
        this.payQrcodeUrl = payQrcodeUrl;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.payOrderNumber);
        dest.writeString(this.payQrcodeUrl);
        dest.writeString(this.totalPrice);
    }

    public Billinfo() {
    }

    protected Billinfo(Parcel in) {
        this.payOrderNumber = in.readString();
        this.payQrcodeUrl = in.readString();
        this.totalPrice = in.readString();
    }

    public static final Creator<Billinfo> CREATOR = new Creator<Billinfo>() {
        @Override
        public Billinfo createFromParcel(Parcel source) {
            return new Billinfo(source);
        }

        @Override
        public Billinfo[] newArray(int size) {
            return new Billinfo[size];
        }
    };
}
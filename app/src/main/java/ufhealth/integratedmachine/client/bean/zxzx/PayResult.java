package ufhealth.integratedmachine.client.bean.zxzx;

import android.os.Parcel;
import android.os.Parcelable;

public class PayResult implements Parcelable
{
    private int payStatus;
    private String payTime;

    public int getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(int payStatus) {
        this.payStatus = payStatus;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.payStatus);
        dest.writeString(this.payTime);
    }

    public PayResult() {
    }

    protected PayResult(Parcel in) {
        this.payStatus = in.readInt();
        this.payTime = in.readString();
    }

    public static final Parcelable.Creator<PayResult> CREATOR = new Parcelable.Creator<PayResult>() {
        @Override
        public PayResult createFromParcel(Parcel source) {
            return new PayResult(source);
        }

        @Override
        public PayResult[] newArray(int size) {
            return new PayResult[size];
        }
    };
}
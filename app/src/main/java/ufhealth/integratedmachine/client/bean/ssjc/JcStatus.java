package ufhealth.integratedmachine.client.bean.ssjc;

import android.os.Parcel;
import android.os.Parcelable;
import com.contrarywind.interfaces.IPickerViewData;

public class JcStatus implements IPickerViewData, Parcelable
{
    private int statusCode;
    private String statusName;

    public JcStatus(int statusCode, String statusName) {
        this.statusCode = statusCode;
        this.statusName = statusName;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getPickerViewText() {
        return statusName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.statusCode);
        dest.writeString(this.statusName);
    }

    public JcStatus() {
    }

    protected JcStatus(Parcel in) {
        this.statusCode = in.readInt();
        this.statusName = in.readString();
    }

    public static final Creator<JcStatus> CREATOR = new Creator<JcStatus>() {
        @Override
        public JcStatus createFromParcel(Parcel source) {
            return new JcStatus(source);
        }

        @Override
        public JcStatus[] newArray(int size) {
            return new JcStatus[size];
        }
    };
}
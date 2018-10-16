package ufhealth.integratedmachine.client.bean.secondtab;

import android.os.Parcel;
import android.os.Parcelable;
import com.contrarywind.interfaces.IPickerViewData;

public class JcType implements IPickerViewData, Parcelable
{
    private int typeCode;
    private String typeName;

    public JcType(int typeCode, String typeName) {
        this.typeCode = typeCode;
        this.typeName = typeName;
    }

    public int getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(int typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getPickerViewText() {
        return typeName;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.typeCode);
        dest.writeString(this.typeName);
    }

    public JcType() {
    }

    protected JcType(Parcel in) {
        this.typeCode = in.readInt();
        this.typeName = in.readString();
    }

    public static final Creator<JcType> CREATOR = new Creator<JcType>() {
        @Override
        public JcType createFromParcel(Parcel source) {
            return new JcType(source);
        }

        @Override
        public JcType[] newArray(int size) {
            return new JcType[size];
        }
    };
}
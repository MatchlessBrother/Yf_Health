package ufhealth.integratedmachine.client.bean.main;

import android.os.Parcel;
import android.os.Parcelable;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import ufhealth.integratedmachine.client.adapter.main.JcConditionAdapter;

public class JcChildCondition implements MultiItemEntity,Parcelable
{
    private int conditionCode;
    private boolean isSelected;
    private String conditionName;

    public JcChildCondition() {
    }

    public JcChildCondition(int conditionCode, boolean isSelected, String conditionName) {
        this.conditionCode = conditionCode;
        this.isSelected = isSelected;
        this.conditionName = conditionName;
    }

    public int getConditionCode() {
        return conditionCode;
    }

    public void setConditionCode(int conditionCode) {
        this.conditionCode = conditionCode;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getConditionName() {
        return conditionName;
    }

    public void setConditionName(String conditionName) {
        this.conditionName = conditionName;
    }

    @Override
    public int getItemType() {
        return JcConditionAdapter.TYPE_CHILD;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.conditionCode);
        dest.writeByte(this.isSelected ? (byte) 1 : (byte) 0);
        dest.writeString(this.conditionName);
    }

    protected JcChildCondition(Parcel in) {
        this.conditionCode = in.readInt();
        this.isSelected = in.readByte() != 0;
        this.conditionName = in.readString();
    }

    public static final Creator<JcChildCondition> CREATOR = new Creator<JcChildCondition>() {
        @Override
        public JcChildCondition createFromParcel(Parcel source) {
            return new JcChildCondition(source);
        }

        @Override
        public JcChildCondition[] newArray(int size) {
            return new JcChildCondition[size];
        }
    };
}
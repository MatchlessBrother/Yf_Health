package ufhealth.integratedmachine.client.bean.main;

import android.os.Parcel;
import android.os.Parcelable;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import ufhealth.integratedmachine.client.adapter.main.JcConditionAdapter;

public class JcParentCondition extends AbstractExpandableItem<JcChildCondition> implements MultiItemEntity,Parcelable
{
    private int conditionCode;
    private boolean isSelected;
    private String conditionName;

    public JcParentCondition() {
    }

    public JcParentCondition(int conditionCode, boolean isSelected, String conditionName) {
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
    public int getLevel() {
        return 0;
    }

    @Override
    public int getItemType() {
        return JcConditionAdapter.TYPE_PARENT;
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

    protected JcParentCondition(Parcel in) {
        this.conditionCode = in.readInt();
        this.isSelected = in.readByte() != 0;
        this.conditionName = in.readString();
    }

    public static final Creator<JcParentCondition> CREATOR = new Creator<JcParentCondition>() {
        @Override
        public JcParentCondition createFromParcel(Parcel source) {
            return new JcParentCondition(source);
        }

        @Override
        public JcParentCondition[] newArray(int size) {
            return new JcParentCondition[size];
        }
    };
}
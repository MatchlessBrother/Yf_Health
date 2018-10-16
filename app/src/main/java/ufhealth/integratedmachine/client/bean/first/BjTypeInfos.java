package ufhealth.integratedmachine.client.bean.first;

import android.os.Parcel;
import android.os.Parcelable;

public class BjTypeInfos implements Parcelable
{
    private int iconType;
    private String noteStr;
    private int appearNumbers;
    private String backgroundColor;

    public int getIconType() {
        return iconType;
    }

    public void setIconType(int iconType) {
        this.iconType = iconType;
    }

    public String getNoteStr() {
        return noteStr;
    }

    public void setNoteStr(String noteStr) {
        this.noteStr = noteStr;
    }

    public int getAppearNumbers() {
        return appearNumbers;
    }

    public void setAppearNumbers(int appearNumbers) {
        this.appearNumbers = appearNumbers;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.iconType);
        dest.writeString(this.noteStr);
        dest.writeInt(this.appearNumbers);
        dest.writeString(this.backgroundColor);
    }

    public BjTypeInfos() {
    }

    protected BjTypeInfos(Parcel in) {
        this.iconType = in.readInt();
        this.noteStr = in.readString();
        this.appearNumbers = in.readInt();
        this.backgroundColor = in.readString();
    }

    public static final Creator<BjTypeInfos> CREATOR = new Creator<BjTypeInfos>() {
        @Override
        public BjTypeInfos createFromParcel(Parcel source) {
            return new BjTypeInfos(source);
        }

        @Override
        public BjTypeInfos[] newArray(int size) {
            return new BjTypeInfos[size];
        }
    };
}
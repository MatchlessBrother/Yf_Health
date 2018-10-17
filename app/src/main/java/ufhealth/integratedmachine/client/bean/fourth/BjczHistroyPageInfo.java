package ufhealth.integratedmachine.client.bean.fourth;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

public class BjczHistroyPageInfo implements Parcelable
{
    private int maxSizeOfPerPage;
    private List<BjczHistroyInfo> bjczHistroyInfoList;

    public int getMaxSizeOfPerPage() {
        return maxSizeOfPerPage;
    }

    public void setMaxSizeOfPerPage(int maxSizeOfPerPage) {
        this.maxSizeOfPerPage = maxSizeOfPerPage;
    }

    public List<BjczHistroyInfo> getBjczHistroyInfoList() {
        return bjczHistroyInfoList;
    }

    public void setBjczHistroyInfoList(List<BjczHistroyInfo> bjczHistroyInfoList) {
        this.bjczHistroyInfoList = bjczHistroyInfoList;
    }

    public static class BjczHistroyInfo implements Parcelable
    {
        private String time;
        private String type;
        private String partment;
        private String position;
        private String equipment;
        private String firstWarning;
        private String secondWarning;

        public BjczHistroyInfo(String time, String type, String partment, String position, String equipment, String firstWarning, String secondWarning) {
            this.time = time;
            this.type = type;
            this.partment = partment;
            this.position = position;
            this.equipment = equipment;
            this.firstWarning = firstWarning;
            this.secondWarning = secondWarning;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPartment() {
            return partment;
        }

        public void setPartment(String partment) {
            this.partment = partment;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getEquipment() {
            return equipment;
        }

        public void setEquipment(String equipment) {
            this.equipment = equipment;
        }

        public String getFirstWarning() {
            return firstWarning;
        }

        public void setFirstWarning(String firstWarning) {
            this.firstWarning = firstWarning;
        }

        public String getSecondWarning() {
            return secondWarning;
        }

        public void setSecondWarning(String secondWarning) {
            this.secondWarning = secondWarning;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.time);
            dest.writeString(this.type);
            dest.writeString(this.partment);
            dest.writeString(this.position);
            dest.writeString(this.equipment);
            dest.writeString(this.firstWarning);
            dest.writeString(this.secondWarning);
        }

        public BjczHistroyInfo() {
        }

        protected BjczHistroyInfo(Parcel in) {
            this.time = in.readString();
            this.type = in.readString();
            this.partment = in.readString();
            this.position = in.readString();
            this.equipment = in.readString();
            this.firstWarning = in.readString();
            this.secondWarning = in.readString();
        }

        public static final Creator<BjczHistroyInfo> CREATOR = new Creator<BjczHistroyInfo>() {
            @Override
            public BjczHistroyInfo createFromParcel(Parcel source) {
                return new BjczHistroyInfo(source);
            }

            @Override
            public BjczHistroyInfo[] newArray(int size) {
                return new BjczHistroyInfo[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.maxSizeOfPerPage);
        dest.writeTypedList(this.bjczHistroyInfoList);
    }

    public BjczHistroyPageInfo() {
    }

    protected BjczHistroyPageInfo(Parcel in) {
        this.maxSizeOfPerPage = in.readInt();
        this.bjczHistroyInfoList = in.createTypedArrayList(BjczHistroyInfo.CREATOR);
    }

    public static final Creator<BjczHistroyPageInfo> CREATOR = new Creator<BjczHistroyPageInfo>() {
        @Override
        public BjczHistroyPageInfo createFromParcel(Parcel source) {
            return new BjczHistroyPageInfo(source);
        }

        @Override
        public BjczHistroyPageInfo[] newArray(int size) {
            return new BjczHistroyPageInfo[size];
        }
    };
}
package ufhealth.integratedmachine.client.bean.fourth;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

public class BjczPageInfo implements Parcelable
{
    private int maxSizeOfPerPage;
    private List<BjczInfo> bjczInfoList;

    public int getMaxSizeOfPerPage() {
        return maxSizeOfPerPage;
    }

    public void setMaxSizeOfPerPage(int maxSizeOfPerPage) {
        this.maxSizeOfPerPage = maxSizeOfPerPage;
    }

    public List<BjczInfo> getBjczInfoList() {
        return bjczInfoList;
    }

    public void setBjczInfoList(List<BjczInfo> bjczInfoList) {
        this.bjczInfoList = bjczInfoList;
    }

    public static class BjczInfo implements Parcelable
    {
        private String time;
        private String type;
        private String partment;
        private String position;
        private String equipment;
        private String firstWarning;
        private String secondWarning;

        public BjczInfo() {
        }

        public BjczInfo(String time, String type, String partment, String position, String equipment, String firstWarning, String secondWarning) {
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

        protected BjczInfo(Parcel in) {
            this.time = in.readString();
            this.type = in.readString();
            this.partment = in.readString();
            this.position = in.readString();
            this.equipment = in.readString();
            this.firstWarning = in.readString();
            this.secondWarning = in.readString();
        }

        public static final Creator<BjczInfo> CREATOR = new Creator<BjczInfo>() {
            @Override
            public BjczInfo createFromParcel(Parcel source) {
                return new BjczInfo(source);
            }

            @Override
            public BjczInfo[] newArray(int size) {
                return new BjczInfo[size];
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
        dest.writeTypedList(this.bjczInfoList);
    }

    public BjczPageInfo() {
    }

    protected BjczPageInfo(Parcel in) {
        this.maxSizeOfPerPage = in.readInt();
        this.bjczInfoList = in.createTypedArrayList(BjczInfo.CREATOR);
    }

    public static final Creator<BjczPageInfo> CREATOR = new Creator<BjczPageInfo>() {
        @Override
        public BjczPageInfo createFromParcel(Parcel source) {
            return new BjczPageInfo(source);
        }

        @Override
        public BjczPageInfo[] newArray(int size) {
            return new BjczPageInfo[size];
        }
    };
}
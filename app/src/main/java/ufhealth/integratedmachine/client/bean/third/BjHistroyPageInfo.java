package ufhealth.integratedmachine.client.bean.third;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

public class BjHistroyPageInfo implements Parcelable {
    private int maxSizeOfPerPage;
    private List<BjHistroyInfo> bjHistroyInfoList;

    public int getMaxSizeOfPerPage() {
        return maxSizeOfPerPage;
    }

    public void setMaxSizeOfPerPage(int maxSizeOfPerPage) {
        this.maxSizeOfPerPage = maxSizeOfPerPage;
    }

    public List<BjHistroyInfo> getBjHistroyInfoList() {
        return bjHistroyInfoList;
    }

    public void setBjHistroyInfoList(List<BjHistroyInfo> bjHistroyInfoList) {
        this.bjHistroyInfoList = bjHistroyInfoList;
    }

    public static class BjHistroyInfo implements Parcelable {
        private String time;
        private String type;
        private String status;
        private int statusCode;
        private String partment;
        private String position;
        private String equipment;
        private String firstWarning;
        private String secondWarning;

        public BjHistroyInfo() {
        }

        public BjHistroyInfo(String time, String type, String status, int statusCode, String partment, String position, String equipment, String firstWarning, String secondWarning) {
            this.time = time;
            this.type = type;
            this.status = status;
            this.statusCode = statusCode;
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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(int statusCode) {
            this.statusCode = statusCode;
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
            dest.writeString(this.status);
            dest.writeInt(this.statusCode);
            dest.writeString(this.partment);
            dest.writeString(this.position);
            dest.writeString(this.equipment);
            dest.writeString(this.firstWarning);
            dest.writeString(this.secondWarning);
        }

        protected BjHistroyInfo(Parcel in) {
            this.time = in.readString();
            this.type = in.readString();
            this.status = in.readString();
            this.statusCode = in.readInt();
            this.partment = in.readString();
            this.position = in.readString();
            this.equipment = in.readString();
            this.firstWarning = in.readString();
            this.secondWarning = in.readString();
        }

        public static final Creator<BjHistroyInfo> CREATOR = new Creator<BjHistroyInfo>() {
            @Override
            public BjHistroyInfo createFromParcel(Parcel source) {
                return new BjHistroyInfo(source);
            }

            @Override
            public BjHistroyInfo[] newArray(int size) {
                return new BjHistroyInfo[size];
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
        dest.writeTypedList(this.bjHistroyInfoList);
    }

    public BjHistroyPageInfo() {
    }

    protected BjHistroyPageInfo(Parcel in) {
        this.maxSizeOfPerPage = in.readInt();
        this.bjHistroyInfoList = in.createTypedArrayList(BjHistroyInfo.CREATOR);
    }

    public static final Creator<BjHistroyPageInfo> CREATOR = new Creator<BjHistroyPageInfo>() {
        @Override
        public BjHistroyPageInfo createFromParcel(Parcel source) {
            return new BjHistroyPageInfo(source);
        }

        @Override
        public BjHistroyPageInfo[] newArray(int size) {
            return new BjHistroyPageInfo[size];
        }
    };
}
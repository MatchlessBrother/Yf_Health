package ufhealth.integratedmachine.client.bean.fourth;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

public class BjczPageInfo implements Parcelable
{
    /**
     * totalRows : 1
     * totalPages : 1
     * pageIndex : 0
     * pageSize : 20
     * content : [{"id":1,"departmentId":5,"departmentName":"炼油运行一部","sensorId":3,"sensorName":"AIA003","address":"成品泵403南","deviceAreaId":4,"deviceAreaName":"2#常减压装置","childCategoryId":8,"childCategoryName":"氢气","parentCategoryId":1,"parentCategoryName":"易燃易爆气体","alarmLevelId":3,"alarmLevelName":"预警","alarmValue":40,"realTimeData":"","alarmStartTime":1539619200000,"alarmEndTime":1539662400000,"alarmTotalNumber":0,"handleStatus":1,"handleStatusDescription":"未处置","handleTime":"","handlePeopleId":"","handlePeopleName":"","handleDescription":"","dataSyncStatus":1,"dataSyncStatusDescription":"异常","alarmSettingDescription":">=50%LLE/PPM","unit":"%LLA/PPM","ruleType":"","ruleValue":"","alarmNumber":1}]
     */
    private int totalRows;
    private int totalPages;
    private int pageIndex;
    private int pageSize;
    private List<ContentBean> content;

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public static class ContentBean implements Parcelable {
        /**
         * id : 1
         * departmentId : 5
         * departmentName : 炼油运行一部
         * sensorId : 3
         * sensorName : AIA003
         * address : 成品泵403南
         * deviceAreaId : 4
         * deviceAreaName : 2#常减压装置
         * childCategoryId : 8
         * childCategoryName : 氢气
         * parentCategoryId : 1
         * parentCategoryName : 易燃易爆气体
         * alarmLevelId : 3
         * alarmLevelName : 预警
         * alarmValue : 40
         * realTimeData :
         * alarmStartTime : 1539619200000
         * alarmEndTime : 1539662400000
         * alarmTotalNumber : 0
         * handleStatus : 1
         * handleStatusDescription : 未处置
         * handleTime :
         * handlePeopleId :
         * handlePeopleName :
         * handleDescription :
         * dataSyncStatus : 1
         * dataSyncStatusDescription : 异常
         * alarmSettingDescription : >=50%LLE/PPM
         * unit : %LLA/PPM
         * ruleType :
         * ruleValue :
         * alarmNumber : 1
         */

        private int id;
        private int departmentId;
        private String departmentName;
        private int sensorId;
        private String sensorName;
        private String address;
        private int deviceAreaId;
        private String deviceAreaName;
        private int childCategoryId;
        private String childCategoryName;
        private int parentCategoryId;
        private String parentCategoryName;
        private int alarmLevelId;
        private String alarmLevelName;
        private long alarmValue;
        private String realTimeData;
        private String alarmStartTime;
        private String alarmEndTime;
        private long alarmTotalNumber;
        private int handleStatus;
        private String handleStatusDescription;
        private String handleTime;
        private String handlePeopleId;
        private String handlePeopleName;
        private String handleDescription;
        private int dataSyncStatus;
        private String dataSyncStatusDescription;
        private String alarmSettingDescription;
        private String unit;
        private String ruleType;
        private String ruleValue;
        private long alarmNumber;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getDepartmentId() {
            return departmentId;
        }

        public void setDepartmentId(int departmentId) {
            this.departmentId = departmentId;
        }

        public String getDepartmentName() {
            return departmentName;
        }

        public void setDepartmentName(String departmentName) {
            this.departmentName = departmentName;
        }

        public int getSensorId() {
            return sensorId;
        }

        public void setSensorId(int sensorId) {
            this.sensorId = sensorId;
        }

        public String getSensorName() {
            return sensorName;
        }

        public void setSensorName(String sensorName) {
            this.sensorName = sensorName;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getDeviceAreaId() {
            return deviceAreaId;
        }

        public void setDeviceAreaId(int deviceAreaId) {
            this.deviceAreaId = deviceAreaId;
        }

        public String getDeviceAreaName() {
            return deviceAreaName;
        }

        public void setDeviceAreaName(String deviceAreaName) {
            this.deviceAreaName = deviceAreaName;
        }

        public int getChildCategoryId() {
            return childCategoryId;
        }

        public void setChildCategoryId(int childCategoryId) {
            this.childCategoryId = childCategoryId;
        }

        public String getChildCategoryName() {
            return childCategoryName;
        }

        public void setChildCategoryName(String childCategoryName) {
            this.childCategoryName = childCategoryName;
        }

        public int getParentCategoryId() {
            return parentCategoryId;
        }

        public void setParentCategoryId(int parentCategoryId) {
            this.parentCategoryId = parentCategoryId;
        }

        public String getParentCategoryName() {
            return parentCategoryName;
        }

        public void setParentCategoryName(String parentCategoryName) {
            this.parentCategoryName = parentCategoryName;
        }

        public int getAlarmLevelId() {
            return alarmLevelId;
        }

        public void setAlarmLevelId(int alarmLevelId) {
            this.alarmLevelId = alarmLevelId;
        }

        public String getAlarmLevelName() {
            return alarmLevelName;
        }

        public void setAlarmLevelName(String alarmLevelName) {
            this.alarmLevelName = alarmLevelName;
        }

        public long getAlarmValue() {
            return alarmValue;
        }

        public void setAlarmValue(long alarmValue) {
            this.alarmValue = alarmValue;
        }

        public String getRealTimeData() {
            return realTimeData;
        }

        public void setRealTimeData(String realTimeData) {
            this.realTimeData = realTimeData;
        }

        public String getAlarmStartTime() {
            return alarmStartTime;
        }

        public void setAlarmStartTime(String alarmStartTime) {
            this.alarmStartTime = alarmStartTime;
        }

        public String getAlarmEndTime() {
            return alarmEndTime;
        }

        public void setAlarmEndTime(String alarmEndTime) {
            this.alarmEndTime = alarmEndTime;
        }

        public long getAlarmTotalNumber() {
            return alarmTotalNumber;
        }

        public void setAlarmTotalNumber(long alarmTotalNumber) {
            this.alarmTotalNumber = alarmTotalNumber;
        }

        public int getHandleStatus() {
            return handleStatus;
        }

        public void setHandleStatus(int handleStatus) {
            this.handleStatus = handleStatus;
        }

        public String getHandleStatusDescription() {
            return handleStatusDescription;
        }

        public void setHandleStatusDescription(String handleStatusDescription) {
            this.handleStatusDescription = handleStatusDescription;
        }

        public String getHandleTime() {
            return handleTime;
        }

        public void setHandleTime(String handleTime) {
            this.handleTime = handleTime;
        }

        public String getHandlePeopleId() {
            return handlePeopleId;
        }

        public void setHandlePeopleId(String handlePeopleId) {
            this.handlePeopleId = handlePeopleId;
        }

        public String getHandlePeopleName() {
            return handlePeopleName;
        }

        public void setHandlePeopleName(String handlePeopleName) {
            this.handlePeopleName = handlePeopleName;
        }

        public String getHandleDescription() {
            return handleDescription;
        }

        public void setHandleDescription(String handleDescription) {
            this.handleDescription = handleDescription;
        }

        public int getDataSyncStatus() {
            return dataSyncStatus;
        }

        public void setDataSyncStatus(int dataSyncStatus) {
            this.dataSyncStatus = dataSyncStatus;
        }

        public String getDataSyncStatusDescription() {
            return dataSyncStatusDescription;
        }

        public void setDataSyncStatusDescription(String dataSyncStatusDescription) {
            this.dataSyncStatusDescription = dataSyncStatusDescription;
        }

        public String getAlarmSettingDescription() {
            return alarmSettingDescription;
        }

        public void setAlarmSettingDescription(String alarmSettingDescription) {
            this.alarmSettingDescription = alarmSettingDescription;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getRuleType() {
            return ruleType;
        }

        public void setRuleType(String ruleType) {
            this.ruleType = ruleType;
        }

        public String getRuleValue() {
            return ruleValue;
        }

        public void setRuleValue(String ruleValue) {
            this.ruleValue = ruleValue;
        }

        public long getAlarmNumber() {
            return alarmNumber;
        }

        public void setAlarmNumber(long alarmNumber) {
            this.alarmNumber = alarmNumber;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeInt(this.departmentId);
            dest.writeString(this.departmentName);
            dest.writeInt(this.sensorId);
            dest.writeString(this.sensorName);
            dest.writeString(this.address);
            dest.writeInt(this.deviceAreaId);
            dest.writeString(this.deviceAreaName);
            dest.writeInt(this.childCategoryId);
            dest.writeString(this.childCategoryName);
            dest.writeInt(this.parentCategoryId);
            dest.writeString(this.parentCategoryName);
            dest.writeInt(this.alarmLevelId);
            dest.writeString(this.alarmLevelName);
            dest.writeLong(this.alarmValue);
            dest.writeString(this.realTimeData);
            dest.writeString(this.alarmStartTime);
            dest.writeString(this.alarmEndTime);
            dest.writeLong(this.alarmTotalNumber);
            dest.writeInt(this.handleStatus);
            dest.writeString(this.handleStatusDescription);
            dest.writeString(this.handleTime);
            dest.writeString(this.handlePeopleId);
            dest.writeString(this.handlePeopleName);
            dest.writeString(this.handleDescription);
            dest.writeInt(this.dataSyncStatus);
            dest.writeString(this.dataSyncStatusDescription);
            dest.writeString(this.alarmSettingDescription);
            dest.writeString(this.unit);
            dest.writeString(this.ruleType);
            dest.writeString(this.ruleValue);
            dest.writeLong(this.alarmNumber);
        }

        public ContentBean() {
        }

        protected ContentBean(Parcel in) {
            this.id = in.readInt();
            this.departmentId = in.readInt();
            this.departmentName = in.readString();
            this.sensorId = in.readInt();
            this.sensorName = in.readString();
            this.address = in.readString();
            this.deviceAreaId = in.readInt();
            this.deviceAreaName = in.readString();
            this.childCategoryId = in.readInt();
            this.childCategoryName = in.readString();
            this.parentCategoryId = in.readInt();
            this.parentCategoryName = in.readString();
            this.alarmLevelId = in.readInt();
            this.alarmLevelName = in.readString();
            this.alarmValue = in.readLong();
            this.realTimeData = in.readString();
            this.alarmStartTime = in.readString();
            this.alarmEndTime = in.readString();
            this.alarmTotalNumber = in.readLong();
            this.handleStatus = in.readInt();
            this.handleStatusDescription = in.readString();
            this.handleTime = in.readString();
            this.handlePeopleId = in.readString();
            this.handlePeopleName = in.readString();
            this.handleDescription = in.readString();
            this.dataSyncStatus = in.readInt();
            this.dataSyncStatusDescription = in.readString();
            this.alarmSettingDescription = in.readString();
            this.unit = in.readString();
            this.ruleType = in.readString();
            this.ruleValue = in.readString();
            this.alarmNumber = in.readLong();
        }

        public static final Creator<ContentBean> CREATOR = new Creator<ContentBean>() {
            @Override
            public ContentBean createFromParcel(Parcel source) {
                return new ContentBean(source);
            }

            @Override
            public ContentBean[] newArray(int size) {
                return new ContentBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.totalRows);
        dest.writeInt(this.totalPages);
        dest.writeInt(this.pageIndex);
        dest.writeInt(this.pageSize);
        dest.writeTypedList(this.content);
    }

    public BjczPageInfo() {
    }

    protected BjczPageInfo(Parcel in) {
        this.totalRows = in.readInt();
        this.totalPages = in.readInt();
        this.pageIndex = in.readInt();
        this.pageSize = in.readInt();
        this.content = in.createTypedArrayList(ContentBean.CREATOR);
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
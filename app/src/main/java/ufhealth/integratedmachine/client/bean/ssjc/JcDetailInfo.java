package ufhealth.integratedmachine.client.bean.ssjc;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

public class JcDetailInfo implements Parcelable
{
    /**
     * id :
     * departmentId :
     * departmentName : 炼油运行一部
     * deviceAreaId :
     * deviceAreaName : 1#常减压装置
     * categoryParentId :
     * categoryParentName : 易燃易爆气体
     * categoryChildId :
     * categoryChildName :
     * name : AIA001
     * address : 成品泵401南
     * medium : 氢气
     * unit : %LLE/PPM
     * realtimeDbPositionId : YP1Z7GQ_AT_604_64
     * realtimeData :
     * dataSyncInterval :
     * dataSyncTime : 2018-10-17T06:29:05.000+0000
     * dataSyncStatus :
     * dataSyncStatusName :
     * alarmStatus :
     * alarmLevelId :
     * alarmLevelName :
     * alarmNumber :
     * alarmTotalNumber :
     * peopleId :
     * peopleName : 刘伟
     * peopleTelephone : 13541100997
     * peopleWorkTelephone : 18980926119
     * settings : [{"levelId":"","levelName":"预警","ruleType":"","ruleValue":"","ruleDescription":"≥50 %LLE/PPM"},{"levelId":"","levelName":"报警","ruleType":"","ruleValue":"","ruleDescription":">60 %LLE/PPM"}]
     * cameras : [{"id":"","name":"1#罐区东","accessUrl":"http://10.124.201.202/?cameraId=sdfi2f039j1f0jf42hr34h42h5"},{"id":26,"name":"1#罐区北","accessUrl":"http://10.124.201.202/?cameraId=sdfij2oi3jf92j19209fj023jfjf892"},{"id":27,"name":"1#罐区南","accessUrl":"http://10.124.201.202/?cameraId=sdfij2oi3jf92j19209fj023jfjf892"}]
     * monthAlramRecordStat : [{"alramLevelAndQuantity":[{"date":"","alarmLevelId":"","alarmLevelName":"报警","alarmLevelColorCode":"FF3300","alarmLevelPriority":"","quantity":""},{"date":null,"alarmLevelId":3,"alarmLevelName":"预警","alarmLevelColorCode":"FF9900","alarmLevelPriority":3,"quantity":0}],"yearAndMonth":"2018-05"},{"alramLevelAndQuantity":[{"date":"","alarmLevelId":"","alarmLevelName":"报警","alarmLevelColorCode":"FF3300","alarmLevelPriority":"","quantity":""},{"date":null,"alarmLevelId":3,"alarmLevelName":"预警","alarmLevelColorCode":"FF9900","alarmLevelPriority":3,"quantity":1}],"yearAndMonth":"2018-06"},{"alramLevelAndQuantity":[{"date":null,"alarmLevelId":4,"alarmLevelName":"报警","alarmLevelColorCode":"FF3300","alarmLevelPriority":2,"quantity":0},{"date":null,"alarmLevelId":3,"alarmLevelName":"预警","alarmLevelColorCode":"FF9900","alarmLevelPriority":3,"quantity":0}],"yearAndMonth":"2018-07"},{"alramLevelAndQuantity":[{"date":null,"alarmLevelId":4,"alarmLevelName":"报警","alarmLevelColorCode":"FF3300","alarmLevelPriority":2,"quantity":0},{"date":null,"alarmLevelId":3,"alarmLevelName":"预警","alarmLevelColorCode":"FF9900","alarmLevelPriority":3,"quantity":0}],"yearAndMonth":"2018-08"},{"alramLevelAndQuantity":[{"date":null,"alarmLevelId":4,"alarmLevelName":"报警","alarmLevelColorCode":"FF3300","alarmLevelPriority":2,"quantity":0},{"date":null,"alarmLevelId":3,"alarmLevelName":"预警","alarmLevelColorCode":"FF9900","alarmLevelPriority":3,"quantity":0}],"yearAndMonth":"2018-09"},{"alramLevelAndQuantity":[{"date":null,"alarmLevelId":4,"alarmLevelName":"报警","alarmLevelColorCode":"FF3300","alarmLevelPriority":2,"quantity":0},{"date":null,"alarmLevelId":3,"alarmLevelName":"预警","alarmLevelColorCode":"FF9900","alarmLevelPriority":3,"quantity":0}],"yearAndMonth":"2018-10"}]
     */

    private String id;
    private String departmentId;
    private String departmentName;
    private String deviceAreaId;
    private String deviceAreaName;
    private String categoryParentId;
    private String categoryParentName;
    private String categoryChildId;
    private String categoryChildName;
    private String name;
    private String address;
    private String medium;
    private String unit;
    private String realtimeDbPositionId;
    private String realtimeData;
    private String dataSyncInterval;
    private String dataSyncTime;
    private String dataSyncStatus;
    private String dataSyncStatusName;
    private String alarmStatus;
    private String alarmLevelId;
    private String alarmLevelName;
    private String alarmNumber;
    private String alarmTotalNumber;
    private String peopleId;
    private String peopleName;
    private String peopleTelephone;
    private String peopleWorkTelephone;
    private List<SettingsBean> settings;
    private List<CamerasBean> cameras;
    private List<MonthAlramRecordStatBean> monthAlramRecordStat;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDeviceAreaId() {
        return deviceAreaId;
    }

    public void setDeviceAreaId(String deviceAreaId) {
        this.deviceAreaId = deviceAreaId;
    }

    public String getDeviceAreaName() {
        return deviceAreaName;
    }

    public void setDeviceAreaName(String deviceAreaName) {
        this.deviceAreaName = deviceAreaName;
    }

    public String getCategoryParentId() {
        return categoryParentId;
    }

    public void setCategoryParentId(String categoryParentId) {
        this.categoryParentId = categoryParentId;
    }

    public String getCategoryParentName() {
        return categoryParentName;
    }

    public void setCategoryParentName(String categoryParentName) {
        this.categoryParentName = categoryParentName;
    }

    public String getCategoryChildId() {
        return categoryChildId;
    }

    public void setCategoryChildId(String categoryChildId) {
        this.categoryChildId = categoryChildId;
    }

    public String getCategoryChildName() {
        return categoryChildName;
    }

    public void setCategoryChildName(String categoryChildName) {
        this.categoryChildName = categoryChildName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getRealtimeDbPositionId() {
        return realtimeDbPositionId;
    }

    public void setRealtimeDbPositionId(String realtimeDbPositionId) {
        this.realtimeDbPositionId = realtimeDbPositionId;
    }

    public String getRealtimeData() {
        return realtimeData;
    }

    public void setRealtimeData(String realtimeData) {
        this.realtimeData = realtimeData;
    }

    public String getDataSyncInterval() {
        return dataSyncInterval;
    }

    public void setDataSyncInterval(String dataSyncInterval) {
        this.dataSyncInterval = dataSyncInterval;
    }

    public String getDataSyncTime() {
        return dataSyncTime;
    }

    public void setDataSyncTime(String dataSyncTime) {
        this.dataSyncTime = dataSyncTime;
    }

    public String getDataSyncStatus() {
        return dataSyncStatus;
    }

    public void setDataSyncStatus(String dataSyncStatus) {
        this.dataSyncStatus = dataSyncStatus;
    }

    public String getDataSyncStatusName() {
        return dataSyncStatusName;
    }

    public void setDataSyncStatusName(String dataSyncStatusName) {
        this.dataSyncStatusName = dataSyncStatusName;
    }

    public String getAlarmStatus() {
        return alarmStatus;
    }

    public void setAlarmStatus(String alarmStatus) {
        this.alarmStatus = alarmStatus;
    }

    public String getAlarmLevelId() {
        return alarmLevelId;
    }

    public void setAlarmLevelId(String alarmLevelId) {
        this.alarmLevelId = alarmLevelId;
    }

    public String getAlarmLevelName() {
        return alarmLevelName;
    }

    public void setAlarmLevelName(String alarmLevelName) {
        this.alarmLevelName = alarmLevelName;
    }

    public String getAlarmNumber() {
        return alarmNumber;
    }

    public void setAlarmNumber(String alarmNumber) {
        this.alarmNumber = alarmNumber;
    }

    public String getAlarmTotalNumber() {
        return alarmTotalNumber;
    }

    public void setAlarmTotalNumber(String alarmTotalNumber) {
        this.alarmTotalNumber = alarmTotalNumber;
    }

    public String getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(String peopleId) {
        this.peopleId = peopleId;
    }

    public String getPeopleName() {
        return peopleName;
    }

    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName;
    }

    public String getPeopleTelephone() {
        return peopleTelephone;
    }

    public void setPeopleTelephone(String peopleTelephone) {
        this.peopleTelephone = peopleTelephone;
    }

    public String getPeopleWorkTelephone() {
        return peopleWorkTelephone;
    }

    public void setPeopleWorkTelephone(String peopleWorkTelephone) {
        this.peopleWorkTelephone = peopleWorkTelephone;
    }

    public List<SettingsBean> getSettings() {
        return settings;
    }

    public void setSettings(List<SettingsBean> settings) {
        this.settings = settings;
    }

    public List<CamerasBean> getCameras() {
        return cameras;
    }

    public void setCameras(List<CamerasBean> cameras) {
        this.cameras = cameras;
    }

    public List<MonthAlramRecordStatBean> getMonthAlramRecordStat() {
        return monthAlramRecordStat;
    }

    public void setMonthAlramRecordStat(List<MonthAlramRecordStatBean> monthAlramRecordStat) {
        this.monthAlramRecordStat = monthAlramRecordStat;
    }

    public static class SettingsBean implements Parcelable{
        /**
         * levelId :
         * levelName : 预警
         * ruleType :
         * ruleValue :
         * ruleDescription : ≥50 %LLE/PPM
         */

        private String levelId;
        private String levelName;
        private String ruleType;
        private String ruleValue;
        private String ruleDescription;

        public String getLevelId() {
            return levelId;
        }

        public void setLevelId(String levelId) {
            this.levelId = levelId;
        }

        public String getLevelName() {
            return levelName;
        }

        public void setLevelName(String levelName) {
            this.levelName = levelName;
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

        public String getRuleDescription() {
            return ruleDescription;
        }

        public void setRuleDescription(String ruleDescription) {
            this.ruleDescription = ruleDescription;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.levelId);
            dest.writeString(this.levelName);
            dest.writeString(this.ruleType);
            dest.writeString(this.ruleValue);
            dest.writeString(this.ruleDescription);
        }

        public SettingsBean() {
        }

        protected SettingsBean(Parcel in) {
            this.levelId = in.readString();
            this.levelName = in.readString();
            this.ruleType = in.readString();
            this.ruleValue = in.readString();
            this.ruleDescription = in.readString();
        }

        public static final Creator<SettingsBean> CREATOR = new Creator<SettingsBean>() {
            @Override
            public SettingsBean createFromParcel(Parcel source) {
                return new SettingsBean(source);
            }

            @Override
            public SettingsBean[] newArray(int size) {
                return new SettingsBean[size];
            }
        };
    }

    public static class CamerasBean implements Parcelable{
        /**
         * id :
         * name : 1#罐区东
         * accessUrl : http://10.124.201.202/?cameraId=sdfi2f039j1f0jf42hr34h42h5
         */

        private String id;
        private String name;
        private String accessUrl;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAccessUrl() {
            return accessUrl;
        }

        public void setAccessUrl(String accessUrl) {
            this.accessUrl = accessUrl;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.name);
            dest.writeString(this.accessUrl);
        }

        public CamerasBean() {
        }

        protected CamerasBean(Parcel in) {
            this.id = in.readString();
            this.name = in.readString();
            this.accessUrl = in.readString();
        }

        public static final Creator<CamerasBean> CREATOR = new Creator<CamerasBean>() {
            @Override
            public CamerasBean createFromParcel(Parcel source) {
                return new CamerasBean(source);
            }

            @Override
            public CamerasBean[] newArray(int size) {
                return new CamerasBean[size];
            }
        };
    }

    public static class MonthAlramRecordStatBean implements Parcelable{
        /**
         * alramLevelAndQuantity : [{"date":"","alarmLevelId":"","alarmLevelName":"报警","alarmLevelColorCode":"FF3300","alarmLevelPriority":"","quantity":""},{"date":null,"alarmLevelId":3,"alarmLevelName":"预警","alarmLevelColorCode":"FF9900","alarmLevelPriority":3,"quantity":0}]
         * yearAndMonth : 2018-05
         */

        private String yearAndMonth;
        private List<AlramLevelAndQuantityBean> alramLevelAndQuantity;

        public String getYearAndMonth() {
            return yearAndMonth;
        }

        public void setYearAndMonth(String yearAndMonth) {
            this.yearAndMonth = yearAndMonth;
        }

        public List<AlramLevelAndQuantityBean> getAlramLevelAndQuantity() {
            return alramLevelAndQuantity;
        }

        public void setAlramLevelAndQuantity(List<AlramLevelAndQuantityBean> alramLevelAndQuantity) {
            this.alramLevelAndQuantity = alramLevelAndQuantity;
        }

        public static class AlramLevelAndQuantityBean implements Parcelable{
            /**
             * date :
             * alarmLevelId :
             * alarmLevelName : 报警
             * alarmLevelColorCode : FF3300
             * alarmLevelPriority :
             * quantity :
             */

            private String date;
            private String alarmLevelId;
            private String alarmLevelName;
            private String alarmLevelColorCode;
            private String alarmLevelPriority;
            private String quantity;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getAlarmLevelId() {
                return alarmLevelId;
            }

            public void setAlarmLevelId(String alarmLevelId) {
                this.alarmLevelId = alarmLevelId;
            }

            public String getAlarmLevelName() {
                return alarmLevelName;
            }

            public void setAlarmLevelName(String alarmLevelName) {
                this.alarmLevelName = alarmLevelName;
            }

            public String getAlarmLevelColorCode() {
                return alarmLevelColorCode;
            }

            public void setAlarmLevelColorCode(String alarmLevelColorCode) {
                this.alarmLevelColorCode = alarmLevelColorCode;
            }

            public String getAlarmLevelPriority() {
                return alarmLevelPriority;
            }

            public void setAlarmLevelPriority(String alarmLevelPriority) {
                this.alarmLevelPriority = alarmLevelPriority;
            }

            public String getQuantity() {
                return quantity;
            }

            public void setQuantity(String quantity) {
                this.quantity = quantity;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.date);
                dest.writeString(this.alarmLevelId);
                dest.writeString(this.alarmLevelName);
                dest.writeString(this.alarmLevelColorCode);
                dest.writeString(this.alarmLevelPriority);
                dest.writeString(this.quantity);
            }

            public AlramLevelAndQuantityBean() {
            }

            protected AlramLevelAndQuantityBean(Parcel in) {
                this.date = in.readString();
                this.alarmLevelId = in.readString();
                this.alarmLevelName = in.readString();
                this.alarmLevelColorCode = in.readString();
                this.alarmLevelPriority = in.readString();
                this.quantity = in.readString();
            }

            public static final Creator<AlramLevelAndQuantityBean> CREATOR = new Creator<AlramLevelAndQuantityBean>() {
                @Override
                public AlramLevelAndQuantityBean createFromParcel(Parcel source) {
                    return new AlramLevelAndQuantityBean(source);
                }

                @Override
                public AlramLevelAndQuantityBean[] newArray(int size) {
                    return new AlramLevelAndQuantityBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.yearAndMonth);
            dest.writeTypedList(this.alramLevelAndQuantity);
        }

        public MonthAlramRecordStatBean() {
        }

        protected MonthAlramRecordStatBean(Parcel in) {
            this.yearAndMonth = in.readString();
            this.alramLevelAndQuantity = in.createTypedArrayList(AlramLevelAndQuantityBean.CREATOR);
        }

        public static final Creator<MonthAlramRecordStatBean> CREATOR = new Creator<MonthAlramRecordStatBean>() {
            @Override
            public MonthAlramRecordStatBean createFromParcel(Parcel source) {
                return new MonthAlramRecordStatBean(source);
            }

            @Override
            public MonthAlramRecordStatBean[] newArray(int size) {
                return new MonthAlramRecordStatBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.departmentId);
        dest.writeString(this.departmentName);
        dest.writeString(this.deviceAreaId);
        dest.writeString(this.deviceAreaName);
        dest.writeString(this.categoryParentId);
        dest.writeString(this.categoryParentName);
        dest.writeString(this.categoryChildId);
        dest.writeString(this.categoryChildName);
        dest.writeString(this.name);
        dest.writeString(this.address);
        dest.writeString(this.medium);
        dest.writeString(this.unit);
        dest.writeString(this.realtimeDbPositionId);
        dest.writeString(this.realtimeData);
        dest.writeString(this.dataSyncInterval);
        dest.writeString(this.dataSyncTime);
        dest.writeString(this.dataSyncStatus);
        dest.writeString(this.dataSyncStatusName);
        dest.writeString(this.alarmStatus);
        dest.writeString(this.alarmLevelId);
        dest.writeString(this.alarmLevelName);
        dest.writeString(this.alarmNumber);
        dest.writeString(this.alarmTotalNumber);
        dest.writeString(this.peopleId);
        dest.writeString(this.peopleName);
        dest.writeString(this.peopleTelephone);
        dest.writeString(this.peopleWorkTelephone);
        dest.writeTypedList(this.settings);
        dest.writeTypedList(this.cameras);
        dest.writeTypedList(this.monthAlramRecordStat);
    }

    public JcDetailInfo() {
    }

    protected JcDetailInfo(Parcel in) {
        this.id = in.readString();
        this.departmentId = in.readString();
        this.departmentName = in.readString();
        this.deviceAreaId = in.readString();
        this.deviceAreaName = in.readString();
        this.categoryParentId = in.readString();
        this.categoryParentName = in.readString();
        this.categoryChildId = in.readString();
        this.categoryChildName = in.readString();
        this.name = in.readString();
        this.address = in.readString();
        this.medium = in.readString();
        this.unit = in.readString();
        this.realtimeDbPositionId = in.readString();
        this.realtimeData = in.readString();
        this.dataSyncInterval = in.readString();
        this.dataSyncTime = in.readString();
        this.dataSyncStatus = in.readString();
        this.dataSyncStatusName = in.readString();
        this.alarmStatus = in.readString();
        this.alarmLevelId = in.readString();
        this.alarmLevelName = in.readString();
        this.alarmNumber = in.readString();
        this.alarmTotalNumber = in.readString();
        this.peopleId = in.readString();
        this.peopleName = in.readString();
        this.peopleTelephone = in.readString();
        this.peopleWorkTelephone = in.readString();
        this.settings = in.createTypedArrayList(SettingsBean.CREATOR);
        this.cameras = in.createTypedArrayList(CamerasBean.CREATOR);
        this.monthAlramRecordStat = in.createTypedArrayList(MonthAlramRecordStatBean.CREATOR);
    }

    public static final Creator<JcDetailInfo> CREATOR = new Creator<JcDetailInfo>() {
        @Override
        public JcDetailInfo createFromParcel(Parcel source) {
            return new JcDetailInfo(source);
        }

        @Override
        public JcDetailInfo[] newArray(int size) {
            return new JcDetailInfo[size];
        }
    };
}
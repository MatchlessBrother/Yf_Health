package ufhealth.integratedmachine.client.bean.ssjc;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class JcDataInfo implements Parcelable
{
    /**
     * id :
     * name : 数据同步异常
     * colorCode : FF3300
     * sensors : [{"id":"","departmentId":"","departmentName":"炼油运行三部","deviceAreaId":"","deviceAreaName":"2#常减压装置","categoryParentId":"","categoryParentName":"有毒有害气体","categoryChildId":"","categoryChildName":"H2S","name":"BDB001000","address":"R101西南侧00","medium":"氢气000","unit":"%LLE/PPM","realtimeDbPositionId":"YP1Z7GQ_BDB_001000","realtimeData":"","dataSyncInterval":"","dataSyncTime":"2018-10-17T06:29:15.000+0000","dataSyncStatus":"","dataSyncStatusName":"异常","alarmStatus":"","alarmLevelId":"","alarmLevelName":"","alarmNumber":"","alarmTotalNumber":"","peopleId":"","peopleName":"章庆文","peopleTelephone":"","peopleWorkTelephone":"6748274","settings":[{"levelId":"","levelName":"预警","ruleType":"","ruleValue":"","ruleDescription":"≥30 %LLE/PPM"},{"levelId":4,"levelName":"报警","ruleType":2,"ruleValue":50,"ruleDescription":"≥50 %LLE/PPM"}],"cameras":"","monthAlramRecordStat":""}]
     */

    private String id;
    private String name;
    private String colorCode;
    private List<SensorsBean> sensors;

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

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public List<SensorsBean> getSensors() {
        return sensors;
    }

    public void setSensors(List<SensorsBean> sensors) {
        this.sensors = sensors;
    }

    public static class SensorsBean implements Parcelable{
        /**
         * id :
         * departmentId :
         * departmentName : 炼油运行三部
         * deviceAreaId :
         * deviceAreaName : 2#常减压装置
         * categoryParentId :
         * categoryParentName : 有毒有害气体
         * categoryChildId :
         * categoryChildName : H2S
         * name : BDB001000
         * address : R101西南侧00
         * medium : 氢气000
         * unit : %LLE/PPM
         * realtimeDbPositionId : YP1Z7GQ_BDB_001000
         * realtimeData :
         * dataSyncInterval :
         * dataSyncTime : 2018-10-17T06:29:15.000+0000
         * dataSyncStatus :
         * dataSyncStatusName : 异常
         * alarmStatus :
         * alarmLevelId :
         * alarmLevelName :
         * alarmNumber :
         * alarmTotalNumber :
         * peopleId :
         * peopleName : 章庆文
         * peopleTelephone :
         * peopleWorkTelephone : 6748274
         * settings : [{"levelId":"","levelName":"预警","ruleType":"","ruleValue":"","ruleDescription":"≥30 %LLE/PPM"},{"levelId":4,"levelName":"报警","ruleType":2,"ruleValue":50,"ruleDescription":"≥50 %LLE/PPM"}]
         * cameras :
         * monthAlramRecordStat :
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
        private String cameras;
        private String monthAlramRecordStat;
        private List<SettingsBean> settings;

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

        public String getCameras() {
            return cameras;
        }

        public void setCameras(String cameras) {
            this.cameras = cameras;
        }

        public String getMonthAlramRecordStat() {
            return monthAlramRecordStat;
        }

        public void setMonthAlramRecordStat(String monthAlramRecordStat) {
            this.monthAlramRecordStat = monthAlramRecordStat;
        }

        public List<SettingsBean> getSettings() {
            return settings;
        }

        public void setSettings(List<SettingsBean> settings) {
            this.settings = settings;
        }

        public static class SettingsBean implements Parcelable{
            /**
             * levelId :
             * levelName : 预警
             * ruleType :
             * ruleValue :
             * ruleDescription : ≥30 %LLE/PPM
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
            dest.writeString(this.cameras);
            dest.writeString(this.monthAlramRecordStat);
            dest.writeTypedList(this.settings);
        }

        public SensorsBean() {
        }

        protected SensorsBean(Parcel in) {
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
            this.cameras = in.readString();
            this.monthAlramRecordStat = in.readString();
            this.settings = in.createTypedArrayList(SettingsBean.CREATOR);
        }

        public static final Creator<SensorsBean> CREATOR = new Creator<SensorsBean>() {
            @Override
            public SensorsBean createFromParcel(Parcel source) {
                return new SensorsBean(source);
            }

            @Override
            public SensorsBean[] newArray(int size) {
                return new SensorsBean[size];
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
        dest.writeString(this.name);
        dest.writeString(this.colorCode);
        dest.writeTypedList(this.sensors);
    }

    public JcDataInfo() {
    }

    protected JcDataInfo(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.colorCode = in.readString();
        this.sensors = in.createTypedArrayList(SensorsBean.CREATOR);
    }

    public static final Creator<JcDataInfo> CREATOR = new Creator<JcDataInfo>() {
        @Override
        public JcDataInfo createFromParcel(Parcel source) {
            return new JcDataInfo(source);
        }

        @Override
        public JcDataInfo[] newArray(int size) {
            return new JcDataInfo[size];
        }
    };
}
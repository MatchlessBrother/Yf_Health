package ufhealth.integratedmachine.client.bean.bjcz;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

public class BjczDetailInfo implements Parcelable
{
    /**
     * id :
     * departmentId :
     * departmentName : 炼油运行一部
     * sensorId :
     * sensorName : AIA001
     * address : 成品泵401南
     * medium : 氢气
     * unit : %LLE/PPM
     * cameraNumber :
     * deviceAreaId :
     * deviceAreaName : 1#常减压装置
     * childCategoryId :
     * childCategoryName :
     * parentCategoryId :
     * parentCategoryName : 易燃易爆气体
     * alarmLevelId :
     * alarmLevelName : 预警
     * alarmValue :
     * realtimeData :
     * realtimeDbPositionId : YP1Z7GQ_AT_604_64
     * alarmStartTime :
     * alarmEndTime :
     * alarmTotalNumber :
     * handleStatus :
     * handleStatusDescription : 已处置
     * handleTime :
     * handlePeopleId :
     * handlePeopleName : 章庆文
     * handlePeopleTelephone :
     * handlePeopleWorkTelephone : 6748274
     * handleDescription : 嗯
     * dataSyncStatus :
     * dataSyncStatusDescription : 异常
     * alarmSettingDescription : ≥50 %LLE/PPM
     * alarmNumber :
     * settings : [{"levelId":"","levelName":"预警","ruleType":"","ruleValue":"","ruleDescription":"≥50 %LLE/PPM"},{"levelId":4,"levelName":"报警","ruleType":1,"ruleValue":60,"ruleDescription":">60 %LLE/PPM"}]
     * cameras : [{"id":"","name":"1#罐区东","accessUrl":"http://10.124.201.202/?cameraId=sdfi2f039j1f0jf42hr34h42h5"},{"id":26,"name":"1#罐区北","accessUrl":"http://10.124.201.202/?cameraId=sdfij2oi3jf92j19209fj023jfjf892"},{"id":27,"name":"1#罐区南","accessUrl":"http://10.124.201.202/?cameraId=sdfij2oi3jf92j19209fj023jfjf892"}]
     * monthAlramRecordStat : [{"alramLevelAndQuantity":[{"date":"","alarmLevelId":"","alarmLevelName":"报警","alarmLevelColorCode":"FF3300","alarmLevelPriority":"","quantity":""},{"date":null,"alarmLevelId":3,"alarmLevelName":"预警","alarmLevelColorCode":"FF9900","alarmLevelPriority":3,"quantity":0}],"yearAndMonth":"2018-05"},{"alramLevelAndQuantity":[{"date":null,"alarmLevelId":4,"alarmLevelName":"报警","alarmLevelColorCode":"FF3300","alarmLevelPriority":2,"quantity":0},{"date":null,"alarmLevelId":3,"alarmLevelName":"预警","alarmLevelColorCode":"FF9900","alarmLevelPriority":3,"quantity":1}],"yearAndMonth":"2018-06"},{"alramLevelAndQuantity":[{"date":null,"alarmLevelId":4,"alarmLevelName":"报警","alarmLevelColorCode":"FF3300","alarmLevelPriority":2,"quantity":0},{"date":null,"alarmLevelId":3,"alarmLevelName":"预警","alarmLevelColorCode":"FF9900","alarmLevelPriority":3,"quantity":0}],"yearAndMonth":"2018-07"},{"alramLevelAndQuantity":[{"date":null,"alarmLevelId":4,"alarmLevelName":"报警","alarmLevelColorCode":"FF3300","alarmLevelPriority":2,"quantity":0},{"date":null,"alarmLevelId":3,"alarmLevelName":"预警","alarmLevelColorCode":"FF9900","alarmLevelPriority":3,"quantity":0}],"yearAndMonth":"2018-08"},{"alramLevelAndQuantity":[{"date":null,"alarmLevelId":4,"alarmLevelName":"报警","alarmLevelColorCode":"FF3300","alarmLevelPriority":2,"quantity":0},{"date":null,"alarmLevelId":3,"alarmLevelName":"预警","alarmLevelColorCode":"FF9900","alarmLevelPriority":3,"quantity":0}],"yearAndMonth":"2018-09"},{"alramLevelAndQuantity":[{"date":null,"alarmLevelId":4,"alarmLevelName":"报警","alarmLevelColorCode":"FF3300","alarmLevelPriority":2,"quantity":0},{"date":null,"alarmLevelId":3,"alarmLevelName":"预警","alarmLevelColorCode":"FF9900","alarmLevelPriority":3,"quantity":0}],"yearAndMonth":"2018-10"}]
     * handleImages : [{"imageUrl":"/1540571846729-890/20181027003705.jpg"},{"imageUrl":"/1540571846729-890/20181027003705-1540571826016-35.jpg"}]
     * peopleId :
     * peopleName : 刘伟
     * peopleTelephone : 13541100997
     * peopleWorkTelephone : 81133191
     */

    private String id;
    private String departmentId;
    private String departmentName;
    private String sensorId;
    private String sensorName;
    private String address;
    private String medium;
    private String unit;
    private String cameraNumber;
    private String deviceAreaId;
    private String deviceAreaName;
    private String childCategoryId;
    private String childCategoryName;
    private String parentCategoryId;
    private String parentCategoryName;
    private String alarmLevelId;
    private String alarmLevelName;
    private String alarmValue;
    private String realtimeData;
    private String realtimeDbPositionId;
    private String alarmStartTime;
    private String alarmEndTime;
    private String alarmTotalNumber;
    private String handleStatus;
    private String handleStatusDescription;
    private String handleTime;
    private String handlePeopleId;
    private String handlePeopleName;
    private String handlePeopleTelephone;
    private String handlePeopleWorkTelephone;
    private String handleDescription;
    private String dataSyncStatus;
    private String dataSyncStatusDescription;
    private String alarmSettingDescription;
    private String alarmNumber;
    private String peopleId;
    private String peopleName;
    private String peopleTelephone;
    private String peopleWorkTelephone;
    private List<SettingsBean> settings;
    private List<CamerasBean> cameras;
    private List<MonthAlramRecordStatBean> monthAlramRecordStat;
    private List<HandleImagesBean> handleImages;

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

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
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

    public String getCameraNumber() {
        return cameraNumber;
    }

    public void setCameraNumber(String cameraNumber) {
        this.cameraNumber = cameraNumber;
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

    public String getChildCategoryId() {
        return childCategoryId;
    }

    public void setChildCategoryId(String childCategoryId) {
        this.childCategoryId = childCategoryId;
    }

    public String getChildCategoryName() {
        return childCategoryName;
    }

    public void setChildCategoryName(String childCategoryName) {
        this.childCategoryName = childCategoryName;
    }

    public String getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(String parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public String getParentCategoryName() {
        return parentCategoryName;
    }

    public void setParentCategoryName(String parentCategoryName) {
        this.parentCategoryName = parentCategoryName;
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

    public String getAlarmValue() {
        return alarmValue;
    }

    public void setAlarmValue(String alarmValue) {
        this.alarmValue = alarmValue;
    }

    public String getRealtimeData() {
        return realtimeData;
    }

    public void setRealtimeData(String realtimeData) {
        this.realtimeData = realtimeData;
    }

    public String getRealtimeDbPositionId() {
        return realtimeDbPositionId;
    }

    public void setRealtimeDbPositionId(String realtimeDbPositionId) {
        this.realtimeDbPositionId = realtimeDbPositionId;
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

    public String getAlarmTotalNumber() {
        return alarmTotalNumber;
    }

    public void setAlarmTotalNumber(String alarmTotalNumber) {
        this.alarmTotalNumber = alarmTotalNumber;
    }

    public String getHandleStatus() {
        return handleStatus;
    }

    public void setHandleStatus(String handleStatus) {
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

    public String getHandlePeopleTelephone() {
        return handlePeopleTelephone;
    }

    public void setHandlePeopleTelephone(String handlePeopleTelephone) {
        this.handlePeopleTelephone = handlePeopleTelephone;
    }

    public String getHandlePeopleWorkTelephone() {
        return handlePeopleWorkTelephone;
    }

    public void setHandlePeopleWorkTelephone(String handlePeopleWorkTelephone) {
        this.handlePeopleWorkTelephone = handlePeopleWorkTelephone;
    }

    public String getHandleDescription() {
        return handleDescription;
    }

    public void setHandleDescription(String handleDescription) {
        this.handleDescription = handleDescription;
    }

    public String getDataSyncStatus() {
        return dataSyncStatus;
    }

    public void setDataSyncStatus(String dataSyncStatus) {
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

    public String getAlarmNumber() {
        return alarmNumber;
    }

    public void setAlarmNumber(String alarmNumber) {
        this.alarmNumber = alarmNumber;
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

    public List<HandleImagesBean> getHandleImages() {
        return handleImages;
    }

    public void setHandleImages(List<HandleImagesBean> handleImages) {
        this.handleImages = handleImages;
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

    public static class HandleImagesBean implements Parcelable{
        /**
         * imageUrl : /1540571846729-890/20181027003705.jpg
         */

        private String imageUrl;

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.imageUrl);
        }

        public HandleImagesBean() {
        }

        protected HandleImagesBean(Parcel in) {
            this.imageUrl = in.readString();
        }

        public static final Creator<HandleImagesBean> CREATOR = new Creator<HandleImagesBean>() {
            @Override
            public HandleImagesBean createFromParcel(Parcel source) {
                return new HandleImagesBean(source);
            }

            @Override
            public HandleImagesBean[] newArray(int size) {
                return new HandleImagesBean[size];
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
        dest.writeString(this.sensorId);
        dest.writeString(this.sensorName);
        dest.writeString(this.address);
        dest.writeString(this.medium);
        dest.writeString(this.unit);
        dest.writeString(this.cameraNumber);
        dest.writeString(this.deviceAreaId);
        dest.writeString(this.deviceAreaName);
        dest.writeString(this.childCategoryId);
        dest.writeString(this.childCategoryName);
        dest.writeString(this.parentCategoryId);
        dest.writeString(this.parentCategoryName);
        dest.writeString(this.alarmLevelId);
        dest.writeString(this.alarmLevelName);
        dest.writeString(this.alarmValue);
        dest.writeString(this.realtimeData);
        dest.writeString(this.realtimeDbPositionId);
        dest.writeString(this.alarmStartTime);
        dest.writeString(this.alarmEndTime);
        dest.writeString(this.alarmTotalNumber);
        dest.writeString(this.handleStatus);
        dest.writeString(this.handleStatusDescription);
        dest.writeString(this.handleTime);
        dest.writeString(this.handlePeopleId);
        dest.writeString(this.handlePeopleName);
        dest.writeString(this.handlePeopleTelephone);
        dest.writeString(this.handlePeopleWorkTelephone);
        dest.writeString(this.handleDescription);
        dest.writeString(this.dataSyncStatus);
        dest.writeString(this.dataSyncStatusDescription);
        dest.writeString(this.alarmSettingDescription);
        dest.writeString(this.alarmNumber);
        dest.writeString(this.peopleId);
        dest.writeString(this.peopleName);
        dest.writeString(this.peopleTelephone);
        dest.writeString(this.peopleWorkTelephone);
        dest.writeTypedList(this.settings);
        dest.writeTypedList(this.cameras);
        dest.writeTypedList(this.monthAlramRecordStat);
        dest.writeTypedList(this.handleImages);
    }

    public BjczDetailInfo() {
    }

    protected BjczDetailInfo(Parcel in) {
        this.id = in.readString();
        this.departmentId = in.readString();
        this.departmentName = in.readString();
        this.sensorId = in.readString();
        this.sensorName = in.readString();
        this.address = in.readString();
        this.medium = in.readString();
        this.unit = in.readString();
        this.cameraNumber = in.readString();
        this.deviceAreaId = in.readString();
        this.deviceAreaName = in.readString();
        this.childCategoryId = in.readString();
        this.childCategoryName = in.readString();
        this.parentCategoryId = in.readString();
        this.parentCategoryName = in.readString();
        this.alarmLevelId = in.readString();
        this.alarmLevelName = in.readString();
        this.alarmValue = in.readString();
        this.realtimeData = in.readString();
        this.realtimeDbPositionId = in.readString();
        this.alarmStartTime = in.readString();
        this.alarmEndTime = in.readString();
        this.alarmTotalNumber = in.readString();
        this.handleStatus = in.readString();
        this.handleStatusDescription = in.readString();
        this.handleTime = in.readString();
        this.handlePeopleId = in.readString();
        this.handlePeopleName = in.readString();
        this.handlePeopleTelephone = in.readString();
        this.handlePeopleWorkTelephone = in.readString();
        this.handleDescription = in.readString();
        this.dataSyncStatus = in.readString();
        this.dataSyncStatusDescription = in.readString();
        this.alarmSettingDescription = in.readString();
        this.alarmNumber = in.readString();
        this.peopleId = in.readString();
        this.peopleName = in.readString();
        this.peopleTelephone = in.readString();
        this.peopleWorkTelephone = in.readString();
        this.settings = in.createTypedArrayList(SettingsBean.CREATOR);
        this.cameras = in.createTypedArrayList(CamerasBean.CREATOR);
        this.monthAlramRecordStat = in.createTypedArrayList(MonthAlramRecordStatBean.CREATOR);
        this.handleImages = in.createTypedArrayList(HandleImagesBean.CREATOR);
    }

    public static final Creator<BjczDetailInfo> CREATOR = new Creator<BjczDetailInfo>() {
        @Override
        public BjczDetailInfo createFromParcel(Parcel source) {
            return new BjczDetailInfo(source);
        }

        @Override
        public BjczDetailInfo[] newArray(int size) {
            return new BjczDetailInfo[size];
        }
    };
}
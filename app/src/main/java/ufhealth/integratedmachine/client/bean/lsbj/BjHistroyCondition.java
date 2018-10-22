package ufhealth.integratedmachine.client.bean.lsbj;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

public class BjHistroyCondition implements Parcelable
{
    private List<CategoryBean> category;
    private List<AlarmLevelBean> alarmLevel;
    private List<DepartmentBean> department;

    public List<AlarmLevelBean> getAlarmLevel() {
        return alarmLevel;
    }

    public void setAlarmLevel(List<AlarmLevelBean> alarmLevel) {
        this.alarmLevel = alarmLevel;
    }

    public List<CategoryBean> getCategory() {
        return category;
    }

    public void setCategory(List<CategoryBean> category) {
        this.category = category;
    }

    public List<DepartmentBean> getDepartment() {
        return department;
    }

    public void setDepartment(List<DepartmentBean> department) {
        this.department = department;
    }

    public static class AlarmLevelBean implements Parcelable{
        /**
         * id : 1
         * name : 全部
         * priority : 1
         * isGenerateAlarmRecord :
         * isGenerateAlarmRecordDescription :
         * colorCode :
         * createTime :
         * isDelete :
         */

        private String id;
        private String name;
        private String priority;
        private String isGenerateAlarmRecord;
        private String isGenerateAlarmRecordDescription;
        private String colorCode;
        private String createTime;
        private String isDelete;

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

        public String getPriority() {
            return priority;
        }

        public void setPriority(String priority) {
            this.priority = priority;
        }

        public String getIsGenerateAlarmRecord() {
            return isGenerateAlarmRecord;
        }

        public void setIsGenerateAlarmRecord(String isGenerateAlarmRecord) {
            this.isGenerateAlarmRecord = isGenerateAlarmRecord;
        }

        public String getIsGenerateAlarmRecordDescription() {
            return isGenerateAlarmRecordDescription;
        }

        public void setIsGenerateAlarmRecordDescription(String isGenerateAlarmRecordDescription) {
            this.isGenerateAlarmRecordDescription = isGenerateAlarmRecordDescription;
        }

        public String getColorCode() {
            return colorCode;
        }

        public void setColorCode(String colorCode) {
            this.colorCode = colorCode;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(String isDelete) {
            this.isDelete = isDelete;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.name);
            dest.writeString(this.priority);
            dest.writeString(this.isGenerateAlarmRecord);
            dest.writeString(this.isGenerateAlarmRecordDescription);
            dest.writeString(this.colorCode);
            dest.writeString(this.createTime);
            dest.writeString(this.isDelete);
        }

        public AlarmLevelBean() {
        }

        protected AlarmLevelBean(Parcel in) {
            this.id = in.readString();
            this.name = in.readString();
            this.priority = in.readString();
            this.isGenerateAlarmRecord = in.readString();
            this.isGenerateAlarmRecordDescription = in.readString();
            this.colorCode = in.readString();
            this.createTime = in.readString();
            this.isDelete = in.readString();
        }

        public static final Creator<AlarmLevelBean> CREATOR = new Creator<AlarmLevelBean>() {
            @Override
            public AlarmLevelBean createFromParcel(Parcel source) {
                return new AlarmLevelBean(source);
            }

            @Override
            public AlarmLevelBean[] newArray(int size) {
                return new AlarmLevelBean[size];
            }
        };
    }

    public static class CategoryBean implements Parcelable{
        /**
         * id : 1
         * name : 全部
         * parentSensorCategoryId :
         * parentSensorCategoryName :
         * createTime :
         * isDelete :
         */

        private String id;
        private String name;
        private String parentSensorCategoryId;
        private String parentSensorCategoryName;
        private String createTime;
        private String isDelete;

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

        public String getParentSensorCategoryId() {
            return parentSensorCategoryId;
        }

        public void setParentSensorCategoryId(String parentSensorCategoryId) {
            this.parentSensorCategoryId = parentSensorCategoryId;
        }

        public String getParentSensorCategoryName() {
            return parentSensorCategoryName;
        }

        public void setParentSensorCategoryName(String parentSensorCategoryName) {
            this.parentSensorCategoryName = parentSensorCategoryName;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(String isDelete) {
            this.isDelete = isDelete;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.name);
            dest.writeString(this.parentSensorCategoryId);
            dest.writeString(this.parentSensorCategoryName);
            dest.writeString(this.createTime);
            dest.writeString(this.isDelete);
        }

        public CategoryBean() {
        }

        protected CategoryBean(Parcel in) {
            this.id = in.readString();
            this.name = in.readString();
            this.parentSensorCategoryId = in.readString();
            this.parentSensorCategoryName = in.readString();
            this.createTime = in.readString();
            this.isDelete = in.readString();
        }

        public static final Creator<CategoryBean> CREATOR = new Creator<CategoryBean>() {
            @Override
            public CategoryBean createFromParcel(Parcel source) {
                return new CategoryBean(source);
            }

            @Override
            public CategoryBean[] newArray(int size) {
                return new CategoryBean[size];
            }
        };
    }

    public static class DepartmentBean implements Parcelable{
        /**
         * departmentId :
         * departmentName : 全部
         * deviceAreaList : [{"id":"","departmentId":"","departmentName":"炼油运行一部","name":"1#常减压装置","peopleId":"","peopleName":"","peopleTelephone":"","createTime":"2018-10-09T16:50:21.000+0000"}]
         */

        private String departmentId;
        private String departmentName;
        private List<DeviceAreaListBean> deviceAreaList;

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

        public List<DeviceAreaListBean> getDeviceAreaList() {
            return deviceAreaList;
        }

        public void setDeviceAreaList(List<DeviceAreaListBean> deviceAreaList) {
            this.deviceAreaList = deviceAreaList;
        }

        public static class DeviceAreaListBean implements Parcelable{
            /**
             * id :
             * departmentId :
             * departmentName : 炼油运行一部
             * name : 1#常减压装置
             * peopleId :
             * peopleName :
             * peopleTelephone :
             * createTime : 2018-10-09T16:50:21.000+0000
             */

            private String id;
            private String departmentId;
            private String departmentName;
            private String name;
            private String peopleId;
            private String peopleName;
            private String peopleTelephone;
            private String createTime;

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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
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

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
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
                dest.writeString(this.name);
                dest.writeString(this.peopleId);
                dest.writeString(this.peopleName);
                dest.writeString(this.peopleTelephone);
                dest.writeString(this.createTime);
            }

            public DeviceAreaListBean() {
            }

            protected DeviceAreaListBean(Parcel in) {
                this.id = in.readString();
                this.departmentId = in.readString();
                this.departmentName = in.readString();
                this.name = in.readString();
                this.peopleId = in.readString();
                this.peopleName = in.readString();
                this.peopleTelephone = in.readString();
                this.createTime = in.readString();
            }

            public static final Creator<DeviceAreaListBean> CREATOR = new Creator<DeviceAreaListBean>() {
                @Override
                public DeviceAreaListBean createFromParcel(Parcel source) {
                    return new DeviceAreaListBean(source);
                }

                @Override
                public DeviceAreaListBean[] newArray(int size) {
                    return new DeviceAreaListBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.departmentId);
            dest.writeString(this.departmentName);
            dest.writeTypedList(this.deviceAreaList);
        }

        public DepartmentBean() {
        }

        protected DepartmentBean(Parcel in) {
            this.departmentId = in.readString();
            this.departmentName = in.readString();
            this.deviceAreaList = in.createTypedArrayList(DeviceAreaListBean.CREATOR);
        }

        public static final Creator<DepartmentBean> CREATOR = new Creator<DepartmentBean>() {
            @Override
            public DepartmentBean createFromParcel(Parcel source) {
                return new DepartmentBean(source);
            }

            @Override
            public DepartmentBean[] newArray(int size) {
                return new DepartmentBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.alarmLevel);
        dest.writeTypedList(this.category);
        dest.writeTypedList(this.department);
    }

    public BjHistroyCondition() {
    }

    protected BjHistroyCondition(Parcel in) {
        this.alarmLevel = in.createTypedArrayList(AlarmLevelBean.CREATOR);
        this.category = in.createTypedArrayList(CategoryBean.CREATOR);
        this.department = in.createTypedArrayList(DepartmentBean.CREATOR);
    }

    public static final Creator<BjHistroyCondition> CREATOR = new Creator<BjHistroyCondition>() {
        @Override
        public BjHistroyCondition createFromParcel(Parcel source) {
            return new BjHistroyCondition(source);
        }

        @Override
        public BjHistroyCondition[] newArray(int size) {
            return new BjHistroyCondition[size];
        }
    };
}
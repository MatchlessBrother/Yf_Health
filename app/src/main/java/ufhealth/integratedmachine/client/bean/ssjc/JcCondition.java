package ufhealth.integratedmachine.client.bean.ssjc;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

public class JcCondition implements Parcelable
{
    private List<CategoryVosBean> categoryVos;
    private List<DepartmentDeviceVosBean> departmentDeviceVos;
    private List<AlarmLevelVosBean> alarmLevelVos;

    public List<CategoryVosBean> getCategoryVos() {
        return categoryVos;
    }

    public void setCategoryVos(List<CategoryVosBean> categoryVos) {
        this.categoryVos = categoryVos;
    }

    public List<DepartmentDeviceVosBean> getDepartmentDeviceVos() {
        return departmentDeviceVos;
    }

    public void setDepartmentDeviceVos(List<DepartmentDeviceVosBean> departmentDeviceVos) {
        this.departmentDeviceVos = departmentDeviceVos;
    }

    public List<AlarmLevelVosBean> getAlarmLevelVos() {
        return alarmLevelVos;
    }

    public void setAlarmLevelVos(List<AlarmLevelVosBean> alarmLevelVos) {
        this.alarmLevelVos = alarmLevelVos;
    }

    public static class CategoryVosBean implements Parcelable{
        /**
         * id :
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

        public CategoryVosBean() {
        }

        protected CategoryVosBean(Parcel in) {
            this.id = in.readString();
            this.name = in.readString();
            this.parentSensorCategoryId = in.readString();
            this.parentSensorCategoryName = in.readString();
            this.createTime = in.readString();
            this.isDelete = in.readString();
        }

        public static final Creator<CategoryVosBean> CREATOR = new Creator<CategoryVosBean>() {
            @Override
            public CategoryVosBean createFromParcel(Parcel source) {
                return new CategoryVosBean(source);
            }

            @Override
            public CategoryVosBean[] newArray(int size) {
                return new CategoryVosBean[size];
            }
        };
    }

    public static class DepartmentDeviceVosBean implements Parcelable{
        /**
         * departmentId :
         * departmentName : 全部
         * deviceAreaList : [{"id":"","departmentId":"","departmentName":"炼油运行三部","name":"1#常减压装置","peopleId":"","peopleName":"","peopleTelephone":"","createTime":"2018-10-11T18:33:29.000+0000"}]
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
             * departmentName : 炼油运行三部
             * name : 1#常减压装置
             * peopleId :
             * peopleName :
             * peopleTelephone :
             * createTime : 2018-10-11T18:33:29.000+0000
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

        public DepartmentDeviceVosBean() {
        }

        protected DepartmentDeviceVosBean(Parcel in) {
            this.departmentId = in.readString();
            this.departmentName = in.readString();
            this.deviceAreaList = in.createTypedArrayList(DeviceAreaListBean.CREATOR);
        }

        public static final Creator<DepartmentDeviceVosBean> CREATOR = new Creator<DepartmentDeviceVosBean>() {
            @Override
            public DepartmentDeviceVosBean createFromParcel(Parcel source) {
                return new DepartmentDeviceVosBean(source);
            }

            @Override
            public DepartmentDeviceVosBean[] newArray(int size) {
                return new DepartmentDeviceVosBean[size];
            }
        };
    }

    public static class AlarmLevelVosBean implements Parcelable{
        /**
         * id :
         * name : 全部
         * priority :
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

        public AlarmLevelVosBean() {
        }

        protected AlarmLevelVosBean(Parcel in) {
            this.id = in.readString();
            this.name = in.readString();
            this.priority = in.readString();
            this.isGenerateAlarmRecord = in.readString();
            this.isGenerateAlarmRecordDescription = in.readString();
            this.colorCode = in.readString();
            this.createTime = in.readString();
            this.isDelete = in.readString();
        }

        public static final Creator<AlarmLevelVosBean> CREATOR = new Creator<AlarmLevelVosBean>() {
            @Override
            public AlarmLevelVosBean createFromParcel(Parcel source) {
                return new AlarmLevelVosBean(source);
            }

            @Override
            public AlarmLevelVosBean[] newArray(int size) {
                return new AlarmLevelVosBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.categoryVos);
        dest.writeTypedList(this.departmentDeviceVos);
        dest.writeTypedList(this.alarmLevelVos);
    }

    public JcCondition() {
    }

    protected JcCondition(Parcel in) {
        this.categoryVos = in.createTypedArrayList(CategoryVosBean.CREATOR);
        this.departmentDeviceVos = in.createTypedArrayList(DepartmentDeviceVosBean.CREATOR);
        this.alarmLevelVos = in.createTypedArrayList(AlarmLevelVosBean.CREATOR);
    }

    public static final Creator<JcCondition> CREATOR = new Creator<JcCondition>() {
        @Override
        public JcCondition createFromParcel(Parcel source) {
            return new JcCondition(source);
        }

        @Override
        public JcCondition[] newArray(int size) {
            return new JcCondition[size];
        }
    };
}
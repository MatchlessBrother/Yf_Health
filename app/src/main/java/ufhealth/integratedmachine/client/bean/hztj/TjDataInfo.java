package ufhealth.integratedmachine.client.bean.hztj;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

public class TjDataInfo implements Parcelable
{
    /**
     * dataSyncExceptionQuantity :
     * handleStatusStat : [{"handleStatus":"","quantity":""},{"handleStatus":2,"quantity":2}]
     * monthAlramRecordStat : [{"alramLevelAndQuantity":[{"date":"","alarmLevelId":"","alarmLevelName":"报警","alarmLevelColorCode":"FF3300","alarmLevelPriority":"","quantity":""},{"date":"","alarmLevelId":"","alarmLevelName":"预警","alarmLevelColorCode":"FF9900","alarmLevelPriority":"","quantity":""}],"yearAndMonth":"2018-05"},{"alramLevelAndQuantity":[{"date":"","alarmLevelId":"","alarmLevelName":"报警","alarmLevelColorCode":"FF3300","alarmLevelPriority":"","quantity":""},{"date":null,"alarmLevelId":3,"alarmLevelName":"预警","alarmLevelColorCode":"FF9900","alarmLevelPriority":3,"quantity":1}],"yearAndMonth":"2018-06"},{"alramLevelAndQuantity":[{"date":null,"alarmLevelId":4,"alarmLevelName":"报警","alarmLevelColorCode":"FF3300","alarmLevelPriority":2,"quantity":1},{"date":null,"alarmLevelId":3,"alarmLevelName":"预警","alarmLevelColorCode":"FF9900","alarmLevelPriority":3,"quantity":0}],"yearAndMonth":"2018-07"},{"alramLevelAndQuantity":[{"date":null,"alarmLevelId":4,"alarmLevelName":"报警","alarmLevelColorCode":"FF3300","alarmLevelPriority":2,"quantity":0},{"date":null,"alarmLevelId":3,"alarmLevelName":"预警","alarmLevelColorCode":"FF9900","alarmLevelPriority":3,"quantity":0}],"yearAndMonth":"2018-08"},{"alramLevelAndQuantity":[{"date":null,"alarmLevelId":4,"alarmLevelName":"报警","alarmLevelColorCode":"FF3300","alarmLevelPriority":2,"quantity":0},{"date":null,"alarmLevelId":3,"alarmLevelName":"预警","alarmLevelColorCode":"FF9900","alarmLevelPriority":3,"quantity":0}],"yearAndMonth":"2018-09"},{"alramLevelAndQuantity":[{"date":null,"alarmLevelId":4,"alarmLevelName":"报警","alarmLevelColorCode":"FF3300","alarmLevelPriority":2,"quantity":0},{"date":null,"alarmLevelId":3,"alarmLevelName":"预警","alarmLevelColorCode":"FF9900","alarmLevelPriority":3,"quantity":0}],"yearAndMonth":"2018-10"}]
     * alarmQuantityVos : [{"alarmLevelId":"","alarmLevelName":"报警","alarmLevelColorCode":"FF3300","alarmLevelPriority":"","sensorQuantity":""},{"alarmLevelId":3,"alarmLevelName":"预警","alarmLevelColorCode":"FF9900","alarmLevelPriority":3,"sensorQuantity":1}]
     */
    private String dataSyncExceptionQuantity;
    private List<HandleStatusStatBean> handleStatusStat;
    private List<MonthAlramRecordStatBean> monthAlramRecordStat;
    private List<AlarmQuantityVosBean> alarmQuantityVos;

    public String getDataSyncExceptionQuantity() {
        return dataSyncExceptionQuantity;
    }

    public void setDataSyncExceptionQuantity(String dataSyncExceptionQuantity) {
        this.dataSyncExceptionQuantity = dataSyncExceptionQuantity;
    }

    public List<HandleStatusStatBean> getHandleStatusStat() {
        return handleStatusStat;
    }

    public void setHandleStatusStat(List<HandleStatusStatBean> handleStatusStat) {
        this.handleStatusStat = handleStatusStat;
    }

    public List<MonthAlramRecordStatBean> getMonthAlramRecordStat() {
        return monthAlramRecordStat;
    }

    public void setMonthAlramRecordStat(List<MonthAlramRecordStatBean> monthAlramRecordStat) {
        this.monthAlramRecordStat = monthAlramRecordStat;
    }

    public List<AlarmQuantityVosBean> getAlarmQuantityVos() {
        return alarmQuantityVos;
    }

    public void setAlarmQuantityVos(List<AlarmQuantityVosBean> alarmQuantityVos) {
        this.alarmQuantityVos = alarmQuantityVos;
    }

    public static class HandleStatusStatBean implements Parcelable
    {
        /**
         * handleStatus :
         * quantity :
         */

        private String handleStatus;
        private String quantity;

        public String getHandleStatus() {
            return handleStatus;
        }

        public void setHandleStatus(String handleStatus) {
            this.handleStatus = handleStatus;
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
            dest.writeString(this.handleStatus);
            dest.writeString(this.quantity);
        }

        public HandleStatusStatBean() {
        }

        protected HandleStatusStatBean(Parcel in) {
            this.handleStatus = in.readString();
            this.quantity = in.readString();
        }

        public static final Creator<HandleStatusStatBean> CREATOR = new Creator<HandleStatusStatBean>() {
            @Override
            public HandleStatusStatBean createFromParcel(Parcel source) {
                return new HandleStatusStatBean(source);
            }

            @Override
            public HandleStatusStatBean[] newArray(int size) {
                return new HandleStatusStatBean[size];
            }
        };
    }

    public static class MonthAlramRecordStatBean implements Parcelable
    {
        /**
         * alramLevelAndQuantity : [{"date":"","alarmLevelId":"","alarmLevelName":"报警","alarmLevelColorCode":"FF3300","alarmLevelPriority":"","quantity":""},{"date":"","alarmLevelId":"","alarmLevelName":"预警","alarmLevelColorCode":"FF9900","alarmLevelPriority":"","quantity":""}]
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

        public static class AlramLevelAndQuantityBean implements Parcelable
        {
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

    public static class AlarmQuantityVosBean implements Parcelable
    {
        /**
         * alarmLevelId :
         * alarmLevelName : 报警
         * alarmLevelColorCode : FF3300
         * alarmLevelPriority :
         * sensorQuantity :
         */

        private String alarmLevelId;
        private String alarmLevelName;
        private String alarmLevelColorCode;
        private String alarmLevelPriority;
        private String sensorQuantity;

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

        public String getSensorQuantity() {
            return sensorQuantity;
        }

        public void setSensorQuantity(String sensorQuantity) {
            this.sensorQuantity = sensorQuantity;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.alarmLevelId);
            dest.writeString(this.alarmLevelName);
            dest.writeString(this.alarmLevelColorCode);
            dest.writeString(this.alarmLevelPriority);
            dest.writeString(this.sensorQuantity);
        }

        public AlarmQuantityVosBean() {
        }

        protected AlarmQuantityVosBean(Parcel in) {
            this.alarmLevelId = in.readString();
            this.alarmLevelName = in.readString();
            this.alarmLevelColorCode = in.readString();
            this.alarmLevelPriority = in.readString();
            this.sensorQuantity = in.readString();
        }

        public static final Creator<AlarmQuantityVosBean> CREATOR = new Creator<AlarmQuantityVosBean>() {
            @Override
            public AlarmQuantityVosBean createFromParcel(Parcel source) {
                return new AlarmQuantityVosBean(source);
            }

            @Override
            public AlarmQuantityVosBean[] newArray(int size) {
                return new AlarmQuantityVosBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.dataSyncExceptionQuantity);
        dest.writeTypedList(this.handleStatusStat);
        dest.writeTypedList(this.monthAlramRecordStat);
        dest.writeTypedList(this.alarmQuantityVos);
    }

    public TjDataInfo() {
    }

    protected TjDataInfo(Parcel in) {
        this.dataSyncExceptionQuantity = in.readString();
        this.handleStatusStat = in.createTypedArrayList(HandleStatusStatBean.CREATOR);
        this.monthAlramRecordStat = in.createTypedArrayList(MonthAlramRecordStatBean.CREATOR);
        this.alarmQuantityVos = in.createTypedArrayList(AlarmQuantityVosBean.CREATOR);
    }

    public static final Creator<TjDataInfo> CREATOR = new Creator<TjDataInfo>() {
        @Override
        public TjDataInfo createFromParcel(Parcel source) {
            return new TjDataInfo(source);
        }

        @Override
        public TjDataInfo[] newArray(int size) {
            return new TjDataInfo[size];
        }
    };
}
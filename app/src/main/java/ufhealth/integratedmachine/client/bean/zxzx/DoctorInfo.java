package ufhealth.integratedmachine.client.bean.zxzx;

import java.util.List;
import android.os.Parcel;
import java.util.ArrayList;
import java.util.LinkedList;
import android.os.Parcelable;

import ufhealth.integratedmachine.client.util.DecimalFormatTools;

public class DoctorInfo implements Parcelable
{
    private boolean last;
    private long totalPages;
    private long totalElements;
    private long sort;
    private boolean first;
    private long numberOfElements;
    private long size;
    private long number;
    private List<ContentBean> content = new LinkedList<>();

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public long getSort() {
        return sort;
    }

    public void setSort(long sort) {
        this.sort = sort;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public long getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(long numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public static class ContentBean implements Parcelable
    {
        private String doctor_name;
        private String hospital_name;
        private String job_name;
        private String be_good_at;
        private String original;
        private long doctor_id;
        private double s_cost;
        private double y_cost;
        private double m_cost;
        private double k_cost;
        private double b_cost;
        private double t_cost;
        private String department_name;
        private String avatar;
        private String is_free;
        private boolean isSelected;
        private List<String> labels;
        private int is_service;

        public String getDoctor_name() {
            return doctor_name;
        }

        public void setDoctor_name(String doctor_name) {
            this.doctor_name = doctor_name;
        }

        public String getHospital_name() {
            return hospital_name;
        }

        public void setHospital_name(String hospital_name) {
            this.hospital_name = hospital_name;
        }

        public String getJob_name() {
            return job_name;
        }

        public void setJob_name(String job_name) {
            this.job_name = job_name;
        }

        public String getBe_good_at() {
            return be_good_at;
        }

        public void setBe_good_at(String be_good_at) {
            this.be_good_at = be_good_at;
        }

        public String getOriginal() {
            return original;
        }

        public void setOriginal(String original) {
            this.original = original;
        }

        public long getDoctor_id() {
            return doctor_id;
        }

        public void setDoctor_id(long doctor_id) {
            this.doctor_id = doctor_id;
        }

        public double getS_cost()
        {
            return DecimalFormatTools.keepTwoDecimalDigits(s_cost);
        }

        public void setS_cost(double s_cost) {
            this.s_cost = s_cost;
        }

        public double getY_cost() {
            return DecimalFormatTools.keepTwoDecimalDigits(y_cost);
        }

        public void setY_cost(double y_cost) {
            this.y_cost = y_cost;
        }

        public double getM_cost() {
            return DecimalFormatTools.keepTwoDecimalDigits(m_cost);
        }

        public void setM_cost(double m_cost) {
            this.m_cost = m_cost;
        }

        public double getK_cost()
        {
            return DecimalFormatTools.keepTwoDecimalDigits(k_cost);
        }

        public void setK_cost(double k_cost) {
            this.k_cost = k_cost;
        }

        public double getB_cost() {
            return DecimalFormatTools.keepTwoDecimalDigits(b_cost);
        }

        public void setB_cost(double b_cost) {
            this.b_cost = b_cost;
        }

        public double getT_cost() {
            return DecimalFormatTools.keepTwoDecimalDigits(t_cost);
        }

        public void setT_cost(double t_cost) {
            this.t_cost = t_cost;
        }

        public String getDepartment_name() {
            return department_name;
        }

        public void setDepartment_name(String department_name) {
            this.department_name = department_name;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getIs_free() {
            return is_free;
        }

        public void setIs_free(String is_free) {
            this.is_free = is_free;
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        public List<String> getLabels() {
            return labels;
        }

        public void setLabels(List<String> labels) {
            this.labels = labels;
        }

        public int getIs_service() {
            return this.is_service;
        }

        public void setIs_service(int is_service) {
            this.is_service = is_service;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.doctor_name);
            dest.writeString(this.hospital_name);
            dest.writeString(this.job_name);
            dest.writeString(this.be_good_at);
            dest.writeString(this.original);
            dest.writeLong(this.doctor_id);
            dest.writeDouble(this.s_cost);
            dest.writeDouble(this.y_cost);
            dest.writeDouble(this.m_cost);
            dest.writeDouble(this.k_cost);
            dest.writeDouble(this.b_cost);
            dest.writeDouble(this.t_cost);
            dest.writeString(this.department_name);
            dest.writeString(this.avatar);
            dest.writeString(this.is_free);
            dest.writeByte(this.isSelected ? (byte) 1 : (byte) 0);
            dest.writeStringList(this.labels);
            dest.writeInt(this.is_service);
        }

        public ContentBean() {
        }

        protected ContentBean(Parcel in) {
            this.doctor_name = in.readString();
            this.hospital_name = in.readString();
            this.job_name = in.readString();
            this.be_good_at = in.readString();
            this.original = in.readString();
            this.doctor_id = in.readLong();
            this.s_cost = in.readDouble();
            this.y_cost = in.readDouble();
            this.m_cost = in.readDouble();
            this.k_cost = in.readDouble();
            this.b_cost = in.readDouble();
            this.t_cost = in.readDouble();
            this.department_name = in.readString();
            this.avatar = in.readString();
            this.is_free = in.readString();
            this.isSelected = in.readByte() != 0;
            this.labels = in.createStringArrayList();
            this.is_service = in.readInt();
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
        dest.writeByte(this.last ? (byte) 1 : (byte) 0);
        dest.writeLong(this.totalPages);
        dest.writeLong(this.totalElements);
        dest.writeLong(this.sort);
        dest.writeByte(this.first ? (byte) 1 : (byte) 0);
        dest.writeLong(this.numberOfElements);
        dest.writeLong(this.size);
        dest.writeLong(this.number);
        dest.writeTypedList(this.content);
    }

    public DoctorInfo() {
    }

    protected DoctorInfo(Parcel in) {
        this.last = in.readByte() != 0;
        this.totalPages = in.readLong();
        this.totalElements = in.readLong();
        this.sort = in.readLong();
        this.first = in.readByte() != 0;
        this.numberOfElements = in.readLong();
        this.size = in.readLong();
        this.number = in.readLong();
        this.content = in.createTypedArrayList(ContentBean.CREATOR);
    }

    public static final Creator<DoctorInfo> CREATOR = new Creator<DoctorInfo>() {
        @Override
        public DoctorInfo createFromParcel(Parcel source) {
            return new DoctorInfo(source);
        }

        @Override
        public DoctorInfo[] newArray(int size) {
            return new DoctorInfo[size];
        }
    };
}
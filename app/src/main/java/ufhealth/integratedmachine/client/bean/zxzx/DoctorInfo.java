package ufhealth.integratedmachine.client.bean.zxzx;

import java.util.List;
import android.os.Parcel;
import java.util.ArrayList;
import java.util.LinkedList;
import android.os.Parcelable;

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

    public static class ContentBean
    {
        private String doctor_name;
        private String hospital_name;
        private String job_name;
        private String be_good_at;
        private String original;
        private long doctor_id;
        private long s_cost;
        private long t_cost;
        private String department_name;
        private String avatar;
        private long y_cost;
        private String is_free;

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

        public long getS_cost() {
            return s_cost;
        }

        public void setS_cost(long s_cost) {
            this.s_cost = s_cost;
        }

        public long getT_cost() {
            return t_cost;
        }

        public void setT_cost(long t_cost) {
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

        public long getY_cost() {
            return y_cost;
        }

        public void setY_cost(long y_cost) {
            this.y_cost = y_cost;
        }

        public String getIs_free() {
            return is_free;
        }

        public void setIs_free(String is_free) {
            this.is_free = is_free;
        }
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
        dest.writeList(this.content);
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
        this.content = new ArrayList<ContentBean>();
        in.readList(this.content, ContentBean.class.getClassLoader());
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
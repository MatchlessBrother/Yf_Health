package ufhealth.integratedmachine.client.bean.zxzx;

import java.util.List;
import android.os.Parcel;
import java.util.ArrayList;
import java.util.LinkedList;
import android.os.Parcelable;

public class DoctorInfoOfCondition implements Parcelable
{
    private List<DepartmentBean> department = new LinkedList<>();
    private List<OriginalBean> original = new LinkedList<>();
    private List<HospitalBean> hospital = new LinkedList<>();
    private List<SortBean> sort = new LinkedList<>();

    public List<DepartmentBean> getDepartment() {
        return department;
    }

    public void setDepartment(List<DepartmentBean> department) {
        this.department = department;
    }

    public List<OriginalBean> getOriginal() {
        return original;
    }

    public void setOriginal(List<OriginalBean> original) {
        this.original = original;
    }

    public List<HospitalBean> getHospital() {
        return hospital;
    }

    public void setHospital(List<HospitalBean> hospital) {
        this.hospital = hospital;
    }

    public List<SortBean> getSort() {
        return sort;
    }

    public void setSort(List<SortBean> sort) {
        this.sort = sort;
    }

    public static class DepartmentBean {
        /**
         * id : 1
         * name : 热门1
         */

        private String id;
        private String name;

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
    }

    public static class OriginalBean {
        /**
         * code : 1
         * name : 优服医生团队
         */

        private String code;
        private String name;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class HospitalBean {
        /**
         * id : 1
         * name : 四川省人民医院
         */

        private String id;
        private String name;

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
    }

    public static class SortBean {
        /**
         * id : 1
         * name : 四川省人民医院
         */

        private String id;
        private String name;

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
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.department);
        dest.writeList(this.original);
        dest.writeList(this.hospital);
        dest.writeList(this.sort);
    }

    public DoctorInfoOfCondition() {
    }

    protected DoctorInfoOfCondition(Parcel in) {
        this.department = new ArrayList<DepartmentBean>();
        in.readList(this.department, DepartmentBean.class.getClassLoader());
        this.original = new ArrayList<OriginalBean>();
        in.readList(this.original, OriginalBean.class.getClassLoader());
        this.hospital = new ArrayList<HospitalBean>();
        in.readList(this.hospital, HospitalBean.class.getClassLoader());
        this.sort = new ArrayList<SortBean>();
        in.readList(this.sort, SortBean.class.getClassLoader());
    }

    public static final Creator<DoctorInfoOfCondition> CREATOR = new Creator<DoctorInfoOfCondition>() {
        @Override
        public DoctorInfoOfCondition createFromParcel(Parcel source) {
            return new DoctorInfoOfCondition(source);
        }

        @Override
        public DoctorInfoOfCondition[] newArray(int size) {
            return new DoctorInfoOfCondition[size];
        }
    };
}
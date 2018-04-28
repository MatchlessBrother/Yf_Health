package ufhealth.integratedmachine.client.bean.zxzx;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class DoctorAllInfo implements Parcelable
{
    /**
     * baseinfo : {"comment_count":"s21","doctor_name":"于姝娅","hospital_name":"四川省人民医院","avatar":"123123","y_cost":20,"consult_count":"123123","job_name":"住院医师","be_good_at":"以中国传统的中药、针灸、推拿等治疗方法为手段，研究自胎儿至青少年这一时期小儿的生长发育、生理病理、喂养保健，以及各类疾病预防和治疗的一门医学科学","original":"优服医生团队","comment_count_per":"123","s_cost":50,"department_name":"热门1","t_cost":0,"is_free":"今日义诊"}
     * page : {"content":[{"id":2,"consult_service":"qwsqw","comment":"萨达阿斯顿撒","coment_socore":[{"score":40,"name":"专业度"}],"comment_time":"2018年04月19日 15:01:40","name":"测试"},{"id":1,"consult_service":null,"comment":"萨达","coment_socore":[],"comment_time":"2018年04月17日 15:01:28","name":"测试"},{"id":3,"consult_service":null,"comment":null,"coment_socore":[],"comment_time":null,"name":"测试"},{"id":6,"consult_service":null,"comment":null,"coment_socore":[],"comment_time":null,"name":"测试"},{"id":8,"consult_service":null,"comment":null,"coment_socore":[],"comment_time":null,"name":"测试"},{"id":9,"consult_service":null,"comment":null,"coment_socore":[],"comment_time":null,"name":"测试"},{"id":10,"consult_service":null,"comment":null,"coment_socore":[],"comment_time":null,"name":"测试"},{"id":11,"consult_service":null,"comment":null,"coment_socore":[],"comment_time":null,"name":"测试"}],"totalPages":1,"totalElements":8,"last":true,"numberOfElements":8,"sort":"662","first":true,"size":10000,"number":0}
     */

    private BaseinfoBean baseinfo;
    private PageBean page;

    public BaseinfoBean getBaseinfo() {
        return baseinfo;
    }

    public void setBaseinfo(BaseinfoBean baseinfo) {
        this.baseinfo = baseinfo;
    }

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }

    public static class BaseinfoBean implements Parcelable{
        /**
         * comment_count : s21
         * doctor_name : 于姝娅
         * hospital_name : 四川省人民医院
         * avatar : 123123
         * y_cost : 20
         * consult_count : 123123
         * job_name : 住院医师
         * be_good_at : 以中国传统的中药、针灸、推拿等治疗方法为手段，研究自胎儿至青少年这一时期小儿的生长发育、生理病理、喂养保健，以及各类疾病预防和治疗的一门医学科学
         * original : 优服医生团队
         * comment_count_per : 123
         * s_cost : 50
         * department_name : 热门1
         * t_cost : 0
         * is_free : 今日义诊
         */

        private String comment_count;
        private String doctor_name;
        private String hospital_name;
        private String avatar;
        private long y_cost;
        private String consult_count;
        private String job_name;
        private String be_good_at;
        private String original;
        private String comment_count_per;
        private long s_cost;
        private String department_name;
        private long t_cost;
        private String is_free;
        private String twzxIsFree;
        private String spzxIsFree;
        private String ypzxIsFree;

        public String getComment_count() {
            return comment_count;
        }

        public void setComment_count(String comment_count) {
            this.comment_count = comment_count;
        }

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

        public String getConsult_count() {
            return consult_count;
        }

        public void setConsult_count(String consult_count) {
            this.consult_count = consult_count;
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

        public String getComment_count_per() {
            return comment_count_per;
        }

        public void setComment_count_per(String comment_count_per) {
            this.comment_count_per = comment_count_per;
        }

        public long getS_cost() {
            return s_cost;
        }

        public void setS_cost(long s_cost) {
            this.s_cost = s_cost;
        }

        public String getDepartment_name() {
            return department_name;
        }

        public void setDepartment_name(String department_name) {
            this.department_name = department_name;
        }

        public long getT_cost() {
            return t_cost;
        }

        public void setT_cost(long t_cost) {
            this.t_cost = t_cost;
        }

        public String getIs_free() {
            return is_free;
        }

        public void setIs_free(String is_free) {
            this.is_free = is_free;
        }

        public String getTwzxIsFree() {
            return this.twzxIsFree;
        }

        public void setTwzxIsFree(String twzxIsFree) {
            this.twzxIsFree = twzxIsFree;
        }

        public String getSpzxIsFree() {
            return this.spzxIsFree;
        }

        public void setSpzxIsFree(String spzxIsFree) {
            this.spzxIsFree = spzxIsFree;
        }

        public String getYpzxIsFree() {
            return this.ypzxIsFree;
        }

        public void setYpzxIsFree(String ypzxIsFree) {
            this.ypzxIsFree = ypzxIsFree;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.comment_count);
            dest.writeString(this.doctor_name);
            dest.writeString(this.hospital_name);
            dest.writeString(this.avatar);
            dest.writeLong(this.y_cost);
            dest.writeString(this.consult_count);
            dest.writeString(this.job_name);
            dest.writeString(this.be_good_at);
            dest.writeString(this.original);
            dest.writeString(this.comment_count_per);
            dest.writeLong(this.s_cost);
            dest.writeString(this.department_name);
            dest.writeLong(this.t_cost);
            dest.writeString(this.is_free);
            dest.writeString(this.twzxIsFree);
            dest.writeString(this.spzxIsFree);
            dest.writeString(this.ypzxIsFree);
        }

        public BaseinfoBean() {
        }

        protected BaseinfoBean(Parcel in) {
            this.comment_count = in.readString();
            this.doctor_name = in.readString();
            this.hospital_name = in.readString();
            this.avatar = in.readString();
            this.y_cost = in.readLong();
            this.consult_count = in.readString();
            this.job_name = in.readString();
            this.be_good_at = in.readString();
            this.original = in.readString();
            this.comment_count_per = in.readString();
            this.s_cost = in.readLong();
            this.department_name = in.readString();
            this.t_cost = in.readLong();
            this.is_free = in.readString();
            this.twzxIsFree = in.readString();
            this.spzxIsFree = in.readString();
            this.ypzxIsFree = in.readString();
        }

        public static final Creator<BaseinfoBean> CREATOR = new Creator<BaseinfoBean>() {
            @Override
            public BaseinfoBean createFromParcel(Parcel source) {
                return new BaseinfoBean(source);
            }

            @Override
            public BaseinfoBean[] newArray(int size) {
                return new BaseinfoBean[size];
            }
        };
    }

    public static class PageBean implements Parcelable
    {
        /**
         * content : [{"id":2,"consult_service":"qwsqw","comment":"萨达阿斯顿撒","coment_socore":[{"score":40,"name":"专业度"}],"comment_time":"2018年04月19日 15:01:40","name":"测试"},{"id":1,"consult_service":null,"comment":"萨达","coment_socore":[],"comment_time":"2018年04月17日 15:01:28","name":"测试"},{"id":3,"consult_service":null,"comment":null,"coment_socore":[],"comment_time":null,"name":"测试"},{"id":6,"consult_service":null,"comment":null,"coment_socore":[],"comment_time":null,"name":"测试"},{"id":8,"consult_service":null,"comment":null,"coment_socore":[],"comment_time":null,"name":"测试"},{"id":9,"consult_service":null,"comment":null,"coment_socore":[],"comment_time":null,"name":"测试"},{"id":10,"consult_service":null,"comment":null,"coment_socore":[],"comment_time":null,"name":"测试"},{"id":11,"consult_service":null,"comment":null,"coment_socore":[],"comment_time":null,"name":"测试"}]
         * totalPages : 1
         * totalElements : 8
         * last : true
         * numberOfElements : 8
         * sort : 662
         * first : true
         * size : 10000
         * number : 0
         */

        private long totalPages;
        private long totalElements;
        private boolean last;
        private long numberOfElements;
        private String sort;
        private boolean first;
        private long size;
        private long number;
        private List<ContentBean> content;

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

        public boolean isLast() {
            return last;
        }

        public void setLast(boolean last) {
            this.last = last;
        }

        public long getNumberOfElements() {
            return numberOfElements;
        }

        public void setNumberOfElements(long numberOfElements) {
            this.numberOfElements = numberOfElements;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public boolean isFirst() {
            return first;
        }

        public void setFirst(boolean first) {
            this.first = first;
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



        public static class ContentBean implements Parcelable{
            /**
             * id : 2
             * consult_service : qwsqw
             * comment : 萨达阿斯顿撒
             * coment_socore : [{"score":40,"name":"专业度"}]
             * comment_time : 2018年04月19日 15:01:40
             * name : 测试
             */

            private int id;
            private String consult_service;
            private String comment;
            private String comment_time;
            private String name;
            private List<ComentSocoreBean> coment_socore;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getConsult_service() {
                return consult_service;
            }

            public void setConsult_service(String consult_service) {
                this.consult_service = consult_service;
            }

            public String getComment() {
                return comment;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }

            public String getComment_time() {
                return comment_time;
            }

            public void setComment_time(String comment_time) {
                this.comment_time = comment_time;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<ComentSocoreBean> getComent_socore() {
                return coment_socore;
            }

            public void setComent_socore(List<ComentSocoreBean> coment_socore) {
                this.coment_socore = coment_socore;
            }

            public static class ComentSocoreBean implements Parcelable
            {
                /**
                 * score : 40
                 * name : 专业度
                 */

                private int score;
                private String name;

                public int getScore() {
                    return score;
                }

                public void setScore(int score) {
                    this.score = score;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeInt(this.score);
                    dest.writeString(this.name);
                }

                public ComentSocoreBean() {
                }

                protected ComentSocoreBean(Parcel in) {
                    this.score = in.readInt();
                    this.name = in.readString();
                }

                public static final Creator<ComentSocoreBean> CREATOR = new Creator<ComentSocoreBean>() {
                    @Override
                    public ComentSocoreBean createFromParcel(Parcel source) {
                        return new ComentSocoreBean(source);
                    }

                    @Override
                    public ComentSocoreBean[] newArray(int size) {
                        return new ComentSocoreBean[size];
                    }
                };
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.id);
                dest.writeString(this.consult_service);
                dest.writeString(this.comment);
                dest.writeString(this.comment_time);
                dest.writeString(this.name);
                dest.writeList(this.coment_socore);
            }

            public ContentBean() {
            }

            protected ContentBean(Parcel in) {
                this.id = in.readInt();
                this.consult_service = in.readString();
                this.comment = in.readString();
                this.comment_time = in.readString();
                this.name = in.readString();
                this.coment_socore = new ArrayList<ComentSocoreBean>();
                in.readList(this.coment_socore, ComentSocoreBean.class.getClassLoader());
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

        public PageBean() {
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeLong(this.totalPages);
            dest.writeLong(this.totalElements);
            dest.writeByte(this.last ? (byte) 1 : (byte) 0);
            dest.writeLong(this.numberOfElements);
            dest.writeString(this.sort);
            dest.writeByte(this.first ? (byte) 1 : (byte) 0);
            dest.writeLong(this.size);
            dest.writeLong(this.number);
            dest.writeList(this.content);
        }

        protected PageBean(Parcel in) {
            this.totalPages = in.readLong();
            this.totalElements = in.readLong();
            this.last = in.readByte() != 0;
            this.numberOfElements = in.readLong();
            this.sort = in.readString();
            this.first = in.readByte() != 0;
            this.size = in.readLong();
            this.number = in.readLong();
            this.content = new ArrayList<ContentBean>();
            in.readList(this.content, ContentBean.class.getClassLoader());
        }

        public static final Creator<PageBean> CREATOR = new Creator<PageBean>() {
            @Override
            public PageBean createFromParcel(Parcel source) {
                return new PageBean(source);
            }

            @Override
            public PageBean[] newArray(int size) {
                return new PageBean[size];
            }
        };
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.baseinfo, flags);
        dest.writeParcelable(this.page, flags);
    }

    public DoctorAllInfo() {
    }

    protected DoctorAllInfo(Parcel in) {
        this.baseinfo = in.readParcelable(BaseinfoBean.class.getClassLoader());
        this.page = in.readParcelable(PageBean.class.getClassLoader());
    }

    public static final Creator<DoctorAllInfo> CREATOR = new Creator<DoctorAllInfo>() {
        @Override
        public DoctorAllInfo createFromParcel(Parcel source) {
            return new DoctorAllInfo(source);
        }

        @Override
        public DoctorAllInfo[] newArray(int size) {
            return new DoctorAllInfo[size];
        }
    };
}
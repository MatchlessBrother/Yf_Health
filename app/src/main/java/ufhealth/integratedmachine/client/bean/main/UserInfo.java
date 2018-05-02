package ufhealth.integratedmachine.client.bean.main;

import android.os.Parcel;
import android.os.Parcelable;

public class UserInfo implements Parcelable
{
    private TokenBean token;
    private UserInfo.UserInfoBean userInfo;

    public TokenBean getToken() {
        return token;
    }

    public void setToken(TokenBean token) {
        this.token = token;
    }

    public UserInfo.UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo.UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public static class TokenBean implements Parcelable {
        private String token;
        private String expireTimestamp;
        private String memberId;

        public String getToken() {
            return this.token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getExpireTimestamp() {
            return this.expireTimestamp;
        }

        public void setExpireTimestamp(String expireTimestamp) {
            this.expireTimestamp = expireTimestamp;
        }

        public String getMemberId() {
            return this.memberId;
        }

        public void setMemberId(String memberId) {
            this.memberId = memberId;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.token);
            dest.writeString(this.expireTimestamp);
            dest.writeString(this.memberId);
        }

        public TokenBean() {
        }

        protected TokenBean(Parcel in) {
            this.token = in.readString();
            this.expireTimestamp = in.readString();
            this.memberId = in.readString();
        }

        public static final Creator<TokenBean> CREATOR = new Creator<TokenBean>() {
            @Override
            public TokenBean createFromParcel(Parcel source) {
                return new TokenBean(source);
            }

            @Override
            public TokenBean[] newArray(int size) {
                return new TokenBean[size];
            }
        };
    }

    public static class UserInfoBean implements Parcelable {
        private int id;
        private String birthday;
        private String createTime;
        private int gender;
        private String avatar;
        private String idcardBackImg;
        private String idcardFrontImg;
        private String mobilePhone;
        private String name;
        private int original;
        private String papersNumber;
        private int papersType;

        public int getId() {
            return this.id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getBirthday() {
            return this.birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getCreateTime() {
            return this.createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getGender() {
            return this.gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public String getAvatar() {
            return this.avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getIdcardBackImg() {
            return this.idcardBackImg;
        }

        public void setIdcardBackImg(String idcardBackImg) {
            this.idcardBackImg = idcardBackImg;
        }

        public String getIdcardFrontImg() {
            return this.idcardFrontImg;
        }

        public void setIdcardFrontImg(String idcardFrontImg) {
            this.idcardFrontImg = idcardFrontImg;
        }

        public String getMobilePhone() {
            return this.mobilePhone;
        }

        public void setMobilePhone(String mobilePhone) {
            this.mobilePhone = mobilePhone;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getOriginal() {
            return this.original;
        }

        public void setOriginal(int original) {
            this.original = original;
        }

        public String getPapersNumber() {
            return this.papersNumber;
        }

        public void setPapersNumber(String papersNumber) {
            this.papersNumber = papersNumber;
        }

        public int getPapersType() {
            return this.papersType;
        }

        public void setPapersType(int papersType) {
            this.papersType = papersType;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeString(this.birthday);
            dest.writeString(this.createTime);
            dest.writeInt(this.gender);
            dest.writeString(this.avatar);
            dest.writeString(this.idcardBackImg);
            dest.writeString(this.idcardFrontImg);
            dest.writeString(this.mobilePhone);
            dest.writeString(this.name);
            dest.writeInt(this.original);
            dest.writeString(this.papersNumber);
            dest.writeInt(this.papersType);
        }

        public UserInfoBean() {
        }

        protected UserInfoBean(Parcel in) {
            this.id = in.readInt();
            this.birthday = in.readString();
            this.createTime = in.readString();
            this.gender = in.readInt();
            this.avatar = in.readString();
            this.idcardBackImg = in.readString();
            this.idcardFrontImg = in.readString();
            this.mobilePhone = in.readString();
            this.name = in.readString();
            this.original = in.readInt();
            this.papersNumber = in.readString();
            this.papersType = in.readInt();
        }

        public static final Creator<UserInfoBean> CREATOR = new Creator<UserInfoBean>() {
            @Override
            public UserInfoBean createFromParcel(Parcel source) {
                return new UserInfoBean(source);
            }

            @Override
            public UserInfoBean[] newArray(int size) {
                return new UserInfoBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.token, flags);
        dest.writeParcelable(this.userInfo, flags);
    }

    public UserInfo() {
    }

    protected UserInfo(Parcel in) {
        this.token = in.readParcelable(TokenBean.class.getClassLoader());
        this.userInfo = in.readParcelable(UserInfoBean.class.getClassLoader());
    }

    public static final Creator<UserInfo> CREATOR = new Creator<UserInfo>() {
        @Override
        public UserInfo createFromParcel(Parcel source) {
            return new UserInfo(source);
        }

        @Override
        public UserInfo[] newArray(int size) {
            return new UserInfo[size];
        }
    };
}
package ufhealth.integratedmachine.client.network;

import rx.Observable;

import java.io.File;
import java.util.Map;
import java.util.List;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Field;
import okhttp3.MultipartBody;
import retrofit2.http.FieldMap;
import retrofit2.http.Multipart;
import retrofit2.http.FormUrlEncoded;
import ufhealth.integratedmachine.client.bean.main.UserInfo;
import ufhealth.integratedmachine.client.bean.main.WifiMacAddress;
import ufhealth.integratedmachine.client.bean.zxzx.Billinfo;
import ufhealth.integratedmachine.client.bean.BaseReturnData;
import ufhealth.integratedmachine.client.bean.zxzx.PayResult;
import ufhealth.integratedmachine.client.bean.zxzx.DoctorInfo;
import ufhealth.integratedmachine.client.bean.zxzx.DoctorAllInfo;
import ufhealth.integratedmachine.client.bean.zxzx.HotDepartment;
import ufhealth.integratedmachine.client.bean.zxzx.UploadImgReturnInfo;
import ufhealth.integratedmachine.client.bean.zxzx.DoctorInfoOfCondition;

public interface NetUrl
{
    @POST("member/login")
    @FormUrlEncoded
    Observable<BaseReturnData<UserInfo>> login(@Field("idCard") String idCard,@Field("name") String name,@Field("birthday") String birthday,@Field("gender") Integer gender,@Field("nation") String nation,@Field("address") String address,@Field("avatarByte") byte[] avatarByte);

    @POST("member/sendIdentifyingCode")
    @FormUrlEncoded
    Observable<BaseReturnData> getVerifiedCode(@Field("phone") String phoneNum);

    @POST("general/device/getDeviceByCardRead")
    @FormUrlEncoded
    Observable<BaseReturnData<WifiMacAddress>> getWifiMacAddress(@Field("device_id") String wifiMacAddress);

    @POST("member/bindPhone")
    @FormUrlEncoded
    Observable<BaseReturnData> bindPhoneNum(@Field("idCard") String idCard,@Field("phone")String phone,@Field("code")String code);

    @POST("general/hospitalHotDepartment/findHospitalHotDepartment")
    Observable<BaseReturnData<List<HotDepartment>>> zxzxGetHotDepartments();

    @POST("general/doctor/getDoctorListByContion")
    @FormUrlEncoded
    Observable<BaseReturnData<DoctorInfo>> zxzxGetDoctorsInfo(@FieldMap Map<String,String> conditions);

    @POST("member/wechatPayment/queryPayResult")
    @FormUrlEncoded
    Observable<BaseReturnData<PayResult>>  zxzxQueryPayResult(@Field("orderNumber") String orderNumber);

    @POST("zixun/comment/getCommentByDoctor")
    @FormUrlEncoded
    Observable<BaseReturnData<DoctorAllInfo>> zxzxGetDoctorAllInfo(@FieldMap Map<String, String> conditions);

    @POST("zixun/zxorder/userOverOrder")
    @FormUrlEncoded
    Observable<BaseReturnData> zxzxFinishBill(@Field("zxType") String zxType,@Field("order_id") String orderId);

    @POST("zixun/imagetext/uploadTwzxImageList")
    @Multipart
    Observable<BaseReturnData<List<UploadImgReturnInfo>>> twzxUploadDatas(@Part List<MultipartBody.Part> filesList);

    @POST("general/doctor/getDocDepOriList")
    @FormUrlEncoded
    Observable<BaseReturnData<DoctorInfoOfCondition>> zxzxGetDoctorInfoOfCondition(@Field("serviceType") String serviceType);

    @POST("zixun/audio/orderAudioSave")
    @FormUrlEncoded
    Observable<BaseReturnData<Billinfo>> zxzxCreateAudioBill(@Field("doctor_id") String doctorId, @Field("timeMin") String timeMin);

    @POST("zixun/video/orderVideoSave")
    @FormUrlEncoded
    Observable<BaseReturnData<Billinfo>> zxzxCreateVideoBill(@Field("doctor_id") String doctorId,@Field("timeMin") String timeMin);

    @POST("zixun/zxorder/memberHangUp")
    @FormUrlEncoded
    Observable<BaseReturnData> hangUpConsultation(@Field("zxType") String zxType,@Field("order_id") String order_id,@Field("server_time") String server_time);

    @POST("zixun/imagetext/imageTextSave")
    @FormUrlEncoded
    Observable<BaseReturnData<Billinfo>> zxzxCreateImageTextBill(@Field("doctor_id") String[] doctorsId,@Field("imgList") String[] imgsPath,@Field("text_description") String description);
}
package ufhealth.integratedmachine.client.network;

import rx.Observable;
import java.util.Map;
import java.util.List;
import retrofit2.http.POST;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import ufhealth.integratedmachine.client.bean.zxzx.Billinfo;
import ufhealth.integratedmachine.client.bean.BaseReturnData;
import ufhealth.integratedmachine.client.bean.zxzx.PayResult;
import ufhealth.integratedmachine.client.bean.zxzx.DoctorInfo;
import ufhealth.integratedmachine.client.bean.zxzx.DoctorAllInfo;
import ufhealth.integratedmachine.client.bean.zxzx.HotDepartment;
import ufhealth.integratedmachine.client.bean.zxzx.DoctorInfoOfCondition;

public interface NetUrl
{
    @POST("general/hospitalHotDepartment/findHospitalHotDepartment")
    Observable<BaseReturnData<List<HotDepartment>>> zxzxGetHotDepartments();

    @POST("general/doctor/getDocDepOriList")
    Observable<BaseReturnData<DoctorInfoOfCondition>> zxzxGetDoctorInfoOfCondition();

    @POST("general/doctor/getDoctorListByContion")
    @FormUrlEncoded
    Observable<BaseReturnData<DoctorInfo>> zxzxGetDoctorsInfo(@FieldMap Map<String,String> conditions);

    @POST("member/wechatPayment/queryPayResult")
    @FormUrlEncoded
    Observable<BaseReturnData<PayResult>>  zxzxQueryPayResult(@Field("orderNumber") String orderNumber);

    @POST("zixun/comment/getCommentByDoctor")
    @FormUrlEncoded
    Observable<BaseReturnData<DoctorAllInfo>> zxzxGetDoctorAllInfo(@FieldMap Map<String, String> conditions);

    @POST("zixun/imagetext/uploadTwzxImage")
    @FormUrlEncoded
    Observable<BaseReturnData> twzxUploadDatas(@FieldMap Map<String, String> conditions, @Field("imgList") String[] imagesPath);

    @POST("zixun/audio/orderAudioSave")
    @FormUrlEncoded
    Observable<BaseReturnData<Billinfo>> zxzxCreateAudioBill(@Field("doctor_id") String doctorId, @Field("timeMin") String timeMin);

    @POST("zixun/video/orderVideoSave")
    @FormUrlEncoded
    Observable<BaseReturnData<Billinfo>> zxzxCreateVideoBill(@Field("doctor_id") String doctorId,@Field("timeMin") String timeMin);

    @POST("zixun/imagetext/imageTextSave")
    @FormUrlEncoded
    Observable<BaseReturnData<Billinfo>> zxzxCreateImageTextBill(@Field("doctor_id") String[] doctorsId,@Field("imgList") String[] imgsPath,@Field("text_description") String description);
}
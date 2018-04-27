package ufhealth.integratedmachine.client.network;

import rx.Observable;
import java.io.File;
import java.util.Map;
import java.util.List;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.Multipart;
import retrofit2.http.FormUrlEncoded;
import ufhealth.integratedmachine.client.bean.BaseReturnData;
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

    @POST("zixun/comment/getCommentByDoctor")
    @FormUrlEncoded
    Observable<BaseReturnData<DoctorAllInfo>> zxzxGetDoctorAllInfo(@FieldMap Map<String, String> conditions);

    @POST("zixun/video/orderAudioSave")
    @FormUrlEncoded
    Observable<BaseReturnData<DoctorAllInfo>> zxzxCreateAudioBill(@FieldMap Map<String, String> conditions);

    @POST("zixun/video/orderVideoSave")
    @FormUrlEncoded
    Observable<BaseReturnData<DoctorAllInfo>> zxzxCreateVideoBill(@FieldMap Map<String, String> conditions);

    @POST("zixun/imagetext/imageTextSave")
    @FormUrlEncoded
    Observable<BaseReturnData> twzxUploadDatas(@FieldMap Map<String, String> conditions, @Field("imgList") String[] imagesPath);

    @POST("zixun/imagetext/imageTextSave")
    @Multipart
    Observable<BaseReturnData<DoctorAllInfo>> zxzxCreateImageTextBill(@Field("doctor_id") String[] doctorsId,@Part("imgList") File files,@Field("text_description") String description);
}
package ufhealth.integratedmachine.client.network;

import retrofit2.http.POST;
import retrofit2.http.Field;
import io.reactivex.Observable;
import retrofit2.http.FormUrlEncoded;
import ufhealth.integratedmachine.client.bean.main.UserInfos;
import ufhealth.integratedmachine.client.bean.BaseReturnData;

public interface NetUrl
{
    @POST("login")
    @FormUrlEncoded
    Observable<BaseReturnData<UserInfos>> signIn(@Field("account") String account, @Field("password") String password);
}
package ufhealth.integratedmachine.client.network;

import java.util.Map;
import okhttp3.RequestBody;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import io.reactivex.Observable;
import retrofit2.http.Multipart;
import ufhealth.integratedmachine.client.bean.main.UserInfos;
import ufhealth.integratedmachine.client.bean.BaseReturnData;

public interface NetUrl
{
    @POST("login")
    @Multipart
    Observable<BaseReturnData<UserInfos>> signIn(@PartMap Map<String, RequestBody> params);
}
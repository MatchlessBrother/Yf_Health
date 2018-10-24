package ufhealth.integratedmachine.client.network;

import java.util.Map;
import okhttp3.RequestBody;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import io.reactivex.Observable;
import retrofit2.http.Multipart;
import ufhealth.integratedmachine.client.bean.main.UserInfo;
import ufhealth.integratedmachine.client.bean.BaseReturnData;
import ufhealth.integratedmachine.client.bean.hztj.TjDataInfo;
import ufhealth.integratedmachine.client.bean.hztj.TjCondition;
import ufhealth.integratedmachine.client.bean.bjcz.BjczPageInfo;
import ufhealth.integratedmachine.client.bean.lsbj.BjHistroyPageInfo;
import ufhealth.integratedmachine.client.bean.lsbj.BjHistroyCondition;
import ufhealth.integratedmachine.client.bean.bjcz.BjczHistroyPageInfo;

public interface NetUrl
{
    @POST("/auth/logout.app")
    Observable<BaseReturnData> signOut();

    @POST("/cgqkshbj/stat/loadCondition.app")
    Observable<BaseReturnData<TjCondition>> requestHztjConditions();

    @POST("/cgqkshbj/record/loadCondition.app")
    Observable<BaseReturnData<BjHistroyCondition>> requestHistroyAlarmOfConditions();

    @POST("/auth/modifyPassword.app")
    @Multipart
    Observable<BaseReturnData> modifyPassword(@PartMap Map<String, RequestBody> params);

    @POST("/auth/login.app")
    @Multipart
    Observable<BaseReturnData<UserInfo>> signIn(@PartMap Map<String, RequestBody> params);

    @POST("/cgqkshbj/stat/stat.app")
    @Multipart
    Observable<BaseReturnData<TjDataInfo>> requestHztjDatas(@PartMap Map<String, RequestBody> params);

    @POST("/cgqkshbj/record/list.app")
    @Multipart
    Observable<BaseReturnData<BjczPageInfo>> requestAlarmDatas(@PartMap Map<String, RequestBody> params);

    @POST("/cgqkshbj/record/list.app")
    @Multipart
    Observable<BaseReturnData<BjHistroyPageInfo>> requestHistroyAlarmDatas(@PartMap Map<String, RequestBody> params);

    @POST("/cgqkshbj/record/list.app")
    @Multipart
    Observable<BaseReturnData<BjczHistroyPageInfo>> requestAlarmHistroyDatas(@PartMap Map<String, RequestBody> params);





}
package ufhealth.integratedmachine.client.network;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import ufhealth.integratedmachine.client.Bean.BaseReturnData;

public interface NetUrl
{
    @POST("http://dimdim215.imwork.net:47017/zixun/imagetext/imageTextSave")
    @FormUrlEncoded
    Call<BaseReturnData<String>> test(@Field("amount") String amount,@Field("doctorId") String doctorId);

  /*  Call<BaseReturnData<String>> call = NetClient.getInstance(this).getNetUrl().test("1","2");
        call.enqueue(new Callback<BaseReturnData<String>>() {
    @Override
    public void onResponse(Call<BaseReturnData<String>> call, Response<BaseReturnData<String>> response) {
        showToast("responseSuccess:" + response.body().toString());
    }

    @Override
    public void onFailure(Call<BaseReturnData<String>> call, Throwable t) {
        showToast("请求失败");
    }
});*/
}
package ufhealth.integratedmachine.client.network;

import java.util.List;
import retrofit2.Retrofit;
import java.util.ArrayList;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import android.content.Context;
import ufhealth.integratedmachine.client.BuildConfig;
import com.yuan.devlibrary._9__Network.retrofit.Http1BaseConfig.RetrofitFactory;
import com.yuan.devlibrary._9__Network.okhttp.Http3Interceptions.RetrysInterceptor;
import com.yuan.devlibrary._9__Network.okhttp.Http1BasicConfig.OkhttpClientFactory;
import com.yuan.devlibrary._9__Network.okhttp.Http3Interceptions.TokenInterceptor_UnPersistentStore;

public class NetClient
{
    private NetUrl mNetUrl;
    private Context mContext;
    public static NetClient mNetClient;
    private volatile Retrofit mRetrofit;
    private volatile OkHttpClient mOkHttpClient;

    public NetUrl getNetUrl()
    {
        if(null == mNetUrl && null != mRetrofit)
            mNetUrl = mRetrofit.create(NetUrl.class);
        return mNetUrl;
    }

    private NetClient(Context context)
    {
        mContext = context;
        List<Interceptor> appInterceptorList = new ArrayList<Interceptor>();
        appInterceptorList.add(new RetrysInterceptor(2,1000,2000));
        TokenInterceptor_UnPersistentStore tokenInterceptor_unPersistentStore = TokenInterceptor_UnPersistentStore.getInstance();
        tokenInterceptor_unPersistentStore.setMaxNumOfTryOn(0);
        appInterceptorList.add(tokenInterceptor_unPersistentStore);
        mOkHttpClient = OkhttpClientFactory.create(context,30,30,30,"",0,appInterceptorList,null);
        mRetrofit = RetrofitFactory.create(mOkHttpClient, BuildConfig.SERVICE_URL);
    }

    public synchronized static NetClient getInstance(Context context)
    {
        if(null == mNetClient)
            mNetClient = new NetClient(context);
        return mNetClient;
    }

    public Retrofit getRetrofit()
    {
        return mRetrofit;
    }

    public OkHttpClient getOkHttpClient()
    {
        return mOkHttpClient;
    }

    public void setRetrofit(Retrofit retrofit)
    {
        mRetrofit = retrofit;
    }

    public void setOkHttpClient(OkHttpClient okHttpClient)
    {
        mOkHttpClient = okHttpClient;
    }
}
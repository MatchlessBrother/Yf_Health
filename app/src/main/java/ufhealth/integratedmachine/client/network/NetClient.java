package ufhealth.integratedmachine.client.network;

import retrofit2.Retrofit;
import okhttp3.OkHttpClient;
import android.content.Context;
import ufhealth.integratedmachine.client.BuildConfig;
import com.yuan.devlibrary._9__Network.retrofit.Http1BaseConfig.RetrofitFactory;
import com.yuan.devlibrary._9__Network.okhttp.Http1BasicConfig.OkhttpClientFactory;

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
        mOkHttpClient = OkhttpClientFactory.create(context);
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
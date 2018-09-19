package ufhealth.integratedmachine.client.ui.base;

import rx.Observer;
import android.content.Context;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.net.SocketTimeoutException;
import retrofit2.adapter.rxjava.HttpException;
import com.yuan.devlibrary._12_______Utils.NetUtils;
import ufhealth.integratedmachine.client.network.NetFlags;
import ufhealth.integratedmachine.client.bean.BaseReturnData;

public class BaseMvp_NetCallBack<T extends BaseReturnData> implements Observer<T>
{
    private Context mContext;
    private BaseMvp_LocalCallBack mBaseMvpLocalCallBack;

    public BaseMvp_NetCallBack(Context context, BaseMvp_LocalCallBack<T> baseMvpLocalCallBack)
    {
        mContext = context;
        mBaseMvpLocalCallBack = baseMvpLocalCallBack;
    }

    public void onCompleted()
    {
        mBaseMvpLocalCallBack.onFinish();

    }

    public void onError(Throwable e)
    {
        if (!NetUtils.WhetherConnectNet(mContext))
        {
            mBaseMvpLocalCallBack.onError("网络不可用,请稍后再试...");
        }
        else if (e instanceof SocketTimeoutException)
        {
            mBaseMvpLocalCallBack.onError("网络连接超时,请稍后再试...");
        }
        else if (e instanceof ConnectException)
        {
            mBaseMvpLocalCallBack.onError("网络连接超时,请稍后再试...");
        }
        else if (e instanceof HttpException)
        {
            mBaseMvpLocalCallBack.onError("网络异常,请稍后再试...");
        }
        else if (e instanceof UnknownHostException)
        {
            mBaseMvpLocalCallBack.onError("网络异常,请稍后再试...");
        }
        else
        {
            mBaseMvpLocalCallBack.onError("网络发生错误,请稍后再试...");
        }
        mBaseMvpLocalCallBack.onFinish();
    }

    public void onNext(T returnDatas)
    {
        if (NetFlags.RequestFail.equals(returnDatas.getCode()))
        {
            mBaseMvpLocalCallBack.onFailure(returnDatas.getMsg());
        }
        else if(NetFlags.RequestSuccess.equals(returnDatas.getCode()))
        {
            mBaseMvpLocalCallBack.onSuccess(returnDatas);
        }
    }
}
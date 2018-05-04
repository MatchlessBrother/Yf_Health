package ufhealth.integratedmachine.client.ui.zxzx.model;

import android.content.Context;

import retrofit2.http.Field;
import rx.schedulers.Schedulers;
import rx.android.schedulers.AndroidSchedulers;
import ufhealth.integratedmachine.client.network.NetClient;
import ufhealth.integratedmachine.client.bean.zxzx.Billinfo;
import ufhealth.integratedmachine.client.bean.BaseReturnData;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_NetCallBack;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_LocalCallBack;

public class BillModel
{
    public static void createImageTextBill(Context context,String[] doctorsId,String[] imgsPath,String description,BaseMvp_LocalCallBack<BaseReturnData<Billinfo>> netCallBack)
    {
        netCallBack.onStart();
        NetClient.getInstance(context).getNetUrl().zxzxCreateImageTextBill(doctorsId,imgsPath,description).
                subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe(new BaseMvp_NetCallBack(context,netCallBack));
    }

    public static void createAudioBill(Context context,String doctorId,String timeMin,BaseMvp_LocalCallBack<BaseReturnData<Billinfo>> netCallBack)
    {
        netCallBack.onStart();
        NetClient.getInstance(context).getNetUrl().zxzxCreateAudioBill(doctorId,timeMin).
                subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe(new BaseMvp_NetCallBack(context,netCallBack));
    }

    public static void createVideoBill(Context context,String doctorId,String timeMin,BaseMvp_LocalCallBack<BaseReturnData<Billinfo>> netCallBack)
    {
        netCallBack.onStart();
        NetClient.getInstance(context).getNetUrl().zxzxCreateVideoBill(doctorId,timeMin).
                subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe(new BaseMvp_NetCallBack(context,netCallBack));
    }

    public static void finishBill(Context context, String zxType,String orderId,BaseMvp_LocalCallBack<BaseReturnData> netCallBack)
    {
        netCallBack.onStart();
        NetClient.getInstance(context).getNetUrl().zxzxFinishBill(zxType,orderId).
                subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe(new BaseMvp_NetCallBack(context,netCallBack));
    }
}
package ufhealth.integratedmachine.client.ui.zxzx.model;

import java.util.HashMap;
import android.content.Context;
import rx.schedulers.Schedulers;
import rx.android.schedulers.AndroidSchedulers;
import ufhealth.integratedmachine.client.network.NetClient;
import ufhealth.integratedmachine.client.bean.zxzx.Billinfo;
import ufhealth.integratedmachine.client.bean.BaseReturnData;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_NetCallBack;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_LocalCallBack;

public class ImgModel
{
    public static void uploadImg(Context context,String[] imgsPath, BaseMvp_LocalCallBack<BaseReturnData<Billinfo>> netCallBack)
    {
        netCallBack.onStart();
        NetClient.getInstance(context).getNetUrl().twzxUploadDatas(new HashMap<String, String>(),imgsPath).
                subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe(new BaseMvp_NetCallBack(context,netCallBack));
    }

    public static void createImageTextBill(Context context,String[] doctorsId,String[] imgsPath,String description,BaseMvp_LocalCallBack<BaseReturnData<Billinfo>> netCallBack)
    {
        netCallBack.onStart();
        NetClient.getInstance(context).getNetUrl().zxzxCreateImageTextBill(doctorsId,imgsPath,description).
                subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe(new BaseMvp_NetCallBack(context,netCallBack));
    }
}
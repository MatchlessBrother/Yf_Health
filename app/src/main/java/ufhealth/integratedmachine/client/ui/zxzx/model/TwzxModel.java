package ufhealth.integratedmachine.client.ui.zxzx.model;

import java.util.Map;
import android.content.Context;
import rx.schedulers.Schedulers;
import rx.android.schedulers.AndroidSchedulers;
import ufhealth.integratedmachine.client.network.NetClient;
import ufhealth.integratedmachine.client.bean.BaseReturnData;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_NetCallBack;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_LocalCallBack;

public class TwzxModel
{
    public static void uploadDatas(Context context,Map<String,String> conditions,String[] imagesPath,BaseMvp_LocalCallBack<BaseReturnData> netCallBack)
    {
        netCallBack.onStart();
        NetClient.getInstance(context).getNetUrl().twzxUploadDatas(conditions,imagesPath).
                subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe(new BaseMvp_NetCallBack(context,netCallBack));
    }
}
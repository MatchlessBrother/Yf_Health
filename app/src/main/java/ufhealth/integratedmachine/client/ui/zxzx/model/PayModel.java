package ufhealth.integratedmachine.client.ui.zxzx.model;

import android.content.Context;
import rx.schedulers.Schedulers;
import rx.android.schedulers.AndroidSchedulers;
import ufhealth.integratedmachine.client.network.NetClient;
import ufhealth.integratedmachine.client.bean.BaseReturnData;
import ufhealth.integratedmachine.client.bean.zxzx.PayResult;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_NetCallBack;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_LocalCallBack;

public class PayModel
{
    public static void queryPayResult(Context context,String orderNumber,BaseMvp_LocalCallBack<BaseReturnData<PayResult>> netCallBack)
    {
        NetClient.getInstance(context).getNetUrl().zxzxQueryPayResult(orderNumber).
                subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe(new BaseMvp_NetCallBack(context,netCallBack));
    }
}
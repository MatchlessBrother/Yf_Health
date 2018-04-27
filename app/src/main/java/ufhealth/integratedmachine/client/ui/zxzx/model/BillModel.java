package ufhealth.integratedmachine.client.ui.zxzx.model;

import android.content.Context;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import ufhealth.integratedmachine.client.bean.BaseReturnData;
import ufhealth.integratedmachine.client.bean.zxzx.DoctorInfoOfCondition;
import ufhealth.integratedmachine.client.network.NetClient;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_LocalCallBack;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_NetCallBack;

public class BillModel
{
    public static void getDoctorInfoOfConditions(Context context, BaseMvp_LocalCallBack<BaseReturnData<DoctorInfoOfCondition>> netCallBack)
    {
        netCallBack.onStart();
        NetClient.getInstance(context).getNetUrl().zxzxGetDoctorInfoOfCondition().
                subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe(new BaseMvp_NetCallBack(context,netCallBack));
    }
}


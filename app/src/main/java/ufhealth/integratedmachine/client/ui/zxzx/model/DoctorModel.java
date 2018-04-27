package ufhealth.integratedmachine.client.ui.zxzx.model;

import java.util.Map;
import android.content.Context;
import rx.schedulers.Schedulers;
import rx.android.schedulers.AndroidSchedulers;
import ufhealth.integratedmachine.client.network.NetClient;
import ufhealth.integratedmachine.client.bean.BaseReturnData;
import ufhealth.integratedmachine.client.bean.zxzx.DoctorInfo;
import ufhealth.integratedmachine.client.bean.zxzx.DoctorAllInfo;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_NetCallBack;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_LocalCallBack;
import ufhealth.integratedmachine.client.bean.zxzx.DoctorInfoOfCondition;

public class DoctorModel
{
    public static void getDoctorInfoOfConditions(Context context, BaseMvp_LocalCallBack<BaseReturnData<DoctorInfoOfCondition>> netCallBack)
    {
        netCallBack.onStart();
        NetClient.getInstance(context).getNetUrl().zxzxGetDoctorInfoOfCondition().
                subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe(new BaseMvp_NetCallBack(context,netCallBack));
    }

    public static void getDoctorsInfo(Context context, Map<String,String> conditions,BaseMvp_LocalCallBack<BaseReturnData<DoctorInfo>> netCallBack)
    {
        netCallBack.onStart();
        NetClient.getInstance(context).getNetUrl().zxzxGetDoctorsInfo(conditions).
                subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe(new BaseMvp_NetCallBack(context,netCallBack));
    }

    public static void getDoctorAllInfo(Context context, Map<String,String> conditions,BaseMvp_LocalCallBack<BaseReturnData<DoctorAllInfo>> netCallBack)
    {
        netCallBack.onStart();
        NetClient.getInstance(context).getNetUrl().zxzxGetDoctorAllInfo(conditions).
                subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe(new BaseMvp_NetCallBack(context,netCallBack));
    }
}

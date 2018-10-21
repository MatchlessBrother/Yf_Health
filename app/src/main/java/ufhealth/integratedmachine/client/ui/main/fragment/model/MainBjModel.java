package ufhealth.integratedmachine.client.ui.main.fragment.model;

import android.content.Context;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.android.schedulers.AndroidSchedulers;
import ufhealth.integratedmachine.client.network.NetClient;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_PVModel;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_NetCallBack;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_LocalCallBack;

public class MainBjModel extends BaseMvp_PVModel
{
    public void executeOfNet(Context context, final BaseMvp_LocalCallBack localCallBack)
    {
        localCallBack.onStart();
        NetClient.getInstance(context).getNetUrl().requestAlarmDatas(getMultipartForms()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseMvp_NetCallBack(context,localCallBack));
    }

    public void executeOfLocal(Context context, BaseMvp_LocalCallBack localCallBack)
    {
        localCallBack.onStart();
        localCallBack.onFinish();
    }
}
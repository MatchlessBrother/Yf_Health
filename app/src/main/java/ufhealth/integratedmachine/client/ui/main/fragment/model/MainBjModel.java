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
    public static final int RequestAlarmDatas = 0x0001;

    public void executeOfNet(Context context, int netRequestCode, BaseMvp_LocalCallBack localCallBack)
    {
        localCallBack.onStart();
        switch(netRequestCode)
        {
            case RequestAlarmDatas:NetClient.getInstance(context).getNetUrl().requestAlarmDatas(getMultipartForms()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseMvp_NetCallBack(context,localCallBack));break;
        }
    }

    public void executeOfLocal(Context context, int localRequestCode, BaseMvp_LocalCallBack localCallBack)
    {
        localCallBack.onStart();
        localCallBack.onFinish();
    }
}
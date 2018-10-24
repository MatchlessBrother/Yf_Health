package ufhealth.integratedmachine.client.ui.main.fragment.model;

import android.content.Context;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.android.schedulers.AndroidSchedulers;
import ufhealth.integratedmachine.client.network.NetClient;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_PVModel;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_NetCallBack;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_LocalCallBack;

public class MainBjHistroyModel extends BaseMvp_PVModel
{
    public static final int RequestHistroyAlarmDatas = 0x0001;
    public static final int RequestDatasOfCondition = 0x0002;

    public void executeOfNet(Context context, int netRequestCode, BaseMvp_LocalCallBack localCallBack)
    {
        localCallBack.onStart();
        switch(netRequestCode)
        {
            case RequestHistroyAlarmDatas:NetClient.getInstance(context).getNetUrl().requestHistroyAlarmDatas(getMultipartForms()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseMvp_NetCallBack(context,localCallBack));break;
            case RequestDatasOfCondition:NetClient.getInstance(context).getNetUrl().requestHistroyAlarmOfConditions().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseMvp_NetCallBack(context,localCallBack));break;
        }
    }

    public void executeOfLocal(Context context, int localRequestCode, BaseMvp_LocalCallBack localCallBack)
    {
        localCallBack.onStart();
        localCallBack.onFinish();
    }
}
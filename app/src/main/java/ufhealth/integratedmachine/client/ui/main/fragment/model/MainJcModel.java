package ufhealth.integratedmachine.client.ui.main.fragment.model;

import android.content.Context;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.android.schedulers.AndroidSchedulers;
import ufhealth.integratedmachine.client.network.NetClient;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_PVModel;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_NetObjCallBack;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_NetListCallBack;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_LocalObjCallBack;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_LocalListCallBack;

public class MainJcModel extends BaseMvp_PVModel
{
    public static final int RequestDatasInfo = 0x0001;
    public static final int RequestDatasOfCondition = 0x0002;

    public void executeOfNet(Context context, int netRequestCode, BaseMvp_LocalObjCallBack localCallBack)
    {
        switch(netRequestCode)
        {
            case RequestDatasOfCondition:NetClient.getInstance(context).getNetUrl().requestJcDatasOfCondition().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseMvp_NetObjCallBack(context,localCallBack));break;
        }
    }

    public void executeOfNet(Context context, int netRequestCode, BaseMvp_LocalListCallBack localCallBack)
    {
        switch(netRequestCode)
        {
            case RequestDatasInfo:NetClient.getInstance(context).getNetUrl().requestJcDatasInfo(getMultipartForms()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseMvp_NetListCallBack(context,localCallBack));break;
        }
    }

    public void executeOfLocal(Context context, int localRequestCode, BaseMvp_LocalObjCallBack localCallBack)
    {
        localCallBack.onStart();
        localCallBack.onFinish();
    }
}
package ufhealth.integratedmachine.client.ui.bjcz.activity.model;

import android.content.Context;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.android.schedulers.AndroidSchedulers;
import ufhealth.integratedmachine.client.network.NetClient;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_PVModel;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_NetCallBack;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_LocalCallBack;

public class BjczModel extends BaseMvp_PVModel
{
    public static final int UploadData = 0x0001;

    public void executeOfNet(Context context, int netRequestCode, BaseMvp_LocalCallBack localCallBack)
    {
        localCallBack.onStart();
        switch(netRequestCode)
        {
            case UploadData:NetClient.getInstance(context).getNetUrl().signIn(getMultipartForms()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseMvp_NetCallBack(context,localCallBack));break;
        }
    }

    public void executeOfLocal(Context context, int localRequestCode, BaseMvp_LocalCallBack localCallBack)
    {
        localCallBack.onStart();
        localCallBack.onFinish();
    }
}
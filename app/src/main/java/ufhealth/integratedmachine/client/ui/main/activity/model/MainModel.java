package ufhealth.integratedmachine.client.ui.main.activity.model;

import android.content.Context;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_PVModel;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_LocalCallBack;

public class MainModel extends BaseMvp_PVModel
{
    public void executeOfNet(Context context, BaseMvp_LocalCallBack localCallBack)
    {
        localCallBack.onStart();
        localCallBack.onFinish();
    }

    public void executeOfLocal(Context context, BaseMvp_LocalCallBack localCallBack)
    {
        localCallBack.onStart();
        localCallBack.onFinish();
    }
}
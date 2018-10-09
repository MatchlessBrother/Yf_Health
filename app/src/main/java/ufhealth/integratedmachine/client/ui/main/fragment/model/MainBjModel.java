package ufhealth.integratedmachine.client.ui.main.fragment.model;

import android.content.Context;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_PVModel;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_LocalCallBack;

public class MainBjModel extends BaseMvp_PVModel
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
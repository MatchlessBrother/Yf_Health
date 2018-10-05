package ufhealth.integratedmachine.client.ui.main.model;

import android.content.Context;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.android.schedulers.AndroidSchedulers;
import ufhealth.integratedmachine.client.network.NetClient;
import ufhealth.integratedmachine.client.bean.BaseReturnData;
import ufhealth.integratedmachine.client.bean.main.UserInfos;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_NetCallBack;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_LocalCallBack;

public class SignInModel
{
    public static void signIn(Context context, String account, String password, BaseMvp_LocalCallBack<BaseReturnData<UserInfos>> localCallBack)
    {
        localCallBack.onStart();
        NetClient.getInstance(context).getNetUrl().signIn(account,password).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseMvp_NetCallBack(context,localCallBack));
    }
}
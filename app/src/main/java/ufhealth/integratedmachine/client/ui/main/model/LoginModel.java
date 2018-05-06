package ufhealth.integratedmachine.client.ui.main.model;

import android.content.Context;
import rx.schedulers.Schedulers;
import rx.android.schedulers.AndroidSchedulers;
import ufhealth.integratedmachine.client.network.NetClient;
import ufhealth.integratedmachine.client.bean.main.UserInfo;
import ufhealth.integratedmachine.client.bean.BaseReturnData;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_NetCallBack;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_LocalCallBack;

public class LoginModel
{
    public static void login(Context context,String idCard,String name,String birthday,Integer gender,String nation,String address, BaseMvp_LocalCallBack<BaseReturnData<UserInfo>> netCallBack)
    {
        netCallBack.onStart();
        NetClient.getInstance(context).getNetUrl().login(idCard,name,birthday,gender,nation,address).
                subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe(new BaseMvp_NetCallBack(context,netCallBack));
    }

    public static void getVerifiedCode(Context context,String phoneNum, BaseMvp_LocalCallBack<BaseReturnData> netCallBack)
    {
        netCallBack.onStart();
        NetClient.getInstance(context).getNetUrl().getVerifiedCode(phoneNum).
                subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe(new BaseMvp_NetCallBack(context,netCallBack));
    }

    public static void bindPhoneNum(Context context,String idCard,String phone,String code, BaseMvp_LocalCallBack<BaseReturnData> netCallBack)
    {
        netCallBack.onStart();
        NetClient.getInstance(context).getNetUrl().bindPhoneNum(idCard,phone,code).
                subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe(new BaseMvp_NetCallBack(context,netCallBack));
    }
}
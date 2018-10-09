package ufhealth.integratedmachine.client.ui.main.activity.model;

import android.content.Context;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.android.schedulers.AndroidSchedulers;
import ufhealth.integratedmachine.client.bean.BaseReturnData;
import ufhealth.integratedmachine.client.bean.main.UserInfos;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_PVModel;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_LocalCallBack;

public class SignInModel extends BaseMvp_PVModel
{
    public void executeOfNet(Context context,final BaseMvp_LocalCallBack localCallBack)
    {
        localCallBack.onStart();
        /*NetClient.getInstance(context).getNetUrl().signIn(getMultipartForms()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseMvp_NetCallBack(context,localCallBack));*/
        if(getForms().get("account").toString().trim().equals("admin") && getForms().get("password").toString().trim().equals("123456"))
        {
            Observable.just("start").map(new Function<String,UserInfos>()
            {
                public UserInfos apply(String s) throws Exception
                {
                    Thread.sleep(3000);
                    UserInfos userInfos = new UserInfos();
                    userInfos.setToken("hyu896tyghus875sii");
                    userInfos.setName("admin");
                    return userInfos;
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<UserInfos>()
            {
                public void accept(UserInfos userInfos) throws Exception
                {
                    BaseReturnData<UserInfos> baseReturnData = new BaseReturnData<>();
                    baseReturnData.setCode("1");
                    baseReturnData.setMsg("请求成功");
                    baseReturnData.setData(userInfos);
                    localCallBack.onSuccess(baseReturnData);
                    localCallBack.onFinish();
                }
            });
        }
        else
        {
            localCallBack.onFailure("账号/密码错误，请重试，谢谢！");
            localCallBack.onFinish();
        }
    }

    public void executeOfLocal(Context context, BaseMvp_LocalCallBack localCallBack)
    {
        localCallBack.onStart();
        localCallBack.onFinish();
    }
}
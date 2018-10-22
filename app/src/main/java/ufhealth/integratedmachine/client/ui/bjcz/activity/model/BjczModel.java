package ufhealth.integratedmachine.client.ui.bjcz.activity.model;

import android.content.Context;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.android.schedulers.AndroidSchedulers;
import ufhealth.integratedmachine.client.bean.BaseReturnData;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_PVModel;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_LocalCallBack;

public class BjczModel extends BaseMvp_PVModel
{
    public void executeOfNet(Context context,final BaseMvp_LocalCallBack localCallBack)
    {
        localCallBack.onStart();
        /*NetClient.getInstance(context).getNetUrl().signIn(getMultipartForms()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseMvp_NetCallBack(context,localCallBack));*/
        Observable.just("uploadDatas").map(new Function<String,String>()
        {
            public String apply(String str) throws Exception
            {
                Thread.sleep(2000);
                return "ok";
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>()
        {
            public void accept(String str) throws Exception
            {
                BaseReturnData baseReturnData = new BaseReturnData();
                baseReturnData.setCode("200");baseReturnData.setMsg("success");
                localCallBack. onSuccess(baseReturnData);
                localCallBack.onFinish();
            }
        });
    }

    public void executeOfLocal(Context context,BaseMvp_LocalCallBack localCallBack)
    {
        localCallBack.onStart();
        localCallBack.onFinish();
    }
}
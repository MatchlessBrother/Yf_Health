package ufhealth.integratedmachine.client.ui.fourth.activity.model;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import android.content.Context;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.android.schedulers.AndroidSchedulers;
import ufhealth.integratedmachine.client.bean.BaseReturnData;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_PVModel;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_LocalCallBack;
import ufhealth.integratedmachine.client.bean.fourth.BjczHistroyPageInfo;

public class BjczHistroyModel extends BaseMvp_PVModel
{
    private static int mRequestNumbers = 0;

    public void executeOfNet(Context context, final BaseMvp_LocalCallBack localCallBack)
    {
        localCallBack.onStart();
        /*NetClient.getInstance(context).getNetUrl().signIn(getMultipartForms()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseMvp_NetCallBack(context,localCallBack));*/
        Observable.just("AddDatas").map(new Function<String,BjczHistroyPageInfo>()
        {
            public BjczHistroyPageInfo apply(String str) throws Exception
            {
                Thread.sleep(2000);
                BjczHistroyPageInfo bjczHistroyPageInfo = new BjczHistroyPageInfo();
                bjczHistroyPageInfo.setMaxSizeOfPerPage(10);
                List<BjczHistroyPageInfo.BjczHistroyInfo> bjczHistroyPageInfoList = new ArrayList<>();
                for(int index = 0;index < 10;index++)
                    bjczHistroyPageInfoList.add(new BjczHistroyPageInfo.BjczHistroyInfo(new Date().getTime() + "","暴脾气","研发部","西南方","台式电脑","≥66vol%","≥66vol%"));
                if(mRequestNumbers >= 2)
                    bjczHistroyPageInfoList.remove(0);
                bjczHistroyPageInfo.setBjczHistroyInfoList(bjczHistroyPageInfoList);
                return bjczHistroyPageInfo;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<BjczHistroyPageInfo>()
        {
            public void accept(BjczHistroyPageInfo bjczHistroyPageInfo) throws Exception
            {
                BaseReturnData<BjczHistroyPageInfo> baseReturnData = new BaseReturnData();
                baseReturnData.setCode("200");
                baseReturnData.setMsg("success");
                baseReturnData.setData(bjczHistroyPageInfo);
                localCallBack. onSuccess (baseReturnData);
                localCallBack.onFinish();
                mRequestNumbers++;
            }
        });
    }

    public void executeOfLocal(Context context, BaseMvp_LocalCallBack localCallBack)
    {
        localCallBack.onStart();
        localCallBack.onFinish();
    }
}
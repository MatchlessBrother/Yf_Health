package ufhealth.integratedmachine.client.ui.main.fragment.model;

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
import ufhealth.integratedmachine.client.bean.fourth.BjczPageInfo;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_LocalCallBack;

public class MainBjModel extends BaseMvp_PVModel
{
    private static int mRequestNumbers = 0;

    public void executeOfNet(Context context, final BaseMvp_LocalCallBack localCallBack)
    {
        localCallBack.onStart();
        /*NetClient.getInstance(context).getNetUrl().signIn(getMultipartForms()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseMvp_NetCallBack(context,localCallBack));*/
        Observable.just("AddDatas").map(new Function<String,BjczPageInfo>()
        {
            public BjczPageInfo apply(String str) throws Exception
            {
                Thread.sleep(2000);
                BjczPageInfo bjczPageInfo = new BjczPageInfo();
                bjczPageInfo.setMaxSizeOfPerPage(10);
                List<BjczPageInfo.BjczInfo> bjczPageInfoList = new ArrayList<>();
                for(int index = 0;index < 10;index++)
                    bjczPageInfoList.add(new BjczPageInfo.BjczInfo(new Date().getTime() + "","暴脾气","研发部","西南方","台式电脑","≥66vol%","≥66vol%"));
                if(mRequestNumbers >= 2)
                    bjczPageInfoList.remove(0);
                bjczPageInfo.setBjczInfoList(bjczPageInfoList);
                return bjczPageInfo;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<BjczPageInfo>()
        {
            public void accept(BjczPageInfo bjczPageInfo) throws Exception
            {
                BaseReturnData<BjczPageInfo> baseReturnData = new BaseReturnData();
                baseReturnData.setCode("200");
                baseReturnData.setMsg("success");
                baseReturnData.setData(bjczPageInfo);
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
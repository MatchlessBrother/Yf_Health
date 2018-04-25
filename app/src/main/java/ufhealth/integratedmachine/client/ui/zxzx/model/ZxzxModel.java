package ufhealth.integratedmachine.client.ui.zxzx.model;

import rx.Observable;
import java.util.List;
import android.content.Context;
import rx.schedulers.Schedulers;
import rx.android.schedulers.AndroidSchedulers;
import ufhealth.integratedmachine.client.network.NetClient;
import ufhealth.integratedmachine.client.bean.BaseReturnData;
import ufhealth.integratedmachine.client.bean.zxzx.HotDepartment;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_NetCallBack;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_LocalCallBack;

public class ZxzxModel
{
    public static void getHotDepartments(final Context context,final BaseMvp_LocalCallBack<BaseReturnData<List<HotDepartment>>> netCallBack)
    {
        netCallBack.onStart();
        Observable<BaseReturnData<List<HotDepartment>>> observable = NetClient.getInstance(context).getNetUrl().zxzxGetHotDepartments();
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseMvp_NetCallBack(context,netCallBack));
    }
}
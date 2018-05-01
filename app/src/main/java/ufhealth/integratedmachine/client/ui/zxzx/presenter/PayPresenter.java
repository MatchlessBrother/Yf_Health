package ufhealth.integratedmachine.client.ui.zxzx.presenter;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;
import java.util.concurrent.TimeUnit;
import rx.android.schedulers.AndroidSchedulers;
import ufhealth.integratedmachine.client.bean.BaseReturnData;
import ufhealth.integratedmachine.client.bean.zxzx.PayResult;
import ufhealth.integratedmachine.client.ui.zxzx.model.PayModel;
import ufhealth.integratedmachine.client.ui.zxzx.view_v.PayAct_V;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_Presenter;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_LocalCallBack;

public class PayPresenter extends BaseMvp_Presenter<PayAct_V>
{
    public PayPresenter()
    {

    }

    public void queryPayResult(final String orderNumber)
    {
        if(isAttachContextAndViewLayer())
        {
            PayModel.queryPayResult(getContext(),orderNumber,new BaseMvp_LocalCallBack<BaseReturnData<PayResult>>(this)
            {
                public void onSuccess(BaseReturnData<PayResult> payResultData)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        PayResult payResult = payResultData.getData();
                        if(payResult.getPayStatus() == 2)
                            getViewLayer().PaymentSuccess();
                        else if(payResult.getPayStatus() == 1)
                        {
                            Observable.just("").delay(1500, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<String>()
                            {
                                public void onCompleted(){}
                                public void onNext(String s)
                                {
                                    queryPayResult(orderNumber);
                                }
                                public void onError(Throwable e){}
                            });
                        }
                        else
                        {
                            getViewLayer().PaymentFailure();
                        }
                    }
                }

                public void onFailure(String msg)
                {
                    super.onFailure(msg);
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().PaymentFailure();
                    }
                }

                public void onError(String msg)
                {
                    super.onError(msg);
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().PaymentFailure();
                    }
                }
            });
        }
    }
}
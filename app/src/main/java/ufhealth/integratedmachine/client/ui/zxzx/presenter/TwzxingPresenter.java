package ufhealth.integratedmachine.client.ui.zxzx.presenter;

import ufhealth.integratedmachine.client.bean.BaseReturnData;
import ufhealth.integratedmachine.client.ui.zxzx.model.BillModel;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_Presenter;
import ufhealth.integratedmachine.client.ui.zxzx.view_v.TwzxingAct_V;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_LocalCallBack;

public class TwzxingPresenter extends BaseMvp_Presenter<TwzxingAct_V>
{
    public void finishBill(String zxType,String orderId)
    {
        if(isAttachContextAndViewLayer())
        {
            BillModel.finishBill(getContext(),zxType.toUpperCase(),orderId,new BaseMvp_LocalCallBack<BaseReturnData>(this)
            {
                public void onSuccess(BaseReturnData baseReturnData)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().finishBillSuccess();
                        getViewLayer().showToast("结束咨询操作成功");
                    }
                }

                public void onFailure(String msg)
                {
                    super.onFailure(msg);
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().showToast("结束咨询操作失败");
                    }
                }

                public void onError(String msg)
                {
                    super.onError(msg);
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().showToast("结束咨询操作失败");
                    }
                }
            });
        }
    }
}
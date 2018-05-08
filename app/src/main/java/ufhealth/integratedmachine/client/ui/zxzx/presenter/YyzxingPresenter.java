package ufhealth.integratedmachine.client.ui.zxzx.presenter;

import android.content.Context;
import ufhealth.integratedmachine.client.bean.BaseReturnData;
import ufhealth.integratedmachine.client.ui.zxzx.model.BillModel;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_Presenter;
import ufhealth.integratedmachine.client.ui.zxzx.view_v.YyzxingAct_V;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_LocalCallBack;

public class YyzxingPresenter extends BaseMvp_Presenter<YyzxingAct_V>
{
    public YyzxingPresenter()
    {

    }

    public void endInMidwayZxBill(Context context, String zxType, String order_id, String server_time)
    {
        if(isAttachContextAndViewLayer())
        {
            BillModel.hangUpConsultation(context,zxType,order_id,server_time,new BaseMvp_LocalCallBack<BaseReturnData>(this)
            {
                public void onSuccess(BaseReturnData data)
                {
                    if(isAttachContextAndViewLayer())
                    {

                    }
                }

                public void onFailure(String msg) {}
                public void onError(String msg) {}
            });
        }
    }
}
package ufhealth.integratedmachine.client.ui.bjcz.activity.presenter;

import ufhealth.integratedmachine.client.bean.BaseReturnData;
import ufhealth.integratedmachine.client.bean.bjcz.BjczDetailInfo;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_Presenter;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_EntranceOfModel;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_LocalObjCallBack;
import ufhealth.integratedmachine.client.ui.bjcz.activity.model.BjczDetailModel;
import ufhealth.integratedmachine.client.ui.bjcz.activity.view_v.BjczDetailAct_V;

public class BjczDetailPresenter extends BaseMvp_Presenter<BjczDetailAct_V>
{
    public BjczDetailPresenter()
    {

    }

    public void getDatas(String alarmId)
    {
        if(isAttachContextAndViewLayer())
        {
            BaseMvp_EntranceOfModel.requestDatas(BjczDetailModel.class).
            putForm("id",alarmId).convertForms().executeOfNet(getContext(),BjczDetailModel.RequestAlarmDetailDatas,new BaseMvp_LocalObjCallBack<BaseReturnData<BjczDetailInfo>>(this)
            {
                public void onSuccess(BaseReturnData<BjczDetailInfo> bjczDetailInfo)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().successOfGetDatas(bjczDetailInfo.getData());
                    }
                }

                public void onFailure(String msg)
                {
                    super.onFailure(msg);
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().failOfGetDatas();
                    }
                }

                public void onError(String msg)
                {
                    super.onError(msg);
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().failOfGetDatas();
                    }
                }
            });
        }
    }
}
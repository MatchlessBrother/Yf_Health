package ufhealth.integratedmachine.client.ui.main.fragment.presenter;

import java.util.Map;
import ufhealth.integratedmachine.client.bean.BaseReturnData;
import ufhealth.integratedmachine.client.bean.hztj.TjDataInfo;
import ufhealth.integratedmachine.client.bean.hztj.TjCondition;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_Presenter;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_LocalObjCallBack;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_EntranceOfModel;
import ufhealth.integratedmachine.client.ui.main.fragment.model.MainHzModel;
import ufhealth.integratedmachine.client.ui.main.fragment.view_v.MainHzFrag_V;

public class MainHzPresenter extends BaseMvp_Presenter<MainHzFrag_V>
{
    public MainHzPresenter()
    {

    }

    public void getDatas(Map conditionsMap)
    {
        if(isAttachContextAndViewLayer())
        {
            BaseMvp_EntranceOfModel.requestDatas(MainHzModel.class).
            putForms(conditionsMap).convertForms().executeOfNet(getContext(),MainHzModel.RequestHztjDatas,new BaseMvp_LocalObjCallBack<BaseReturnData<TjDataInfo>>(this)
            {
                public void onSuccess(BaseReturnData<TjDataInfo> tjDataInfo)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().getSuccessOfDatas(tjDataInfo.getData());
                    }
                }

                public void onFailure(String msg)
                {
                    super.onFailure(msg);
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().getFailureOfDatas();
                    }
                }

                public void onError(String msg)
                {
                    super.onError(msg);
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().getFailureOfDatas();
                    }
                }
            });
        }
    }

    public void getConditions(final boolean isNeedDrawableLayout)
    {
        if(isAttachContextAndViewLayer())
        {
            BaseMvp_EntranceOfModel.requestDatas(MainHzModel.class).
            executeOfNet(getContext(),MainHzModel.RequestHztjCondtions,new BaseMvp_LocalObjCallBack<BaseReturnData<TjCondition>>(this)
            {
                public void onSuccess(BaseReturnData<TjCondition> tjCondition)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().getSuccessOfConditions(tjCondition.getData(),isNeedDrawableLayout);
                    }
                }
            });
        }
    }
}
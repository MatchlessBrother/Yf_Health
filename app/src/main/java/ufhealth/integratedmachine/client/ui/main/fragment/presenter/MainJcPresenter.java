package ufhealth.integratedmachine.client.ui.main.fragment.presenter;

import java.util.Map;
import ufhealth.integratedmachine.client.bean.BaseReturnData;
import ufhealth.integratedmachine.client.bean.BaseReturnListData;
import ufhealth.integratedmachine.client.bean.ssjc.JcDataInfo;
import ufhealth.integratedmachine.client.bean.ssjc.JcCondition;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_LocalListCallBack;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_Presenter;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_EntranceOfModel;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_LocalObjCallBack;
import ufhealth.integratedmachine.client.ui.main.fragment.model.MainJcModel;
import ufhealth.integratedmachine.client.ui.main.fragment.view_v.MainJcFrag_V;

public class MainJcPresenter extends BaseMvp_Presenter<MainJcFrag_V>
{
    public MainJcPresenter()
    {

    }

    public void getDatasInfo(Map conditionsMap)
    {
        if(isAttachContextAndViewLayer())
        {
            BaseMvp_EntranceOfModel.requestDatas(MainJcModel.class).
            putForms(conditionsMap).convertForms().executeOfNet(getContext(),MainJcModel.RequestDatasInfo,new BaseMvp_LocalListCallBack<BaseReturnListData<JcDataInfo>>(this)
            {
                public void onSuccess(BaseReturnListData<JcDataInfo> jcDataInfo)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        //getViewLayer().getSuccessOfDataInfos(jcDataInfo.getData());
                        int a = 1;
                        a = a + 1;
                    }
                }

                public void onFailure(String msg)
                {
                    super.onFailure(msg);
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().getFailOfDataInfos();
                    }
                }

                public void onError(String msg)
                {
                    super.onError(msg);
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().getFailOfDataInfos();
                    }
                }
            });
        }
    }

    public void getDatasOfCondition(final boolean isNeedDrawableLayout)
    {
        if(isAttachContextAndViewLayer())
        {
            BaseMvp_EntranceOfModel.requestDatas(MainJcModel.class).
            executeOfNet(getContext(),MainJcModel.RequestDatasOfCondition,new BaseMvp_LocalObjCallBack<BaseReturnData<JcCondition>>(this)
            {
                public void onSuccess(BaseReturnData<JcCondition> jcCondition)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().getSuccessOfCondition(jcCondition.getData(),isNeedDrawableLayout);
                    }
                }
            });
        }
    }
}
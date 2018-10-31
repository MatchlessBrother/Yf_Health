package ufhealth.integratedmachine.client.ui.main.fragment.presenter;

import java.util.Map;
import java.util.Arrays;
import ufhealth.integratedmachine.client.bean.BaseReturnData;
import ufhealth.integratedmachine.client.bean.ssjc.JcDataInfo;
import ufhealth.integratedmachine.client.bean.ssjc.JcCondition;
import ufhealth.integratedmachine.client.bean.BaseReturnListData;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_Presenter;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_EntranceOfModel;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_LocalObjCallBack;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_LocalListCallBack;
import ufhealth.integratedmachine.client.ui.main.fragment.model.MainJcModel;
import ufhealth.integratedmachine.client.ui.main.fragment.view_v.MainJcFrag_V;

public class MainJcPresenter extends BaseMvp_Presenter<MainJcFrag_V>
{
    public MainJcPresenter()
    {

    }

    public void getDatasInfo(Map conditionsMap,boolean isShowProgress)
    {
        if(isAttachContextAndViewLayer())
        {
            final int RequestCode = isShowProgress ? MainJcModel.RequestDatasInfoWithProgress : MainJcModel.RequestDatasInfo;
            BaseMvp_EntranceOfModel.requestDatas(MainJcModel.class).
            putForms(conditionsMap).convertForms().executeOfNet(getContext(),RequestCode,new BaseMvp_LocalListCallBack<BaseReturnListData<JcDataInfo>>(this)
            {
                public void onSuccess(BaseReturnListData<JcDataInfo> jcDataInfo)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().getSuccessOfDataInfos(Arrays.asList(jcDataInfo.getData()));
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
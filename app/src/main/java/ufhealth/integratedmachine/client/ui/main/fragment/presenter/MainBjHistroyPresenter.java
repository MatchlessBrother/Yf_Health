package ufhealth.integratedmachine.client.ui.main.fragment.presenter;

import java.util.Map;
import ufhealth.integratedmachine.client.bean.BaseReturnData;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_Presenter;
import ufhealth.integratedmachine.client.bean.lsbj.BjHistroyPageInfo;
import ufhealth.integratedmachine.client.bean.lsbj.BjHistroyCondition;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_LocalCallBack;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_EntranceOfModel;
import ufhealth.integratedmachine.client.ui.main.fragment.model.MainBjHistroyModel;
import ufhealth.integratedmachine.client.ui.main.fragment.view_v.MainBjHistroyFrag_V;

public class MainBjHistroyPresenter extends BaseMvp_Presenter<MainBjHistroyFrag_V>
{
    private int currentPageOfIndex;
    private int currentPageOfMaxSize;

    public MainBjHistroyPresenter()
    {
        currentPageOfIndex = 0;
        currentPageOfMaxSize = 20;
    }

    public void refreshDatas(Map conditionsMap)
    {
        if(isAttachContextAndViewLayer())
        {
            currentPageOfIndex = 0;
            BaseMvp_EntranceOfModel.requestDatas(MainBjHistroyModel.class).
            putForm("pageIndex",currentPageOfIndex + "").putForm("pageSize",currentPageOfMaxSize + "").putForms(conditionsMap).convertForms().executeOfNet(getContext(),MainBjHistroyModel.RequestHistroyAlarmDatas,new BaseMvp_LocalCallBack<BaseReturnData<BjHistroyPageInfo>>(this)
            {
                public void onSuccess(BaseReturnData<BjHistroyPageInfo> bjHistroyPageInfo)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().finishRefresh();
                        currentPageOfIndex = currentPageOfMaxSize;
                        getViewLayer().refreshDatas(bjHistroyPageInfo.getData());
                    }
                }

                public void onFailure(String msg)
                {
                    super.onFailure(msg);
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().finishRefresh();
                    }
                }

                public void onError(String msg)
                {
                    super.onError(msg);
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().finishRefresh();
                    }
                }
            });
        }
    }

    public void loadMoreDatas(Map conditionsMap)
    {
        if(isAttachContextAndViewLayer())
        {
            BaseMvp_EntranceOfModel.requestDatas(MainBjHistroyModel.class).
            putForm("pageIndex",currentPageOfIndex + "").putForm("pageSize",currentPageOfMaxSize + "").putForms(conditionsMap).convertForms().executeOfNet(getContext(),MainBjHistroyModel.RequestHistroyAlarmDatas,new BaseMvp_LocalCallBack<BaseReturnData<BjHistroyPageInfo>>(this)
            {
                public void onSuccess(BaseReturnData<BjHistroyPageInfo> bjHistroyPageInfo)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().finishLoadMore();
                        currentPageOfIndex += currentPageOfMaxSize;
                        getViewLayer().loadMoreDatas(bjHistroyPageInfo.getData());
                    }
                }

                public void onFailure(String msg)
                {
                    super.onFailure(msg);
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().finishLoadMore();
                    }
                }

                public void onError(String msg)
                {
                    super.onError(msg);
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().finishLoadMore();
                    }
                }
            });
        }
    }

    public void getDatasOfCondition(final boolean isNeedDrawableLayout)
    {
        if(isAttachContextAndViewLayer())
        {
            BaseMvp_EntranceOfModel.requestDatas(MainBjHistroyModel.class).
            executeOfNet(getContext(),MainBjHistroyModel.RequestDatasOfCondition,new BaseMvp_LocalCallBack<BaseReturnData<BjHistroyCondition>>(this)
            {
                public void onSuccess(BaseReturnData<BjHistroyCondition> bjHistroyCondition)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().getSuccessOfCondition(bjHistroyCondition.getData(),isNeedDrawableLayout);
                    }
                }
            });
        }
    }
}
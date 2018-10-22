package ufhealth.integratedmachine.client.ui.bjcz.activity.presenter;

import ufhealth.integratedmachine.client.bean.BaseReturnData;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_Presenter;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_LocalCallBack;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_EntranceOfModel;
import ufhealth.integratedmachine.client.bean.bjcz.BjczHistroyPageInfo;
import ufhealth.integratedmachine.client.ui.bjcz.activity.model.BjczHistroyModel;
import ufhealth.integratedmachine.client.ui.bjcz.activity.view_v.BjczHistroyAct_V;

public class BjczHistroyPresenter extends BaseMvp_Presenter<BjczHistroyAct_V>
{
    private int currentPageOfIndex;
    private int currentPageOfMaxSize;

    public BjczHistroyPresenter()
    {
        currentPageOfIndex = 0;
        currentPageOfMaxSize = 20;
    }

    public void refreshDatas()
    {
        if(isAttachContextAndViewLayer())
        {
            currentPageOfIndex = 0;
            BaseMvp_EntranceOfModel.requestDatas(BjczHistroyModel.class).
            putForm("pageIndex",currentPageOfIndex + "").putForm("pageSize",currentPageOfMaxSize+"").putForm("handleStatus","2").convertForms().executeOfNet(getContext(),new BaseMvp_LocalCallBack<BaseReturnData<BjczHistroyPageInfo>>(this)
            {
                public void onSuccess(BaseReturnData<BjczHistroyPageInfo> bjczHistroyPageInfo)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().finishRefresh();
                        currentPageOfIndex = currentPageOfMaxSize;
                        getViewLayer().refreshDatas(bjczHistroyPageInfo.getData());
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

    public void loadMoreDatas()
    {
        if(isAttachContextAndViewLayer())
        {
            BaseMvp_EntranceOfModel.requestDatas(BjczHistroyModel.class).
            putForm("pageIndex",currentPageOfIndex + "").putForm("pageSize",currentPageOfMaxSize + "").putForm("handleStatus","2").convertForms().executeOfNet(getContext(),new BaseMvp_LocalCallBack<BaseReturnData<BjczHistroyPageInfo>>(this)
            {
                public void onSuccess(BaseReturnData<BjczHistroyPageInfo> bjczHistroyPageInfo)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().finishLoadMore();
                        currentPageOfIndex += currentPageOfMaxSize;
                        getViewLayer().loadMoreDatas(bjczHistroyPageInfo.getData());
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
}
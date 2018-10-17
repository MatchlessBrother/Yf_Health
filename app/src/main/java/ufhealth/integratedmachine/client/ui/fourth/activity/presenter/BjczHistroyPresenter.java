package ufhealth.integratedmachine.client.ui.fourth.activity.presenter;

import ufhealth.integratedmachine.client.bean.BaseReturnData;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_Presenter;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_LocalCallBack;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_EntranceOfModel;
import ufhealth.integratedmachine.client.bean.fourth.BjczHistroyPageInfo;
import ufhealth.integratedmachine.client.ui.fourth.activity.model.BjczHistroyModel;
import ufhealth.integratedmachine.client.ui.fourth.activity.view_v.BjczHistroyAct_V;

public class BjczHistroyPresenter extends BaseMvp_Presenter<BjczHistroyAct_V>
{
    private int currentPageOfIndex;

    public BjczHistroyPresenter()
    {
        currentPageOfIndex = 1;

    }

    public void refreshDatas()
    {
        if(isAttachContextAndViewLayer())
        {
            currentPageOfIndex = 1;
            BaseMvp_EntranceOfModel.requestDatas(BjczHistroyModel.class).
            putForm("page",currentPageOfIndex + "").convertForms().executeOfNet(getContext(),new BaseMvp_LocalCallBack<BaseReturnData<BjczHistroyPageInfo>>(this)
            {
                public void onSuccess(BaseReturnData<BjczHistroyPageInfo> bjczHistroyPageInfo)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        currentPageOfIndex++;
                        getViewLayer().finishRefresh();
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
            putForm("page",currentPageOfIndex + "").convertForms().executeOfNet(getContext(),new BaseMvp_LocalCallBack<BaseReturnData<BjczHistroyPageInfo>>(this)
            {
                public void onSuccess(BaseReturnData<BjczHistroyPageInfo> bjczHistroyPageInfo)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        currentPageOfIndex++;
                        getViewLayer().finishLoadMore();
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
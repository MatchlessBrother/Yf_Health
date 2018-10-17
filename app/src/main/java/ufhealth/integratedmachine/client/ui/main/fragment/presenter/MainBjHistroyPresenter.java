package ufhealth.integratedmachine.client.ui.main.fragment.presenter;

import ufhealth.integratedmachine.client.bean.BaseReturnData;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_Presenter;
import ufhealth.integratedmachine.client.bean.third.BjHistroyPageInfo;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_LocalCallBack;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_EntranceOfModel;
import ufhealth.integratedmachine.client.ui.main.fragment.model.MainBjHistroyModel;
import ufhealth.integratedmachine.client.ui.main.fragment.view_v.MainBjHistroyFrag_V;

public class MainBjHistroyPresenter extends BaseMvp_Presenter<MainBjHistroyFrag_V>
{
    private int currentPageOfIndex;

    public MainBjHistroyPresenter()
    {
        currentPageOfIndex = 1;

    }

    public void refreshDatas()
    {
        if(isAttachContextAndViewLayer())
        {
            currentPageOfIndex = 1;
            BaseMvp_EntranceOfModel.requestDatas(MainBjHistroyModel.class).
            putForm("page",currentPageOfIndex + "").convertForms().executeOfNet(getContext(),new BaseMvp_LocalCallBack<BaseReturnData<BjHistroyPageInfo>>(this)
            {
                public void onSuccess(BaseReturnData<BjHistroyPageInfo> bjHistroyPageInfo)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        currentPageOfIndex++;
                        getViewLayer().finishRefresh();
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

    public void loadMoreDatas()
    {
        if(isAttachContextAndViewLayer())
        {
            BaseMvp_EntranceOfModel.requestDatas(MainBjHistroyModel.class).
            putForm("page",currentPageOfIndex + "").convertForms().executeOfNet(getContext(),new BaseMvp_LocalCallBack<BaseReturnData<BjHistroyPageInfo>>(this)
            {
                public void onSuccess(BaseReturnData<BjHistroyPageInfo> bjHistroyPageInfo)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        currentPageOfIndex++;
                        getViewLayer().finishLoadMore();
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
}
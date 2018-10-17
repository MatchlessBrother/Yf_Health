package ufhealth.integratedmachine.client.ui.main.fragment.presenter;

import ufhealth.integratedmachine.client.bean.BaseReturnData;
import ufhealth.integratedmachine.client.bean.fourth.BjczPageInfo;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_Presenter;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_LocalCallBack;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_EntranceOfModel;
import ufhealth.integratedmachine.client.ui.main.fragment.model.MainBjModel;
import ufhealth.integratedmachine.client.ui.main.fragment.view_v.MainBjFrag_V;

public class MainBjPresenter extends BaseMvp_Presenter<MainBjFrag_V>
{
    private int currentPageOfIndex;

    public MainBjPresenter()
    {
        currentPageOfIndex = 1;

    }

    public void refreshDatas()
    {
        if(isAttachContextAndViewLayer())
        {
            currentPageOfIndex = 1;
            BaseMvp_EntranceOfModel.requestDatas(MainBjModel.class).
            putForm("page",currentPageOfIndex + "").convertForms().executeOfNet(getContext(),new BaseMvp_LocalCallBack<BaseReturnData<BjczPageInfo>>(this)
            {
                public void onSuccess(BaseReturnData<BjczPageInfo> bjczPageInfo)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        currentPageOfIndex++;
                        getViewLayer().finishRefresh();
                        getViewLayer().refreshDatas(bjczPageInfo.getData());
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
            BaseMvp_EntranceOfModel.requestDatas(MainBjModel.class).
            putForm("page",currentPageOfIndex + "").convertForms().executeOfNet(getContext(),new BaseMvp_LocalCallBack<BaseReturnData<BjczPageInfo>>(this)
            {
                public void onSuccess(BaseReturnData<BjczPageInfo> bjczPageInfo)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        currentPageOfIndex++;
                        getViewLayer().finishLoadMore();
                        getViewLayer().loadMoreDatas(bjczPageInfo.getData());
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
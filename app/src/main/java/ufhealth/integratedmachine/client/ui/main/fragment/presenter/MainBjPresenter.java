package ufhealth.integratedmachine.client.ui.main.fragment.presenter;

import ufhealth.integratedmachine.client.bean.BaseReturnData;
import ufhealth.integratedmachine.client.bean.bjcz.BjczPageInfo;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_Presenter;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_LocalObjCallBack;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_EntranceOfModel;
import ufhealth.integratedmachine.client.ui.main.fragment.model.MainBjModel;
import ufhealth.integratedmachine.client.ui.main.fragment.view_v.MainBjFrag_V;

public class MainBjPresenter extends BaseMvp_Presenter<MainBjFrag_V>
{
    private int currentPageOfIndex;
    private int currentPageOfMaxSize;

    public MainBjPresenter()
    {
        currentPageOfIndex = 0;
        currentPageOfMaxSize = 20;
    }

    public void refreshDatas()
    {
        if(isAttachContextAndViewLayer())
        {
            currentPageOfIndex = 0;
            BaseMvp_EntranceOfModel.requestDatas(MainBjModel.class).
            putForm("pageIndex",currentPageOfIndex + "").putForm("pageSize",currentPageOfMaxSize + "").putForm("handleStatus","1").convertForms().executeOfNet(getContext(),MainBjModel.RequestAlarmDatas,new BaseMvp_LocalObjCallBack<BaseReturnData<BjczPageInfo>>(this)
            {
                public void onSuccess(BaseReturnData<BjczPageInfo> bjczPageInfo)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().finishRefresh();
                        currentPageOfIndex = currentPageOfMaxSize;
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
            putForm("pageIndex",currentPageOfIndex + "").putForm("pageSize",currentPageOfMaxSize + "").putForm("handleStatus","1").convertForms().executeOfNet(getContext(),MainBjModel.RequestAlarmDatas,new BaseMvp_LocalObjCallBack<BaseReturnData<BjczPageInfo>>(this)
            {
                public void onSuccess(BaseReturnData<BjczPageInfo> bjczPageInfo)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().finishLoadMore();
                        currentPageOfIndex += currentPageOfMaxSize;
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
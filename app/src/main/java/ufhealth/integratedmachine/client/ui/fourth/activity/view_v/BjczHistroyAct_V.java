package ufhealth.integratedmachine.client.ui.fourth.activity.view_v;

import ufhealth.integratedmachine.client.ui.base.BaseMvp_View;
import ufhealth.integratedmachine.client.bean.fourth.BjczHistroyPageInfo;

public interface BjczHistroyAct_V extends BaseMvp_View
{
    void finishRefresh();
    void finishLoadMore();
    void refreshDatas(BjczHistroyPageInfo bjczHistroyPageInfo);
    void loadMoreDatas(BjczHistroyPageInfo bjczHistroyPageInfo);
}
package ufhealth.integratedmachine.client.ui.bjcz.activity.view_v;

import ufhealth.integratedmachine.client.ui.base.BaseMvp_View;
import ufhealth.integratedmachine.client.bean.bjcz.BjczHistroyPageInfo;

public interface BjczHistroyAct_V extends BaseMvp_View
{
    void finishRefresh();
    void finishLoadMore();
    void refreshDatas(BjczHistroyPageInfo bjczHistroyPageInfo);
    void loadMoreDatas(BjczHistroyPageInfo bjczHistroyPageInfo);
}
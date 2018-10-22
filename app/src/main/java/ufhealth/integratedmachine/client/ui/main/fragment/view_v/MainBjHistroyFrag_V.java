package ufhealth.integratedmachine.client.ui.main.fragment.view_v;

import ufhealth.integratedmachine.client.ui.base.BaseMvp_View;
import ufhealth.integratedmachine.client.bean.lsbj.BjHistroyPageInfo;

public interface MainBjHistroyFrag_V extends BaseMvp_View
{
    void finishRefresh();
    void finishLoadMore();
    void refreshDatas(BjHistroyPageInfo bjHistroyPageInfo);
    void loadMoreDatas(BjHistroyPageInfo bjHistroyPageInfo);
}
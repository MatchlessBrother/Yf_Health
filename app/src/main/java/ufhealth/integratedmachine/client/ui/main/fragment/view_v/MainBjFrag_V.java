package ufhealth.integratedmachine.client.ui.main.fragment.view_v;

import ufhealth.integratedmachine.client.ui.base.BaseMvp_View;
import ufhealth.integratedmachine.client.bean.bjcz.BjczPageInfo;

public interface MainBjFrag_V extends BaseMvp_View
{
    void finishRefresh();
    void finishLoadMore();
    void refreshDatas(BjczPageInfo bjczPageInfo);
    void loadMoreDatas(BjczPageInfo bjczPageInfo);
}
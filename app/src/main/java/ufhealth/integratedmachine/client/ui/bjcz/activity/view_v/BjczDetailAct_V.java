package ufhealth.integratedmachine.client.ui.bjcz.activity.view_v;

import ufhealth.integratedmachine.client.ui.base.BaseMvp_View;
import ufhealth.integratedmachine.client.bean.bjcz.BjczDetailInfo;

public interface BjczDetailAct_V extends BaseMvp_View
{
    public void failOfGetDatas();
    public void successOfGetDatas(BjczDetailInfo bjczDetailInfo);
}
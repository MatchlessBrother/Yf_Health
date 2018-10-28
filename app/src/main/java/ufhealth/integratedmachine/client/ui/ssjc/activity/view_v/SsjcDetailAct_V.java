package ufhealth.integratedmachine.client.ui.ssjc.activity.view_v;

import ufhealth.integratedmachine.client.ui.base.BaseMvp_View;
import ufhealth.integratedmachine.client.bean.ssjc.JcDetailInfo;

public interface SsjcDetailAct_V extends BaseMvp_View
{
    public void failOfGetDatas();
    public void successOfGetDatas(JcDetailInfo jcDetailInfo);
}

package ufhealth.integratedmachine.client.ui.main.fragment.view_v;

import ufhealth.integratedmachine.client.bean.hztj.TjDataInfo;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_View;
import ufhealth.integratedmachine.client.bean.hztj.TjCondition;

public interface MainHzFrag_V extends BaseMvp_View
{
    void getFailureOfDatas();
    void getSuccessOfDatas(TjDataInfo tjDataInfo);
    void getSuccessOfConditions(TjCondition tjCondition,boolean isNeedDrawableLayout);
}
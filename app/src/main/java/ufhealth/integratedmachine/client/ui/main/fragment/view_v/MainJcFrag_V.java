package ufhealth.integratedmachine.client.ui.main.fragment.view_v;

import ufhealth.integratedmachine.client.bean.ssjc.JcDataInfo;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_View;
import ufhealth.integratedmachine.client.bean.ssjc.JcCondition;

public interface MainJcFrag_V extends BaseMvp_View
{
    void getFailOfDataInfos();
    void getSuccessOfDataInfos(JcDataInfo jcDataInfo);
    void getSuccessOfCondition(JcCondition jcCondition,boolean isNeedDrawableLayout);
}
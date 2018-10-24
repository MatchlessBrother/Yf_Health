package ufhealth.integratedmachine.client.ui.main.fragment.view_v;

import ufhealth.integratedmachine.client.bean.lsbj.BjHistroyCondition;
import ufhealth.integratedmachine.client.bean.lsbj.BjHistroyPageInfo;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_View;

public interface MainHzFrag_V extends BaseMvp_View
{
    void getSuccessOfData();
    void getSuccessOfCondition();
}
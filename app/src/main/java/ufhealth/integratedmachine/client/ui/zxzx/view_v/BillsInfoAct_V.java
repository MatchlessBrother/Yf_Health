package ufhealth.integratedmachine.client.ui.zxzx.view_v;

import ufhealth.integratedmachine.client.bean.zxzx.Billinfo;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_View;

public interface BillsInfoAct_V extends BaseMvp_View
{
    void startImageTextPayActivity(Billinfo billinfo);
}
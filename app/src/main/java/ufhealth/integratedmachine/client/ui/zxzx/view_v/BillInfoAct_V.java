package ufhealth.integratedmachine.client.ui.zxzx.view_v;

import ufhealth.integratedmachine.client.bean.zxzx.Billinfo;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_View;
import ufhealth.integratedmachine.client.bean.zxzx.DoctorAllInfo;

public interface BillInfoAct_V extends BaseMvp_View
{
    void createFressBillSuccess();
    void setDoctorBaseInfo(DoctorAllInfo.BaseinfoBean baseinfoBean);
    void startAudioPayActivity(Billinfo billinfo);
    void startVideoPayActivity(Billinfo billinfo);
    void startImageTextPayActivity(Billinfo billinfo);
}
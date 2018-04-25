package ufhealth.integratedmachine.client.ui.zxzx.view_v;

import java.util.List;

import ufhealth.integratedmachine.client.bean.zxzx.DoctorInfo;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_View;

public interface ChooseDoctorAct_V extends BaseMvp_View
{
    void setAdapterDatas(List<DoctorInfo.ContentBean> doctorsInfo);
}
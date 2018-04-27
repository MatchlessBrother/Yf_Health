package ufhealth.integratedmachine.client.ui.zxzx.view_v;

import ufhealth.integratedmachine.client.ui.base.BaseMvp_View;
import ufhealth.integratedmachine.client.bean.zxzx.DoctorAllInfo;

public interface DoctorInfoAct_V extends BaseMvp_View
{
    void setDoctorBaseInfo(DoctorAllInfo.BaseinfoBean baseinfoBean);

    void finishRefreshDoctorRatingInfo();
    void finishLoadMoreDoctorRatingInfo();

    void refreshDoctorRatingInfo(DoctorAllInfo.PageBean pageBean);
    void loadMoreDoctorRatingInfo(DoctorAllInfo.PageBean pageBean);
}
package ufhealth.integratedmachine.client.ui.zxzx.view_v;

import ufhealth.integratedmachine.client.bean.zxzx.DoctorInfo;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_View;

public interface ChooseMultiDoctor_V extends BaseMvp_View
{
    void refreshDatas(DoctorInfo doctorsInfo);
    void finishRefresh();
    void loadMoreDatas(DoctorInfo doctorsInfo);
    void finishLoadMore();
}
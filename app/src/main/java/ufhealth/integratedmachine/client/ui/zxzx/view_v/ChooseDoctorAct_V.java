package ufhealth.integratedmachine.client.ui.zxzx.view_v;

import java.util.List;
import ufhealth.integratedmachine.client.bean.zxzx.DoctorInfo;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_View;
import ufhealth.integratedmachine.client.bean.zxzx.DoctorInfoOfCondition;

public interface ChooseDoctorAct_V extends BaseMvp_View
{
    void refreshDatas(DoctorInfo doctorsInfo);
    void finishRefresh();
    void loadMoreDatas(DoctorInfo doctorsInfo);
    void finishLoadMore();
    void setSortOptions(List<DoctorInfoOfCondition.SortBean> sortConditions);
    void setSourceOptions(List<DoctorInfoOfCondition.OriginalBean> sourceConditions);
    void setHospitalOptions(List<DoctorInfoOfCondition.HospitalBean> hospitalConditions);
    void setDepartmentOptions(List<DoctorInfoOfCondition.DepartmentBean> departmentConditions);
}
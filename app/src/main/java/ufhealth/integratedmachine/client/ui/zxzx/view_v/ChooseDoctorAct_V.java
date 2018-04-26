package ufhealth.integratedmachine.client.ui.zxzx.view_v;

import java.util.List;
import ufhealth.integratedmachine.client.bean.zxzx.DoctorInfo;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_View;
import ufhealth.integratedmachine.client.bean.zxzx.DoctorInfoOfCondition;

public interface ChooseDoctorAct_V extends BaseMvp_View
{
    void initAdapterDatas(List<DoctorInfo.ContentBean> doctorsInfo);
    void showSortOptions(List<DoctorInfoOfCondition.SortBean> sortConditions);
    void showSourceOptions(List<DoctorInfoOfCondition.OriginalBean> sourceConditions);
    void showHospitalOptions(List<DoctorInfoOfCondition.HospitalBean> hospitalConditions);
    void showDepartmentOptions(List<DoctorInfoOfCondition.DepartmentBean> departmentConditions);
}
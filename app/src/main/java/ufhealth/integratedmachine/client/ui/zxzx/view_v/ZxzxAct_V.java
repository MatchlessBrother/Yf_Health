package ufhealth.integratedmachine.client.ui.zxzx.view_v;

import java.util.List;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_View;
import ufhealth.integratedmachine.client.bean.zxzx.HotDepartment;

public interface ZxzxAct_V extends BaseMvp_View
{
    void setHotDepartmentsException();
    void setHotDepartments(List<HotDepartment> hotDepartments);
}
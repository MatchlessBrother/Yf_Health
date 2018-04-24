package ufhealth.integratedmachine.client.ui.zxzx.presenter;

import java.util.List;
import ufhealth.integratedmachine.client.bean.zxzx.HotDepartment;
import ufhealth.integratedmachine.client.ui.zxzx.view_v.ZxzxAct_V;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_Presenter;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_LocalCallBack;
import ufhealth.integratedmachine.client.ui.zxzx.model.ZxzxHotDepartmentModel;

public class ZxzxPresenter extends BaseMvp_Presenter<ZxzxAct_V>
{
    public ZxzxPresenter()
    {

    }

    public void getHotDepartments()
    {
        if(isAttachContextAndViewLayer())
        {
            ZxzxHotDepartmentModel.getHotDepartments(getContext(),new BaseMvp_LocalCallBack<List<HotDepartment>>(this)
            {
                public void onSuccess(List<HotDepartment> data)
                {
                    if(isAttachContextAndViewLayer())
                        getViewLayer().setHotDepartments(data);
                }

                public void onFailure(String msg)
                {
                    super.onFailure(msg);
                    if(isAttachContextAndViewLayer())
                        getViewLayer().setHotDepartmentsException();
                }

                public void onError(String msg)
                {
                    super.onError(msg);
                    if(isAttachContextAndViewLayer())
                        getViewLayer().setHotDepartmentsException();
                }
            });
        }
    }
}
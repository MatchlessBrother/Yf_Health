package ufhealth.integratedmachine.client.ui.zxzx.presenter;

import java.util.List;
import ufhealth.integratedmachine.client.bean.BaseReturnData;
import ufhealth.integratedmachine.client.ui.zxzx.model.ZxzxModel;
import ufhealth.integratedmachine.client.bean.zxzx.HotDepartment;
import ufhealth.integratedmachine.client.ui.zxzx.view_v.ZxzxAct_V;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_Presenter;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_LocalCallBack;

public class ZxzxPresenter extends BaseMvp_Presenter<ZxzxAct_V>
{
    public void getHotDepartments()
    {
        if(isAttachContextAndViewLayer())
        {
            ZxzxModel.getHotDepartments(getContext(),new BaseMvp_LocalCallBack<BaseReturnData<List<HotDepartment>>>(this)
            {
                public void onSuccess(BaseReturnData<List<HotDepartment>> data)
                {
                    if(isAttachContextAndViewLayer())
                        getViewLayer().setHotDepartments(data.getData());
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
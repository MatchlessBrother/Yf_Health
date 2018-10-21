package ufhealth.integratedmachine.client.ui.main.activity.presenter;

import ufhealth.integratedmachine.client.bean.BaseReturnData;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_Presenter;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_LocalCallBack;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_EntranceOfModel;
import ufhealth.integratedmachine.client.ui.main.activity.model.ModifyPasswordModel;
import ufhealth.integratedmachine.client.ui.main.activity.view_v.ModifyPasswordAct_V;

public class ModifyPasswordPresenter extends BaseMvp_Presenter<ModifyPasswordAct_V>
{
    public void modifyPassword(String oldPassword,String newPassword)
    {
        if(isAttachContextAndViewLayer())
        {
            BaseMvp_EntranceOfModel.requestDatas(ModifyPasswordModel.class).
            putForm("oldPassword",oldPassword).putForm("newPassword",newPassword).convertForms().executeOfNet(getContext(),new BaseMvp_LocalCallBack<BaseReturnData>(this)
            {
                public void onSuccess(BaseReturnData baseReturnData)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().successOfModifyPassword();
                    }
                }
            });
        }
    }
}
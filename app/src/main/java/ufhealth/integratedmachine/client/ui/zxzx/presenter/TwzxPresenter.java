package ufhealth.integratedmachine.client.ui.zxzx.presenter;

import java.util.Map;
import ufhealth.integratedmachine.client.bean.BaseReturnData;
import ufhealth.integratedmachine.client.ui.zxzx.model.TwzxModel;
import ufhealth.integratedmachine.client.ui.zxzx.view_v.TwzxAct_V;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_Presenter;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_LocalCallBack;

public class TwzxPresenter extends BaseMvp_Presenter<TwzxAct_V>
{
    public void uploadDatas(Map<String,String> datas,String[] imagesPath)
    {
        if(isAttachContextAndViewLayer())
        {
            TwzxModel.uploadDatas(getContext(),datas,imagesPath,new BaseMvp_LocalCallBack<BaseReturnData>(this)
            {
                public void onSuccess(BaseReturnData data)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().commitSuccess();
                        getViewLayer().showToast("提交成功");
                    }
                }
            });
        }
    }
}
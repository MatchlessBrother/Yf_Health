package ufhealth.integratedmachine.client.ui.bjcz.activity.presenter;

import java.util.List;
import ufhealth.integratedmachine.client.bean.BaseReturnData;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_Presenter;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_LocalCallBack;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_EntranceOfModel;
import ufhealth.integratedmachine.client.ui.bjcz.activity.model.BjczModel;
import ufhealth.integratedmachine.client.ui.bjcz.activity.view_v.BjczAct_V;

public class BjczPresenter extends BaseMvp_Presenter<BjczAct_V>
{
    public void disposeAlarm(String disposeAlarmOfDiscription,List<String> disposeAlarmOfImgs)
    {
        if(isAttachContextAndViewLayer())
        {
            BaseMvp_EntranceOfModel.requestDatas(BjczModel.class).
            putForm("content",disposeAlarmOfDiscription).putFilesPath(disposeAlarmOfImgs).convertForms().executeOfNet(getContext(),BjczModel.UploadData,new BaseMvp_LocalCallBack<BaseReturnData>(this)
            {
                public void onSuccess(BaseReturnData baseReturnData)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().successOfUploadDatas();
                    }
                }

                public void onFailure(String msg)
                {
                    super.onFailure(msg);
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().failOfUploadDatas();
                    }
                }

                public void onError(String msg)
                {
                    super.onError(msg);
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().failOfUploadDatas();
                    }
                }
            });
        }
    }
}
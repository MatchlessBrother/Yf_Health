package ufhealth.integratedmachine.client.ui.bjcz.activity.presenter;

import java.util.Map;
import java.util.List;
import java.util.Arrays;
import ufhealth.integratedmachine.client.bean.BaseReturnData;
import ufhealth.integratedmachine.client.bean.BaseReturnListData;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_Presenter;
import ufhealth.integratedmachine.client.bean.bjcz.BjczUploadImgInfo;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_EntranceOfModel;
import ufhealth.integratedmachine.client.ui.bjcz.activity.model.BjczModel;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_LocalObjCallBack;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_LocalListCallBack;
import ufhealth.integratedmachine.client.ui.bjcz.activity.view_v.BjczAct_V;

public class BjczPresenter extends BaseMvp_Presenter<BjczAct_V>
{
    public void disposeAlarmImage(List<String> disposeAlarmOfImgs)
    {
        if(isAttachContextAndViewLayer())
        {
            BaseMvp_EntranceOfModel.requestDatas(BjczModel.class).
            putFilesPath(disposeAlarmOfImgs).convertFiles().executeOfNet(getContext(),BjczModel.UploadImage,new BaseMvp_LocalListCallBack<BaseReturnListData<BjczUploadImgInfo>>(this)
            {
                public void onSuccess(BaseReturnListData<BjczUploadImgInfo> bjczUploadImgInfo)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().successOfUploadImgDatas(Arrays.asList(bjczUploadImgInfo.getData()));
                    }
                }

                public void onFailure(String msg)
                {
                    super.onFailure(msg);
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().failOfUploadImgDatas();
                    }
                }

                public void onError(String msg)
                {
                    super.onError(msg);
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().failOfUploadImgDatas();
                    }
                }
            });
        }
    }

    public void disposeAlarm(String alarmId,String alarmOfDiscription,Map<String,String> alarmOfImagePaths)
    {
        if(isAttachContextAndViewLayer())
        {
            BaseMvp_EntranceOfModel.requestDatas(BjczModel.class).
            putAllowSameKeyForm("id",alarmId).putAllowSameKeyForm("description",alarmOfDiscription).putAllowSameKeyForms(alarmOfImagePaths).convertAllowSameKeyForms().executeOfNet(getContext(),BjczModel.UploadData,new BaseMvp_LocalObjCallBack<BaseReturnData>(this)
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
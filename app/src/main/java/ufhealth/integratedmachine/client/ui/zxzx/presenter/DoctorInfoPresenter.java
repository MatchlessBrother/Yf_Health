package ufhealth.integratedmachine.client.ui.zxzx.presenter;

import java.util.Map;
import java.util.HashMap;
import ufhealth.integratedmachine.client.bean.BaseReturnData;
import ufhealth.integratedmachine.client.bean.zxzx.DoctorAllInfo;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_Presenter;
import ufhealth.integratedmachine.client.ui.zxzx.model.DoctorModel;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_LocalCallBack;
import ufhealth.integratedmachine.client.ui.zxzx.view_v.DoctorInfoAct_V;

public class DoctorInfoPresenter extends BaseMvp_Presenter<DoctorInfoAct_V>
{
    private DoctorAllInfo doctorAllInfo;
    private Map<String,String> conditions;

    public DoctorInfoPresenter()
    {
        conditions = new HashMap<>();
    }

    public boolean doctorIsValid()
    {
        if(isAttachContextAndViewLayer())
        {
            if(null != doctorAllInfo && null != doctorAllInfo.getBaseinfo() && null != doctorAllInfo.getBaseinfo().getDoctor_name())
                return true;
            else
                return false;
        }
        else
            return false;
    }

    public void initDoctorAllInfo(String doctorId)
    {
        if(isAttachContextAndViewLayer())
        {
            conditions.clear();
            conditions.put("is_baseInfo","3");
            conditions.put("doctor_id",doctorId);
            conditions.put("page","1");
            DoctorModel.getDoctorAllInfo(getContext(),conditions,new BaseMvp_LocalCallBack<BaseReturnData<DoctorAllInfo>>(this)
            {
                public void onSuccess(BaseReturnData<DoctorAllInfo> data)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        doctorAllInfo = data.getData();
                        getViewLayer().setDoctorBaseInfo(doctorAllInfo.getBaseinfo());
                        getViewLayer().refreshDoctorRatingInfo(doctorAllInfo.getPage());
                        conditions.put("page", String.valueOf(Integer.valueOf(conditions.get("page").trim()) + 1));
                    }
                }

                public void onFailure(String msg)
                {
                    super.onFailure(msg);
                    if(isAttachContextAndViewLayer())
                    {

                    }
                }

                public void onError(String msg)
                {
                    super.onError(msg);
                    if(isAttachContextAndViewLayer())
                    {

                    }
                }
            });
        }
    }

    public void refreshDoctorRatingInfo(String doctorId)
    {
        if(isAttachContextAndViewLayer())
        {
            conditions.clear();
            conditions.put("page","1");
            conditions.put("doctor_id",doctorId);
            conditions.put("is_baseInfo","2");
            DoctorModel.getDoctorAllInfo(getContext(),conditions,new BaseMvp_LocalCallBack<BaseReturnData<DoctorAllInfo>>(this)
            {
                public void onSuccess(BaseReturnData<DoctorAllInfo> data)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        doctorAllInfo = data.getData();
                        getViewLayer().refreshDoctorRatingInfo(doctorAllInfo.getPage());
                        getViewLayer().finishRefreshDoctorRatingInfo();
                        conditions.put("page", String.valueOf(Integer.valueOf(conditions.get("page").trim()) + 1));
                    }
                }

                public void onFailure(String msg)
                {
                    super.onFailure(msg);
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().finishRefreshDoctorRatingInfo();
                    }
                }

                public void onError(String msg)
                {
                    super.onError(msg);
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().finishRefreshDoctorRatingInfo();
                    }
                }
            });
        }
    }

    public void loadMoreDoctorRatingInfo(String doctorId)
    {
        if(isAttachContextAndViewLayer())
        {
            conditions.put("is_baseInfo","2");
            conditions.put("doctor_id",doctorId);
            DoctorModel.getDoctorAllInfo(getContext(),conditions,new BaseMvp_LocalCallBack<BaseReturnData<DoctorAllInfo>>(this)
            {
                public void onSuccess(BaseReturnData<DoctorAllInfo> data)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        doctorAllInfo = data.getData();
                        getViewLayer().loadMoreDoctorRatingInfo(doctorAllInfo.getPage());
                        getViewLayer().finishLoadMoreDoctorRatingInfo();
                        conditions.put("page", String.valueOf(Integer.valueOf(conditions.get("page").trim()) + 1));
                    }
                }

                public void onFailure(String msg)
                {
                    super.onFailure(msg);
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().finishLoadMoreDoctorRatingInfo();
                    }
                }

                public void onError(String msg)
                {
                    super.onError(msg);
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().finishLoadMoreDoctorRatingInfo();
                    }
                }
            });
        }
    }
}
package ufhealth.integratedmachine.client.ui.zxzx.presenter;

import java.util.Map;
import java.util.HashMap;
import ufhealth.integratedmachine.client.bean.BaseReturnData;
import ufhealth.integratedmachine.client.bean.zxzx.DoctorInfo;
import ufhealth.integratedmachine.client.ui.zxzx.model.DoctorModel;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_Presenter;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_LocalCallBack;
import ufhealth.integratedmachine.client.ui.zxzx.view_v.ChooseMultiDoctor_V;

public class ChooseMultiDoctorPresenter extends BaseMvp_Presenter<ChooseMultiDoctor_V>
{
    private DoctorInfo doctorInfo;
    private Map<String,String> conditions;

    public ChooseMultiDoctorPresenter()
    {
        doctorInfo = new DoctorInfo();
        conditions = new HashMap<>();
    }

    public void refreshDatas()
    {
        if(isAttachContextAndViewLayer())
        {
            conditions.clear();
            conditions.put("page","1");
            DoctorModel.getDoctorsInfo(getContext(),conditions,new BaseMvp_LocalCallBack<BaseReturnData<DoctorInfo>>(this)
            {
                public void onSuccess(BaseReturnData<DoctorInfo> data)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        doctorInfo = data.getData();
                        getViewLayer().refreshDatas(doctorInfo);
                        getViewLayer().finishRefresh();
                        conditions.put("page", String.valueOf(Integer.valueOf(conditions.get("page").trim()) + 1));
                    }
                }

                public void onFailure(String msg)
                {
                    super.onFailure(msg);
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().finishRefresh();
                    }
                }

                public void onError(String msg)
                {
                    super.onError(msg);
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().finishRefresh();
                    }
                }
            });
        }
    }

    public void loadMoreDatas()
    {
        if(isAttachContextAndViewLayer())
        {
            DoctorModel.getDoctorsInfo(getContext(),conditions,new BaseMvp_LocalCallBack<BaseReturnData<DoctorInfo>>(this)
            {
                public void onSuccess(BaseReturnData<DoctorInfo> data)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        doctorInfo = data.getData();
                        getViewLayer().loadMoreDatas(doctorInfo);
                        getViewLayer().finishLoadMore();
                        conditions.put("page", String.valueOf(Integer.valueOf(conditions.get("page").trim()) + 1));
                    }
                }

                public void onFailure(String msg)
                {
                    super.onFailure(msg);
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().finishLoadMore();
                    }
                }

                public void onError(String msg)
                {
                    super.onError(msg);
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().finishLoadMore();
                    }
                }
            });
        }
    }
}
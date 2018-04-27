package ufhealth.integratedmachine.client.ui.zxzx.presenter;

import java.util.Map;
import java.util.HashMap;
import ufhealth.integratedmachine.client.bean.BaseReturnData;
import ufhealth.integratedmachine.client.bean.zxzx.DoctorAllInfo;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_Presenter;
import ufhealth.integratedmachine.client.ui.zxzx.model.DoctorModel;
import ufhealth.integratedmachine.client.ui.zxzx.view_v.BillInfoAct_V;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_LocalCallBack;

public class BillInfoPresenter extends BaseMvp_Presenter<BillInfoAct_V>
{
    private DoctorAllInfo doctorAllInfo;
    private Map<String,String> conditions;

    public BillInfoPresenter()
    {
        conditions = new HashMap<>();
    }

    public void initDoctorAllInfo(String doctorId)
    {
        if(isAttachContextAndViewLayer())
        {
            conditions.clear();
            conditions.put("is_baseInfo","1");
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
}
package ufhealth.integratedmachine.client.ui.zxzx.presenter;

import java.util.Map;
import java.util.HashMap;
import ufhealth.integratedmachine.client.bean.BaseReturnData;
import ufhealth.integratedmachine.client.bean.zxzx.DoctorInfo;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_Presenter;
import ufhealth.integratedmachine.client.ui.zxzx.model.DoctorModel;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_LocalCallBack;
import ufhealth.integratedmachine.client.bean.zxzx.DoctorInfoOfCondition;
import ufhealth.integratedmachine.client.ui.zxzx.view_v.ChooseDoctorAct_V;

public class ChooseDoctorPresenter extends BaseMvp_Presenter<ChooseDoctorAct_V>
{
    private DoctorInfo doctorInfo;
    private Map<String,String> conditions;
    private DoctorInfoOfCondition doctorInfoOfCondition;

    public ChooseDoctorPresenter()
    {
        doctorInfo = new DoctorInfo();
        conditions = new HashMap<>();
        doctorInfoOfCondition = new DoctorInfoOfCondition();
    }

    public void getDoctorInfoOfConditions()
    {
        if(isAttachContextAndViewLayer())
        {
            DoctorModel.getDoctorInfoOfConditions(getContext(),new BaseMvp_LocalCallBack<BaseReturnData<DoctorInfoOfCondition>>(this)
            {
                public void onSuccess(BaseReturnData<DoctorInfoOfCondition> data)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        doctorInfoOfCondition = data.getData();
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

    public void initConditions()
    {
        conditions.clear();
        conditions.put("page","1");
    }

    public void getDoctorInfo()
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
                        getViewLayer().setAdapterDatas(doctorInfo.getContent());
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
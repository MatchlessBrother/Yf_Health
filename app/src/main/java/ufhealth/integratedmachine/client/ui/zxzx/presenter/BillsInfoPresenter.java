package ufhealth.integratedmachine.client.ui.zxzx.presenter;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import ufhealth.integratedmachine.client.bean.BaseReturnData;
import ufhealth.integratedmachine.client.ui.zxzx.model.BillModel;
import ufhealth.integratedmachine.client.bean.zxzx.DoctorAllInfo;
import ufhealth.integratedmachine.client.ui.zxzx.model.DoctorModel;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_Presenter;
import ufhealth.integratedmachine.client.ui.zxzx.view_v.BillsInfoAct_V;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_LocalCallBack;

public class BillsInfoPresenter extends BaseMvp_Presenter<BillsInfoAct_V>
{
    private DoctorAllInfo doctorAllInfo;
    private Map<String,String> conditions;

    public BillsInfoPresenter()
    {
        conditions = new HashMap<>();
    }

    public void initDoctorsInfo(List<String> doctorsId)
    {
        if(isAttachContextAndViewLayer())
        {
            conditions.clear();
            conditions.put("is_baseInfo","1");
            conditions.put("page","1");
            for(String doctorId : doctorsId)
            {
                conditions.put("doctor_id",doctorId);
                DoctorModel.getDoctorAllInfo(getContext(),conditions,new BaseMvp_LocalCallBack<BaseReturnData<DoctorAllInfo>>(this)
                {
                    public void onSuccess(BaseReturnData<DoctorAllInfo> data)
                    {
                        if(isAttachContextAndViewLayer())
                        {
                            doctorAllInfo = data.getData();
                            getViewLayer().addDoctorBaseInfo(doctorAllInfo.getBaseinfo());
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

   /* public void createImageTextBill(List<String> doctorsId,List<String> picturesPath,String content)
    {
        if(isAttachContextAndViewLayer())
        {
            conditions.clear();
            conditions.put("doctor_id",doctorId);
            conditions.put("timeMin",minTime);
            BillModel.createVideoBill(getContext(),conditions,new BaseMvp_LocalCallBack<BaseReturnData<DoctorAllInfo>>(this)
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
                        getViewLayer().showToast("生成订单失败，请重新点击");
                    }
                }

                public void onError(String msg)
                {
                    super.onError(msg);
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().showToast("生成订单失败，请重新点击");
                    }
                }
            });
        }
    }*/

    private String[] SetToArray(List<String> list)
    {
        String[] array = new String[list.size()];
        for(int index = 0;index < list.size();index++)
            array[index] = list.get(index);
        return array;
    }
}
package ufhealth.integratedmachine.client.ui.zxzx.presenter;

import java.util.Map;
import java.util.HashMap;
import ufhealth.integratedmachine.client.bean.zxzx.Billinfo;
import ufhealth.integratedmachine.client.bean.BaseReturnData;
import ufhealth.integratedmachine.client.bean.zxzx.DoctorAllInfo;
import ufhealth.integratedmachine.client.ui.zxzx.model.BillModel;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_Presenter;
import ufhealth.integratedmachine.client.ui.zxzx.model.DoctorModel;
import ufhealth.integratedmachine.client.ui.zxzx.view_v.BillInfoAct_V;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_LocalCallBack;

public class BillInfoPresenter extends BaseMvp_Presenter<BillInfoAct_V>
{
    private DoctorAllInfo doctorAllInfo;

    public BillInfoPresenter()
    {
    }

    public void initDoctorAllInfo(String doctorId)
    {
        if(isAttachContextAndViewLayer())
        {
            Map conditions = new HashMap();
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

    public void createAudioBill(String doctorId,String minTime)
    {
        if(isAttachContextAndViewLayer())
        {
            BillModel.createAudioBill(getContext(),doctorId,minTime,new BaseMvp_LocalCallBack<BaseReturnData<Billinfo>>(this)
            {
                public void onSuccess(BaseReturnData<Billinfo> returnBillInfo)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        Billinfo billinfo = returnBillInfo.getData();
                        if(null != billinfo && null != billinfo.getPayOrderNumber() && null != billinfo.getPayQrcodeUrl() && null != billinfo.getPayOrderNumber() &&
                            !"".equals(billinfo.getPayQrcodeUrl().trim()) && !"".equals(billinfo.getPayOrderNumber().trim()) && !"".equals(billinfo.getTotalPrice().trim()))
                            getViewLayer().startAudioPayActivity(returnBillInfo.getData());
                        else
                            getViewLayer().showToast("服务器忙！请稍后重新发起咨询请求...谢谢！");
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

    public void createVideoBill(String doctorId,String minTime)
    {
        if(isAttachContextAndViewLayer())
        {
            BillModel.createVideoBill(getContext(),doctorId,minTime,new BaseMvp_LocalCallBack<BaseReturnData<Billinfo>>(this)
            {
                public void onSuccess(BaseReturnData<Billinfo> returnBillInfo)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        Billinfo billinfo = returnBillInfo.getData();
                        if(null != billinfo && null != billinfo.getPayOrderNumber() && null != billinfo.getPayQrcodeUrl() && null != billinfo.getPayOrderNumber() &&
                                !"".equals(billinfo.getPayQrcodeUrl().trim()) && !"".equals(billinfo.getPayOrderNumber().trim()) && !"".equals(billinfo.getTotalPrice().trim()))
                            getViewLayer().startVideoPayActivity(returnBillInfo.getData());
                        else
                            getViewLayer().showToast("服务器忙！请稍后重新发起咨询请求...谢谢！");
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

    public void createImageTextBill(String[] doctorsId,String[] imgsPath,String description)
    {
        if(isAttachContextAndViewLayer())
        {
            BillModel.createImageTextBill(getContext(),doctorsId,imgsPath,description,new BaseMvp_LocalCallBack<BaseReturnData<Billinfo>>(this)
            {
                public void onSuccess(BaseReturnData<Billinfo> returnBillInfo)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        Billinfo billinfo = returnBillInfo.getData();
                        if(null != billinfo && null != billinfo.getPayOrderNumber() && null != billinfo.getPayQrcodeUrl() && null != billinfo.getPayOrderNumber() &&
                                !"".equals(billinfo.getPayQrcodeUrl().trim()) && !"".equals(billinfo.getPayOrderNumber().trim()) && !"".equals(billinfo.getTotalPrice().trim()))
                            getViewLayer().startImageTextPayActivity(returnBillInfo.getData());
                        else
                            getViewLayer().showToast("服务器忙！请稍后重新发起咨询请求...谢谢！");
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
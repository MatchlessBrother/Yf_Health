package ufhealth.integratedmachine.client.ui.zxzx.presenter;

import java.util.List;

import ufhealth.integratedmachine.client.bean.BaseReturnData;
import ufhealth.integratedmachine.client.bean.zxzx.Billinfo;
import ufhealth.integratedmachine.client.bean.zxzx.DoctorInfo;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_LocalCallBack;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_Presenter;
import ufhealth.integratedmachine.client.ui.zxzx.model.BillModel;
import ufhealth.integratedmachine.client.ui.zxzx.view_v.BillsInfoAct_V;

public class BillsInfoPresenter extends BaseMvp_Presenter<BillsInfoAct_V>
{
    public BillsInfoPresenter()
    {

    }

    public void uploadImgs(String questions, boolean isIncludeFreeDoctor, List<String> imgsPath, List<DoctorInfo.ContentBean> doctors)
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
                        if(null != billinfo && null != billinfo.getPayOrderNumber() && null != billinfo.getPayQrcodeUrl() && null != billinfo.getTotalPrice() &&
                                !"".equals(billinfo.getPayOrderNumber().trim()) && !"".equals(billinfo.getPayQrcodeUrl().trim()) && !"".equals(billinfo.getTotalPrice().trim()))
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
                        if(null != billinfo && null != billinfo.getPayOrderNumber() && null != billinfo.getPayQrcodeUrl() && null != billinfo.getTotalPrice() &&
                                !"".equals(billinfo.getPayOrderNumber().trim()) && !"".equals(billinfo.getPayQrcodeUrl().trim()) && !"".equals(billinfo.getTotalPrice().trim()))
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

    private String[] SetToArray(List<String> list)
    {
        String[] array = new String[list.size()];
        for(int index = 0;index < list.size();index++)
            array[index] = list.get(index);
        return array;
    }
}
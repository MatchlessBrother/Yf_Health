package ufhealth.integratedmachine.client.ui.zxzx.presenter;

import java.util.List;
import java.util.ArrayList;
import android.content.Context;
import ufhealth.integratedmachine.client.bean.zxzx.Billinfo;
import ufhealth.integratedmachine.client.bean.BaseReturnData;
import ufhealth.integratedmachine.client.bean.zxzx.DoctorInfo;
import ufhealth.integratedmachine.client.ui.zxzx.model.ImgModel;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_Presenter;
import ufhealth.integratedmachine.client.bean.zxzx.UploadImgReturnInfo;
import ufhealth.integratedmachine.client.ui.zxzx.view_v.BillsInfoAct_V;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_LocalCallBack;

public class BillsInfoPresenter extends BaseMvp_Presenter<BillsInfoAct_V>
{
    public BillsInfoPresenter()
    {

    }

    public void uploadImgs(final Context context,final String description,final List<String> imgsPath,final List<DoctorInfo.ContentBean> doctors,final boolean isIncludeFreeDoctor)
    {
        if(isAttachContextAndViewLayer())
        {
            ImgModel.uploadImg(context,imgsPath,new BaseMvp_LocalCallBack<BaseReturnData<List<UploadImgReturnInfo>>>(this)
            {
                public void onSuccess(BaseReturnData<List<UploadImgReturnInfo>> returnBillInfo)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        List<UploadImgReturnInfo> uploadImgReturnInfo = returnBillInfo.getData();
                        List<String> imgsPathKey = new ArrayList<>();
                        for(UploadImgReturnInfo returnUploadInfo : uploadImgReturnInfo)
                        {
                            if(null != returnUploadInfo && null != returnUploadInfo.getKey() && !"".equals(returnUploadInfo.getKey().trim()))
                                imgsPathKey.add(returnUploadInfo.getKey().trim());
                        }

                        List<String> doctorsId = new ArrayList<>();
                        for(DoctorInfo.ContentBean doctor : doctors)
                        {
                            if(null != doctor && 0 != doctor.getDoctor_id())
                                doctorsId.add(doctor.getDoctor_id()+"");
                        }
                        createImageTextBill(context,description,setToArray(imgsPathKey),setToArray(doctorsId),isIncludeFreeDoctor);
                    }
                }

                public void onFailure(String msg)
                {
                    super.onFailure(msg);
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().showToast("亲，因为网络异常！请稍后再尝试重新生成咨询订单！");
                    }
                }

                public void onError(String msg)
                {
                    super.onError(msg);
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().showToast("亲，因为网络异常！请稍后再尝试重新生成咨询订单！");
                    }
                }
            });
        }
    }

    public void createImageTextBill(Context context,String description,String[] imgsPath,String[] doctorsId,boolean isIncludeFreeDoctor)
    {
        if(isAttachContextAndViewLayer())
        {
            ImgModel.createImageTextBill(context,doctorsId,imgsPath,description,new BaseMvp_LocalCallBack<BaseReturnData<Billinfo>>(this)
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

    public static String[] setToArray(List<String> list)
    {
        String[] array = new String[list.size()];
        for(int index = 0;index < list.size();index++)
            array[index] = list.get(index);
        return array;
    }
}
package ufhealth.integratedmachine.client.ui.zxzx.model;

import java.io.File;
import java.util.List;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.MultipartBody;
import android.content.Context;
import rx.schedulers.Schedulers;
import rx.android.schedulers.AndroidSchedulers;
import ufhealth.integratedmachine.client.network.NetClient;
import ufhealth.integratedmachine.client.bean.zxzx.Billinfo;
import ufhealth.integratedmachine.client.bean.BaseReturnData;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_NetCallBack;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_LocalCallBack;
import ufhealth.integratedmachine.client.bean.zxzx.UploadImgReturnInfo;

public class ImgModel
{
    public static void uploadImg(Context context, List<String> imgsPath, BaseMvp_LocalCallBack<BaseReturnData<List<UploadImgReturnInfo>>> netCallBack)
    {
        netCallBack.onStart();
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        for (int i = 0; i < imgsPath.size(); i++)
        {
            if(null != imgsPath && null != imgsPath.get(i) && !"".equals(imgsPath.get(i).trim()))
            {
                File file = new File(imgsPath.get(i));//imgsPath 图片地址
                RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                builder.addFormDataPart("files", file.getName(), imageBody);//"file"后台接收图片流的参数名
            }
        }
        List<MultipartBody.Part> imgsPart = builder.build().parts();
        NetClient.getInstance(context).getNetUrl().twzxUploadDatas(imgsPart).
                subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe(new BaseMvp_NetCallBack(context,netCallBack));
    }

    public static void createImageTextBill(Context context,String[] doctorsId,String[] imgsPath,String description,BaseMvp_LocalCallBack<BaseReturnData<Billinfo>> netCallBack)
    {
        netCallBack.onStart();
        NetClient.getInstance(context).getNetUrl().zxzxCreateImageTextBill(doctorsId,imgsPath,description).
                subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe(new BaseMvp_NetCallBack(context,netCallBack));
    }
}
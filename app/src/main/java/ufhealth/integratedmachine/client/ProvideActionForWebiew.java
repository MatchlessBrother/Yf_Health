package ufhealth.integratedmachine.client;

import java.util.List;
import android.util.Log;
import okhttp3.Interceptor;
import android.content.Intent;
import android.annotation.SuppressLint;
import com.netease.nimlib.sdk.NIMClient;
import android.webkit.JavascriptInterface;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.auth.AuthService;
import com.yuan.devlibrary._12_______Utils.NetTools;
import ufhealth.integratedmachine.client.base.BaseAct;
import ufhealth.integratedmachine.client.network.NetClient;
import ufhealth.integratedmachine.client.ui.zxzx.view.SpzxingAct;
import ufhealth.integratedmachine.client.ui.zxzx.view.TwzxingAct;
import ufhealth.integratedmachine.client.ui.zxzx.view.YyzxingAct;
import com.yuan.devlibrary._11___Widget.promptBox.BaseProgressDialog;
import com.yuan.devlibrary._9__Network.okhttp.Http3Interceptions.TokenInterceptor_UnPersistentStore;

@SuppressLint("SetJavaScriptEnabled")
public class ProvideActionForWebiew
{
    private BaseAct baseAct;
    private BaseProgressDialog progressDialog;

    public ProvideActionForWebiew(BaseAct baseAct)
    {
        this.baseAct = baseAct;
    }

    @JavascriptInterface
    public String getToken()
    {
        List<Interceptor> interceptorList = NetClient.getInstance(baseAct.getApplicationContext()).getOkHttpClient().interceptors();
        for(int index=0;index<interceptorList.size();index++)
        {
            if(interceptorList.get(index) instanceof TokenInterceptor_UnPersistentStore)
            {
                TokenInterceptor_UnPersistentStore interceptor = (TokenInterceptor_UnPersistentStore) interceptorList.get(index);
                return interceptor.getToken(NetClient.getInstance(baseAct.getApplicationContext()).getRetrofit().baseUrl().host().trim());
            }
        }
        return "";
    }

    @JavascriptInterface
    public void finishActivity()
    {
        baseAct.finish();
    }

    @JavascriptInterface
    public void startZxActWithDialog(final String type,final String doctorId,final String accId,final String syTime,final String orderId)
    {
        progressDialog = baseAct.showLoadingDialog();
        startZxAct(type,doctorId,accId,syTime,orderId);
    }

    @JavascriptInterface
    public void startZxAct(final String type,final String doctorId,final String accId,final String syTime,final String orderId)
    {
        if(baseAct.getBaseApp().getImIsLogined() && null != baseAct.getBaseApp().getIMLoginInfo())
        {
            baseAct.dismissLoadingDialog(progressDialog);
            Intent intent = null;
            switch(type)
            {
                case "SPZX":intent = new Intent(baseAct.getBaseContext(),SpzxingAct.class);break;
                case "YPZX":intent = new Intent(baseAct.getBaseContext(),YyzxingAct.class);break;
                case "TWZX":intent = new Intent(baseAct.getBaseContext(),TwzxingAct.class);break;
            }
            intent.putExtra("accid",accId);
            intent.putExtra("sytime",Long.valueOf(syTime));
            intent.putExtra("orderid",orderId);
            intent.putExtra("doctorid",doctorId);
            baseAct.startActivityForResult(intent,66);
        }
        else
        {
            if(null != baseAct.getBaseApp().getImUserInfo() &&
               null != baseAct.getBaseApp().getImUserInfo().getAccid() && !"".equals(baseAct.getBaseApp().getImUserInfo().getAccid().trim()) &&
               null != baseAct.getBaseApp().getImUserInfo().getToken() && !"".equals(baseAct.getBaseApp().getImUserInfo().getToken().trim()))
            {
                LoginInfo loginInfo = new LoginInfo(baseAct.getBaseApp().getImUserInfo().getAccid().trim(),baseAct.getBaseApp().getImUserInfo().getToken().trim());
                NIMClient.getService(AuthService.class).login(loginInfo).setCallback(new RequestCallback<LoginInfo>()
                {
                    public void onSuccess(LoginInfo loginInfo)
                    {
                        baseAct.dismissLoadingDialog(progressDialog);
                        baseAct.getBaseApp().setIMLoginInfo(loginInfo);
                        Intent intent = null;
                        switch(type)
                        {
                            case "SPZX":intent = new Intent(baseAct.getBaseContext(),SpzxingAct.class);break;
                            case "YPZX":intent = new Intent(baseAct.getBaseContext(),YyzxingAct.class);break;
                            case "TWZX":intent = new Intent(baseAct.getBaseContext(),TwzxingAct.class);break;
                        }
                        intent.putExtra("accid",accId);
                        intent.putExtra("sytime",Long.valueOf(syTime));
                        intent.putExtra("orderid",orderId);
                        intent.putExtra("doctorid",doctorId);
                        baseAct.startActivityForResult(intent,66);
                    }

                    public void onFailed(int code)
                    {
                        if(!NetTools.WhetherConnectNet(baseAct.getBaseContext()))
                        {
                            baseAct.showToast("网络发生异常！请确保网络正常后再发起咨询请求...");
                            baseAct.dismissLoadingDialog(progressDialog);
                            return;
                        }
                        else if(code  == 302)
                        {
                            baseAct.showToast("多媒体咨询账号密码发生错误！请联系管理员...");
                            baseAct.dismissLoadingDialog(progressDialog);
                            return;
                        }
                        else
                        {
                            startZxAct(type,doctorId,accId,syTime,orderId);
                        }
                    }

                    public void onException(Throwable exception)
                    {
                        baseAct.showToast("网络发生异常！请确保网络正常后再发起咨询请求...");
                        Log.e("ImException",exception.getMessage().toString());
                        baseAct.dismissLoadingDialog(progressDialog);
                    }
                });
            }
            else
            {
                baseAct.showToast("无法进行多媒体通讯，请重新登录后再试...");
            }
        }
    }
}
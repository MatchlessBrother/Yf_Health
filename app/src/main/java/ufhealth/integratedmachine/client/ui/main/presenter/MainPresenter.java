package ufhealth.integratedmachine.client.ui.main.presenter;

import java.util.List;
import okhttp3.Interceptor;
import android.content.Context;
import ufhealth.integratedmachine.client.network.NetClient;
import ufhealth.integratedmachine.client.bean.main.UserInfo;
import ufhealth.integratedmachine.client.bean.BaseReturnData;
import ufhealth.integratedmachine.client.bean.main.WifiMacAddress;
import ufhealth.integratedmachine.client.ui.main.model.LoginModel;
import ufhealth.integratedmachine.client.ui.main.view_v.MainAct_V;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_Presenter;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_LocalCallBack;
import com.yuan.devlibrary._9__Network.okhttp.Http3Interceptions.TokenInterceptor_UnPersistentStore;

public class MainPresenter extends BaseMvp_Presenter<MainAct_V>
{
    public MainPresenter()
    {

    }

    public void logging(final Context context, final String idCard,final String name,final String birthday,final Integer gender,final String nation,final String address,final byte[] avatarByte)
    {
        if(isAttachContextAndViewLayer())
        {
            LoginModel.login(context,idCard,name,birthday,gender,nation,address,avatarByte,new BaseMvp_LocalCallBack<BaseReturnData<UserInfo>>(this)
            {
                public void onSuccess(BaseReturnData<UserInfo> returnUserInfo)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        if(null == returnUserInfo.getData().getUserInfo().getMobilePhone() || returnUserInfo.getData().getUserInfo().getMobilePhone().equals(""))
                            getViewLayer().showBindIdCardDialog(name,idCard);
                        List<Interceptor> interceptorList = NetClient.getInstance(context.getApplicationContext()).getOkHttpClient().interceptors();
                        for(int index=0;index<interceptorList.size();index++)
                        {
                            if(interceptorList.get(index) instanceof TokenInterceptor_UnPersistentStore)
                            {
                                TokenInterceptor_UnPersistentStore interceptor = (TokenInterceptor_UnPersistentStore) interceptorList.get(index);
                                interceptor.setToken(NetClient.getInstance(context.getApplicationContext()).getRetrofit().baseUrl().host().trim(), returnUserInfo.getData().getToken().getToken().trim());
                                getViewLayer().getBaseApp().setImUserInfo(returnUserInfo.getData().getYunXinUserInfo());
                                getViewLayer().getBaseApp().setUserInfo(returnUserInfo.getData().getUserInfo());
                                getViewLayer().logged(returnUserInfo.getData().getUserInfo());
                                getViewLayer().getBaseApp().setCountDownTime();
                                getViewLayer().getBaseApp().startCountDown();
                                return;
                            }
                            if(index == interceptorList.size() -1)
                            {
                                getViewLayer().showToast("登录失败！请重新刷卡登录");
                            }
                        }
                    }
                }
            });
        }
    }

    public void bindPhoneNum(final Context context,final String idCard,final String phone,final String code)
    {
        if(isAttachContextAndViewLayer())
        {
            LoginModel.bindPhoneNum(context,idCard,phone,code,new BaseMvp_LocalCallBack<BaseReturnData>(this)
            {
                public void onSuccess(BaseReturnData returnUserInfo)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().bindIdCardSuccess(phone);
                    }
                }
            });
        }
    }

    public void getVerifiedCode(final Context context,final String phoneNum)
    {
        if(isAttachContextAndViewLayer())
        {
            LoginModel.getVerifiedCode(context,phoneNum,new BaseMvp_LocalCallBack<BaseReturnData>(this)
            {
                public void onSuccess(BaseReturnData returnUserInfo)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().getVerifiedCodeSuccess();
                        getViewLayer().showToast("成功获取短信验证码");
                    }
                }
            });
        }
    }

    public void getBleMacAddress(final Context context,final String wifiMacAddress)
    {
        if(isAttachContextAndViewLayer())
        {
            LoginModel.getWifiMacAddress(context,wifiMacAddress,new BaseMvp_LocalCallBack<BaseReturnData<WifiMacAddress>>(this)
            {
                public void onSuccess(BaseReturnData<WifiMacAddress> returnWifiMacAddress)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().setWifiMacAddress(returnWifiMacAddress.getData().getCardReaderId().trim());
                    }
                }

                public void onFailure(String msg)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().showToast("获取设备标识码失败，请确保连接网络并重启应用！");
                    }
                }

                public void onError(String msg)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().showToast("获取设备标识码失败，请确保连接网络并重启应用！");
                    }
                }
            });
        }
    }

    public void logOut(final Context context)
    {
        if(isAttachContextAndViewLayer())
        {
            List<Interceptor> interceptorList = NetClient.getInstance(context.getApplicationContext()).getOkHttpClient().interceptors();
            for(int index=0;index<interceptorList.size();index++)
            {
                if(interceptorList.get(index) instanceof TokenInterceptor_UnPersistentStore)
                {
                    TokenInterceptor_UnPersistentStore interceptor = (TokenInterceptor_UnPersistentStore) interceptorList.get(index);
                    interceptor.setToken(NetClient.getInstance(context.getApplicationContext()).getRetrofit().baseUrl().host().trim(),"");
                    getViewLayer().getBaseApp().setImUserInfo(new UserInfo.ImUserInfo());
                    getViewLayer().getBaseApp().setUserInfo(null);
                    getViewLayer().logOut();
                    getViewLayer().getBaseApp().cancelCountDown();
                    getViewLayer().getBaseApp().setCountDownTime();
                    return;
                }
                if(index == interceptorList.size() -1)
                {
                    getViewLayer().showToast("退出登录失败！请重新退出登录");
                }
            }
        }
    }
}
package ufhealth.integratedmachine.client.ui.main.presenter;

import java.util.List;
import okhttp3.Interceptor;
import android.content.Context;
import ufhealth.integratedmachine.client.network.NetClient;
import ufhealth.integratedmachine.client.bean.main.UserInfo;
import ufhealth.integratedmachine.client.bean.BaseReturnData;
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

    public void logging(final Context context, String idCard)
    {
        if(isAttachContextAndViewLayer())
        {
            LoginModel.login(context,idCard,new BaseMvp_LocalCallBack<BaseReturnData<UserInfo>>(this)
            {
                public void onSuccess(BaseReturnData<UserInfo> returnUserInfo)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        List<Interceptor> interceptorList = NetClient.getInstance(context.getApplicationContext()).getOkHttpClient().interceptors();
                        for(int index=0;index<interceptorList.size();index++)
                        {
                            if(interceptorList.get(index) instanceof TokenInterceptor_UnPersistentStore)
                            {
                                TokenInterceptor_UnPersistentStore interceptor = (TokenInterceptor_UnPersistentStore) interceptorList.get(index);
                                interceptor.setToken(NetClient.getInstance(context.getApplicationContext()).getRetrofit().baseUrl().host().trim(), returnUserInfo.getData().getToken().getToken().trim());

                                getViewLayer().getBaseApp().setUserInfo(returnUserInfo.getData().getUserInfo());
                                getViewLayer().logging(returnUserInfo.getData().getUserInfo());

                                UserInfo.ImUserInfo imUserInfo = new UserInfo.ImUserInfo();
                                imUserInfo.setAccid("test3");
                                imUserInfo.setToken("005d1223e1a6f9d4da435dd04d17150b");
                                getViewLayer().getBaseApp().setImUserInfo(imUserInfo);

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

    public void logOut(Context context)
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

                    getViewLayer().getBaseApp().setUserInfo(null);
                    getViewLayer().logOut();

                    UserInfo.ImUserInfo imUserInfo = new UserInfo.ImUserInfo();
                    imUserInfo.setAccid("");
                    imUserInfo.setToken("");
                    getViewLayer().getBaseApp().setImUserInfo(imUserInfo);

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
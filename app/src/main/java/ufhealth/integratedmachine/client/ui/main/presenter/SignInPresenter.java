package ufhealth.integratedmachine.client.ui.main.presenter;

import java.util.List;
import okhttp3.Interceptor;
import ufhealth.integratedmachine.client.network.NetClient;
import ufhealth.integratedmachine.client.bean.main.UserInfos;
import ufhealth.integratedmachine.client.bean.BaseReturnData;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_Presenter;
import ufhealth.integratedmachine.client.ui.main.model.SignInModel;
import ufhealth.integratedmachine.client.ui.main.view_v.SignInAct_V;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_LocalCallBack;
import com.yuan.devlibrary._9__Network.okhttp.Http3Interceptions.TokenInterceptor_PersistentStore;

public class SignInPresenter extends BaseMvp_Presenter<SignInAct_V>
{
    public void signIn(String account,String password)
    {
        if(isAttachContextAndViewLayer())
        {
            SignInModel.signIn(getContext(),account,password,new BaseMvp_LocalCallBack<BaseReturnData<UserInfos>>(this)
            {
                public void onSuccess(BaseReturnData<UserInfos> userInfos)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        List<Interceptor> interceptorList = NetClient.getInstance(getContext().getApplicationContext()).getOkHttpClient().interceptors();
                        for(int index = 0;index<interceptorList.size();index++)
                        {
                            if(interceptorList.get(index) instanceof TokenInterceptor_PersistentStore)
                            {
                                TokenInterceptor_PersistentStore interceptor = (TokenInterceptor_PersistentStore) interceptorList.get(index);
                                interceptor.updateToken(NetClient.getInstance(getContext().getApplicationContext()).getRetrofit().baseUrl().host().trim(), userInfos.getData().getToken().trim());
                                getViewLayer().getBaseApp().setUserInfos(userInfos.getData());
                                return;
                            }
                            if(index == interceptorList.size() -1)
                            {
                                getViewLayer().showToast("登录失败，请稍后再试，谢谢！");
                            }
                        }
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
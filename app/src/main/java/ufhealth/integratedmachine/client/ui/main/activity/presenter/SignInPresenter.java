package ufhealth.integratedmachine.client.ui.main.activity.presenter;

import java.util.List;
import okhttp3.Interceptor;
import ufhealth.integratedmachine.client.network.NetClient;
import ufhealth.integratedmachine.client.bean.main.UserInfo;
import ufhealth.integratedmachine.client.bean.BaseReturnData;
import com.yuan.devlibrary._12_______Utils.SharepreferenceUtils;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_Presenter;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_LocalCallBack;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_EntranceOfModel;
import ufhealth.integratedmachine.client.ui.main.activity.model.SignInModel;
import ufhealth.integratedmachine.client.ui.main.activity.view_v.SignInAct_V;
import com.yuan.devlibrary._9__Network.okhttp.Http3Interceptions.TokenInterceptor_PersistentStore;

public class SignInPresenter extends BaseMvp_Presenter<SignInAct_V>
{
    public void signIn(final String username,final String password)
    {
        if(isAttachContextAndViewLayer())
        {
            BaseMvp_EntranceOfModel.requestDatas(SignInModel.class).
            putForm("username",username).putForm("password",password).convertForms().executeOfNet(getContext(),SignInModel.SignIn,new BaseMvp_LocalCallBack<BaseReturnData<UserInfo>>(this)
            {
                public void onSuccess(BaseReturnData<UserInfo> userInfo)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        List<Interceptor> interceptorList = NetClient.getInstance(getContext().getApplicationContext()).getOkHttpClient().interceptors();
                        for(int index = 0;index<interceptorList.size();index++)
                        {
                            if(interceptorList.get(index) instanceof TokenInterceptor_PersistentStore)
                            {
                                TokenInterceptor_PersistentStore interceptor = (TokenInterceptor_PersistentStore) interceptorList.get(index);
                                interceptor.updateToken(NetClient.getInstance(getContext().getApplicationContext()).getRetrofit().baseUrl().host().trim(), userInfo.getData().getToken().trim());
                                SharepreferenceUtils.storageObject(getContext(),"password",password);
                                SharepreferenceUtils.storageObject(getContext(),"username",username);
                                getViewLayer().getBaseApp().setUserInfos(userInfo.getData());
                                getViewLayer().signInSuccess();
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
                        getViewLayer().signInFailure();
                    }
                }

                public void onError(String msg)
                {
                    super.onError(msg);
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().signInFailure();
                    }
                }
            });
        }
    }
}
package ufhealth.integratedmachine.client.ui.main.presenter;

import ufhealth.integratedmachine.client.bean.main.UserInfo;
import ufhealth.integratedmachine.client.ui.main.view_v.MainAct_V;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_Presenter;

public class MainPresenter extends BaseMvp_Presenter<MainAct_V>
{
    public MainPresenter()
    {

    }

    public void logging()
    {
        //模拟用户刷身份证进行登录--P层
        if(isAttachContextAndViewLayer())
        {
            UserInfo userInfo = new UserInfo(1,"袁小龙","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1523879440634&di=96253c00b9a7b78764a44d9a7eedd581&imgtype=0&src=http%3A%2F%2Fe.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2Ff31fbe096b63f6240fb4d5c98d44ebf81b4ca30b.jpg");
            //模拟用户刷身份证进行登录--调用V层
            getViewLayer().getBaseApp().setUserInfo(userInfo);
            getViewLayer().getBaseApp().setCountDownTime();
            getViewLayer().getBaseApp().startCountDown();
            getViewLayer().logging(userInfo);
        }
    }

    public void logOut()
    {
        //模拟用户退出登录--P层
        if(isAttachContextAndViewLayer())
        {
            //模拟用户退出登录--调用V层
            getViewLayer().getBaseApp().setUserInfo(null);
            getViewLayer().getBaseApp().cancelCountDown();
            getViewLayer().getBaseApp().setCountDownTime();
            getViewLayer().logOut();
        }
    }
}
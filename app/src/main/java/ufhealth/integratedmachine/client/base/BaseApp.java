package ufhealth.integratedmachine.client.base;

import android.content.Context;
import com.hwangjr.rxbus.RxBus;
import android.support.multidex.MultiDex;
import com.yuan.devlibrary._1App.BaseApplication;
import com.yuan.devlibrary._1App.BaseUiAdapterHelper;
import ufhealth.integratedmachine.client.util.CountDownUtil;
import ufhealth.integratedmachine.client.ui.main.model.UserInfo;

public class BaseApp extends BaseApplication
{
    private BaseApp mBaseApp;
    private Boolean mIsLogged;
    private UserInfo mUserInfo;
    private CountDownUtil mCountDownUtil;
    private BaseUiAdapterHelper mUiHelper;
    private static final Integer COUNTDOWN_TIME = 180;//以秒为单位

    protected void attachBaseContext(Context base)
    {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public void onCreate()
    {
        super.onCreate();
        mBaseApp  = this;
        mUiHelper = BaseUiAdapterHelper.getInstances(mBaseApp,1920,1080);
        mUiHelper.performSchemeForApp();
        /***************************************计时器操作*****************************************/
        mIsLogged = false;
        mCountDownUtil = new CountDownUtil(COUNTDOWN_TIME)
        {
            public void onFinish()
            {
                mCountDownUtil.cancel();
                RxBus.get().post(true);
                mCountDownUtil.setmMillisInFuture(COUNTDOWN_TIME);
            }

            public void onTick(long millisUntilFinished)
            {
                RxBus.get().post(millisUntilFinished);
            }
        };
    }

    public Boolean getIsLogged()
    {
        return mIsLogged;

    }

    public UserInfo getUserInfo()
    {
        return mUserInfo;

    }

    public void setCountDownTime()
    {
        mCountDownUtil.setmMillisInFuture(COUNTDOWN_TIME);

    }

    public void setCountDownTime(Long time)
    {
        mCountDownUtil.setmMillisInFuture(null != time ? time : 0);
    }

    public void setIsLogged(Boolean isLogged)
    {
        mIsLogged = isLogged;

    }

    public void setUserInfo(UserInfo userInfo)
    {
        mUserInfo = userInfo;

    }

    public void startCountDown()
    {
        mCountDownUtil.start();
    }

    public void cancelCountDown()
    {
        mCountDownUtil.cancel();
    }
}
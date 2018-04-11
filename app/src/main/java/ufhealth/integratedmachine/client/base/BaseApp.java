package ufhealth.integratedmachine.client.base;

import com.yuan.devlibrary._1App.BaseApplication;
import com.yuan.devlibrary._1App.BaseUiAdapterHelper;

public class BaseApp extends BaseApplication
{
    private BaseApp mBaseApp;
    private BaseUiAdapterHelper mUiHelper;

    public void onCreate()
    {
        super.onCreate();
        mBaseApp  = this;
        mUiHelper = BaseUiAdapterHelper.getInstances(mBaseApp,1920,1080);
        mUiHelper.performSchemeForApp();
    }
}
package com.yuan.devlibrary._1App;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import android.app.Activity;
import android.graphics.Point;
import android.app.Application;
import java.lang.reflect.Field;
import android.util.DisplayMetrics;
import android.content.res.Resources;
import com.yuan.devlibrary._12_______Utils.ScreenInfosUtils;

public class BaseUiAdapterHelper
{
    private float mDesignWidth = 0f;
    private float mDesignHeight = 0f;
    private Application mApplication = null;
    private static BaseUiAdapterHelper mInstances = null;
    private Application.ActivityLifecycleCallbacks mActivityLifecycleCallbacks = null;

    public static BaseUiAdapterHelper getInstances(Application application,float designWidth,float designHeight)
    {
        if(mInstances != null)
            return mInstances;
        else
        {
            if(application == null)
            {
                Toast.makeText(application.getApplicationContext(),"获取界面适配工具失败了！",Toast.LENGTH_LONG).show();
                return null;
            }
            else
            {
                mInstances = new BaseUiAdapterHelper(application,designWidth,designHeight);
                return mInstances;
            }
        }
    }

    private BaseUiAdapterHelper(Application application,float designWidth,float designHeight)
    {
        mApplication = application;
        mDesignWidth = designWidth;
        mDesignHeight = designHeight;
        mActivityLifecycleCallbacks = new Application.ActivityLifecycleCallbacks()
        {
            public void onActivityCreated(Activity activity, Bundle savedInstanceState)
            {
                setLengthWidthRatio(activity,mDesignWidth,mDesignHeight);
                activity.getWindow().getDecorView().invalidate();
            }
            public void onActivityResumed(Activity activity)
            {
                Log.i("mmssgg","StatusBarHeight:" + ScreenInfosUtils.getStatusBarHeightBeforeInit(activity));
                if(ScreenInfosUtils.isShowNavigationBar(activity))
                    Log.i("mmssgg","NavigationBarHeight:" + ScreenInfosUtils.getNavigationBarHeight(activity));
                setLengthWidthRatio(activity,mDesignWidth,mDesignHeight);
                activity.getWindow().getDecorView().invalidate();
            }
            public void onActivityPaused(Activity activity)
            {
                revokeLengthWidthRatio(activity);
            }
            public void onActivityStarted(Activity activity){}
            public void onActivityStopped(Activity activity){}
            public void onActivityDestroyed(Activity activity){}
            public void onActivitySaveInstanceState(Activity activity, Bundle outState){}
        };
    }

   /*********************************整个App采用此UI适配方案*******************************/
    public void performSchemeForApp()
    {
        if(null != mApplication && null != mActivityLifecycleCallbacks)
        {
            mApplication.registerActivityLifecycleCallbacks(mActivityLifecycleCallbacks);
        }
    }

    /********************************整个App撤销采用此UI适配方案****************************/
    public void revokeSchemeForApp()
    {
        if(null != mApplication && null != mActivityLifecycleCallbacks)
        {
            mApplication.unregisterActivityLifecycleCallbacks(mActivityLifecycleCallbacks);
        }
    }

    /**如果要临时赋值Activity长宽比例值,则需要在Activity的onCreate()函数以及onResume()函数分
     **别调用此类的performSchemeForActOrFrag()函数,最后也必须在Activity的onPaused()函数里调
     *********用此类的revokeSchemeForActOrFrag()函数，只有这样才能做到最完美的UI适配*******/
    /**************************单个Activity页面采用此UI适配方案***************************
    ********************长宽比例值为0时则表示不设置相应的长宽比例项**********************/
    public void performSchemeForActOrFrag(Activity activity,float designWidth,float designHeight)
    {
        setLengthWidthRatio(activity,designWidth,designHeight);
    }

    /************************单个Activity页面撤销采用此UI适配方案*************************/
    public void revokeSchemeForActOrFrag(Activity activity)
    {
        revokeLengthWidthRatio(activity);
    }

    /******************************根据效果图尺寸设置长宽比例值*****************************/
    /**********************长宽比例值为0时则表示不设置相应的长宽比例项**********************/
    private void setLengthWidthRatio(Activity activity, float designWidth, float designHeight)
    {
        if(activity == null) return;
        Point size = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(size);
        Resources resources = activity.getResources();
        resources.getDisplayMetrics().widthPixels = size.x;
        resources.getDisplayMetrics().heightPixels = size.y;
        if(ScreenInfosUtils.isShowStatusBar(activity))
            size.y = size.y - ScreenInfosUtils.getStatusBarHeightBeforeInit(activity);
        if(designWidth != 0f) resources.getDisplayMetrics().xdpi = size.x / designWidth * 72f;
        if(designHeight != 0f) resources.getDisplayMetrics().scaledDensity = size.y / designHeight;
        DisplayMetrics metrics = getMetricsOnMiui(activity.getResources());
        if(metrics != null)
        {
            if(designWidth != 0) metrics.xdpi = size.x / designWidth * 72f;
            if(designHeight != 0) metrics.scaledDensity = size.y / designHeight;
        }
    }

    /*********************************撤销已设置的长宽比例值********************************/
    private void revokeLengthWidthRatio(Activity context)
    {
        context.getResources().getDisplayMetrics().setToDefaults();
        DisplayMetrics metrics = getMetricsOnMiui(context.getResources());
        if(metrics != null)
            metrics.setToDefaults();
    }

    /******************解决小米更改框架导致MIUI7+Android5.1.1上出现的失效问题***************/
    /******************(以及极少数基于这部分MIUI去掉art然后置入xposed的手机)****************/
    private DisplayMetrics getMetricsOnMiui(Resources resources)
    {
        if("MiuiResources".equals(resources.getClass().getSimpleName()) || "XResources".equals(resources.getClass().getSimpleName()))
        {
            try
            {
                Field field = Resources.class.getDeclaredField("mTmpMetrics");
                field.setAccessible(true);
                return  (DisplayMetrics) field.get(resources);
            }
            catch (Exception e)
            {
                return null;
            }
        }
        return null;
    }
}
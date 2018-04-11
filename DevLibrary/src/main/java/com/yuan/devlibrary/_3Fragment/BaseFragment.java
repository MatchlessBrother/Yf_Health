package com.yuan.devlibrary._3Fragment;

import android.view.View;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.support.v4.app.Fragment;
import com.yuan.devlibrary._2Activity.BaseActivity;
import com.yuan.devlibrary._12_______Utils.ResourceTools;

public abstract class BaseFragment extends Fragment
{
    protected BaseActivity activity;
    protected View         mRootView;

    /***********************************Fragment初始化界面的部分***********************************/
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        if (mRootView == null)
        {
            mRootView = ResourceTools.generateView(getActivity(),setLayoutResID());
            initWidgets(mRootView);
            initDatas();
            initLogic();
        }
        ViewGroup parentViewGroup = (ViewGroup)mRootView.getParent();
        if(parentViewGroup != null)
            parentViewGroup.removeView(mRootView);
        return mRootView;
    }

    /***********************************初始化布局文件所有控件的函数*******************************/
    protected abstract void initWidgets(View rootView);

    /**************************************设置布局文件的函数**************************************/
    protected abstract int setLayoutResID();

    /**********************************初始化Fragment界面数据的函数********************************/
    protected abstract void initDatas();

    /**********************************初始化Fragment界面逻辑的函数********************************/
    protected abstract void initLogic();
}
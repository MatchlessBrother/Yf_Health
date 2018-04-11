package ufhealth.integratedmachine.client.base;

import android.os.Bundle;
import com.yuan.devlibrary._2Activity.BasePhotoActivity;
import com.yuan.devlibrary._12_______Utils.ScreenInfosTools;

public abstract class BasePhotoAct extends BasePhotoActivity
{
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    protected void initStatusBarAddTitleBar()
    {
        if(!ScreenInfosTools.isShowTitleBar(this))
            ScreenInfosTools.hideTitleBar(this);
    }
}
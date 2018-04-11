package ufhealth.integratedmachine.client.base;

import android.os.Bundle;
import android.util.TypedValue;

import com.yuan.devlibrary._11___Widget.promptBox.BaseProgressDialog;
import com.yuan.devlibrary._12_______Utils.PromptBoxTools;
import com.yuan.devlibrary._2Activity.BasePhotoActivity;
import com.yuan.devlibrary._12_______Utils.ScreenInfosTools;

import ufhealth.integratedmachine.client.ui.base.BaseMvp_View;

public abstract class BasePhotoAct extends BasePhotoActivity implements BaseMvp_View
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

    public BaseProgressDialog showLoading()
    {
        return PromptBoxTools.showLoadingDialog(this,"请稍等",2,false,null);
    }

    public void hideLoading(BaseProgressDialog progressDialog)
    {
        progressDialog.hide();

    }
}
package ufhealth.integratedmachine.client.base;

import android.os.Bundle;

import com.yuan.devlibrary._11___Widget.promptBox.BaseProgressDialog;
import com.yuan.devlibrary._12_______Utils.PromptBoxTools;
import com.yuan.devlibrary._2Activity.BaseActivity;
import com.yuan.devlibrary._12_______Utils.ScreenInfosTools;

import ufhealth.integratedmachine.client.ui.base.BaseMvp_View;

public abstract class BaseAct extends BaseActivity implements BaseMvp_View
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

    public void showToast(String str)
    {
        PromptBoxTools.showToast(this,str);

    }

    public BaseProgressDialog showLoading()
    {
        return PromptBoxTools.showLoadingDialog(this,"请稍等",2,false,null);
    }

    public void hideLoading(BaseProgressDialog progressDialog)
    {
        PromptBoxTools.dismissLoadingDialog(progressDialog);

    }
}
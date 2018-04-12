package ufhealth.integratedmachine.client.base;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.yuan.devlibrary._11___Widget.promptBox.BaseDialog;
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
        showToast(str,24);

    }

    public BaseProgressDialog showLoadingDialog()
    {
        return PromptBoxTools.showLoadingDialog(this,"请稍等",1,80,false,null);
    }

    public void showToast(String str,float strSize)
    {
        PromptBoxTools.showToast(this,str,strSize, TypedValue.COMPLEX_UNIT_SP, Gravity.CENTER,0);
    }

    public void dismissPromptDialog(BaseDialog baseDialog)
    {
        PromptBoxTools.dismissPromptDialog(baseDialog);
    }

    public BaseProgressDialog showLoadingDialog(boolean isCanceledOnTouchOutside,BaseProgressDialog.OnClickOutsideListener onClickOutsideListener)
    {
        return PromptBoxTools.showLoadingDialog(this,"请稍等",1,80,isCanceledOnTouchOutside,onClickOutsideListener);
    }

    @Override
    public void dismissLoadingDialog(BaseProgressDialog progressDialog)
    {
        PromptBoxTools.dismissLoadingDialog(progressDialog);
    }

    public BaseDialog showPromptDialog(String titleStr,String contentStr,String falseStr,String trueStr)
    {
        return showPromptDialog(titleStr,contentStr,falseStr,trueStr,true,null,null,null);
    }

    @Override
    public BaseDialog showPromptDialog(String titleStr,String contentStr,String falseStr,String trueStr,boolean isCanceledOnTouchOutside,View.OnClickListener trueOnClickListener,View.OnClickListener falseOnClickListener,BaseDialog.OnClickOutsideListener onClickOutsideListener)
    {
        return PromptBoxTools.showPromptDialog(this,titleStr,Color.argb(255,51,51,51), 36,TypedValue.COMPLEX_UNIT_SP,new ColorDrawable(0xffffffff),View.VISIBLE,
                                                             contentStr, Color.argb(255,102,102,102),30,TypedValue.COMPLEX_UNIT_SP,new ColorDrawable(0xffffffff),
                                                             falseStr,Color.argb(255,51,51,51),36,TypedValue.COMPLEX_UNIT_SP,new ColorDrawable(0xffffffff),View.VISIBLE,
                                                             trueStr,Color.argb(255,51,51,51),36,TypedValue.COMPLEX_UNIT_SP,new ColorDrawable(0xffffffff),View.VISIBLE,
                                                             isCanceledOnTouchOutside,trueOnClickListener,falseOnClickListener,onClickOutsideListener);
    }
}
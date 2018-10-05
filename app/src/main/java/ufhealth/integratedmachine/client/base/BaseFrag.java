package ufhealth.integratedmachine.client.base;

import android.view.View;
import android.view.Gravity;
import android.graphics.Color;
import android.util.TypedValue;
import android.graphics.drawable.ColorDrawable;
import com.yuan.devlibrary._3Fragment.BaseFragment;
import com.yuan.devlibrary._12_______Utils.PromptBoxUtils;
import com.yuan.devlibrary._11___Widget.promptBox.BaseDialog;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_View;
import com.yuan.devlibrary._11___Widget.promptBox.BaseProgressDialog;

public abstract class BaseFrag extends BaseFragment implements BaseMvp_View
{
    /**********************************************************************************************/
    /**********************************************************************************************/

    public void showToast(String str)
    {
        showToast(str,22);

    }

    public void showToast(String str,float strSize)
    {
        PromptBoxUtils.showToast(mActivity,str,strSize, TypedValue.COMPLEX_UNIT_MM, Gravity.CENTER,0);
    }

    /**********************************************************************************************/
    /**********************************************************************************************/

    public BaseProgressDialog showLoadingDialog()
    {
        return PromptBoxUtils.showLoadingDialog(mActivity,"请稍等",1,80,false,null);
    }

    public void dismissLoadingDialog(BaseProgressDialog progressDialog)
    {
        PromptBoxUtils.dismissLoadingDialog(progressDialog);
    }

    public BaseProgressDialog showLoadingDialog(boolean isCanceledOnTouchOutside,BaseProgressDialog.OnClickOutsideListener onClickOutsideListener)
    {
        return PromptBoxUtils.showLoadingDialog(mActivity,"请稍等",1,80,isCanceledOnTouchOutside,onClickOutsideListener);
    }

    /**********************************************************************************************/
    /**********************************************************************************************/

    public void dismissPromptDialog(BaseDialog baseDialog)
    {
        PromptBoxUtils.dismissPromptDialog(baseDialog);
    }

    public BaseDialog showPromptDialog(String titleStr,String contentStr,String falseStr,String trueStr)
    {
        return showPromptDialog(titleStr,contentStr,falseStr,trueStr,true,null,null,null);
    }

    public BaseDialog showPromptDialog(String titleStr,String contentStr,String falseStr,String trueStr,boolean isCanceledOnTouchOutside,View.OnClickListener trueOnClickListener,View.OnClickListener falseOnClickListener,BaseDialog.OnClickOutsideListener onClickOutsideListener)
    {
        return PromptBoxUtils.showPromptDialog(mActivity,titleStr,Color.argb(255,51,51,51), 36,TypedValue.COMPLEX_UNIT_MM,new ColorDrawable(0xffffffff),View.VISIBLE,
                contentStr, Color.argb(255,102,102,102),30,TypedValue.COMPLEX_UNIT_MM,new ColorDrawable(0xffffffff),
                falseStr,Color.argb(255,51,51,51),36,TypedValue.COMPLEX_UNIT_MM,new ColorDrawable(0xffffffff),View.VISIBLE,
                trueStr,Color.argb(255,51,51,51),36,TypedValue.COMPLEX_UNIT_MM,new ColorDrawable(0xffffffff),View.VISIBLE,
                isCanceledOnTouchOutside,trueOnClickListener,falseOnClickListener,onClickOutsideListener);
    }
}
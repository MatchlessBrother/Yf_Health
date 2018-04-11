package ufhealth.integratedmachine.client.ui.base;

import android.view.View;
import android.graphics.drawable.Drawable;
import com.yuan.devlibrary._11___Widget.promptBox.BaseDialog;
import com.yuan.devlibrary._11___Widget.promptBox.BaseProgressDialog;

public interface BaseMvp_View
{
    /****显示Toast提示框****/
    void showToast(String msg);

    /**显示加载进度框*/
    BaseProgressDialog showLoadingDialog();

    /**隐藏加载进度框*/
    void dismissLoadingDialog(BaseProgressDialog progressDialog);

    /*************************************隐藏默认提示框*******************************************/
    void dismissPromptDialog(BaseDialog baseDialog);

    /**显示默认提示框，参数含义自己根据名字看，字体大小值默认以Sp为准，颜色属性值默认以动态Color生*
     ********成法为准,背景属性值默认以动态ColorDrawable生成法为准,其余则按照普通情况使用即可*******/
    BaseDialog showPromptDialog(String titleStr, int titleStrColor, Drawable titleStrBackground, int titleStrVisible, String contentStr, String falseStr, int falseStrVisible, String trueStr, int trueStrColor, Drawable trueStrBackground, int trueStrVisible, boolean isCanceledOnTouchOutside, View.OnClickListener trueOnClickListener, View.OnClickListener falseOnClickListener, BaseDialog.OnClickOutsideListener onClickOutsideListener);
}
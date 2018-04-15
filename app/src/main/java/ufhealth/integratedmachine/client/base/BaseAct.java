package ufhealth.integratedmachine.client.base;

import android.view.View;
import android.view.Gravity;
import android.graphics.Color;
import android.util.TypedValue;
import android.widget.TextView;
import ufhealth.integratedmachine.client.R;
import android.graphics.drawable.ColorDrawable;
import com.yuan.devlibrary._2Activity.BaseActivity;
import com.yuan.devlibrary._12_______Utils.PromptBoxTools;
import com.yuan.devlibrary._12_______Utils.ScreenInfosTools;
import com.yuan.devlibrary._11___Widget.promptBox.BaseDialog;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_View;
import com.yuan.devlibrary._11___Widget.promptBox.BaseProgressDialog;

public abstract class BaseAct extends BaseActivity implements BaseMvp_View,View.OnClickListener
{
    private TextView mTitleBackBtn;
    private TextView mTitleContent;
    private TextView mTitleCountdownBtn;
    private TextView mTitleMoreSelector;

    protected void initStatusBarAddTitleBar()
    {
        if (!ScreenInfosTools.isShowTitleBar(this))
            ScreenInfosTools.hideTitleBar(this);
    }

    protected void initWidgets(View rootView)
    {
        if (null != rootView.findViewById(R.id.activity_title_back) &&
                null != rootView.findViewById(R.id.activity_title_countdown) &&
                null != rootView.findViewById(R.id.activity_title_content) &&
                null != rootView.findViewById(R.id.activity_title_more))
        {
            mTitleBackBtn = (TextView) rootView.findViewById(R.id.activity_title_back);
            mTitleContent = (TextView) rootView.findViewById(R.id.activity_title_countdown);
            mTitleCountdownBtn = (TextView) rootView.findViewById(R.id.activity_title_content);
            mTitleMoreSelector = (TextView) rootView.findViewById(R.id.activity_title_more);
            mTitleBackBtn.setOnClickListener(this);
            mTitleMoreSelector.setOnClickListener(this);
        }
    }

    public void onClick(View view)
    {
        switch(view.getId())
        {
            case R.id.activity_title_back:finish();break;
            case R.id.activity_title_more:showToast("hahahahah");break;
        }
    }

    protected boolean isUseDefaultTitleLine()
    {
        return null != mTitleBackBtn && null != mTitleCountdownBtn && null != mTitleContent && null != mTitleMoreSelector ? true : false;
    }

    protected TextView getTitleMoreSelector()
    {
        if(isUseDefaultTitleLine() && null != mTitleMoreSelector)
            return mTitleMoreSelector;
        else
        {
            showToast("因布局中未包含默认标题行,\n所以无法获取\"更多\"选择项");
            return null;
        }
    }

    protected void setTitleContent(String title)
    {
        if(isUseDefaultTitleLine())
            mTitleContent.setText(null != title ? title.trim().toString() : "");
    }

    protected void setTitleCountDownTime(Integer time)
    {
        if(isUseDefaultTitleLine())
            mTitleCountdownBtn.setText((null != time ? time : 0) + "S");
    }

    /**********************************************************************************************/
    /**********************************************************************************************/

    public void showToast(String str)
    {
        showToast(str,24);

    }

    public void showToast(String str,float strSize)
    {
        PromptBoxTools.showToast(this,str,strSize, TypedValue.COMPLEX_UNIT_SP, Gravity.CENTER,0);
    }

    /**********************************************************************************************/
    /**********************************************************************************************/

    public BaseProgressDialog showLoadingDialog()
    {
        return PromptBoxTools.showLoadingDialog(this,"请稍等",1,80,false,null);
    }

    public void dismissLoadingDialog(BaseProgressDialog progressDialog)
    {
        PromptBoxTools.dismissLoadingDialog(progressDialog);
    }

    public BaseProgressDialog showLoadingDialog(boolean isCanceledOnTouchOutside,BaseProgressDialog.OnClickOutsideListener onClickOutsideListener)
    {
        return PromptBoxTools.showLoadingDialog(this,"请稍等",1,80,isCanceledOnTouchOutside,onClickOutsideListener);
    }

    /**********************************************************************************************/
    /**********************************************************************************************/

    public void dismissPromptDialog(BaseDialog baseDialog)
    {
        PromptBoxTools.dismissPromptDialog(baseDialog);
    }

    public BaseDialog showPromptDialog(String titleStr,String contentStr,String falseStr,String trueStr)
    {
        return showPromptDialog(titleStr,contentStr,falseStr,trueStr,true,null,null,null);
    }

    public BaseDialog showPromptDialog(String titleStr,String contentStr,String falseStr,String trueStr,boolean isCanceledOnTouchOutside,View.OnClickListener trueOnClickListener,View.OnClickListener falseOnClickListener,BaseDialog.OnClickOutsideListener onClickOutsideListener)
    {
        return PromptBoxTools.showPromptDialog(this,titleStr,Color.argb(255,51,51,51), 36,TypedValue.COMPLEX_UNIT_SP,new ColorDrawable(0xffffffff),View.VISIBLE,
                                                             contentStr, Color.argb(255,102,102,102),30,TypedValue.COMPLEX_UNIT_SP,new ColorDrawable(0xffffffff),
                                                             falseStr,Color.argb(255,51,51,51),36,TypedValue.COMPLEX_UNIT_SP,new ColorDrawable(0xffffffff),View.VISIBLE,
                                                             trueStr,Color.argb(255,51,51,51),36,TypedValue.COMPLEX_UNIT_SP,new ColorDrawable(0xffffffff),View.VISIBLE,
                                                             isCanceledOnTouchOutside,trueOnClickListener,falseOnClickListener,onClickOutsideListener);
    }
}
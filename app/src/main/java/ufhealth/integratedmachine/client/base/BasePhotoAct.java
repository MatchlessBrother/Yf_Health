package ufhealth.integratedmachine.client.base;

import android.os.Bundle;
import android.view.View;
import android.view.Gravity;
import android.graphics.Color;
import com.hwangjr.rxbus.RxBus;
import android.util.TypedValue;
import android.widget.TextView;
import android.widget.ImageView;
import android.view.MotionEvent;
import com.bumptech.glide.Glide;
import ufhealth.integratedmachine.client.R;
import android.graphics.drawable.ColorDrawable;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.yuan.devlibrary._2Activity.BasePhotoActivity;
import com.yuan.devlibrary._12_______Utils.PromptBoxTools;
import com.yuan.devlibrary._12_______Utils.ScreenInfosTools;
import com.yuan.devlibrary._11___Widget.promptBox.BaseDialog;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_View;
import ufhealth.integratedmachine.client.ui.main.view.MainAct;
import com.yuan.devlibrary._11___Widget.promptBox.BaseProgressDialog;

public abstract class BasePhotoAct extends BasePhotoActivity implements BaseMvp_View,View.OnClickListener
{
    private TextView mTitleBackBtn;
    private TextView mTitleContent;
    private TextView mTitleCountdownBtn;
    private TextView mTitleMoreSelector;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        RxBus.get().register(this);
    }

    protected void initStatusBarAddTitleBar()
    {
        if(!ScreenInfosTools.isShowTitleBar(this))
            ScreenInfosTools.hideTitleBar(this);
    }

    protected void onResume()
    {
        super.onResume();
        if(!ScreenInfosTools.ishideNavigationBarAndStatusBar(this))
            ScreenInfosTools.hideNavigationBarAndStatusBar(this);
    }

    protected void initWidgets(View rootView)
    {
        if (null != rootView.findViewById(R.id.activity_title_back) &&
                null != rootView.findViewById(R.id.activity_title_countdown) &&
                null != rootView.findViewById(R.id.activity_title_content) &&
                null != rootView.findViewById(R.id.activity_title_more))
        {
            mTitleBackBtn = (TextView) rootView.findViewById(R.id.activity_title_back);
            mTitleContent = (TextView) rootView.findViewById(R.id.activity_title_content);
            mTitleCountdownBtn = (TextView) rootView.findViewById(R.id.activity_title_countdown);
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
            case R.id.activity_title_more:onTitleMoreClick();break;
        }
    }

    protected void onTitleMoreClick()
    {

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

    protected void setTitleCountDownTime(Long time)
    {
        if(isUseDefaultTitleLine())
            mTitleCountdownBtn.setText((null != time ? time : 0) + "秒后自动退出");
    }

    protected void setTitleCountDownVisibility(Integer visibility)
    {
        if(isUseDefaultTitleLine())
            mTitleCountdownBtn.setVisibility(visibility == View.GONE || visibility == View.INVISIBLE || visibility == View.VISIBLE ? visibility : View.INVISIBLE);
    }

    protected void setTitleMoreSelectorVisibility(Integer visibility)
    {
        if(isUseDefaultTitleLine())
            mTitleMoreSelector.setVisibility(visibility == View.GONE || visibility == View.INVISIBLE || visibility == View.VISIBLE ? visibility : View.INVISIBLE);
    }

    public void useGlideLoadImg(ImageView imageView, int drawable)
    {
        Glide.with(this).load(drawable).placeholder(R.mipmap.defaultheadimg).error(R.mipmap.defaultheadimg).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
    }

    public void useGlideLoadImg(ImageView imageView, String imgPath)
    {
        Glide.with(this).load(imgPath).placeholder(R.mipmap.defaultheadimg).error(R.mipmap.defaultheadimg).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
    }

    /**********************************************************************************************/
    /**********************************************************************************************/

    public BaseApp getBaseApp()
    {
        return (BaseApp)getApplication();

    }

    public boolean dispatchTouchEvent(MotionEvent ev)
    {
        if(null != getBaseApp().getUserInfo())
        {
            switch(ev.getAction())
            {
                case MotionEvent.ACTION_DOWN:
                {
                    getBaseApp().cancelCountDown();
                    break;
                }
                case MotionEvent.ACTION_UP:
                {
                    getBaseApp().setCountDownTime();
                    getBaseApp().startCountDown();
                    break;
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**********************************************************************************************/
    /***************************************自动退出倒计时*****************************************/
    /**********************************************************************************************/
    /******因为RxBus接受消息的函数不具有继承特性,所以我们必须显示指定接受消息的函数(即:加注释)*****/
    /**************具体逻辑已封装成以下两个函数,使用者只需假覆盖以下函数并加上注解即可*************/

    public void onBackPressed()
    {
        getBaseApp().setCountDownTime();
        getBaseApp().startCountDown();
        super.onBackPressed();
    }

    public void receiveCountDownTime(Long countDownTime)
    {
        setTitleCountDownTime(countDownTime);

    }

    public void receiveCountDownFinish(Boolean isFinish)
    {
        if(isFinish && !(getActivity() instanceof MainAct))
            finish();
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

    protected void onDestroy()
    {
        super.onDestroy();
        RxBus.get().unregister(this);
    }
}
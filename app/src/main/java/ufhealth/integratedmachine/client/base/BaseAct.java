package ufhealth.integratedmachine.client.base;

import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.Gravity;
import android.graphics.Color;
import android.util.TypedValue;
import android.widget.TextView;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import android.widget.ImageButton;
import android.graphics.BitmapFactory;
import ufhealth.integratedmachine.client.R;
import android.graphics.drawable.ColorDrawable;
import com.bumptech.glide.request.RequestOptions;
import com.yuan.devlibrary._2Activity.BaseActivity;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.yuan.devlibrary._12_______Utils.PromptBoxUtils;
import com.yuan.devlibrary._11___Widget.promptBox.BaseDialog;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_View;
import com.yuan.devlibrary._11___Widget.promptBox.BaseProgressDialog;

public abstract class BaseAct extends BaseActivity implements BaseMvp_View,View.OnClickListener
{
    private TextView mTitleContent;
    private TextView mTitleMoreFont;
    private ImageButton mTitleBackBtn;
    private ImageButton mTitleMoreIcon;
    private static final String LOG_TAG = BaseAct.class.getSimpleName();

    protected void initStatusBarAddTitleBar()
    {
        requestWindowFeature(Window.FEATURE_NO_TITLE);

    }

    protected void initWidgets(View rootView)
    {
        if (null != rootView.findViewById(R.id.activity_title_back) &&
                null != rootView.findViewById(R.id.activity_title_content) &&
                null != rootView.findViewById(R.id.activity_title_moreicon) &&
                null != rootView.findViewById(R.id.activity_title_morefont))
        {
            mTitleContent = (TextView) rootView.findViewById(R.id.activity_title_content);
            mTitleBackBtn = (ImageButton) rootView.findViewById(R.id.activity_title_back);
            mTitleMoreFont = (TextView) rootView.findViewById(R.id.activity_title_morefont);
            mTitleMoreIcon = (ImageButton) rootView.findViewById(R.id.activity_title_moreicon);
            mTitleBackBtn.setOnClickListener(this);
            mTitleMoreFont.setOnClickListener(this);
            mTitleMoreIcon.setOnClickListener(this);
        }
    }

    public BaseApp getBaseApp()
    {
        return (BaseApp)getApplication();

    }

    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.activity_title_back:onTitleBackClick();break;
            case R.id.activity_title_morefont:onTitleMoreFontClick();break;
            case R.id.activity_title_moreicon:onTitleMoreIconClick();break;
        }
    }

    protected void onTitleBackClick()
    {
        finish();

    }

    protected void onTitleMoreFontClick()
    {

    }

    protected void onTitleMoreIconClick()
    {

    }

    protected boolean isUseDefaultTitleLine()
    {
        return null != mTitleBackBtn && null != mTitleContent && null !=mTitleMoreFont  && null != mTitleMoreIcon ? true : false;
    }

    protected void setTitleBack(int resource)
    {
        if(isUseDefaultTitleLine())
            mTitleBackBtn.setImageBitmap(BitmapFactory.decodeResource(getResources(),resource));
        else
            Log.i(LOG_TAG,"因未使用自定义标题栏,所以设置返回按钮图标失败！");
    }

    protected void setTitleContent(String title)
    {
        if (isUseDefaultTitleLine())
            mTitleContent.setText(null != title ? title.trim() : "");
        else
            Log.i(LOG_TAG,"因未使用自定义标题栏,所以设置标题内容失败！");
    }

    protected void setTitleBackVisible(int value)
    {
        if(isUseDefaultTitleLine())
            mTitleBackBtn.setVisibility(value);
        else
            Log.i(LOG_TAG,"因未使用自定义标题栏,所以隐藏/显示返回按钮失败！");
    }

    protected void setTitleMoreIcon(int resource)
    {
        if(isUseDefaultTitleLine())
            mTitleMoreIcon.setImageBitmap(BitmapFactory.decodeResource(getResources(),resource));
        else
            Log.i(LOG_TAG,"因未使用自定义标题栏,所以设置更多按钮图标失败！");
    }

    protected void setTitleMoreIconVisible(int value)
    {
        if(isUseDefaultTitleLine())
            mTitleMoreIcon.setVisibility(value);
        else
            Log.i(LOG_TAG,"因未使用自定义标题栏,所以隐藏/显示更多按钮失败！");
    }

    protected void setTitleMoreFont(String text)
    {
        if(isUseDefaultTitleLine())
            mTitleMoreFont.setText(null != text ? text.trim() : "");
        else
            Log.i(LOG_TAG,"因未使用自定义标题栏,所以设置更多文字内容失败！");
    }

    protected void setTitleMoreFontVisible(int value)
    {
        if(isUseDefaultTitleLine())
            mTitleMoreFont.setVisibility(value);
        else
            Log.i(LOG_TAG,"因未使用自定义标题栏,所以隐藏/显示更多文字内容失败！");
    }

    public void useGlideLoadImg(ImageView imageView, int drawable)
    {
        RequestOptions options = new RequestOptions();
        options.error(R.mipmap.defaultimage);
        options.placeholder(R.mipmap.defaultimage);
        options.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(this).load(drawable).apply(options).into(imageView);
    }

    public void useGlideLoadImg(ImageView imageView, String imgPath)
    {
        RequestOptions options = new RequestOptions();
        options.error(R.mipmap.defaultimage);
        options.placeholder(R.mipmap.defaultimage);
        options.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(this).load(imgPath).apply(options).into(imageView);
    }

    public void useGlideLoadImgForHead(ImageView imageView, int drawable)
    {
        RequestOptions options = new RequestOptions();
        options.error(R.mipmap.defaultheadimg);
        options.placeholder(R.mipmap.defaultheadimg);
        options.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(this).load(drawable).apply(options).into(imageView);
    }

    public void useGlideLoadImgForHead(ImageView imageView, String imgPath)
    {
        RequestOptions options = new RequestOptions();
        options.error(R.mipmap.defaultheadimg);
        options.placeholder(R.mipmap.defaultheadimg);
        options.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(this).load(imgPath).apply(options).into(imageView);
    }

    /**********************************************************************************************/
    /**********************************************************************************************/

    public void showToast(String str)
    {
        showToast(str,22);

    }

    public void showToast(String str,float strSize)
    {
        PromptBoxUtils.showToast(this,str,strSize, TypedValue.COMPLEX_UNIT_MM, Gravity.CENTER,0);
    }

    /**********************************************************************************************/
    /**********************************************************************************************/

    public BaseProgressDialog showLoadingDialog()
    {
        return PromptBoxUtils.showLoadingDialog(this,"请稍等",1,80,false,null);
    }

    public void dismissLoadingDialog(BaseProgressDialog progressDialog)
    {
        PromptBoxUtils.dismissLoadingDialog(progressDialog);
    }

    public BaseProgressDialog showLoadingDialog(boolean isCanceledOnTouchOutside,BaseProgressDialog.OnClickOutsideListener onClickOutsideListener)
    {
        return PromptBoxUtils.showLoadingDialog(this,"请稍等",1,80,isCanceledOnTouchOutside,onClickOutsideListener);
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
        return PromptBoxUtils.showPromptDialog(this,titleStr,Color.argb(255,51,51,51), 36,TypedValue.COMPLEX_UNIT_MM,new ColorDrawable(0xffffffff),View.VISIBLE,
                                                             contentStr, Color.argb(255,102,102,102),30,TypedValue.COMPLEX_UNIT_MM,new ColorDrawable(0xffffffff),
                                                             falseStr,Color.argb(255,51,51,51),36,TypedValue.COMPLEX_UNIT_MM,new ColorDrawable(0xffffffff),View.VISIBLE,
                                                             trueStr,Color.argb(255,51,51,51),36,TypedValue.COMPLEX_UNIT_MM,new ColorDrawable(0xffffffff),View.VISIBLE,
                                                             isCanceledOnTouchOutside,trueOnClickListener,falseOnClickListener,onClickOutsideListener);
    }
}
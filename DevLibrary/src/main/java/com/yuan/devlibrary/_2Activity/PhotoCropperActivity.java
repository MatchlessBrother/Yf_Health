package com.yuan.devlibrary._2Activity;

import java.io.File;
import android.os.Bundle;
import android.view.View;
import java.io.IOException;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.TextView;
import java.io.FileOutputStream;
import com.yuan.devlibrary.R;
import android.widget.LinearLayout;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import android.graphics.BitmapFactory;
import com.yuan.devlibrary._12_______Utils.StringTools;

public class PhotoCropperActivity extends Activity implements View.OnClickListener
{
    private LinearLayout mTitleLl;
    private TextView mBackTv;
    private TextView mTitleTv;
    private TextView mCompleteTv;
    private PhotoCropperImageView mCropIv;

    /**************************设置标题栏字体的颜色值**************************/
    private static  Integer mTitleTextColor = 0;
    /*******************设置标题栏字体颜色值时用到的特定字符串*****************/
    public static final String TITLETEXTCOLOR = "TitleTextColor";
    /**************获取或则设置标题栏与裁剪框大小按钮的背景颜色值**************/
    private static  Integer mTitleColor = 0;
    /********获取或则设置标题栏与裁剪框大小按钮背景颜色用到的特定字符串********/
    public static final String TITLECOLOR = "TitleColor";
    /*************************需要裁剪的图片的的字符串路径*********************/
    private static String  mBitmapPath = "";
    /*************获取或则设置截取框需要截取的图片所使用的特定字符串***********/
    public static final String BITMAPPATH = "BitmapPath";
    /*******************************裁剪框是否是圆形***************************/
    private static boolean  mIsRoundCropper = false;
    /**************获取或则设置裁剪框是否是圆形所使用的特定字符串**************/
    public static final String ROUNDCROPPER = "RoundCropper";

    /****************图片截取框宽度与高度的比例值**************/
    /****保存以及恢复图片截取框宽度和高度所用到的特定字符串****/
    private static Integer mAspectRatio_x = 1;
    private static Integer mAspectRatio_y = 1;
    public static final String ASPECTRATIO_X = "AspectRatio_x";
    public static final String ASPECTRATIO_Y = "AspectRatio_y";

    /*********保存图片截取框宽度和高度的比例值*********/
    protected void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        bundle.putInt(ASPECTRATIO_X, mAspectRatio_x);
        bundle.putInt(ASPECTRATIO_Y, mAspectRatio_y);
        bundle.putInt(TITLECOLOR, mTitleColor);
        bundle.putString(BITMAPPATH, mBitmapPath);
        bundle.putInt(TITLETEXTCOLOR, mTitleTextColor);
        bundle.putBoolean(ROUNDCROPPER, mIsRoundCropper);
    }

    /*********恢复图片截取框宽度和高度的比例值*********/
    protected void onRestoreInstanceState(Bundle bundle)
    {
        super.onRestoreInstanceState(bundle);
        mAspectRatio_x = bundle.getInt(ASPECTRATIO_X);
        mAspectRatio_y = bundle.getInt(ASPECTRATIO_Y);
        mTitleColor = bundle.getInt(TITLECOLOR);
        mBitmapPath = bundle.getString(BITMAPPATH);
        mTitleTextColor =  bundle.getInt(TITLETEXTCOLOR);
        mIsRoundCropper = bundle.getBoolean(ROUNDCROPPER);
        mTitleLl.setBackgroundColor(mTitleColor);
        mBackTv.setTextColor(mTitleTextColor);
        mTitleTv.setTextColor(mTitleTextColor);
        mCompleteTv.setTextColor(mTitleTextColor);
        mCropIv.setImageBitmap(BitmapFactory.decodeFile(mBitmapPath));
        mCropIv.setCustomRatio(mAspectRatio_x, mAspectRatio_y);
        if(mIsRoundCropper)
            mCropIv.setCropMode(PhotoCropperImageView.CropMode.CIRCLE);
        else
            mCropIv.setCropMode(PhotoCropperImageView.CropMode.RATIO_CUSTOM);
    }

    /*****************初始化裁剪设备*******************/
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photocropper);
        mTitleLl = (LinearLayout)findViewById(R.id.photocropper_titleall);
        mBackTv = (TextView)findViewById(R.id.photocropper_titleback);
        mTitleTv = (TextView)findViewById(R.id.photocropper_title);
        mCompleteTv = (TextView)findViewById(R.id.photocropper_titlecomplete);
        mCropIv = (PhotoCropperImageView)findViewById(R.id.photocropper_imageview);
        /***************************************设置标题栏的字体颜色***************************************/
        mTitleTextColor = getIntent().getIntExtra(TITLETEXTCOLOR,R.color.white);
        mBackTv.setTextColor(mTitleTextColor);
        mTitleTv.setTextColor(mTitleTextColor);
        mCompleteTv.setTextColor(mTitleTextColor);
        /***************************************设置标题栏的背景颜色***************************************/
        mTitleColor = getIntent().getIntExtra(TITLECOLOR,R.color.cropperactivity_titlebg);
        mTitleLl.setBackgroundColor(mTitleColor);
        /****************************************设置需要截取的图片****************************************/
        mBitmapPath = getIntent().getStringExtra(BITMAPPATH);
        if(!StringTools.isEmpty(mBitmapPath))
            mCropIv.setImageBitmap(BitmapFactory.decodeFile(mBitmapPath));
        else
        {
            Toast.makeText(this, "亲，无法对相关图片进行截取操作了！\n因为在指定路径无法获取相关图片哟！", Toast.LENGTH_SHORT).show();
            finish();
        }
        /*************************************设置截取框的宽高比，并保持不变********************************/
        mCropIv.setCropEnabled(true);
        mAspectRatio_x = getIntent().getIntExtra(ASPECTRATIO_X,1);
        mAspectRatio_y = getIntent().getIntExtra(ASPECTRATIO_Y, 1);
        mCropIv.setCustomRatio(mAspectRatio_x, mAspectRatio_y);
        mCropIv.setMinFrameSizeInDp(30);
        mCropIv.setHandleSizeInDp(12);
        mCropIv.setTouchPaddingInDp(18);
        mCropIv.setFrameStrokeWeightInDp(2);
        mCropIv.setGuideStrokeWeightInDp(1);
        mCropIv.setInitialFrameScale(0.5f);
        mCropIv.setHandleShowMode(PhotoCropperImageView.ShowMode.SHOW_ALWAYS);
        mCropIv.setGuideShowMode(PhotoCropperImageView.ShowMode.SHOW_ON_TOUCH);
        mCropIv.setFrameColor(getResources().getColor(R.color.white));
        mCropIv.setGuideColor(getResources().getColor(R.color.white));
        mCropIv.setBackgroundColor(getResources().getColor(R.color.transparent));
        mCropIv.setHandleColor(getIntent().getIntExtra(TITLECOLOR, R.color.white));
        mCropIv.setOverlayColor(getResources().getColor(R.color.cropperactivity_blacktransparent));
        mIsRoundCropper = getIntent().getBooleanExtra(ROUNDCROPPER, false);
        if(mIsRoundCropper)
            mCropIv.setCropMode(PhotoCropperImageView.CropMode.CIRCLE);
        else
            mCropIv.setCropMode(PhotoCropperImageView.CropMode.RATIO_CUSTOM);
        mBackTv.setOnClickListener(this);
        mCompleteTv.setOnClickListener(this);
    }

    /*********************具体操作*********************/
    public void onClick(View v)
    {
        /*******取消裁剪图片******/
        if(v == mBackTv)
        {
            Intent intent = new Intent();
            intent.putExtra(BITMAPPATH, "");
            setResult(0,intent);
            finish();
        }
        /*****裁剪图片的过程*****/
        else if(v == mCompleteTv)
        {
            try
            {
                Bitmap cropBitmap = mCropIv. getCroppedBitmap();
                File cropBitmapFile = new File(mBitmapPath);
                BufferedOutputStream outputStream =  new BufferedOutputStream(new FileOutputStream(cropBitmapFile));
                cropBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                outputStream.flush();
                outputStream.close();
                Intent intent = new Intent();
                intent.putExtra(BITMAPPATH, mBitmapPath);
                setResult(0,intent);
                finish();
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void onBackPressed()
    {
        Intent intent = new Intent();
        intent.putExtra(BITMAPPATH, "");
        setResult(0,intent);
        finish();
    }
}
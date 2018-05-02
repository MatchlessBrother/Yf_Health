package com.yuan.devlibrary._2Activity;

import java.io.File;
import java.util.Date;
import android.net.Uri;
import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import io.reactivex.Single;
import android.view.Window;
import java.util.ArrayList;
import java.io.IOException;
import java.util.LinkedList;
import android.view.Gravity;
import com.yuan.devlibrary.R;
import android.content.Intent;
import android.graphics.Bitmap;
import android.app.AlertDialog;
import android.graphics.Matrix;
import android.widget.TextView;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import io.reactivex.SingleSource;
import java.text.SimpleDateFormat;
import android.view.WindowManager;
import android.view.LayoutInflater;
import android.util.DisplayMetrics;
import android.provider.MediaStore;
import android.media.ExifInterface;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import android.content.ComponentName;
import android.graphics.BitmapFactory;
import me.iwf.photopicker.PhotoPicker;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import android.content.pm.PackageManager;
import io.reactivex.schedulers.Schedulers;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import com.yuan.devlibrary._12_______Utils.MemoryTools;
import com.yuan.devlibrary._12_______Utils.StringTools;
import io.reactivex.android.schedulers.AndroidSchedulers;
import com.yuan.devlibrary._12_______Utils.PromptBoxTools;

public abstract class BasePhotoActivity extends BaseActivity
{
    /***********新图片的手机根路径***********/
    private String mCachePath;
    /************新图片的手机路径************/
    private String mImgFilePath;
    /********对图片采用的像素压缩类型********/
    private Integer mCompressPixelStyle;
    /******从图库中获取图片时限定的数量******/
    private Integer mChooseBitmapMaxSize;
    /*****对图片进行质量压缩时限定的大小*****/
    private Double mResultBitmapQualitySize;
    /******裁剪图片页面标题栏背景颜色值******/
    private Integer mTitleBgColor;
    /******裁剪图片页面标题栏字体颜色值******/
    private Integer mTitleTextColor;
    /******裁剪图片页面裁剪框是否是圆形******/
    private Boolean mIsRoundCropper;

    /****************************获取相机权限的RequestCode值**************************/
    private static final int REQUEST_CODE_PERMISSION_CAMER                   = 0x0001;
    /******************获取访问手机外置内存权限的RequestCode值（相机）****************/
    private static final int REQUEST_CODE_PERMISSION_CAMER_EXTERNALSTORAGE   = 0x0002;
    /******************获取访问手机外置内存权限的RequestCode值（相册）****************/
    private static final int REQUEST_CODE_PERMISSION_GALLERY_EXTERNALSTORAGE = 0x0003;
    /**************************启动相机获取图片的RequestCode值************************/
    private static final int REQUEST_CODE_IMAGE_CAMERA                       = 0x0004;
    /**************************从图库中获取图片的RequestCode值************************/
    private static final int REQUEST_CODE_IMAGE_GALLERY                      = 0x0005;
    /*************************在裁剪之后获取图片的RequestCode值***********************/
    private static final int REQUEST_CODE_IMAGE_CROPPER                      = 0x0006;

    /*****************************************初始化基本数据***************************************/
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mIsRoundCropper = false;
        mCompressPixelStyle = 1;
        mChooseBitmapMaxSize = 1;
        mResultBitmapQualitySize = 60d;
        mTitleTextColor = R.color.white;
        mTitleBgColor = R.color.cropperactivity_titlebg;
        mCachePath =  MemoryTools.getBestFilesPath(this) + File.separator + "photos";
        File PathFile = new File(mCachePath);
        if(!PathFile.exists()) PathFile.mkdirs();
    }

    /*************************************显示获取图片的方式选择框**********************************
     ***************************textColorRes：描述获取图片方式文字的颜色值**************************
     ***************************isHeadImg：获取的图片是否充当头像文件使用***************************
     **********************ChooseBitmapMaxSize：从图库中获取图片的最大数量限定**********************
     ***************************isRoundCropper：裁剪图片时是否进行圆形裁剪**************************
     **********************titleTextColorRes：图片截取页面标题栏的字体颜色值************************
     ****************titleBgColorRes：图片截取页面，标题栏与裁剪框按钮的背景颜色值*****************/
    public void showSelectPhotoDialog(Float textSize,Integer textSizeType,Integer textColorRes,final Boolean isHeadImg,final Integer ChooseBitmapMaxSize,final Boolean isRoundCropper,final Integer titleTextColorRes,final Integer titleBgColorRes)
    {
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.show();

        View view = LayoutInflater.from(this).inflate(R.layout.inflater_selectphotodialog,null);
        alertDialog.setContentView(view);
        TextView cameraTv = (TextView)view.findViewById(R.id.selectphotodialog_camera);
        TextView galleryTv = (TextView)view.findViewById(R.id.selectphotodialog_gallery);
        TextView cancelTv = (TextView)view.findViewById(R.id.selectphotodialog_cancel);
        cameraTv.setTextColor(textColorRes);
        cameraTv.setTextSize(textSizeType,textSize);
        galleryTv.setTextColor(textColorRes);
        galleryTv.setTextSize(textSizeType,textSize);
        cancelTv.setTextColor(textColorRes);
        cancelTv.setTextSize(textSizeType,textSize);
        cameraTv.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                mTitleTextColor = titleTextColorRes;
                mIsRoundCropper = isRoundCropper;
                mTitleBgColor = titleBgColorRes;
                startCamera(isHeadImg);
                alertDialog.dismiss();
            }
        });
        galleryTv.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                mTitleTextColor = titleTextColorRes;
                mIsRoundCropper = isRoundCropper;
                mTitleBgColor = titleBgColorRes;
                startGallery(isHeadImg,ChooseBitmapMaxSize);
                alertDialog.dismiss();
            }
        });
        cancelTv.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                alertDialog.dismiss();
            }
        });

        Window window = alertDialog.getWindow();
        window.getDecorView().setPadding(0,0,0,0);
        window.setWindowAnimations(R.style.BottomOpenDialogAnim);
        window.getDecorView().setBackgroundResource(R.color.transparent);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = displayMetrics.widthPixels;
        params.height = alertDialog.getWindow().getAttributes().height;
        params.gravity = Gravity.BOTTOM;
        window.setAttributes(params);
    }

    /***********启动照相机获取图片***********/
    public void startCamera(Boolean isHeadImg)
    {
        PackageManager packageManager = getPackageManager();
        /****************判定手机是否含有可用的摄像头********************/
        if (packageManager.hasSystemFeature(packageManager.FEATURE_CAMERA))
        {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            ComponentName componentName = intent.resolveActivity(packageManager);
            /**************判定手机是否含有摄像头驱动软件***************/
            if(componentName != null)
            {
                File PathFile = new File(mCachePath);
                if(!PathFile.exists()) PathFile.mkdirs();

                mImgFilePath = null;
                if(isHeadImg)
                    mImgFilePath = mCachePath + File.separator + "headImg.jpg";
                else
                    mImgFilePath = mCachePath + File.separator + new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date()) + ".jpg";
                File imageFile = new File(mImgFilePath);
                /*************判定手机是否可以创建图片文件************/
                if(imageFile != null)
                {
                    /********判断应用是否正确获取了照相的权限********/
                    int cameraPermissionState = ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA);
                    if(cameraPermissionState == PackageManager.PERMISSION_GRANTED)
                    {
                        /**判断应用是否正确获取了读写外置内存的权限**/
                        int readStoragePermissionState = ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE);
                        if(readStoragePermissionState == PackageManager.PERMISSION_GRANTED)
                        {
                            /***************启动照相机****************/
                            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                            {
                                intent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(this,getPackageName()+".provider",imageFile));
                                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                            }
                            else
                            {
                                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imageFile));
                            }
                            startActivityForResult(intent, REQUEST_CODE_IMAGE_CAMERA);
                        }
                        else
                        {
                            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE))
                                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_CODE_PERMISSION_CAMER_EXTERNALSTORAGE);
                            else
                               PromptBoxTools.showPermissionDialog(this,"亲，当前操作行为缺少读写系统内存的权限哟！请进入系统设置页面后查看并修改当前应用的相关权限后再继续使用，谢谢！","去设置",null,null);
                        }
                    }
                    else
                    {
                       if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CAMERA))
                            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},REQUEST_CODE_PERMISSION_CAMER);
                       else
                           PromptBoxTools.showPermissionDialog(this,"亲，当前操作行为缺少使用相机的权限哟！请进入系统设置页面后查看并修改当前应用的相关权限后再继续使用，谢谢！","去设置",null,null);
                    }
                }
                else
                    PromptBoxTools.showToast(this,"亲，启动照相机失败了！\n因为无法创建图片文件哟！");
            }
            else
                PromptBoxTools.showToast(this,"亲，启动照相机失败了！\n因为摄像头缺少驱动哟！");
        }
        else
            PromptBoxTools.showToast(this,"亲，启动照相机失败了！\n因为无可用的摄像头哟！");
    }

    /************启动图库获取图片*************/
    public void startGallery(Boolean isHeadImg,Integer ChooseBitmapMaxSize)
    {
        mImgFilePath = null;
        File PathFile = new File(mCachePath);
        if(!PathFile.exists()) PathFile.mkdirs();

        if(isHeadImg)
        {
            mChooseBitmapMaxSize = 1;
            mImgFilePath = mCachePath + File.separator + "headImg.jpg";
        }
        else
        {
            mChooseBitmapMaxSize = ChooseBitmapMaxSize;
            mImgFilePath = mCachePath + File.separator + new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date()) + ".jpg";
        }
        File imageFile = new File(mImgFilePath);
        if(imageFile != null)
        {
            /**判断应用是否正确获取了读写外置内存的权限**/
            int readStoragePermissionState = ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE);
            if(readStoragePermissionState == PackageManager.PERMISSION_GRANTED)
            {
                /*********************************************************************启动图库选择图片**********************************************************************/
                PhotoPicker.builder().setPhotoCount(mChooseBitmapMaxSize).setShowCamera(true).setShowGif(true).setPreviewEnabled(false).start(this,REQUEST_CODE_IMAGE_GALLERY);
            }
            else
            {
                if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE))
                    ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_CODE_PERMISSION_GALLERY_EXTERNALSTORAGE);
                else
                    PromptBoxTools.showPermissionDialog(this,"亲，当前操作行为缺少读写系统内存的权限哟！请进入系统设置页面后查看并修改当前应用的相关权限后再继续使用，谢谢！","去设置",null,null);
            }
        }
        else
            PromptBoxTools.showToast(this,"亲，启动图库选择图片失败了！\n因为无法创建相关图片文件哟！");
    }

    /******************动态申请应用权限的回调函数******************/
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(REQUEST_CODE_PERMISSION_CAMER == requestCode && null != grantResults && grantResults.length > 0 && PackageManager.PERMISSION_GRANTED == grantResults[0])
        {
            /**判断应用是否正确获取了读写外置内存的权限**/
            int readStoragePermissionState = ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE);
            if(readStoragePermissionState == PackageManager.PERMISSION_GRANTED)
            {
                /************启动照相机*************/
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(mImgFilePath)));
                startActivityForResult(intent, REQUEST_CODE_IMAGE_CAMERA);
            }
            else
            {
                if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE))
                    ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_CODE_PERMISSION_CAMER_EXTERNALSTORAGE);
                else
                    PromptBoxTools.showPermissionDialog(this,"亲，当前操作行为缺少读写系统内存的权限哟！请进入系统设置页面后查看并修改当前应用的相关权限后再继续使用，谢谢！","去设置",null,null);
            }
        }
        else if(REQUEST_CODE_PERMISSION_CAMER_EXTERNALSTORAGE == requestCode && null != grantResults && grantResults.length > 0 && PackageManager.PERMISSION_GRANTED == grantResults[0])
        {
            /**************启动照相机***************/
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(mImgFilePath)));
            startActivityForResult(intent, REQUEST_CODE_IMAGE_CAMERA);
        }
        else if(REQUEST_CODE_PERMISSION_GALLERY_EXTERNALSTORAGE == requestCode && null != grantResults && grantResults.length > 0 && PackageManager.PERMISSION_GRANTED == grantResults[0])
        {
            /*********************************************************************启动图库选择图片**********************************************************************/
            PhotoPicker.builder().setPhotoCount(mChooseBitmapMaxSize).setShowCamera(true).setShowGif(true).setPreviewEnabled(false).start(this,REQUEST_CODE_IMAGE_GALLERY);
        }
    }

    /****************获取指定路径的图片大小,先宽后长***************/
    private int[] getImageSize(String imagePath)
    {
        int[] imageSize = new int[2];
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inSampleSize = 1;
        BitmapFactory.decodeFile(imagePath, options);
        imageSize[0] = options.outWidth;
        imageSize[1] = options.outHeight;
        return imageSize;
    }

    /****************设置压缩图片像素时采用的压缩方式**************/
    public void setCompressPixelStyle(Integer compressPixelStyle)
    {
        mCompressPixelStyle = compressPixelStyle;
    }

    /******************获取指定路径图片的旋转角度******************/
    private int getImageRotatedDegree(String bitmapFilePath)
    {
        int degree = 0;
        try
        {
            ExifInterface exifInterface = new ExifInterface(bitmapFilePath);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,ExifInterface.ORIENTATION_NORMAL);
            switch (orientation)
            {
                case ExifInterface.ORIENTATION_ROTATE_90:degree = 90;break;
                case ExifInterface.ORIENTATION_ROTATE_180:degree = 180;break;
                case ExifInterface.ORIENTATION_ROTATE_270:degree = 270;break;
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return degree;
        }
        return degree;
    }

    /******************校正指定路径图片的旋转角度******************/
    private Bitmap setImageRotatedDegree(Bitmap bitmap,int degreen)
    {
        if(null == bitmap || degreen == 0)
            return bitmap;

        Matrix matrix = new Matrix();
        matrix.postRotate(degreen);
        Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        if (null != bitmap)
            bitmap.recycle();
        return newBitmap;
    }

    /**对指定路径的图片进行像素压缩,防止读取图片时引发内存OOM异常**/
    private String compressImagePixel_AllAcion(String bitmapFilePath)
    {
        File bitmapFile = new File(bitmapFilePath);
        if(bitmapFile.exists())
        {
            Bitmap bitmap = compressImagePixel_ChooseCompressStyle(mCompressPixelStyle,bitmapFilePath);
            int degree = getImageRotatedDegree(bitmapFilePath);
            if(bitmap != null)
                bitmap = setImageRotatedDegree(bitmap,degree);
            else
            {
                PromptBoxTools.showToast(this,"亲，无法获取相关图片！图片可能已经损坏了哟！");
                return null;
            }
            try
            {
                /**********用相机拍照获取图片作为头像或另做他用的情况,以及从图库中选取最近使用的头像图片作为头像的情况**********/
                if(bitmapFilePath.equals(mImgFilePath) && bitmapFilePath == mImgFilePath)
                {
                    bitmapFilePath = mImgFilePath;
                    File newBitmapFile = new File(mImgFilePath);
                    BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(newBitmapFile));
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                    outputStream.flush();
                    outputStream.close();
                    return bitmapFilePath;
                }
                else
                {
                    /*****************从图库中选取图片作为头像，排除从图库中选取最近使用的头像图片作为头像的情况*****************/
                    if(mImgFilePath.contains("headImg"))
                    {
                        bitmapFilePath = mImgFilePath;
                        File newBitmapFile = new File(mImgFilePath);
                        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(newBitmapFile));
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                        outputStream.flush();
                        outputStream.close();
                        return bitmapFilePath;
                    }

                    /**从图库中选取以headImg字符串命名的图片去另做他用的情况，包括从图库中选取最近使用的头像图片去另做他用的情况**/
                    else if(bitmapFilePath.substring(bitmapFilePath.lastIndexOf("/")).contains("headImg"))
                    {
                        bitmapFilePath = mImgFilePath;
                        File newBitmapFile = new File(mImgFilePath);
                        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(newBitmapFile));
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                        outputStream.flush();
                        outputStream.close();
                        return bitmapFilePath;
                    }

                    /**************************************从图库选取其余图片去另做他用的情况*************************************/
                    else
                    {
                        bitmapFilePath = mImgFilePath;
                        File newBitmapFile = new File(mImgFilePath);
                        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(newBitmapFile));
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                        outputStream.flush();
                        outputStream.close();
                        return bitmapFilePath;
                    }
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
            PromptBoxTools.showToast(this,"亲，对图片进行的所有压缩操作失败了！因为在指定路径没有找到相关的图片哟！");
        return null;
    }

    /**根据mCompressPixelStyle的值来确定对图片采用的像素压缩方式***/
    private Bitmap compressImagePixel_ChooseCompressStyle(int compressImagePixelStyle,String bitmapFilePath)
    {
        if(compressImagePixelStyle == 1)
            return compressImagePixel_CalculationFirst(bitmapFilePath);
        else
            return compressImagePixel_CalculationSecond(bitmapFilePath);
    }

    /**对需要像素压缩的图片进行计算,得到其长宽,以及像素缩放比,最终的质量压缩值等等,以便做到精确压缩*/
    private Bitmap compressImagePixel_CalculationFirst(String bitmapFilePath)
    {
        mResultBitmapQualitySize = 80d;
        File bitmapFile = new File(bitmapFilePath);
        int width = getImageSize(bitmapFilePath)[0];
        int height = getImageSize(bitmapFilePath)[1];
        int thumbW = width % 2 == 1 ? width + 1 : width;
        int thumbH = height % 2 == 1 ? height + 1 : height;
        width = thumbW > thumbH ? thumbH : thumbW;
        height = thumbW > thumbH ? thumbW : thumbH;
        double scale = ((double) width / height);

        if (scale <= 1 && scale > 0.5625)
        {
            if (height < 1664)
            {
                if (bitmapFile.length() / 1024 < 150) return  BitmapFactory.decodeFile(bitmapFilePath);
                mResultBitmapQualitySize = (width * height) / Math.pow(1664, 2) * 150;
                mResultBitmapQualitySize = mResultBitmapQualitySize < 60 ? 60 : mResultBitmapQualitySize;
            }
            else if (height >= 1664 && height < 4990)
            {
                thumbW = width / 2;
                thumbH = height / 2;
                mResultBitmapQualitySize = (thumbW * thumbH) / Math.pow(2495, 2) * 300;
                mResultBitmapQualitySize = mResultBitmapQualitySize < 60 ? 60 : mResultBitmapQualitySize;
            }
            else if (height >= 4990 && height < 10240)
            {
                thumbW = width / 4;
                thumbH = height / 4;
                mResultBitmapQualitySize = (thumbW * thumbH) / Math.pow(2560, 2) * 300;
                mResultBitmapQualitySize = mResultBitmapQualitySize < 100 ? 100 : mResultBitmapQualitySize;
            }
            else
            {
                int multiple = height / 1280 == 0 ? 1 : height / 1280;
                thumbW = width / multiple;
                thumbH = height / multiple;
                mResultBitmapQualitySize = (thumbW * thumbH) / Math.pow(2560, 2) * 300;
                mResultBitmapQualitySize = mResultBitmapQualitySize < 100 ? 100 : mResultBitmapQualitySize;
            }
        }
        else if (scale <= 0.5625 && scale > 0.5)
        {
            if (height < 1280 && bitmapFile.length() / 1024 < 200)  return  BitmapFactory.decodeFile(bitmapFilePath);
            int multiple = height / 1280 == 0 ? 1 : height / 1280;
            thumbW = width / multiple;
            thumbH = height / multiple;
            mResultBitmapQualitySize = (thumbW * thumbH) / (1440.0 * 2560.0) * 400;
            mResultBitmapQualitySize = mResultBitmapQualitySize < 100 ? 100 : mResultBitmapQualitySize;
        }
        else
        {
            int multiple = (int) Math.ceil(height / (1280.0 / scale));
            thumbW = width / multiple;
            thumbH = height / multiple;
            mResultBitmapQualitySize = ((thumbW * thumbH) / (1280.0 * (1280 / scale))) * 500;
            mResultBitmapQualitySize = mResultBitmapQualitySize < 100 ? 100 : mResultBitmapQualitySize;
        }
        return compressImagePixel_Complete(bitmapFilePath,thumbW,thumbH);
    }

    /**对需要像素压缩的图片进行计算,得到其长宽,以及像素缩放比,最终的质量压缩值等等,以便做到精确压缩*/
    private Bitmap compressImagePixel_CalculationSecond(String bitmapFilePath)
    {
        int shortSide = 720;
        int longSide = 1280;
        Double minSize = 60d;
        Double maxSize = 120d;
        mResultBitmapQualitySize = 80d;

        int[] imgSize = getImageSize(bitmapFilePath);
        int width = 0, height = 0;
        if (imgSize[0] <= imgSize[1])
        {
            double scale = (double) imgSize[0] / (double) imgSize[1];
            if (scale <= 1.0 && scale > 0.5625)
            {
                width = imgSize[0] > longSide ? longSide : imgSize[0];
                height = width * imgSize[1] / imgSize[0];
                mResultBitmapQualitySize = minSize;
            }
            else if (scale <= 0.5625)
            {
                height = imgSize[1] > shortSide ? shortSide : imgSize[1];
                width = height * imgSize[0] / imgSize[1];
                mResultBitmapQualitySize = maxSize;
            }
        }
        else
        {
            double scale = (double) imgSize[1] / (double) imgSize[0];
            if (scale <= 1.0 && scale > 0.5625)
            {
                height = imgSize[1] > longSide ? longSide : imgSize[1];
                width = height * imgSize[0] / imgSize[1];
                mResultBitmapQualitySize = minSize;
            }
            else if (scale <= 0.5625)
            {
                width = imgSize[0] > shortSide ? shortSide : imgSize[0];
                height = width * imgSize[1] / imgSize[0];
                mResultBitmapQualitySize = maxSize;
            }
        }
        return compressImagePixel_Complete(bitmapFilePath,width, height);
    }

    /**根据之前计算出图片的长宽值以及原本图片的长宽值得出合适的缩放比例,防止在内存中加载图片时发生OOM异常**/
    private Bitmap compressImagePixel_Complete(String bitmapFilePath, int width, int height)
    {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(bitmapFilePath, options);
        int inSampleSize = 1;
        int outW = options.outWidth;
        int outH = options.outHeight;
        if (outW > width || outH > height)
        {
            int halfW = outW / 2;
            int halfH = outH / 2;
            while ((halfW / inSampleSize) > width && (halfH / inSampleSize) > height)
            {
                inSampleSize *= 2;
            }
        }
        int heightRatio = (int) Math.ceil(options.outHeight / (float) height);
        int widthRatio = (int) Math.ceil(options.outWidth / (float) width);
        if (widthRatio > 1 || heightRatio > 1)
        {
            if (heightRatio > widthRatio)
                options.inSampleSize = heightRatio;
            else
                options.inSampleSize = widthRatio;
        }
        else
            options.inSampleSize = inSampleSize;
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(bitmapFilePath, options);
    }

    /********************对指定路径的图片进行质量压缩，其大小必须在MaxSize(KB)以内*************************/
    private String compressImageQuality_Complete(String bitmapFilePath,int maxSize)
    {
        File bitmapFile = new File(bitmapFilePath);
        if(bitmapFile.exists())
        {
            int imageQuality = 100;
            Bitmap bitmap = BitmapFactory.decodeFile(bitmapFilePath);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            while (outputStream.toByteArray().length > 1024 * maxSize && imageQuality > 5)
            {
                imageQuality -= 5;
                outputStream.reset();
                bitmap.compress(Bitmap.CompressFormat.JPEG, imageQuality, outputStream);
            }
            try
            {
                FileOutputStream fileOutputStream = new FileOutputStream(bitmapFile);
                fileOutputStream.write(outputStream.toByteArray());
                fileOutputStream.flush();
                fileOutputStream.close();
                return bitmapFilePath;
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
        PromptBoxTools.showToast(this,"亲，对图片进行的质量压缩操作失败了！因为在指定路径没有找到相关的图片哟！");
        return null;
    }

    /*************************************获取图片以及裁剪图片的处理过程***********************************/
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_IMAGE_CAMERA)
        {
            final String imgPath = compressImagePixel_AllAcion(mImgFilePath);
            if(!StringTools.isEmpty(imgPath))
            {
                Intent intent = new Intent(this,PhotoCropperActivity.class);
                intent.putExtra(PhotoCropperActivity.TITLECOLOR,mTitleBgColor);
                intent.putExtra(PhotoCropperActivity.BITMAPPATH,imgPath);
                intent.putExtra(PhotoCropperActivity.ROUNDCROPPER,mIsRoundCropper);
                intent.putExtra(PhotoCropperActivity.TITLETEXTCOLOR,mTitleTextColor);
                startActivityForResult(intent,REQUEST_CODE_IMAGE_CROPPER);
            }
            else
                PromptBoxTools.showToast(this,"亲，获取拍照的图片失败了！\n因为在指定路径无法查找到图片哟！");
        }
        else if(resultCode == RESULT_OK && requestCode == REQUEST_CODE_IMAGE_GALLERY && data != null)
        {
            final ArrayList<String> imgList = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
            if(imgList.size() == 1)
            {
                String imgPath = imgList.get(0);
                if(!StringTools.isEmpty(imgPath))
                {
                    File bitmapFile = new File(imgPath);
                    /****为了不破坏原有图片,我们生成新图片来进行操作****/
                    /********超过120KB的图片需要进行像素压缩处理********/
                    if(bitmapFile.length() >= 120 * 1024)
                        imgPath = compressImagePixel_AllAcion(imgPath);
                    /*****不超过120的图片则不需要进行像素压缩处理*****/
                    else
                    {
                        try
                        {
                            mResultBitmapQualitySize = 100d;
                            FileInputStream fileInputStream = new FileInputStream(bitmapFile);
                            byte[] fileInputStreamSz = new byte[(int)bitmapFile.length()];
                            fileInputStream.read(fileInputStreamSz);
                            File newBitmapFile = new File(mImgFilePath);
                            BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(newBitmapFile));
                            outputStream.write(fileInputStreamSz);
                            outputStream.flush();
                            outputStream.close();
                            imgPath = mImgFilePath;
                        }
                        catch (FileNotFoundException e) {e.printStackTrace();}
                        catch(Exception e) {e.printStackTrace();}
                    }
                    Intent intent = new Intent(this,PhotoCropperActivity.class);
                    intent.putExtra(PhotoCropperActivity.TITLECOLOR,mTitleBgColor);
                    intent.putExtra(PhotoCropperActivity.BITMAPPATH,imgPath);
                    intent.putExtra(PhotoCropperActivity.ROUNDCROPPER,mIsRoundCropper);
                    intent.putExtra(PhotoCropperActivity.TITLETEXTCOLOR,mTitleTextColor);
                    startActivityForResult(intent, REQUEST_CODE_IMAGE_CROPPER);
                }
                else
                    PromptBoxTools.showToast(this,"亲，获取选定的图片失败了！\n因为在指定路径无法查找到图片哟！");
            }
            else
            {
                Single.just("only").flatMap(new Function<String, SingleSource<LinkedList<String>>>()
                {
                    public SingleSource<LinkedList<String>> apply(@io.reactivex.annotations.NonNull String s) throws Exception
                    {
                        for(int index = 0;index<imgList.size();index++)
                        {
                            String imgPath = imgList.get(index);
                            File bitmapFile = new File(imgPath);
                            /*********为了不破坏原有图片,我们尽量生成新图片来进行操作********/
                            /********超过120KB的图片需要进行像素压缩以及质量压缩的处理*******/
                            mImgFilePath = mCachePath + File.separator + new SimpleDateFormat("yyyy_MM_dd_HH_mm").format(new Date()) + "_" + index + ".jpg";
                            if(bitmapFile.length() >= 120 * 1024)
                                imgPath = compressImageQuality_Complete(compressImagePixel_AllAcion(imgPath),(int)mResultBitmapQualitySize.doubleValue());
                            /***不超过120KB的图片我们则直接使用原图片，避免消耗多余的空间***/
                            else imgPath = imgPath;
                            /**至此对于图片必须的像素压缩完成*/
                            imgList.set(index,imgPath);
                        }
                        LinkedList<String> dataList = new LinkedList<>();dataList.addAll(imgList);
                        return Single.just(dataList);
                    }
                }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<LinkedList<String>>()
                {
                    public void accept(@io.reactivex.annotations.NonNull LinkedList<String> dataList) throws Exception
                    {
                        setOnNewImgPathListener(dataList);
                    }
                });
            }
        }
        else if(requestCode == REQUEST_CODE_IMAGE_CROPPER && data != null)
        {
            String cropperBitmapPath = data.getStringExtra(PhotoCropperActivity.BITMAPPATH);
            File bitmapFile = new File(cropperBitmapPath);
            if(!StringTools.isEmpty(cropperBitmapPath) && bitmapFile.exists())
            {
                /****************************************超过120KB的图片需要进行质量压缩处理****************************************/
                if(bitmapFile.length() >= 120 * 1024)
                    cropperBitmapPath = compressImageQuality_Complete(cropperBitmapPath,(int)mResultBitmapQualitySize.doubleValue());
                LinkedList<String> dataList = new LinkedList<>();dataList.add(cropperBitmapPath);
                setOnNewImgPathListener(dataList);
            }
            else
                PromptBoxTools.showToast(this,"亲，手机发生异常情况导致裁剪\n图片失败了哟！请您稍后重试！");
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /************************通过此回调函数:把新获取的照片路径返回给相关的Activity*************************/
    public abstract void setOnNewImgPathListener(LinkedList<String> bitmapPaths);
}
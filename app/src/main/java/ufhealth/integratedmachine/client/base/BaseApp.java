package ufhealth.integratedmachine.client.base;

import java.io.IOException;
import android.graphics.Color;
import android.os.Environment;
import android.text.TextUtils;
import android.content.Context;
import com.hwangjr.rxbus.RxBus;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.netease.nimlib.sdk.NIMClient;
import android.support.multidex.MultiDex;

import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.SDKOptions;
import ufhealth.integratedmachine.client.R;

import com.netease.nimlib.sdk.StatusCode;
import com.netease.nimlib.sdk.auth.AuthServiceObserver;
import com.netease.nimlib.sdk.util.NIMUtil;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.yuan.devlibrary._1App.BaseApplication;
import com.netease.nimlib.sdk.uinfo.UserInfoProvider;
import com.yuan.devlibrary._1App.BaseUiAdapterHelper;
import com.netease.nimlib.sdk.StatusBarNotificationConfig;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.yuan.devlibrary._12_______Utils.ScreenInfosTools;
import ufhealth.integratedmachine.client.util.CountDownUtil;
import ufhealth.integratedmachine.client.bean.main.UserInfo.*;
import ufhealth.integratedmachine.client.ui.main.view.MyBillsAct;

public class BaseApp extends BaseApplication
{
    private BaseApp mBaseApp;
    private Boolean mIsLogged;

    private Boolean mImIsLogined;
    private UserInfoBean mUserInfo;
    private ImUserInfo mImUserInfo;
    private LoginInfo mImLoginInfo;

    private CountDownUtil mCountDownUtil;
    private BaseUiAdapterHelper mUiHelper;
    private static final Integer COUNTDOWN_TIME = 3600;//以秒为单位

    protected void attachBaseContext(Context base)
    {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public void onCreate()
    {
        super.onCreate();
        mBaseApp  = this;
        mUiHelper = BaseUiAdapterHelper.getInstances(mBaseApp,1920,1080);
        mUiHelper.performSchemeForApp();
        /***************************************计时器操作*****************************************/
        mIsLogged = false;
        mCountDownUtil = new CountDownUtil(COUNTDOWN_TIME)
        {
            public void onFinish()
            {
                mCountDownUtil.cancel();
                RxBus.get().post(true);
                mCountDownUtil.setmMillisInFuture(COUNTDOWN_TIME);
            }

            public void onTick(long millisUntilFinished)
            {
                RxBus.get().post(millisUntilFinished);
            }
        };

        // SDK初始化（启动后台服务，若已经存在用户登录信息,SDK将完成自动登录）
        NIMClient.init(this,loginInfo(), options());
        if (NIMUtil.isMainProcess(this))
        {
            // 注意：以下操作必须在主进程中进行
            // 1、UI相关初始化操作
            // 2、相关Service调用
        }
    }

    public Boolean getIsLogged()
    {
        return mIsLogged;

    }

    public void setCountDownTime()
    {
        mCountDownUtil.setmMillisInFuture(COUNTDOWN_TIME);

    }

    public void setCountDownTime(Long time)
    {
        mCountDownUtil.setmMillisInFuture(null != time ? time : 0);
    }

    public Boolean getImIsLogined()
    {
        return this.mImIsLogined;

    }

    public void setImIsLogined(Boolean mImIsLogined)
    {
        this.mImIsLogined = mImIsLogined;

    }

    public void setIsLogged(Boolean isLogged)
    {
        mIsLogged = isLogged;

    }

    public ImUserInfo getImUserInfo()
    {
        return mImUserInfo;

    }

    public void setImUserInfo(ImUserInfo imUserInfo)
    {
        mImUserInfo = imUserInfo;

    }

    public UserInfoBean getUserInfo()
    {
        return mUserInfo;

    }

    public void setUserInfo(UserInfoBean userInfo)
    {
        mUserInfo = userInfo;

    }

    public void startCountDown()
    {
        mCountDownUtil.start();
    }

    public void cancelCountDown()
    {
        mCountDownUtil.cancel();
    }

    // 如果返回值为 null，则全部使用默认参数。
    private SDKOptions options()
    {
        SDKOptions options = new SDKOptions();
        // 如果将新消息通知提醒托管给 SDK 完成，需要添加以下配置。否则无需设置。
        StatusBarNotificationConfig config = new StatusBarNotificationConfig();
        config.notificationEntrance = MyBillsAct.class; // 点击通知栏跳转到该Activity
        config.notificationSmallIconId = R.mipmap.ic_launcher_round;
        // 呼吸灯配置
        config.ledARGB = Color.GREEN;
        config.ledOnMs = 1000;
        config.ledOffMs = 1500;
        // 通知铃声的uri字符串
        config.notificationSound = "android.resource://com.netease.nim.demo/raw/msg";
        options.statusBarNotificationConfig = config;

        // 配置保存图片，文件，log 等数据的目录
        // 如果 options 中没有设置这个值，SDK 会使用采用默认路径作为 SDK 的数据目录。
        // 该目录目前包含 log, file, image, audio, video, thumb 这6个目录。
        String sdkPath = getAppCacheDir(this) + "/nim"; // 可以不设置，那么将采用默认路径
        // 如果第三方 APP 需要缓存清理功能， 清理这个目录下面个子目录的内容即可。
        options.sdkStorageRootPath = sdkPath;
        // 配置是否需要预下载附件缩略图，默认为 true
        options.preloadAttach = false;
        // 配置附件缩略图的尺寸大小。表示向服务器请求缩略图文件的大小
        // 该值一般应根据屏幕尺寸来确定， 默认值为 Screen.width / 2
        options.thumbnailSize = ScreenInfosTools.getScreenWidth(this) / 2;
        // 用户资料提供者, 目前主要用于提供用户资料，用于新消息通知栏中显示消息来源的头像和昵称
        options.userInfoProvider = new UserInfoProvider()
        {
            public com.netease.nimlib.sdk.uinfo.model.UserInfo getUserInfo(String account)
            {
                return null;
            }

            public int getDefaultIconResId()
            {
                return R.mipmap.defaultheadimg;
            }

            public Bitmap getTeamIcon(String tid)
            {
                return null;
            }

            public Bitmap getAvatarForMessageNotifier(String account)
            {
                return null;
            }

            public String getDisplayNameForMessageNotifier(String account, String sessionId,SessionTypeEnum sessionType)
            {
                return null;
            }

            public Bitmap getAvatarForMessageNotifier(SessionTypeEnum sessionType, String sessionId)
            {
                return BitmapFactory.decodeResource(getResources(),R.mipmap.defaultheadimg);
            }
        };

        //异步初始化SDK，默认为 false，开启可降低 Application#onCreate 中 SDK 初始化函数的同步响应时间
        options.asyncInitSDK = true;
        //是否在SDK初始化时检查清单文件配置是否完全，默认为 false，建议开发者在调试阶段打开，上线时关掉
        options.checkManifestConfig = true;
        return options;
    }

    // 如果已经存在用户登录信息，返回LoginInfo，否则返回null即可
    private LoginInfo loginInfo()
    {
        return null;
    }

    /**配置 APP 保存图片/语音/文件/log等数据的目录
     *******这里示例用SD卡的应用扩展存储目录*****/
    static String getAppCacheDir(Context context)
    {
        String storageRootPath = null;
        try
        {
            // SD卡应用扩展存储区(APP卸载后，该目录下被清除，用户也可以在设置界面中手动清除)，请根据APP对数据缓存的重要性及生命周期来决定是否采用此缓存目录.
            // 该存储区在API 19以上不需要写权限，即可配置 <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" android:maxSdkVersion="18"/>
            if (context.getExternalCacheDir() != null)
            {
                storageRootPath = context.getExternalCacheDir().getCanonicalPath();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        if (TextUtils.isEmpty(storageRootPath))
        {
            // SD卡应用公共存储区(APP卸载后，该目录不会被清除，下载安装APP后，缓存数据依然可以被加载。SDK默认使用此目录)，该存储区域需要写权限!
            storageRootPath = Environment.getExternalStorageDirectory() + "/" + context.getPackageName();
        }
        return storageRootPath;
    }

    public LoginInfo getIMLoginInfo()
    {
        return mImLoginInfo;
    }

    public void setIMLoginInfo(LoginInfo imLoginInfo)
    {
        mImLoginInfo = imLoginInfo;
    }
}
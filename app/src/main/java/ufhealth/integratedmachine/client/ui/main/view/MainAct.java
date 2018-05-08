package ufhealth.integratedmachine.client.ui.main.view;

import android.util.Log;
import android.view.View;
import rx.functions.Func1;
import com.invs.InvsConst;
import com.invs.InvsIdCard;
import rx.functions.Action1;
import android.os.SystemClock;
import android.content.Intent;
import android.content.Context;
import android.widget.TextView;
import rx.schedulers.Schedulers;
import com.invs.IClientCallBack;
import android.net.wifi.WifiInfo;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.widget.RelativeLayout;
import static com.invs.InvsConst.msg;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.NIMClient;
import android.support.v7.widget.Toolbar;
import com.netease.nimlib.sdk.StatusCode;
import android.content.BroadcastReceiver;
import android.bluetooth.BluetoothAdapter;
import ufhealth.integratedmachine.client.R;
import android.support.v4.widget.DrawerLayout;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.netease.nimlib.sdk.auth.AuthService;
import rx.android.schedulers.AndroidSchedulers;
import android.support.v7.app.ActionBarDrawerToggle;
import de.hdodenhof.circleimageview.CircleImageView;
import ufhealth.integratedmachine.client.base.BaseAct;
import com.netease.nimlib.sdk.auth.AuthServiceObserver;
import ufhealth.integratedmachine.client.BtReaderClient;
import ufhealth.integratedmachine.client.bean.main.UserInfo;
import ufhealth.integratedmachine.client.ui.bjjy.view.BjjyAct;
import ufhealth.integratedmachine.client.ui.jkda.view.JkdaAct;
import ufhealth.integratedmachine.client.ui.jkjc.view.JkjcAct;
import ufhealth.integratedmachine.client.ui.zxzx.view.ZxzxAct;
import ufhealth.integratedmachine.client.ui.main.view_v.MainAct_V;
import ufhealth.integratedmachine.client.util.BindingCellPhoneDialog;
import com.yuan.devlibrary._11___Widget.promptBox.BaseProgressDialog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

import ufhealth.integratedmachine.client.ui.main.presenter.MainPresenter;

public class MainAct extends BaseAct implements MainAct_V,View.OnClickListener,IClientCallBack
{
    private TextView mainLogin;
    private RelativeLayout zxzx;
    private RelativeLayout bjjy;
    private RelativeLayout tjfw;
    private RelativeLayout yyfw;
    private RelativeLayout jkjc;
    private RelativeLayout jkda;
    private Toolbar mainToolbar;
    private ImageButton mainExit;
    private String BleMacAddress;
    private TextView mainCountdown;
    private TextView mainSlideName;
    private TextView mainUfLogoMac;
    private TextView mainSlideXxtzNum;
    private LinearLayout mainSlideGrzl;
    private LinearLayout mainSlideXxtz;
    private LinearLayout mainSlideWddd;
    private LinearLayout mainSlideWdda;
    private LinearLayout mainSlideGybj;
    private String deviceWifiMacAddress;
    private MainPresenter mainPresenter;
    private CircleImageView mainSlideImg;
    private DrawerLayout mainDrawerlayout;
    private BaseProgressDialog progressDialog;
    private BindingCellPhoneDialog cellPhoneDialog;

    /***********IM状态监听***********/
    private Observer imOnLineObserver;
    private IntentFilter intentFilter;
    private static boolean isStartReadIdCard;
    private static boolean isNeenConnectIdCard;
    /*********************身份证识别设备返回读取的数据*******************/
    private final BroadcastReceiver mBltReceiver = new BroadcastReceiver()
    {
        public void onReceive(Context context, Intent intent)
        {
            final String action = intent.getAction();
            if (msg.equals(action))
            {
                if (intent.getBooleanExtra("tag", false))
                {
                    InvsIdCard invsIdCard = (InvsIdCard) intent.getSerializableExtra("InvsIdCard");
                    if(null == getBaseApp().getUserInfo() && null != invsIdCard && null != invsIdCard.idNo && !"".equals(invsIdCard.idNo))
                    {
                        mainPresenter.logging(MainAct.this,invsIdCard.idNo.trim(),invsIdCard.name.trim(),invsIdCard.birth,(invsIdCard.sex.trim().equals("男") ? 1 : 2),invsIdCard.nation.trim(),invsIdCard.address.trim(),new byte[]{});
                    }
                }
                //读卡失败
                else{}
            }
        }
    };

    /****************更新已登录后的Ui界面**************/
    public void loggedUi(UserInfo.UserInfoBean userInfo)
    {
        mainExit.setVisibility(View.VISIBLE);
        mainToolbar.setVisibility(View.VISIBLE);
        mainCountdown.setVisibility(View.VISIBLE);
        mainLogin.setText("欢迎您，" + ((null != userInfo && null != userInfo.getName() && !"".equals(userInfo.getName().trim())) ?
                userInfo.getName().trim() : "未命名") + "（" + userInfo.getPapersNumber().trim() + "）");
        if(mainDrawerlayout.isShown())
            mainDrawerlayout.closeDrawers();
        mainDrawerlayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }

    /****************更新已登录后的逻辑****************/
    public void logged(UserInfo.UserInfoBean userInfo)
    {
        stopReadIdCard();
        loggedUi(userInfo);
        useGlideLoadImg(mainSlideImg,null != userInfo.getAvatar() ? userInfo.getAvatar().trim().toString() : "");
        mainSlideName.setText(null != userInfo.getName() ? userInfo.getName().trim().toString() : "无名氏");
    }

    protected void initWidgets(View rootView)
    {
        zxzx = rootView.findViewById(R.id.zxzx);
        bjjy = rootView.findViewById(R.id.bjjy);
        tjfw = rootView.findViewById(R.id.tjfw);
        yyfw = rootView.findViewById(R.id.yyfw);
        jkjc = rootView.findViewById(R.id.jkjc);
        jkda = rootView.findViewById(R.id.jkda);
        mainExit = rootView.findViewById(R.id.main_exit);
        mainLogin = rootView.findViewById(R.id.main_login);
        mainToolbar = rootView.findViewById(R.id.main_toolbar);
        mainCountdown = rootView.findViewById(R.id.main_countdown);
        mainSlideImg = rootView.findViewById(R.id.main_slide_img);
        mainUfLogoMac = rootView.findViewById(R.id.main_uflogomac);
        mainSlideName = rootView.findViewById(R.id.main_slide_name);
        mainSlideGrzl = rootView.findViewById(R.id.main_slide_grzl);
        mainSlideXxtz = rootView.findViewById(R.id.main_slide_xxtz);
        mainSlideWddd = rootView.findViewById(R.id.main_slide_wddd);
        mainSlideWdda = rootView.findViewById(R.id.main_slide_wdda);
        mainSlideGybj = rootView.findViewById(R.id.main_slide_gybj);
        mainDrawerlayout = rootView.findViewById(R.id.main_drawerlayout);
        mainSlideXxtzNum = rootView.findViewById(R.id.main_slide_xxtz_num);
        deviceWifiMacAddress = getAdresseMAC(this);
        mainUfLogoMac.setText("设备号：" + deviceWifiMacAddress.trim());

        /***************IM状态监听模块**************/
        imOnLineObserver = new Observer<StatusCode>()
        {
            //被踢出、账号被禁用、密码错误等情况，自动登录失败，需要返回到登录界面进行重新登录操作
            //有一个问题是当么有网络的情况下IM无法正常退出，导致账号处于异常在线。
            public void onEvent(StatusCode statusCode)
            {
                if (statusCode == StatusCode.LOGINED)
                {
                    getBaseApp().setImIsLogined(true);
                }
                else if(statusCode == StatusCode.UNLOGIN)
                {
                    getBaseApp().setImIsLogined(false);
                }
            }
        };
        NIMClient.getService(AuthServiceObserver.class).observeOnlineStatus(imOnLineObserver, true);

        /*********************身份证模块*******************/
        initStartReadIdCard();
        /*****************身份证信息回调模块****************/
        intentFilter = new IntentFilter();
        intentFilter.addAction(msg);
        registerReceiver(mBltReceiver, intentFilter);

        zxzx.setOnClickListener(this);
        bjjy.setOnClickListener(this);
        tjfw.setOnClickListener(this);
        yyfw.setOnClickListener(this);
        jkjc.setOnClickListener(this);
        jkda.setOnClickListener(this);
        mainExit.setOnClickListener(this);
        mainSlideImg.setOnClickListener(this);
        mainSlideName.setOnClickListener(this);
        mainSlideGrzl.setOnClickListener(this);
        mainSlideXxtz.setOnClickListener(this);
        mainSlideWddd.setOnClickListener(this);
        mainSlideWdda.setOnClickListener(this);
        mainSlideGybj.setOnClickListener(this);
        mainSlideXxtzNum.setOnClickListener(this);
    }

    protected int setLayoutResID()
    {
        return R.layout.activity_main;

    }

    protected void initDatas()
    {
        mainPresenter = new MainPresenter();
        mainPresenter.attachContextAndViewLayer(this,this);
        mainPresenter.getBleMacAddress(this,deviceWifiMacAddress);
    }

    protected void initLogic()
    {
        setSupportActionBar(mainToolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mainDrawerlayout, mainToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mainDrawerlayout.setDrawerListener(toggle);toggle.syncState();
        if(mainDrawerlayout.isShown()) mainDrawerlayout.closeDrawers();
        /************************根据逻辑更新界面*********************/
        if(null != getBaseApp().getUserInfo())
            loggedUi(getBaseApp().getUserInfo());
        else
            logOutUi();
    }

    /**更新登出后的Ui界面***/
    public void logOutUi()
    {
        mainExit.setVisibility(View.INVISIBLE);
        mainToolbar.setVisibility(View.INVISIBLE);
        mainCountdown.setVisibility(View.INVISIBLE);
        if(mainDrawerlayout.isShown())
            mainDrawerlayout.closeDrawers();
        mainDrawerlayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    /***更新登出后的逻辑***/
    public void logOut()
    {
        logOutUi();
        isNeenConnectIdCard = false;
        mainLogin.setText("正在连接身份证读取设备，请稍等...");
        startReadIdCard();
        mainSlideName.setText("用户姓名");
        useGlideLoadImg(mainSlideImg,R.mipmap.defaultheadimg);
        if(getBaseApp().getImIsLogined())
            NIMClient.getService(AuthService.class).logout();
    }

    public void onClick(View view)
    {
        if(null != getBaseApp().getUserInfo())
        {
            if(null != getBaseApp().getUserInfo().getMobilePhone() && !"".equals(getBaseApp().getUserInfo().getMobilePhone().trim()))
            {
                if(mainDrawerlayout.isShown()) mainDrawerlayout.closeDrawers();
                switch(view.getId())
                {
                    case R.id.zxzx:clickZxzx();break;
                    case R.id.bjjy:clickBjjy();break;
                    case R.id.tjfw:clickTjfw();break;
                    case R.id.yyfw:clickYyfw();break;
                    case R.id.jkjc:clickJkjc();break;
                    case R.id.jkda:clickJkda();break;
                    case R.id.main_slide_img:clickMain_slide_img();break;
                    case R.id.main_slide_name:clickMain_slide_name();break;
                    case R.id.main_slide_grzl:clickMain_slide_grzl();break;
                    case R.id.main_slide_xxtz:clickMain_slide_xxtz();break;
                    case R.id.main_slide_wddd:clickMain_slide_wddd();break;
                    case R.id.main_slide_wdda:clickMain_slide_wdda();break;
                    case R.id.main_slide_gybj:clickMain_slide_gybj();break;
                    case R.id.main_exit: mainPresenter.logOut(this);break;
                    case R.id.main_slide_xxtz_num:clickMain_slide_xxtz_num();break;
                }
            }
            else
            {
                if(view.getId() == R.id.main_exit)
                {
                    mainPresenter.logOut(this);
                    return;
                }
                if(null != cellPhoneDialog)
                   cellPhoneDialog.dismissDialog();
                showBindIdCardDialog(getBaseApp().getUserInfo().getName().trim(),getBaseApp().getUserInfo().getPapersNumber().trim());
            }
        }
        else
            showToast("请刷身份证进行登录操\n作,否则无法操作哟...",28);
    }

    protected   void   onDestroy()
    {
        NIMClient.getService(AuthServiceObserver.class).observeOnlineStatus(imOnLineObserver, false);
        mainPresenter.detachContextAndViewLayout();
        unregisterReceiver(mBltReceiver);
        super.onDestroy();
    }

    public void clickZxzx()
    {
        Intent intent = new Intent(this,ZxzxAct.class);
        startActivity(intent);
    }

    public void clickBjjy()
    {
        Intent intent = new Intent(this,BjjyAct.class);
        startActivity(intent);
    }

    public void clickTjfw()
    {
        /*Intent intent = new Intent(this,TjfwAct.class);
        startActivity(intent);*/
        showToast("功能暂未开放，敬请期待...");
    }

    public void clickYyfw()
    {
        /*Intent intent = new Intent(this,YyfwAct.class);
        startActivity(intent);*/
        showToast("功能暂未开放，敬请期待...");
    }

    public void clickJkjc()
    {
        Intent intent = new Intent(this,JkjcAct.class);
        startActivity(intent);
    }

    public void clickJkda()
    {
        Intent intent = new Intent(this,JkdaAct.class);
        startActivity(intent);
    }

    public void clickMain_slide_img()
    {
        Intent intent = new Intent(this,UserInfoAct.class);
        startActivity(intent);
    }

    public void clickMain_slide_name()
    {
        Intent intent = new Intent(this,UserInfoAct.class);
        startActivity(intent);
    }

    public void clickMain_slide_grzl()
    {
        Intent intent = new Intent(this,UserInfoAct.class);
        startActivity(intent);
    }

    public void clickMain_slide_xxtz()
    {
        /*Intent intent = new Intent(this,MsgNotifiesAct.class);
        startActivity(intent);*/
        showToast("功能暂未开放，敬请期待...");
    }

    public void clickMain_slide_wddd()
    {
        Intent intent = new Intent(this,MyBillsAct.class);
        startActivity(intent);
    }

    public void clickMain_slide_wdda()
    {
        Intent intent = new Intent(this,MyArchivesAct.class);
        startActivity(intent);
    }

    public void clickMain_slide_gybj()
    {
        Intent intent = new Intent(this,AboutAppAct.class);
        startActivity(intent);
    }

    public void clickMain_slide_xxtz_num()
    {
        /*Intent intent = new Intent(this,MsgNotifiesAct.class);
        startActivity(intent);*/
        showToast("功能暂未开放，敬请期待...");
    }

    @Subscribe
    public void receiveCountDownTime(Long countDownTime)
    {
        if(null != mainCountdown)
            mainCountdown.setText(null != countDownTime ? countDownTime + "秒后自动退出" : "0秒后自动退出");
    }

    @Subscribe
    public void receiveCountDownFinish(Boolean isFinish)
    {
        if(isFinish)
            mainPresenter.logOut(this);
         if(null != getBaseApp().getImIsLogined())
             NIMClient.getService(AuthService.class).logout();
    }

    /**********************************************************/
    /**********************绑定身份证模块**********************/
    public void getVerifiedCodeSuccess()
    {
        if(null != cellPhoneDialog)
        {
            cellPhoneDialog.startCountDownTime();
        }
    }

    public  void  bindIdCardSuccess(String phone)
    {
        if(null != cellPhoneDialog)
        {
            cellPhoneDialog.dismissDialog();
            getBaseApp().getUserInfo().setMobilePhone(phone.trim());
        }
    }

    public void showBindIdCardDialog(String name, String idCard)
    {
        cellPhoneDialog = BindingCellPhoneDialog.getInstance(this);
        BindingCellPhoneDialog.OnClickSureListener onClickSureListener = new BindingCellPhoneDialog.OnClickSureListener()
        {
            public void onClickGetCode(String phoneNum)
            {
                mainPresenter.getVerifiedCode(MainAct.this,phoneNum);
            }

            public void onClickSure(String idcard,String phoneNum, String verificatedCode)
            {
                mainPresenter.bindPhoneNum(MainAct.this,idcard,phoneNum,verificatedCode);
            }
        };
        cellPhoneDialog.showDialog(this,name,idCard,true,null,onClickSureListener);
    }

    /***********************************************************/
    /**************************蓝牙模块*************************/
    private ReadThread mReadThread = null;
    private BtReaderClient mBtReaderClient = null;

    public void startReadIdCard()
    {
        mBtReaderClient = new BtReaderClient(this);
        mBtReaderClient.setCallBack(this);
        rx.Observable.just("").map(new Func1<String, String>()
        {
            public String call(String s)
            {
                BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
                if(!btAdapter.isEnabled())
                    btAdapter.enable();
                while(true)
                {
                    if(mBtReaderClient.connectBt(BleMacAddress))
                    {
                        isNeenConnectIdCard = true;
                        break;
                    }
                }
                return s;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<String>()
        {
            public void call(String s)
            {
                startThreadReadCard();
            }
        });
    }

    public  void  stopReadIdCard()
    {
        if(stopThreadReadCard() && null != mBtReaderClient)
        {
            unregisterReceiver(mBtReaderClient.getmBltReceiver());
            mBtReaderClient.setCallBack(null);
            mBtReaderClient.disconnectBt();
            mBtReaderClient = null;
        }
    }

    public void initStartReadIdCard()
    {
        if(null != BleMacAddress && !"".equals(BleMacAddress))
        {
            if(!isStartReadIdCard)
            {
                isNeenConnectIdCard = false;
                isStartReadIdCard = true;
                startReadIdCard();
            }
        }
    }

    public void startThreadReadCard()
    {
        mReadThread = new ReadThread();
        mReadThread.start();
    }

    public boolean stopThreadReadCard()
    {
        if (mReadThread != null && mReadThread.isAlive())
        {
            mReadThread.over();
            mReadThread = null;
            return true;
        }
        else
            return true;
    }

    public class BaseThread extends Thread
    {
        public boolean mOver = false;
        public boolean isOver(){
            return (this.interrupted() || mOver);
        }

        public void over()
        {
            this.interrupted();
            mOver = true;
        }
    }

    public class ReadThread extends BaseThread
    {
        public InvsIdCard mCard = null;
        protected void sendMsg(int cmd, boolean succ)
        {
            Intent intent = new Intent();
            intent.setAction(msg);
            intent.putExtra("cmd", cmd);
            intent.putExtra("tag", succ);
            if (succ && cmd == InvsConst.Cmd_ReadCard)
                intent.putExtra("InvsIdCard", mCard);
            MainAct.this.sendBroadcast(intent);
        }

        void readCard()
        {
            try
            {
                SystemClock.sleep(100);
                int iResult = mBtReaderClient.findCardCmd();
                if (iResult == -1)
                {
                    mBtReaderClient.disconnectBt();
                    over();
                    return;
                }
                else if (iResult == 0x9f)
                {

                }
                else
                {
                    sendMsg(InvsConst.Cmd_ReadCard, false);
                    return;
                }

                iResult = mBtReaderClient.readCardCmd();
                if (iResult == -1)
                {
                    mBtReaderClient.disconnectBt();
                    over();
                    return;
                }
                else if (iResult == 0x90)
                {
                    mCard = mBtReaderClient.mInvsIdCard;
                    sendMsg(InvsConst.Cmd_ReadCard, true);
                }
                else
                {
                    sendMsg(InvsConst.Cmd_ReadCard, false);
                    return;
                }

                while(!isOver())
                {
                    SystemClock.sleep(50);
                    iResult = mBtReaderClient.readAppCmd();
                    if (iResult == 0x90 || iResult == 0x91)
                        continue;

                    if (iResult == -1)
                    {
                        mBtReaderClient.disconnectBt();
                        over();
                    }
                    break;
                }
            }
            catch (Exception e)
            {

            }
        }
        public void run()
        {
            while(!isOver())
            {
                readCard();
            }
            SystemClock.sleep(5);
        }
    }

    public void onBtState(final boolean isConnect)
    {
        if(null != getBaseApp().getUserInfo())
            logged(getBaseApp().getUserInfo());
        else
        {
            if(isConnect)
                mainLogin.setText("请刷身份证进行登录操作...");
            else
            {
                if(isNeenConnectIdCard)
                {
                    isNeenConnectIdCard = false;
                    new Thread(new Runnable()
                    {
                        public void run()
                        {
                            while(true)
                            {
                                if(mBtReaderClient.connectBt(BleMacAddress))
                                {
                                    startThreadReadCard();
                                    isNeenConnectIdCard = true;
                                    break;
                                }
                            }
                        }
                    }).start();
                    mainLogin.setText("正在连接身份证读取设备，请稍等...");
                }
            }
        }
    }

    /***********************************获取WifiMac地址**********************************/
    private static final String marshmallowMacAddress = "02:00:00:00:00:00";
    private static final String fileAddressMac = "/sys/class/net/wlan0/address";
    public String getAdresseMAC(Context context)
    {
        WifiManager wifiMan = (WifiManager)context.getSystemService(Context.WIFI_SERVICE) ;
        WifiInfo wifiInf = wifiMan.getConnectionInfo();
        if(wifiInf != null && marshmallowMacAddress.equals(wifiInf.getMacAddress()))
        {
            String result = null;
            try
            {
                result= getAddressMacByInterface();
                if (result != null)
                {
                    return result;
                }
                else
                {
                    result = getAddressMacByFile(wifiMan);
                    return result;
                }
            }
            catch (IOException e)
            {
                Log.e("MobileAccess", "Erreur lecture propriete Adresse MAC");
            }
            catch (Exception e)
            {
                Log.e("MobileAcces", "Erreur lecture propriete Adresse MAC ");
            }
        }
        else
        {
            if (wifiInf != null && wifiInf.getMacAddress() != null)
            {
                return wifiInf.getMacAddress();
            }
            else
            {
                return "";
            }
        }
        return marshmallowMacAddress;
    }

    public void setWifiMacAddress(String wifiMacAddress)
    {
        BleMacAddress = wifiMacAddress;
        initStartReadIdCard();
    }

    private String getAddressMacByInterface()
    {
        try
        {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all)
            {
                if (nif.getName().equalsIgnoreCase("wlan0"))
                {
                    byte[] macBytes = nif.getHardwareAddress();
                    if (macBytes == null)
                    {
                        return "";
                    }
                    StringBuilder res1 = new StringBuilder();
                    for (byte b : macBytes)
                    {
                        res1.append(String.format("%02X:",b));
                    }
                    if (res1.length() > 0)
                    {
                        res1.deleteCharAt(res1.length() - 1);
                    }
                    return res1.toString();
                }
            }
        }
        catch (Exception e)
        {
            Log.e("MobileAcces", "Erreur lecture propriete Adresse MAC ");
        }
        return null;
    }

    private String getAddressMacByFile(WifiManager wifiMan) throws Exception
    {
        String ret;
        int wifiState = wifiMan.getWifiState();
        wifiMan.setWifiEnabled(true);
        File fl = new File(fileAddressMac);
        FileInputStream fin = new FileInputStream(fl);
        ret = crunchifyGetStringFromStream(fin);
        fin.close();
        boolean enabled = WifiManager.WIFI_STATE_ENABLED == wifiState;
        wifiMan.setWifiEnabled(enabled); return ret;
    }

    private String crunchifyGetStringFromStream(InputStream crunchifyStream) throws IOException
    {
        if (crunchifyStream != null)
        {
            Writer crunchifyWriter = new StringWriter();
            char[] crunchifyBuffer = new char[2048];
            try
            {
                Reader crunchifyReader = new BufferedReader(new InputStreamReader(crunchifyStream, "UTF-8"));
                int counter;
                while ((counter = crunchifyReader.read(crunchifyBuffer)) != -1)
                {
                    crunchifyWriter.write(crunchifyBuffer, 0, counter);
                }
            }
            finally
            {
                crunchifyStream.close();
            }
            return crunchifyWriter.toString();
        }
        else
        {
            return "No Contents";
        }
    }
}
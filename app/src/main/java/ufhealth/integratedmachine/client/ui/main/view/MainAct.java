package ufhealth.integratedmachine.client.ui.main.view;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.webkit.JavascriptInterface;
import android.widget.TextView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.invs.BltBase;
import com.invs.BtReaderClient;
import com.invs.IClientCallBack;
import com.invs.InvsConst;
import com.invs.InvsIdCard;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.NIMClient;
import android.support.v7.widget.Toolbar;
import com.netease.nimlib.sdk.StatusCode;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import ufhealth.integratedmachine.client.R;
import com.netease.nimlib.sdk.auth.LoginInfo;
import android.support.v4.widget.DrawerLayout;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.auth.AuthService;
import com.yuan.devlibrary._12_______Utils.NetTools;
import android.support.v7.app.ActionBarDrawerToggle;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;
import ufhealth.integratedmachine.client.base.BaseAct;
import com.netease.nimlib.sdk.auth.AuthServiceObserver;
import ufhealth.integratedmachine.client.bean.main.UserInfo;
import ufhealth.integratedmachine.client.ui.bjjy.view.BjjyAct;
import ufhealth.integratedmachine.client.ui.jkda.view.JkdaAct;
import ufhealth.integratedmachine.client.ui.jkjc.view.JkjcAct;
import ufhealth.integratedmachine.client.ui.tjfw.view.TjfwAct;
import ufhealth.integratedmachine.client.ui.zxzx.view.ZxzxAct;
import ufhealth.integratedmachine.client.ui.zxzx.view.SpzxingAct;
import ufhealth.integratedmachine.client.ui.zxzx.view.YyzxingAct;
import ufhealth.integratedmachine.client.ui.zxzx.view.TwzxingAct;
import ufhealth.integratedmachine.client.ui.main.view_v.MainAct_V;
import com.yuan.devlibrary._11___Widget.promptBox.BaseProgressDialog;

import java.util.Observable;

import ufhealth.integratedmachine.client.ui.main.presenter.MainPresenter;

import static com.invs.InvsConst.msg;

public class MainAct extends BaseAct implements MainAct_V,View.OnClickListener,IClientCallBack
{
    private Toolbar mainToolbar;
    private TextView mainCountdown;
    private ImageButton mainExit;
    private TextView mainLogin;
    private RelativeLayout zxzx;
    private RelativeLayout bjjy;
    private RelativeLayout tjfw;
    private RelativeLayout yyfw;
    private RelativeLayout jkjc;
    private RelativeLayout jkda;
    private TextView mainSlideName;
    private LinearLayout mainSlideGrzl;
    private LinearLayout mainSlideXxtz;
    private TextView mainSlideXxtzNum;
    private LinearLayout mainSlideWddd;
    private LinearLayout mainSlideWdda;
    private LinearLayout mainSlideGybj;
    private CircleImageView mainSlideImg;
    private DrawerLayout mainDrawerlayout;
    private MainPresenter mainPresenter;
    private BaseProgressDialog progressDialog;

    private Observer imOnLineObserver;
    private ReadThread mReadThread = null;
    private BtReaderClient mBtReaderClient;

    protected int setLayoutResID()
    {
        return R.layout.activity_main;

    }

    protected void initWidgets(View rootView)
    {
        mainExit = rootView.findViewById(R.id.main_exit);
        mainLogin = rootView.findViewById(R.id.main_login);
        mainToolbar = rootView.findViewById(R.id.main_toolbar);
        mainCountdown = rootView.findViewById(R.id.main_countdown);
        mainDrawerlayout = rootView.findViewById(R.id.main_drawerlayout);
        zxzx = rootView.findViewById(R.id.zxzx);
        bjjy = rootView.findViewById(R.id.bjjy);
        tjfw = rootView.findViewById(R.id.tjfw);
        yyfw = rootView.findViewById(R.id.yyfw);
        jkjc = rootView.findViewById(R.id.jkjc);
        jkda = rootView.findViewById(R.id.jkda);
        mainSlideImg = rootView.findViewById(R.id.main_slide_img);
        mainSlideName = rootView.findViewById(R.id.main_slide_name);
        mainSlideGrzl = rootView.findViewById(R.id.main_slide_grzl);
        mainSlideXxtz = rootView.findViewById(R.id.main_slide_xxtz);
        mainSlideWddd = rootView.findViewById(R.id.main_slide_wddd);
        mainSlideWdda = rootView.findViewById(R.id.main_slide_wdda);
        mainSlideGybj = rootView.findViewById(R.id.main_slide_gybj);
        mainSlideXxtzNum = rootView.findViewById(R.id.main_slide_xxtz_num);

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
                    if(mBtReaderClient.connectBt("00:0E:0B:00:02:33"))
                        break;
                }
                return s;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<String>()
        {
            public void call(String s)
            {
                startThreadReadCard();
                //4、开启循环读卡（业务中应该放在点击登录的时候，开启循环读卡；登录成功后，关闭循环读卡）
                //startThreadReadCard();  //开启循环读卡
                //stopThreadReadCard(); //关闭循环读卡
                //5、注册BroadcastReceiver，用于接收蓝牙传回的消息
            }
        });

        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(msg);
        registerReceiver(mBltReceiver, intentFilter);

    }

    protected void initDatas()
    {
        mainPresenter = new MainPresenter();
        mainPresenter.attachContextAndViewLayer(this,this);
    }

    protected void onResume()
    {
        super.onResume();
        initLogic();
    }

    protected void initLogic()
    {
        setSupportActionBar(mainToolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mainDrawerlayout, mainToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mainDrawerlayout.setDrawerListener(toggle);toggle.syncState();
        if(mainDrawerlayout.isShown()) mainDrawerlayout.closeDrawers();
        if(getBaseApp().getIsLogged()) logged(getBaseApp().getUserInfo());
        else notLogged();

        zxzx.setOnClickListener(this);
        bjjy.setOnClickListener(this);
        tjfw.setOnClickListener(this);
        yyfw.setOnClickListener(this);
        jkjc.setOnClickListener(this);
        jkda.setOnClickListener(this);
        mainExit.setOnClickListener(this);
        /***临时用于模仿用户的登录操作***/
        mainLogin.setOnClickListener(this);
        mainSlideImg.setOnClickListener(this);
        mainSlideName.setOnClickListener(this);
        mainSlideGrzl.setOnClickListener(this);
        mainSlideXxtz.setOnClickListener(this);
        mainSlideWddd.setOnClickListener(this);
        mainSlideWdda.setOnClickListener(this);
        mainSlideGybj.setOnClickListener(this);
        mainSlideXxtzNum.setOnClickListener(this);
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
    }

    /*******已登录*****/
    public void logged(UserInfo.UserInfoBean userInfo)
    {
        getBaseApp().setIsLogged(true);
        mainToolbar.setVisibility(View.VISIBLE);
        mainCountdown.setVisibility(View.VISIBLE);
        mainExit.setVisibility(View.VISIBLE);
        mainLogin.setText("欢迎您，" + ((null != userInfo && null != userInfo.getName() && !"".equals(userInfo.getName().trim())) ?
                userInfo.getName().trim() : "未命名") + "（" + userInfo.getPapersNumber().trim() + "）");
        if(mainDrawerlayout.isShown())
            mainDrawerlayout.closeDrawers();
        mainDrawerlayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }

    /********未登录*******/
    public void notLogged()
    {
        getBaseApp().setIsLogged(false);
        mainToolbar.setVisibility(View.INVISIBLE);
        mainCountdown.setVisibility(View.INVISIBLE);
        mainExit.setVisibility(View.INVISIBLE);
        mainLogin.setText("请刷身份证进行登录操作...");
        if(mainDrawerlayout.isShown())
            mainDrawerlayout.closeDrawers();
        mainDrawerlayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    public void onClick(View view)
    {
        if(getBaseApp().getIsLogged())
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
                case R.id.main_exit: mainPresenter.logOut(this);break;
                case R.id.main_slide_img:clickMain_slide_img();break;
                case R.id.main_slide_name:clickMain_slide_name();break;
                case R.id.main_slide_grzl:clickMain_slide_grzl();break;
                case R.id.main_slide_xxtz:clickMain_slide_xxtz();break;
                case R.id.main_slide_wddd:clickMain_slide_wddd();break;
                case R.id.main_slide_wdda:clickMain_slide_wdda();break;
                case R.id.main_slide_gybj:clickMain_slide_gybj();break;
                case R.id.main_slide_xxtz_num:clickMain_slide_xxtz_num();break;
            }
        }
        else
        {
            if(view.getId() == R.id.main_login)
            {
                if(!getBaseApp().getIsLogged())
                    mainPresenter.logging(this,"510922198812235114");
            }
            else
            {
                showToast("请刷身份证进行登录操\n作,否则无法操作哟...",28);
            }
        }
    }

    @Subscribe
    public void receiveCountDownTime(Long countDownTime)
    {
        if(null != mainCountdown)
            mainCountdown.setText(null != countDownTime ? countDownTime + "S" : "0S");
    }

    @Subscribe
    public void receiveCountDownFinish(Boolean isFinish)
    {
        if(isFinish) mainPresenter.logOut(this);
        if(getBaseApp().getImIsLogined()) NIMClient.getService(AuthService.class).logout();
    }

    /*****************************************蓝牙层******************************************/

    /**********************************************************************************************/
    /********************************************VIEW层********************************************/
    /**********************************************************************************************/

    /********进行登录操作********/
    public void logging(UserInfo.UserInfoBean userInfo)
    {
        logged(userInfo);
        useGlideLoadImg(mainSlideImg,null != userInfo.getAvatar() ? userInfo.getAvatar().trim().toString() : "");
        mainSlideName.setText(null != userInfo.getName() ? userInfo.getName().trim().toString() : "无名氏");
    }

    /********进行登出操作********/
    public void logOut()
    {
        useGlideLoadImg(mainSlideImg,R.mipmap.defaultheadimg);
        mainSlideName.setText("用户姓名");
        startThreadReadCard();
        notLogged();
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
        Intent intent = new Intent(this,TjfwAct.class);
        startActivity(intent);
    }

    public void clickYyfw()
    {
        /*Intent intent = new Intent(this,YyfwAct.class);
        startActivity(intent);*/
        startZxActWithDialog("SPZX","1","test3",18l,"1");
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

    protected void onDestroy()
    {
        NIMClient.getService(AuthServiceObserver.class).observeOnlineStatus(imOnLineObserver, true);
        mainPresenter.detachContextAndViewLayout();
        super.onDestroy();
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
        Intent intent = new Intent(this,MsgNotifiesAct.class);
        startActivity(intent);
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
        Intent intent = new Intent(this,MsgNotifiesAct.class);
        startActivity(intent);
    }

    public void startZxAct(final String type,final String doctorId,final String accId,final long time,final String orderId)
    {
        if(getBaseApp().getImIsLogined() && null != getBaseApp().getIMLoginInfo())
        {
            dismissLoadingDialog(progressDialog);
            Intent intent = null;
            switch(type)
            {
                case "SPZX":intent = new Intent(MainAct.this,SpzxingAct.class);break;
                case "YPZX":intent = new Intent(MainAct.this,YyzxingAct.class);break;
                case "TWZX":intent = new Intent(MainAct.this,TwzxingAct.class);break;
            }
            intent.putExtra("accid",accId);
            intent.putExtra("orderid",orderId);
            intent.putExtra("doctorid",doctorId);
            intent.putExtra("sytime",time);
            startActivity(intent);
        }
        else
        {
            LoginInfo loginInfo = new LoginInfo(getBaseApp().getImUserInfo().getAccid().trim(),getBaseApp().getImUserInfo().getToken().trim());
            NIMClient.getService(AuthService.class).login(loginInfo).setCallback(new RequestCallback<LoginInfo>()
            {
                public void onSuccess(LoginInfo loginInfo)
                {
                    dismissLoadingDialog(progressDialog);
                    getBaseApp().setIMLoginInfo(loginInfo);
                    Intent intent = null;
                    switch(type)
                    {
                        case "SPZX":intent = new Intent(MainAct.this,SpzxingAct.class);break;
                        case "YPZX":intent = new Intent(MainAct.this,YyzxingAct.class);break;
                        case "TWZX":intent = new Intent(MainAct.this,TwzxingAct.class);break;
                    }
                    intent.putExtra("accid",accId);
                    intent.putExtra("orderid",orderId);
                    intent.putExtra("doctorid",doctorId);
                    intent.putExtra("sytime",time);
                    startActivity(intent);
                }

                public void onFailed(int code)
                {
                    if(!NetTools.WhetherConnectNet(MainAct.this))
                    {
                        showToast("网络发生异常！请确保网络正常后再发起咨询请求");
                        dismissLoadingDialog(progressDialog);
                        return;
                    }
                    else if(code  == 302)
                    {
                        showToast("IM账号密码发生错误！请联系管理员");
                        dismissLoadingDialog(progressDialog);
                        return;
                    }
                    else
                    {
                        startZxAct(type,doctorId,accId,time,orderId);
                    }
                }

                public void onException(Throwable exception)
                {
                    Log.e("ImException",exception.getMessage().toString());
                    dismissLoadingDialog(progressDialog);
                }
            });
        }
    }

    @JavascriptInterface
    public void startZxActWithDialog(final String type,final String doctorId,final String accId,final long time,final String orderId)
    {
        progressDialog = showLoadingDialog();
        startZxAct(type,doctorId,accId,time,orderId);
    }


    public void startThreadReadCard()
    {
        mReadThread = new ReadThread();
        mReadThread.start();
    }

    public void stopThreadReadCard()
    {
        if (mReadThread != null && mReadThread.isAlive()){
            mReadThread.over();
            mReadThread = null;
        }
    }

    public void onBtState(final boolean isConnect)
    {
        if (isConnect)
            mainLogin.setText("请刷身份证进行登录操作...");
        else
            mainLogin.setText("正在连接身份证读取设备，请稍等...");
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
                    StringBuffer sb = new StringBuffer();
                    sb.append("姓名：" + invsIdCard.name);
                    sb.append("\r\n");
                    sb.append("性别：" + invsIdCard.sex);
                    sb.append("\r\n");
                    sb.append("民族：" + invsIdCard.nation);
                    sb.append("\r\n");
                    sb.append("出生日期：" + invsIdCard.birth);
                    sb.append("\r\n");
                    sb.append("住址：" + invsIdCard.address);
                    sb.append("\r\n");
                    sb.append("身份证号：" + invsIdCard.idNo);
                    showToast(sb.toString());
                    if(null != invsIdCard && null != invsIdCard.idNo && !"".equals(invsIdCard.idNo))
                        stopThreadReadCard();
                }
                else
                {
                    //读卡失败
                }
            }
        }
    };
}
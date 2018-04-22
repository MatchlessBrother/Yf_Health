package ufhealth.integratedmachine.client.ui.main.view;

import android.view.View;
import android.content.Intent;
import android.widget.TextView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.support.v7.widget.Toolbar;
import ufhealth.integratedmachine.client.R;
import android.support.v4.widget.DrawerLayout;
import com.hwangjr.rxbus.annotation.Subscribe;
import android.support.v7.app.ActionBarDrawerToggle;
import de.hdodenhof.circleimageview.CircleImageView;
import ufhealth.integratedmachine.client.base.BaseAct;
import ufhealth.integratedmachine.client.ui.bjjy.view.BjjyAct;
import ufhealth.integratedmachine.client.ui.jkda.view.JkdaAct;
import ufhealth.integratedmachine.client.ui.jkjc.view.JkjcAct;
import ufhealth.integratedmachine.client.ui.tjfw.view.TjfwAct;
import ufhealth.integratedmachine.client.ui.yyfw.view.YyfwAct;
import ufhealth.integratedmachine.client.ui.zxzx.view.SpzxingAct;
import ufhealth.integratedmachine.client.ui.zxzx.view.YyzxingAct;
import ufhealth.integratedmachine.client.ui.zxzx.view.ZxzxAct;
import ufhealth.integratedmachine.client.ui.main.model.UserInfo;
import ufhealth.integratedmachine.client.ui.main.view_v.MainAct_V;
import ufhealth.integratedmachine.client.ui.main.presenter.MainPresenter;

public class MainAct extends BaseAct implements MainAct_V,View.OnClickListener
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
    }

    protected void initDatas()
    {
        mainPresenter = new MainPresenter(this);

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
        if(getBaseApp().getIsLogged()) logged();
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
    }

    /*******已登录*****/
    public void logged()
    {
        getBaseApp().setIsLogged(true);
        mainToolbar.setVisibility(View.VISIBLE);
        mainCountdown.setVisibility(View.VISIBLE);
        mainExit.setVisibility(View.VISIBLE);
        mainLogin.setText("欢迎您，瓜婆娘（510121*********698）");
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
                case R.id.main_exit: mainPresenter.logOut();break;
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
                    mainPresenter.logging();
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
        if(isFinish) mainPresenter.logOut();
    }

    /**********************************************************************************************/
    /********************************************VIEW层********************************************/
    /**********************************************************************************************/

    /********进行登录操作********/
    public void logging(UserInfo userInfo)
    {
        logged();
        useGlideLoadImg(mainSlideImg,null != userInfo.headImgPath ? userInfo.headImgPath.trim().toString() : "");
        mainSlideName.setText(null != userInfo.getName() ? userInfo.getName().trim().toString() : "无名氏");
    }

    public void logOut()
    {
        useGlideLoadImg(mainSlideImg,R.mipmap.defaultheadimg);
        mainSlideName.setText("用户姓名");
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
        Intent intent = new Intent(this,YyfwAct.class);
        startActivity(intent);
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
        Intent intent = new Intent(this,UserInfosAct.class);
        startActivity(intent);
    }

    public void clickMain_slide_name()
    {
        Intent intent = new Intent(this,UserInfosAct.class);
        startActivity(intent);
    }

    public void clickMain_slide_grzl()
    {
        Intent intent = new Intent(this,UserInfosAct.class);
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
        Intent intent = new Intent(this,SpzxingAct.class);
        startActivity(intent);
    }

    public void clickMain_slide_xxtz_num()
    {
        Intent intent = new Intent(this,MsgNotifiesAct.class);
        startActivity(intent);
    }
}
package ufhealth.integratedmachine.client.ui.main.view;

import android.view.View;
import android.widget.TextView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.support.v7.widget.Toolbar;
import ufhealth.integratedmachine.client.R;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import de.hdodenhof.circleimageview.CircleImageView;
import ufhealth.integratedmachine.client.base.BaseAct;
import com.yuan.devlibrary._12_______Utils.PromptBoxTools;
import ufhealth.integratedmachine.client.ui.main.view_v.MainAct_V;

public class MainAct extends MainAct_V implements View.OnClickListener
{
    private Toolbar mainToolbar;
    private TextView mainCountdown;
    private ImageButton mainExit;
    private DrawerLayout mainDrawerlayout;
    private TextView mainTs;
    private TextView mainTsbtn;
    private RelativeLayout zxzx;
    private RelativeLayout bjjy;
    private RelativeLayout tjfw;
    private RelativeLayout yyfw;
    private RelativeLayout jkjc;
    private RelativeLayout jkda;
    private CircleImageView mainSlideImg;
    private TextView mainSlideName;
    private LinearLayout mainSlideGrzl;
    private LinearLayout mainSlideXxtz;
    private TextView mainSlideXxtzNum;
    private LinearLayout mainSlideWddd;
    private LinearLayout mainSlideWdda;
    private LinearLayout mainSlideGybj;
    private boolean mIsLogged = false;

    protected int setLayoutResID()
    {
        return R.layout.activity_main;
    }

    protected void initWidgets(View rootView)
    {
        mainToolbar = rootView.findViewById(R.id.main_toolbar);
        mainCountdown = rootView.findViewById(R.id.main_countdown);
        mainExit = rootView.findViewById(R.id.main_exit);
        mainDrawerlayout = rootView.findViewById(R.id.main_drawerlayout);
        mainTs = rootView.findViewById(R.id.main_ts);
        mainTsbtn = rootView.findViewById(R.id.main_tsbtn);
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
        mainSlideXxtzNum = rootView.findViewById(R.id.main_slide_xxtz_num);
        mainSlideWddd = rootView.findViewById(R.id.main_slide_wddd);
        mainSlideWdda = rootView.findViewById(R.id.main_slide_wdda);
        mainSlideGybj = rootView.findViewById(R.id.main_slide_gybj);
    }

    protected void initDatas()
    {

    }

    protected void initLogic()
    {
        setSupportActionBar(mainToolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mainDrawerlayout, mainToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mainDrawerlayout.setDrawerListener(toggle);
        toggle.syncState();
        if(mainDrawerlayout.isShown())
            mainDrawerlayout.closeDrawers();
        mainDrawerlayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        mainExit.setOnClickListener(this);
        mainTsbtn.setOnClickListener(this);
        zxzx.setOnClickListener(this);
        bjjy.setOnClickListener(this);
        tjfw.setOnClickListener(this);
        yyfw.setOnClickListener(this);
        jkjc.setOnClickListener(this);
        jkda.setOnClickListener(this);
        mainSlideImg.setOnClickListener(this);
        mainSlideName.setOnClickListener(this);
        mainSlideGrzl.setOnClickListener(this);
        mainSlideXxtz.setOnClickListener(this);
        mainSlideXxtzNum.setOnClickListener(this);
        mainSlideWddd.setOnClickListener(this);
        mainSlideWdda.setOnClickListener(this);
        mainSlideGybj.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        if(mIsLogged)
        {
            switch(view.getId())
            {
                case R.id.main_exit:loginOut();break;
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
                case R.id.main_slide_xxtz_num:clickMain_slide_xxtz_num();break;
            }
        }
        else
        {
           if(view.getId() == R.id.main_tsbtn)
           {
              clickLoginBtn();
           }
           else
           {
               PromptBoxTools.showToast(this,"请刷身份证登录之后再进行操作");
           }
        }
    }

    /**********************************************************************************************/
    /********************************************VIEW层********************************************/
    /**********************************************************************************************/


    public void clickLoginBtn()
    {

    }

    @Override
    public void clickLoginIn() {

    }

    @Override
    public void loginIn()
    {
        try
        {
            mainTs.setVisibility(View.VISIBLE);
            mainTs.setText("请刷身份证进行登录...");
            mainTsbtn.setVisibility(View.INVISIBLE);



            /**为了减缓服务器的请求压力，这里应该有个刷身份证的时间限定并开始网络请求*/
            Thread.sleep(1500);//模仿服务器登录



            loginOn();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void loginOn()
    {
        mIsLogged = true;
        mainToolbar.setVisibility(View.VISIBLE);
        /****************************************/
        //mainCountdown.setText(countdownTime+"s");
        mainCountdown.setVisibility(View.VISIBLE);
        /****************************************/
        mainExit.setVisibility(View.VISIBLE);
        mainTs.setText("欢迎您，瓜婆娘（510121*********698）");
        mainDrawerlayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }

    @Override
    public void loginOut()
    {
        mIsLogged = false;
        mainToolbar.setVisibility(View.INVISIBLE);
        mainCountdown.setVisibility(View.INVISIBLE);
        mainExit.setVisibility(View.INVISIBLE);
        mainTs.setVisibility(View.INVISIBLE);
        mainTsbtn.setVisibility(View.VISIBLE);
        if(mainDrawerlayout.isShown())
            mainDrawerlayout.closeDrawers();
        mainDrawerlayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    public void clickZxzx()
    {

    }

    public void clickBjjy()
    {

    }

    public void clickTjfw()
    {

    }

    public void clickYyfw()
    {

    }

    public void clickJkjc()
    {

    }

    public void clickJkda()
    {

    }

    public void clickMain_slide_img()
    {

    }

    public void clickMain_slide_name()
    {

    }

    public void clickMain_slide_grzl()
    {

    }

    public void clickMain_slide_xxtz()
    {

    }

    public void clickMain_slide_wddd()
    {

    }

    public void clickMain_slide_wdda()
    {

    }

    public void clickMain_slide_gybj()
    {

    }

    public void clickMain_slide_xxtz_num()
    {

    }
}
package ufhealth.integratedmachine.client.ui.main.view_v;

import android.os.CountDownTimer;

import ufhealth.integratedmachine.client.base.BaseAct;

public abstract class MainAct_V extends BaseAct
{
    /*************一体机使用状态************/
    public enum STATE {LOGING, LOGED, LOGOUT}
    public STATE mState = STATE.LOGOUT;
    /*************登录操作倒计时************/
    public Integer mCountDownTime = 20;
    public CountDownTimer mCountDownTimer = new CountDownTimer(mCountDownTime * 1000,1000)
    {
        public void onTick(long l)
        {
            if(mState == STATE.LOGOUT)
            {
                loginIn();
                mState = STATE.LOGING;
            }
        }

        public void onFinish()
        {
            if(mState == STATE.LOGING)
                mState = STATE.LOGOUT;
        }
    };

    public abstract void clickLoginIn();

    public void loginIn() {};
    public abstract void loginOn();
    public abstract void loginOut();
    public abstract void clickZxzx();
    public abstract void clickBjjy();
    public abstract void clickTjfw();
    public abstract void clickYyfw();
    public abstract void clickJkjc();
    public abstract void clickJkda();
    public abstract void clickMain_slide_img();
    public abstract void clickMain_slide_name();
    public abstract void clickMain_slide_grzl();
    public abstract void clickMain_slide_xxtz();
    public abstract void clickMain_slide_wddd();
    public abstract void clickMain_slide_wdda();
    public abstract void clickMain_slide_gybj();
    public abstract void clickMain_slide_xxtz_num();
}

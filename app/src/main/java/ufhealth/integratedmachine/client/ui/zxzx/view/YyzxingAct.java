package ufhealth.integratedmachine.client.ui.zxzx.view;

import android.view.View;
import ufhealth.integratedmachine.client.R;
import com.hwangjr.rxbus.annotation.Subscribe;
import ufhealth.integratedmachine.client.base.BaseAct;
import ufhealth.integratedmachine.client.widget.RippleLayout;

public class YyzxingAct extends BaseAct
{
    private RippleLayout rippleView;

    protected int setLayoutResID()
    {
        return R.layout.activity_yyzxing;
    }

    protected void initWidgets(View rootView)
    {
        super.initWidgets(rootView);
        setTitleContent("语音咨询中");
        rippleView = findViewById(R.id.yyzxing_right_bottom_ripplelayout);
        rippleView.startRippleAnimation();
    }

    protected void initDatas()
    {

    }

    protected void initLogic()
    {

    }

    @Subscribe
    public void receiveCountDownFinish(Boolean isFinish)
    {
        super.receiveCountDownFinish(isFinish);

    }

    @Subscribe
    public void receiveCountDownTime(Long countDownTime)
    {
        super.receiveCountDownTime(countDownTime);

    }

    public void onClick(View view)
    {
        super.onClick(view);
    }
}
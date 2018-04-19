package ufhealth.integratedmachine.client.ui.zxzx.view;

import android.view.View;
import ufhealth.integratedmachine.client.R;
import com.hwangjr.rxbus.annotation.Subscribe;
import ufhealth.integratedmachine.client.base.BaseAct;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_View;

public class ChooseDoctorAct extends BaseAct implements BaseMvp_View,View.OnClickListener
{
    protected int setLayoutResID()
    {
        return R.layout.activity_choosedoctor;
    }

    protected void initWidgets(View rootView)
    {
        super.initWidgets(rootView);
        setTitleContent("选择医生");
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
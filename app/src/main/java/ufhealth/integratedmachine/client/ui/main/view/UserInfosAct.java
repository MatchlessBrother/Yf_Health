package ufhealth.integratedmachine.client.ui.main.view;

import android.view.View;
import ufhealth.integratedmachine.client.R;
import com.hwangjr.rxbus.annotation.Subscribe;
import ufhealth.integratedmachine.client.base.BaseAct;
import ufhealth.integratedmachine.client.ui.main.view_v.UserInfosAct_V;

public class UserInfosAct extends BaseAct implements UserInfosAct_V,View.OnClickListener
{
    protected int setLayoutResID()
    {
        return R.layout.activity_userinfos;
    }

    protected void initWidgets(View rootView)
    {
        super.initWidgets(rootView);
        setTitleContent("个人资料");
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
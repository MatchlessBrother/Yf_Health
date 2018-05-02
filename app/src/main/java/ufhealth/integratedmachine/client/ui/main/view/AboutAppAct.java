package ufhealth.integratedmachine.client.ui.main.view;

import android.view.View;
import android.widget.TextView;
import ufhealth.integratedmachine.client.R;
import com.hwangjr.rxbus.annotation.Subscribe;
import ufhealth.integratedmachine.client.base.BaseAct;
import ufhealth.integratedmachine.client.ui.main.view_v.AboutAppAct_V;
import ufhealth.integratedmachine.client.ui.main.presenter.AboutAppPresenter;

public class AboutAppAct extends BaseAct implements AboutAppAct_V,View.OnClickListener
{
    private TextView aboutappYtj;
    private TextView aboutappSbbh;
    private TextView aboutappSswz;
    private AboutAppPresenter aboutAppPresenter;

    protected int setLayoutResID()
    {
        return R.layout.activity_aboutapp;
    }

    protected void initWidgets(View rootView)
    {
        super.initWidgets(rootView);
        setTitleContent("关于本机");
        aboutappYtj = (TextView) findViewById(R.id.aboutapp_ytj);
        aboutappSbbh = (TextView) findViewById(R.id.aboutapp_sbbh);
        aboutappSswz = (TextView) findViewById(R.id.aboutapp_sswz);
    }

    protected void initDatas()
    {
        aboutAppPresenter = new AboutAppPresenter();
        aboutAppPresenter.attachContextAndViewLayer(this,this);
    }

    protected void initLogic()
    {

    }

    public void onClick(View view)
    {
        super.onClick(view);

    }

    protected void onDestroy()
    {
        aboutAppPresenter.detachContextAndViewLayout();
        super.onDestroy();
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
}
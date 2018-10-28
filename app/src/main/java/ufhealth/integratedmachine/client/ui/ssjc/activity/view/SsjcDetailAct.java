package ufhealth.integratedmachine.client.ui.ssjc.activity.view;

import android.view.View;
import ufhealth.integratedmachine.client.R;
import ufhealth.integratedmachine.client.base.BaseAct;
import ufhealth.integratedmachine.client.bean.ssjc.JcDetailInfo;
import ufhealth.integratedmachine.client.ui.ssjc.activity.view_v.SsjcDetailAct_V;
import ufhealth.integratedmachine.client.ui.ssjc.activity.presenter.SsjcDetailPresenter;

public class SsjcDetailAct extends BaseAct implements SsjcDetailAct_V,View.OnClickListener
{
    private String mAlarmId;
    private SsjcDetailPresenter mSsjcDetailPresenter;

    protected int setLayoutResID()
    {
        return R.layout.activity_ssjcdetail;
    }

    protected void initWidgets(View rootView)
    {
        super.initWidgets(rootView);
        setTitleContent("监测详情");
        mAlarmId = getIntent().getStringExtra("alarmid");
    }

    protected void initDatas()
    {
        mSsjcDetailPresenter = new SsjcDetailPresenter();
        bindBaseMvpPresenter(mSsjcDetailPresenter);
    }

    protected void initLogic()
    {

    }

    public void failOfGetDatas()
    {

    }

    public void onClick(View view)
    {
        super.onClick(view);
        switch (view.getId())
        {

        }
    }

    public void successOfGetDatas(JcDetailInfo jcDetailInfo)
    {

    }
}

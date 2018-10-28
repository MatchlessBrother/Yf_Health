package ufhealth.integratedmachine.client.ui.bjcz.activity.view;

import android.view.View;
import ufhealth.integratedmachine.client.R;
import ufhealth.integratedmachine.client.base.BaseAct;
import ufhealth.integratedmachine.client.bean.bjcz.BjczDetailInfo;
import ufhealth.integratedmachine.client.ui.bjcz.activity.view_v.BjczDetailAct_V;
import ufhealth.integratedmachine.client.ui.bjcz.activity.presenter.BjczDetailPresenter;

public class BjczDetailAct extends BaseAct implements BjczDetailAct_V,View.OnClickListener
{
    private String mAlarmId;
    private BjczDetailPresenter mBjczDetailPresenter;

    protected int setLayoutResID()
    {
        return R.layout.activity_bjczdetail;
    }

    protected void initWidgets(View rootView)
    {
        super.initWidgets(rootView);
        setTitleContent("报警详情");
        mAlarmId = getIntent().getStringExtra("alarmid");
    }

    protected void initDatas()
    {
        mBjczDetailPresenter = new BjczDetailPresenter();
        bindBaseMvpPresenter(mBjczDetailPresenter);
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

    public void successOfGetDatas(BjczDetailInfo bjczDetailInfo)
    {

    }
}
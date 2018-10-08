package ufhealth.integratedmachine.client.ui.main.view;

import android.view.View;
import ufhealth.integratedmachine.client.R;
import ufhealth.integratedmachine.client.base.BaseAct;
import ufhealth.integratedmachine.client.ui.main.view_v.MainAct_V;
import ufhealth.integratedmachine.client.ui.main.presenter.MainPresenter;

public class MainAct extends BaseAct implements MainAct_V,View.OnClickListener
{
    private MainPresenter mMainPresenter;

    protected int setLayoutResID()
    {
        return R.layout.activity_main;

    }

    protected void initWidgets(View rootView)
    {
        super.initWidgets(rootView);

    }

    protected void initDatas()
    {
        mMainPresenter = new MainPresenter();
        bindBaseMvpPresenter(mMainPresenter);
    }

    protected void initLogic()
    {

    }

    public void onClick(View view)
    {
        super.onClick(view);
        switch(view.getId())
        {

        }
    }
}
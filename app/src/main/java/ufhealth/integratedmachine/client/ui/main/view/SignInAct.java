package ufhealth.integratedmachine.client.ui.main.view;

import android.view.View;
import ufhealth.integratedmachine.client.R;
import ufhealth.integratedmachine.client.base.BaseAct;
import ufhealth.integratedmachine.client.ui.main.view_v.SignInAct_V;
import ufhealth.integratedmachine.client.ui.main.presenter.SignInPresenter;

public class SignInAct extends BaseAct implements SignInAct_V,View.OnClickListener
{
    private SignInPresenter mSignInPresenter;

    protected int setLayoutResID()
    {
        return R.layout.activity_signin;

    }

    protected void initWidgets(View rootView)
    {
        super.initWidgets(rootView);

    }

    protected void initDatas()
    {
        mSignInPresenter = new SignInPresenter();
        bindBaseMvpPresenter(mSignInPresenter);
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
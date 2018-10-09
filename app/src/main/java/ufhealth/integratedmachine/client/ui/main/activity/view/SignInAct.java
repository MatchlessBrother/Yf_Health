package ufhealth.integratedmachine.client.ui.main.activity.view;

import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;
import ufhealth.integratedmachine.client.R;
import ufhealth.integratedmachine.client.base.BaseAct;
import ufhealth.integratedmachine.client.ui.main.activity.view_v.SignInAct_V;
import ufhealth.integratedmachine.client.ui.main.activity.presenter.SignInPresenter;

public class SignInAct extends BaseAct implements SignInAct_V,View.OnClickListener
{
    private Button mSigninLogin;
    private TextView mSigninVersion;
    private EditText mSigninAccount;
    private EditText mSigninPassword;
    private SignInPresenter mSignInPresenter;

    protected int setLayoutResID()
    {
        return R.layout.activity_signin;

    }

    protected void initWidgets(View rootView)
    {
        super.initWidgets(rootView);
        mSigninLogin = (Button)rootView.findViewById(R.id.signin_login);
        mSigninVersion = (TextView)rootView.findViewById(R.id.signin_version);
        mSigninAccount = (EditText)rootView.findViewById(R.id.signin_account);
        mSigninPassword = (EditText)rootView.findViewById(R.id.signin_password);
        mSigninLogin.setOnClickListener(this);
    }

    protected void initDatas()
    {

    }

    protected void initLogic()
    {
        mSignInPresenter = new SignInPresenter();
        bindBaseMvpPresenter(mSignInPresenter);
    }

    public void onClick(View view)
    {
        super.onClick(view);
        switch(view.getId())
        {
            case R.id.signin_login:mSignInPresenter.signIn(mSigninAccount.getText().toString().trim(),mSigninPassword.getText().toString().trim());break;
        }
    }

    public void signInSuccess()
    {
        Intent intent = new Intent(this,MainAct.class);
        startActivity(intent);
        finish();
    }

    public void signInFailure()
    {

    }
}
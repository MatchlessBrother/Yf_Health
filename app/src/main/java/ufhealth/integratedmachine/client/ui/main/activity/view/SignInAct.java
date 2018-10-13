package ufhealth.integratedmachine.client.ui.main.activity.view;

import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;
import ufhealth.integratedmachine.client.R;
import ufhealth.integratedmachine.client.base.BaseAct;
import com.yuan.devlibrary._12_______Utils.SharepreferenceUtils;
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
    }

    protected void initDatas()
    {
        mSignInPresenter = new SignInPresenter();
        bindBaseMvpPresenter(mSignInPresenter);
    }

    protected void initLogic()
    {
        mSigninLogin.setOnClickListener(this);
        if(null != SharepreferenceUtils.extractObject(this,"password",String.class) && !"".equals(SharepreferenceUtils.extractObject(this,"password",String.class)) &&
                null != SharepreferenceUtils.extractObject(this,"account",String.class) && !"".equals(SharepreferenceUtils.extractObject(this,"account",String.class)))
        {
            Intent intent = new Intent(this,MainAct.class);
            intent.putExtra("islogined",false);
            startActivity(intent);
            finish();
        }
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
        intent.putExtra("islogined",true);
        startActivity(intent);
        finish();
    }

    public void signInFailure()
    {

    }

    public static void quitCrrentAccount(BaseAct baseAct,String noteStr)
    {
        SharepreferenceUtils.storageObject(baseAct,"password","");
        SharepreferenceUtils.storageObject(baseAct,"account","");
        Intent intent = new Intent(baseAct,SignInAct.class);
        baseAct.getBaseApp().finishAllActivity();
        baseAct.startActivity(intent);
        baseAct.showToast(noteStr);
    }
}
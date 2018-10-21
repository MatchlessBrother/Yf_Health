package ufhealth.integratedmachine.client.ui.main.activity.view;

import android.view.View;
import android.widget.EditText;
import ufhealth.integratedmachine.client.R;
import ufhealth.integratedmachine.client.base.BaseAct;
import com.yuan.devlibrary._12_______Utils.StringUtils;
import ufhealth.integratedmachine.client.ui.main.activity.view_v.ModifyPasswordAct_V;
import ufhealth.integratedmachine.client.ui.main.activity.presenter.ModifyPasswordPresenter;

public class ModifyPasswordAct extends BaseAct implements ModifyPasswordAct_V,View.OnClickListener
{
    private EditText mModifypasswordOldpassword;
    private EditText mModifypasswordNewpassword1;
    private EditText mModifypasswordNewpassword2;
    private ModifyPasswordPresenter mModifyPasswordPresenter;

    protected int setLayoutResID()
    {
        return R.layout.activity_modifypassword;

    }

    protected void initWidgets(View rootView)
    {
        super.initWidgets(rootView);
        setTitleMoreFont("完成");
        setTitleContent("修改密码");
        setTitleMoreFontVisible(View.VISIBLE);
        mModifypasswordOldpassword = (EditText) findViewById(R.id.modifypassword_oldpassword);
        mModifypasswordNewpassword1 = (EditText) findViewById(R.id.modifypassword_newpassword1);
        mModifypasswordNewpassword2 = (EditText) findViewById(R.id.modifypassword_newpassword2);
    }

    protected void initDatas()
    {
        mModifyPasswordPresenter = new ModifyPasswordPresenter();
        bindBaseMvpPresenter(mModifyPasswordPresenter);
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

    protected void onTitleMoreFontClick()
    {
        super.onTitleMoreFontClick();
        if(StringUtils.isEmpty(mModifypasswordOldpassword.getText().toString().trim()))
        {
            showToast("原始密码不能为空，请重新输入！");
            return;
        }
        else if(StringUtils.isEmpty(mModifypasswordNewpassword1.getText().toString().trim()) || StringUtils.isEmpty(mModifypasswordNewpassword2.getText().toString().trim()))
        {
            showToast("新密码不能为空，请重新输入！");
            return;
        }
        else if(!mModifypasswordNewpassword1.getText().toString().trim().equals(mModifypasswordNewpassword2.getText().toString().trim()))
        {
            showToast("输入的两次新密码不相同，请重新输入！");
            return;
        }
        mModifyPasswordPresenter.modifyPassword(mModifypasswordOldpassword.getText().toString().trim(),mModifypasswordNewpassword2.getText().toString().trim());
    }

    public void successOfModifyPassword()
    {
        /****************退出当前账号****************/
        showToast("修改密码成功！");
        //SignInAct.quitCrrentAccount(this,"修改密码成功！请重新登陆！");
    }
}
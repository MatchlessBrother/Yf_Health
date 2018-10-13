package ufhealth.integratedmachine.client.ui.main.fragment.view;

import android.view.View;
import android.content.Intent;
import android.widget.TextView;
import android.view.LayoutInflater;
import ufhealth.integratedmachine.client.R;
import ufhealth.integratedmachine.client.base.BaseAct;
import ufhealth.integratedmachine.client.base.BaseFrag;
import com.yuan.devlibrary._11___Widget.promptBox.BasePopupWindow;
import ufhealth.integratedmachine.client.ui.main.activity.view.SignInAct;
import ufhealth.integratedmachine.client.ui.main.fragment.view_v.MainHzFrag_V;
import ufhealth.integratedmachine.client.ui.main.activity.view.ModifyPasswordAct;
import ufhealth.integratedmachine.client.ui.main.fragment.presenter.MainHzPresenter;

public class MainHzFrag extends BaseFrag implements MainHzFrag_V,View.OnClickListener
{
    private TextView mMainhzfragBj;
    private TextView mMainhzfragYj;
    private TextView mMainhzfragSj;
    private TextView mMainhzfragSwcz;
    private TextView mMainhzfragTscz;
    private MainHzPresenter mMainHzPresenter;

    protected int setLayoutResID()
    {
        return R.layout.fragment_main_hz;
    }

    protected void initWidgets(View rootView)
    {
        super.initWidgets(rootView);
        setTitleContent("汇总统计");
        setTitleBack(R.mipmap.usericon);
        mMainhzfragBj = (TextView)rootView.findViewById(R.id.mainhzfrag_bj);
        mMainhzfragYj = (TextView)rootView.findViewById(R.id.mainhzfrag_yj);
        mMainhzfragSj = (TextView)rootView.findViewById(R.id.mainhzfrag_sj);
        mMainhzfragSwcz = (TextView)rootView.findViewById(R.id.mainhzfrag_swcz);
        mMainhzfragTscz = (TextView)rootView.findViewById(R.id.mainhzfrag_tscz);
    }

    protected void initDatas()
    {
        mMainHzPresenter = new MainHzPresenter();
        bindBaseMvpPresenter(mMainHzPresenter);
    }

    protected void initLogic()
    {
        mMainhzfragBj.setOnClickListener(this);
        mMainhzfragYj.setOnClickListener(this);
        mMainhzfragSj.setOnClickListener(this);
        mMainhzfragSwcz.setOnClickListener(this);
        mMainhzfragTscz.setOnClickListener(this);
    }

    public void onClick(View view)
    {
        super.onClick(view);
        switch (view.getId())
        {
            case R.id.mainhzfrag_bj:
            {
                break;
            }
            case R.id.mainhzfrag_yj:
            {
                break;
            }
            case R.id.mainhzfrag_sj:
            {
                break;
            }
            case R.id.mainhzfrag_swcz:
            {
                break;
            }
            case R.id.mainhzfrag_tscz:
            {
                break;
            }
        }
    }

    protected void onTitleBackClick()
    {
        final View basePopupWindowContent = LayoutInflater.from(mActivity).inflate(R.layout.dialog_signin_exit,null);
        TextView signInBtn =(TextView)basePopupWindowContent.findViewById(R.id.dialogsigninexit_signin);
        TextView exitBtn =(TextView)basePopupWindowContent.findViewById(R.id.dialogsigninexit_exit);
        final BasePopupWindow basePopupWindow  =  new BasePopupWindow(mActivity);
        basePopupWindow.setContentView(basePopupWindowContent);
        signInBtn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(mActivity,ModifyPasswordAct.class);
                if(basePopupWindow.isShowing()) basePopupWindow.dismiss();
                startActivity(intent);
            }
        });

        exitBtn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                if(basePopupWindow.isShowing()) basePopupWindow.dismiss();
                SignInAct.quitCrrentAccount((BaseAct)mActivity,"退出登录成功！");
            }
        });
        if(isUseDefaultTitleLine())
            basePopupWindow.showAsDropDown(mTitleBackBtn,12,6);
    }
}
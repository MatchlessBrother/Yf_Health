package ufhealth.integratedmachine.client.ui.main.fragment.view;

import android.view.View;
import android.content.Intent;
import android.widget.TextView;
import android.view.LayoutInflater;
import ufhealth.integratedmachine.client.R;
import android.support.v7.widget.RecyclerView;
import android.support.v4.widget.SwipeRefreshLayout;
import ufhealth.integratedmachine.client.base.BaseAct;
import ufhealth.integratedmachine.client.base.BaseFrag;
import com.yuan.devlibrary._11___Widget.promptBox.BasePopupWindow;
import ufhealth.integratedmachine.client.ui.main.activity.view.SignInAct;
import ufhealth.integratedmachine.client.ui.main.fragment.view_v.MainBjFrag_V;
import ufhealth.integratedmachine.client.ui.main.activity.view.ModifyPasswordAct;
import ufhealth.integratedmachine.client.ui.thirdtab.activity.view.BjczHistroyAct;
import ufhealth.integratedmachine.client.ui.main.fragment.presenter.MainBjPresenter;

public class MainBjFrag extends BaseFrag implements MainBjFrag_V,View.OnClickListener
{
    private RecyclerView mainbjfragRecycler;
    private MainBjPresenter mMainBjPresenter;
    private SwipeRefreshLayout mainbjfragSwiperefreshlayout;

    protected int setLayoutResID()
    {
        return R.layout.fragment_main_bj;
    }

    protected void initWidgets(View rootView)
    {
        super.initWidgets(rootView);
        setTitleMoreFont("历史");
        setTitleContent("报警处置");
        setTitleBack(R.mipmap.usericon);
        setTitleMoreFontVisible(View.VISIBLE);
        mainbjfragRecycler = (RecyclerView)rootView.findViewById(R.id.mainbjfrag_recycler);
        mainbjfragSwiperefreshlayout = (SwipeRefreshLayout)rootView.findViewById(R.id.mainbjfrag_swiperefreshlayout);
    }

    protected void initDatas()
    {
        mMainBjPresenter = new MainBjPresenter();
        bindBaseMvpPresenter(mMainBjPresenter);
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

    protected void onTitleMoreFontClick()
    {
        super.onTitleMoreFontClick();
        Intent intent = new Intent(mActivity, BjczHistroyAct.class);
        startActivity(intent);
    }
}
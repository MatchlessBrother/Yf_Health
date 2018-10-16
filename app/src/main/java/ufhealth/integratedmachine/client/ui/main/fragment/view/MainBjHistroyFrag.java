package ufhealth.integratedmachine.client.ui.main.fragment.view;

import android.view.View;
import android.content.Intent;
import android.widget.TextView;
import android.view.LayoutInflater;
import ufhealth.integratedmachine.client.R;
import android.support.v7.widget.RecyclerView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import ufhealth.integratedmachine.client.base.BaseAct;
import ufhealth.integratedmachine.client.base.BaseFrag;
import com.yuan.devlibrary._11___Widget.promptBox.BasePopupWindow;
import ufhealth.integratedmachine.client.ui.main.activity.view.SignInAct;
import ufhealth.integratedmachine.client.ui.main.activity.view.ModifyPasswordAct;
import ufhealth.integratedmachine.client.ui.main.fragment.view_v.MainBjHistroyFrag_V;
import ufhealth.integratedmachine.client.ui.main.fragment.presenter.MainBjHistroyPresenter;

public class MainBjHistroyFrag extends BaseFrag implements MainBjHistroyFrag_V,View.OnClickListener
{
    private RecyclerView mMainbjhistroyfragRecycler;
    private MainBjHistroyPresenter mMainBjHistroyPresenter;
    private SwipeRefreshLayout mMainbjhistroyfragSwiperefreshlayout;

    protected int setLayoutResID()
    {
        return R.layout.fragment_main_bjhistroy;
    }

    protected void initWidgets(View rootView)
    {
        super.initWidgets(rootView);
        setTitleContent("历史报警");
        setTitleBack(R.mipmap.usericon);
        setTitleMoreIcon(R.mipmap.searchicon);
        setTitleMoreIconVisible(View.VISIBLE);
        mMainbjhistroyfragSwiperefreshlayout = (SwipeRefreshLayout)rootView.findViewById(R.id.mainbjhistroyfrag_swiperefreshlayout);
        mMainbjhistroyfragRecycler = (RecyclerView)rootView.findViewById(R.id.mainbjhistroyfrag_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mMainbjhistroyfragRecycler.setLayoutManager(linearLayoutManager);
    }

    protected void initDatas()
    {
        mMainBjHistroyPresenter = new MainBjHistroyPresenter();
        bindBaseMvpPresenter(mMainBjHistroyPresenter);
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

    protected void onTitleMoreIconClick()
    {
        super.onTitleMoreFontClick();
        showToast("功能暂未开放,请耐心等待,谢谢!");
    }
}
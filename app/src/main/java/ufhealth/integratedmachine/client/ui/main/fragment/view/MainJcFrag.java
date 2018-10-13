package ufhealth.integratedmachine.client.ui.main.fragment.view;

import android.view.View;
import android.content.Intent;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import ufhealth.integratedmachine.client.R;
import android.support.v7.widget.RecyclerView;
import android.support.v4.widget.SwipeRefreshLayout;
import ufhealth.integratedmachine.client.base.BaseAct;
import ufhealth.integratedmachine.client.base.BaseFrag;
import com.yuan.devlibrary._11___Widget.promptBox.BasePopupWindow;
import ufhealth.integratedmachine.client.ui.main.activity.view.SignInAct;
import ufhealth.integratedmachine.client.ui.main.fragment.view_v.MainJcFrag_V;
import ufhealth.integratedmachine.client.ui.main.activity.view.ModifyPasswordAct;
import ufhealth.integratedmachine.client.ui.main.fragment.presenter.MainJcPresenter;

public class MainJcFrag extends BaseFrag implements MainJcFrag_V,View.OnClickListener
{
    private TextView mainjcfragLx;
    private TextView mainjcfragZt;
    private TextView mainjcfragLs;
    private LinearLayout mainjcfragLxAll;
    private LinearLayout mainjcfragZtAll;
    private RecyclerView mainjcfragRecycler;
    private MainJcPresenter mMainJcPresenter;
    private SwipeRefreshLayout mainjcfragSwiperefreshlayout;

    protected int setLayoutResID()
    {
        return R.layout.fragment_main_jc;
    }

    protected void initWidgets(View rootView)
    {
        super.initWidgets(rootView);
        setTitleContent("实时监测");
        setTitleBack(R.mipmap.usericon);
        setTitleMoreIcon(R.mipmap.searchicon);
        setTitleMoreIconVisible(View.VISIBLE);
        mainjcfragLx = (TextView)rootView.findViewById(R.id.mainjcfrag_lx);
        mainjcfragZt = (TextView)rootView.findViewById(R.id.mainjcfrag_zt);
        mainjcfragLs = (TextView)rootView.findViewById(R.id.mainjcfrag_ls);
        mainjcfragLxAll = (LinearLayout)rootView.findViewById(R.id.mainjcfrag_lx_all);
        mainjcfragZtAll = (LinearLayout)rootView.findViewById(R.id.mainjcfrag_zt_all);
        mainjcfragRecycler = (RecyclerView)rootView.findViewById(R.id.mainjcfrag_recycler);
        mainjcfragSwiperefreshlayout = (SwipeRefreshLayout)rootView.findViewById(R.id.mainjcfrag_swiperefreshlayout);
    }

    protected void initDatas()
    {
        mMainJcPresenter = new MainJcPresenter();
        bindBaseMvpPresenter(mMainJcPresenter);
    }

    protected void initLogic()
    {
        mainjcfragLs.setOnClickListener(this);
        mainjcfragLxAll.setOnClickListener(this);
        mainjcfragZtAll.setOnClickListener(this);
    }

    public void onClick(View view)
    {
        super.onClick(view);
        switch (view.getId())
        {
            case R.id.mainjcfrag_lx:
            {
                break;
            }
            case R.id.mainjcfrag_zt:
            {
                break;
            }
            case R.id.mainjcfrag_ls:
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

    protected void onTitleMoreIconClick()
    {
        super.onTitleMoreIconClick();
        showToast("开启搜索条件啊！");
    }
}
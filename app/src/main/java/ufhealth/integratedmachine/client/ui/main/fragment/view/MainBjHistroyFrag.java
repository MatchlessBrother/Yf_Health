package ufhealth.integratedmachine.client.ui.main.fragment.view;

import android.view.View;
import java.util.ArrayList;
import android.content.Intent;
import android.widget.TextView;
import android.view.LayoutInflater;
import ufhealth.integratedmachine.client.R;
import android.support.v7.widget.RecyclerView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import ufhealth.integratedmachine.client.base.BaseAct;
import com.chad.library.adapter.base.BaseQuickAdapter;
import ufhealth.integratedmachine.client.base.BaseFrag;
import com.yuan.devlibrary._11___Widget.promptBox.BasePopupWindow;
import ufhealth.integratedmachine.client.bean.third.BjHistroyPageInfo;
import ufhealth.integratedmachine.client.adapter.third.BjHistroyAdapter;
import ufhealth.integratedmachine.client.ui.main.activity.view.MainAct;
import ufhealth.integratedmachine.client.ui.main.activity.view.SignInAct;
import ufhealth.integratedmachine.client.ui.main.activity.view.ModifyPasswordAct;
import ufhealth.integratedmachine.client.ui.main.fragment.view_v.MainBjHistroyFrag_V;
import ufhealth.integratedmachine.client.ui.main.fragment.presenter.MainBjHistroyPresenter;

public class MainBjHistroyFrag extends BaseFrag implements MainBjHistroyFrag_V,View.OnClickListener
{
    private BjHistroyAdapter mBjHistroyAdapter;
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
        mBjHistroyAdapter = new BjHistroyAdapter(mActivity,new ArrayList<BjHistroyPageInfo.BjHistroyInfo>());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mMainbjhistroyfragRecycler.setLayoutManager(linearLayoutManager);
        mMainbjhistroyfragRecycler.setAdapter(mBjHistroyAdapter);
        mMainbjhistroyfragSwiperefreshlayout.setEnabled(true);
        mBjHistroyAdapter.setEnableLoadMore(true);
    }

    protected void initDatas()
    {
        mMainBjHistroyPresenter = new MainBjHistroyPresenter();
        bindBaseMvpPresenter(mMainBjHistroyPresenter);
    }

    protected void initLogic()
    {
        mMainBjHistroyPresenter.refreshDatas();
        mMainbjhistroyfragSwiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            public void onRefresh()
            {
                mMainBjHistroyPresenter.refreshDatas();
            }
        });

        mBjHistroyAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener()
        {
            public void onLoadMoreRequested()
            {
                mMainBjHistroyPresenter.loadMoreDatas();
            }
        },mMainbjhistroyfragRecycler);

        mBjHistroyAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener()
        {
            public void onItemClick(BaseQuickAdapter adapter, View view, int position)
            {
                showToast("已点击第" + (position + 1) + "个子选项 ! ");
            }
        });
    }

    public void finishRefresh()
    {
        mMainbjhistroyfragSwiperefreshlayout.setRefreshing(false);

    }

    public void finishLoadMore()
    {
        mBjHistroyAdapter.loadMoreComplete();

    }

    public void onClick(View view)
    {
        super.onClick(view);
        switch(view.getId())
        {

        }
    }

    public void refreshDatas(BjHistroyPageInfo bjHistroyPageInfo)
    {
        mBjHistroyAdapter.setNewData(bjHistroyPageInfo.getBjHistroyInfoList());
        if(bjHistroyPageInfo.getBjHistroyInfoList().size() < bjHistroyPageInfo.getMaxSizeOfPerPage())
            mBjHistroyAdapter.setEnableLoadMore(false);
        else
            mBjHistroyAdapter.setEnableLoadMore(true);
    }

    public void loadMoreDatas(BjHistroyPageInfo bjHistroyPageInfo)
    {
        mBjHistroyAdapter.addData(bjHistroyPageInfo.getBjHistroyInfoList());
        mBjHistroyAdapter.notifyDataSetChanged();
        if(bjHistroyPageInfo.getBjHistroyInfoList().size() < bjHistroyPageInfo.getMaxSizeOfPerPage())
            mBjHistroyAdapter.setEnableLoadMore(false);
        else
            mBjHistroyAdapter.setEnableLoadMore(true);
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
                    ((MainAct)mActivity).signOutAction();
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
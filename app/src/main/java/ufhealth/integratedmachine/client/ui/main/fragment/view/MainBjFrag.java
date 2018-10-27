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
import com.chad.library.adapter.base.BaseQuickAdapter;
import ufhealth.integratedmachine.client.base.BaseFrag;
import ufhealth.integratedmachine.client.bean.bjcz.BjczPageInfo;
import com.yuan.devlibrary._11___Widget.promptBox.BasePopupWindow;
import ufhealth.integratedmachine.client.adapter.bjcz.BjczAdapter;
import ufhealth.integratedmachine.client.ui.main.activity.view.MainAct;
import ufhealth.integratedmachine.client.ui.bjcz.activity.view.BjczAct;
import ufhealth.integratedmachine.client.ui.main.fragment.view_v.MainBjFrag_V;
import ufhealth.integratedmachine.client.ui.bjcz.activity.view.BjczHistroyAct;
import ufhealth.integratedmachine.client.ui.main.activity.view.ModifyPasswordAct;
import ufhealth.integratedmachine.client.ui.main.fragment.presenter.MainBjPresenter;

public class MainBjFrag extends BaseFrag implements MainBjFrag_V,View.OnClickListener
{
    private BjczAdapter mBjczAdapter;
    private RecyclerView mMainbjfragRecycler;
    private MainBjPresenter mMainBjPresenter;
    private SwipeRefreshLayout mMainbjfragSwiperefreshlayout;

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
        mMainbjfragSwiperefreshlayout = (SwipeRefreshLayout)rootView.findViewById(R.id.mainbjfrag_swiperefreshlayout);
        mMainbjfragRecycler = (RecyclerView)rootView.findViewById(R.id.mainbjfrag_recycler);
        mBjczAdapter = new BjczAdapter(mActivity,new ArrayList<BjczPageInfo.ContentBean>());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mMainbjfragRecycler.setLayoutManager(linearLayoutManager);
        mMainbjfragRecycler.setAdapter(mBjczAdapter);
        mMainbjfragSwiperefreshlayout.setEnabled(true);
        mBjczAdapter.setEnableLoadMore(true);
    }

    protected void initDatas()
    {
        mMainBjPresenter = new MainBjPresenter();
        bindBaseMvpPresenter(mMainBjPresenter);
    }

    protected void initLogic()
    {
        mMainBjPresenter.refreshDatas();
        mMainbjfragSwiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            public void onRefresh()
            {
                mMainBjPresenter.refreshDatas();
            }
        });

        mBjczAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener()
        {
            public void onLoadMoreRequested()
            {
                mMainBjPresenter.loadMoreDatas();
            }
        },mMainbjfragRecycler);

        mBjczAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener()
        {
            public void onItemClick(BaseQuickAdapter adapter, View view, int position)
            {
                showToast("已点击第" + (position + 1) + "个子选项 ! ");
            }
        });

        mBjczAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener()
        {
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position)
            {
                switch(view.getId())
                {
                    case R.id.item_mainbjczfragment_cancel:
                    {
                        break;
                    }
                    case R.id.item_mainbjczfragment_process:
                    {
                        Intent intent = new Intent(mActivity,BjczAct.class);
                        intent.putExtra("alarmid",((BjczPageInfo.ContentBean)adapter.getData().get(position)).getId() + "");
                        startActivity(intent);
                        break;
                    }
                }
            }
        });
    }

    public void finishRefresh()
    {
        mMainbjfragSwiperefreshlayout.setRefreshing(false);

    }

    public void finishLoadMore()
    {
        mBjczAdapter.loadMoreComplete();

    }

    public void onClick(View view)
    {
        super.onClick(view);
        switch(view.getId())
        {

        }
    }

    public void refreshDatas(BjczPageInfo bjczPageInfo)
    {
        mBjczAdapter.setNewData(bjczPageInfo.getContent());
        if(bjczPageInfo.getContent().size() < bjczPageInfo.getPageSize())
            mBjczAdapter.setEnableLoadMore(false);
        else
            mBjczAdapter.setEnableLoadMore(true);
    }

    public void loadMoreDatas(BjczPageInfo bjczPageInfo)
    {
        mBjczAdapter.addData(bjczPageInfo.getContent());
        mBjczAdapter.notifyDataSetChanged();
        if(bjczPageInfo.getContent().size() < bjczPageInfo.getPageSize())
            mBjczAdapter.setEnableLoadMore(false);
        else
            mBjczAdapter.setEnableLoadMore(true);
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

    protected void onTitleMoreFontClick()
    {
        super.onTitleMoreFontClick();
        startActivity(new Intent(mActivity, BjczHistroyAct.class));
    }
}
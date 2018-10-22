package ufhealth.integratedmachine.client.ui.bjcz.activity.view;

import android.view.View;
import java.util.ArrayList;
import ufhealth.integratedmachine.client.R;
import android.support.v7.widget.RecyclerView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import ufhealth.integratedmachine.client.base.BaseAct;
import ufhealth.integratedmachine.client.bean.bjcz.BjczHistroyPageInfo;
import ufhealth.integratedmachine.client.adapter.bjcz.BjczHistroyAdapter;
import ufhealth.integratedmachine.client.ui.bjcz.activity.view_v.BjczHistroyAct_V;
import ufhealth.integratedmachine.client.ui.bjcz.activity.presenter.BjczHistroyPresenter;

public class BjczHistroyAct extends BaseAct implements BjczHistroyAct_V,View.OnClickListener
{
    private RecyclerView mBjczHistroyRecycler;
    private BjczHistroyAdapter mBjczHistroyAdapter;
    private BjczHistroyPresenter mBjczHistroyPresenter;
    private SwipeRefreshLayout mBjczHistroySwiperefreshlayout;

    protected int setLayoutResID()
    {
        return R.layout.activity_bjczhistroy;
    }

    protected void initWidgets(View rootView)
    {
        super.initWidgets(rootView);
        setTitleContent("报警处置历史");
        mBjczHistroySwiperefreshlayout = (SwipeRefreshLayout)rootView.findViewById(R.id.bjczhistroy_swiperefreshlayout);
        mBjczHistroyAdapter = new BjczHistroyAdapter(mActivity,new ArrayList<BjczHistroyPageInfo.ContentBean>());
        mBjczHistroyRecycler = (RecyclerView)rootView.findViewById(R.id.bjczhistroy_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mBjczHistroyRecycler.setLayoutManager(linearLayoutManager);
        mBjczHistroyRecycler.setAdapter(mBjczHistroyAdapter);
        mBjczHistroySwiperefreshlayout.setEnabled(true);
        mBjczHistroyAdapter.setEnableLoadMore(true);
    }

    protected void initDatas()
    {
        mBjczHistroyPresenter = new BjczHistroyPresenter();
        bindBaseMvpPresenter(mBjczHistroyPresenter);
    }

    protected void initLogic()
    {
        mBjczHistroyPresenter.refreshDatas();
        mBjczHistroySwiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            public void onRefresh()
            {
                mBjczHistroyPresenter.refreshDatas();
            }
        });

        mBjczHistroyAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener()
        {
            public void onLoadMoreRequested()
            {
                mBjczHistroyPresenter.loadMoreDatas();
            }
        },mBjczHistroyRecycler);

        mBjczHistroyAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener()
        {
            public void onItemClick(BaseQuickAdapter adapter, View view, int position)
            {
                showToast("已点击第" + (position + 1) + "个子选项 ! ");
            }
        });
    }

    public void finishRefresh()
    {
        mBjczHistroySwiperefreshlayout.setRefreshing(false);

    }

    public void finishLoadMore()
    {
        mBjczHistroyAdapter.loadMoreComplete();

    }

    public void onClick(View view)
    {
        super.onClick(view);
        switch(view.getId())
        {

        }
    }

    public void refreshDatas(BjczHistroyPageInfo bjczHistroyPageInfo)
    {
        mBjczHistroyAdapter.setNewData(bjczHistroyPageInfo.getContent());
        if(bjczHistroyPageInfo.getContent().size() < bjczHistroyPageInfo.getPageSize())
            mBjczHistroyAdapter.setEnableLoadMore(false);
        else
            mBjczHistroyAdapter.setEnableLoadMore(true);
    }

    public void loadMoreDatas(BjczHistroyPageInfo bjczHistroyPageInfo)
    {
        mBjczHistroyAdapter.addData(bjczHistroyPageInfo.getContent());
        mBjczHistroyAdapter.notifyDataSetChanged();
        if(bjczHistroyPageInfo.getContent().size() < bjczHistroyPageInfo.getPageSize())
            mBjczHistroyAdapter.setEnableLoadMore(false);
        else
            mBjczHistroyAdapter.setEnableLoadMore(true);
    }
}
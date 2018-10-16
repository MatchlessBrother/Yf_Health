package ufhealth.integratedmachine.client.ui.fourth.activity.view;

import android.view.View;
import ufhealth.integratedmachine.client.R;
import android.support.v7.widget.RecyclerView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import ufhealth.integratedmachine.client.base.BaseAct;
import ufhealth.integratedmachine.client.ui.fourth.activity.view_v.BjczHistroyAct_V;
import ufhealth.integratedmachine.client.ui.fourth.activity.presenter.BjczHistroyPresenter;

public class BjczHistroyAct extends BaseAct implements BjczHistroyAct_V,View.OnClickListener
{
    private RecyclerView mBjczHistroyRecycler;
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
        mBjczHistroyRecycler = (RecyclerView)rootView.findViewById(R.id.bjczhistroy_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mBjczHistroyRecycler.setLayoutManager(linearLayoutManager);
    }

    protected void initDatas()
    {
        mBjczHistroyPresenter = new BjczHistroyPresenter();
        bindBaseMvpPresenter(mBjczHistroyPresenter);
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
}
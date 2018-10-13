package ufhealth.integratedmachine.client.ui.thirdtab.activity.view;

import android.view.View;
import ufhealth.integratedmachine.client.R;
import android.support.v7.widget.RecyclerView;
import android.support.v4.widget.SwipeRefreshLayout;
import ufhealth.integratedmachine.client.base.BaseAct;
import ufhealth.integratedmachine.client.ui.main.fragment.presenter.MainBjPresenter;
import ufhealth.integratedmachine.client.ui.thirdtab.activity.view_v.BjczHistroyAct_V;

public class BjczHistroyAct extends BaseAct implements BjczHistroyAct_V,View.OnClickListener
{
    private RecyclerView bjczhistroyRecycler;
    private MainBjPresenter mMainBjPresenter;
    private SwipeRefreshLayout bjczhistroySwiperefreshlayout;

    protected int setLayoutResID()
    {
        return R.layout.activity_bjczhistroy;
    }

    protected void initWidgets(View rootView)
    {
        super.initWidgets(rootView);
        setTitleContent("报警处置历史");
        bjczhistroyRecycler = (RecyclerView)rootView.findViewById(R.id.bjczhistroy_recycler);
        bjczhistroySwiperefreshlayout = (SwipeRefreshLayout)rootView.findViewById(R.id.bjczhistroy_swiperefreshlayout);
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
}
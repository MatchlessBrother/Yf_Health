package ufhealth.integratedmachine.client.ui.main.activity.view;

import android.view.View;
import android.view.Gravity;
import android.content.Intent;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.ImageView;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import ufhealth.integratedmachine.client.R;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.app.FragmentPagerAdapter;
import ufhealth.integratedmachine.client.base.BaseAct;
import com.yuan.devlibrary._12_______Utils.SharepreferenceUtils;
import ufhealth.integratedmachine.client.ui.main.fragment.view.MainBjFrag;
import ufhealth.integratedmachine.client.ui.main.fragment.view.MainHzFrag;
import ufhealth.integratedmachine.client.ui.main.fragment.view.MainJcFrag;
import ufhealth.integratedmachine.client.ui.main.activity.view_v.MainAct_V;
import ufhealth.integratedmachine.client.ui.main.activity.view_v.SignInAct_V;
import ufhealth.integratedmachine.client.ui.main.fragment.view.MainBjHistroyFrag;
import ufhealth.integratedmachine.client.ui.main.activity.presenter.MainPresenter;
import ufhealth.integratedmachine.client.ui.main.activity.presenter.SignInPresenter;

public class MainAct extends BaseAct implements MainAct_V,SignInAct_V,View.OnClickListener
{
    private ViewPager mViewPager;
    private MainPresenter mMainPresenter;
    private DrawerLayout mMainDrawerlayout;
    private SignInPresenter mSignInPresenter;
    private FragmentTabHost mFragmentTabHost;
    private LinearLayout mMainjcfragConditions;
    private LinearLayout mMainbjhistroyfragConditions;
    private String mTabSpecTv[] = { "汇总统计", "实时监测","历史报警","报警处置"};
    private Class[] mTabSpecFragClass = { MainHzFrag.class,MainJcFrag.class, MainBjHistroyFrag.class,MainBjFrag.class};
    private Fragment[] mTabSpecFrag = new Fragment[]{new MainHzFrag(),new MainJcFrag(),new MainBjHistroyFrag(),new MainBjFrag()};
    private int[] mTabSpecImg= { R.drawable.selector_tabspec_hz, R.drawable.selector_tabspec_jc,R.drawable.selector_tabspec_bjhistroy,R.drawable.selector_tabspec_bj };

    protected int setLayoutResID()
    {
        return R.layout.activity_main;
    }

    public View getRootView()
    {
        return mRootView;
    }

    protected void initWidgets(View rootView)
    {
        super.initWidgets(rootView);
        mViewPager = (ViewPager)rootView.findViewById(R.id.main_viewpager);
        mFragmentTabHost = (FragmentTabHost)rootView.findViewById(android.R.id.tabhost);
        mMainDrawerlayout = (DrawerLayout)rootView.findViewById(R.id.main_drawerlayout);
        mMainjcfragConditions = (LinearLayout)rootView.findViewById(R.id.mainjcfrag_conditions);
        mMainbjhistroyfragConditions = (LinearLayout)rootView.findViewById(R.id.mainbjhistroyfrag_conditions);
        /****************************************************************************************************/
        mFragmentTabHost.setup(this,getSupportFragmentManager(),R.id.main_viewpager);
        mMainDrawerlayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        mFragmentTabHost.getTabWidget().setDividerDrawable(null);
        mViewPager.setOffscreenPageLimit(1);
        for(int index = 0;index < mTabSpecTv.length;index++)
        {
            TabHost.TabSpec tabSpec = mFragmentTabHost.newTabSpec(mTabSpecTv[index]).setIndicator(getTabSpecView(index));
            /********************/mFragmentTabHost.addTab(tabSpec,mTabSpecFragClass[index],null); /********************/
            mFragmentTabHost.getTabWidget().getChildTabViewAt(index).setBackgroundResource(R.color.transparent);
        }
    }

    private View getTabSpecView(int index)
    {
        View view = LayoutInflater.from(this).inflate(R.layout.item_tabspec,null);
        ImageView tabSpecImg = (ImageView)view.findViewById(R.id.item_bottomtab_img);
        TextView tabSpecTv = (TextView)view.findViewById(R.id.item_bottomtab_tv);
        tabSpecImg.setBackgroundResource(mTabSpecImg[index]);
        tabSpecTv.setText(mTabSpecTv[index]);
        return view;
    }

    protected void initDatas()
    {
        mMainPresenter = new MainPresenter();
        mSignInPresenter = new SignInPresenter();
        bindBaseMvpPresenter(mMainPresenter);
        bindBaseMvpPresenter(mSignInPresenter);
    }

    protected void initLogic()
    {
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager())
        {
            public Fragment getItem(int position)
            {
                return mTabSpecFrag[position];

            }

            public int getCount()
            {
                return mTabSpecFrag.length;

            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {

            }

            public void onPageScrollStateChanged(int state)
            {

            }

            public void onPageSelected(int position)
            {
                mFragmentTabHost.setCurrentTab(position);

            }
        });

        mFragmentTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener()
        {
            public void onTabChanged(String tabId)
            {
                mViewPager.setCurrentItem(mFragmentTabHost.getCurrentTab());
                if(mFragmentTabHost.getCurrentTab() == 1)
                {
                    mMainjcfragConditions.setVisibility(View.VISIBLE);
                    mMainbjhistroyfragConditions.setVisibility(View.GONE);
                    mMainDrawerlayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                }
                else if(mFragmentTabHost.getCurrentTab() == 2)
                {
                   /*mMainjcfragConditions.setVisibility(View.GONE);
                    mMainbjhistroyfragConditions.setVisibility(View.VISIBLE);
                    mMainDrawerlayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);*/
                    mMainjcfragConditions.setVisibility(View.GONE);
                    mMainbjhistroyfragConditions.setVisibility(View.GONE);
                    mMainDrawerlayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                }
                else
                {
                    mMainjcfragConditions.setVisibility(View.GONE);
                    mMainbjhistroyfragConditions.setVisibility(View.GONE);
                    mMainDrawerlayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                }
            }
        });
        if(!getIntent().getBooleanExtra("islogined",false))
            mSignInPresenter.signIn(SharepreferenceUtils.extractObject(this,"username",String.class).trim(),SharepreferenceUtils.extractObject(this,"password",String.class).trim());
    }

    public void onClick(View view)
    {
        super.onClick(view);
        switch(view.getId())
        {

        }
    }

    public void signInSuccess()
    {

    }

    public void signInFailure()
    {
        SignInAct.quitCrrentAccount(this,"账号发生异常，请重新登录！");
        Intent intent = new Intent(this,SignInAct.class);
        startActivity(intent);
        finish();
    }

    public void signOutAction()
    {
        mMainPresenter.signOut();

    }

    public void signOutSuccess()
    {
        SignInAct.quitCrrentAccount((BaseAct)mActivity,"退出登录成功！");

    }

    public void signOutFailure()
    {

    }

    public void onBackPressed()
    {
        if(null != mMainDrawerlayout && mMainDrawerlayout.isDrawerOpen(Gravity.END))
            mMainDrawerlayout.closeDrawers();
        else
            super.onBackPressed();
    }
}
package ufhealth.integratedmachine.client.ui.main.activity.view;

import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.ImageView;
import android.view.LayoutInflater;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import ufhealth.integratedmachine.client.R;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentPagerAdapter;
import ufhealth.integratedmachine.client.base.BaseAct;
import com.yuan.devlibrary._12_______Utils.SharepreferenceUtils;
import ufhealth.integratedmachine.client.ui.main.fragment.view.MainBjFrag;
import ufhealth.integratedmachine.client.ui.main.fragment.view.MainHzFrag;
import ufhealth.integratedmachine.client.ui.main.fragment.view.MainJcFrag;
import ufhealth.integratedmachine.client.ui.main.activity.view_v.MainAct_V;
import ufhealth.integratedmachine.client.ui.main.activity.view_v.SignInAct_V;
import ufhealth.integratedmachine.client.ui.main.activity.presenter.MainPresenter;
import ufhealth.integratedmachine.client.ui.main.activity.presenter.SignInPresenter;

public class MainAct extends BaseAct implements MainAct_V,SignInAct_V,View.OnClickListener
{
    private ViewPager mViewPager;
    private MainPresenter mMainPresenter;
    private SignInPresenter mSignInPresenter;
    private FragmentTabHost mFragmentTabHost;
    private String mTabSpecTv[] = { "汇总统计", "实时监测","报警处置"};
    private Class[] mTabSpecFragClass = { MainHzFrag.class,MainJcFrag.class, MainBjFrag.class};
    private Fragment[] mTabSpecFrag = new Fragment[]{new MainHzFrag(),new MainJcFrag(),new MainBjFrag()};
    private int[] mTabSpecImg= { R.drawable.selector_tabspec_hz, R.drawable.selector_tabspec_jc,R.drawable.selector_tabspec_bj };

    protected int setLayoutResID()
    {
        return R.layout.activity_main;

    }

    protected void initWidgets(View rootView)
    {
        super.initWidgets(rootView);
        mViewPager          =  (ViewPager)        rootView.findViewById(R.id.main_viewpager);
        mFragmentTabHost = (FragmentTabHost)rootView.findViewById(android.R.id.tabhost);
        mFragmentTabHost.setup(this,getSupportFragmentManager(),R.id.main_viewpager);
        mFragmentTabHost.getTabWidget().setDividerDrawable(null);
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
        mViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager())
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
            }
        });
        if(!getIntent().getBooleanExtra("islogined",false))
            mSignInPresenter.signIn(SharepreferenceUtils.extractObject(this,"account",String.class).trim(),SharepreferenceUtils.extractObject(this,"password",String.class).trim());
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

    }
}
package ufhealth.integratedmachine.client.ui.main.fragment.view;

import android.view.View;
import android.content.Intent;
import android.widget.TextView;
import android.view.LayoutInflater;
import ufhealth.integratedmachine.client.R;
import android.support.v7.widget.RecyclerView;
import android.support.v4.widget.NestedScrollView;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import ufhealth.integratedmachine.client.base.BaseAct;
import ufhealth.integratedmachine.client.base.BaseFrag;
import com.yuan.devlibrary._11___Widget.promptBox.BasePopupWindow;
import ufhealth.integratedmachine.client.ui.main.activity.view.SignInAct;
import ufhealth.integratedmachine.client.ui.main.fragment.view_v.MainHzFrag_V;
import ufhealth.integratedmachine.client.ui.main.activity.view.ModifyPasswordAct;
import ufhealth.integratedmachine.client.ui.main.fragment.presenter.MainHzPresenter;

public class MainHzFrag extends BaseFrag implements MainHzFrag_V,View.OnClickListener
{
    private XAxis mXAxis;
    private YAxis mYAxis;
    private TextView mMainhzfragSj;
    private TextView mMainhzfragSwcz;
    private TextView mMainhzfragTscz;
    private RecyclerView mRecyclerView;
    private BarChart mMainhzfragBarchart;
    private TextView mainhzfragBarchartText;
    private MainHzPresenter mMainHzPresenter;
    private NestedScrollView mNestedScrollView;

    protected int setLayoutResID()
    {
        return R.layout.fragment_main_hz;
    }

    protected void initWidgets(View rootView)
    {
        super.initWidgets(rootView);
        setTitleContent("汇总统计");
        setTitleBack(R.mipmap.usericon);
        mMainhzfragSj = (TextView)rootView.findViewById(R.id.mainhzfrag_sj);
        mMainhzfragSwcz = (TextView)rootView.findViewById(R.id.mainhzfrag_swcz);
        mMainhzfragTscz = (TextView)rootView.findViewById(R.id.mainhzfrag_tscz);
        mMainhzfragBarchart = (BarChart)rootView.findViewById(R.id.mainhzfrag_barchart);
        mRecyclerView = (RecyclerView)rootView.findViewById(R.id.mainhzfrag_recyclerview);
        mainhzfragBarchartText = (TextView)rootView.findViewById(R.id.mainhzfrag_barchart_text);
        mNestedScrollView = (NestedScrollView)rootView.findViewById(R.id.mainhzfrag_nestedscrollview);

        mMainhzfragBarchart.setDrawBarShadow(false);
        mMainhzfragBarchart.setDrawValueAboveBar(true);
        mMainhzfragBarchart.setMaxVisibleValueCount(72);
        mMainhzfragBarchart.setDrawGridBackground(false);
        mMainhzfragBarchart.getDescription().setEnabled(false);
        mMainhzfragBarchart.getXAxis().setLabelRotationAngle(0);

        mXAxis = mMainhzfragBarchart.getXAxis();
        mXAxis.setLabelCount(7);
        mXAxis.setGranularity(1f);
        mXAxis.setAxisMaximum(6f);
        mXAxis.setAxisMinimum(0.5f);
        mXAxis.setDrawAxisLine(true);
        mXAxis.setDrawGridLines(false);
        mXAxis.setXOffset(0);
        mXAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        mMainhzfragBarchart.getAxisLeft().setDrawAxisLine(false);

        mYAxis = mMainhzfragBarchart.getAxisLeft();
        //设置Y左边轴显示的值 label 数量
        mYAxis.setLabelCount(8, false);
        //设置值显示的位置，我们这里设置为显示在Y轴外面
        mYAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        //设置Y轴 与值的空间空隙 这里设置30f意为30%空隙，默认是10%
        mYAxis.setSpaceTop(30f);
        //设置Y轴最小坐标和最大坐标
        mYAxis.setAxisMinimum(0f);
        mYAxis.setAxisMaximum(80f);
        mYAxis.setAxisMinValue(0f);
        //Y轴右边轴的设置，跟左边轴类似
        YAxis rightAxis = mMainhzfragBarchart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setLabelCount(8, false);
        rightAxis.setSpaceTop(30f);
        rightAxis.setAxisMinimum(0f);
        rightAxis.setAxisMaximum(80f);
    }

    protected void initDatas()
    {
        mMainHzPresenter = new MainHzPresenter();
        bindBaseMvpPresenter(mMainHzPresenter);
    }

    protected void initLogic()
    {
        mMainhzfragSj.setOnClickListener(this);
        mMainhzfragSwcz.setOnClickListener(this);
        mMainhzfragTscz.setOnClickListener(this);
        mainhzfragBarchartText.setOnClickListener(this);
    }

    public void onClick(View view)
    {
        super.onClick(view);
        switch (view.getId())
        {
            case R.id.mainhzfrag_sj:
            {
                break;
            }
            case R.id.mainhzfrag_swcz:
            {
                break;
            }
            case R.id.mainhzfrag_tscz:
            {
                break;
            }
            case R.id.mainhzfrag_barchart_text:
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
}
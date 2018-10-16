package ufhealth.integratedmachine.client.ui.main.fragment.view;

import java.util.List;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import java.util.ArrayList;
import android.content.Intent;
import android.widget.TextView;
import android.view.LayoutInflater;
import ufhealth.integratedmachine.client.R;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.GridLayoutManager;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import ufhealth.integratedmachine.client.base.BaseAct;
import ufhealth.integratedmachine.client.base.BaseFrag;
import ufhealth.integratedmachine.client.bean.first.BjTypeInfos;

import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.yuan.devlibrary._11___Widget.promptBox.BasePopupWindow;
import ufhealth.integratedmachine.client.adapter.first.BjTypeAdapter;
import ufhealth.integratedmachine.client.ui.main.activity.view.SignInAct;
import ufhealth.integratedmachine.client.ui.main.fragment.view_v.MainHzFrag_V;
import ufhealth.integratedmachine.client.ui.main.activity.view.ModifyPasswordAct;
import ufhealth.integratedmachine.client.ui.main.fragment.presenter.MainHzPresenter;

import static com.github.mikephil.charting.components.YAxis.YAxisLabelPosition.OUTSIDE_CHART;

public class MainHzFrag extends BaseFrag implements MainHzFrag_V,View.OnClickListener
{
    private XAxis mXAxis;
    private YAxis mYAxisLeft;
    private YAxis mYAxisRight;
    private TextView mMainhzfragSj;
    private TextView mMainhzfragSwcz;
    private TextView mMainhzfragTscz;
    private RecyclerView mRecyclerView;
    private BarChart mMainhzfragBarchart;
    private BjTypeAdapter mBjTypeAdapter;
    private TextView mMainhzfragBarchartText;
    private MainHzPresenter mMainHzPresenter;

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
        mMainhzfragBarchartText = (TextView)rootView.findViewById(R.id.mainhzfrag_barchart_text);
        mBjTypeAdapter = new BjTypeAdapter(mActivity,new ArrayList<BjTypeInfos>());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mActivity,3);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setFocusableInTouchMode(false);
        mRecyclerView.setAdapter(mBjTypeAdapter);
        /******************************************************************************************/
        /*************************************初始化柱状图控件*************************************/
        /******************************************************************************************/
        mMainhzfragBarchart.setPinchZoom(false);
        mMainhzfragBarchart.setDragEnabled(false);
        mMainhzfragBarchart.setTouchEnabled(false);
        mMainhzfragBarchart.setScaleXEnabled(false);
        mMainhzfragBarchart.setScaleYEnabled(false);
        mMainhzfragBarchart.setDrawBarShadow(false);
        mMainhzfragBarchart.setDrawValueAboveBar(true);
        mMainhzfragBarchart.setMaxVisibleValueCount(60);
        mMainhzfragBarchart.setDrawGridBackground(false);
        mMainhzfragBarchart.getDescription().setEnabled(false);
        mMainhzfragBarchart.setDragDecelerationFrictionCoef(0.3f);
        mMainhzfragBarchart.setNoDataText("报警监测数据图无可显示的数据！");

        mYAxisRight = mMainhzfragBarchart.getAxisRight();
        mYAxisRight.setEnabled(false);
        mYAxisLeft = mMainhzfragBarchart.getAxisLeft();
        mYAxisLeft.setSpaceTop(10f);
        mYAxisLeft.setEnabled(true);
        mYAxisLeft.setTextSize(12f);
        mYAxisLeft.setDrawLabels(true);
        mYAxisLeft.setAxisLineWidth(2);
        mYAxisLeft.setZeroLineWidth(6f);
        mYAxisLeft.setDrawAxisLine(true);
        mYAxisLeft.setDrawZeroLine(true);
        mYAxisLeft.setDrawGridLines(false);
        mYAxisLeft.setPosition(OUTSIDE_CHART);
        mYAxisLeft.setLabelCount(6,false);/***********/
        mYAxisLeft.setZeroLineColor(Color.BLACK);/***/
        mYAxisLeft.setTypeface(Typeface.DEFAULT_BOLD);
        mYAxisLeft.setTextColor(getResources().getColor(R.color.default_font_black));
        mYAxisLeft.setAxisLineColor(getResources().getColor(R.color.default_font_black));

        mXAxis = mMainhzfragBarchart.getXAxis();
        mXAxis.setXOffset(0);
        mXAxis.setTextSize(10f);
        mXAxis.setLabelCount(10);
        mXAxis.setGranularity(1f);
        mXAxis.setDrawAxisLine(true);
        mXAxis.setDrawGridLines(false);
        mXAxis.setLabelRotationAngle(0f);
        mXAxis.setTextColor(Color.BLACK);
        mXAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //mAxis.setValueFormatter(new MyCustomFormatter());
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
        mMainhzfragBarchartText.setOnClickListener(this);
        /***************************************************/
        /*********************模拟网络数据******************/
        /***************************************************/
        List<BjTypeInfos> bjTypeInfosList = new ArrayList<>();
        for(int index = 0;index < 8;index++)
        {
            BjTypeInfos bjTypeInfos = new BjTypeInfos();
            if(index % 4 == 0)
            {
                bjTypeInfos.setIconType(0);
                bjTypeInfos.setNoteStr("报警");
                bjTypeInfos.setAppearNumbers(6);
                bjTypeInfos.setBackgroundColor("ff0000");
            }
            else if(index % 4 == 1)
            {
                bjTypeInfos.setIconType(1);
                bjTypeInfos.setNoteStr("预警");
                bjTypeInfos.setAppearNumbers(8);
                bjTypeInfos.setBackgroundColor("00ff00");
            }
            else if(index % 4 == 2)
            {
                bjTypeInfos.setIconType(1);
                bjTypeInfos.setNoteStr("警告");
                bjTypeInfos.setAppearNumbers(12);
                bjTypeInfos.setBackgroundColor("0000FF");
            }
            else if(index % 4 == 3)
            {
                bjTypeInfos.setIconType(0);
                bjTypeInfos.setNoteStr("无所谓");
                bjTypeInfos.setAppearNumbers(18);
                bjTypeInfos.setBackgroundColor("FFA800");
            }

            bjTypeInfosList.add(bjTypeInfos);
        }
        mBjTypeAdapter.getData().addAll(bjTypeInfosList);
        mBjTypeAdapter.notifyDataSetChanged();

        List<BarEntry> entries = new ArrayList<BarEntry>();
        List<BarEntry> entries2 = new ArrayList<BarEntry>();
        for (int i = 0; i < 20; i++)
        {
            entries.add(new BarEntry(i, 20 * i));
            entries2.add(new BarEntry(i,10 * i));
        }

        BarDataSet dataSet = new BarDataSet(entries, "BarDataSet1"); // add entries to dataset
        dataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        BarDataSet dataSet2 = new BarDataSet(entries2, "BarDataSet2"); // add entries to dataset
        dataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        dataSet2.setColor(Color.RED);
        dataSet2.setBarBorderColor(Color.BLUE);

        List<IBarDataSet> dataSets=new ArrayList<IBarDataSet>();
        dataSets.add(dataSet);
        dataSets.add(dataSet2);
        //柱状图数据集
        BarData data = new BarData(dataSets);
        //设置柱子宽度
        data.setBarWidth(0.9f);
        mMainhzfragBarchart.setData(data);//装载数据
        mMainhzfragBarchart.setFitBars(true); //X轴自适应所有柱形图
        mMainhzfragBarchart.invalidate();//刷新
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
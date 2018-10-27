package ufhealth.integratedmachine.client.ui.main.fragment.view;

import java.util.Map;
import java.util.Date;
import java.util.List;
import java.util.HashMap;
import android.view.View;
import java.util.Calendar;
import java.util.ArrayList;
import android.view.Gravity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import android.widget.LinearLayout;
import android.view.LayoutInflater;
import ufhealth.integratedmachine.client.R;
import android.support.v7.widget.RecyclerView;
import android.support.v4.widget.DrawerLayout;
import com.bigkoo.pickerview.view.TimePickerView;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v4.widget.SwipeRefreshLayout;
import com.bigkoo.pickerview.view.OptionsPickerView;
import android.support.v7.widget.LinearLayoutManager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import ufhealth.integratedmachine.client.base.BaseFrag;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import ufhealth.integratedmachine.client.bean.hztj.TjDataInfo;
import ufhealth.integratedmachine.client.bean.hztj.TjCondition;

import com.google.gson.Gson;
import com.yuan.devlibrary._11___Widget.promptBox.BasePopupWindow;
import ufhealth.integratedmachine.client.adapter.hztj.TjTypeAdapter;
import ufhealth.integratedmachine.client.ui.main.activity.view.MainAct;
import ufhealth.integratedmachine.client.adapter.hztj.TjConditionAdapter;
import ufhealth.integratedmachine.client.ui.main.fragment.view_v.MainHzFrag_V;
import ufhealth.integratedmachine.client.ui.main.activity.view.ModifyPasswordAct;
import ufhealth.integratedmachine.client.ui.main.fragment.presenter.MainHzPresenter;
import ufhealth.integratedmachine.client.widget.EchartView;

public class MainHzFrag extends BaseFrag implements MainHzFrag_V,View.OnClickListener
{
    private TextView mMainhzfragSj;
    private TextView mMainhzfragSwcz;
    private TextView mMainhzfragTscz;
    private RecyclerView mRecyclerView;
    private TjTypeAdapter mTjTypeAdapter;
    private EchartView mMainhzfragEchartView;
    private TextView mMainhzfragBarchartText;
    private NestedScrollView mMainhzfragNestedscrollview;
    private SwipeRefreshLayout mMainhzfragSwiperefreshlayout;
    /******************************************************/
    /******************************************************/
    private TextView mMainhzfragLx;
    private TextView mMainhzfragZt;
    private TextView mMainhzfragEt;
    private TextView mMainhzfragSt;
    private DrawerLayout mDrawerLayout;
    private Button mMainhzfrag_SureBtn;
    private Button mMainhzfrag_ResetBtn;
    private LinearLayout mMainhzfragLxAll;
    private LinearLayout mMainhzfragZtAll;
    private LinearLayout mMainhzfragEtAll;
    private LinearLayout mMainhzfragStAll;
    private TjConditionAdapter mTjConditionAdapter;
    private RecyclerView mMainhzfragRecyclerConditions;
    /******************************************************/
    /******************************************************/
    private Date mEndTimeDate;
    private Date mStartTimeDate;
    private TjCondition mTjCondition;
    private int mCurrentSelectedLsItemOfIndex;
    private int mCurrentSelectedZtItemOfIndex;
    private Map<String,String> mConditionsMap;
    private TimePickerView mEndTimePickerView;
    private SimpleDateFormat mSimpleDateFormat;
    private TimePickerView mStartTimePickerView;
    private MainHzPresenter mMainHzPresenter;
    private OptionsPickerView mLsOptionsPickerView;
    private OptionsPickerView mZtOptionsPickerView;
    private List<TjCondition.CategoryVosBean> mLsList;
    private List<TjCondition.AlarmLevelVosBean> mZtList;

    protected int setLayoutResID()
    {
        return R.layout.fragment_main_hz;
    }

    protected void initWidgets(View rootView)
    {
        super.initWidgets(rootView);
        setTitleContent("汇总统计");
        setTitleBack(R.mipmap.usericon);
        setTitleMoreIcon(R.mipmap.searchicon);
        setTitleMoreIconVisible(View.VISIBLE);
        /**********************************控件初始化第一部分**************************************/
        mMainhzfragSj = (TextView)rootView.findViewById(R.id.mainhzfrag_sj);
        mMainhzfragSwcz = (TextView)rootView.findViewById(R.id.mainhzfrag_swcz);
        mMainhzfragTscz = (TextView)rootView.findViewById(R.id.mainhzfrag_tscz);
        mRecyclerView = (RecyclerView)rootView.findViewById(R.id.mainhzfrag_recyclerview);
        mMainhzfragEchartView = (EchartView) rootView.findViewById(R.id.mainhzfrag_echartview);
        mMainhzfragBarchartText = (TextView)rootView.findViewById(R.id.mainhzfrag_barchart_text);
        mMainhzfragNestedscrollview = (NestedScrollView)rootView.findViewById(R.id.mainhzfrag_nestedscrollview);
        mMainhzfragSwiperefreshlayout = (SwipeRefreshLayout)rootView.findViewById(R.id.mainhzfrag_swiperefreshlayout);
        mTjTypeAdapter = new TjTypeAdapter(mActivity,new ArrayList<TjDataInfo.AlarmQuantityVosBean>());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mActivity,3);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setFocusableInTouchMode(false);
        mRecyclerView.setAdapter(mTjTypeAdapter);
        /**********************************控件初始化第二部分**************************************/
        mMainhzfragRecyclerConditions = (RecyclerView)((MainAct)mActivity).getRootView().findViewById(R.id.mainhzfrag_conditions_recycler);
        mMainhzfragStAll = (LinearLayout)((MainAct)mActivity).getRootView().findViewById(R.id.mainhzfrag_conditions_starttime_all);
        mMainhzfragEtAll = (LinearLayout)((MainAct)mActivity).getRootView().findViewById(R.id.mainhzfrag_conditions_endtime_all);
        mMainhzfragZtAll = (LinearLayout)((MainAct)mActivity).getRootView().findViewById(R.id.mainhzfrag_conditions_zt_all);
        mMainhzfragLxAll = (LinearLayout)((MainAct)mActivity).getRootView().findViewById(R.id.mainhzfrag_conditions_lx_all);
        mMainhzfrag_ResetBtn = (Button)((MainAct)mActivity).getRootView().findViewById(R.id.mainhzfrag_conditions_reset);
        mMainhzfragSt = (TextView)((MainAct)mActivity).getRootView().findViewById(R.id.mainhzfrag_conditions_starttime);
        mMainhzfrag_SureBtn = (Button)((MainAct)mActivity).getRootView().findViewById(R.id.mainhzfrag_conditions_sure);
        mMainhzfragEt = (TextView)((MainAct)mActivity).getRootView().findViewById(R.id.mainhzfrag_conditions_endtime);
        mMainhzfragZt = (TextView)((MainAct)mActivity).getRootView().findViewById(R.id.mainhzfrag_conditions_zt);
        mMainhzfragLx = (TextView)((MainAct)mActivity).getRootView().findViewById(R.id.mainhzfrag_conditions_lx);
        mDrawerLayout = (DrawerLayout)((MainAct)mActivity).getRootView().findViewById(R.id.main_drawerlayout);
        mTjConditionAdapter = new TjConditionAdapter(mActivity,new ArrayList<MultiItemEntity>());
        LinearLayoutManager linearLayoutManagerConditions = new LinearLayoutManager(mActivity);
        linearLayoutManagerConditions.setOrientation(LinearLayoutManager.VERTICAL);
        mMainhzfragRecyclerConditions.setLayoutManager(linearLayoutManagerConditions);
        mTjConditionAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        mMainhzfragRecyclerConditions.setAdapter(mTjConditionAdapter);
        mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        /**********************************控件初始化第三部分**************************************/
        mLsOptionsPickerView = new OptionsPickerBuilder(mActivity, new OnOptionsSelectListener()
        {
            public void onOptionsSelect(int options1Index, int options2Index, int options3Index, View view)
            {
                mCurrentSelectedLsItemOfIndex = options1Index;
                mLsOptionsPickerView.setSelectOptions(mCurrentSelectedLsItemOfIndex);
                mMainhzfragLx.setText(mCurrentSelectedLsItemOfIndex > -1 && mCurrentSelectedLsItemOfIndex < mLsList.size() ? mLsList.get(mCurrentSelectedLsItemOfIndex).getPickerViewText().trim() : "");
            }
        }) .setTitleText("类型选择").setLabels("","","").setTitleSize(33)
                .setSubmitText("确定") .setCancelText("取消")
                .setSubCalSize(28).setContentTextSize(18)
                .setBgColor(getResources().getColor(R.color.white))
                .setTitleColor(getResources().getColor(R.color.white))
                .setSubmitColor(getResources().getColor(R.color.white))
                .setCancelColor(getResources().getColor(R.color.white))
                .setBackgroundId(getResources().getColor(R.color.white))
                .setTitleBgColor(getResources().getColor(R.color.colorPrimary))
                .setDividerColor(getResources().getColor(R.color.default_font_gray))
                .setOutSideCancelable(true).isRestoreItem(false).isCenterLabel(false)
                .setCyclic(false,false,false).setSelectOptions(0, 0, 0).build();

        mZtOptionsPickerView = new OptionsPickerBuilder(mActivity, new OnOptionsSelectListener()
        {
            public void onOptionsSelect(int options1Index, int options2Index, int options3Index, View view)
            {
                mCurrentSelectedZtItemOfIndex = options1Index;
                mZtOptionsPickerView.setSelectOptions(mCurrentSelectedZtItemOfIndex);
                mMainhzfragZt.setText(mCurrentSelectedZtItemOfIndex > -1 && mCurrentSelectedZtItemOfIndex < mZtList.size() ? mZtList.get(mCurrentSelectedZtItemOfIndex).getPickerViewText().trim() : "");
            }
        }) .setTitleText("状态选择").setLabels("","","").setTitleSize(33)
                .setSubmitText("确定") .setCancelText("取消")
                .setSubCalSize(28).setContentTextSize(18)
                .setBgColor(getResources().getColor(R.color.white))
                .setTitleColor(getResources().getColor(R.color.white))
                .setSubmitColor(getResources().getColor(R.color.white))
                .setCancelColor(getResources().getColor(R.color.white))
                .setBackgroundId(getResources().getColor(R.color.white))
                .setTitleBgColor(getResources().getColor(R.color.colorPrimary))
                .setDividerColor(getResources().getColor(R.color.default_font_gray))
                .setOutSideCancelable(true).isRestoreItem(false).isCenterLabel(false)
                .setCyclic(false,false,false).setSelectOptions(0, 0, 0).build();
        /******************************************************************************************/
        Calendar startDateRange = Calendar.getInstance();
        startDateRange.set(2000,0,1);
        Calendar endDateRange = Calendar.getInstance();
        endDateRange.setTime(new Date());
        Calendar endTimeCalendar = Calendar.getInstance();
        endTimeCalendar.setTime(new Date());
        Calendar startTimeCalendar = Calendar.getInstance();
        startTimeCalendar.setTime(new Date());
        mEndTimePickerView = new TimePickerBuilder(mActivity,new OnTimeSelectListener()
        {
            public void onTimeSelect(Date date, View view)
            {
                mEndTimeDate = date;
                mMainhzfragEt.setText(mSimpleDateFormat.format(mEndTimeDate));
            }
        }).setTitleText("结束时间").setTitleSize(33)
                .setSubmitText("确定") .setCancelText("取消")
                .setSubCalSize(28).setContentTextSize(18)
                .setBgColor(getResources().getColor(R.color.white))
                .setTitleColor(getResources().getColor(R.color.white))
                .setSubmitColor(getResources().getColor(R.color.white))
                .setCancelColor(getResources().getColor(R.color.white))
                .setBackgroundId(getResources().getColor(R.color.white))
                .setTitleBgColor(getResources().getColor(R.color.colorPrimary))
                .setDividerColor(getResources().getColor(R.color.default_font_gray))
                .setType(new boolean[]{ true , true , true , false , false , false })
                .setOutSideCancelable(true).isCenterLabel(false).setLabel("年","月","日","时","分","秒")
                .setRangDate(startDateRange,endDateRange).setDate(endTimeCalendar).isCyclic(true).build();

        mStartTimePickerView = new TimePickerBuilder(mActivity,new OnTimeSelectListener()
        {
            public void onTimeSelect(Date date, View view)
            {
                mStartTimeDate = date;
                mMainhzfragSt.setText(mSimpleDateFormat.format(mStartTimeDate));
            }
        }).setTitleText("开始时间").setTitleSize(33)
                .setSubmitText("确定") .setCancelText("取消")
                .setSubCalSize(28).setContentTextSize(18)
                .setBgColor(getResources().getColor(R.color.white))
                .setTitleColor(getResources().getColor(R.color.white))
                .setSubmitColor(getResources().getColor(R.color.white))
                .setCancelColor(getResources().getColor(R.color.white))
                .setBackgroundId(getResources().getColor(R.color.white))
                .setTitleBgColor(getResources().getColor(R.color.colorPrimary))
                .setDividerColor(getResources().getColor(R.color.default_font_gray))
                .setType(new boolean[]{ true , true , true , false , false , false })
                .setOutSideCancelable(true).isCenterLabel(false).setLabel("年","月","日","时","分","秒")
                .setRangDate(startDateRange,endDateRange).setDate(startTimeCalendar).isCyclic(true).build();
    }

    protected void initDatas()
    {
        mMainHzPresenter = new MainHzPresenter();
        bindBaseMvpPresenter(mMainHzPresenter);
        mCurrentSelectedLsItemOfIndex = -1;
        mCurrentSelectedZtItemOfIndex = -1;
        mConditionsMap = new HashMap<>();
        mEndTimeDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, -5);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        mStartTimeDate = calendar.getTime();
        mMainhzfragEt.setText(mSimpleDateFormat.format(mEndTimeDate));
        mMainhzfragSt.setText(mSimpleDateFormat.format(mStartTimeDate));
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(mEndTimeDate);
        mEndTimePickerView.setDate(endCalendar);
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(mStartTimeDate);
        mStartTimePickerView.setDate(startCalendar);
    }

    protected void initLogic()
    {
        updateConditionsMap();
        mMainHzPresenter.getConditions(false);
        mMainHzPresenter.getDatas(mConditionsMap);
        mMainhzfragSwiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            public void onRefresh()
            {
                updateConditionsMap();
                mMainHzPresenter.getDatas(mConditionsMap);
            }
        });

        mTjTypeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener()
        {
            public void onItemClick(BaseQuickAdapter adapter, View view, int position)
            {

            }
        });
        mMainhzfragSj.setOnClickListener(this);
        mMainhzfragSwcz.setOnClickListener(this);
        mMainhzfragTscz.setOnClickListener(this);
        mMainhzfragLxAll.setOnClickListener(this);
        mMainhzfragZtAll.setOnClickListener(this);
        mMainhzfragStAll.setOnClickListener(this);
        mMainhzfragEtAll.setOnClickListener(this);
        mMainhzfrag_SureBtn.setOnClickListener(this);
        mMainhzfrag_ResetBtn.setOnClickListener(this);
        mMainhzfragBarchartText.setOnClickListener(this);
    }

    public void onClick(View view)
    {
        super.onClick(view);
        if(mTjCondition == null)
        {
            mMainHzPresenter.getConditions(true);
            return;
        }
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
            case R.id.mainhzfrag_conditions_lx_all:
            {
                if(null != mLsList && mLsList.size() > 0)
                {
                    mLsOptionsPickerView.setSelectOptions(mCurrentSelectedLsItemOfIndex);
                    mLsOptionsPickerView.show();
                }
                else
                    showToast("没有可以选择的内容！");
                break;
            }
            case R.id.mainhzfrag_conditions_zt_all:
            {
                if(null != mZtList && mZtList.size() > 0)
                {
                    mZtOptionsPickerView.setSelectOptions(mCurrentSelectedZtItemOfIndex);
                    mZtOptionsPickerView.show();
                }
                else
                    showToast("没有可以选择的内容！");
                break;
            }
            case R.id.mainhzfrag_conditions_endtime_all:
            {
                Calendar calendar = Calendar.getInstance();
                if(null != mEndTimeDate)
                    calendar.setTime(mEndTimeDate);
                else
                    calendar.setTime(new Date(  ));
                mEndTimePickerView.setDate(calendar);
                mEndTimePickerView.show();
                break;
            }
            case R.id.mainhzfrag_conditions_starttime_all:
            {
                Calendar calendar = Calendar.getInstance();
                if(null != mStartTimeDate)
                    calendar.setTime(mStartTimeDate);
                else
                    calendar.setTime(new Date(  ));
                mStartTimePickerView.setDate(calendar);
                mStartTimePickerView.show();
                break;
            }
            case R.id.mainhzfrag_conditions_reset:
            {
                mTjConditionAdapter.initAdapterConfigure(mTjConditionAdapter.getData().size() > 0 ? (TjCondition.DepartmentDeviceVosBean)mTjConditionAdapter.getData().get(0) : null);
                mTjConditionAdapter.notifyDataSetChanged();

                if(null != mLsList)
                {
                    mCurrentSelectedLsItemOfIndex = mLsList.size() > 0 ? 0 : -1;
                    if(mCurrentSelectedLsItemOfIndex > -1 && mCurrentSelectedLsItemOfIndex < mLsList.size())mLsOptionsPickerView.setSelectOptions(mCurrentSelectedLsItemOfIndex);
                    mMainhzfragLx.setText(mCurrentSelectedLsItemOfIndex > -1 && mCurrentSelectedLsItemOfIndex < mLsList.size() ? mLsList.get(mCurrentSelectedLsItemOfIndex).getPickerViewText().trim() : "");
                }

               if(null != mZtList)
               {
                   mCurrentSelectedZtItemOfIndex = mZtList.size() > 0 ? 0 : -1;
                   if(mCurrentSelectedZtItemOfIndex > -1 && mCurrentSelectedZtItemOfIndex < mZtList.size())mZtOptionsPickerView.setSelectOptions(mCurrentSelectedZtItemOfIndex);
                   mMainhzfragZt.setText(mCurrentSelectedZtItemOfIndex > -1 && mCurrentSelectedZtItemOfIndex < mZtList.size() ? mZtList.get(mCurrentSelectedZtItemOfIndex).getPickerViewText().trim() : "");
               }

                mEndTimeDate = new Date();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date());
                calendar.add(Calendar.MONTH, -5);
                calendar.set(Calendar.DAY_OF_MONTH, 1);
                mStartTimeDate = calendar.getTime();
                mMainhzfragEt.setText(mSimpleDateFormat.format(mEndTimeDate));
                mMainhzfragSt.setText(mSimpleDateFormat.format(mStartTimeDate));
                Calendar endCalendar = Calendar.getInstance();
                endCalendar.setTime(mEndTimeDate);
                mEndTimePickerView.setDate(endCalendar);
                Calendar startCalendar = Calendar.getInstance();
                startCalendar.setTime(mStartTimeDate);
                mStartTimePickerView.setDate(startCalendar);
                break;
            }
            case R.id.mainhzfrag_conditions_sure:
            {
                updateConditionsMap();
                mDrawerLayout.closeDrawers();
                mMainHzPresenter.getDatas(mConditionsMap);
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
                    ((MainAct)mActivity).signOutAction();
            }
        });
        if(isUseDefaultTitleLine())
            basePopupWindow.showAsDropDown(mTitleBackBtn,12,6);
    }

    protected void updateConditionsMap()
    {
        if(null == mConditionsMap)
            mConditionsMap = new HashMap<>();

        if(null == mEndTimeDate)
            mConditionsMap.put("endDate","");
        else
            mConditionsMap.put("endDate",mSimpleDateFormat.format(mEndTimeDate));

        if(null == mStartTimeDate)
            mConditionsMap.put("startDate","");
        else
            mConditionsMap.put("startDate",mSimpleDateFormat.format(mStartTimeDate));

        if(mCurrentSelectedLsItemOfIndex > -1 && mCurrentSelectedLsItemOfIndex < mLsList.size())
            mConditionsMap.put("categoryId",String.valueOf(null != mLsList.get(mCurrentSelectedLsItemOfIndex).getId() ? mLsList.get(mCurrentSelectedLsItemOfIndex).getId().trim() : ""));
        else
            mConditionsMap.put("categoryId","");

        if(mCurrentSelectedZtItemOfIndex > -1 && mCurrentSelectedZtItemOfIndex < mZtList.size())
            mConditionsMap.put("alarmId",String.valueOf(null != mZtList.get(mCurrentSelectedZtItemOfIndex).getId() ? mZtList.get(mCurrentSelectedZtItemOfIndex).getId().trim() : ""));
        else
            mConditionsMap.put("alarmId","");

        mConditionsMap.put("departmentId",String.valueOf(mTjConditionAdapter.getmSelectedParentCode() != -1 ? mTjConditionAdapter.getmSelectedParentCode() : ""));
        if(mTjConditionAdapter.isSelectedChildCondition())
            mConditionsMap.put("deviceAreaId",String.valueOf(mTjConditionAdapter.getmSelectedChildCode() != -1 ? mTjConditionAdapter.getmSelectedChildCode() : ""));
        else
        {
            if(mConditionsMap.containsKey("deviceAreaId"))
            {
                mConditionsMap.remove("deviceAreaId");
                mConditionsMap.put("deviceAreaId","");
            }
        }
    }

    protected void onTitleMoreIconClick()
    {
        super.onTitleMoreIconClick();
        if(null != mTjCondition)
        {
            if(!mDrawerLayout.isDrawerOpen(Gravity.END))
            {
                mDrawerLayout.openDrawer(Gravity.END);
            }
        }
        else
        {
            mMainHzPresenter.getConditions(true);
        }
    }

    public void initBarGraph(final TjDataInfo tjDataInfo)
    {
        mMainhzfragEchartView.loadUrl("file:///android_asset/stat.html");
        if(null != mMainhzfragEchartView)
        {
            mMainhzfragEchartView.setWebViewClient(new WebViewClient()
            {
                public void onPageFinished(WebView view, String url)
                {
                    super.onPageFinished(view, url);
                    mMainhzfragEchartView.refreshEchartsViewWithDataJson(new Gson().toJson(tjDataInfo));
                }
            });
        }
    }

    public void getFailureOfDatas()
    {
        if(mMainhzfragSwiperefreshlayout.isRefreshing())
            mMainhzfragSwiperefreshlayout.setRefreshing(false);
    }

    public void getSuccessOfDatas(TjDataInfo tjDataInfo)
    {
        if(mMainhzfragSwiperefreshlayout.isRefreshing())
            mMainhzfragSwiperefreshlayout.setRefreshing(false);
        mMainhzfragBarchartText.setText("近" + (null != tjDataInfo.getMonthAlramRecordStat() ? tjDataInfo.getMonthAlramRecordStat().size() : "0") + "月报警监测数据图");

        if(null != tjDataInfo)
        {
            mMainhzfragSj.setText(null != tjDataInfo.getDataSyncExceptionQuantity() ? tjDataInfo.getDataSyncExceptionQuantity().trim() : "0");
            mTjTypeAdapter.setNewData(tjDataInfo.getAlarmQuantityVos());
            if(null != tjDataInfo.getHandleStatusStat())
            {
                for(int index = 0;index < tjDataInfo.getHandleStatusStat().size();index++)
                {
                    if(null != tjDataInfo.getHandleStatusStat().get(index).getHandleStatus() && tjDataInfo.getHandleStatusStat().get(index).getHandleStatus().equals("1"))
                        mMainhzfragSwcz.setText(null != tjDataInfo.getHandleStatusStat().get(index).getQuantity() ? tjDataInfo.getHandleStatusStat().get(index).getQuantity().trim() : "0");
                    else if(null != tjDataInfo.getHandleStatusStat().get(index).getHandleStatus() && tjDataInfo.getHandleStatusStat().get(index).getHandleStatus().equals("2"))
                        mMainhzfragTscz.setText(null != tjDataInfo.getHandleStatusStat().get(index).getQuantity() ? tjDataInfo.getHandleStatusStat().get(index).getQuantity().trim() : "0");
                }
            }
            //初始化表格数据
            initBarGraph(tjDataInfo);
        }
    }

    public void getSuccessOfConditions(TjCondition tjCondition, boolean isNeedDrawableLayout)
    {
        if(null != tjCondition)
        {
            mTjCondition = tjCondition;
            mLsList = new ArrayList<>();
            mLsList.addAll(null != mTjCondition.getCategoryVos() ? mTjCondition.getCategoryVos() : new ArrayList<TjCondition.CategoryVosBean>());
            mCurrentSelectedLsItemOfIndex = mLsList.size() > 0 ? 0 : -1;
            mLsOptionsPickerView.setNPicker(mLsList,null,null);
            if(mCurrentSelectedLsItemOfIndex > -1 && mCurrentSelectedLsItemOfIndex < mLsList.size())mLsOptionsPickerView.setSelectOptions(mCurrentSelectedLsItemOfIndex);
            mMainhzfragLx.setText(mCurrentSelectedLsItemOfIndex > -1 && mCurrentSelectedLsItemOfIndex < mLsList.size() ? mLsList.get(mCurrentSelectedLsItemOfIndex).getPickerViewText().trim() : "");
            /**********************************************************************************************************************************************************************************************/
            mZtList = new ArrayList<>();
            mZtList.addAll(null != mTjCondition.getAlarmLevelVos() ? mTjCondition.getAlarmLevelVos() : new ArrayList<TjCondition.AlarmLevelVosBean>());
            mCurrentSelectedZtItemOfIndex = mZtList.size() > 0 ? 0 : -1;
            mZtOptionsPickerView.setNPicker(mZtList,null,null);
            if(mCurrentSelectedZtItemOfIndex > -1 && mCurrentSelectedZtItemOfIndex < mZtList.size())mZtOptionsPickerView.setSelectOptions(mCurrentSelectedZtItemOfIndex);
            mMainhzfragZt.setText(mCurrentSelectedZtItemOfIndex > -1 && mCurrentSelectedZtItemOfIndex < mZtList.size() ? mZtList.get(mCurrentSelectedZtItemOfIndex).getPickerViewText().trim() : "");
            /**********************************************************************************************************************************************************************************************/
            for(int parrentIndex = 0;parrentIndex < mTjCondition.getDepartmentDeviceVos().size();parrentIndex++)
            {
                for(int childIndex = 0;childIndex < mTjCondition.getDepartmentDeviceVos().get(parrentIndex).getDeviceAreaList().size();childIndex++)
                {
                    mTjCondition.getDepartmentDeviceVos().get(parrentIndex).addSubItem(mTjCondition.getDepartmentDeviceVos().get(parrentIndex).getDeviceAreaList().get(childIndex));
                }
            }
            List<MultiItemEntity> recyclerConditions = new ArrayList<>();
            recyclerConditions.addAll(mTjCondition.getDepartmentDeviceVos());
            mTjConditionAdapter.initAdapterConfigure(mTjCondition.getDepartmentDeviceVos().size() > 0 ? mTjCondition.getDepartmentDeviceVos().get(0) : null);
            mTjConditionAdapter.setNewData(recyclerConditions);
            if(isNeedDrawableLayout)
            {
                if(!mDrawerLayout.isDrawerOpen(Gravity.END))
                {
                    mDrawerLayout.openDrawer(Gravity.END);
                }
            }
            else
            {
                if(mDrawerLayout.isDrawerOpen(Gravity.END))
                {
                    mDrawerLayout.closeDrawers();
                }
            }
        }
    }
}
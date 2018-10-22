package ufhealth.integratedmachine.client.ui.main.fragment.view;

import java.util.Map;
import java.util.Date;
import java.util.List;
import java.util.HashMap;
import android.view.View;
import java.util.Calendar;
import java.util.ArrayList;
import android.view.Gravity;
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
import android.support.v7.widget.GridLayoutManager;
import com.bigkoo.pickerview.view.OptionsPickerView;
import android.support.v7.widget.LinearLayoutManager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import ufhealth.integratedmachine.client.base.BaseFrag;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import ufhealth.integratedmachine.client.bean.ssjc.JcType;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import ufhealth.integratedmachine.client.bean.ssjc.JcStatus;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import ufhealth.integratedmachine.client.bean.hztj.BjTypeInfo;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.yuan.devlibrary._11___Widget.promptBox.BasePopupWindow;
import ufhealth.integratedmachine.client.adapter.hztj.BjTypeAdapter;
import ufhealth.integratedmachine.client.bean.ssjc.JcChildCondition;
import ufhealth.integratedmachine.client.bean.ssjc.JcParentCondition;
import ufhealth.integratedmachine.client.ui.main.activity.view.MainAct;
import ufhealth.integratedmachine.client.adapter.ssjc.JcConditionAdapter;
import ufhealth.integratedmachine.client.ui.main.fragment.view_v.MainHzFrag_V;
import ufhealth.integratedmachine.client.ui.main.activity.view.ModifyPasswordAct;
import ufhealth.integratedmachine.client.ui.main.fragment.presenter.MainHzPresenter;

public class MainHzFrag extends BaseFrag implements MainHzFrag_V,View.OnClickListener
{
    private TextView mMainhzfragSj;
    private TextView mMainhzfragSwcz;
    private TextView mMainhzfragTscz;
    private RecyclerView mRecyclerView;
    private BjTypeAdapter mBjTypeAdapter;
    private TextView mMainhzfragBarchartText;
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
    private JcConditionAdapter mJcConditionAdapter;
    private RecyclerView mMainhzfragRecyclerConditions;
    /******************************************************/
    /******************************************************/
    private Date mEndTimeDate;
    private Date mStartTimeDate;
    private List<JcType> mLsList;
    private List<JcStatus> mZtList;
    private int mCurrentSelectedLsItemOfIndex;
    private int mCurrentSelectedZtItemOfIndex;
    private Map<String,String> mConditionsMap;
    private TimePickerView mEndTimePickerView;
    private SimpleDateFormat mSimpleDateFormat;
    private TimePickerView mStartTimePickerView;
    private OptionsPickerView mLsOptionsPickerView;
    private OptionsPickerView mZtOptionsPickerView;
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
        setTitleMoreIcon(R.mipmap.searchicon);
        setTitleMoreIconVisible(View.VISIBLE);
        /**********************************控件初始化第一部分**************************************/
        mMainhzfragSj = (TextView)rootView.findViewById(R.id.mainhzfrag_sj);
        mMainhzfragSwcz = (TextView)rootView.findViewById(R.id.mainhzfrag_swcz);
        mMainhzfragTscz = (TextView)rootView.findViewById(R.id.mainhzfrag_tscz);
        mRecyclerView = (RecyclerView)rootView.findViewById(R.id.mainhzfrag_recyclerview);
        mMainhzfragBarchartText = (TextView)rootView.findViewById(R.id.mainhzfrag_barchart_text);
        mBjTypeAdapter = new BjTypeAdapter(mActivity,new ArrayList<BjTypeInfo>());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mActivity,3);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setFocusableInTouchMode(false);
        mRecyclerView.setAdapter(mBjTypeAdapter);
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
        mJcConditionAdapter = new JcConditionAdapter(mActivity,new ArrayList<MultiItemEntity>());
        LinearLayoutManager linearLayoutManagerConditions = new LinearLayoutManager(mActivity);
        linearLayoutManagerConditions.setOrientation(LinearLayoutManager.VERTICAL);
        mMainhzfragRecyclerConditions.setLayoutManager(linearLayoutManagerConditions);
        mJcConditionAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        mMainhzfragRecyclerConditions.setAdapter(mJcConditionAdapter);
        mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        /**********************************控件初始化第三部分**************************************/
        mLsOptionsPickerView = new OptionsPickerBuilder(mActivity, new OnOptionsSelectListener()
        {
            public void onOptionsSelect(int options1Index, int options2Index, int options3Index, View view)
            {
                mCurrentSelectedLsItemOfIndex = options1Index;
                mLsOptionsPickerView.setSelectOptions(mCurrentSelectedLsItemOfIndex);
                mMainhzfragLx.setText(mCurrentSelectedLsItemOfIndex > -1 && mCurrentSelectedLsItemOfIndex < mLsList.size() ? mLsList.get(mCurrentSelectedLsItemOfIndex).getTypeName().trim() : "");
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
                mMainhzfragZt.setText(mCurrentSelectedZtItemOfIndex > -1 && mCurrentSelectedZtItemOfIndex < mZtList.size() ? mZtList.get(mCurrentSelectedZtItemOfIndex).getStatusName().trim() : "");
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

        Calendar startDateRange = Calendar.getInstance();
        startDateRange.set(2000,0,1);
        Calendar endDateRange = Calendar.getInstance();
        endDateRange.setTime(new Date());
        Calendar endTimeCalendar = Calendar.getInstance();
        endTimeCalendar.setTime(new Date());
        Calendar startTimeCalendar = Calendar.getInstance();
        endTimeCalendar.setTime(new Date());
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
        mConditionsMap = new HashMap<>();
        mCurrentSelectedLsItemOfIndex = -1;
        mCurrentSelectedZtItemOfIndex = -1;
        mMainHzPresenter = new MainHzPresenter();
        bindBaseMvpPresenter(mMainHzPresenter);
        /*************************************************
         ********************模拟网络数据*****************
         *************************************************/
        mLsList = new ArrayList<>();
        mLsList.add(new JcType(1,"高危"));
        mLsList.add(new JcType(2,"危险"));
        mLsList.add(new JcType(3,"警告"));
        mLsList.add(new JcType(4,"预警"));
        mLsList.add(new JcType(5,"注意"));
        mLsOptionsPickerView.setNPicker(mLsList,null,null);
        mCurrentSelectedLsItemOfIndex = 0;
        mLsOptionsPickerView.setSelectOptions(mCurrentSelectedLsItemOfIndex);
        mMainhzfragLx.setText(mCurrentSelectedLsItemOfIndex > -1 && mCurrentSelectedLsItemOfIndex < mLsList.size() ? mLsList.get(mCurrentSelectedLsItemOfIndex).getTypeName().trim() : "");

        mZtList = new ArrayList<>();
        mZtList.add(new JcStatus(1,"未开始"));
        mZtList.add(new JcStatus(2,"准备开始"));
        mZtList.add(new JcStatus(3,"已开始"));
        mZtList.add(new JcStatus(4,"进行中"));
        mZtList.add(new JcStatus(5,"已完成"));
        mZtList.add(new JcStatus(6,"准备结束"));
        mZtList.add(new JcStatus(7,"已结束"));
        mZtOptionsPickerView.setNPicker(mZtList,null,null);
        mCurrentSelectedZtItemOfIndex = 0;
        mZtOptionsPickerView.setSelectOptions(mCurrentSelectedZtItemOfIndex);
        mMainhzfragZt.setText(mCurrentSelectedZtItemOfIndex > -1 && mCurrentSelectedZtItemOfIndex < mZtList.size() ? mZtList.get(mCurrentSelectedZtItemOfIndex).getStatusName().trim() : "");

        JcParentCondition jcParentCondition  = new JcParentCondition(0,false,"全部");
        List<MultiItemEntity> conditions = new ArrayList<>();
        conditions.add(jcParentCondition);
        for (int parentIndex = 1; parentIndex <= 6; parentIndex++)
        {
            JcParentCondition parentCondition = new JcParentCondition(parentIndex,false,"部门" + parentIndex);
            for (int childIndex = 1; childIndex <= 6; childIndex++)
            {
                JcChildCondition childCondition = new JcChildCondition(childIndex,false,"区域" + childIndex);
                parentCondition.addSubItem(childCondition);
            }
            conditions.add(parentCondition);
        }
        mJcConditionAdapter.initAdapterConfigure(jcParentCondition);
        mJcConditionAdapter.setNewData(conditions);
    }

    protected void initLogic()
    {
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
        /***************************************************/
        /*********************模拟网络数据******************/
        /***************************************************/
        List<BjTypeInfo> bjTypeInfoList = new ArrayList<>();
        for(int index = 0;index < 8;index++)
        {
            BjTypeInfo bjTypeInfo = new BjTypeInfo();
            if(index % 4 == 0)
            {
                bjTypeInfo.setIconType(0);
                bjTypeInfo.setNoteStr("报警");
                bjTypeInfo.setAppearNumbers(6);
                bjTypeInfo.setBackgroundColor("ff0000");
            }
            else if(index % 4 == 1)
            {
                bjTypeInfo.setIconType(1);
                bjTypeInfo.setNoteStr("预警");
                bjTypeInfo.setAppearNumbers(8);
                bjTypeInfo.setBackgroundColor("00ff00");
            }
            else if(index % 4 == 2)
            {
                bjTypeInfo.setIconType(1);
                bjTypeInfo.setNoteStr("警告");
                bjTypeInfo.setAppearNumbers(12);
                bjTypeInfo.setBackgroundColor("0000FF");
            }
            else if(index % 4 == 3)
            {
                bjTypeInfo.setIconType(0);
                bjTypeInfo.setNoteStr("无所谓");
                bjTypeInfo.setAppearNumbers(18);
                bjTypeInfo.setBackgroundColor("FFA800");
            }
            bjTypeInfoList.add(bjTypeInfo);
        }
        mBjTypeAdapter.getData().addAll(bjTypeInfoList);
        mBjTypeAdapter.notifyDataSetChanged();
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
                mJcConditionAdapter.initAdapterConfigure((JcParentCondition) mJcConditionAdapter.getData().get(0));
                mJcConditionAdapter.notifyDataSetChanged();

                mCurrentSelectedLsItemOfIndex = 0;
                mLsOptionsPickerView.setSelectOptions(mCurrentSelectedLsItemOfIndex);
                mMainhzfragLx.setText(mCurrentSelectedLsItemOfIndex > -1 && mCurrentSelectedLsItemOfIndex < mLsList.size() ? mLsList.get(mCurrentSelectedLsItemOfIndex).getTypeName().trim() : "");

                mCurrentSelectedZtItemOfIndex = 0;
                mZtOptionsPickerView.setSelectOptions(mCurrentSelectedZtItemOfIndex);
                mMainhzfragZt.setText(mCurrentSelectedZtItemOfIndex > -1 && mCurrentSelectedZtItemOfIndex < mZtList.size() ? mZtList.get(mCurrentSelectedZtItemOfIndex).getStatusName().trim() : "");

                mEndTimeDate = null;
                mStartTimeDate = null;
                mMainhzfragEt.setText("");
                mMainhzfragSt.setText("");
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date());/**********/
                mStartTimePickerView.setDate(calendar);
                mEndTimePickerView.setDate(calendar);
                break;
            }
            case R.id.mainhzfrag_conditions_sure:
            {
                updateConditionsMap();
                mDrawerLayout.closeDrawers();
                StringBuffer buffer = new StringBuffer();
                for(Map.Entry<String,String> entry : mConditionsMap.entrySet())
                    buffer.append(entry.getKey() + "：" + entry.getValue() + "\r\n");
                showToast(buffer.toString().trim());
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
            mConditionsMap.put("endTime","");
        else
            mConditionsMap.put("endTime",mSimpleDateFormat.format(mEndTimeDate));


        if(null == mStartTimeDate)
            mConditionsMap.put("startTime","");
        else
            mConditionsMap.put("startTime",mSimpleDateFormat.format(mStartTimeDate));


        if(mCurrentSelectedLsItemOfIndex > -1 && mCurrentSelectedLsItemOfIndex < mLsList.size())
            mConditionsMap.put("type",String.valueOf(mLsList.get(mCurrentSelectedLsItemOfIndex).getTypeCode()));
        else
            mConditionsMap.put("type","");


        if(mCurrentSelectedZtItemOfIndex > -1 && mCurrentSelectedZtItemOfIndex < mZtList.size())
            mConditionsMap.put("status",String.valueOf(mZtList.get(mCurrentSelectedZtItemOfIndex).getStatusCode()));
        else
            mConditionsMap.put("status","");


        mConditionsMap.put("partmentId",String.valueOf(mJcConditionAdapter.getmSelectedParentCode()));
        if(mJcConditionAdapter.isSelectedChildCondition())
            mConditionsMap.put("areaId",String.valueOf(mJcConditionAdapter.getmSelectedChildCode()));
        else
        {
            if(mConditionsMap.containsKey("areaId"))
            {
                mConditionsMap.remove("areaId");
                mConditionsMap.put("areaId","");
            }
        }
    }

    protected void onTitleMoreIconClick()
    {
        super.onTitleMoreIconClick();
        if(!mDrawerLayout.isDrawerOpen(Gravity.END))
            mDrawerLayout.openDrawer(Gravity.END);
    }
}
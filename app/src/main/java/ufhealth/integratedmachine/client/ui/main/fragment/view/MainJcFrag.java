package ufhealth.integratedmachine.client.ui.main.fragment.view;

import java.util.Map;
import java.util.List;
import java.util.Date;
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
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import com.bigkoo.pickerview.view.TimePickerView;
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
import ufhealth.integratedmachine.client.bean.hztj.TjCondition;
import com.yuan.devlibrary._11___Widget.promptBox.BasePopupWindow;
import ufhealth.integratedmachine.client.ui.main.activity.view.MainAct;
import ufhealth.integratedmachine.client.adapter.ssjc.JcConditionAdapter;
import ufhealth.integratedmachine.client.ui.main.fragment.view_v.MainJcFrag_V;
import ufhealth.integratedmachine.client.ui.main.activity.view.ModifyPasswordAct;
import ufhealth.integratedmachine.client.ui.main.fragment.presenter.MainJcPresenter;

public class MainJcFrag extends BaseFrag implements MainJcFrag_V,View.OnClickListener
{
    /******************************************************/
    /******************************************************/
    private RecyclerView mMainjcfragRecycler;
    private SwipeRefreshLayout mMainjcfragSwiperefreshlayout;
    /******************************************************/
    /******************************************************/
    private TextView mMainjcfragLx;
    private TextView mMainjcfragZt;
    private TextView mMainjcfragEt;
    private TextView mMainjcfragSt;
    private DrawerLayout mDrawerLayout;
    private Button mMainjcfrag_SureBtn;
    private Button mMainjcfrag_ResetBtn;
    private LinearLayout mMainjcfragLxAll;
    private LinearLayout mMainjcfragZtAll;
    private LinearLayout mMainjcfragEtAll;
    private LinearLayout mMainjcfragStAll;
    private JcConditionAdapter mJcConditionAdapter;
    private RecyclerView mMainjcfragRecyclerConditions;
    /******************************************************/
    /******************************************************/
    private Date mEndTimeDate;
    private Date mStartTimeDate;
    private TjCondition mTjCondition;
    private MainJcPresenter mMainJcPresenter;
    private int mCurrentSelectedLsItemOfIndex;
    private int mCurrentSelectedZtItemOfIndex;
    private Map<String,String> mConditionsMap;
    private TimePickerView mEndTimePickerView;
    private SimpleDateFormat mSimpleDateFormat;
    private TimePickerView mStartTimePickerView;
    private OptionsPickerView mLsOptionsPickerView;
    private OptionsPickerView mZtOptionsPickerView;
    private List<TjCondition.CategoryVosBean> mLsList;
    private List<TjCondition.AlarmLevelVosBean> mZtList;


    protected int setLayoutResID()
    {
        return R.layout.fragment_main_jc;
    }

    protected void initWidgets(View rootView)
    {
        super.initWidgets(rootView);
        setTitleContent("实时监测");
        setTitleBack(R.mipmap.usericon);
        setTitleMoreIcon(R.mipmap.searchicon);
        setTitleMoreIconVisible(View.VISIBLE);
        /**********************************控件初始化第一部分**************************************/
        mMainjcfragSwiperefreshlayout = (SwipeRefreshLayout)rootView.findViewById(R.id.mainjcfrag_swiperefreshlayout);
        mMainjcfragRecycler = (RecyclerView)rootView.findViewById(R.id.mainjcfrag_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
        linearLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        mMainjcfragRecycler.setLayoutManager(linearLayoutManager);
        mMainjcfragSwiperefreshlayout.setEnabled(true);
        /**********************************控件初始化第二部分**************************************/
        mMainjcfragRecyclerConditions = (RecyclerView)((MainAct)mActivity).getRootView().findViewById(R.id.mainjcfrag_conditions_recycler);
        mMainjcfragStAll = (LinearLayout)((MainAct)mActivity).getRootView().findViewById(R.id.mainjcfrag_conditions_starttime_all);
        mMainjcfragEtAll = (LinearLayout)((MainAct)mActivity).getRootView().findViewById(R.id.mainjcfrag_conditions_endtime_all);
        mMainjcfragZtAll = (LinearLayout)((MainAct)mActivity).getRootView().findViewById(R.id.mainjcfrag_conditions_zt_all);
        mMainjcfragLxAll = (LinearLayout)((MainAct)mActivity).getRootView().findViewById(R.id.mainjcfrag_conditions_lx_all);
        mMainjcfrag_ResetBtn = (Button)((MainAct)mActivity).getRootView().findViewById(R.id.mainjcfrag_conditions_reset);
        mMainjcfragSt = (TextView)((MainAct)mActivity).getRootView().findViewById(R.id.mainjcfrag_conditions_starttime);
        mMainjcfrag_SureBtn = (Button)((MainAct)mActivity).getRootView().findViewById(R.id.mainjcfrag_conditions_sure);
        mMainjcfragEt = (TextView)((MainAct)mActivity).getRootView().findViewById(R.id.mainjcfrag_conditions_endtime);
        mMainjcfragZt = (TextView)((MainAct)mActivity).getRootView().findViewById(R.id.mainjcfrag_conditions_zt);
        mMainjcfragLx = (TextView)((MainAct)mActivity).getRootView().findViewById(R.id.mainjcfrag_conditions_lx);
        mDrawerLayout = (DrawerLayout)((MainAct)mActivity).getRootView().findViewById(R.id.main_drawerlayout);
        mJcConditionAdapter = new JcConditionAdapter(mActivity,new ArrayList<MultiItemEntity>());
        LinearLayoutManager linearLayoutManagerConditions = new LinearLayoutManager(mActivity);
        linearLayoutManagerConditions.setOrientation(LinearLayoutManager.VERTICAL);
        mMainjcfragRecyclerConditions.setLayoutManager(linearLayoutManagerConditions);
        mJcConditionAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        mMainjcfragRecyclerConditions.setAdapter(mJcConditionAdapter);
        mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        /**********************************控件初始化第三部分**************************************/
        mLsOptionsPickerView = new OptionsPickerBuilder(mActivity, new OnOptionsSelectListener()
        {
            public void onOptionsSelect(int options1Index, int options2Index, int options3Index, View view)
            {
                mCurrentSelectedLsItemOfIndex = options1Index;
                mLsOptionsPickerView.setSelectOptions(mCurrentSelectedLsItemOfIndex);
                mMainjcfragLx.setText(mCurrentSelectedLsItemOfIndex > -1 && mCurrentSelectedLsItemOfIndex < mLsList.size() ? mLsList.get(mCurrentSelectedLsItemOfIndex).getPickerViewText().trim() : "");
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
                mMainjcfragZt.setText(mCurrentSelectedZtItemOfIndex > -1 && mCurrentSelectedZtItemOfIndex < mZtList.size() ? mZtList.get(mCurrentSelectedZtItemOfIndex).getPickerViewText().trim() : "");
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
        endTimeCalendar.setTime(new Date());
        mEndTimePickerView = new TimePickerBuilder(mActivity,new OnTimeSelectListener()
        {
            public void onTimeSelect(Date date, View view)
            {
                mEndTimeDate = date;
                mMainjcfragEt.setText(mSimpleDateFormat.format(mEndTimeDate));
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
                mMainjcfragSt.setText(mSimpleDateFormat.format(mStartTimeDate));
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
        mMainJcPresenter = new MainJcPresenter();
        bindBaseMvpPresenter(mMainJcPresenter);
        /*************************************************
        *********************模拟网络数据*****************
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
        mMainjcfragLx.setText(mCurrentSelectedLsItemOfIndex > -1 && mCurrentSelectedLsItemOfIndex < mLsList.size() ? mLsList.get(mCurrentSelectedLsItemOfIndex).getTypeName().trim() : "");

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
        mMainjcfragZt.setText(mCurrentSelectedZtItemOfIndex > -1 && mCurrentSelectedZtItemOfIndex < mZtList.size() ? mZtList.get(mCurrentSelectedZtItemOfIndex).getStatusName().trim() : "");

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
        mMainjcfragLxAll.setOnClickListener(this);
        mMainjcfragZtAll.setOnClickListener(this);
        mMainjcfragStAll.setOnClickListener(this);
        mMainjcfragEtAll.setOnClickListener(this);
        mMainjcfrag_SureBtn.setOnClickListener(this);
        mMainjcfrag_ResetBtn.setOnClickListener(this);
    }

    public void onClick(View view)
    {
        super.onClick(view);
        switch (view.getId())
        {
            case R.id.mainjcfrag_conditions_lx_all:
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
            case R.id.mainjcfrag_conditions_zt_all:
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
            case R.id.mainjcfrag_conditions_endtime_all:
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
            case R.id.mainjcfrag_conditions_starttime_all:
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
            case R.id.mainjcfrag_conditions_reset:
            {
                mJcConditionAdapter.initAdapterConfigure((JcParentCondition) mJcConditionAdapter.getData().get(0));
                mJcConditionAdapter.notifyDataSetChanged();

                mCurrentSelectedLsItemOfIndex = 0;
                mLsOptionsPickerView.setSelectOptions(mCurrentSelectedLsItemOfIndex);
                mMainjcfragLx.setText(mCurrentSelectedLsItemOfIndex > -1 && mCurrentSelectedLsItemOfIndex < mLsList.size() ? mLsList.get(mCurrentSelectedLsItemOfIndex).getTypeName().trim() : "");

                mCurrentSelectedZtItemOfIndex = 0;
                mZtOptionsPickerView.setSelectOptions(mCurrentSelectedZtItemOfIndex);
                mMainjcfragZt.setText(mCurrentSelectedZtItemOfIndex > -1 && mCurrentSelectedZtItemOfIndex < mZtList.size() ? mZtList.get(mCurrentSelectedZtItemOfIndex).getStatusName().trim() : "");

                mEndTimeDate = null;
                mStartTimeDate = null;
                mMainjcfragEt.setText("");
                mMainjcfragSt.setText("");
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date());/**********/
                mStartTimePickerView.setDate(calendar);
                mEndTimePickerView.setDate(calendar);
                break;
            }
            case R.id.mainjcfrag_conditions_sure:
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
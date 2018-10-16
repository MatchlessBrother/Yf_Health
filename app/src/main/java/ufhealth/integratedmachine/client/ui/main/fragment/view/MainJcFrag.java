package ufhealth.integratedmachine.client.ui.main.fragment.view;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import android.view.View;
import java.util.ArrayList;

import android.content.Intent;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.view.LayoutInflater;
import ufhealth.integratedmachine.client.R;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v4.widget.SwipeRefreshLayout;
import com.bigkoo.pickerview.view.OptionsPickerView;
import android.support.v7.widget.LinearLayoutManager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import ufhealth.integratedmachine.client.base.BaseAct;
import ufhealth.integratedmachine.client.base.BaseFrag;
import ufhealth.integratedmachine.client.bean.secondtab.JcType;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import ufhealth.integratedmachine.client.bean.secondtab.JcStatus;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.yuan.devlibrary._11___Widget.promptBox.BasePopupWindow;
import ufhealth.integratedmachine.client.bean.secondtab.JcChildCondition;
import ufhealth.integratedmachine.client.bean.secondtab.JcParentCondition;
import ufhealth.integratedmachine.client.adapter.secondtab.JcConditionAdapter;
import ufhealth.integratedmachine.client.ui.main.activity.view.SignInAct;
import ufhealth.integratedmachine.client.ui.main.fragment.view_v.MainJcFrag_V;
import ufhealth.integratedmachine.client.ui.main.activity.view.ModifyPasswordAct;
import ufhealth.integratedmachine.client.ui.main.fragment.presenter.MainJcPresenter;

public class MainJcFrag extends BaseFrag implements MainJcFrag_V,View.OnClickListener
{
    private TextView mainjcfragLx;
    private TextView mainjcfragZt;
    private TextView mainjcfragLs;
    private LinearLayout mainjcfragAll;
    private LinearLayout mainjcfragLxAll;
    private LinearLayout mainjcfragZtAll;
    private RecyclerView mainjcfragRecycler;
    private JcConditionAdapter jcConditionAdapter;
    private RecyclerView mainjcfragRecyclerConditions;
    private SwipeRefreshLayout mainjcfragSwiperefreshlayout;
    /******************************************************/
    /******************************************************/
    private List<JcType> mLsList;
    private List<JcStatus> mZtList;
    private MainJcPresenter mMainJcPresenter;
    private int mCurrentSelectedLsItemOfIndex;
    private int mCurrentSelectedZtItemOfIndex;
    private Map<String,String> mConditionsMap;
    private OptionsPickerView mLsOptionsPickerView;
    private OptionsPickerView mZtOptionsPickerView;

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
        mainjcfragLx = (TextView)rootView.findViewById(R.id.mainjcfrag_lx);
        mainjcfragZt = (TextView)rootView.findViewById(R.id.mainjcfrag_zt);
        mainjcfragLs = (TextView)rootView.findViewById(R.id.mainjcfrag_ls);
        mainjcfragAll = (LinearLayout)rootView.findViewById(R.id.mainjcfrag_all);
        mainjcfragLxAll = (LinearLayout)rootView.findViewById(R.id.mainjcfrag_lx_all);
        mainjcfragZtAll = (LinearLayout)rootView.findViewById(R.id.mainjcfrag_zt_all);
        mainjcfragRecycler = (RecyclerView)rootView.findViewById(R.id.mainjcfrag_recycler);
        mainjcfragRecyclerConditions = (RecyclerView)rootView.findViewById(R.id.mainjcfrag_recycler_conditions);
        mainjcfragSwiperefreshlayout = (SwipeRefreshLayout)rootView.findViewById(R.id.mainjcfrag_swiperefreshlayout);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mActivity,2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        mainjcfragRecycler.setLayoutManager(gridLayoutManager);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mainjcfragRecyclerConditions.setLayoutManager(linearLayoutManager);
        jcConditionAdapter = new JcConditionAdapter(mActivity,new ArrayList<MultiItemEntity>())
        {
            public void selectedCondtionForComplete()
            {
                mainjcfragRecyclerConditions.setVisibility(View.GONE);
            }
        };
        jcConditionAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        mainjcfragRecyclerConditions.setAdapter(jcConditionAdapter);
        /******************************************************************************************/
        /******************************************************************************************/
        mLsOptionsPickerView = new OptionsPickerBuilder(mActivity, new OnOptionsSelectListener()
        {
            public void onOptionsSelect(int options1Index, int options2Index, int options3Index, View view)
            {
                mCurrentSelectedLsItemOfIndex = options1Index;
                mLsOptionsPickerView.setSelectOptions(mCurrentSelectedLsItemOfIndex);
                mainjcfragLx.setText(mCurrentSelectedLsItemOfIndex > -1 && mCurrentSelectedLsItemOfIndex < mLsList.size() ? mLsList.get(mCurrentSelectedLsItemOfIndex).getTypeName().trim() : "");
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
                mainjcfragZt.setText(mCurrentSelectedZtItemOfIndex > -1 && mCurrentSelectedZtItemOfIndex < mZtList.size() ? mZtList.get(mCurrentSelectedZtItemOfIndex).getStatusName().trim() : "");
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
    }

    protected void initDatas()
    {
        mConditionsMap = new HashMap<>();
        mCurrentSelectedLsItemOfIndex = -1;
        mCurrentSelectedZtItemOfIndex = -1;
        mMainJcPresenter = new MainJcPresenter();
        bindBaseMvpPresenter(mMainJcPresenter);
        /***************************************************/
        /*********************模拟网络数据******************/
        /***************************************************/
        mLsList = new ArrayList<>();
        mLsList.add(new JcType(1,"高危"));
        mLsList.add(new JcType(2,"危险"));
        mLsList.add(new JcType(3,"警告"));
        mLsList.add(new JcType(4,"预警"));
        mLsList.add(new JcType(5,"注意"));
        mLsOptionsPickerView.setNPicker(mLsList,null,null);

        mZtList = new ArrayList<>();
        mZtList.add(new JcStatus(1,"未开始"));
        mZtList.add(new JcStatus(2,"准备开始"));
        mZtList.add(new JcStatus(3,"已开始"));
        mZtList.add(new JcStatus(4,"进行中"));
        mZtList.add(new JcStatus(5,"已完成"));
        mZtList.add(new JcStatus(6,"准备结束"));
        mZtList.add(new JcStatus(7,"已结束"));
        mZtOptionsPickerView.setNPicker(mZtList,null,null);

        List<MultiItemEntity> conditions = new ArrayList<>();
        for (int parentIndex = 1; parentIndex <= 6; parentIndex++)
        {
            JcParentCondition parentCondition = new JcParentCondition(parentIndex,false,"部门" + parentIndex);
            for (int childIndex = 1; childIndex <= 6; childIndex++)
            {
                JcChildCondition childCondition = new JcChildCondition(childIndex,false,"区域" + childIndex);
                parentCondition.addSubItem(childCondition);
            }
            conditions.add(parentCondition);
        }jcConditionAdapter.setNewData(conditions);
    }

    protected void initLogic()
    {
        mainjcfragLs.setOnClickListener(this);
        mainjcfragLxAll.setOnClickListener(this);
        mainjcfragZtAll.setOnClickListener(this);
    }

    public void onClick(View view)
    {
        super.onClick(view);
        if(view.getId() != R.id.titlebar_moreicon)
            mainjcfragRecyclerConditions.setVisibility(View.GONE);
        switch (view.getId())
        {
            case R.id.mainjcfrag_lx_all:
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
            case R.id.mainjcfrag_zt_all:
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
            case R.id.mainjcfrag_ls:
            {
                updateConditionsMap();
                StringBuffer stringBuffer  = new StringBuffer();
                for(Map.Entry<String,String> entry :mConditionsMap.entrySet())
                    stringBuffer.append(entry.getKey() + "：" + entry.getValue() + "\r\n");
                showToast(stringBuffer.toString());
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

    protected void updateConditionsMap()
    {
        if(null == mConditionsMap)
            mConditionsMap = new HashMap<>();
        if(mCurrentSelectedLsItemOfIndex > -1 && mCurrentSelectedLsItemOfIndex < mLsList.size())
            mConditionsMap.put("type",String.valueOf(mLsList.get(mCurrentSelectedLsItemOfIndex).getTypeCode()));
        if(mCurrentSelectedZtItemOfIndex > -1 && mCurrentSelectedZtItemOfIndex < mZtList.size())
            mConditionsMap.put("status",String.valueOf(mZtList.get(mCurrentSelectedZtItemOfIndex).getStatusCode()));
        mConditionsMap.put("partmentId",String.valueOf(jcConditionAdapter.getmSelectedParentCode()));
        if(jcConditionAdapter.isSelectedChildCondition())
            mConditionsMap.put("areaId",String.valueOf(jcConditionAdapter.getmSelectedChildCode()));
        else
            if(mConditionsMap.containsKey("areaId"))mConditionsMap.remove("areaId");
    }

    protected void onTitleMoreIconClick()
    {
        super.onTitleMoreIconClick();
        if(mainjcfragRecyclerConditions.getVisibility() == View.GONE)
            mainjcfragRecyclerConditions.setVisibility(View.VISIBLE);
        else
            mainjcfragRecyclerConditions.setVisibility(View.GONE);
        jcConditionAdapter.notifyDataSetChanged();
    }
}
package ufhealth.integratedmachine.client.ui.ssjc.activity.view;

import android.net.Uri;
import android.view.View;
import java.util.ArrayList;
import com.google.gson.Gson;
import android.content.Intent;
import android.graphics.Color;
import android.webkit.WebView;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.webkit.WebViewClient;
import ufhealth.integratedmachine.client.R;
import android.support.v7.widget.RecyclerView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import ufhealth.integratedmachine.client.base.BaseAct;
import ufhealth.integratedmachine.client.widget.EchartView;
import ufhealth.integratedmachine.client.bean.ssjc.JcDetailInfo;
import ufhealth.integratedmachine.client.adapter.ssjc.JcGlspAdapter;
import ufhealth.integratedmachine.client.ui.ssjc.activity.view_v.SsjcDetailAct_V;
import ufhealth.integratedmachine.client.ui.ssjc.activity.presenter.SsjcDetailPresenter;

public class SsjcDetailAct extends BaseAct implements SsjcDetailAct_V,View.OnClickListener
{
    private String mAlarmId;
    private TextView mSsjcdetailFzr;
    private TextView mSsjcdetailSsz;
    private TextView mSsjcdetailName;
    private TextView mSsjcdetailArea;
    private TextView mSsjcdetailType;
    private TextView mSsjcdetailLsgj;
    private TextView mSsjcdetailLxgj;
    private TextView mSsjcdetailLxdh;
    private TextView mSsjcdetailBgdh;
    private TextView mSsjcdetailSjtx;
    private TextView mSsjcdetailSskbh;
    private JcGlspAdapter mJcGlspAdapter;
    private RecyclerView mSsjcdetailGlsp;
    private TextView mSsjcdetailPosition;
    private EchartView mSsjcdetailLsbjqxt;
    private TextView mSsjcdetailDepartment;
    private LinearLayout mSsjcdetailKeyvalue;
    private SsjcDetailPresenter mSsjcDetailPresenter;
    private SwipeRefreshLayout mSsjcdetailSwiperefreshlayout;

    protected int setLayoutResID()
    {
        return R.layout.activity_ssjcdetail;
    }

    protected void initWidgets(View rootView)
    {
        super.initWidgets(rootView);
        setTitleContent("实时监测");
        mAlarmId = getIntent().getStringExtra("alarmid");
        mSsjcdetailSsz = (TextView) rootView.findViewById(R.id.ssjcdetail_ssz);
        mSsjcdetailFzr = (TextView) rootView.findViewById(R.id.ssjcdetail_fzr);
        mSsjcdetailName = (TextView) rootView.findViewById(R.id.ssjcdetail_name);
        mSsjcdetailArea = (TextView) rootView.findViewById(R.id.ssjcdetail_area);
        mSsjcdetailType = (TextView) rootView.findViewById(R.id.ssjcdetail_type);
        mSsjcdetailLsgj = (TextView) rootView.findViewById(R.id.ssjcdetail_lsgj);
        mSsjcdetailLxgj = (TextView) rootView.findViewById(R.id.ssjcdetail_lxgj);
        mSsjcdetailLxdh = (TextView) rootView.findViewById(R.id.ssjcdetail_lxdh);
        mSsjcdetailBgdh = (TextView) rootView.findViewById(R.id.ssjcdetail_bgdh);
        mSsjcdetailSjtx = (TextView) rootView.findViewById(R.id.ssjcdetail_sjtx);
        mSsjcdetailSskbh = (TextView) rootView.findViewById(R.id.ssjcdetail_sskbh);
        mSsjcdetailGlsp = (RecyclerView) rootView.findViewById(R.id.ssjcdetail_glsp);
        mSsjcdetailPosition = (TextView) rootView.findViewById(R.id.ssjcdetail_position);
        mSsjcdetailLsbjqxt = (EchartView) rootView.findViewById(R.id.ssjcdetail_lsbjqxt);
        mSsjcdetailKeyvalue = (LinearLayout) rootView.findViewById(R.id.ssjcdetail_keyvalue);
        mSsjcdetailDepartment = (TextView)  rootView.findViewById(R.id.ssjcdetail_department);
        mSsjcdetailSwiperefreshlayout = (SwipeRefreshLayout) rootView.findViewById(R.id.ssjcdetail_swiperefreshlayout);
        mJcGlspAdapter = new JcGlspAdapter(this,new ArrayList<JcDetailInfo.CamerasBean>());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mSsjcdetailGlsp.setLayoutManager(linearLayoutManager);
        mSsjcdetailGlsp.setAdapter(mJcGlspAdapter);
    }

    protected void initDatas()
    {
        mSsjcDetailPresenter = new SsjcDetailPresenter();
        bindBaseMvpPresenter(mSsjcDetailPresenter);
        mSsjcdetailSwiperefreshlayout.setEnabled(true);
        mSsjcdetailSwiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            public void onRefresh()
            {
                mSsjcDetailPresenter.getDatas(mAlarmId);
            }
        });

        mJcGlspAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener()
        {
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position)
            {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse(null != mJcGlspAdapter.getData().get(position).getAccessUrl() ? mJcGlspAdapter.getData().get(position).getAccessUrl().trim() : "");
                intent.setData(content_url);
                startActivity(intent);
            }
        });
    }

    protected void initLogic()
    {
        mSsjcDetailPresenter.getDatas(mAlarmId);

    }

    public void failOfGetDatas()
    {

    }

    public void successOfGetDatas(final JcDetailInfo jcDetailInfo)
    {
        mSsjcdetailSwiperefreshlayout.setRefreshing(false);
        mSsjcdetailName.setText(null != jcDetailInfo.getName() ? jcDetailInfo.getName().trim() : "");
        mSsjcdetailDepartment.setText(null != jcDetailInfo.getDepartmentName() ? jcDetailInfo.getDepartmentName().trim() : "");
        mSsjcdetailArea.setText(null != jcDetailInfo.getDeviceAreaName() ? jcDetailInfo.getDeviceAreaName().trim() : "");
        mSsjcdetailPosition.setText(null != jcDetailInfo.getAddress() ? jcDetailInfo.getAddress().trim() : "");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(null != jcDetailInfo.getCategoryParentName() ? jcDetailInfo.getCategoryParentName().trim(): "");
        stringBuffer.append(null != jcDetailInfo.getMedium() && !"".equals( jcDetailInfo.getMedium().trim()) ? "-" + jcDetailInfo.getMedium().trim(): "");
        mSsjcdetailType.setText(stringBuffer.toString());
        String ssz = "";
        if(null != jcDetailInfo.getRealtimeData() && jcDetailInfo.getRealtimeData().trim().indexOf(".") > 0)
        {
            ssz = jcDetailInfo.getRealtimeData().trim();
            ssz = ssz.replaceAll("0+?$", "");
            ssz = ssz.replaceAll("[.]$", "");
        }
        mSsjcdetailSsz.setText(!"".equals(ssz.trim()) ? ssz : (null != jcDetailInfo.getRealtimeData() ? jcDetailInfo.getRealtimeData().trim() : ""));
        mSsjcdetailLsgj.setText(null != jcDetailInfo.getAlarmTotalNumber() ? jcDetailInfo.getAlarmTotalNumber().trim() : "");
        mSsjcdetailLxgj.setText(null != jcDetailInfo.getAlarmNumber() ? jcDetailInfo.getAlarmNumber().trim() : "");
        mSsjcdetailSskbh.setText(null != jcDetailInfo.getRealtimeDbPositionId() ? jcDetailInfo.getRealtimeDbPositionId().trim() : "");
        mSsjcdetailFzr.setText(null != jcDetailInfo.getPeopleName() ? jcDetailInfo.getPeopleName().trim() : "");
        mSsjcdetailLxdh.setText(null != jcDetailInfo.getPeopleTelephone() ? jcDetailInfo.getPeopleTelephone().trim() : "");
        mSsjcdetailBgdh.setText(null != jcDetailInfo.getPeopleWorkTelephone() ? jcDetailInfo.getPeopleWorkTelephone().trim() : "");
        mSsjcdetailSjtx.setText(null != jcDetailInfo.getDataSyncStatusName() ? jcDetailInfo.getDataSyncStatusName().trim() : "");
        mSsjcdetailSjtx.setTextColor((null != jcDetailInfo.getDataSyncStatus() && "1".equals(jcDetailInfo.getDataSyncStatus().trim())) ? Color.argb(255,0,255,0) : Color.argb(255,255,0,0));
        /******************************************************************************************/
        mSsjcdetailKeyvalue.removeAllViews();
        LayoutInflater inflater = LayoutInflater.from(this);
        for(int index = 0;index < jcDetailInfo.getSettings().size();index++)
        {
            View view = inflater.inflate(R.layout.item_ssjcdetail,null);
            TextView key = (TextView)view.findViewById(R.id.ssjcdetail_key);
            TextView value = (TextView)view.findViewById(R.id.ssjcdetail_value);
            key.setText(null != jcDetailInfo.getSettings().get(index).getLevelName() ? jcDetailInfo.getSettings().get(index).getLevelName().trim() : "");
            value.setText(null != jcDetailInfo.getSettings().get(index).getRuleDescription() ? jcDetailInfo.getSettings().get(index).getRuleDescription().trim() : "");
            mSsjcdetailKeyvalue.addView(view);
        }
        /******************************************************************************************/
        mJcGlspAdapter.setNewData(null != jcDetailInfo.getCameras() ? jcDetailInfo.getCameras() : new ArrayList<JcDetailInfo.CamerasBean>());
        /******************************************************************************************/
        mSsjcdetailLsbjqxt.loadUrl("file:///android_asset/detail.html");
        if(null != mSsjcdetailLsbjqxt)
        {
            mSsjcdetailLsbjqxt.setWebViewClient(new WebViewClient()
            {
                public void onPageFinished(WebView view, String url)
                {
                    super.onPageFinished(view, url);
                    mSsjcdetailLsbjqxt.refreshEchartsViewWithDataJson(new Gson().toJson(jcDetailInfo));
                }
            });
        }
    }
}
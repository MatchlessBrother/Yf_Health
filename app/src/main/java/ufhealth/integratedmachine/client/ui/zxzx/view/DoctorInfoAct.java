package ufhealth.integratedmachine.client.ui.zxzx.view;

import android.view.View;
import java.util.ArrayList;
import android.content.Intent;
import android.graphics.Color;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.graphics.BitmapFactory;
import com.donkingliang.labels.LabelsView;
import ufhealth.integratedmachine.client.R;
import com.hwangjr.rxbus.annotation.Subscribe;
import android.support.v7.widget.RecyclerView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import ufhealth.integratedmachine.client.base.BaseAct;
import ufhealth.integratedmachine.client.bean.zxzx.DoctorAllInfo;
import ufhealth.integratedmachine.client.ui.zxzx.view_v.DoctorInfoAct_V;
import ufhealth.integratedmachine.client.adapter.zxzx.DoctorInfoRatingAdapter;
import ufhealth.integratedmachine.client.ui.zxzx.presenter.DoctorInfoPresenter;

public class DoctorInfoAct extends BaseAct implements DoctorInfoAct_V,View.OnClickListener
{
    private String TYPE;
    private ImageView doctorinfoImg;
    private TextView doctorinfoImgStatus;
    private TextView doctorinfoName;
    private TextView doctorinfoPosition;
    private TextView doctorinfoHospitalname;
    private TextView doctorinfoDepartmentname;
    private TextView doctorinfoSpecialize;
    private TextView doctorinfoSource;
    private TextView doctorinfoStartchat;
    private TextView doctorinfoZsnum;
    private TextView doctorinfoHpnum;
    private TextView doctorinfoPjnum;
    private LabelsView doctorinfoLabels;
    private TextView doctorinfo_startnote;
    private ImageView doctorinfoRightTopTwline;
    private ImageView doctorinfoRightTopTwimg;
    private TextView doctorinfoRightTopTwname;
    private TextView doctorinfoRightTopTwfy;
    private TextView doctorinfoRightTopTwjryz;
    private ImageView doctorinfoRightTopYyline;
    private ImageView doctorinfoRightTopYyimg;
    private TextView doctorinfoRightTopYyname;
    private TextView doctorinfoRightTopYyfy;
    private TextView doctorinfoRightTopYyjryz;
    private ImageView doctorinfoRightTopSpline;
    private ImageView doctorinfoRightTopSpimg;
    private TextView doctorinfoRightTopSpname;
    private TextView doctorinfoRightTopSpfy;
    private TextView doctorinfoRightTopSpjryz;
    private TextView doctorinfoRightBottomYhpj;
    private RelativeLayout doctorinfoRightTopSpAll;
    private RelativeLayout doctorinfoRightTopYyAll;
    private RelativeLayout doctorinfoRightTopTwAll;
    private DoctorInfoPresenter doctorInfoPresenter;
    private RecyclerView doctorinfoRightBottomRecyclerview;
    private DoctorInfoRatingAdapter doctorInfoRatingAdapter;
    private SwipeRefreshLayout doctorinfoRightBottomSwipeLayout;

    protected int setLayoutResID()
    {
        return R.layout.activity_doctorinfo;
    }

    protected void initWidgets(View rootView)
    {
        super.initWidgets(rootView);
        setTitleContent("医生主页");
        TYPE = getIntent().getStringExtra("type");
        doctorinfoImg = rootView.findViewById(R.id.doctorinfo_img);
        doctorinfoImgStatus = rootView.findViewById(R.id.doctorinfo_img_status);
        doctorinfoName = rootView.findViewById(R.id.doctorinfo_name);
        doctorinfoPosition = rootView.findViewById(R.id.doctorinfo_position);
        doctorinfoHospitalname = rootView.findViewById(R.id.doctorinfo_hospitalname);
        doctorinfoDepartmentname = rootView.findViewById(R.id.doctorinfo_departmentname);
        doctorinfoSpecialize = rootView.findViewById(R.id.doctorinfo_specialize);
        doctorinfoSource = rootView.findViewById(R.id.doctorinfo_source);
        doctorinfoStartchat = rootView.findViewById(R.id.doctorinfo_startchat);
        doctorinfoZsnum = rootView.findViewById(R.id.doctorinfo_zsnum);
        doctorinfoHpnum = rootView.findViewById(R.id.doctorinfo_hpnum);
        doctorinfoPjnum = rootView.findViewById(R.id.doctorinfo_pjnum);
        doctorinfoLabels = rootView.findViewById(R.id.doctorinfo_labels);
        doctorinfo_startnote = rootView.findViewById(R.id.doctorinfo_startnote);
        doctorinfoRightTopSpAll = rootView.findViewById(R.id.doctorinfo_right_top_spall);
        doctorinfoRightTopYyAll = rootView.findViewById(R.id.doctorinfo_right_top_yyall);
        doctorinfoRightTopTwAll = rootView.findViewById(R.id.doctorinfo_right_top_twall);
        doctorinfoRightTopTwline = rootView.findViewById(R.id.doctorinfo_right_top_twline);
        doctorinfoRightTopTwimg = rootView.findViewById(R.id.doctorinfo_right_top_twimg);
        doctorinfoRightTopTwname = rootView.findViewById(R.id.doctorinfo_right_top_twname);
        doctorinfoRightTopTwfy = rootView.findViewById(R.id.doctorinfo_right_top_twfy);
        doctorinfoRightTopTwjryz = rootView.findViewById(R.id.doctorinfo_right_top_twjryz);
        doctorinfoRightTopYyline = rootView.findViewById(R.id.doctorinfo_right_top_yyline);
        doctorinfoRightTopYyimg = rootView.findViewById(R.id.doctorinfo_right_top_yyimg);
        doctorinfoRightTopYyname = rootView.findViewById(R.id.doctorinfo_right_top_yyname);
        doctorinfoRightTopYyfy = rootView.findViewById(R.id.doctorinfo_right_top_yyfy);
        doctorinfoRightTopYyjryz = rootView.findViewById(R.id.doctorinfo_right_top_yyjryz);
        doctorinfoRightTopSpline = rootView.findViewById(R.id.doctorinfo_right_top_spline);
        doctorinfoRightTopSpimg = rootView.findViewById(R.id.doctorinfo_right_top_spimg);
        doctorinfoRightTopSpname = rootView.findViewById(R.id.doctorinfo_right_top_spname);
        doctorinfoRightTopSpfy = rootView.findViewById(R.id.doctorinfo_right_top_spfy);
        doctorinfoRightTopSpjryz = rootView.findViewById(R.id.doctorinfo_right_top_spjryz);
        doctorinfoRightBottomYhpj = rootView.findViewById(R.id.doctorinfo_right_bottom_yhpj);
        doctorinfoRightBottomSwipeLayout = rootView.findViewById(R.id.doctorinfo_right_bottom_swipelayout);
        doctorinfoRightBottomRecyclerview = rootView.findViewById(R.id.doctorinfo_right_bottom_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        doctorinfoRightBottomRecyclerview.setLayoutManager(linearLayoutManager);
        doctorInfoRatingAdapter = new DoctorInfoRatingAdapter(this,new ArrayList<DoctorAllInfo.PageBean.ContentBean>());
        doctorinfoRightBottomRecyclerview.setAdapter(doctorInfoRatingAdapter);

        switch(TYPE)
        {
            case ZxzxAct.SPZX:
            {
                TYPE = ZxzxAct.SPZX;
                doctorinfoRightTopSpline.setVisibility(View.VISIBLE);
                doctorinfoRightTopSpimg.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.spzx_blue));
                doctorinfoRightTopSpname.setTextColor(Color.argb(255,0,147,221));
                doctorinfoRightTopSpfy.setText("0元/分钟");
                break;
            }
            case ZxzxAct.YYZX:
            {
                TYPE = ZxzxAct.YYZX;
                doctorinfoRightTopYyline.setVisibility(View.VISIBLE);
                doctorinfoRightTopYyimg.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.yyzx_blue));
                doctorinfoRightTopYyname.setTextColor(Color.argb(255,0,147,221));
                doctorinfoRightTopYyfy.setText("0元/分钟");
                break;
            }
            case ZxzxAct.KSZX:
            case ZxzxAct.BGJD:
            {
                TYPE = ZxzxAct.TWZX;
                doctorinfoRightTopTwline.setVisibility(View.VISIBLE);
                doctorinfoRightTopTwimg.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.kszx_blue));
                doctorinfoRightTopTwname.setTextColor(Color.argb(255,0,147,221));
                doctorinfoRightTopTwfy.setText("0元/次");
                break;
            }
            default:
            {
                doctorinfoStartchat.setEnabled(false);
                doctorinfo_startnote.setVisibility(View.VISIBLE);
                break;
            }
        }

        doctorinfoRightBottomSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            public void onRefresh()
            {
                doctorInfoPresenter.refreshDoctorRatingInfo(getIntent().getStringExtra("id").trim());
            }
        });

        doctorInfoRatingAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener()
        {
            public void onLoadMoreRequested()
            {
                doctorInfoPresenter.loadMoreDoctorRatingInfo(getIntent().getStringExtra("id").trim());
            }
        },doctorinfoRightBottomRecyclerview);

        doctorinfoStartchat.setOnClickListener(this);
        doctorinfoRightTopSpAll.setOnClickListener(this);
        doctorinfoRightTopYyAll.setOnClickListener(this);
        doctorinfoRightTopTwAll.setOnClickListener(this);
    }

    protected void initDatas()
    {
        doctorInfoPresenter = new DoctorInfoPresenter();
        doctorInfoPresenter.attachContextAndViewLayer(this,this);
    }

    protected void initLogic()
    {
        doctorInfoPresenter.initDoctorAllInfo(getIntent().getStringExtra("id").trim());
    }

    public void onClick(View view)
    {
        super.onClick(view);
        switch(view.getId())
        {
            case R.id.doctorinfo_startchat:
            {
                if(TYPE == ZxzxAct.SPZX || TYPE == ZxzxAct.YYZX || TYPE == ZxzxAct.TWZX)
                {
                    Intent intent = new Intent(this, BillInfoAct.class);
                    intent.putExtra("type",TYPE);
                    intent.putExtra("id",getIntent().getStringExtra("id"));
                    startActivity(intent);
                }
                break;
            }

            case R.id.doctorinfo_right_top_spall:
            {
                TYPE = ZxzxAct.SPZX;
                doctorinfoStartchat.setEnabled(true);
                doctorinfo_startnote.setVisibility(View.GONE);
                doctorinfoRightTopTwline.setVisibility(View.GONE);
                doctorinfoRightTopYyline.setVisibility(View.GONE);
                doctorinfoRightTopSpline.setVisibility(View.VISIBLE);
                doctorinfoRightTopSpimg.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.spzx_blue));
                doctorinfoRightTopSpname.setTextColor(Color.argb(255,0,147,221));
                break;
            }

            case R.id.doctorinfo_right_top_yyall:
            {
                TYPE = ZxzxAct.YYZX;
                doctorinfoStartchat.setEnabled(true);
                doctorinfo_startnote.setVisibility(View.GONE);
                doctorinfoRightTopTwline.setVisibility(View.GONE);
                doctorinfoRightTopSpline.setVisibility(View.GONE);
                doctorinfoRightTopYyline.setVisibility(View.VISIBLE);
                doctorinfoRightTopYyimg.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.yyzx_blue));
                doctorinfoRightTopYyname.setTextColor(Color.argb(255,0,147,221));
                break;
            }

            case R.id.doctorinfo_right_top_twall:
            {
                TYPE = ZxzxAct.TWZX;
                doctorinfoStartchat.setEnabled(true);
                doctorinfo_startnote.setVisibility(View.GONE);
                doctorinfoRightTopSpline.setVisibility(View.GONE);
                doctorinfoRightTopYyline.setVisibility(View.GONE);
                doctorinfoRightTopTwline.setVisibility(View.VISIBLE);
                doctorinfoRightTopTwimg.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.kszx_blue));
                doctorinfoRightTopTwname.setTextColor(Color.argb(255,0,147,221));
                break;
            }
        }
    }

    protected void onDestroy()
    {
        doctorInfoPresenter.detachContextAndViewLayout();
        super.onDestroy();
    }

    public void finishRefreshDoctorRatingInfo()
    {
        doctorinfoRightBottomSwipeLayout.setRefreshing(false);
    }

    public void finishLoadMoreDoctorRatingInfo()
    {
        doctorInfoRatingAdapter.loadMoreComplete();

    }

    public void setDoctorBaseInfo(DoctorAllInfo.BaseinfoBean baseinfoBean)
    {
        if(null != baseinfoBean)
        {
            useGlideLoadImg(doctorinfoImg,null != baseinfoBean.getAvatar() ? baseinfoBean.getAvatar().trim() : "");
            doctorinfoImgStatus.setText("在线");
            doctorinfoImgStatus.setVisibility(View.VISIBLE);
            doctorinfoName.setText(null != baseinfoBean.getDoctor_name() ? baseinfoBean.getDoctor_name().trim() : "未知");
            doctorinfoPosition.setText(null != baseinfoBean.getJob_name() ? baseinfoBean.getJob_name().trim() : "未知");
            doctorinfoHospitalname.setText("医院：" + (null != baseinfoBean.getHospital_name() ? baseinfoBean.getHospital_name().trim() : "未知"));
            doctorinfoDepartmentname.setText("科室：" + (null != baseinfoBean.getDepartment_name() ? baseinfoBean.getDepartment_name().trim() : "未知"));
            doctorinfoSpecialize.setText("擅长：" + (null != baseinfoBean.getBe_good_at() ? baseinfoBean.getBe_good_at().trim() : "未知" ));
            doctorinfoSource.setText("来源：" + (null != baseinfoBean.getOriginal() ? baseinfoBean.getOriginal().trim() : "未知"));
            doctorinfoZsnum.setText(null != baseinfoBean.getConsult_count() ? baseinfoBean.getConsult_count().trim() : "0");
            doctorinfoHpnum.setText(null != baseinfoBean.getComment_count_per() ? baseinfoBean.getComment_count_per().trim() + "%" : "0%");
            doctorinfoPjnum.setText(null != baseinfoBean.getComment_count() ? baseinfoBean.getComment_count().trim() : "0");
            doctorinfoLabels.setLabels(new ArrayList<String>());
            doctorinfoStartchat.setVisibility(View.VISIBLE);
            doctorinfoRightBottomYhpj.setText("用户评价：(" + (null != baseinfoBean.getComment_count() ? baseinfoBean.getComment_count().trim() : "0")+")");


            doctorinfoRightTopTwfy.setText(baseinfoBean.getT_cost() + "元/次");
            if(null != baseinfoBean.getTwzxIsFree() && "yes".equals(baseinfoBean.getIs_free().trim()))
            {
                doctorinfoRightTopTwjryz.setVisibility(View.VISIBLE);
                doctorinfoRightTopTwjryz.setText(baseinfoBean.getIs_free().trim());
            }
            doctorinfoRightTopYyfy.setText(baseinfoBean.getY_cost() + "元/分钟");
            if(null != baseinfoBean.getYpzxIsFree() && "yes".equals(baseinfoBean.getYpzxIsFree().trim()))
            {
                doctorinfoRightTopYyjryz.setVisibility(View.VISIBLE);
                doctorinfoRightTopYyjryz.setText(baseinfoBean.getIs_free().trim());
            }
            doctorinfoRightTopSpfy.setText(baseinfoBean.getS_cost() + "元/分钟");
            if(null != baseinfoBean.getSpzxIsFree() && "yes".equals(baseinfoBean.getSpzxIsFree().trim()))
            {
                doctorinfoRightTopSpjryz.setVisibility(View.VISIBLE);
                doctorinfoRightTopSpjryz.setText(baseinfoBean.getIs_free().trim());
            }
        }
    }

    public void refreshDoctorRatingInfo(DoctorAllInfo.PageBean pageBean)
    {
        doctorInfoRatingAdapter.setNewData(pageBean.getContent());
        if(pageBean.getContent().size() < pageBean.getSize())
            doctorInfoRatingAdapter.setEnableLoadMore(false);
        else
            doctorInfoRatingAdapter.setEnableLoadMore(true);
    }

    public void loadMoreDoctorRatingInfo(DoctorAllInfo.PageBean pageBean)
    {
        doctorInfoRatingAdapter.addData(pageBean.getContent());
        doctorInfoRatingAdapter.notifyDataSetChanged();
        if(pageBean.getContent().size() < pageBean.getSize())
            doctorInfoRatingAdapter.setEnableLoadMore(false);
        else
            doctorInfoRatingAdapter.setEnableLoadMore(true);
    }

    @Subscribe
    public void receiveCountDownFinish(Boolean isFinish)
    {
        super.receiveCountDownFinish(isFinish);

    }

    @Subscribe
    public void receiveCountDownTime(Long countDownTime)
    {
        super.receiveCountDownTime(countDownTime);

    }
}
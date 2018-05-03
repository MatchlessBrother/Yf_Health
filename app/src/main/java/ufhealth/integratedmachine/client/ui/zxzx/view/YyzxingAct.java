package ufhealth.integratedmachine.client.ui.zxzx.view;

import android.view.View;
import java.util.ArrayList;
import android.graphics.Color;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.donkingliang.labels.LabelsView;
import ufhealth.integratedmachine.client.R;
import com.hwangjr.rxbus.annotation.Subscribe;
import ufhealth.integratedmachine.client.base.BaseAct;
import ufhealth.integratedmachine.client.widget.RippleLayout;
import ufhealth.integratedmachine.client.bean.zxzx.DoctorAllInfo;
import ufhealth.integratedmachine.client.ui.zxzx.view_v.YyzxingAct_V;
import ufhealth.integratedmachine.client.ui.zxzx.view_v.DoctorInfoAct_V;
import ufhealth.integratedmachine.client.ui.zxzx.presenter.YyzxingPresenter;
import ufhealth.integratedmachine.client.ui.zxzx.presenter.DoctorInfoPresenter;

public class YyzxingAct extends BaseAct implements YyzxingAct_V,DoctorInfoAct_V,View.OnClickListener
{
    private RippleLayout rippleView;
    private RelativeLayout doctorinfoImgall;
    private ImageView doctorinfoImg;
    private TextView doctorinfoImgStatus;
    private TextView doctorinfoName;
    private TextView doctorinfoPosition;
    private TextView doctorinfoHospitalname;
    private TextView doctorinfoDepartmentname;
    private TextView doctorinfoSpecialize;
    private TextView doctorinfoSource;
    private TextView doctorinfoStartchat;
    private TextView doctorinfoStartnote;
    private LinearLayout doctorinfoZshppj;
    private TextView doctorinfoZsnum;
    private TextView doctorinfoHpnum;
    private TextView doctorinfoPjnum;
    private LabelsView doctorinfoLabels;
    private TextView yyzxingRightTop;
    private LinearLayout yyzxingRightBottom;
    private TextView yyzxingRightBottomTime;
    private TextView yyzxingRightBottomLjstatus;

    private YyzxingPresenter yyzxingPresenter;
    private DoctorInfoPresenter doctorInfoPresenter;

    protected int setLayoutResID()
    {
        return R.layout.activity_yyzxing;
    }

    protected void initWidgets(View rootView)
    {
        super.initWidgets(rootView);
        setTitleContent("语音咨询中");
        doctorinfoImgall = (RelativeLayout) rootView.findViewById(R.id.doctorinfo_imgall);
        doctorinfoImg = (ImageView) rootView.findViewById(R.id.doctorinfo_img);
        doctorinfoImgStatus = (TextView) rootView.findViewById(R.id.doctorinfo_img_status);
        doctorinfoName = (TextView) rootView.findViewById(R.id.doctorinfo_name);
        doctorinfoPosition = (TextView) rootView.findViewById(R.id.doctorinfo_position);
        doctorinfoHospitalname = (TextView) rootView.findViewById(R.id.doctorinfo_hospitalname);
        doctorinfoDepartmentname = (TextView) rootView.findViewById(R.id.doctorinfo_departmentname);
        doctorinfoSpecialize = (TextView) rootView.findViewById(R.id.doctorinfo_specialize);
        doctorinfoSource = (TextView) rootView.findViewById(R.id.doctorinfo_source);
        doctorinfoStartchat = (TextView) rootView.findViewById(R.id.doctorinfo_startchat);
        doctorinfoStartnote = (TextView) rootView.findViewById(R.id.doctorinfo_startnote);
        doctorinfoZshppj = (LinearLayout) rootView.findViewById(R.id.doctorinfo_zshppj);
        doctorinfoZsnum = (TextView) rootView.findViewById(R.id.doctorinfo_zsnum);
        doctorinfoHpnum = (TextView) rootView.findViewById(R.id.doctorinfo_hpnum);
        doctorinfoPjnum = (TextView) rootView.findViewById(R.id.doctorinfo_pjnum);
        doctorinfoLabels = (LabelsView) rootView.findViewById(R.id.doctorinfo_labels);
        yyzxingRightTop = (TextView) rootView.findViewById(R.id.yyzxing_right_top);
        yyzxingRightBottom = (LinearLayout) rootView.findViewById(R.id.yyzxing_right_bottom);
        yyzxingRightBottomTime = (TextView) rootView.findViewById(R.id.yyzxing_right_bottom_time);
        yyzxingRightBottomLjstatus = (TextView) rootView.findViewById(R.id.yyzxing_right_bottom_ljstatus);
        rippleView = (RippleLayout)rootView.findViewById(R.id.yyzxing_right_bottom_ripplelayout);


        /**********连接成功再播放动画**************/
        rippleView.startRippleAnimation();
        /**********连接成功再播放动画**************/


        doctorinfoStartchat.setText("结束咨询");
        doctorinfoStartnote.setVisibility(View.GONE);
        doctorinfoStartchat.setVisibility(View.INVISIBLE);
        doctorinfoStartchat.setBackgroundColor(Color.argb(255,255,0,0));
        doctorinfoStartchat.setOnClickListener(this);
    }

    protected void initDatas()
    {
        doctorInfoPresenter = new DoctorInfoPresenter();
        doctorInfoPresenter.attachContextAndViewLayer(this,this);
        yyzxingPresenter = new YyzxingPresenter();
        yyzxingPresenter.attachContextAndViewLayer(this,this);
    }

    protected void initLogic()
    {
        doctorInfoPresenter.initDoctorAllInfo(getIntent().getStringExtra("doctorid").trim());
    }

    public void onClick(View view)
    {
        super.onClick(view);
        switch (view.getId())
        {
            case R.id.activity_title_back:
            {
                break;
            }
            case R.id.doctorinfo_startchat:
            {
                break;
            }
        }
    }

    public void onBackPressed()
    {

    }

    protected void onDestroy()
    {
        doctorInfoPresenter.detachContextAndViewLayout();
        yyzxingPresenter.detachContextAndViewLayout();
        super.onDestroy();
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

    public void finishRefreshDoctorRatingInfo()
    {

    }

    public void finishLoadMoreDoctorRatingInfo()
    {

    }

    public void setDoctorBaseInfo(DoctorAllInfo.BaseinfoBean baseinfoBean)
    {
        if(null != baseinfoBean)
        {
            useGlideLoadImg(doctorinfoImg,null != baseinfoBean.getAvatar() ? baseinfoBean.getAvatar().trim() : "");
            doctorinfoImgStatus.setText("在线");
            doctorinfoImgStatus.setVisibility(View.GONE);
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
            yyzxingRightTop.setText("正在与" + (null != baseinfoBean.getDoctor_name() ? baseinfoBean.getDoctor_name().trim() : "未知") +
                                    "医生咨询，此次通话将在" + getIntent().getIntExtra("time",0) / 60 + "分钟后结束");
            doctorinfoStartchat.setVisibility(View.VISIBLE);
        }
    }

    public void refreshDoctorRatingInfo(DoctorAllInfo.PageBean pageBean)
    {

    }

    public void loadMoreDoctorRatingInfo(DoctorAllInfo.PageBean pageBean)
    {

    }
}
package ufhealth.integratedmachine.client.ui.zxzx.view;

import android.view.View;
import java.util.ArrayList;
import java.util.LinkedList;
import android.graphics.Color;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.donkingliang.labels.LabelsView;
import ufhealth.integratedmachine.client.R;
import android.support.v7.widget.RecyclerView;
import com.hwangjr.rxbus.annotation.Subscribe;
import android.support.v7.widget.LinearLayoutManager;
import ufhealth.integratedmachine.client.base.BasePhotoAct;
import ufhealth.integratedmachine.client.bean.zxzx.DoctorAllInfo;
import ufhealth.integratedmachine.client.ui.zxzx.view_v.TwzxingAct_V;
import ufhealth.integratedmachine.client.ui.zxzx.view_v.DoctorInfoAct_V;
import ufhealth.integratedmachine.client.ui.zxzx.presenter.TwzxingPresenter;
import ufhealth.integratedmachine.client.adapter.zxzx.DoctorInfoRatingAdapter;
import ufhealth.integratedmachine.client.ui.zxzx.presenter.DoctorInfoPresenter;

public class TwzxingAct extends BasePhotoAct implements TwzxingAct_V,DoctorInfoAct_V,View.OnClickListener
{
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
    private TextView twzxingRightTop;
    private EditText twzxingRightBottomEt;
    private TextView twzxingRightBottomSend;
    private LinearLayout twzxingRightBottomAll;
    private ImageView twzxingRightBottomExpression;
    private ImageView twzxingRightBottomPicture;
    private RecyclerView twzxingRightBottomRecyclerview;
    private DoctorInfoRatingAdapter doctorInfoRatingAdapter;

    private TwzxingPresenter twzxingPresenter;
    private DoctorInfoPresenter doctorInfoPresenter;

    protected int setLayoutResID()
    {
        return R.layout.activity_twzxing;
    }

    protected void initWidgets(View rootView)
    {
        super.initWidgets(rootView);
        setTitleContent("图文咨询中");
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
        twzxingRightTop = (TextView) rootView.findViewById(R.id.twzxing_right_top);
        twzxingRightBottomAll = (LinearLayout) rootView.findViewById(R.id.twzxing_right_bottom_all);
        twzxingRightBottomEt = (EditText) rootView.findViewById(R.id.twzxing_right_bottom_et);
        twzxingRightBottomSend = (TextView) rootView.findViewById(R.id.twzxing_right_bottom_send);
        twzxingRightBottomExpression = (ImageView) rootView.findViewById(R.id.twzxing_right_bottom_expression);
        twzxingRightBottomPicture = (ImageView) rootView.findViewById(R.id.twzxing_right_bottom_picture);
        twzxingRightBottomRecyclerview = (RecyclerView) rootView.findViewById(R.id.twzxing_right_bottom_recyclerview);
        doctorinfoStartchat.setText("结束咨询");
        doctorinfoStartnote.setVisibility(View.GONE);
        twzxingRightBottomAll.setVisibility(View.GONE);
        doctorinfoStartchat.setVisibility(View.INVISIBLE);
        doctorinfoStartchat.setBackgroundColor(Color.argb(255,255,0,0));


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        twzxingRightBottomRecyclerview.setLayoutManager(linearLayoutManager);


        doctorInfoRatingAdapter = new DoctorInfoRatingAdapter(this,new ArrayList<DoctorAllInfo.PageBean.ContentBean>());
        twzxingRightBottomRecyclerview.setAdapter(doctorInfoRatingAdapter);


        doctorinfoStartchat.setOnClickListener(this);
        twzxingRightBottomSend.setOnClickListener(this);
        twzxingRightBottomPicture.setOnClickListener(this);
        twzxingRightBottomExpression.setOnClickListener(this);
    }

    protected void initDatas()
    {
        doctorInfoPresenter = new DoctorInfoPresenter();
        doctorInfoPresenter.attachContextAndViewLayer(this,this);
        twzxingPresenter = new TwzxingPresenter();
        twzxingPresenter.attachContextAndViewLayer(this,this);
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
            case R.id.twzxing_right_bottom_expression:
            {
                break;
            }
            case R.id.twzxing_right_bottom_picture:
            {
                startGallery(false,3);
                break;
            }
            case R.id.twzxing_right_bottom_send:
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
        twzxingPresenter.detachContextAndViewLayout();
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

    public void finishRefreshDoctorRatingInfo() {}

    public void finishLoadMoreDoctorRatingInfo() {}

    public void refreshDoctorRatingInfo(DoctorAllInfo.PageBean pageBean) {}

    public void loadMoreDoctorRatingInfo(DoctorAllInfo.PageBean pageBean) {}

    public void setOnNewImgPathListener(LinkedList<String> bitmapPaths)
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
            doctorinfoStartchat.setVisibility(View.VISIBLE);
            twzxingRightBottomAll.setVisibility(View.VISIBLE);
        }
    }
}
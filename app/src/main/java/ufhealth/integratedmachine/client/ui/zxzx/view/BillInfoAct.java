package ufhealth.integratedmachine.client.ui.zxzx.view;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import ufhealth.integratedmachine.client.R;

import com.donkingliang.labels.LabelsView;
import com.hwangjr.rxbus.annotation.Subscribe;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;

import ufhealth.integratedmachine.client.base.BaseAct;
import ufhealth.integratedmachine.client.bean.zxzx.DoctorAllInfo;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_View;
import ufhealth.integratedmachine.client.ui.zxzx.presenter.BillInfoPresenter;
import ufhealth.integratedmachine.client.ui.zxzx.view_v.BillInfoAct_V;

public class BillInfoAct extends BaseAct implements BillInfoAct_V,View.OnClickListener
{
    private Double value = 0d;
    private Integer startTime = 5;
    private Integer changeTime = 1;
    private ImageView doctorbillinfoImg;
    private TextView doctorbillinfoSource;
    private TextView doctorbillinfoName;
    private TextView doctorbillinfoPosition;
    private TextView doctorbillinfoHospitalname;
    private TextView doctorbillinfoDepartmentname;
    private TextView doctorbillinfoSpecialize;
    private LabelsView doctorbillinfoLabels;
    private TextView doctorbillinfoType;
    private TextView doctorbillinfoValue;
    private LinearLayout doctorbillinfoTimeall;
    private TextView doctorbillinfoTimeDelbtn;
    private TextView doctorbillinfoTime;
    private TextView doctorbillinfoTimeAddbtn;
    private TextView doctorbillinfoTotalvalue;
    private TextView doctorbillinfoStartpay;
    private BillInfoPresenter billInfoPresenter;
    private DecimalFormat decimalFormat = new DecimalFormat("#.0");

    protected int setLayoutResID()
    {
        return R.layout.activity_billinfo;
    }

    protected void initWidgets(View rootView)
    {
        super.initWidgets(rootView);
        setTitleContent("订单信息");
        doctorbillinfoImg = rootView.findViewById(R.id.doctorbillinfo_img);
        doctorbillinfoSource = rootView.findViewById(R.id.doctorbillinfo_source);
        doctorbillinfoName = rootView.findViewById(R.id.doctorbillinfo_name);
        doctorbillinfoPosition = rootView.findViewById(R.id.doctorbillinfo_position);
        doctorbillinfoHospitalname = rootView.findViewById(R.id.doctorbillinfo_hospitalname);
        doctorbillinfoDepartmentname = rootView.findViewById(R.id.doctorbillinfo_departmentname);
        doctorbillinfoSpecialize = rootView.findViewById(R.id.doctorbillinfo_specialize);
        doctorbillinfoLabels = rootView.findViewById(R.id.doctorbillinfo_labels);
        doctorbillinfoType = rootView.findViewById(R.id.doctorbillinfo_type);
        doctorbillinfoValue = rootView.findViewById(R.id.doctorbillinfo_value);
        doctorbillinfoTimeall = rootView.findViewById(R.id.doctorbillinfo_timeall);
        doctorbillinfoTimeDelbtn =rootView.findViewById(R.id.doctorbillinfo_time_delbtn);
        doctorbillinfoTime = rootView.findViewById(R.id.doctorbillinfo_time);
        doctorbillinfoTimeAddbtn = rootView.findViewById(R.id.doctorbillinfo_time_addbtn);
        doctorbillinfoTotalvalue = rootView.findViewById(R.id.doctorbillinfo_totalvalue);
        doctorbillinfoStartpay =rootView. findViewById(R.id.doctorbillinfo_startpay);
        doctorbillinfoTimeAddbtn.setOnClickListener(this);
        doctorbillinfoTimeDelbtn.setOnClickListener(this);
        doctorbillinfoStartpay.setOnClickListener(this);
    }

    protected void initDatas()
    {
        billInfoPresenter = new BillInfoPresenter();
        billInfoPresenter.attachContextAndViewLayer(this,this);
    }

    protected void initLogic()
    {
        billInfoPresenter.initDoctorAllInfo(getIntent().getStringExtra("id"));
    }

    public void setDoctorBaseInfo(DoctorAllInfo.BaseinfoBean baseinfoBean)
    {
        if(null != baseinfoBean)
        {
            useGlideLoadImg(doctorbillinfoImg,null != baseinfoBean.getAvatar() ? baseinfoBean.getAvatar().trim() : "");
            doctorbillinfoName.setText(null != baseinfoBean.getDoctor_name() ? baseinfoBean.getDoctor_name().trim() : "未知");
            doctorbillinfoPosition.setText(null != baseinfoBean.getJob_name() ? baseinfoBean.getJob_name().trim() : "未知");
            doctorbillinfoHospitalname.setText(null != baseinfoBean.getHospital_name() ? baseinfoBean.getHospital_name().trim() : "未知");
            doctorbillinfoDepartmentname.setText(null != baseinfoBean.getDepartment_name() ? baseinfoBean.getDepartment_name().trim() : "未知");
            doctorbillinfoSpecialize.setText("擅长：" + (null != baseinfoBean.getBe_good_at() ? baseinfoBean.getBe_good_at().trim() : "未知" ));
            doctorbillinfoSource.setText("来源：" + (null != baseinfoBean.getOriginal() ? baseinfoBean.getOriginal().trim() : "未知"));
            doctorbillinfoLabels.setLabels(new ArrayList<String>());
            switch(getIntent().getStringExtra("type"))
            {
                case ChooseDoctorAct.SPZX:
                {
                    doctorbillinfoTimeall.setVisibility(View.VISIBLE);
                    doctorbillinfoStartpay.setVisibility(View.VISIBLE);
                    doctorbillinfoType.setText("视频咨询");
                    value = Double.valueOf(baseinfoBean.getS_cost());
                    doctorbillinfoValue.setText(baseinfoBean.getS_cost()+"元/分钟");
                    doctorbillinfoTime.setText(startTime+"分钟");
                    doctorbillinfoTotalvalue.setText("订单总价：" + decimalFormat.format(startTime * baseinfoBean.getS_cost()) +"元");
                    break;
                }
                case ChooseDoctorAct.YYZX:
                {
                    doctorbillinfoTimeall.setVisibility(View.VISIBLE);
                    doctorbillinfoStartpay.setVisibility(View.VISIBLE);
                    doctorbillinfoType.setText("语音咨询");
                    value = Double.valueOf(baseinfoBean.getY_cost());
                    doctorbillinfoValue.setText(baseinfoBean.getY_cost()+"元/分钟");
                    doctorbillinfoTime.setText(startTime+"分钟");
                    doctorbillinfoTotalvalue.setText("订单总价：" + decimalFormat.format(startTime * baseinfoBean.getY_cost()) +"元");
                    break;
                }
                case ChooseDoctorAct.MYYZ:
                {
                    doctorbillinfoStartpay.setVisibility(View.VISIBLE);
                    doctorbillinfoStartpay.setText("立即咨询");
                    doctorbillinfoType.setText("名医义诊");
                    value = Double.valueOf(baseinfoBean.getT_cost());
                    doctorbillinfoValue.setText(baseinfoBean.getT_cost()+"元/次");
                    doctorbillinfoTotalvalue.setText("订单总价：" + decimalFormat.format(startTime * baseinfoBean.getT_cost()) +"元");
                    break;
                }
            }
        }
    }

    public void onClick(View view)
    {
        super.onClick(view);
        switch (view.getId())
        {
            case R.id.doctorbillinfo_time_addbtn:
            {
                startTime += changeTime;
                doctorbillinfoTime.setText(startTime+"分钟");
                doctorbillinfoTotalvalue.setText("订单总价：" + decimalFormat.format(startTime * value) +"元");
                break;
            }
            case R.id.doctorbillinfo_time_delbtn:
            {
                if(startTime > changeTime)
                {
                    startTime -= changeTime;
                    doctorbillinfoTime.setText(startTime+"分钟");
                    doctorbillinfoTotalvalue.setText("订单总价：" + decimalFormat.format(startTime * value) +"元");
                }
                break;
            }
            case R.id.doctorbillinfo_startpay:
            {
                if(value != 0)
                {
                    //调用billmodel接口生成订单并支付成功
                }
                else
                {

                }
                break;
            }
        }
    }

    protected void onDestroy()
    {
        billInfoPresenter.detachContextAndViewLayout();
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
}
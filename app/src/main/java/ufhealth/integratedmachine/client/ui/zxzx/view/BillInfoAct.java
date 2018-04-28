package ufhealth.integratedmachine.client.ui.zxzx.view;

import android.view.View;
import java.util.ArrayList;
import android.content.Intent;
import java.text.DecimalFormat;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.donkingliang.labels.LabelsView;
import ufhealth.integratedmachine.client.R;
import com.hwangjr.rxbus.annotation.Subscribe;
import ufhealth.integratedmachine.client.base.BaseAct;
import ufhealth.integratedmachine.client.bean.zxzx.Billinfo;
import ufhealth.integratedmachine.client.bean.zxzx.DoctorAllInfo;
import ufhealth.integratedmachine.client.ui.zxzx.view_v.BillInfoAct_V;
import ufhealth.integratedmachine.client.ui.zxzx.presenter.BillInfoPresenter;

public class BillInfoAct extends BaseAct implements BillInfoAct_V,View.OnClickListener
{
    private String TYPE = "";
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
        TYPE = getIntent().getStringExtra("type");
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

    protected void onDestroy()
    {
        billInfoPresenter.detachContextAndViewLayout();
        super.onDestroy();
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
                switch(TYPE)
                {
                    case ZxzxAct.SPZX:
                    {
                        billInfoPresenter.createVideoBill(getIntent().getStringExtra("id").trim(),startTime+"");
                        break;
                    }
                    case ZxzxAct.YYZX:
                    {
                        billInfoPresenter.createAudioBill(getIntent().getStringExtra("id").trim(),startTime+"");
                        break;
                    }
                    case ZxzxAct.TWZX:
                    {
                        billInfoPresenter.createImageTextBill(new String[]{getIntent().getStringExtra("id").trim()},new String[]{""},"");
                    }
                }
                break;
            }
        }
    }

    @Subscribe
    public void receiveCountDownTime(Long countDownTime)
    {
        super.receiveCountDownTime(countDownTime);

    }

    @Subscribe
    public void receiveCountDownFinish(Boolean isFinish)
    {
        super.receiveCountDownFinish(isFinish);

    }

    public void startAudioPayActivity(Billinfo billinfo)
    {
        Intent intent = new Intent(this,PayAct.class);
        intent.putExtra("type",TYPE);
        intent.putExtra("bill",billinfo);
        startActivity(intent);
    }

    public void startVideoPayActivity(Billinfo billinfo)
    {
        Intent intent = new Intent(this,PayAct.class);
        intent.putExtra("type",TYPE);
        intent.putExtra("bill",billinfo);
        startActivity(intent);
    }

    public void startImageTextPayActivity(Billinfo billinfo)
    {
        Intent intent = new Intent(this,PayAct.class);
        intent.putExtra("type",TYPE);
        intent.putExtra("bill",billinfo);
        startActivity(intent);
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

            switch(TYPE)
            {
                case ZxzxAct.SPZX:
                {
                    doctorbillinfoType.setText("视频咨询");
                    value = Double.valueOf(baseinfoBean.getS_cost());
                    doctorbillinfoValue.setText(baseinfoBean.getS_cost()+"元/分钟");
                    doctorbillinfoTime.setText(startTime+"分钟");
                    doctorbillinfoTimeall.setVisibility(View.VISIBLE);
                    doctorbillinfoTotalvalue.setText("订单总价：" + decimalFormat.format(startTime * baseinfoBean.getS_cost()) +"元");
                    break;
                }
                case ZxzxAct.YYZX:
                {
                    doctorbillinfoType.setText("语音咨询");
                    value = Double.valueOf(baseinfoBean.getY_cost());
                    doctorbillinfoValue.setText(baseinfoBean.getY_cost()+"元/分钟");
                    doctorbillinfoTime.setText(startTime+"分钟");
                    doctorbillinfoTimeall.setVisibility(View.VISIBLE);
                    doctorbillinfoTotalvalue.setText("订单总价：" + decimalFormat.format(startTime * baseinfoBean.getY_cost()) +"元");
                    break;
                }
                case ZxzxAct.TWZX:
                {
                    doctorbillinfoType.setText("图文咨询");
                    value = Double.valueOf(baseinfoBean.getT_cost());
                    doctorbillinfoValue.setText(baseinfoBean.getT_cost()+"元/次");
                    doctorbillinfoTimeall.setVisibility(View.GONE);
                    doctorbillinfoTotalvalue.setText("订单总价：" + decimalFormat.format(startTime * baseinfoBean.getT_cost()) +"元");
                    break;
                }
            }
        }
    }
}
package ufhealth.integratedmachine.client.ui.zxzx.view;

import java.util.List;
import android.view.View;
import java.util.ArrayList;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;
import ufhealth.integratedmachine.client.R;
import com.hwangjr.rxbus.annotation.Subscribe;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import ufhealth.integratedmachine.client.base.BaseAct;
import ufhealth.integratedmachine.client.bean.zxzx.Billinfo;
import ufhealth.integratedmachine.client.bean.zxzx.DoctorInfo;
import ufhealth.integratedmachine.client.util.DecimalFormatTools;
import ufhealth.integratedmachine.client.ui.zxzx.view_v.BillsInfoAct_V;
import ufhealth.integratedmachine.client.adapter.zxzx.BillsInfoAdapter;
import ufhealth.integratedmachine.client.ui.zxzx.presenter.BillsInfoPresenter;

public class BillsInfoAct extends BaseAct implements BillsInfoAct_V,View.OnClickListener
{
    public String TYPE;
    private Button billBtn;
    private TextView billValue;
    private RecyclerView billRecyclerview;
    private BillsInfoAdapter billsInfoAdapter;
    private BillsInfoPresenter billsInfoPresenter;

    private String questions;
    private List<String> imgsPath;
    private boolean isIncludeFreeDoctor;
    private List<DoctorInfo.ContentBean> doctors;

    protected int setLayoutResID()
    {
        return R.layout.activity_bill;
    }

    protected void initWidgets(View rootView)
    {
        super.initWidgets(rootView);
        setTitleContent("订单信息");
        TYPE = getIntent().getStringExtra("type");
        billBtn = (Button) findViewById(R.id.bill_btn);
        billValue = (TextView) findViewById(R.id.bill_value);
        billRecyclerview = (RecyclerView) findViewById(R.id.bill_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        billRecyclerview.setLayoutManager(linearLayoutManager);
        billsInfoAdapter = new BillsInfoAdapter(this,new ArrayList<DoctorInfo.ContentBean>());
        billRecyclerview.setAdapter(billsInfoAdapter);
        billBtn.setOnClickListener(this);
    }

    protected void initDatas()
    {
        billsInfoPresenter = new BillsInfoPresenter();
        billsInfoPresenter.attachContextAndViewLayer(this,this);
    }

    protected void initLogic()
    {
        questions = getIntent().getStringExtra("questions");
        imgsPath  = getIntent().getStringArrayListExtra("imgsPath");
        doctors = getIntent().getParcelableArrayListExtra("doctors");
        isIncludeFreeDoctor = getIntent().getBooleanExtra("isIncludeFreeDoctor",false);
        billsInfoAdapter.setNewData(doctors);

        List<String> lsImgsPath = new ArrayList<>();
        for(String imgPath : imgsPath)
           if(!"".equals(imgPath.trim()))
               lsImgsPath.add(imgPath);
        imgsPath.clear();
        imgsPath.addAll(lsImgsPath);
        double totalValue = 0d;
        for(DoctorInfo.ContentBean doctor : doctors)
            totalValue += doctor.getT_cost();
        billValue.setText("共选择" + doctors.size() + "个医生，订单总价：" + DecimalFormatTools.keepTwoDecimalDigits(totalValue) + "元");
    }

    public void onClick(View view)
    {
        super.onClick(view);
        switch(view.getId())
        {
            case R.id.bill_btn:
            {
                if(null != imgsPath && imgsPath.size() > 0)
                    billsInfoPresenter.uploadImgs(this,null != questions ? questions.trim() : "",imgsPath,doctors,isIncludeFreeDoctor);
                else
                {
                    List<String> doctorsId = new ArrayList<>();
                    for(DoctorInfo.ContentBean doctor : doctors)
                        if(null != doctor && 0 != doctor.getDoctor_id())
                            doctorsId.add(doctor.getDoctor_id()+"");
                    billsInfoPresenter.createImageTextBill(this,null != questions ? questions.trim() : "",imgsPath.size() > 0 ? BillsInfoPresenter.setToArray(imgsPath) : new String[]{""},BillsInfoPresenter.setToArray(doctorsId),isIncludeFreeDoctor);
                }
                break;
            }
        }
    }

    protected void onDestroy()
    {
        billsInfoPresenter.detachContextAndViewLayout();
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

    public void startImageTextPayActivity(Billinfo billinfo)
    {
        if(null != billinfo && null != billinfo.getPayOrderNumber() && null != billinfo.getTotalPrice() && null != billinfo.getCreateTime() && null != billinfo.getPayQrcodeUrl() &&
                (!"".equals(billinfo.getPayOrderNumber().trim())) && (!"".equals(billinfo.getTotalPrice().trim())) && (!"".equals(billinfo.getCreateTime().trim())) && (!"".equals(billinfo.getPayQrcodeUrl().trim())))
        {
            Intent intent = new Intent(this,PayAct.class);
            intent.putExtra("type",TYPE);
            intent.putExtra("bill",billinfo);
            startActivity(intent);
        }
        else
        {
            showToast("亲，您的网络发生异常！请确保网络正常后再试...");
        }
    }
}
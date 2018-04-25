package ufhealth.integratedmachine.client.ui.zxzx.view;

import android.view.View;
import java.util.LinkedList;
import java.util.List;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.LinearLayout;
import ufhealth.integratedmachine.client.R;
import com.hwangjr.rxbus.annotation.Subscribe;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import ufhealth.integratedmachine.client.base.BaseAct;
import ufhealth.integratedmachine.client.bean.zxzx.DoctorInfo;
import ufhealth.integratedmachine.client.adapter.zxzx.DoctorInfoAdapter;
import ufhealth.integratedmachine.client.ui.zxzx.view_v.ChooseDoctorAct_V;
import ufhealth.integratedmachine.client.ui.zxzx.presenter.ChooseDoctorPresenter;

public class ChooseDoctorAct extends BaseAct implements ChooseDoctorAct_V,View.OnClickListener
{
    private LinearLayout chosedocDefaultsortLl;
    private TextView chosedocDefaultsortName;
    private LinearLayout chosedocSourceLl;
    private TextView chosedocSourceName;
    private LinearLayout chosedocDepartmentLl;
    private TextView chosedocDepartmentName;
    private LinearLayout chosedocHospitalLl;
    private TextView chosedocHospitalName;
    private EditText chosedocSearchEt;
    private Button chosedocSearchBtn;
    private RecyclerView chosedocRecyclerview;
    private DoctorInfoAdapter doctorInfoAdapter;
    private ChooseDoctorPresenter chooseDoctorPresenter;

    public String TYPE;
    public  static  final  String  SPZX = "spzx";
    public  static  final  String  YYZX = "yyzx";
    public  static  final  String  MYYZ = "MYYZ";

    protected int setLayoutResID()
    {
        return R.layout.activity_choosedoctor;
    }

    protected void initWidgets(View rootView)
    {
        super.initWidgets(rootView);
        setTitleContent("选择医生");
        chosedocDefaultsortLl = rootView.findViewById(R.id.chosedoc_defaultsort_ll);
        chosedocDefaultsortName = rootView.findViewById(R.id.chosedoc_defaultsort_name);
        chosedocSourceLl = rootView.findViewById(R.id.chosedoc_source_ll);
        chosedocSourceName = rootView.findViewById(R.id.chosedoc_source_name);
        chosedocDepartmentLl = rootView.findViewById(R.id.chosedoc_department_ll);
        chosedocDepartmentName = rootView.findViewById(R.id.chosedoc_department_name);
        chosedocHospitalLl = rootView.findViewById(R.id.chosedoc_hospital_ll);
        chosedocHospitalName = rootView.findViewById(R.id.chosedoc_hospital_name);
        chosedocSearchEt = rootView.findViewById(R.id.chosedoc_search_et);
        chosedocSearchBtn = rootView.findViewById(R.id.chosedoc_search_btn);
        chosedocRecyclerview = rootView.findViewById(R.id.chosedoc_recyclerview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        chosedocRecyclerview.setLayoutManager(linearLayoutManager);
        if(getIntent().getStringExtra("type").equals(SPZX))
            doctorInfoAdapter = new DoctorInfoAdapter(this,new LinkedList<DoctorInfo.ContentBean>(),DoctorInfoAdapter.LJZX,DoctorInfoAdapter.SP);
        else if(getIntent().getStringExtra("type").equals(YYZX))
            doctorInfoAdapter = new DoctorInfoAdapter(this,new LinkedList<DoctorInfo.ContentBean>(),DoctorInfoAdapter.LJZX,DoctorInfoAdapter.YY);
        else if(getIntent().getStringExtra("type").equals(MYYZ))
            doctorInfoAdapter = new DoctorInfoAdapter(this,new LinkedList<DoctorInfo.ContentBean>(),DoctorInfoAdapter.LJZX,DoctorInfoAdapter.MY);
        chosedocRecyclerview.setAdapter(doctorInfoAdapter);

        chosedocSearchBtn.setOnClickListener(this);
        chosedocHospitalLl.setOnClickListener(this);
        chosedocDepartmentLl.setOnClickListener(this);
        chosedocSourceLl.setOnClickListener(this);
        chosedocDefaultsortLl.setOnClickListener(this);
    }

    protected void initDatas()
    {
        TYPE = getIntent().getStringExtra("type");
        chooseDoctorPresenter = new ChooseDoctorPresenter();
        chooseDoctorPresenter.attachContextAndViewLayer(this,this);
    }

    protected void initLogic()
    {
        chooseDoctorPresenter.getDoctorInfoOfConditions();
        chooseDoctorPresenter.initConditions();
        chooseDoctorPresenter.getDoctorInfo();
    }

    public void setAdapterDatas(List<DoctorInfo.ContentBean> doctorsInfo)
    {
        doctorInfoAdapter.setNewData(doctorsInfo);
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

    public void onClick(View view)
    {
        super.onClick(view);
        switch (view.getId())
        {
            case R.id.chosedoc_search_btn:
            {
                break;
            }
            case R.id.chosedoc_hospital_ll:
            {
                break;
            }
            case R.id.chosedoc_department_ll:
            {
                break;
            }
            case R.id.chosedoc_source_ll:
            {
                break;
            }
            case R.id.chosedoc_defaultsort_ll:
            {
                break;
            }
        }
    }

    protected void onDestroy()
    {
        chooseDoctorPresenter.detachContextAndViewLayout();
        super.onDestroy();
    }
}
package ufhealth.integratedmachine.client.ui.zxzx.view;

import android.app.Dialog;
import android.graphics.Color;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.LinearLayout;
import ufhealth.integratedmachine.client.R;

import com.flyco.dialog.entity.DialogMenuItem;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.NormalListDialog;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.yuan.devlibrary._12_______Utils.ArraysetConvertedTools;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import ufhealth.integratedmachine.client.base.BaseAct;
import ufhealth.integratedmachine.client.bean.zxzx.DoctorInfo;
import ufhealth.integratedmachine.client.adapter.zxzx.DoctorInfoAdapter;
import ufhealth.integratedmachine.client.bean.zxzx.DoctorInfoOfCondition;
import ufhealth.integratedmachine.client.ui.zxzx.view_v.ChooseDoctorAct_V;
import ufhealth.integratedmachine.client.ui.zxzx.presenter.ChooseDoctorPresenter;
import ufhealth.integratedmachine.client.widget.ListDialog;

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
    public  static  final  String  MYYZ = "myyz";

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
            doctorInfoAdapter = DoctorInfoAdapter.getAdapter(this,new LinkedList<DoctorInfo.ContentBean>(),DoctorInfoAdapter.LJZX,DoctorInfoAdapter.SP);
        else if(getIntent().getStringExtra("type").equals(YYZX))
            doctorInfoAdapter = DoctorInfoAdapter.getAdapter(this,new LinkedList<DoctorInfo.ContentBean>(),DoctorInfoAdapter.LJZX,DoctorInfoAdapter.YY);
        else if(getIntent().getStringExtra("type").equals(MYYZ))
            doctorInfoAdapter = DoctorInfoAdapter.getAdapter(this,new LinkedList<DoctorInfo.ContentBean>(),DoctorInfoAdapter.LJZX,DoctorInfoAdapter.MY);
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

    public void initAdapterDatas(List<DoctorInfo.ContentBean> doctorsInfo)
    {
        doctorInfoAdapter.setNewData(doctorsInfo);
    }

    public void onClick(View view)
    {
        super.onClick(view);
        switch (view.getId())
        {
            case R.id.chosedoc_search_btn:
            {
                chooseDoctorPresenter.setContent();
                break;
            }
            case R.id.chosedoc_hospital_ll:
            {
                chooseDoctorPresenter.getHospitalOptions();
                break;
            }
            case R.id.chosedoc_department_ll:
            {
                chooseDoctorPresenter.getDepartmentOptions();
                break;
            }
            case R.id.chosedoc_source_ll:
            {
                chooseDoctorPresenter.getSourceOptions();
                break;
            }
            case R.id.chosedoc_defaultsort_ll:
            {
                chooseDoctorPresenter.getSortOptions();
                break;
            }
        }
    }

    public void showSortOptions(final List<DoctorInfoOfCondition.SortBean> sortConditions)
    {
        boolean isEmpty = true;
        String[] conditionsName = new String[sortConditions.size()];
        for(int index = 0 ; index < sortConditions.size() ; index++)
        {
            DoctorInfoOfCondition.SortBean sortBean = sortConditions.get(index);
            if(null != sortBean && null != sortBean.getName() && null != sortBean.getId() && !sortBean.getName().trim().equals("") && !sortBean.getId().trim().equals(""))
            {
                isEmpty = false;
                conditionsName[index] = sortBean.getName().trim();
            }
        }
        if(isEmpty && conditionsName.length > 0)
            conditionsName[0] = "暂无选择项哟！";
        else if(isEmpty)
            conditionsName = new String[]{"暂无选择项哟！"};
        else
        {
            String[] temp = new String[conditionsName.length + 1];
            for(int index = 0;index < conditionsName.length; index++)
                temp[index] = conditionsName[index];
            temp[conditionsName.length] = "取消选择！";
            conditionsName = temp;
        }
        final boolean finalIsEmpty = isEmpty;
        final ListDialog listDialog = showConditionsDialog(conditionsName,4);
        listDialog.setOnOperItemClickL(new OnOperItemClickL()
        {
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                if(listDialog.isShowing())
                    listDialog.dismiss();

                if(!finalIsEmpty)
                {
                    for(int index=0;index<sortConditions.size();index++)
                    {
                        if(null == sortConditions.get(index))
                        {
                            continue;
                        }
                        else if(null == sortConditions.get(index).getName())
                        {
                            sortConditions.get(index).setSelected(false);
                            continue;
                        }
                        else if(!sortConditions.get(index).getName().trim().equals(((DialogMenuItem)parent.getAdapter().getItem(position)).mOperName.trim()))
                        {
                            sortConditions.get(index).setSelected(false);
                            if(index == sortConditions.size() - 1 && ((DialogMenuItem)parent.getAdapter().getItem(position)).mOperName.trim().equals("取消选择！"))
                            {
                                if(!("默认排序".equals(chosedocDefaultsortName.getText().toString().trim())))
                                    chooseDoctorPresenter.getDoctorInfo();
                                chosedocDefaultsortName.setText("默认排序");
                            }
                            continue;
                        }
                        else
                        {
                            sortConditions.get(index).setSelected(true);
                            if(!(((DialogMenuItem)parent.getAdapter().getItem(position)).mOperName.trim().equals(chosedocDefaultsortName.getText().toString().trim())))
                                chooseDoctorPresenter.getDoctorInfo();
                            chosedocDefaultsortName.setText(sortConditions.get(index).getName().trim());
                        }
                    }
                }
            }
        });
    }

    public void showSourceOptions(final List<DoctorInfoOfCondition.OriginalBean> sourceConditions)
    {
        boolean isEmpty = true;
        String[] conditionsName = new String[sourceConditions.size()];
        for(int index = 0 ; index < sourceConditions.size() ; index++)
        {
            DoctorInfoOfCondition.OriginalBean sourceBean = sourceConditions.get(index);
            if(null != sourceBean && null != sourceBean.getName() && null != sourceBean.getCode() && !sourceBean.getName().trim().equals("") && !sourceBean.getCode().trim().equals(""))
            {
                isEmpty = false;
                conditionsName[index] = sourceBean.getName().trim();
            }
        }
        if(isEmpty && conditionsName.length > 0)
            conditionsName[0] = "暂无选择项哟！";
        else if(isEmpty)
            conditionsName = new String[]{"暂无选择项哟！"};
        else
        {
            String[] temp = new String[conditionsName.length + 1];
            for(int index = 0;index < conditionsName.length; index++)
                temp[index] = conditionsName[index];
            temp[conditionsName.length] = "取消选择！";
            conditionsName = temp;
        }
        final boolean finalIsEmpty = isEmpty;
        final ListDialog listDialog = showConditionsDialog(conditionsName,4);
        listDialog.setOnOperItemClickL(new OnOperItemClickL()
        {
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                if(listDialog.isShowing())
                    listDialog.dismiss();
                if(!finalIsEmpty)
                {
                    for(int index=0;index<sourceConditions.size();index++)
                    {
                        if(null == sourceConditions.get(index))
                        {
                            continue;
                        }
                        else if(null == sourceConditions.get(index).getName())
                        {
                            sourceConditions.get(index).setSelected(false);
                            continue;
                        }
                        else if(!sourceConditions.get(index).getName().trim().equals(((DialogMenuItem)parent.getAdapter().getItem(position)).mOperName.trim()))
                        {
                            sourceConditions.get(index).setSelected(false);
                            if(index == sourceConditions.size() - 1 && ((DialogMenuItem)parent.getAdapter().getItem(position)).mOperName.trim().equals("取消选择！"))
                            {
                                if(!("来源".equals(chosedocSourceName.getText().toString().trim())))
                                    chooseDoctorPresenter.getDoctorInfo();
                                chosedocSourceName.setText("来源");
                            }
                            continue;
                        }
                        else
                        {
                            sourceConditions.get(index).setSelected(true);
                            if(!(((DialogMenuItem)parent.getAdapter().getItem(position)).mOperName.trim().equals(chosedocSourceName.getText().toString().trim())))
                                chooseDoctorPresenter.getDoctorInfo();
                            chosedocSourceName.setText(sourceConditions.get(index).getName().trim());
                        }
                    }
                }
            }
        });
    }

    public void showHospitalOptions(final List<DoctorInfoOfCondition.HospitalBean> hospitalConditions)
    {
        boolean isEmpty = true;
        String[] conditionsName = new String[hospitalConditions.size()];
        for(int index = 0 ; index < hospitalConditions.size() ; index++)
        {
            DoctorInfoOfCondition.HospitalBean hospitalBean = hospitalConditions.get(index);
            if(null != hospitalBean && null != hospitalBean.getName() && null != hospitalBean.getId() && !hospitalBean.getName().trim().equals("") && !hospitalBean.getId().trim().equals(""))
            {
                isEmpty = false;
                conditionsName[index] = hospitalBean.getName().trim();
            }
        }
        if(isEmpty && conditionsName.length > 0)
            conditionsName[0] = "暂无选择项哟！";
        else if(isEmpty)
            conditionsName = new String[]{"暂无选择项哟！"};
        else
        {
            String[] temp = new String[conditionsName.length + 1];
            for(int index = 0;index < conditionsName.length; index++)
                temp[index] = conditionsName[index];
            temp[conditionsName.length] = "取消选择！";
            conditionsName = temp;
        }
        final boolean finalIsEmpty = isEmpty;
        final ListDialog listDialog = showConditionsDialog(conditionsName,4);
        listDialog.setOnOperItemClickL(new OnOperItemClickL()
        {
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                if(listDialog.isShowing())
                    listDialog.dismiss();
                if(!finalIsEmpty)
                {
                    for(int index=0;index<hospitalConditions.size();index++)
                    {
                        if(null == hospitalConditions.get(index))
                        {
                            continue;
                        }
                        else if(null == hospitalConditions.get(index).getName())
                        {
                            hospitalConditions.get(index).setSelected(false);
                            continue;
                        }
                        else if(!hospitalConditions.get(index).getName().trim().equals(((DialogMenuItem)parent.getAdapter().getItem(position)).mOperName.trim()))
                        {
                            hospitalConditions.get(index).setSelected(false);
                            if(index == hospitalConditions.size() - 1 && ((DialogMenuItem)parent.getAdapter().getItem(position)).mOperName.trim().equals("取消选择！"))
                            {
                                if(!("医院".equals(chosedocHospitalName.getText().toString().trim())))
                                    chooseDoctorPresenter.getDoctorInfo();
                                chosedocHospitalName.setText("医院");
                            }
                            continue;
                        }
                        else
                        {
                            hospitalConditions.get(index).setSelected(true);
                            if(!(((DialogMenuItem)parent.getAdapter().getItem(position)).mOperName.trim().equals(chosedocHospitalName.getText().toString().trim())))
                                chooseDoctorPresenter.getDoctorInfo();
                            chosedocHospitalName.setText(hospitalConditions.get(index).getName().trim());
                        }
                    }
                }
            }
        });
    }

    public void showDepartmentOptions(final List<DoctorInfoOfCondition.DepartmentBean> departmentConditions)
    {
        boolean isEmpty = true;
        String[] conditionsName = new String[departmentConditions.size()];
        for(int index = 0 ; index < departmentConditions.size() ; index++)
        {
            DoctorInfoOfCondition.DepartmentBean departmentBean = departmentConditions.get(index);
            if(null != departmentBean && null != departmentBean.getName() && null != departmentBean.getId() && !departmentBean.getName().trim().equals("") && !departmentBean.getId().trim().equals(""))
            {
                isEmpty = false;
                conditionsName[index] = departmentBean.getName().trim();
            }
        }
        if(isEmpty && conditionsName.length > 0)
            conditionsName[0] = "暂无选择项哟！";
        else if(isEmpty)
            conditionsName = new String[]{"暂无选择项哟！"};
        else
        {
            String[] temp = new String[conditionsName.length + 1];
            for(int index = 0;index < conditionsName.length; index++)
                temp[index] = conditionsName[index];
            temp[conditionsName.length] = "取消选择！";
            conditionsName = temp;
        }
        final boolean finalIsEmpty = isEmpty;
        final ListDialog listDialog = showConditionsDialog(conditionsName,4);
        listDialog.setOnOperItemClickL(new OnOperItemClickL()
        {
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                if(listDialog.isShowing())
                    listDialog.dismiss();
                if(!finalIsEmpty)
                {
                    for(int index=0;index<departmentConditions.size();index++)
                    {
                        if(null == departmentConditions.get(index))
                        {
                            continue;
                        }
                        else if(null == departmentConditions.get(index).getName())
                        {
                            departmentConditions.get(index).setSelected(false);
                            continue;
                        }
                        else if(!departmentConditions.get(index).getName().trim().equals(((DialogMenuItem)parent.getAdapter().getItem(position)).mOperName.trim()))
                        {
                            departmentConditions.get(index).setSelected(false);
                            if(index == departmentConditions.size() - 1 && ((DialogMenuItem)parent.getAdapter().getItem(position)).mOperName.trim().equals("取消选择！"))
                            {
                                if(!("科室".equals(chosedocDepartmentName.getText().toString().trim())))
                                    chooseDoctorPresenter.getDoctorInfo();
                                chosedocDepartmentName.setText("科室");
                            }
                            continue;
                        }
                        else
                        {
                            departmentConditions.get(index).setSelected(true);
                            if(!(((DialogMenuItem)parent.getAdapter().getItem(position)).mOperName.trim().equals(chosedocDepartmentName.getText().toString().trim())))
                                chooseDoctorPresenter.getDoctorInfo();
                            chosedocDepartmentName.setText(departmentConditions.get(index).getName().trim());
                        }
                    }
                }
            }
        });
    }

    /*****************************1医院----2科室----3来源----4排序规则*****************************/
    public ListDialog showConditionsDialog(String[] strSz, final Integer contitionsType)
    {
        ListDialog conditionsDialog = new ListDialog(this,strSz);
        conditionsDialog.titleBgColor(Color.argb(255,0,147,221));
        conditionsDialog.titleTextColor(Color.argb(255,255,255,255));
        conditionsDialog.titleTextSize_SP(32);
        conditionsDialog.cornerRadius(4);
        conditionsDialog.itemTextSize(28);
        switch(contitionsType)
        {
            case 1:conditionsDialog.setTitle("医院选择：");break;
            case 2:conditionsDialog.setTitle("科室选择：");break;
            case 3:conditionsDialog.setTitle("来源选择：");break;
            case 4:conditionsDialog.setTitle("排序规则：");break;
            default:conditionsDialog.setTitle("排序规则：");break;
        }
        conditionsDialog.widthScale(0.4f);
        conditionsDialog.heightScale(0.6f);
        conditionsDialog.show();
        return conditionsDialog;
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

    protected void onDestroy()
    {
        chooseDoctorPresenter.detachContextAndViewLayout();
        super.onDestroy();
    }
}
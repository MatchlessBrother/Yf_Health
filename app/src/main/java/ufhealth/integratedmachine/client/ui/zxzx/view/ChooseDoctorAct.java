package ufhealth.integratedmachine.client.ui.zxzx.view;

import java.util.List;
import android.view.View;
import java.util.LinkedList;
import android.widget.Button;
import android.content.Intent;
import android.graphics.Color;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import ufhealth.integratedmachine.client.R;
import com.flyco.dialog.entity.DialogMenuItem;
import com.hwangjr.rxbus.annotation.Subscribe;
import android.support.v7.widget.RecyclerView;
import com.flyco.dialog.listener.OnOperItemClickL;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import ufhealth.integratedmachine.client.base.BaseAct;
import com.chad.library.adapter.base.BaseQuickAdapter;
import ufhealth.integratedmachine.client.widget.ListDialog;
import ufhealth.integratedmachine.client.bean.zxzx.DoctorInfo;
import ufhealth.integratedmachine.client.adapter.zxzx.DoctorInfoAdapter;
import ufhealth.integratedmachine.client.bean.zxzx.DoctorInfoOfCondition;
import ufhealth.integratedmachine.client.ui.zxzx.view_v.ChooseDoctorAct_V;
import ufhealth.integratedmachine.client.ui.zxzx.presenter.ChooseDoctorPresenter;

public class ChooseDoctorAct extends BaseAct implements ChooseDoctorAct_V,View.OnClickListener
{
    public String TYPE;
    public  static  final  String  SPZX = "spzx";
    public  static  final  String  YYZX = "yyzx";
    public  static  final  String  MYYZ = "myyz";

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
    private SwipeRefreshLayout chosedocSwipeRefreshLayout;

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
        chosedocSwipeRefreshLayout = rootView.findViewById(R.id.chosedoc_swiperefreshlayout);
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

        chosedocSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            public void onRefresh()
            {
                chooseDoctorPresenter.refreshDatas();
            }
        });

        doctorInfoAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener()
        {
            public void onLoadMoreRequested()
            {
                chooseDoctorPresenter.loadMoreDatas();
            }
        },chosedocRecyclerview);

        doctorInfoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener()
        {
            public void onItemClick(BaseQuickAdapter adapter, View view, int position)
            {
                Intent intent = new Intent(ChooseDoctorAct.this,DoctorInfoAct.class);
                intent.putExtra("type",TYPE);
                intent.putExtra("id",((DoctorInfo.ContentBean)adapter.getItem(position)).getDoctor_id()+"");
                startActivity(intent);
            }
        });
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
        chooseDoctorPresenter.refreshDatas();
    }

    public void onClick(View view)
    {
        super.onClick(view);
        switch (view.getId())
        {
            case R.id.chosedoc_search_btn:
            {
                doctorInfoAdapter.setEnableLoadMore(false);
                chooseDoctorPresenter.setSearchContent(chosedocSearchEt.getText().toString().trim());
                chooseDoctorPresenter.refreshDatas();
                break;
            }
            case R.id.chosedoc_hospital_ll:
            {
                chooseDoctorPresenter.setHospitalOptions();
                break;
            }
            case R.id.chosedoc_department_ll:
            {
                chooseDoctorPresenter.setDepartmentOptions();
                break;
            }
            case R.id.chosedoc_source_ll:
            {
                chooseDoctorPresenter.setSourceOptions();
                break;
            }
            case R.id.chosedoc_defaultsort_ll:
            {
                chooseDoctorPresenter.setSortOptions();
                break;
            }
        }
    }

    public void finishRefresh()
    {
        chosedocSwipeRefreshLayout.setRefreshing(false);

    }

    public void finishLoadMore()
    {
        doctorInfoAdapter.loadMoreComplete();

    }

    public void refreshDatas(DoctorInfo doctorsInfo)
    {
        if(doctorsInfo.getContent().size() < doctorsInfo.getSize())
            doctorInfoAdapter.setEnableLoadMore(false);
        else
            doctorInfoAdapter.setEnableLoadMore(true);
        doctorInfoAdapter.setNewData(doctorsInfo.getContent());
    }

    public void loadMoreDatas(DoctorInfo doctorsInfo)
    {
        if(doctorsInfo.getContent().size() < doctorsInfo.getSize())
            doctorInfoAdapter.setEnableLoadMore(false);
        else
            doctorInfoAdapter.setEnableLoadMore(true);
        doctorInfoAdapter.addData(doctorsInfo.getContent());
        doctorInfoAdapter.notifyDataSetChanged();
    }


    /********************************1医院----2科室----3来源----4排序规则********************************/
    public ListDialog getConditionsDialog(String[] strSz, final Integer contitionsType)
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

    public void setSortOptions(final List<DoctorInfoOfCondition.SortBean> sortConditions)
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
        final ListDialog listDialog = getConditionsDialog(conditionsName,4);
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
                                chosedocDefaultsortName.setText("默认排序");
                            continue;
                        }
                        else
                        {
                            sortConditions.get(index).setSelected(true);
                            chosedocDefaultsortName.setText(sortConditions.get(index).getName().trim());
                        }
                    }
                }
            }
        });
    }

    public void setSourceOptions(final List<DoctorInfoOfCondition.OriginalBean> sourceConditions)
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
        final ListDialog listDialog = getConditionsDialog(conditionsName,3);
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
                                chosedocSourceName.setText("来源");
                            continue;
                        }
                        else
                        {
                            sourceConditions.get(index).setSelected(true);
                            chosedocSourceName.setText(sourceConditions.get(index).getName().trim());
                        }
                    }
                }
            }
        });
    }

    public void setHospitalOptions(final List<DoctorInfoOfCondition.HospitalBean> hospitalConditions)
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
        final ListDialog listDialog = getConditionsDialog(conditionsName,1);
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
                                chosedocHospitalName.setText("医院");
                            continue;
                        }
                        else
                        {
                            hospitalConditions.get(index).setSelected(true);
                            chosedocHospitalName.setText(hospitalConditions.get(index).getName().trim());
                        }
                    }
                }
            }
        });
    }

    public void setDepartmentOptions(final List<DoctorInfoOfCondition.DepartmentBean> departmentConditions)
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
        final ListDialog listDialog = getConditionsDialog(conditionsName,2);
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
                                chosedocDepartmentName.setText("科室");
                            continue;
                        }
                        else
                        {
                            departmentConditions.get(index).setSelected(true);
                            chosedocDepartmentName.setText(departmentConditions.get(index).getName().trim());
                        }
                    }
                }
            }
        });
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
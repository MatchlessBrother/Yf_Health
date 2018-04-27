package ufhealth.integratedmachine.client.ui.zxzx.presenter;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import ufhealth.integratedmachine.client.bean.BaseReturnData;
import ufhealth.integratedmachine.client.bean.zxzx.DoctorInfo;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_Presenter;
import ufhealth.integratedmachine.client.ui.zxzx.model.DoctorModel;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_LocalCallBack;
import ufhealth.integratedmachine.client.bean.zxzx.DoctorInfoOfCondition;
import ufhealth.integratedmachine.client.ui.zxzx.view_v.ChooseDoctorAct_V;

public class ChooseDoctorPresenter extends BaseMvp_Presenter<ChooseDoctorAct_V>
{
    private DoctorInfo doctorInfo;
    private Map<String,String> conditions;
    private DoctorInfoOfCondition doctorInfoOfCondition;

    public ChooseDoctorPresenter()
    {
        doctorInfo = new DoctorInfo();
        conditions = new HashMap<>();
        doctorInfoOfCondition = new DoctorInfoOfCondition();
    }

    public void setSortOptions()
    {
        if(isAttachContextAndViewLayer())
        {
            if(null != doctorInfoOfCondition && null != doctorInfoOfCondition.getSort() && doctorInfoOfCondition.getSort().size() > 0)
            {
                getViewLayer().setSortOptions(doctorInfoOfCondition.getSort());
                return;
            }
        }
        getViewLayer().setSortOptions(new ArrayList<DoctorInfoOfCondition.SortBean>());
    }

    public void setSourceOptions()
    {
        if(isAttachContextAndViewLayer())
        {
            if(null != doctorInfoOfCondition && null != doctorInfoOfCondition.getOriginal() && doctorInfoOfCondition.getOriginal().size() > 0)
            {
                getViewLayer().setSourceOptions(doctorInfoOfCondition.getOriginal());
                return;
            }
        }
        getViewLayer().setSourceOptions(new ArrayList<DoctorInfoOfCondition.OriginalBean>());
    }

    public void setHospitalOptions()
    {
        if(isAttachContextAndViewLayer())
        {
            if(null != doctorInfoOfCondition && null != doctorInfoOfCondition.getHospital() && doctorInfoOfCondition.getHospital().size() > 0)
            {
                getViewLayer().setHospitalOptions(doctorInfoOfCondition.getHospital());
                return;
            }
        }
        getViewLayer().setHospitalOptions(new ArrayList<DoctorInfoOfCondition.HospitalBean>());
    }

    public void setDepartmentOptions()
    {
        if(isAttachContextAndViewLayer())
        {
            if(null != doctorInfoOfCondition && null != doctorInfoOfCondition.getDepartment() && doctorInfoOfCondition.getDepartment().size() > 0)
            {
                getViewLayer().setDepartmentOptions(doctorInfoOfCondition.getDepartment());
                return;
            }
        }
        getViewLayer().setDepartmentOptions(new ArrayList<DoctorInfoOfCondition.DepartmentBean>());
    }

    public void setSearchContent(String searchContent)
    {
        conditions.clear();
        conditions.put("page","1");
        conditions.put("like_para",searchContent.trim());
        for(DoctorInfoOfCondition.HospitalBean hospitalBean: doctorInfoOfCondition.getHospital())
            if(hospitalBean.isSelected())
            {
                conditions.put("hospital_id",hospitalBean.getId());
                break;
            }

        for(DoctorInfoOfCondition.DepartmentBean departmentBean: doctorInfoOfCondition.getDepartment())
            if(departmentBean.isSelected())
            {
                conditions.put("department_id",departmentBean.getId());
                break;
            }

        for(DoctorInfoOfCondition.SortBean sortBean: doctorInfoOfCondition.getSort())
            if(sortBean.isSelected())
            {
                conditions.put("sort",sortBean.getId());
                break;
            }

        for(DoctorInfoOfCondition.OriginalBean sourceBean: doctorInfoOfCondition.getOriginal())
            if(sourceBean.isSelected())
            {
                conditions.put("original_id",sourceBean.getCode());
                break;
            }
    }

    public void refreshDatas()
    {
        if(isAttachContextAndViewLayer())
        {
            conditions.clear();
            conditions.put("page","1");
            DoctorModel.getDoctorsInfo(getContext(),conditions,new BaseMvp_LocalCallBack<BaseReturnData<DoctorInfo>>(this)
            {
                public void onSuccess(BaseReturnData<DoctorInfo> data)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        doctorInfo = data.getData();
                        getViewLayer().refreshDatas(doctorInfo);
                        getViewLayer().finishRefresh();
                        conditions.put("page", String.valueOf(Integer.valueOf(conditions.get("page").trim()) + 1));
                    }
                }

                public void onFailure(String msg)
                {
                    super.onFailure(msg);
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().finishRefresh();
                    }
                }

                public void onError(String msg)
                {
                    super.onError(msg);
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().finishRefresh();
                    }
                }
            });
        }
    }

    public void loadMoreDatas()
    {
        if(isAttachContextAndViewLayer())
        {
            DoctorModel.getDoctorsInfo(getContext(),conditions,new BaseMvp_LocalCallBack<BaseReturnData<DoctorInfo>>(this)
            {
                public void onSuccess(BaseReturnData<DoctorInfo> data)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        doctorInfo = data.getData();
                        getViewLayer().loadMoreDatas(doctorInfo);
                        getViewLayer().finishLoadMore();
                        conditions.put("page", String.valueOf(Integer.valueOf(conditions.get("page").trim()) + 1));
                    }
                }

                public void onFailure(String msg)
                {
                    super.onFailure(msg);
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().finishLoadMore();
                    }
                }

                public void onError(String msg)
                {
                    super.onError(msg);
                    if(isAttachContextAndViewLayer())
                    {
                        getViewLayer().finishLoadMore();
                    }
                }
            });
        }
    }

    public void getDoctorInfoOfConditions()
    {
        if(isAttachContextAndViewLayer())
        {
            DoctorModel.getDoctorInfoOfConditions(getContext(),new BaseMvp_LocalCallBack<BaseReturnData<DoctorInfoOfCondition>>(this)
            {
                public void onSuccess(BaseReturnData<DoctorInfoOfCondition> data)
                {
                    if(isAttachContextAndViewLayer())
                    {
                        doctorInfoOfCondition = data.getData();
                    }
                }

                public void onFailure(String msg)
                {
                    super.onFailure(msg);
                    if(isAttachContextAndViewLayer())
                    {

                    }
                }

                public void onError(String msg)
                {
                    super.onError(msg);
                    if(isAttachContextAndViewLayer())
                    {

                    }
                }
            });
        }
    }
}
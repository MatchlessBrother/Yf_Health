package ufhealth.integratedmachine.client.adapter.zxzx;

import java.util.List;

import android.graphics.Color;
import android.view.View;
import java.util.ArrayList;
import android.content.Intent;
import android.content.Context;
import android.widget.CheckBox;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import android.widget.CompoundButton;
import com.donkingliang.labels.LabelsView;
import ufhealth.integratedmachine.client.R;
import android.support.annotation.Nullable;
import com.yuan.devlibrary._12_______Utils.NetTools;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.BaseQuickAdapter;
import ufhealth.integratedmachine.client.base.BaseAct;
import ufhealth.integratedmachine.client.ui.zxzx.view.ZxzxAct;
import ufhealth.integratedmachine.client.bean.zxzx.DoctorInfo;
import ufhealth.integratedmachine.client.ui.zxzx.view.BillInfoAct;
import com.yuan.devlibrary._12_______Utils.CheckBoxRadioBtnModifyTools;
import ufhealth.integratedmachine.client.bean.zxzx.DoctorInfo.ContentBean;

public class DoctorInfoAdapter extends BaseQuickAdapter<DoctorInfo.ContentBean,BaseViewHolder>
{
    private Context mContext;
    private String  mTypeValue = "";
    public static final String SPZX = "spzx";// 视频咨询
    public static final String YYZX = "ypzx";// 语音咨询
    public static final String MYYZ = "myyz";// 名医义诊
    public static final String KSZX = "kszx";// 快速咨询
    public static final String BGJD = "bgjd";// 报告咨询
    public static final String RMKS = "rmks";// 热门科室
    public static final String SEARCH  = "search";//搜索

    public static DoctorInfoAdapter getAdapter(Context context,String type,@Nullable List<ContentBean> datas)
    {
        switch(type)
        {
            case RMKS: return new DoctorInfoAdapter(context,R.layout.item_doctorinfo_allinfo,type,datas);
            case MYYZ: return new DoctorInfoAdapter(context,R.layout.item_doctorinfo_allinfo,type,datas);
            case SEARCH: return new DoctorInfoAdapter(context,R.layout.item_doctorinfo_allinfo,type,datas);

            case SPZX: return new DoctorInfoAdapter(context,R.layout.item_doctorinfo_ljzx,type,datas);
            case YYZX: return new DoctorInfoAdapter(context,R.layout.item_doctorinfo_ljzx,type,datas);

            case KSZX: return new DoctorInfoAdapter(context,R.layout.item_doctorinfo_multiselect,type,datas);
            case BGJD: return new DoctorInfoAdapter(context,R.layout.item_doctorinfo_multiselect,type,datas);
            default:return null;
        }
    }

    public String getTypeValue()
    {
        return mTypeValue;
    }

    private DoctorInfoAdapter(Context context,int layoutResId,String type,@Nullable List<ContentBean> datas)
    {
       super(layoutResId, datas);
        mContext = context;
        mTypeValue  = type;
    }

    protected void convert(BaseViewHolder helper, final ContentBean contentBean)
    {
        switch (mTypeValue)
        {
            case SPZX:
            case YYZX:
            {
                Glide.with(mContext).load(null != contentBean.getAvatar() ? contentBean.getAvatar().trim() : "").
                        placeholder(R.mipmap.defaultimage).error(R.mipmap.defaultimage).
                        into((ImageView) helper.itemView.findViewById(R.id.doctorinfo_img));
                helper.setText(R.id.doctorinfo_name,(null != contentBean.getDoctor_name() ? contentBean.getDoctor_name().trim() : "未知"));
                helper.setText(R.id.doctorinfo_position,(null != contentBean.getJob_name() ? contentBean.getJob_name().trim() : "未知"));
                helper.setText(R.id.doctorinfo_hospitalname,(null != contentBean.getHospital_name() ? contentBean.getHospital_name().trim() : "未知"));
                helper.setText(R.id.doctorinfo_departmentname,(null != contentBean.getDepartment_name() ? contentBean.getDepartment_name().trim() : "未知"));
                helper.setText(R.id.doctorinfo_specialize,(null != contentBean.getBe_good_at() ? "擅长：" + contentBean.getBe_good_at().trim() : "擅长：未知"));
                helper.setText(R.id.doctorinfo_source,(null != contentBean.getOriginal() ? "来源：" + contentBean.getOriginal().trim() : "来源：未知"));
                switch (mTypeValue)
                {
                    case SPZX:helper.setText(R.id.doctorinfo_value,"¥" + contentBean.getS_cost() + " / 分钟");break;
                    case YYZX:helper.setText(R.id.doctorinfo_value,"¥" + contentBean.getY_cost() + " / 分钟");break;
                }
                LabelsView labelsView = helper.itemView.findViewById(R.id.doctorinfo_labels);
                labelsView.setLabels(null != contentBean.getLabels() && contentBean.getLabels().size() != 0 ? contentBean.getLabels() : new ArrayList<String>());
                if(contentBean.getIs_service() == 0)
                {
                    helper.setText(R.id.doctorinfo_ljzx,"暂不提供服务");
                    helper.itemView.findViewById(R.id.doctorinfo_ljzx).setEnabled(false);
                    helper.itemView.findViewById(R.id.doctorinfo_ljzx).setBackgroundColor(Color.argb(255,204,204,204));
                }
                else
                {
                    helper.setText(R.id.doctorinfo_ljzx,"立即咨询");
                    helper.itemView.findViewById(R.id.doctorinfo_ljzx).setEnabled(true);
                    helper.itemView.findViewById(R.id.doctorinfo_ljzx).setBackgroundColor(Color.argb(255,0,147,221));
                }

                helper.itemView.findViewById(R.id.doctorinfo_ljzx).setOnClickListener(new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        if(NetTools.WhetherConnectNet(mContext) && 0 != contentBean.getDoctor_id())
                        {
                            Intent intent = new Intent(mContext, BillInfoAct.class);
                            switch (mTypeValue)
                            {
                                case SPZX:intent.putExtra("type", ZxzxAct.SPZX);break;
                                case YYZX:intent.putExtra("type",ZxzxAct.YYZX);break;
                            }
                            intent.putExtra("id",contentBean.getDoctor_id()+"");
                            mContext.startActivity(intent);
                        }
                        else
                        {
                            ((BaseAct)mContext).showToast("亲，您的网络有问题！请稍后再试...");
                        }
                    }
                });
                break;
            }

            case RMKS:
            case MYYZ:
            case SEARCH:
            {
                Glide.with(mContext).load(null != contentBean.getAvatar() ? contentBean.getAvatar().trim() : "").
                        placeholder(R.mipmap.defaultimage).error(R.mipmap.defaultimage).
                        into((ImageView) helper.itemView.findViewById(R.id.doctorinfo_img));
                helper.setText(R.id.doctorinfo_name,(null != contentBean.getDoctor_name() ? contentBean.getDoctor_name().trim() : "未知"));
                helper.setText(R.id.doctorinfo_position,(null != contentBean.getJob_name() ? contentBean.getJob_name().trim() : "未知"));
                helper.setText(R.id.doctorinfo_hospitalname,(null != contentBean.getHospital_name() ? contentBean.getHospital_name().trim() : "未知"));
                helper.setText(R.id.doctorinfo_departmentname,(null != contentBean.getDepartment_name() ? contentBean.getDepartment_name().trim() : "未知"));
                helper.setText(R.id.doctorinfo_specialize,(null != contentBean.getBe_good_at() ? "擅长：" + contentBean.getBe_good_at().trim() : "擅长：未知"));
                helper.setText(R.id.doctorinfo_source,(null != contentBean.getOriginal() ? "来源：" + contentBean.getOriginal().trim() : "来源：未知"));
                helper.setGone(R.id.doctorinfo_valuedetail_spzx_all,contentBean.getS_cost() >= 0 ? true :false);
                helper.setText(R.id.doctorinfo_valuedetail_spzx,contentBean.getS_cost() + "元/分钟");
                helper.setGone(R.id.doctorinfo_valuedetail_yyzx_all,contentBean.getY_cost() >= 0 ? true :false);
                helper.setText(R.id.doctorinfo_valuedetail_yyzx,contentBean.getY_cost() + "元/分钟");
                helper.setGone(R.id.doctorinfo_valuedetail_twzx_all,contentBean.getT_cost() >= 0 ? true :false);
                helper.setText(R.id.doctorinfo_valuedetail_twzx, contentBean.getT_cost() + "元/次");
                LabelsView labelsView = helper.itemView.findViewById(R.id.doctorinfo_labels_allinfo);
                labelsView.setLabels(null != contentBean.getLabels() && contentBean.getLabels().size() != 0 ? contentBean.getLabels() : new ArrayList<String>());
                break;
            }

            case KSZX:
            case BGJD:
            {
                Glide.with(mContext).load(null != contentBean.getAvatar() ? contentBean.getAvatar().trim() : "").
                        placeholder(R.mipmap.defaultimage).error(R.mipmap.defaultimage).
                        into((ImageView) helper.itemView.findViewById(R.id.doctorinfo_img));
                helper.setText(R.id.doctorinfo_name,(null != contentBean.getDoctor_name() ? contentBean.getDoctor_name().trim() : "未知"));
                helper.setText(R.id.doctorinfo_position,(null != contentBean.getJob_name() ? contentBean.getJob_name().trim() : "未知"));
                helper.setText(R.id.doctorinfo_hospitalname,(null != contentBean.getHospital_name() ? contentBean.getHospital_name().trim() : "未知"));
                helper.setText(R.id.doctorinfo_departmentname,(null != contentBean.getDepartment_name() ? contentBean.getDepartment_name().trim() : "未知"));
                helper.setText(R.id.doctorinfo_specialize,(null != contentBean.getBe_good_at() ? "擅长：" + contentBean.getBe_good_at().trim() : "擅长：未知"));
                CheckBoxRadioBtnModifyTools.setHavedDrawbleView(mContext,(CheckBox)helper.itemView.findViewById(R.id.doctorinfo_checkbox),R.drawable.checkbox_blue_no,60,60,3);
                switch (mTypeValue)
                {
                    case KSZX:
                    {
                        if(contentBean.getIs_service() == 1)
                        {
                            ((CheckBox)helper.itemView.findViewById(R.id.doctorinfo_checkbox)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
                            {
                                public void onCheckedChanged(CompoundButton compoundButton, boolean selected)
                                {
                                    contentBean.setSelected(selected);
                                }
                            });
                            helper.setText(R.id.doctorinfo_checkbox,"¥" + contentBean.getT_cost() + " / 次");
                            helper.setChecked(R.id.doctorinfo_checkbox,contentBean.isSelected());
                            helper.itemView.findViewById(R.id.doctorinfo_checkbox).setEnabled(true);
                            helper.setTextColor(R.id.doctorinfo_checkbox,Color.argb(255,255,0,0));
                            CheckBoxRadioBtnModifyTools.setHavedDrawbleView(mContext,(CheckBox)helper.itemView.findViewById(R.id.doctorinfo_checkbox),R.drawable.checkbox_blue_no,60,60,3);
                        }
                        else
                        {
                            ((CheckBox)helper.itemView.findViewById(R.id.doctorinfo_checkbox)).setOnCheckedChangeListener(null);
                            helper.setText(R.id.doctorinfo_checkbox,"暂不提供服务");
                            helper.setChecked(R.id.doctorinfo_checkbox,false);
                            helper.itemView.findViewById(R.id.doctorinfo_checkbox).setEnabled(false);
                            helper.setTextColor(R.id.doctorinfo_checkbox,Color.argb(255,204,204,204));
                            CheckBoxRadioBtnModifyTools.setHavedDrawbleView(mContext,(CheckBox)helper.itemView.findViewById(R.id.doctorinfo_checkbox),R.drawable.checkbox_blue_no,0,0,3);
                        }
                        break;
                    }
                    case BGJD:
                    {
                        if(contentBean.getIs_service() == 1)
                        {
                            ((CheckBox)helper.itemView.findViewById(R.id.doctorinfo_checkbox)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
                            {
                                public void onCheckedChanged(CompoundButton compoundButton, boolean selected)
                                {
                                    contentBean.setSelected(selected);
                                }
                            });
                            helper.setText(R.id.doctorinfo_checkbox,"¥" + contentBean.getT_cost() + " / 次");
                            helper.setChecked(R.id.doctorinfo_checkbox,contentBean.isSelected());
                            helper.itemView.findViewById(R.id.doctorinfo_checkbox).setEnabled(true);
                            helper.setTextColor(R.id.doctorinfo_checkbox,Color.argb(255,255,0,0));
                            CheckBoxRadioBtnModifyTools.setHavedDrawbleView(mContext,(CheckBox)helper.itemView.findViewById(R.id.doctorinfo_checkbox),R.drawable.checkbox_blue_no,60,60,3);

                        }
                        else
                        {
                            ((CheckBox)helper.itemView.findViewById(R.id.doctorinfo_checkbox)).setOnCheckedChangeListener(null);
                            helper.setText(R.id.doctorinfo_checkbox,"暂不提供服务");
                            helper.setChecked(R.id.doctorinfo_checkbox,false);
                            helper.itemView.findViewById(R.id.doctorinfo_checkbox).setEnabled(false);
                            helper.setTextColor(R.id.doctorinfo_checkbox,Color.argb(255,204,204,204));
                            CheckBoxRadioBtnModifyTools.setHavedDrawbleView(mContext,(CheckBox)helper.itemView.findViewById(R.id.doctorinfo_checkbox),R.drawable.checkbox_blue_no,0,0,3);
                        }
                        break;
                    }
                }
                LabelsView labelsView = helper.itemView.findViewById(R.id.doctorinfo_labels_checkbox);
                labelsView.setLabels(null != contentBean.getLabels() && contentBean.getLabels().size() != 0 ? contentBean.getLabels() : new ArrayList<String>());
                break;
            }
        }
    }
}
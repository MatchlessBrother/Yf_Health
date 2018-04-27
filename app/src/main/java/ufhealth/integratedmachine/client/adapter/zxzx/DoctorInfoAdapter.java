package ufhealth.integratedmachine.client.adapter.zxzx;

import java.util.List;
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
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.BaseQuickAdapter;
import ufhealth.integratedmachine.client.bean.zxzx.DoctorInfo;
import ufhealth.integratedmachine.client.ui.zxzx.view.BillInfoAct;
import ufhealth.integratedmachine.client.ui.zxzx.view.ChooseDoctorAct;
import ufhealth.integratedmachine.client.bean.zxzx.DoctorInfo.ContentBean;

public class DoctorInfoAdapter extends BaseQuickAdapter<DoctorInfo.ContentBean,BaseViewHolder>
{
    private Context mContext;
    private String mType = "";
    public static final String LJZX = "ljzx";//立即咨询
    public static final String SHOWINFOS = "showinfos";//仅仅显示详情
    public static final String MULTISELECT = "multiselect";//多选情况

    private String mMediaType = "";
    public static final String SP = "sp";//视频咨询
    public static final String YY = "yy";//语音咨询
    public static final String MY = "my";//名医义诊
    public static final String KS = "ks";//快速咨询
    public static final String BG = "bg";//报告咨询

    public static DoctorInfoAdapter getAdapter(Context context,@Nullable List<ContentBean> datas,String type,String mediaType)
    {
        switch(type)
        {
            case LJZX: return new DoctorInfoAdapter(context,R.layout.item_doctorinfo_ljzx,datas,type,mediaType);
            case SHOWINFOS: return new DoctorInfoAdapter(context,R.layout.item_doctorinfo_allinfo,datas,type,mediaType);
            case MULTISELECT: return new DoctorInfoAdapter(context,R.layout.item_doctorinfo_multiselect,datas,type,mediaType);
            default:return new DoctorInfoAdapter(context,R.layout.item_doctorinfo_ljzx,datas,type,mediaType);
        }
    }

    private DoctorInfoAdapter(Context context,int layoutResId,@Nullable List<ContentBean> datas,String type,String mediaType)
    {
       super(layoutResId, datas);
        mContext = context;
        mType = (null != type ? type.trim() : LJZX);
        mMediaType = (null != mediaType ? mediaType.trim() : SP);
    }

    protected void convert(BaseViewHolder helper, final ContentBean bean)
    {
        switch (mType)
        {
            case LJZX:
            {
                Glide.with(mContext).load(null != bean.getAvatar() ? bean.getAvatar().trim() : "").
                        placeholder(R.mipmap.defaultimage).error(R.mipmap.defaultimage).
                        into((ImageView) helper.itemView.findViewById(R.id.doctorinfo_img));
                helper.setText(R.id.doctorinfo_name,(null != bean.getDoctor_name() ? bean.getDoctor_name().trim() : "未知"));
                helper.setText(R.id.doctorinfo_position,(null != bean.getJob_name() ? bean.getJob_name().trim() : "未知"));
                helper.setText(R.id.doctorinfo_hospitalname,(null != bean.getHospital_name() ? bean.getHospital_name().trim() : "未知"));
                helper.setText(R.id.doctorinfo_departmentname,(null != bean.getDepartment_name() ? bean.getDepartment_name().trim() : "未知"));
                helper.setText(R.id.doctorinfo_specialize,(null != bean.getBe_good_at() ? "擅长：" + bean.getBe_good_at().trim() : "擅长：未知"));
                helper.setText(R.id.doctorinfo_source,(null != bean.getOriginal() ? "来源：" + bean.getOriginal().trim() : "来源：未知"));
                switch (mMediaType)
                {
                    case SP:helper.setText(R.id.doctorinfo_value,"¥" + bean.getS_cost() + " / 分钟");break;
                    case YY:helper.setText(R.id.doctorinfo_value,"¥" + bean.getY_cost() + " / 分钟");break;
                    case MY:helper.setText(R.id.doctorinfo_value,"¥" + bean.getT_cost() + " / 次");break;
                }
                LabelsView labelsView = helper.itemView.findViewById(R.id.doctorinfo_labels);
                labelsView.setLabels(null != bean.getLabels() && bean.getLabels().size() != 0 ? bean.getLabels() : new ArrayList<String>());
                helper.itemView.findViewById(R.id.doctorinfo_ljzx).setOnClickListener(new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        Intent intent = new Intent(mContext, BillInfoAct.class);
                        switch (mMediaType)
                        {
                            case SP:intent.putExtra("type", ChooseDoctorAct.SPZX);break;
                            case YY:intent.putExtra("type",ChooseDoctorAct.YYZX);break;
                            case MY:intent.putExtra("type",ChooseDoctorAct.MYYZ);break;
                        }
                        intent.putExtra("id",bean.getDoctor_id()+"");
                        mContext.startActivity(intent);
                    }
                });
                break;
            }
            case SHOWINFOS:
            {
                Glide.with(mContext).load(null != bean.getAvatar() ? bean.getAvatar().trim() : "").
                        placeholder(R.mipmap.defaultimage).error(R.mipmap.defaultimage).
                        into((ImageView) helper.itemView.findViewById(R.id.doctorinfo_img));
                helper.setText(R.id.doctorinfo_name,(null != bean.getDoctor_name() ? bean.getDoctor_name().trim() : "未知"));
                helper.setText(R.id.doctorinfo_position,(null != bean.getJob_name() ? bean.getJob_name().trim() : "未知"));
                helper.setText(R.id.doctorinfo_hospitalname,(null != bean.getHospital_name() ? bean.getHospital_name().trim() : "未知"));
                helper.setText(R.id.doctorinfo_departmentname,(null != bean.getDepartment_name() ? bean.getDepartment_name().trim() : "未知"));
                helper.setText(R.id.doctorinfo_specialize,(null != bean.getBe_good_at() ? "擅长：" + bean.getBe_good_at().trim() : "擅长：未知"));
                helper.setText(R.id.doctorinfo_source,(null != bean.getOriginal() ? "来源：" + bean.getOriginal().trim() : "来源：未知"));
                helper.setText(R.id.doctorinfo_valuedetail_spzx,bean.getS_cost() + "元/分钟");
                helper.setText(R.id.doctorinfo_valuedetail_yyzx,bean.getY_cost() + "元/分钟");
                helper.setText(R.id.doctorinfo_valuedetail_twzx, bean.getT_cost() + "元/次");
                LabelsView labelsView = helper.itemView.findViewById(R.id.doctorinfo_labels_allinfo);
                labelsView.setLabels(null != bean.getLabels() && bean.getLabels().size() != 0 ? bean.getLabels() : new ArrayList<String>());
                break;
            }
            case MULTISELECT:
            {
                Glide.with(mContext).load(null != bean.getAvatar() ? bean.getAvatar().trim() : "").
                        placeholder(R.mipmap.defaultimage).error(R.mipmap.defaultimage).
                        into((ImageView) helper.itemView.findViewById(R.id.doctorinfo_img));
                helper.setText(R.id.doctorinfo_name,(null != bean.getDoctor_name() ? bean.getDoctor_name().trim() : "未知"));
                helper.setText(R.id.doctorinfo_position,(null != bean.getJob_name() ? bean.getJob_name().trim() : "未知"));
                helper.setText(R.id.doctorinfo_hospitalname,(null != bean.getHospital_name() ? bean.getHospital_name().trim() : "未知"));
                helper.setText(R.id.doctorinfo_departmentname,(null != bean.getDepartment_name() ? bean.getDepartment_name().trim() : "未知"));
                helper.setText(R.id.doctorinfo_specialize,(null != bean.getBe_good_at() ? "擅长：" + bean.getBe_good_at().trim() : "擅长：未知"));
                switch (mMediaType)
                {
                    case KS:
                    {
                        helper.setText(R.id.doctorinfo_checkbox,"¥" + bean.getT_cost() + " / 次");
                        helper.setChecked(R.id.doctorinfo_checkbox,bean.isSelected());
                        break;
                    }
                    case BG:
                    {
                        helper.setText(R.id.doctorinfo_checkbox,"¥" + bean.getT_cost() + " / 次");
                        helper.setChecked(R.id.doctorinfo_checkbox,bean.isSelected());
                        break;
                    }
                }
                LabelsView labelsView = helper.itemView.findViewById(R.id.doctorinfo_labels_checkbox);
                labelsView.setLabels(null != bean.getLabels() && bean.getLabels().size() != 0 ? bean.getLabels() : new ArrayList<String>());
                ((CheckBox)helper.itemView.findViewById(R.id.doctorinfo_checkbox)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
                {
                    public void onCheckedChanged(CompoundButton compoundButton, boolean selected)
                    {
                        bean.setSelected(selected);
                    }
                });
                break;
            }
        }
    }
}
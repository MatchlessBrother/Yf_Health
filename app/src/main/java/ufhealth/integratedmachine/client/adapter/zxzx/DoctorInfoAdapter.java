package ufhealth.integratedmachine.client.adapter.zxzx;

import java.util.ArrayList;
import java.util.List;
import ufhealth.integratedmachine.client.R;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.donkingliang.labels.LabelsView;

import ufhealth.integratedmachine.client.bean.zxzx.DoctorInfo;
import ufhealth.integratedmachine.client.bean.zxzx.DoctorInfo.ContentBean;
import ufhealth.integratedmachine.client.ui.zxzx.view.DoctorInfoAct;

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

    public DoctorInfoAdapter(Context context,@Nullable List<ContentBean> datas,String type,String mediaType)
    {
        super(R.layout.item_doctorinfo, datas);
        mType = (null != type ? type.trim() : LJZX);
        mContext = context;
        mMediaType = mediaType;
    }

    protected void convert(BaseViewHolder helper, final ContentBean bean)
    {
        switch (mType)
        {
            case LJZX:
            {
                helper.setGone(R.id.doctorinfo_fy_cb,false).
                        setGone(R.id.doctorinfo_fyall,false).
                        setGone(R.id.doctorinfo_labels_fyall,false).
                        setGone(R.id.doctorinfo_labels_checkbox,false);
                helper.setText(R.id.doctorinfo_source,(null != bean.getOriginal() ? "来源：" + bean.getOriginal().trim() : "来源：未知"));
                switch (mMediaType)
                {
                    case SP:helper.setText(R.id.doctorinfo_fy_tv,"¥" + bean.getS_cost() + " / 分钟");break;
                    case YY:helper.setText(R.id.doctorinfo_fy_tv,"¥" + bean.getY_cost() + " / 分钟");break;
                    case MY:helper.setText(R.id.doctorinfo_fy_tv,"¥" + bean.getM_cost() + " / 分钟");break;
                }
                LabelsView labelsView = helper.itemView.findViewById(R.id.doctorinfo_labels_btn);
                labelsView.setLabels(new ArrayList<String>());

                helper.itemView.findViewById(R.id.doctorinfo_ljzx).setOnClickListener(new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        Intent intent = new Intent(mContext, DoctorInfoAct.class);
                        intent.putExtra("doctorId",bean.getDoctor_id()+"");
                        mContext.startActivity(intent);
                    }
                });
                break;
            }
            case SHOWINFOS:
            {
                helper.setGone(R.id.doctorinfo_fy_tv,false).
                        setGone(R.id.doctorinfo_fy_cb,false).
                        setGone(R.id.doctorinfo_ljzx,false).
                        setGone(R.id.doctorinfo_labels_btn,false).
                        setGone(R.id.doctorinfo_labels_checkbox,false);
                helper.setText(R.id.doctorinfo_source,(null != bean.getOriginal() ? bean.getOriginal().trim() : ""));
                helper.setText(R.id.doctorinfo_fyall_spjg,bean.getS_cost() + "元/分钟");
                helper.setText(R.id.doctorinfo_fyall_yyjg,bean.getY_cost() + "元/分钟");
                helper.setText(R.id.doctorinfo_fyall_twjg, bean.getT_cost() + "元/次");
                LabelsView labelsView = helper.itemView.findViewById(R.id.doctorinfo_labels_fyall);
                labelsView.setLabels(new ArrayList<String>());
                break;
            }
            case MULTISELECT:
            {
                helper.setGone(R.id.doctorinfo_source,false).
                        setGone(R.id.doctorinfo_fy_tv,false).
                        setGone(R.id.doctorinfo_ljzx,false).
                        setGone(R.id.doctorinfo_fyall,false).
                        setGone(R.id.doctorinfo_labels_fyall,false).
                        setGone(R.id.doctorinfo_labels_btn,false);
                switch (mMediaType)
                {
                    case KS:helper.setText(R.id.doctorinfo_fy_cb,"¥" + bean.getK_cost() + " / 次");break;
                    case BG:helper.setText(R.id.doctorinfo_fy_cb,"¥" + bean.getB_cost() + " / 次");break;
                }
                LabelsView labelsView = helper.itemView.findViewById(R.id.doctorinfo_labels_checkbox);
                labelsView.setLabels(new ArrayList<String>());
                break;
            }
        }
        Glide.with(mContext).load(null != bean.getAvatar() ? bean.getAvatar().trim() : "").
                placeholder(R.mipmap.defaultimage).error(R.mipmap.defaultimage).
                into((ImageView) helper.itemView.findViewById(R.id.doctorinfo_img));
        helper.setText(R.id.doctorinfo_name,(null != bean.getDoctor_name() ? bean.getDoctor_name().trim() : "未知"));
        helper.setText(R.id.doctorinfo_position,(null != bean.getJob_name() ? bean.getJob_name().trim() : "未知"));
        helper.setText(R.id.doctorinfo_hospitalname,(null != bean.getHospital_name() ? bean.getHospital_name().trim() : "未知"));
        helper.setText(R.id.doctorinfo_departmentname,(null != bean.getDepartment_name() ? bean.getDepartment_name().trim() : "未知"));
        helper.setText(R.id.doctorinfo_specialize,(null != bean.getBe_good_at() ? "擅长：" + bean.getBe_good_at().trim() : "擅长：未知"));
    }
}
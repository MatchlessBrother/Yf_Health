package ufhealth.integratedmachine.client.adapter.zxzx;

import java.util.List;
import android.content.Context;
import com.bumptech.glide.Glide;
import android.widget.ImageView;
import android.support.annotation.Nullable;
import ufhealth.integratedmachine.client.R;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.BaseQuickAdapter;
import ufhealth.integratedmachine.client.bean.zxzx.DoctorInfo;

public class BillsInfoAdapter extends BaseQuickAdapter<DoctorInfo.ContentBean,BaseViewHolder>
{
    private Context mContext;

    public BillsInfoAdapter(Context context,@Nullable List<DoctorInfo.ContentBean> datas)
    {
        super(R.layout.item_billsinfo, datas);
        mContext = context;
    }

    protected void convert(BaseViewHolder helper, DoctorInfo.ContentBean contentBean)
    {
        Glide.with(mContext).load(null != contentBean.getAvatar() ? contentBean.getAvatar().trim() : "").
                placeholder(R.mipmap.defaultimage).error(R.mipmap.defaultimage).
                into((ImageView) helper.itemView.findViewById(R.id.billsinfo_img));
        helper.setText(R.id.billsinfo_name,(null != contentBean.getDoctor_name() ? contentBean.getDoctor_name().trim() : "未知"));
        helper.setText(R.id.billsinfo_position,(null != contentBean.getJob_name() ? contentBean.getJob_name().trim() : "未知"));
        helper.setText(R.id.billsinfo_hospitalname,(null != contentBean.getHospital_name() ? contentBean.getHospital_name().trim() : "未知"));
        helper.setText(R.id.billsinfo_departmentname,(null != contentBean.getDepartment_name() ? contentBean.getDepartment_name().trim() : "未知"));
        helper.setText(R.id.billsinfo_service_value,"服务费用：" + contentBean.getT_cost() + "元 / 次");
    }
}
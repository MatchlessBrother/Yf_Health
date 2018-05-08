package ufhealth.integratedmachine.client.adapter.zxzx;

import java.util.List;
import android.view.View;
import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import android.support.annotation.Nullable;
import ufhealth.integratedmachine.client.R;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import ufhealth.integratedmachine.client.bean.zxzx.HotDepartment;

public class ZxzxHotDepartmentAdapter extends BaseQuickAdapter<HotDepartment, BaseViewHolder>
{
    private Context mContext;

    public ZxzxHotDepartmentAdapter(Context context, @Nullable List<HotDepartment> data)
    {
        super(R.layout.item_hotdepartment, data);
        this.mContext = context;
    }

    protected void convert(BaseViewHolder helper, HotDepartment data)
    {
        View rootView = helper.itemView;
        Glide.with(mContext).load(data.getImg_url()).placeholder(R.mipmap.defaultimage).error(R.mipmap.defaultimage).
          diskCacheStrategy(DiskCacheStrategy.SOURCE).into((ImageView)rootView.findViewById(R.id.hotdepartment_img));
        helper.setText(R.id.hotdepartment_name,null != data.getName() ? data.getName().trim() : "未知");
    }
}
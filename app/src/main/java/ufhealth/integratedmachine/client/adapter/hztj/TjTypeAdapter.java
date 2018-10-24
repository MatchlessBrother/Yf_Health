package ufhealth.integratedmachine.client.adapter.hztj;

import java.util.List;
import android.graphics.Color;
import android.content.Context;
import android.util.TypedValue;
import ufhealth.integratedmachine.client.R;
import android.graphics.drawable.GradientDrawable;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.BaseQuickAdapter;
import ufhealth.integratedmachine.client.bean.hztj.TjDataInfo;

public class TjTypeAdapter extends BaseQuickAdapter<TjDataInfo.AlarmQuantityVosBean,BaseViewHolder>
{
    private Context mContext;

    public TjTypeAdapter(Context context, List<TjDataInfo.AlarmQuantityVosBean> alarmQuantityVosBeans)
    {
        super(R.layout.item_mainhzfragment, alarmQuantityVosBeans);
        mContext = context;
    }

    protected void convert(BaseViewHolder viewHolder, TjDataInfo.AlarmQuantityVosBean alarmQuantityVosBean)
    {
        GradientDrawable backgroundDrawable=new GradientDrawable();
        /***/backgroundDrawable.setShape(GradientDrawable.RECTANGLE);
        if(alarmQuantityVosBean.getAlarmLevelName().contains("报警"))
            viewHolder.setBackgroundRes(R.id.item_mainhzfragment_img,R.mipmap.bj);
        else if(alarmQuantityVosBean.getAlarmLevelName().contains("预警"))
            viewHolder.setBackgroundRes(R.id.item_mainhzfragment_img,R.mipmap.yj);
        else
            viewHolder.setBackgroundRes(R.id.item_mainhzfragment_img,R.mipmap.yj);
        backgroundDrawable.setCornerRadius(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_MM,6,mContext.getResources().getDisplayMetrics()));
        viewHolder.setText(R.id.item_mainhzfragment_number,null != alarmQuantityVosBean.getSensorQuantity() ? alarmQuantityVosBean.getSensorQuantity().trim() : "0");
        viewHolder.setText(R.id.item_mainhzfragment_tv,null != alarmQuantityVosBean.getAlarmLevelName() ? alarmQuantityVosBean.getAlarmLevelName().trim() : "未知类型");
        backgroundDrawable.setColor(Color.parseColor("#" + (null != alarmQuantityVosBean.getAlarmLevelColorCode() ? alarmQuantityVosBean.getAlarmLevelColorCode().trim() : "FF0000")));
        viewHolder.getView(R.id.item_mainhzfragment_bg).setBackgroundDrawable(backgroundDrawable);
    }
}
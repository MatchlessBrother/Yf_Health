package ufhealth.integratedmachine.client.adapter.third;

import java.util.Date;
import java.util.List;
import android.content.Context;
import java.text.SimpleDateFormat;
import android.support.annotation.Nullable;
import ufhealth.integratedmachine.client.R;
import android.support.v4.content.ContextCompat;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.BaseQuickAdapter;
import ufhealth.integratedmachine.client.bean.third.BjHistroyPageInfo;

public class BjHistroyAdapter extends BaseQuickAdapter<BjHistroyPageInfo.BjHistroyInfo, BaseViewHolder>
{
    private Context mContext;
    private SimpleDateFormat mTimeFormat;

    public BjHistroyAdapter(Context context,@Nullable List<BjHistroyPageInfo.BjHistroyInfo> data)
    {
        super(R.layout.item_mainbjhistroyfragment,data);
        mTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        mContext = context;
    }

    protected void convert(BaseViewHolder helper, BjHistroyPageInfo.BjHistroyInfo bjHistroyInfo)
    {
        switch(bjHistroyInfo.getStatusCode())
        {
            case 0:helper.setTextColor(R.id.item_mainbjhistroyfragment_status,ContextCompat.getColor(mContext,R.color.red));break;
            case 1:helper.setTextColor(R.id.item_mainbjhistroyfragment_status,ContextCompat.getColor(mContext,R.color.colorPrimary));break;
        }helper.setText(R.id.item_mainbjhistroyfragment_status,null != bjHistroyInfo.getStatus() && !"".equals(bjHistroyInfo.getStatus().trim()) ? bjHistroyInfo.getStatus().trim() : "未知");
        helper.setText(R.id.item_mainbjhistroyfragment_twoline_right,"类型 : " + (null != bjHistroyInfo.getType() && !"".equals(bjHistroyInfo.getType().trim()) ? bjHistroyInfo.getType().trim() : "未知"));
        helper.setText(R.id.item_mainbjhistroyfragment_oneline_left,"部门 : " + (null != bjHistroyInfo.getPartment() && !"".equals(bjHistroyInfo.getPartment().trim()) ? bjHistroyInfo.getPartment().trim() : "未知"));
        helper.setText(R.id.item_mainbjhistroyfragment_twoline_left,"位置 : " + (null != bjHistroyInfo.getPosition() && !"".equals(bjHistroyInfo.getPosition().trim()) ? bjHistroyInfo.getPosition().trim() : "未知"));
        helper.setText(R.id.item_mainbjhistroyfragment_oneline_right,"设备 : " + (null != bjHistroyInfo.getEquipment() && !"".equals(bjHistroyInfo.getEquipment().trim()) ? bjHistroyInfo.getEquipment().trim() : "未知"));
        helper.setText(R.id.item_mainbjhistroyfragment_threeline_left,"报警值 : " + (null != bjHistroyInfo.getFirstWarning() && !"".equals(bjHistroyInfo.getFirstWarning().trim()) ? bjHistroyInfo.getFirstWarning().trim() : "未知"));
        helper.setText(R.id.item_mainbjhistroyfragment_threeline_right,"实时值 : " + (null != bjHistroyInfo.getSecondWarning() && !"".equals(bjHistroyInfo.getSecondWarning().trim()) ? bjHistroyInfo.getSecondWarning().trim() : "未知"));
        helper.setText(R.id.item_mainbjhistroyfragment_time,"报警时间 : " + (null != bjHistroyInfo.getTime() && !"".equals(bjHistroyInfo.getTime().trim()) ? mTimeFormat.format(new Date(Long.valueOf(bjHistroyInfo.getTime().trim()))) : "未知"));
    }
}
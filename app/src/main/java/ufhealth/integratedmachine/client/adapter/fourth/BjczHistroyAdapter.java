package ufhealth.integratedmachine.client.adapter.fourth;

import java.util.Date;
import java.util.List;
import android.content.Context;
import java.text.SimpleDateFormat;
import android.support.annotation.Nullable;
import ufhealth.integratedmachine.client.R;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.BaseQuickAdapter;
import ufhealth.integratedmachine.client.bean.fourth.BjczHistroyPageInfo;

public class BjczHistroyAdapter extends BaseQuickAdapter<BjczHistroyPageInfo.BjczHistroyInfo,BaseViewHolder>
{
    private Context mContext;
    private SimpleDateFormat mTimeFormat;

    public BjczHistroyAdapter(Context context,@Nullable List<BjczHistroyPageInfo.BjczHistroyInfo> data)
    {
        super(R.layout.item_bjczhistroy,data);
        mTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        mContext = context;
    }

    protected void convert(BaseViewHolder helper,BjczHistroyPageInfo.BjczHistroyInfo bjczHistroyInfo)
    {
        helper.setText(R.id.item_bjczhistroy_twoline_right,"类型 : " + (null != bjczHistroyInfo.getType() && !"".equals(bjczHistroyInfo.getType().trim()) ? bjczHistroyInfo.getType().trim() : "未知"));
        helper.setText(R.id.item_bjczhistroy_oneline_left,"部门 : " + (null != bjczHistroyInfo.getPartment() && !"".equals(bjczHistroyInfo.getPartment().trim()) ? bjczHistroyInfo.getPartment().trim() : "未知"));
        helper.setText(R.id.item_bjczhistroy_twoline_left,"位置 : " + (null != bjczHistroyInfo.getPosition() && !"".equals(bjczHistroyInfo.getPosition().trim()) ? bjczHistroyInfo.getPosition().trim() : "未知"));
        helper.setText(R.id.item_bjczhistroy_oneline_right,"设备 : " + (null != bjczHistroyInfo.getEquipment() && !"".equals(bjczHistroyInfo.getEquipment().trim()) ? bjczHistroyInfo.getEquipment().trim() : "未知"));
        helper.setText(R.id.item_bjczhistroy_threeline_left,"报警值 : " + (null != bjczHistroyInfo.getFirstWarning() && !"".equals(bjczHistroyInfo.getFirstWarning().trim()) ? bjczHistroyInfo.getFirstWarning().trim() : "未知"));
        helper.setText(R.id.item_bjczhistroy_threeline_right,"实时值 : " + (null != bjczHistroyInfo.getSecondWarning() && !"".equals(bjczHistroyInfo.getSecondWarning().trim()) ? bjczHistroyInfo.getSecondWarning().trim() : "未知"));
        helper.setText(R.id.item_bjczhistroy_time,"报警时间 : " + (null != bjczHistroyInfo.getTime() && !"".equals(bjczHistroyInfo.getTime().trim()) ? mTimeFormat.format(new Date(Long.valueOf(bjczHistroyInfo.getTime().trim()))) : "未知"));
    }
}
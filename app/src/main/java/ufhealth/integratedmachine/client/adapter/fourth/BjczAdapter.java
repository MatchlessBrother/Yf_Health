package ufhealth.integratedmachine.client.adapter.fourth;

import java.util.Date;
import java.util.List;
import android.content.Context;
import java.text.SimpleDateFormat;
import android.support.annotation.Nullable;
import ufhealth.integratedmachine.client.R;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.BaseQuickAdapter;
import ufhealth.integratedmachine.client.bean.fourth.BjczPageInfo;

public class BjczAdapter extends BaseQuickAdapter<BjczPageInfo.BjczInfo,BaseViewHolder>
{
    private Context mContext;
    private SimpleDateFormat mTimeFormat;

    public BjczAdapter(Context context,@Nullable List<BjczPageInfo.BjczInfo> data)
    {
        super(R.layout.item_mainbjczfragment,data);
        mTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        mContext = context;
    }

    protected void convert(BaseViewHolder helper,BjczPageInfo.BjczInfo bjczInfo)
    {
        helper.setText(R.id.item_mainbjczfragment_twoline_right,"类型 : " + (null != bjczInfo.getType() && !"".equals(bjczInfo.getType().trim()) ? bjczInfo.getType().trim() : "未知"));
        helper.setText(R.id.item_mainbjczfragment_oneline_left,"部门 : " + (null != bjczInfo.getPartment() && !"".equals(bjczInfo.getPartment().trim()) ? bjczInfo.getPartment().trim() : "未知"));
        helper.setText(R.id.item_mainbjczfragment_twoline_left,"位置 : " + (null != bjczInfo.getPosition() && !"".equals(bjczInfo.getPosition().trim()) ? bjczInfo.getPosition().trim() : "未知"));
        helper.setText(R.id.item_mainbjczfragment_oneline_right,"设备 : " + (null != bjczInfo.getEquipment() && !"".equals(bjczInfo.getEquipment().trim()) ? bjczInfo.getEquipment().trim() : "未知"));
        helper.setText(R.id.item_mainbjczfragment_threeline_left,"报警值 : " + (null != bjczInfo.getFirstWarning() && !"".equals(bjczInfo.getFirstWarning().trim()) ? bjczInfo.getFirstWarning().trim() : "未知"));
        helper.setText(R.id.item_mainbjczfragment_threeline_right,"实时值 : " + (null != bjczInfo.getSecondWarning() && !"".equals(bjczInfo.getSecondWarning().trim()) ? bjczInfo.getSecondWarning().trim() : "未知"));
        helper.setText(R.id.item_mainbjczfragment_time,"报警时间 : " + (null != bjczInfo.getTime() && !"".equals(bjczInfo.getTime().trim()) ? mTimeFormat.format(new Date(Long.valueOf(bjczInfo.getTime().trim()))) : "未知"));
    }
}
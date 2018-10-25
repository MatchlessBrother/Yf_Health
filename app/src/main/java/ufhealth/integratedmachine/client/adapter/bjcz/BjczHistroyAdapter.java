package ufhealth.integratedmachine.client.adapter.bjcz;

import java.util.Date;
import java.util.List;
import android.content.Context;
import java.text.SimpleDateFormat;
import android.support.annotation.Nullable;
import ufhealth.integratedmachine.client.R;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.BaseQuickAdapter;
import ufhealth.integratedmachine.client.bean.bjcz.BjczHistroyPageInfo;

public class BjczHistroyAdapter extends BaseQuickAdapter<BjczHistroyPageInfo.ContentBean,BaseViewHolder>
{
    private Context mContext;
    private SimpleDateFormat mTimeFormat;

    public BjczHistroyAdapter(Context context,@Nullable List<BjczHistroyPageInfo.ContentBean> data)
    {
        super(R.layout.item_bjczhistroy,data);
        mTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        mContext = context;
    }

    protected void convert(BaseViewHolder helper,BjczHistroyPageInfo.ContentBean bjczHistroyInfo)
    {
        helper.setText(R.id.item_bjczhistroy_fourline_right,bjczHistroyInfo.getAlarmNumber() + "");
        helper.setText(R.id.item_bjczhistroy_fourline_left,bjczHistroyInfo.getAlarmValue() + bjczHistroyInfo.getUnit().trim());
        helper.setText(R.id.item_bjczhistroy_twoline_right,null != bjczHistroyInfo.getAddress() && !"".equals(bjczHistroyInfo.getAddress().trim()) ? bjczHistroyInfo.getAddress().trim() : "");
        helper.setText(R.id.item_bjczhistroy_twoline_left,null != bjczHistroyInfo.getSensorName() && !"".equals(bjczHistroyInfo.getSensorName().trim()) ? bjczHistroyInfo.getSensorName().trim() : "");
        helper.setText(R.id.item_bjczhistroy_oneline_left,null != bjczHistroyInfo.getDepartmentName() && !"".equals(bjczHistroyInfo.getDepartmentName().trim()) ? bjczHistroyInfo.getDepartmentName().trim() : "");
        helper.setText(R.id.item_bjczhistroy_oneline_right,null != bjczHistroyInfo.getDeviceAreaName() && !"".equals(bjczHistroyInfo.getDeviceAreaName().trim()) ? bjczHistroyInfo.getDeviceAreaName().trim() : "");
        helper.setText(R.id.item_bjczhistroy_threeline_left,null != bjczHistroyInfo.getParentCategoryName() && !"".equals(bjczHistroyInfo.getParentCategoryName().trim()) ? bjczHistroyInfo.getParentCategoryName().trim() : "");
        helper.setText(R.id.item_bjczhistroy_threeline_right,null != bjczHistroyInfo.getAlarmSettingDescription() && !"".equals(bjczHistroyInfo.getAlarmSettingDescription().trim()) ? bjczHistroyInfo.getAlarmSettingDescription().trim() : "");
        helper.setText(R.id.item_bjczhistroy_time,"报警时间 : " +  mTimeFormat.format(new Date(Long.valueOf(null != bjczHistroyInfo.getAlarmStartTime() && !"".equals(bjczHistroyInfo.getAlarmStartTime().trim()) ? bjczHistroyInfo.getAlarmStartTime().trim() : String.valueOf(new Date().getTime())))));
    }
}
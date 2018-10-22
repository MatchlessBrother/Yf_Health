package ufhealth.integratedmachine.client.adapter.lsbj;

import java.util.Date;
import java.util.List;
import android.content.Context;
import java.text.SimpleDateFormat;
import android.support.annotation.Nullable;
import ufhealth.integratedmachine.client.R;
import android.support.v4.content.ContextCompat;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.BaseQuickAdapter;
import ufhealth.integratedmachine.client.bean.lsbj.BjHistroyPageInfo;

public class BjHistroyAdapter extends BaseQuickAdapter<BjHistroyPageInfo.ContentBean, BaseViewHolder>
{
    private Context mContext;
    private SimpleDateFormat mTimeFormat;

    public BjHistroyAdapter(Context context,@Nullable List<BjHistroyPageInfo.ContentBean> data)
    {
        super(R.layout.item_mainbjhistroyfragment,data);
        mTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        mContext = context;
    }

    protected void convert(BaseViewHolder helper, BjHistroyPageInfo.ContentBean bjHistroyInfo)
    {
        switch(bjHistroyInfo.getHandleStatus())
        {
            case 1:helper.setTextColor(R.id.item_mainbjhistroyfragment_status,ContextCompat.getColor(mContext,R.color.red));break;
            case 2:helper.setTextColor(R.id.item_mainbjhistroyfragment_status,ContextCompat.getColor(mContext,R.color.colorPrimary));break;
        }helper.setText(R.id.item_mainbjhistroyfragment_status,null != bjHistroyInfo.getHandleStatusDescription() && !"".equals(bjHistroyInfo.getHandleStatusDescription().trim()) ? bjHistroyInfo.getHandleStatusDescription().trim() : "");
        helper.setText(R.id.item_mainbjhistroyfragment_twoline_right,null != bjHistroyInfo.getAddress() && !"".equals(bjHistroyInfo.getAddress().trim()) ? bjHistroyInfo.getAddress().trim() : "");
        helper.setText(R.id.item_mainbjhistroyfragment_twoline_left,null != bjHistroyInfo.getSensorName() && !"".equals(bjHistroyInfo.getSensorName().trim()) ? bjHistroyInfo.getSensorName().trim() : "");
        helper.setText(R.id.item_mainbjhistroyfragment_oneline_left,null != bjHistroyInfo.getDepartmentName() && !"".equals(bjHistroyInfo.getDepartmentName().trim()) ? bjHistroyInfo.getDepartmentName().trim() : "");
        helper.setText(R.id.item_mainbjhistroyfragment_oneline_right,null != bjHistroyInfo.getDeviceAreaName() && !"".equals(bjHistroyInfo.getDeviceAreaName().trim()) ? bjHistroyInfo.getDeviceAreaName().trim() : "");
        helper.setText(R.id.item_mainbjhistroyfragment_threeline_right,null != bjHistroyInfo.getAlarmSettingDescription() && !"".equals(bjHistroyInfo.getAlarmSettingDescription().trim()) ? bjHistroyInfo.getAlarmSettingDescription().trim() : "");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(null != bjHistroyInfo.getParentCategoryName() && !"".equals(bjHistroyInfo.getParentCategoryName().trim()) ? bjHistroyInfo.getParentCategoryName().trim() + "-": "");
        stringBuffer.append(null != bjHistroyInfo.getChildCategoryName() && !"".equals(bjHistroyInfo.getChildCategoryName().trim()) ? bjHistroyInfo.getChildCategoryName().trim() : "");
        helper.setText(R.id.item_mainbjhistroyfragment_threeline_left,stringBuffer.toString().trim());
        helper.setText(R.id.item_mainbjhistroyfragment_fourline_right,bjHistroyInfo.getAlarmNumber() + "");
        helper.setText(R.id.item_mainbjhistroyfragment_fourline_left,bjHistroyInfo.getAlarmValue() + bjHistroyInfo.getUnit().trim());
        helper.setText(R.id.item_mainbjhistroyfragment_time,"报警时间 : " +  mTimeFormat.format(new Date(Long.valueOf(null != bjHistroyInfo.getAlarmStartTime() && !"".equals(bjHistroyInfo.getAlarmStartTime().trim()) ? bjHistroyInfo.getAlarmStartTime().trim() : String.valueOf(new Date().getTime())))));
    }
}
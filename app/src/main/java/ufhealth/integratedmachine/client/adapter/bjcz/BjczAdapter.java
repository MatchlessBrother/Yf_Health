package ufhealth.integratedmachine.client.adapter.bjcz;

import java.util.Date;
import java.util.List;
import android.content.Context;
import java.text.SimpleDateFormat;
import android.support.annotation.Nullable;
import ufhealth.integratedmachine.client.R;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.BaseQuickAdapter;
import ufhealth.integratedmachine.client.bean.bjcz.BjczPageInfo;

public class BjczAdapter extends BaseQuickAdapter<BjczPageInfo.ContentBean,BaseViewHolder>
{
    private Context mContext;
    private SimpleDateFormat mTimeFormat;

    public BjczAdapter(Context context,@Nullable List<BjczPageInfo.ContentBean> data)
    {
        super(R.layout.item_mainbjczfragment,data);
        mTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        mContext = context;
    }

    protected void convert(BaseViewHolder helper,BjczPageInfo.ContentBean bjczInfo)
    {
        helper.addOnClickListener(R.id.item_mainbjczfragment_cancel).addOnClickListener(R.id.item_mainbjczfragment_process);
        helper.setText(R.id.item_mainbjczfragment_twoline_right,null != bjczInfo.getAddress() && !"".equals(bjczInfo.getAddress().trim()) ? bjczInfo.getAddress().trim() : "");
        helper.setText(R.id.item_mainbjczfragment_twoline_left,null != bjczInfo.getSensorName() && !"".equals(bjczInfo.getSensorName().trim()) ? bjczInfo.getSensorName().trim() : "");
        helper.setText(R.id.item_mainbjczfragment_oneline_left,null != bjczInfo.getDepartmentName() && !"".equals(bjczInfo.getDepartmentName().trim()) ? bjczInfo.getDepartmentName().trim() : "");
        helper.setText(R.id.item_mainbjczfragment_oneline_right,null != bjczInfo.getDeviceAreaName() && !"".equals(bjczInfo.getDeviceAreaName().trim()) ? bjczInfo.getDeviceAreaName().trim() : "");
        helper.setText(R.id.item_mainbjczfragment_threeline_right,null != bjczInfo.getAlarmSettingDescription() && !"".equals(bjczInfo.getAlarmSettingDescription().trim()) ? bjczInfo.getAlarmSettingDescription().trim() : "");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(null != bjczInfo.getParentCategoryName() && !"".equals(bjczInfo.getParentCategoryName().trim()) ? bjczInfo.getParentCategoryName().trim() + "-": "");
        stringBuffer.append(null != bjczInfo.getChildCategoryName() && !"".equals(bjczInfo.getChildCategoryName().trim()) ? bjczInfo.getChildCategoryName().trim() : "");
        helper.setText(R.id.item_mainbjczfragment_threeline_left,stringBuffer.toString().trim());
        helper.setText(R.id.item_mainbjczfragment_fourline_right,bjczInfo.getAlarmNumber() + "");
        helper.setText(R.id.item_mainbjczfragment_fourline_left,bjczInfo.getAlarmValue() + bjczInfo.getUnit().trim());
        helper.setText(R.id.item_mainbjczfragment_time,"报警时间 : " +  mTimeFormat.format(new Date(Long.valueOf(null != bjczInfo.getAlarmStartTime() && !"".equals(bjczInfo.getAlarmStartTime().trim()) ? bjczInfo.getAlarmStartTime().trim() : String.valueOf(new Date().getTime())))));
    }
}
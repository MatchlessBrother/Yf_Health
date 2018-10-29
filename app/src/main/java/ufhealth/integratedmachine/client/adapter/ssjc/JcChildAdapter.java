package ufhealth.integratedmachine.client.adapter.ssjc;

import java.util.List;
import android.view.View;
import android.text.Spanned;
import android.content.Intent;
import android.graphics.Color;
import android.content.Context;
import android.util.TypedValue;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.text.SpannableString;
import ufhealth.integratedmachine.client.R;
import android.text.style.ForegroundColorSpan;
import android.support.v4.content.ContextCompat;
import android.graphics.drawable.GradientDrawable;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.BaseQuickAdapter;
import ufhealth.integratedmachine.client.bean.ssjc.JcDataInfo;
import ufhealth.integratedmachine.client.ui.ssjc.activity.view.SsjcDetailAct;

public class JcChildAdapter extends BaseQuickAdapter<JcDataInfo.SensorsBean,BaseViewHolder>
{
    private String mBgColor;
    private Context mContext;
    public JcChildAdapter(Context context,String bgColor, List<JcDataInfo.SensorsBean> sensorsBeans)
    {
        super(R.layout.item_mainjcfrag_child, sensorsBeans);
        mBgColor = bgColor;
        mContext = context;
    }

    protected void convert(BaseViewHolder helper,final JcDataInfo.SensorsBean sensorsBean)
    {
        if(null != sensorsBean.getDataSyncStatusName() && sensorsBean.getDataSyncStatusName().trim().contains("异常"))
            helper.setTextColor(R.id.item_mainjcfrag_sjtx, ContextCompat.getColor(mContext,R.color.red));
        else
            helper.setTextColor(R.id.item_mainjcfrag_sjtx,ContextCompat.getColor(mContext,R.color.default_font_black));
        StringBuffer stringBuffer  = new StringBuffer();
        stringBuffer.append(null != sensorsBean.getRealtimeData() ? sensorsBean.getRealtimeData().trim():"0");
        stringBuffer.append(null != sensorsBean.getUnit()         ?        sensorsBean.getUnit().trim() : "");
        helper.setText(R.id.item_mainjcfrag_ssz,"实时值 : " + stringBuffer.toString().trim());
        helper.setText(R.id.item_mainjcfrag_wh,"位号 : " + (null != sensorsBean.getName() ? sensorsBean.getName().trim() :""));
        helper.setText(R.id.item_mainjcfrag_dz,"位置 : " + (null != sensorsBean.getAddress() ? sensorsBean.getAddress().trim() :""));
        helper.setText(R.id.item_mainjcfrag_qy,"区域 : " + (null != sensorsBean.getDeviceAreaName() ? sensorsBean.getDeviceAreaName().trim() :""));
        helper.setText(R.id.item_mainjcfrag_lsgj,"历史报警 : " + (null != sensorsBean.getAlarmTotalNumber() ? sensorsBean.getAlarmTotalNumber().trim() :"0"));
        if(null != sensorsBean.getDataSyncStatus() && "2".equals(sensorsBean.getDataSyncStatus().trim()))
        {
            String sjtx = "数据通讯 : "+(null != sensorsBean.getDataSyncStatusName() ? sensorsBean.getDataSyncStatusName().trim() :"");
            SpannableString spannableString = new SpannableString(sjtx);
            ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#FF0000"));
            spannableString.setSpan(colorSpan,sjtx.indexOf(":"),spannableString.length(),Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
            ((TextView)(helper.itemView.findViewById(R.id.item_mainjcfrag_sjtx))).setText(spannableString);
        }
        else helper.setText(R.id.item_mainjcfrag_sjtx,"数据通讯 : " + (null != sensorsBean.getDataSyncStatusName() ? sensorsBean.getDataSyncStatusName().trim() :""));
        helper.setText(R.id.item_mainjcfrag_jczl,"检查种类 : " + (null != sensorsBean.getCategoryParentName() ? sensorsBean.getCategoryParentName().trim() :""));
        for(int index = 0;index < sensorsBean.getSettings().size();index++)
        {
            TextView textView = new TextView(mContext);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_MM,4,mContext.getResources().getDisplayMetrics()),0,0);
            textView.setTextColor(mContext.getResources().getColor(R.color.default_font_black));
            textView.setLayoutParams(layoutParams);
            textView.setSingleLine(true);
            textView.setTextSize(23);
            StringBuffer buffer = new StringBuffer();
            buffer.append(null != sensorsBean.getSettings().get(index).getLevelName() ? sensorsBean.getSettings().get(index).getLevelName().trim() + " : " : "");
            buffer.append(null != sensorsBean.getSettings().get(index).getRuleDescription() ? sensorsBean.getSettings().get(index).getRuleDescription().trim() + " : " : "");
            textView.setText(buffer.toString());
            ((LinearLayout)helper.getView(R.id.item_mainjcfrag_topbg)).addView(textView);
        }

        GradientDrawable topBackgroundDrawable = new GradientDrawable();
        /***/topBackgroundDrawable.setShape(GradientDrawable.RECTANGLE);
        topBackgroundDrawable.setCornerRadii(new float[]{12,12,12,12,0,0,0,0});
        topBackgroundDrawable.setColor(Color.parseColor("#33" + (null != mBgColor ? mBgColor.trim() : "FF0000")));
        topBackgroundDrawable.setStroke((int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_MM,1,mContext.getResources().getDisplayMetrics()),Color.parseColor("#FF" + (null != mBgColor ? mBgColor.trim() : "FF0000")));
        helper.getView(R.id.item_mainjcfrag_topbg).setBackgroundDrawable(topBackgroundDrawable);
        /**************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************/
        GradientDrawable bottomBackgroundDrawable = new GradientDrawable();
        /***/bottomBackgroundDrawable.setShape(GradientDrawable.RECTANGLE);
        bottomBackgroundDrawable.setCornerRadii(new float[]{0,0,0,0,12,12,12,12});
        bottomBackgroundDrawable.setColor(Color.parseColor("#FF" + (null != mBgColor ? mBgColor.trim() : "FF0000")));
        helper.getView(R.id.item_mainjcfrag_bottombg).setBackgroundDrawable(bottomBackgroundDrawable);
        helper.itemView.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(mContext,SsjcDetailAct.class);
                intent.putExtra("alarmid",String.valueOf(sensorsBean.getId()));
                mContext.startActivity(intent);
            }
        });
    }
}
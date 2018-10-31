package ufhealth.integratedmachine.client.adapter.ssjc;

import java.util.List;
import android.graphics.Color;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.TextView;
import android.widget.LinearLayout;
import ufhealth.integratedmachine.client.R;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.BaseQuickAdapter;
import ufhealth.integratedmachine.client.bean.ssjc.JcDataAdapterInfo;

public class JcAdapter extends BaseQuickAdapter<JcDataAdapterInfo,BaseViewHolder>
{
    private Context mContext;
    public JcAdapter(Context context,List<JcDataAdapterInfo> sensorsBeans)
    {
        super(R.layout.item_mainjcfrag,sensorsBeans);
        mContext = context;
    }

    protected void convert(BaseViewHolder helper,final JcDataAdapterInfo sensorsBean)
    {
        if(sensorsBean.isDivideLine())
        {
            helper.setGone(R.id.item_mainjcfrag_topbottombg,false);
            helper.getView(R.id.item_mainjcfrag_allbg).setBackgroundResource(R.color.default_divide_line);
        }
        else
        {
            helper.setGone(R.id.item_mainjcfrag_topbottombg,true);
            helper.getView(R.id.item_mainjcfrag_allbg).setBackgroundResource(R.color.white);
            helper.setText(R.id.item_mainjcfrag_wh,sensorsBean.getWh().trim());
            helper.setText(R.id.item_mainjcfrag_qy,sensorsBean.getQy().trim());
            helper.setText(R.id.item_mainjcfrag_dz,sensorsBean.getWz().trim());
            helper.setText(R.id.item_mainjcfrag_lsgj,sensorsBean.getLsbj().trim());
            helper.setText(R.id.item_mainjcfrag_sjtx,sensorsBean.getSjtx().trim());
            ((TextView)helper.getView(R.id.item_mainjcfrag_sjtx)).setTextColor(Color.parseColor(sensorsBean.getSjtxColorCode().trim()));
            helper.setText(R.id.item_mainjcfrag_jczl,sensorsBean.getJczl().trim());
            ((LinearLayout)helper.itemView.findViewById(R.id.item_mainjcfrag_expand)).removeAllViews();
            for(int index = 0;index < sensorsBean.getLevelNameRuleDescriptions().size();index++)
            {
                if(null != sensorsBean.getLevelNameRuleDescriptions().get(index) && null != sensorsBean.getLevelNameRuleDescriptions().get(index).getParent())
                    ((ViewGroup)sensorsBean.getLevelNameRuleDescriptions().get(index).getParent()).removeAllViews();
                ((LinearLayout)helper.itemView.findViewById(R.id.item_mainjcfrag_expand)).addView(sensorsBean.getLevelNameRuleDescriptions().get(index));
            }
            helper.getView(R.id.item_mainjcfrag_topbg).setBackgroundDrawable(sensorsBean.getTopBackgroundDrawable());
            helper.getView(R.id.item_mainjcfrag_bottombg).setBackgroundDrawable(sensorsBean.getBottomBackgroundDrawable());
            helper.setText(R.id.item_mainjcfrag_ssz,sensorsBean.getSsz().trim());
        }
    }
}
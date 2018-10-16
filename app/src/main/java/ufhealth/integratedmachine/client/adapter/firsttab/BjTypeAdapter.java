package ufhealth.integratedmachine.client.adapter.firsttab;

import java.util.List;
import android.graphics.Color;
import android.content.Context;
import android.util.TypedValue;
import ufhealth.integratedmachine.client.R;
import android.graphics.drawable.GradientDrawable;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.BaseQuickAdapter;
import ufhealth.integratedmachine.client.bean.firsttab.BjTypeInfos;

public class BjTypeAdapter extends BaseQuickAdapter<BjTypeInfos,BaseViewHolder>
{
    private Context mContext;

    public BjTypeAdapter(Context context, List<BjTypeInfos> bjTypeInfosList)
    {
        super(R.layout.item_mainhzfragment,bjTypeInfosList);
        mContext = context;
    }

    protected void convert(BaseViewHolder viewHolder, BjTypeInfos bjTypeInfos)
    {
        switch(bjTypeInfos.getIconType())
        {
            case 0:viewHolder.setBackgroundRes(R.id.item_mainhzfragment_img,R.mipmap.bj);break;
            case 1:viewHolder.setBackgroundRes(R.id.item_mainhzfragment_img,R.mipmap.yj);break;
            default:viewHolder.setBackgroundRes(R.id.item_mainhzfragment_img,R.mipmap.bj);break;
        }
        GradientDrawable backgroundDrawable=new GradientDrawable();
        backgroundDrawable.setShape(GradientDrawable.RECTANGLE);
        viewHolder.setText(R.id.item_mainhzfragment_tv, bjTypeInfos.getNoteStr().trim());
        backgroundDrawable.setColor(Color.parseColor("#" + bjTypeInfos.getBackgroundColor()));
        viewHolder.setText(R.id.item_mainhzfragment_number,String.valueOf(bjTypeInfos.getAppearNumbers()));
        backgroundDrawable.setCornerRadius(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_MM,6,mContext.getResources().getDisplayMetrics()));
        viewHolder.getView(R.id.item_mainhzfragment_bg).setBackgroundDrawable(backgroundDrawable);
    }
}
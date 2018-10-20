package ufhealth.integratedmachine.client.adapter.second;

import java.util.List;
import android.view.View;
import android.content.Context;
import ufhealth.integratedmachine.client.R;
import android.support.v4.content.ContextCompat;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import ufhealth.integratedmachine.client.bean.second.JcChildCondition;
import ufhealth.integratedmachine.client.bean.second.JcParentCondition;

public class JcConditionAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder>
{
    private Context mContext;
    private int mSelectedChildCode = -1;
    private int mSelectedParentCode = -1;
    public static final int TYPE_PARENT = 0;
    public static final int TYPE_CHILD = 1;

    public JcConditionAdapter(Context context, List<MultiItemEntity> data)
    {
        super(data);
        mContext = context;
        mSelectedChildCode = -1;
        mSelectedParentCode = -1;
        addItemType(TYPE_PARENT, R.layout.item_mainjcfragment_condition_parent);
        addItemType(TYPE_CHILD, R.layout.item_mainjcfragment_condition_child);
    }

    protected void convert(final BaseViewHolder helper, MultiItemEntity item)
    {
       switch (helper.getItemViewType())
       {
           case TYPE_PARENT:
           {
               final JcParentCondition parentCondition = (JcParentCondition)item;
               helper.setBackgroundRes(R.id.item_mainjcfragment_condition_parent_all,parentCondition.isSelected() ? R.color.colorPrimary : R.color.white);
               helper.setText(R.id.item_mainjcfragment_condition_parent_tv,null != parentCondition.getConditionName() ? parentCondition.getConditionName().trim() : "");
               helper.setTextColor(R.id.item_mainjcfragment_condition_parent_tv,parentCondition.isSelected() ? ContextCompat.getColor(mContext,R.color.white): ContextCompat.getColor(mContext,R.color.default_font_black));
               helper.setGone(R.id.item_mainjcfragment_condition_parent_imgall,(null != parentCondition.getConditionName() && (parentCondition.getConditionName().trim().contains("所有") || parentCondition.getConditionName().trim().contains("全部")))? false : true);
               helper.setImageResource(R.id.item_mainjcfragment_condition_parent_img,parentCondition.isSelected() ? (parentCondition.isExpanded() ? R.drawable.icon_arrowbottom_white : R.drawable.icon_arrowright_white) : parentCondition.isExpanded() ? R.drawable.icon_arrowbottom_blue : R.drawable.icon_arrowright_blue);
               helper.itemView.findViewById(R.id.item_mainjcfragment_condition_parent_tv).setOnClickListener(new View.OnClickListener()/*******************选择父类*********************/
               {
                   public synchronized void onClick(View v)
                   {
                       initItemFontColor();
                       mSelectedChildCode = -1;
                       parentCondition.setSelected(true);
                       mSelectedParentCode = parentCondition.getConditionCode();
                       notifyDataSetChanged();
                   }
               });
               helper.itemView.findViewById(R.id.item_mainjcfragment_condition_parent_imgall).setOnClickListener(new View.OnClickListener()/******************展开子类******************/
               {
                   public synchronized void onClick(View v)
                   {
                       int position = helper.getAdapterPosition();
                       if (parentCondition.isExpanded())
                           collapse(position);
                       else
                           expand(position);
                       notifyDataSetChanged();
                   }
               });
               break;
           }
           case TYPE_CHILD:
           {
               final JcChildCondition childCondition = (JcChildCondition)item;
               helper.setBackgroundRes(R.id.item_mainjcfragment_condition_child_all,childCondition.isSelected() ? R.color.colorPrimary : R.color.white);
               helper.setText(R.id.item_mainjcfragment_condition_child_tv,null != childCondition.getConditionName() ? childCondition.getConditionName().trim() : "");
               helper.setTextColor(R.id.item_mainjcfragment_condition_child_tv,childCondition.isSelected() ? ContextCompat.getColor(mContext,R.color.white): ContextCompat.getColor(mContext,R.color.default_font_black));
               helper.itemView.setOnClickListener(new View.OnClickListener()
               {
                   public synchronized void onClick(View v)
                   {
                       initItemFontColor();
                       childCondition.setSelected(true);
                       mSelectedChildCode = childCondition.getConditionCode();
                       mSelectedParentCode = ((JcParentCondition)(getData().get(getParentPosition(childCondition)))).getConditionCode();
                       notifyDataSetChanged();
                   }
               });
               break;
           }
       }
    }

    public void initAdapterConfigure(JcParentCondition jcParentCondition)
    {
        initItemFontColor();
        mSelectedChildCode = -1;
        jcParentCondition.setSelected(true);
        mSelectedParentCode = jcParentCondition.getConditionCode();
    }

    public int getmSelectedChildCode()
    {
        return mSelectedChildCode;

    }

    public int getmSelectedParentCode()
    {
        return mSelectedParentCode;

    }

    public boolean isSelectedChildCondition()
    {
        if(mSelectedChildCode == -1)
            return false;
        else
            return true;
    }

    private synchronized void initItemFontColor()
    {
        for(int index = 0;index < getData().size();index++)
        {
            if(getData().get(index) instanceof JcParentCondition)
            {
                JcParentCondition jcParentCondition = (JcParentCondition)getData().get(index);
                jcParentCondition.setSelected(false);
                List<JcChildCondition> jcChildConditions = jcParentCondition.getSubItems();
                if(null != jcChildConditions && jcChildConditions.size() > 0)
                {
                    for(int position = 0;position < jcChildConditions.size();position++)
                    {
                        JcChildCondition jcChildConditions1 = jcChildConditions.get(position);
                        jcChildConditions1.setSelected(false);
                    }
                }
            }
            else if(getData().get(index) instanceof JcChildCondition)
            {
                JcChildCondition jcChildCondition = (JcChildCondition)getData().get(index);
                jcChildCondition.setSelected(false);
            }
        }
    }
}
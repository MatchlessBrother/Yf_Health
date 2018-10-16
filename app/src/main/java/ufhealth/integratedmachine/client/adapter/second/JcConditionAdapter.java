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

public abstract class JcConditionAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder>
{
    private Context mContext;
    private int mSelectedChildCode = -1;
    private int mSelectedParentCode = -1;
    private int mLastSelectedOfIndex= -1;
    public static final int TYPE_PARENT = 0;
    public static final int TYPE_CHILD = 1;

    public JcConditionAdapter(Context context, List<MultiItemEntity> data)
    {
        super(data);
        mContext = context;
        mSelectedChildCode = -1;
        mSelectedParentCode = -1;
        mLastSelectedOfIndex = -1;
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
                helper.setText(R.id.item_mainjcfragment_condition_parent_tv,null != parentCondition.getConditionName() ? parentCondition.getConditionName().trim() : "");
                helper.setImageResource(R.id.item_mainjcfragment_condition_parent_img,parentCondition.isExpanded() ? R.drawable.icon_arrowbottom_blue : R.drawable.icon_arrowright_blue);
                helper.setTextColor(R.id.item_mainjcfragment_condition_parent_tv,parentCondition.isSelected() ? ContextCompat.getColor(mContext,R.color.colorPrimary) :ContextCompat.getColor(mContext,R.color.default_font_black));
                helper.setGone(R.id.item_mainjcfragment_condition_parent_img,(null != parentCondition.getConditionName() && (parentCondition.getConditionName().trim().contains("所有") || parentCondition.getConditionName().trim().contains("全部")))? false : true);
                helper.itemView.findViewById(R.id.item_mainjcfragment_condition_parent_tv).setOnClickListener(new View.OnClickListener()/*******************选择父类*********************/
                {
                    public void onClick(View v)
                    {
                        initItemFontColor(helper);
                        mSelectedChildCode = -1;
                        selectedCondtionForComplete();
                        parentCondition.setSelected(true);
                        mLastSelectedOfIndex = helper.getAdapterPosition();
                        mSelectedParentCode = parentCondition.getConditionCode();
                    }
                });
                helper.itemView.findViewById(R.id.item_mainjcfragment_condition_parent_imgall).setOnClickListener(new View.OnClickListener()/******************展开子类******************/
                {
                    public void onClick(View v)
                    {
                        int position = helper.getAdapterPosition();
                        if (parentCondition.isExpanded())
                        {
                            collapse(position);
                        }
                        else
                        {
                            expand(position);
                        }
                    }
                });
                break;
            }
            case TYPE_CHILD:
            {
                final JcChildCondition childCondition = (JcChildCondition)item;
                helper.setText(R.id.item_mainjcfragment_condition_child_tv,null != childCondition.getConditionName() ? childCondition.getConditionName().trim() : "");
                helper.setTextColor(R.id.item_mainjcfragment_condition_child_tv,childCondition.isSelected() ? ContextCompat.getColor(mContext,R.color.colorPrimary) :ContextCompat.getColor(mContext,R.color.default_font_black));
                helper.itemView.setOnClickListener(new View.OnClickListener()
                {
                    public void onClick(View v)
                    {
                        initItemFontColor(helper);
                        selectedCondtionForComplete();
                        childCondition.setSelected(true);
                        mLastSelectedOfIndex = helper.getAdapterPosition();
                        mSelectedChildCode = childCondition.getConditionCode();
                        mSelectedParentCode = ((JcParentCondition)(getData().get(getParentPosition(childCondition)))).getConditionCode();
                    }
                });
                break;
            }
        }
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

    public abstract void selectedCondtionForComplete();

    private void initItemFontColor(BaseViewHolder helper)
    {
        if(mLastSelectedOfIndex != -1)
        {
            if(getData().get(mLastSelectedOfIndex) instanceof JcParentCondition)
            {
                ((JcParentCondition)getData().get(mLastSelectedOfIndex)).setSelected(false);
            }
            else if(getData().get(mLastSelectedOfIndex) instanceof JcChildCondition)
            {
                ((JcChildCondition)getData().get(mLastSelectedOfIndex)).setSelected(false);
            }
        }
    }
}
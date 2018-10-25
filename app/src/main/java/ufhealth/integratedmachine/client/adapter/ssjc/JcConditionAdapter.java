package ufhealth.integratedmachine.client.adapter.ssjc;

import java.util.List;
import android.view.View;
import android.content.Context;
import ufhealth.integratedmachine.client.R;
import android.support.v4.content.ContextCompat;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import ufhealth.integratedmachine.client.bean.ssjc.JcCondition;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;

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
               final JcCondition.DepartmentDeviceVosBean departmentDeviceVosBean = (JcCondition.DepartmentDeviceVosBean)item;
               helper.setBackgroundRes(R.id.item_mainjcfragment_condition_parent_all,departmentDeviceVosBean.isSelected() ? R.color.colorPrimary : R.color.white);
               helper.setText(R.id.item_mainjcfragment_condition_parent_tv,null != departmentDeviceVosBean.getDepartmentName() ? departmentDeviceVosBean.getDepartmentName().trim() : "");
               helper.setTextColor(R.id.item_mainjcfragment_condition_parent_tv,departmentDeviceVosBean.isSelected() ? ContextCompat.getColor(mContext,R.color.white): ContextCompat.getColor(mContext,R.color.default_font_black));
               helper.setGone(R.id.item_mainjcfragment_condition_parent_imgall,(null != departmentDeviceVosBean.getDepartmentName() && (departmentDeviceVosBean.getDepartmentName().trim().contains("所有") || departmentDeviceVosBean.getDepartmentName().trim().contains("全部")))? false : true);
               helper.setImageResource(R.id.item_mainjcfragment_condition_parent_img,departmentDeviceVosBean.isSelected() ? (departmentDeviceVosBean.isExpanded() ? R.drawable.icon_arrowbottom_white : R.drawable.icon_arrowright_white) : departmentDeviceVosBean.isExpanded() ? R.drawable.icon_arrowbottom_blue : R.drawable.icon_arrowright_blue);
               helper.itemView.findViewById(R.id.item_mainjcfragment_condition_parent_tv).setOnClickListener(new View.OnClickListener()/*******************选择父类*********************/
               {
                   public synchronized void onClick(View v)
                   {
                       initItemFontColor();
                       mSelectedChildCode = -1;
                       departmentDeviceVosBean.setSelected(true);
                       mSelectedParentCode = Integer.valueOf(null != departmentDeviceVosBean.getDepartmentId() ? departmentDeviceVosBean.getDepartmentId().trim() : "-1");
                       notifyDataSetChanged();
                   }
               });
               helper.itemView.findViewById(R.id.item_mainjcfragment_condition_parent_imgall).setOnClickListener(new View.OnClickListener()/******************展开子类******************/
               {
                   public synchronized void onClick(View v)
                   {
                       int position = helper.getAdapterPosition();
                       if (departmentDeviceVosBean.isExpanded())
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
               final JcCondition.DepartmentDeviceVosBean.DeviceAreaListBean deviceAreaListBean = (JcCondition.DepartmentDeviceVosBean.DeviceAreaListBean)item;
               helper.setBackgroundRes(R.id.item_mainjcfragment_condition_child_all,deviceAreaListBean.isSelected() ? R.color.colorPrimary : R.color.white);
               helper.setText(R.id.item_mainjcfragment_condition_child_tv,null != deviceAreaListBean.getName() ? deviceAreaListBean.getName().trim() : "");
               helper.setTextColor(R.id.item_mainjcfragment_condition_child_tv,deviceAreaListBean.isSelected() ? ContextCompat.getColor(mContext,R.color.white): ContextCompat.getColor(mContext,R.color.default_font_black));
               helper.itemView.setOnClickListener(new View.OnClickListener()
               {
                   public synchronized void onClick(View v)
                   {
                       initItemFontColor();
                       deviceAreaListBean.setSelected(true);
                       mSelectedChildCode = Integer.valueOf(deviceAreaListBean.getId());
                       mSelectedParentCode = Integer.valueOf(((JcCondition.DepartmentDeviceVosBean)(getData().get(getParentPosition(deviceAreaListBean)))).getDepartmentId());
                       notifyDataSetChanged();
                   }
               });
               break;
           }
       }
    }

    public void initAdapterConfigure(JcCondition.DepartmentDeviceVosBean departmentDeviceVosBean)
    {
        if(null != departmentDeviceVosBean)
        {
            initItemFontColor();
            mSelectedChildCode = -1;
            departmentDeviceVosBean.setSelected(true);
            mSelectedParentCode = Integer.valueOf(null != departmentDeviceVosBean.getDepartmentId() ? departmentDeviceVosBean.getDepartmentId().trim() : "-1");
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

    private synchronized void initItemFontColor()
    {
        for(int index = 0;index < getData().size();index++)
        {
            if(getData().get(index) instanceof JcCondition.DepartmentDeviceVosBean)
            {
                JcCondition.DepartmentDeviceVosBean departmentDeviceVosBean = (JcCondition.DepartmentDeviceVosBean)getData().get(index);
                departmentDeviceVosBean.setSelected(false);
                List<JcCondition.DepartmentDeviceVosBean.DeviceAreaListBean> deviceAreaListBeans = departmentDeviceVosBean.getSubItems();
                if(null != deviceAreaListBeans && deviceAreaListBeans.size() > 0)
                {
                    for(int position = 0;position < deviceAreaListBeans.size();position++)
                    {
                        JcCondition.DepartmentDeviceVosBean.DeviceAreaListBean deviceAreaListBean = deviceAreaListBeans.get(position);
                        deviceAreaListBean.setSelected(false);
                    }
                }
            }
            else if(getData().get(index) instanceof JcCondition.DepartmentDeviceVosBean.DeviceAreaListBean)
            {
                JcCondition.DepartmentDeviceVosBean.DeviceAreaListBean deviceAreaListBean = (JcCondition.DepartmentDeviceVosBean.DeviceAreaListBean)getData().get(index);
                deviceAreaListBean.setSelected(false);
            }
        }
    }
}
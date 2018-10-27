package ufhealth.integratedmachine.client.adapter.hztj;

import java.util.List;
import android.view.View;
import android.content.Context;
import ufhealth.integratedmachine.client.R;
import android.support.v4.content.ContextCompat;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import ufhealth.integratedmachine.client.bean.hztj.TjCondition;

public class TjConditionAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder>
{
    private Context mContext;
    private int mSelectedChildCode = -1;
    private int mSelectedParentCode = -1;
    public static final int TYPE_PARENT = 0;
    public static final int TYPE_CHILD = 1;

    public TjConditionAdapter(Context context, List<MultiItemEntity> data)
    {
        super(data);
        mContext = context;
        mSelectedChildCode = -1;
        mSelectedParentCode = -1;
        addItemType(TYPE_PARENT, R.layout.item_maintjfragment_condition_parent);
        addItemType(TYPE_CHILD, R.layout.item_maintjfragment_condition_child);
    }

    protected void convert(final BaseViewHolder helper, MultiItemEntity item)
    {
        switch (helper.getItemViewType())
        {
            case TYPE_PARENT:
            {
                final TjCondition.DepartmentDeviceVosBean departmentBean = (TjCondition.DepartmentDeviceVosBean)item;
                helper.setBackgroundRes(R.id.item_maintjfragment_condition_parent_all,departmentBean.isSelected() ? R.color.colorPrimary : R.color.white);
                helper.setText(R.id.item_maintjfragment_condition_parent_tv,null != departmentBean.getDepartmentName() ? departmentBean.getDepartmentName().trim() : "");
                helper.setTextColor(R.id.item_maintjfragment_condition_parent_tv,departmentBean.isSelected() ? ContextCompat.getColor(mContext,R.color.white): ContextCompat.getColor(mContext,R.color.colorPrimary));
                helper.setGone(R.id.item_maintjfragment_condition_parent_imgall,(null != departmentBean.getDepartmentName() && (departmentBean.getDepartmentName().trim().contains("所有") || departmentBean.getDepartmentName().trim().contains("全部")))? false : true);
                helper.setImageResource(R.id.item_maintjfragment_condition_parent_img,departmentBean.isSelected() ? (departmentBean.isExpanded() ? R.drawable.icon_arrowbottom_white : R.drawable.icon_arrowright_white) : departmentBean.isExpanded() ? R.drawable.icon_arrowbottom_blue : R.drawable.icon_arrowright_blue);
                helper.itemView.findViewById(R.id.item_maintjfragment_condition_parent_tv).setOnClickListener(new View.OnClickListener()/*******************选择父类*********************/
                {
                    public synchronized void onClick(View v)
                    {
                        initItemFontColor();
                        mSelectedChildCode = -1;
                        departmentBean.setSelected(true);
                        mSelectedParentCode = Integer.valueOf(null != departmentBean.getDepartmentId() ? departmentBean.getDepartmentId().trim() : "-1");
                        notifyDataSetChanged();
                    }
                });
                helper.itemView.findViewById(R.id.item_maintjfragment_condition_parent_imgall).setOnClickListener(new View.OnClickListener()/******************展开子类******************/
                {
                    public synchronized void onClick(View v)
                    {
                        int position = helper.getAdapterPosition();
                        if (departmentBean.isExpanded())
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
                final TjCondition.DepartmentDeviceVosBean.DeviceAreaListBean deviceAreaListBean = (TjCondition.DepartmentDeviceVosBean.DeviceAreaListBean)item;
                helper.setBackgroundRes(R.id.item_maintjfragment_condition_child_all,deviceAreaListBean.isSelected() ? R.color.colorPrimary : R.color.white);
                helper.setText(R.id.item_maintjfragment_condition_child_tv,null != deviceAreaListBean.getName() ? deviceAreaListBean.getName().trim() : "");
                helper.setTextColor(R.id.item_maintjfragment_condition_child_tv,deviceAreaListBean.isSelected() ? ContextCompat.getColor(mContext,R.color.white): ContextCompat.getColor(mContext,R.color.default_font_black));
                helper.itemView.setOnClickListener(new View.OnClickListener()
                {
                    public synchronized void onClick(View v)
                    {
                        initItemFontColor();
                        deviceAreaListBean.setSelected(true);
                        mSelectedChildCode = Integer.valueOf(deviceAreaListBean.getId());
                        mSelectedParentCode = Integer.valueOf(((TjCondition.DepartmentDeviceVosBean)(getData().get(getParentPosition(deviceAreaListBean)))).getDepartmentId());
                        notifyDataSetChanged();
                    }
                });
                break;
            }
        }
    }

    public void initAdapterConfigure(TjCondition.DepartmentDeviceVosBean departmentBean)
    {
        if(null != departmentBean)
        {
            initItemFontColor();
            mSelectedChildCode = -1;
            departmentBean.setSelected(true);
            mSelectedParentCode = Integer.valueOf(null != departmentBean.getDepartmentId() ? departmentBean.getDepartmentId().trim() : "-1");
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
            if(getData().get(index) instanceof TjCondition.DepartmentDeviceVosBean)
            {
                TjCondition.DepartmentDeviceVosBean departmentBean = (TjCondition.DepartmentDeviceVosBean)getData().get(index);
                departmentBean.setSelected(false);
                List<TjCondition.DepartmentDeviceVosBean.DeviceAreaListBean> deviceAreaListBeans = departmentBean.getSubItems();
                if(null != deviceAreaListBeans && deviceAreaListBeans.size() > 0)
                {
                    for(int position = 0;position < deviceAreaListBeans.size();position++)
                    {
                        TjCondition.DepartmentDeviceVosBean.DeviceAreaListBean deviceAreaListBean = deviceAreaListBeans.get(position);
                        deviceAreaListBean.setSelected(false);
                    }
                }
            }
            else if(getData().get(index) instanceof TjCondition.DepartmentDeviceVosBean.DeviceAreaListBean)
            {
                TjCondition.DepartmentDeviceVosBean.DeviceAreaListBean deviceAreaListBean = (TjCondition.DepartmentDeviceVosBean.DeviceAreaListBean)getData().get(index);
                deviceAreaListBean.setSelected(false);
            }
        }
    }
}
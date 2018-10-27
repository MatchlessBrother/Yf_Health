package ufhealth.integratedmachine.client.adapter.ssjc;

import java.util.List;
import android.content.Context;
import ufhealth.integratedmachine.client.R;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.GridLayoutManager;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.BaseQuickAdapter;
import ufhealth.integratedmachine.client.bean.ssjc.JcDataInfo;

public class JcParentAdapter extends BaseQuickAdapter<JcDataInfo,BaseViewHolder>
{
    private Context mContext;

    public JcParentAdapter(Context context, List<JcDataInfo> alarmQuantityVosBeans)
    {
        super(R.layout.item_mainjcfrag_parent, alarmQuantityVosBeans);
        mContext = context;
    }

    protected void convert(BaseViewHolder viewHolder, JcDataInfo jcDataInfo)
    {
        JcChildAdapter jcChildAdapter = new JcChildAdapter(mContext,jcDataInfo.getColorCode(),jcDataInfo.getSensors());
        RecyclerView recyclerView = viewHolder.itemView.findViewById(R.id.item_mainjcfrag_recyclerview);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext,2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(jcChildAdapter);
    }
}
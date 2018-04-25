package ufhealth.integratedmachine.client.adapter.zxzx;

import java.util.List;
import ufhealth.integratedmachine.client.R;
import android.support.annotation.Nullable;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.BaseQuickAdapter;
import ufhealth.integratedmachine.client.bean.zxzx.DoctorInfo;
import ufhealth.integratedmachine.client.bean.zxzx.DoctorInfo.ContentBean;

public class DoctorInfoAdapter extends BaseQuickAdapter<DoctorInfo.ContentBean,BaseViewHolder>
{
    private String mType = "";
    public static final String LJZX = "ljzx";//立即咨询
    public static final String SHOWINFOS = "showinfos";//仅仅显示详情
    public static final String MULTISELECT = "multiselect";//多选情况

    public DoctorInfoAdapter(@Nullable List<ContentBean> datas,String type)
    {
        super(R.layout.item_doctorinfo, datas);
        mType = (null != type ? type.trim() : LJZX);
    }

    protected void convert(BaseViewHolder helper, ContentBean item)
    {
        switch (mType)
        {
            case LJZX:
            {
                 break;
            }
            case SHOWINFOS:
            {
                break;
            }
            case MULTISELECT:
            {
                break;
            }
        }
    }
}
package ufhealth.integratedmachine.client.adapter.zxzx;

import java.util.List;
import android.content.Context;
import com.hedgehog.ratingbar.RatingBar;
import android.support.annotation.Nullable;
import ufhealth.integratedmachine.client.R;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.BaseQuickAdapter;
import ufhealth.integratedmachine.client.bean.zxzx.DoctorAllInfo.PageBean;

public class DoctorInfoRatingAdapter extends BaseQuickAdapter<PageBean.ContentBean,BaseViewHolder>
{
    public DoctorInfoRatingAdapter(Context context,@Nullable List<PageBean.ContentBean> data)
    {
        super(R.layout.item_doctorinfo_rating, data);
    }

    protected void convert(BaseViewHolder helper, PageBean.ContentBean item)
    {
        helper.setText(R.id.doctorinfo_rating_name,null != item.getName() ? item.getName() : "姓名隐藏").
               setText(R.id.doctorinfo_rating_time,null != item.getComment_time() ? item.getComment_time() : "时间隐藏").
               setText(R.id.doctorinfo_rating_type,null != item.getConsult_service() ? item.getConsult_service() : "服务类型隐藏").
               setText(R.id.doctorinfo_rating_content,null != item.getComment() ? item.getComment() : "我觉得********");

        RatingBar zydBar = helper.itemView.findViewById(R.id.doctorinfo_rating_zyd);
        zydBar.setStar(0);
        RatingBar fwtdBar = helper.itemView.findViewById(R.id.doctorinfo_rating_fwtd);
        fwtdBar.setStar(0);
        RatingBar xysdBar = helper.itemView.findViewById(R.id.doctorinfo_rating_xysd);
        xysdBar.setStar(0);

        for(PageBean.ContentBean.ComentSocoreBean comentSocoreBean : item.getComent_socore())
        {
            if(null != comentSocoreBean.getName())
            {
                switch(comentSocoreBean.getName().trim())
                {
                    case "专业度":
                    {
                        zydBar.setStar(comentSocoreBean.getScore() < 5 ? comentSocoreBean.getScore() : 5);
                        break;
                    }
                    case "服务态度":
                    {
                        fwtdBar.setStar(comentSocoreBean.getScore() < 5 ? comentSocoreBean.getScore() : 5);
                        break;
                    }
                    case "响应速度":
                    {
                        xysdBar.setStar(comentSocoreBean.getScore() < 5 ? comentSocoreBean.getScore() : 5);
                        break;
                    }
                }
            }
        }
    }
}
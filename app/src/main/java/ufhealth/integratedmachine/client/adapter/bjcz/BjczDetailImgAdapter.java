package ufhealth.integratedmachine.client.adapter.bjcz;

import java.util.List;
import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import android.support.annotation.Nullable;
import ufhealth.integratedmachine.client.R;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import ufhealth.integratedmachine.client.bean.bjcz.BjczDetailInfo;

public class BjczDetailImgAdapter extends BaseQuickAdapter<BjczDetailInfo.HandleImagesBean,BaseViewHolder>
{
    private String mBasePath;
    private Context mContext;
    public BjczDetailImgAdapter(Context context,@Nullable List<BjczDetailInfo.HandleImagesBean> data)
    {
        super(R.layout.item_bjczdetailimg,data);
        mContext = context;
        mBasePath = "http://git.yunfanwulian.com:20001";
    }

    protected void convert(BaseViewHolder helper, BjczDetailInfo.HandleImagesBean handleImagesBean)
    {
        RequestOptions options = new RequestOptions();
        options.error(R.mipmap.defaultimage);
        options.placeholder(R.mipmap.defaultimage);
        options.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(mContext).load(null != handleImagesBean.getImageUrl() ? mBasePath + handleImagesBean.getImageUrl().trim() : "").apply(options).into((ImageView) helper.getView(R.id.item_bjczdetail_img));
    }
}
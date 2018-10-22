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

public class BjczImgAdapter extends BaseQuickAdapter<String,BaseViewHolder>
{
    private Context mContext;

    public BjczImgAdapter(Context context,@Nullable List<String> data)
    {
        super(R.layout.item_bjczimg,data);
        mContext = context;
    }

    protected void convert(BaseViewHolder helper,String imgPath)
    {
        if("".equals(imgPath))
        {
            helper.setGone(R.id.item_bjczimg_bigimg,false);
            helper.setGone(R.id.item_bjczimg_smallimg,true);
        }
        else
        {
            RequestOptions options = new RequestOptions();
            options.error(R.mipmap.defaultimage);
            options.placeholder(R.mipmap.defaultimage);
            options.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
            helper.setGone(R.id.item_bjczimg_bigimg,true);
            helper.setGone(R.id.item_bjczimg_smallimg,false);
            Glide.with(mContext).load(imgPath).apply(options).into((ImageView) helper.getView(R.id.item_bjczimg_bigimg));
        }
    }
}
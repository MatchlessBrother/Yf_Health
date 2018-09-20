package ufhealth.integratedmachine.client.adapter;

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

public class PicturesAdapter extends BaseQuickAdapter<String,BaseViewHolder>
{
    private Context mContext;

    public PicturesAdapter(Context context,@Nullable List<String> datas)
    {
        super(R.layout.item_pictures,datas);
        mContext = context;
    }

    protected void convert(BaseViewHolder helper, String contentBean)
    {
        RequestOptions options = new RequestOptions();
        options.error(R.mipmap.defaultimage);
        options.placeholder(R.mipmap.defaultimage);
        options.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(mContext).load(null != contentBean ? contentBean.trim() : "").apply(options).into((ImageView) helper.itemView.findViewById(R.id.pictures_img));
    }
}

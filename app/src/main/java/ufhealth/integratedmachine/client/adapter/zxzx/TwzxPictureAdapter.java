package ufhealth.integratedmachine.client.adapter.zxzx;

import java.util.List;
import android.view.View;
import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import android.support.annotation.Nullable;
import ufhealth.integratedmachine.client.R;

import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class TwzxPictureAdapter extends BaseQuickAdapter<String,BaseViewHolder>
{
    private Integer mMaxNum;
    private Context mContext;

    public TwzxPictureAdapter(Context context,Integer maxNum,@Nullable List<String> imgPaths)
    {
        super(R.layout.item_twzxpicture, imgPaths);
        mContext = context;
        mMaxNum = maxNum;
    }

    protected void convert(BaseViewHolder helper, String imagePath)
    {
        if(helper.getAdapterPosition() < mMaxNum)
        {
            View rootView = helper.itemView;
            if(null != imagePath && imagePath.equals(""))
            {
                RequestOptions options = new RequestOptions();
                options.placeholder(R.mipmap.addimgicon);
                options.error(R.mipmap.addimgicon);
                options.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
                Glide.with(mContext).load(R.mipmap.addimgicon).apply(options).into((ImageView) helper.itemView.findViewById(R.id.twzxpicture_img));
            }
            else
            {
                RequestOptions options = new RequestOptions();
                options.placeholder(R.mipmap.defaultimage);
                options.error(R.mipmap.defaultimage);
                options.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
                Glide.with(mContext).load(imagePath).apply(options).into((ImageView) helper.itemView.findViewById(R.id.twzxpicture_img));
            }
        }
    }
}
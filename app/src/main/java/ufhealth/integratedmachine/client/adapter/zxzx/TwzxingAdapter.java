package ufhealth.integratedmachine.client.adapter.zxzx;

import java.util.List;
import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import java.text.SimpleDateFormat;
import android.support.annotation.Nullable;
import ufhealth.integratedmachine.client.R;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.netease.nimlib.sdk.msg.constant.MsgDirectionEnum;
import com.netease.nimlib.sdk.msg.attachment.ImageAttachment;
import ufhealth.integratedmachine.client.widget.CornersTransform;

public class TwzxingAdapter extends BaseQuickAdapter<IMMessage, BaseViewHolder>
{
    private Context mContext;
    public SimpleDateFormat simpleDateFormat;
    public static final int SEND_TEXT_MSG = 1;
    public static final int SEND_PICTURE_MSG = 2;

    public static final int ACCEPT_TEXT_MSG = -1;
    public static final int ACCEPT_PICTURE_MSG = -2;

    public TwzxingAdapter(Context context,@Nullable List<IMMessage> data)
    {
        super(data);
        mContext = context;
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        setMultiTypeDelegate(new MultiTypeDelegate<IMMessage>()
        {
            protected int getItemType(IMMessage imMessage)
            {
                if(imMessage.getDirect() == MsgDirectionEnum.Out)//我发出的消息
                {
                    switch(imMessage.getMsgType())//消息类型区分
                    {
                        case text:return SEND_TEXT_MSG;
                        case image:return SEND_PICTURE_MSG;
                        default:return SEND_TEXT_MSG;
                    }
                }
                else//我接收的消息
                {
                    switch(imMessage.getMsgType())//消息类型区分
                    {
                        case text:return ACCEPT_TEXT_MSG;
                        case image:return ACCEPT_PICTURE_MSG;
                        default:return ACCEPT_TEXT_MSG;
                    }
                }
            }
        });

        getMultiTypeDelegate()
                .registerItemType(SEND_TEXT_MSG, R.layout.item_twzxing_sendtextmsg)
                .registerItemType(SEND_PICTURE_MSG, R.layout.item_twzxing_sendpicturemsg)
                .registerItemType(ACCEPT_TEXT_MSG, R.layout.item_twzxing_accepttextmsg)
                .registerItemType(ACCEPT_PICTURE_MSG, R.layout.item_twzxing_acceptpicturemsg);
    }

    protected void convert(BaseViewHolder helper, IMMessage item)
    {
        switch (helper.getItemViewType())
        {
            case SEND_TEXT_MSG:
            {
                Glide.with(mContext).load("").
                        placeholder(R.mipmap.defaultheadimg).error(R.mipmap.defaultheadimg).
                        into((ImageView) helper.itemView.findViewById(R.id.twzxing_sendtextmsg_img));
                helper.
                        setText(R.id.twzxing_sendtextmsg_text,item.getContent()).
                        setText(R.id.twzxing_sendtextmsg_time,simpleDateFormat.format(item.getTime()));
                break;
            }
            case SEND_PICTURE_MSG:
            {
                helper.setText(R.id.twzxing_sendpicturemsg_time,simpleDateFormat.format(item.getTime()));
                Glide.with(mContext).load("").placeholder(R.mipmap.defaultheadimg).error(R.mipmap.defaultheadimg).
                        into((ImageView) helper.itemView.findViewById(R.id.twzxing_sendpicturemsg_img));

                Glide.with(mContext).load(((ImageAttachment)item.getAttachment()).getPath()).
                        bitmapTransform(new CornersTransform(mContext,12f)).override(300,300).
                        placeholder(R.mipmap.defaultimage).error(R.mipmap.defaultimage).
                        into((ImageView) helper.itemView.findViewById(R.id.twzxing_sendpicturemsg_picture));
                break;
            }
            case ACCEPT_TEXT_MSG:
            {
                Glide.with(mContext).load("").
                        placeholder(R.mipmap.defaultheadimg).error(R.mipmap.defaultheadimg).
                        into((ImageView) helper.itemView.findViewById(R.id.twzxing_accepttextmsg_img));
                helper.
                        setText(R.id.twzxing_accepttextmsg_text,item.getContent()).
                        setText(R.id.twzxing_accepttextmsg_time,simpleDateFormat.format(item.getTime()));
                break;
            }
            case ACCEPT_PICTURE_MSG:
            {
                helper.setText(R.id.twzxing_acceptpicturemsg_time,simpleDateFormat.format(item.getTime()));
                Glide.with(mContext).load("").placeholder(R.mipmap.defaultheadimg).error(R.mipmap.defaultheadimg).
                                        into((ImageView) helper.itemView.findViewById(R.id.twzxing_acceptpicturemsg_img));

                Glide.with(mContext).load(((ImageAttachment)item.getAttachment()).getPath()).
                        bitmapTransform(new CornersTransform(mContext,12f)).override(300,300).
                        placeholder(R.mipmap.defaultimage).error(R.mipmap.defaultimage).
                        into((ImageView) helper.itemView.findViewById(R.id.twzxing_acceptpicturemsg_picture));
                break;
            }
        }
    }
}
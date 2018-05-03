package ufhealth.integratedmachine.client.adapter.zxzx;

import java.util.List;
import android.content.Context;
import ufhealth.integratedmachine.client.R;
import android.support.annotation.Nullable;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.netease.nimlib.sdk.msg.constant.MsgDirectionEnum;

public class TwzxingAdapter extends BaseQuickAdapter<IMMessage, BaseViewHolder>
{
    private Context mContext;
    public static final int SEND_TEXT_MSG = 1;
    public static final int SEND_PICTURE_MSG = 2;

    public static final int ACCEPT_TEXT_MSG = -1;
    public static final int ACCEPT_PICTURE_MSG = -2;

    public TwzxingAdapter(Context context,@Nullable List<IMMessage> data)
    {
        super(data);
        mContext = context;
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
                .registerItemType(ACCEPT_PICTURE_MSG, R.layout.item_twzxing_accepttextmsg)
                .registerItemType(ACCEPT_PICTURE_MSG, R.layout.item_twzxing_acceptpicturemsg);
    }

    protected void convert(BaseViewHolder helper, IMMessage item)
    {
        switch (helper.getItemViewType())
        {
            case SEND_TEXT_MSG:
            {
                break;
            }
            case SEND_PICTURE_MSG:
            {
                break;
            }
            case ACCEPT_TEXT_MSG:
            {
                break;
            }
            case ACCEPT_PICTURE_MSG:
            {
                break;
            }
        }
    }
}
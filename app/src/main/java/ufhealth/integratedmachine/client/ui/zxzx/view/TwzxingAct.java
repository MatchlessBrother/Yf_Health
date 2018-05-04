package ufhealth.integratedmachine.client.ui.zxzx.view;

import java.io.File;
import java.util.List;
import android.view.View;
import java.util.ArrayList;
import java.util.LinkedList;
import android.text.Editable;
import android.text.TextUtils;
import android.graphics.Color;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ImageView;
import android.text.TextWatcher;
import android.widget.LinearLayout;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.NIMClient;
import com.donkingliang.labels.LabelsView;
import ufhealth.integratedmachine.client.R;
import com.netease.nimlib.sdk.msg.MsgService;
import android.support.v7.widget.RecyclerView;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.netease.nimlib.sdk.msg.MessageBuilder;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.netease.nimlib.sdk.msg.MsgServiceObserve;
import android.support.v7.widget.LinearLayoutManager;
import com.netease.nimlib.sdk.msg.constant.MsgTypeEnum;
import com.netease.nimlib.sdk.msg.constant.MsgStatusEnum;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import ufhealth.integratedmachine.client.base.BasePhotoAct;
import com.netease.nimlib.sdk.msg.constant.MsgDirectionEnum;
import com.netease.nimlib.sdk.msg.constant.AttachStatusEnum;
import com.netease.nimlib.sdk.msg.attachment.ImageAttachment;
import ufhealth.integratedmachine.client.bean.zxzx.DoctorAllInfo;
import ufhealth.integratedmachine.client.adapter.zxzx.TwzxingAdapter;
import ufhealth.integratedmachine.client.ui.zxzx.view_v.TwzxingAct_V;
import ufhealth.integratedmachine.client.ui.zxzx.view_v.DoctorInfoAct_V;
import ufhealth.integratedmachine.client.ui.zxzx.presenter.TwzxingPresenter;
import ufhealth.integratedmachine.client.ui.zxzx.presenter.DoctorInfoPresenter;

public class TwzxingAct extends BasePhotoAct implements TwzxingAct_V,DoctorInfoAct_V,View.OnClickListener
{
    private String orderId;
    private String doctorId;
    private String chatObjAccId;


    private ImageView doctorinfoImg;
    private TextView doctorinfoImgStatus;
    private TextView doctorinfoName;
    private TextView doctorinfoPosition;
    private TextView doctorinfoHospitalname;
    private TextView doctorinfoDepartmentname;
    private TextView doctorinfoSpecialize;
    private TextView doctorinfoSource;
    private TextView doctorinfoStartchat;
    private TextView doctorinfoStartnote;
    private LinearLayout doctorinfoZshppj;
    private TextView doctorinfoZsnum;
    private TextView doctorinfoHpnum;
    private TextView doctorinfoPjnum;
    private LabelsView doctorinfoLabels;
    private TextView twzxingRightTop;
    private EditText twzxingRightBottomEt;
    private TextView twzxingRightBottomSend;
    private LinearLayout twzxingRightBottomAll;
    private ImageView twzxingRightBottomExpression;
    private ImageView twzxingRightBottomPicture;


    private TwzxingAdapter twzxingAdapter;
    private RecyclerView twzxingRightBottomRecyclerview;


    private TwzxingPresenter twzxingPresenter;
    private DoctorInfoPresenter doctorInfoPresenter;


    private Observer<IMMessage> statusObserver;
    private Observer<List<IMMessage>> incomingMessageObserver;

    protected int setLayoutResID()
    {
        return R.layout.activity_twzxing;
    }

    private boolean isHasDownloadedPicture(IMMessage message)
    {
        if (message.getAttachStatus() == AttachStatusEnum.transferred && !TextUtils.isEmpty(((ImageAttachment) message.getAttachment()).getPath()))
            return true;
        return false;
    }

    protected void initWidgets(View rootView)
    {
        super.initWidgets(rootView);
        setTitleContent("图文咨询中");
        orderId = getIntent().getStringExtra("orderid").trim();
        doctorId = getIntent().getStringExtra("doctorid").trim();
        chatObjAccId = getIntent().getStringExtra("accid").trim();
        doctorinfoImg = (ImageView) rootView.findViewById(R.id.doctorinfo_img);
        doctorinfoImgStatus = (TextView) rootView.findViewById(R.id.doctorinfo_img_status);
        doctorinfoName = (TextView) rootView.findViewById(R.id.doctorinfo_name);
        doctorinfoPosition = (TextView) rootView.findViewById(R.id.doctorinfo_position);
        doctorinfoHospitalname = (TextView) rootView.findViewById(R.id.doctorinfo_hospitalname);
        doctorinfoDepartmentname = (TextView) rootView.findViewById(R.id.doctorinfo_departmentname);
        doctorinfoSpecialize = (TextView) rootView.findViewById(R.id.doctorinfo_specialize);
        doctorinfoSource = (TextView) rootView.findViewById(R.id.doctorinfo_source);
        doctorinfoStartchat = (TextView) rootView.findViewById(R.id.doctorinfo_startchat);
        doctorinfoStartnote = (TextView) rootView.findViewById(R.id.doctorinfo_startnote);
        doctorinfoZshppj = (LinearLayout) rootView.findViewById(R.id.doctorinfo_zshppj);
        doctorinfoZsnum = (TextView) rootView.findViewById(R.id.doctorinfo_zsnum);
        doctorinfoHpnum = (TextView) rootView.findViewById(R.id.doctorinfo_hpnum);
        doctorinfoPjnum = (TextView) rootView.findViewById(R.id.doctorinfo_pjnum);
        doctorinfoLabels = (LabelsView) rootView.findViewById(R.id.doctorinfo_labels);
        twzxingRightTop = (TextView) rootView.findViewById(R.id.twzxing_right_top);
        twzxingRightBottomAll = (LinearLayout) rootView.findViewById(R.id.twzxing_right_bottom_all);
        twzxingRightBottomEt = (EditText) rootView.findViewById(R.id.twzxing_right_bottom_et);
        twzxingRightBottomSend = (TextView) rootView.findViewById(R.id.twzxing_right_bottom_send);
        twzxingRightBottomExpression = (ImageView) rootView.findViewById(R.id.twzxing_right_bottom_expression);
        twzxingRightBottomPicture = (ImageView) rootView.findViewById(R.id.twzxing_right_bottom_picture);
        twzxingRightBottomRecyclerview = (RecyclerView) rootView.findViewById(R.id.twzxing_right_bottom_recyclerview);
        doctorinfoStartchat.setEnabled(true);
        doctorinfoStartchat.setText("结束咨询");
        doctorinfoStartnote.setVisibility(View.GONE);
        twzxingRightBottomAll.setVisibility(View.GONE);
        doctorinfoStartchat.setVisibility(View.INVISIBLE);
        doctorinfoStartchat.setBackgroundColor(Color.argb(255,255,0,0));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        twzxingRightBottomRecyclerview.setLayoutManager(linearLayoutManager);
        twzxingAdapter = new TwzxingAdapter(this,new ArrayList<IMMessage>());
        twzxingRightBottomRecyclerview.setAdapter(twzxingAdapter);

        twzxingRightBottomEt.addTextChangedListener(new TextWatcher()
        {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            public void afterTextChanged(Editable editable) {}
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                if(twzxingRightBottomEt.getText().toString().length() > 0)
                    twzxingRightBottomSend.setVisibility(View.VISIBLE);
                else
                    twzxingRightBottomSend.setVisibility(View.GONE);
            }
        });

        incomingMessageObserver = new Observer<List<IMMessage>>()
        {
            public void onEvent(List<IMMessage> messages)
            {
                if(null != messages && messages.size() > 0 && null != chatObjAccId && !"".equals(chatObjAccId.trim()))
                {
                    IMMessage message = messages.get(0);
                    if(message.getSessionId().equals(chatObjAccId.trim()))
                    {
                        if(message.getMsgType() == MsgTypeEnum.text)
                        {
                            twzxingAdapter.addData(message);
                            twzxingAdapter.notifyDataSetChanged();
                            twzxingRightBottomRecyclerview.scrollToPosition(twzxingAdapter.getItemCount()-1);
                        }
                        else if(message.getMsgType() == MsgTypeEnum.image)
                        {
                            if(!isHasDownloadedPicture(message))
                            {
                                NIMClient.getService(MsgService.class).downloadAttachment(message, false);
                            }
                        }
                    }
                }
            }
        };
        statusObserver = new Observer<IMMessage>()
        {
            public void onEvent(IMMessage message)
            {
                // 1、根据sessionId判断是否是自己的消息
                // 2、更改内存中消息的状态
                // 3、刷新界面
                if(message.getDirect() == MsgDirectionEnum.In)//接受消息
                {
                    if(null != chatObjAccId && !"".equals(chatObjAccId.trim()) && message.getSessionId().equals(chatObjAccId.trim()))//只接收当前通讯对象所发送的消息
                    {
                        if(message.getMsgType() == MsgTypeEnum.image)
                        {
                            if(message.getAttachStatus() == AttachStatusEnum.transferred)
                            {
                                twzxingAdapter.addData(message);
                                twzxingAdapter.notifyDataSetChanged();
                                twzxingRightBottomRecyclerview.scrollToPosition(twzxingAdapter.getItemCount()-1);
                            }
                        }
                    }
                }
                else if(message.getDirect() == MsgDirectionEnum.Out)//发送消息
                {
                    if(message.getMsgType() == MsgTypeEnum.text)//我发送的文本消息
                    {
                        if(message.getStatus() == MsgStatusEnum.success)//确定文本消息已发送
                        {
                            twzxingAdapter.addData(message);
                            twzxingAdapter.notifyDataSetChanged();
                            twzxingRightBottomRecyclerview.scrollToPosition(twzxingAdapter.getItemCount()-1);
                        }
                    }
                    else if(message.getMsgType() == MsgTypeEnum.image)//我发送的图片消息
                    {
                        if(message.getAttachStatus() == AttachStatusEnum.transferred)//确定图片消息已发送
                        {
                            twzxingAdapter.addData(message);
                            twzxingAdapter.notifyDataSetChanged();
                            twzxingRightBottomRecyclerview.scrollToPosition(twzxingAdapter.getItemCount()-1);
                        }
                    }
                }
            }
        };
        doctorinfoStartchat.setOnClickListener(this);
        twzxingRightBottomSend.setOnClickListener(this);
        twzxingRightBottomPicture.setOnClickListener(this);
        twzxingRightBottomExpression.setOnClickListener(this);
        NIMClient.getService(MsgServiceObserve.class).observeMsgStatus(statusObserver, true);
        NIMClient.getService(MsgServiceObserve.class).observeReceiveMessage(incomingMessageObserver, true);
    }

    protected void initDatas()
    {
        doctorInfoPresenter = new DoctorInfoPresenter();
        doctorInfoPresenter.attachContextAndViewLayer(this,this);
        twzxingPresenter = new TwzxingPresenter();
        twzxingPresenter.attachContextAndViewLayer(this,this);
    }

    protected void initLogic()
    {
        doctorInfoPresenter.initDoctorAllInfo(doctorId);

    }

    public void onClick(View view)
    {
        super.onClick(view);
        switch (view.getId())
        {
            case R.id.doctorinfo_startchat:
            {
                twzxingPresenter.finishBill("TWZX",orderId);
                break;
            }
            case R.id.twzxing_right_bottom_expression:
            {
                break;
            }
            case R.id.twzxing_right_bottom_picture:
            {
                startGallery(false,3);
                break;
            }
            case R.id.twzxing_right_bottom_send:
            {
                SessionTypeEnum sessionType = SessionTypeEnum.P2P;
                IMMessage textMessage = MessageBuilder.createTextMessage(chatObjAccId, sessionType,twzxingRightBottomEt.getText().toString().trim());
                NIMClient.getService(MsgService.class).sendMessage(textMessage, true);
                twzxingRightBottomEt.setText("");
                break;
            }
        }
    }

    protected void onDestroy()
    {
        NIMClient.getService(MsgServiceObserve.class).observeMsgStatus(statusObserver, false);
        NIMClient.getService(MsgServiceObserve.class).observeReceiveMessage(incomingMessageObserver, false);
        doctorInfoPresenter.detachContextAndViewLayout();
        twzxingPresenter.detachContextAndViewLayout();
        super.onDestroy();
    }

    public void finishBillSuccess()
    {
        finish();

    }

    public void finishRefreshDoctorRatingInfo()
    {

    }

    public void finishLoadMoreDoctorRatingInfo()
    {

    }

    @Subscribe
    public void receiveCountDownTime(Long countDownTime)
    {
        super.receiveCountDownTime(countDownTime);

    }

    @Subscribe
    public void receiveCountDownFinish(Boolean isFinish)
    {
        super.receiveCountDownFinish(isFinish);

    }

    public void refreshDoctorRatingInfo(DoctorAllInfo.PageBean pageBean)
    {

    }

    public void loadMoreDoctorRatingInfo(DoctorAllInfo.PageBean pageBean)
    {

    }

    public void setOnNewImgPathListener(LinkedList<String> bitmapPaths)
    {
        for(String imagePath : bitmapPaths)
        {
            SessionTypeEnum sessionType = SessionTypeEnum.P2P;
            File file = new File(imagePath.trim());
            IMMessage message = MessageBuilder.createImageMessage(chatObjAccId, sessionType, file, file.getName());
            NIMClient.getService(MsgService.class).sendMessage(message, true);
        }
    }

    public void setDoctorBaseInfo(DoctorAllInfo.BaseinfoBean baseinfoBean)
    {
        if(null != baseinfoBean)
        {
            useGlideLoadImg(doctorinfoImg,null != baseinfoBean.getAvatar() ? baseinfoBean.getAvatar().trim() : "");
            doctorinfoImgStatus.setText("在线");
            doctorinfoImgStatus.setVisibility(View.GONE);
            doctorinfoName.setText(null != baseinfoBean.getDoctor_name() ? baseinfoBean.getDoctor_name().trim() : "未知");
            doctorinfoPosition.setText(null != baseinfoBean.getJob_name() ? baseinfoBean.getJob_name().trim() : "未知");
            doctorinfoHospitalname.setText("医院：" + (null != baseinfoBean.getHospital_name() ? baseinfoBean.getHospital_name().trim() : "未知"));
            doctorinfoDepartmentname.setText("科室：" + (null != baseinfoBean.getDepartment_name() ? baseinfoBean.getDepartment_name().trim() : "未知"));
            doctorinfoSpecialize.setText("擅长：" + (null != baseinfoBean.getBe_good_at() ? baseinfoBean.getBe_good_at().trim() : "未知" ));
            doctorinfoSource.setText("来源：" + (null != baseinfoBean.getOriginal() ? baseinfoBean.getOriginal().trim() : "未知"));
            doctorinfoZsnum.setText(null != baseinfoBean.getConsult_count() ? baseinfoBean.getConsult_count().trim() : "0");
            doctorinfoHpnum.setText(null != baseinfoBean.getComment_count_per() ? baseinfoBean.getComment_count_per().trim() + "%" : "0%");
            doctorinfoPjnum.setText(null != baseinfoBean.getComment_count() ? baseinfoBean.getComment_count().trim() : "0");
            doctorinfoLabels.setLabels(new ArrayList<String>());
            doctorinfoStartchat.setVisibility(View.VISIBLE);
            twzxingRightBottomAll.setVisibility(View.VISIBLE);
        }
    }
}
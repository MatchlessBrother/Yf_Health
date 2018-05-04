package ufhealth.integratedmachine.client.ui.zxzx.view;

import java.util.Map;
import android.view.View;
import java.util.ArrayList;
import android.graphics.Color;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.netease.nimlib.sdk.Observer;
import com.donkingliang.labels.LabelsView;
import ufhealth.integratedmachine.client.R;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.netease.nimlib.sdk.avchat.AVChatManager;
import com.netease.nimlib.sdk.avchat.AVChatCallback;
import ufhealth.integratedmachine.client.base.BaseAct;
import com.netease.nimlib.sdk.avchat.model.AVChatData;
import com.netease.nimlib.sdk.avchat.constant.AVChatType;
import com.netease.nimlib.sdk.avchat.model.AVChatVideoFrame;
import com.netease.nimlib.sdk.avchat.model.AVChatAudioFrame;
import com.netease.nimlib.sdk.avchat.model.AVChatParameters;
import com.netease.nimlib.sdk.avchat.model.AVChatCommonEvent;
import ufhealth.integratedmachine.client.widget.RippleLayout;
import com.netease.nimlib.sdk.avchat.AVChatStateObserverLite;
import com.netease.nimlib.sdk.avchat.model.AVChatNetworkStats;
import com.netease.nimlib.sdk.avchat.model.AVChatNotifyOption;
import com.netease.nimlib.sdk.avchat.model.AVChatSessionStats;
import com.netease.nimlib.sdk.avchat.constant.AVChatEventType;
import com.netease.nimlib.sdk.avchat.model.AVChatCalleeAckEvent;
import ufhealth.integratedmachine.client.bean.zxzx.DoctorAllInfo;
import ufhealth.integratedmachine.client.ui.zxzx.view_v.YyzxingAct_V;
import ufhealth.integratedmachine.client.ui.zxzx.view_v.DoctorInfoAct_V;
import ufhealth.integratedmachine.client.ui.zxzx.presenter.YyzxingPresenter;
import ufhealth.integratedmachine.client.ui.zxzx.presenter.DoctorInfoPresenter;

public class YyzxingAct extends BaseAct implements YyzxingAct_V,DoctorInfoAct_V,View.OnClickListener
{
    private String orderId;
    private String doctorId;
    private String chatObjAccId;
    private RippleLayout rippleView;
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
    private TextView yyzxingRightTop;
    private RelativeLayout doctorinfoImgall;
    private LinearLayout yyzxingRightBottom;
    private TextView yyzxingRightBottomTime;
    private TextView yyzxingRightBottomLjstatus;

    private YyzxingPresenter yyzxingPresenter;
    private DoctorInfoPresenter doctorInfoPresenter;

    private AVChatData avChatData = null;
    private Observer<AVChatCommonEvent> callHangupObserver = null;
    private Observer<AVChatCalleeAckEvent> callAckObserver = null;
    private AVChatStateObserverLite aVChatStateObserverLite = null;


    protected int setLayoutResID()
    {
        return R.layout.activity_yyzxing;
    }

    protected void initWidgets(View rootView)
    {
        super.initWidgets(rootView);
        setTitleContent("语音咨询中");
        orderId = getIntent().getStringExtra("orderid").trim();
        doctorId = getIntent().getStringExtra("doctorid").trim();
        chatObjAccId = getIntent().getStringExtra("accid").trim();
        doctorinfoImgall = (RelativeLayout) rootView.findViewById(R.id.doctorinfo_imgall);
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
        yyzxingRightTop = (TextView) rootView.findViewById(R.id.yyzxing_right_top);
        yyzxingRightBottom = (LinearLayout) rootView.findViewById(R.id.yyzxing_right_bottom);
        yyzxingRightBottomTime = (TextView) rootView.findViewById(R.id.yyzxing_right_bottom_time);
        yyzxingRightBottomLjstatus = (TextView) rootView.findViewById(R.id.yyzxing_right_bottom_ljstatus);
        rippleView = (RippleLayout)rootView.findViewById(R.id.yyzxing_right_bottom_ripplelayout);

        /**********连接成功再播放动画**************/
        //rippleView.startRippleAnimation();
        /**********连接成功再播放动画**************/

        doctorinfoStartchat.setText("结束咨询");
        doctorinfoStartnote.setVisibility(View.GONE);
        doctorinfoStartchat.setVisibility(View.INVISIBLE);
        doctorinfoStartchat.setBackgroundColor(Color.argb(255,255,0,0));
        doctorinfoStartchat.setOnClickListener(this);
    }

    protected void initDatas()
    {
        doctorInfoPresenter = new DoctorInfoPresenter();
        doctorInfoPresenter.attachContextAndViewLayer(this,this);
        yyzxingPresenter = new YyzxingPresenter();
        yyzxingPresenter.attachContextAndViewLayer(this,this);
    }

    protected void initLogic()
    {
        doctorInfoPresenter.initDoctorAllInfo(getIntent().getStringExtra("doctorid").trim());

        callHangupObserver = new Observer<AVChatCommonEvent>()
        {
            public void onEvent(AVChatCommonEvent hangUpInfo)//结束通话
            {
                AVChatManager.getInstance().disableRtc();//销毁音视频引擎和释放资源
            }
        };

        aVChatStateObserverLite = new AVChatStateObserverLite()
        {
            public void onLeaveChannel() {}

            public void onCallEstablished()
            {
                //回话建立完毕，可以正式开始通讯
            }

            public void onDisconnectServer() {}

            public void onLiveEvent(int event) {}

            public void onUserJoined(String account) {}

            public void onAudioDeviceChanged(int device) {}

            public void onProtocolIncompatible(int status) {}

            public void onDeviceEvent(int code, String desc) {}

            public void onConnectionTypeChanged(int netType) {}

            public void onFirstVideoFrameRendered(String user) {}

            public void onUserLeave(String account, int event) {}

            public void onFirstVideoFrameAvailable(String account) {}

            public void onVideoFpsReported(String account, int fps) {}

            public void onSessionStats(AVChatSessionStats sessionStats) {}

            public boolean onAudioFrameFilter(AVChatAudioFrame frame) {return false;}

            public void onReportSpeaker(Map<String, Integer> speakers, int mixedEnergy) {}

            public void onNetworkQuality(String user, int quality, AVChatNetworkStats stats) {}

            public void onJoinedChannel(int code, String audioFile, String videoFile, int elapsed) {}

            public void onVideoFrameResolutionChanged(String user, int width, int height, int rotate) {}

            public boolean onVideoFrameFilter(AVChatVideoFrame frame, boolean maybeDualInput) {return false;}
        };

        callAckObserver = new Observer<AVChatCalleeAckEvent>()
        {
            public void onEvent(AVChatCalleeAckEvent ackInfo)
            {
                if (ackInfo.getEvent() == AVChatEventType.CALLEE_ACK_BUSY)
                {
                    //对方正在忙
                }
                else if (ackInfo.getEvent() == AVChatEventType.CALLEE_ACK_REJECT)
                {
                    //对方拒绝接听
                }
                else if (ackInfo.getEvent() == AVChatEventType.CALLEE_ACK_AGREE)
                {
                    //对方同意接听
                }
            }
        };

        AVChatManager.getInstance().observeAVChatState(aVChatStateObserverLite, true);
        AVChatManager.getInstance().observeCalleeAckNotification(callAckObserver, true);
        AVChatManager.getInstance().observeHangUpNotification(callHangupObserver, true);
    }

    public void onClick(View view)
    {
        super.onClick(view);
        switch (view.getId())
        {
            case R.id.activity_title_back:
            {
                break;
            }
            case R.id.doctorinfo_startchat:
            {
                break;
            }
        }
    }

    public void onBackPressed()
    {

    }

    protected void onDestroy()
    {
        AVChatManager.getInstance().observeCalleeAckNotification(callAckObserver, false);
        AVChatManager.getInstance().observeHangUpNotification(callHangupObserver, false);
        AVChatManager.getInstance().observeAVChatState(aVChatStateObserverLite, false);
        doctorInfoPresenter.detachContextAndViewLayout();
        yyzxingPresenter.detachContextAndViewLayout();
        super.onDestroy();
    }

    public void finishRefreshDoctorRatingInfo()
    {

    }

    public void finishLoadMoreDoctorRatingInfo()
    {

    }

    @Subscribe
    public void receiveCountDownFinish(Boolean isFinish)
    {
        super.receiveCountDownFinish(isFinish);

    }

    @Subscribe
    public void receiveCountDownTime(Long countDownTime)
    {
        super.receiveCountDownTime(countDownTime);

    }

    /********************************挂断音频******************************/
    public void finishAudioChat(AVChatData avChatData)
    {
        AVChatManager.getInstance().hangUp2(avChatData.getChatId(), new AVChatCallback<Void>()
        {
            public void onSuccess(Void aVoid)
            {
                //成功挂断电话
            }

            public void onFailed(int code)
            {

            }

            public void onException(Throwable exception)
            {

            }
        });
    }

    /********************************拨打音频******************************/
    public void beginAudioChat(String account, final AVChatType callTypeEnum)
    {
        AVChatManager.getInstance().enableRtc();//打开Rtc模块

        AVChatParameters avChatParameters = new AVChatParameters();//设置自定义音频需要的可选参数
        avChatParameters.setRequestKey(AVChatParameters.KEY_AUDIO_EFFECT_NOISE_SUPPRESSOR);
        avChatParameters.setRequestKey(AVChatParameters.KEY_AUDIO_EFFECT_AUTOMATIC_GAIN_CONTROL);
        avChatParameters.setRequestKey(AVChatParameters.KEY_AUDIO_EFFECT_ACOUSTIC_ECHO_CANCELER);
        AVChatManager.getInstance().setParameters(avChatParameters);

        AVChatNotifyOption notifyOption = new AVChatNotifyOption();
        notifyOption.webRTCCompat = false;//是否兼容WebRTC模式
        notifyOption.extendMessage = orderId; //附加字段
        notifyOption.forceKeepCalling = true;//为true则离线持续呼叫

        //发起语音呼叫
        AVChatManager.getInstance().call2(account, callTypeEnum, notifyOption, new AVChatCallback<AVChatData>()
        {
            @Override
            public void onSuccess(AVChatData data)
            {
                avChatData = data; //发起会话成功
            }

            public void onFailed(int code)
            {
              /*  closeRtc();
                closeSessions(-1);*/
            }

            public void onException(Throwable exception)
            {
               /* closeRtc();
                closeSessions(-1);*/
            }
        });
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
            yyzxingRightTop.setText("正在与" + (null != baseinfoBean.getDoctor_name() ? baseinfoBean.getDoctor_name().trim() : "未知") +
                                    "医生咨询，此次通话将在" + getIntent().getIntExtra("time",0) / 60 + "分钟后结束");
            doctorinfoStartchat.setVisibility(View.VISIBLE);
        }
    }

    public void refreshDoctorRatingInfo(DoctorAllInfo.PageBean pageBean)
    {

    }

    public void loadMoreDoctorRatingInfo(DoctorAllInfo.PageBean pageBean)
    {

    }
}
package ufhealth.integratedmachine.client.ui.zxzx.view;

import java.util.Map;
import java.util.Timer;
import android.view.View;
import java.util.TimerTask;
import java.util.ArrayList;
import android.graphics.Color;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.CompoundButton;
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
import com.netease.nimlib.sdk.avchat.constant.AVChatResCode;
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
import com.netease.nimlib.sdk.avchat.constant.AVChatUserQuitType;
import ufhealth.integratedmachine.client.bean.zxzx.DoctorAllInfo;
import ufhealth.integratedmachine.client.ui.zxzx.view_v.YyzxingAct_V;
import ufhealth.integratedmachine.client.ui.zxzx.view_v.DoctorInfoAct_V;
import ufhealth.integratedmachine.client.ui.zxzx.presenter.YyzxingPresenter;
import ufhealth.integratedmachine.client.ui.zxzx.presenter.DoctorInfoPresenter;

public class YyzxingAct extends BaseAct implements YyzxingAct_V,DoctorInfoAct_V,View.OnClickListener
{
    private long syTime;
    private long ksTime;
    private Timer timer;
    private boolean isNoTime;
    private TimerTask timerTask;


    private int rtcStatus;
    private String orderId;
    private String doctorId;
    private String doctorName;
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
    private TextView yyzxingRightTopTime;
    private CheckBox yyzxingRightTopSpeaker;
    private CheckBox yyzxingRightTopMicrophone;
    private LabelsView doctorinfoLabels;
    private RelativeLayout doctorinfoImgall;
    private LinearLayout yyzxingRightBottom;
    private TextView yyzxingRightBottomTime;
    private TextView yyzxingRightBottomLjstatus;

    private YyzxingPresenter yyzxingPresenter;
    private DoctorInfoPresenter doctorInfoPresenter;

    private AVChatData avChatData = null;
    private Boolean toggleSpeakerMode = false;
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
        ksTime = 0;
        syTime = getIntent().getLongExtra("sytime",0);
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
        yyzxingRightTopTime = (TextView) findViewById(R.id.yyzxing_right_top_time);
        yyzxingRightTopSpeaker = (CheckBox) findViewById(R.id.yyzxing_right_top_speaker);
        yyzxingRightTopMicrophone = (CheckBox) findViewById(R.id.yyzxing_right_top_microphone);
        doctorinfoLabels = (LabelsView) rootView.findViewById(R.id.doctorinfo_labels);
        yyzxingRightBottom = (LinearLayout) rootView.findViewById(R.id.yyzxing_right_bottom);
        yyzxingRightBottomTime = (TextView) rootView.findViewById(R.id.yyzxing_right_bottom_time);
        yyzxingRightBottomLjstatus = (TextView) rootView.findViewById(R.id.yyzxing_right_bottom_ljstatus);
        rippleView = (RippleLayout)rootView.findViewById(R.id.yyzxing_right_bottom_ripplelayout);
        doctorinfoStartchat.setEnabled(true);
        doctorinfoStartnote.setVisibility(View.GONE);
        doctorinfoStartchat.setVisibility(View.INVISIBLE);
        doctorinfoStartchat.setOnClickListener(this);
    }

    protected void initDatas()
    {
        doctorInfoPresenter = new DoctorInfoPresenter();
        doctorInfoPresenter.attachContextAndViewLayer(this,this);
        yyzxingPresenter = new YyzxingPresenter();
        yyzxingPresenter.attachContextAndViewLayer(this,this);
        yyzxingRightTopSpeaker.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton compoundButton, boolean bol)
            {
                if(bol)
                    openSpeakerModel();
                else
                    closeSpeakerModel();
            }
        });
        yyzxingRightTopMicrophone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton compoundButton, boolean bol)
            {
                if(bol)
                    openMicrophone();
                else
                    closeMicrophone();
            }
        });
        yyzxingRightTopSpeaker.setChecked(true);
        yyzxingRightTopMicrophone.setChecked(true);
    }

    protected void initLogic()
    {
        doctorInfoPresenter.initDoctorAllInfo(doctorId);
        callAckObserver = new Observer<AVChatCalleeAckEvent>()
        {
            public void onEvent(AVChatCalleeAckEvent ackInfo)
            {
                if (ackInfo.getEvent() == AVChatEventType.CALLEE_ACK_BUSY)
                {
                    setZxStatusUi(1);
                    destroyAudioEnviroment();
                    yyzxingRightBottomLjstatus.setText("医生正在与他人语音，请稍后再试...");
                }
                else if (ackInfo.getEvent() == AVChatEventType.CALLEE_ACK_REJECT)
                {
                    setZxStatusUi(1);
                    destroyAudioEnviroment();
                    yyzxingRightBottomLjstatus.setText("医生正在与他人语音，请稍后再试...");
                }
                else if (ackInfo.getEvent() == AVChatEventType.CALLEE_ACK_AGREE)
                {
                    yyzxingRightBottomLjstatus.setText("正在建立语音连接，请稍等...");
                }
            }
        };


        aVChatStateObserverLite = new AVChatStateObserverLite()
        {
            public void onCallEstablished()
            {
                setZxStatusUi(3);
                beginAutoCalculationTime();
                rippleView.startRippleAnimation();
                yyzxingRightBottomLjstatus.setText("语音连接成功，请开始咨询！");
            }

            public void onJoinedChannel(int code, String audioFile, String videoFile, int elapsed)
            {
                if(code != AVChatResCode.JoinChannelCode.OK)
                {
                    setZxStatusUi(1);
                    destroyAudioEnviroment();
                    yyzxingRightBottomLjstatus.setText("建立语音连接失败，请稍后重试...");
                }
            }

            public void onUserLeave(String account, int event)
            {
                if(event  == AVChatUserQuitType.TIMEOUT && null != avChatData)
                {
                    finishAudioChat(avChatData);
                    showToast("对方网络异常导致离线了哟！");
                }
            }

            public void onLeaveChannel() {}

            public void onDisconnectServer() {}

            public void onLiveEvent(int event) {}

            public void onUserJoined(String account) {}

            public void onAudioDeviceChanged(int device) {}

            public void onProtocolIncompatible(int status) {}

            public void onDeviceEvent(int code, String desc) {}

            public void onConnectionTypeChanged(int netType) {}

            public void onFirstVideoFrameRendered(String user) {}

            public void onFirstVideoFrameAvailable(String account) {}

            public void onVideoFpsReported(String account, int fps) {}

            public void onSessionStats(AVChatSessionStats sessionStats) {}

            public boolean onAudioFrameFilter(AVChatAudioFrame frame) {return false;}

            public void onReportSpeaker(Map<String, Integer> speakers, int mixedEnergy) {}

            public void onNetworkQuality(String user, int quality, AVChatNetworkStats stats) {}

            public void onVideoFrameResolutionChanged(String user, int width, int height, int rotate) {}

            public boolean onVideoFrameFilter(AVChatVideoFrame frame, boolean maybeDualInput) {return false;}
        };


        callHangupObserver = new Observer<AVChatCommonEvent>()
        {
            public void onEvent(AVChatCommonEvent hangUpInfo)//结束通话
            {
                if(hangUpInfo.getEvent() == AVChatEventType.PEER_HANG_UP)
                {
                    setZxStatusUi(1);
                    destroyAudioEnviroment();
                    finishAutoCalculationTime();
                    rippleView.stopRippleAnimation();
                    showToast("对方关闭语音咨询，谢谢使用！");
                    yyzxingRightBottomLjstatus.setText("成功关闭语音咨询，谢谢使用！");
                }
            }
        };

        AVChatManager.getInstance().observeAVChatState(aVChatStateObserverLite, true);
        AVChatManager.getInstance().observeCalleeAckNotification(callAckObserver, true);
        AVChatManager.getInstance().observeHangUpNotification(callHangupObserver, true);
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.activity_title_back:
            {
                if(rtcStatus == 1)
                    finish();
                else if((rtcStatus == 2 ||rtcStatus == 3) && null != avChatData)
                    finishAudioChat(avChatData);
                break;
            }
            case R.id.doctorinfo_startchat:
            {
                if(rtcStatus == 1)
                {
                    setZxStatusUi(2);
                    beginAudioChat(chatObjAccId,AVChatType.VIDEO);
                }
                else if(rtcStatus == 3 && null != avChatData)
                {
                    finishAudioChat(avChatData);
                }
                break;
            }
        }
    }

    public void onBackPressed()
    {
        if(rtcStatus == 1)
            finish();
        else if((rtcStatus == 2 ||rtcStatus == 3) && null != avChatData)
            finishAudioChat(avChatData);
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
            setZxStatusUi(2);
            beginAudioChat(chatObjAccId,AVChatType.AUDIO);
            doctorName = null != baseinfoBean.getDoctor_name() ? baseinfoBean.getDoctor_name().trim() : "未知";
            yyzxingRightBottomTime.setText(getTimeText(ksTime));
            yyzxingRightTopTime.setText("正在与" + doctorName + "医生咨询，此次通话将在 " + getTimeText(syTime) + " 后结束");
        }
    }

    /**1未开始语音通讯，2处于连接过程中,3正在咨询,4时限用完**/
    public void setZxStatusUi(int rtcCode)
    {
        switch(rtcCode)
        {
            case 1:
            {
                rtcStatus = 1;
                doctorinfoStartchat.setText("开始咨询");
                doctorinfoStartchat.setBackgroundColor(Color.argb(255,0,147,221));
                break;
            }
            case 2:
            {
                rtcStatus = 2;
                doctorinfoStartchat.setText("正在连接");
                doctorinfoStartchat.setBackgroundColor(Color.argb(255,0,147,221));
                break;
            }
            case 3:
            {
                rtcStatus = 3;
                doctorinfoStartchat.setText("结束咨询");
                doctorinfoStartchat.setBackgroundColor(Color.argb(255,255,0,0));
                break;
            }
            case 4:
            {
                rtcStatus = 1;
                doctorinfoStartchat.setEnabled(false);
                doctorinfoStartchat.setText("谢谢咨询");
                yyzxingRightTopTime.setText("正在与" + doctorName + "医生咨询，此次通话已结束");
                doctorinfoStartchat.setBackgroundColor(Color.argb(255,102,102,102));
            }
        }
    }

    /********************************开启麦克风**************************/
    public void openMicrophone()
    {
        AVChatManager.getInstance().muteLocalAudio(false);

    }

    /********************************关闭麦克风**************************/
    public void closeMicrophone()
    {
        AVChatManager.getInstance().muteLocalAudio(true);

    }

    /*********************************开启扬声器***************************/
    public void openSpeakerModel()
    {
        AVChatManager.getInstance().setSpeaker(true);

    }

    /*******************************关闭扬声器*****************************/
    public void closeSpeakerModel()
    {
        AVChatManager.getInstance().setSpeaker(false);

    }

    /****************************关闭音频通讯环境**************************/
    public void destroyAudioEnviroment()
    {
        AVChatManager.getInstance().disableRtc();
    }

    /***************************初始化音频通讯环境*************************/
    public AVChatNotifyOption initAudioEnviroment()
    {
        AVChatManager.getInstance().enableRtc();//打开Rtc模块
        AVChatNotifyOption notifyOption = new AVChatNotifyOption();
        notifyOption.webRTCCompat = false;//是否兼容WebRTC模式
        notifyOption.extendMessage = orderId; //附加字段
        notifyOption.forceKeepCalling = true;//为true则离线持续呼叫
        return notifyOption;
    }

    /********************************挂断音频******************************/
    public void finishAudioChat(AVChatData avChatData)
    {
        AVChatManager.getInstance().hangUp2(avChatData.getChatId(), new AVChatCallback<Void>()
        {
            public void onSuccess(Void aVoid)
            {
                if(!isNoTime)
                    setZxStatusUi(1);
                else
                    setZxStatusUi(4);
                destroyAudioEnviroment();
                finishAutoCalculationTime();
                rippleView.stopRippleAnimation();
                yyzxingRightBottomLjstatus.setText("成功关闭语音咨询，谢谢使用！");
            }

            public void onFailed(int code)
            {
                setZxStatusUi(3);
                yyzxingRightBottomLjstatus.setText("关闭语音咨询失败，请重试...");
            }

            public void onException(Throwable exception)
            {
                setZxStatusUi(3);
                yyzxingRightBottomLjstatus.setText("关闭语音咨询失败，请重试...");
            }
        });
    }

    /********************************拨打音频******************************/
    public void beginAudioChat(String otherSideAccId, final AVChatType aVChatType)
    {
        AVChatManager.getInstance().call2(otherSideAccId,aVChatType,initAudioEnviroment(),new AVChatCallback<AVChatData>()
        {
            public void onSuccess(AVChatData data)
            {
                avChatData = data; //发起会话成功
                yyzxingRightBottomLjstatus.setText("成功发起语音咨询请求，请稍等...");
            }

            public void onFailed(int code)
            {
                setZxStatusUi(1);
                destroyAudioEnviroment();
                yyzxingRightBottomLjstatus.setText("发起语音咨询请求失败，请重试...");
            }

            public void onException(Throwable exception)
            {
                setZxStatusUi(1);
                destroyAudioEnviroment();
                yyzxingRightBottomLjstatus.setText("发起语音咨询请求失败，请重试...");
            }
        });
    }

    /**************开始倒计时************/
    public void beginAutoCalculationTime()
    {
        timer = new Timer();
        timerTask = new TimerTask()
        {
            public void run()
            {
                runOnUiThread(new Runnable()
                {
                    public void run()
                    {
                        syTime--;
                        ksTime++;
                        yyzxingRightBottomTime.setText(getTimeText(ksTime));
                        yyzxingRightTopTime.setText("正在与" + doctorName + "医生咨询，此次通话将在 " + getTimeText(syTime) + " 后结束");
                        if(syTime < 0)
                        {
                            showToast("咨询时限用完了！如需继续咨询，请购买咨询时间后再继续咨询，谢谢使用！");
                            finishAutoCalculationTime();
                            isNoTime = true;
                            if(avChatData != null)
                                finishAudioChat(avChatData);
                        }
                    }
                });
            }
        };
        timer.schedule(timerTask,1000,1000);
    }

    /**************结束倒计时************/
    public void finishAutoCalculationTime()
    {
        if(null != timer)
            timer.cancel();
    }

    /********时间中文化（单位：秒）**********/
    public String getTimeText(long secondTime)
    {
        int seconds = (int)secondTime % 60;
        int minutes = (int)((secondTime / 60) % 60);
        int hours = (int)((secondTime / (60 * 60)) % 24);
        int days = (int)(secondTime / (60 * 60 * 24));
        boolean hasDays = days > 0;
        return String.format("%1$02d%4$s %2$02d%5$s %3$02d%6$s",
                hasDays ? days : hours,
                hasDays ? hours : minutes,
                hasDays ? minutes : seconds,
                hasDays ? "天" : "小时",
                hasDays ? "小时" : "分钟",
                hasDays ? "分钟" : "秒");
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

    public void refreshDoctorRatingInfo(DoctorAllInfo.PageBean pageBean)
    {

    }

    public void loadMoreDoctorRatingInfo(DoctorAllInfo.PageBean pageBean)
    {

    }
}
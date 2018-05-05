package ufhealth.integratedmachine.client.ui.zxzx.view;

import android.view.View;
import java.util.ArrayList;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import android.graphics.Color;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.donkingliang.labels.LabelsView;
import ufhealth.integratedmachine.client.R;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.avchat.AVChatCallback;
import com.netease.nimlib.sdk.avchat.AVChatManager;
import com.netease.nimlib.sdk.avchat.AVChatStateObserverLite;
import com.netease.nimlib.sdk.avchat.constant.AVChatEventType;
import com.netease.nimlib.sdk.avchat.constant.AVChatResCode;
import com.netease.nimlib.sdk.avchat.constant.AVChatType;
import com.netease.nimlib.sdk.avchat.constant.AVChatUserQuitType;
import com.netease.nimlib.sdk.avchat.constant.AVChatVideoScalingType;
import com.netease.nimlib.sdk.avchat.model.AVChatAudioFrame;
import com.netease.nimlib.sdk.avchat.model.AVChatCalleeAckEvent;
import com.netease.nimlib.sdk.avchat.model.AVChatCameraCapturer;
import com.netease.nimlib.sdk.avchat.model.AVChatCommonEvent;
import com.netease.nimlib.sdk.avchat.model.AVChatData;
import com.netease.nimlib.sdk.avchat.model.AVChatNetworkStats;
import com.netease.nimlib.sdk.avchat.model.AVChatNotifyOption;
import com.netease.nimlib.sdk.avchat.model.AVChatParameters;
import com.netease.nimlib.sdk.avchat.model.AVChatSessionStats;
import com.netease.nimlib.sdk.avchat.model.AVChatSurfaceViewRenderer;
import com.netease.nimlib.sdk.avchat.model.AVChatVideoCapturerFactory;
import com.netease.nimlib.sdk.avchat.model.AVChatVideoFrame;

import ufhealth.integratedmachine.client.base.BaseAct;
import ufhealth.integratedmachine.client.bean.zxzx.DoctorAllInfo;
import ufhealth.integratedmachine.client.ui.zxzx.view_v.SpzxingAct_V;
import ufhealth.integratedmachine.client.ui.zxzx.view_v.DoctorInfoAct_V;
import ufhealth.integratedmachine.client.ui.zxzx.presenter.SpzxingPresenter;
import ufhealth.integratedmachine.client.ui.zxzx.presenter.DoctorInfoPresenter;

public class SpzxingAct extends BaseAct implements SpzxingAct_V,DoctorInfoAct_V,View.OnClickListener
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

    private RelativeLayout doctorinfoImgall;
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
    private TextView spzxingRightTopTime;
    private CheckBox spzxingRightTopSpeaker;
    private CheckBox spzxingRightTopMicrophone;
    private LinearLayout spzxingRightBottom;
    private AVChatSurfaceViewRenderer spzxingRightOthervedio;
    private AVChatSurfaceViewRenderer spzxingRightMevedio;
    private TextView spzxingRightBottomLjstatus;

    private SpzxingPresenter spzxingPresenter;
    private DoctorInfoPresenter doctorInfoPresenter;

    private AVChatData avChatData = null;
    private Boolean toggleSpeakerMode = false;
    private AVChatCameraCapturer videoCapturer = null;
    private Observer<AVChatCommonEvent> callHangupObserver = null;
    private Observer<AVChatCalleeAckEvent> callAckObserver = null;
    private AVChatStateObserverLite aVChatStateObserverLite = null;

    protected int setLayoutResID()
    {
        return R.layout.activity_spzxing;
    }

    protected void initWidgets(View rootView)
    {
        super.initWidgets(rootView);
        setTitleContent("视频咨询中");
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
        doctorinfoLabels = (LabelsView) rootView.findViewById(R.id.doctorinfo_labels);
        spzxingRightBottom = (LinearLayout) rootView.findViewById(R.id.spzxing_right_bottom);
        spzxingRightOthervedio = (AVChatSurfaceViewRenderer) rootView.findViewById(R.id.spzxing_right_othervedio);
        spzxingRightMevedio = (AVChatSurfaceViewRenderer) rootView.findViewById(R.id.spzxing_right_mevedio);
        spzxingRightBottomLjstatus = (TextView) rootView.findViewById(R.id.spzxing_right_bottom_ljstatus);
        spzxingRightTopTime = (TextView) findViewById(R.id.spzxing_right_top_time);
        spzxingRightTopSpeaker = (CheckBox) findViewById(R.id.spzxing_right_top_speaker);
        spzxingRightTopMicrophone = (CheckBox) findViewById(R.id.spzxing_right_top_microphone);


        doctorinfoStartchat.setEnabled(true);
        doctorinfoStartnote.setVisibility(View.GONE);
        doctorinfoStartchat.setVisibility(View.INVISIBLE);
        doctorinfoStartchat.setOnClickListener(this);
    }

    protected void initDatas()
    {
        doctorInfoPresenter = new DoctorInfoPresenter();
        doctorInfoPresenter.attachContextAndViewLayer(this,this);
        spzxingPresenter = new SpzxingPresenter();
        spzxingPresenter.attachContextAndViewLayer(this,this);
    }

    protected void initLogic()
    {
        doctorInfoPresenter.initDoctorAllInfo(getIntent().getStringExtra("doctorid").trim());
        spzxingRightTopSpeaker.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton compoundButton, boolean bol)
            {
                if(bol)
                    openSpeakerModel();
                else
                    closeSpeakerModel();
            }
        });
        spzxingRightTopMicrophone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton compoundButton, boolean bol)
            {
                if(bol)
                    openMicrophone();
                else
                    closeMicrophone();
            }
        });
        spzxingRightTopSpeaker.setChecked(true);
        spzxingRightTopMicrophone.setChecked(true);


        callHangupObserver = new Observer<AVChatCommonEvent>()
        {
            public void onEvent(AVChatCommonEvent hangUpInfo)//结束通话
            {
                if(hangUpInfo.getEvent() == AVChatEventType.PEER_HANG_UP)
                {
                    setZxStatusUi(1);
                    finishAutoCalculationTime();
                    destroyVedioEnviroment();
                    showToast("对方关闭视频咨询，谢谢使用！");
                    spzxingRightBottomLjstatus.setText("成功关闭视频咨询，谢谢使用！");
                }
            }
        };
        aVChatStateObserverLite = new AVChatStateObserverLite()
        {
            public void onLeaveChannel() {}

            public void onCallEstablished()
            {
                setZxStatusUi(3);
                beginAutoCalculationTime();
                spzxingRightBottomLjstatus.setText("视频连接成功，请开始咨询！");
            }

            public void onUserLeave(String account, int event)
            {
                if(event  == AVChatUserQuitType.TIMEOUT && null != avChatData)
                {
                    finishVedioChat(avChatData);
                    showToast("对方网络异常导致离线了哟！");
                }
            }

            public void onJoinedChannel(int code, String audioFile, String videoFile, int elapsed)
            {
                if(code != AVChatResCode.JoinChannelCode.OK)
                {
                    setZxStatusUi(1);
                    spzxingRightBottomLjstatus.setText("建立视频连接失败，请稍后重试...");
                }
            }

            public void onDisconnectServer() {}

            public void onLiveEvent(int event) {}

            public void onUserJoined(String account) {}

            public void onAudioDeviceChanged(int device) {}

            public void onProtocolIncompatible(int status) {}

            public void onDeviceEvent(int code, String desc) {}

            public void onConnectionTypeChanged(int netType) {}

            public void onFirstVideoFrameRendered(String user)
            {

            }

            public void onFirstVideoFrameAvailable(String account)
            {
            }

            public void onVideoFpsReported(String account, int fps) {}

            public void onSessionStats(AVChatSessionStats sessionStats) {}

            public boolean onAudioFrameFilter(AVChatAudioFrame frame) {return false;}

            public void onReportSpeaker(Map<String, Integer> speakers, int mixedEnergy) {}

            public void onNetworkQuality(String user, int quality, AVChatNetworkStats stats) {}

            public void onVideoFrameResolutionChanged(String user, int width, int height, int rotate) {}

            public boolean onVideoFrameFilter(AVChatVideoFrame frame, boolean maybeDualInput) {return false;}
        };
        callAckObserver = new Observer<AVChatCalleeAckEvent>()
        {
            public void onEvent(AVChatCalleeAckEvent ackInfo)
            {
                if (ackInfo.getEvent() == AVChatEventType.CALLEE_ACK_BUSY)
                {
                    setZxStatusUi(1);
                    spzxingRightBottomLjstatus.setText("医生正在与他人视频，请稍后再试...");
                }
                else if (ackInfo.getEvent() == AVChatEventType.CALLEE_ACK_REJECT)
                {
                    setZxStatusUi(1);
                    spzxingRightBottomLjstatus.setText("医生正在与他人视频，请稍后再试...");
                }
                else if (ackInfo.getEvent() == AVChatEventType.CALLEE_ACK_AGREE)
                {
                    spzxingRightBottomLjstatus.setText("正在建立语音视频，请稍等...");
                }
            }
        };
        AVChatManager.getInstance().observeAVChatState(aVChatStateObserverLite, true);
        AVChatManager.getInstance().observeCalleeAckNotification(callAckObserver, true);
        AVChatManager.getInstance().observeHangUpNotification(callHangupObserver, true);
    }

    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.activity_title_back:
            {
                if(rtcStatus == 1)
                    finish();
                else if(rtcStatus == 3 && null != avChatData)
                    finishVedioChat(avChatData);
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
                    finishVedioChat(avChatData);
                break;
            }
        }
    }

    public void onBackPressed()
    {
        if(rtcStatus == 1)
            finish();
        else if(rtcStatus == 3 && null != avChatData)
            finishVedioChat(avChatData);
    }

    protected void onDestroy()
    {
        AVChatManager.getInstance().observeCalleeAckNotification(callAckObserver, false);
        AVChatManager.getInstance().observeHangUpNotification(callHangupObserver, false);
        AVChatManager.getInstance().observeAVChatState(aVChatStateObserverLite, false);
        doctorInfoPresenter.detachContextAndViewLayout();
        spzxingPresenter.detachContextAndViewLayout();
        super.onDestroy();
    }



    /**1未开始视频通讯，2处于连接过程中,3正在咨询,4处于关闭过程中,5时限用完**/
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
                rtcStatus = 4;
                doctorinfoStartchat.setText("正在结束");
                doctorinfoStartchat.setBackgroundColor(Color.argb(255,255,0,0));
                break;
            }
            case 5:
            {
                rtcStatus = 1;
                doctorinfoStartchat.setEnabled(false);
                doctorinfoStartchat.setText("谢谢咨询");
                doctorinfoStartchat.setBackgroundColor(Color.argb(255,102,102,102));
                spzxingRightTopTime.setText("正在与" + doctorName + "医生咨询，此次通话已结束");
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

    /****************************关闭视频通讯环境**************************/
    public void destroyVedioEnviroment()
    {
        AVChatManager.getInstance().setParameters(new AVChatParameters());
        AVChatManager.getInstance().disableVideo();
        AVChatManager.getInstance().disableRtc();
    }

    /********************************挂断视频******************************/
    public void finishVedioChat(AVChatData avChatData)
    {
        AVChatManager.getInstance().hangUp2(avChatData.getChatId(), new AVChatCallback<Void>()
        {
            public void onSuccess(Void aVoid)
            {
                if(!isNoTime)
                    setZxStatusUi(1);
                else
                    setZxStatusUi(5);
                destroyVedioEnviroment();
                finishAutoCalculationTime();
                spzxingRightBottomLjstatus.setText("成功关闭视频咨询，谢谢使用！");
            }

            public void onFailed(int code)
            {
                setZxStatusUi(3);
                spzxingRightBottomLjstatus.setText("关闭视频咨询失败，请重试...");
            }

            public void onException(Throwable exception)
            {
                setZxStatusUi(3);
                spzxingRightBottomLjstatus.setText("关闭视频咨询失败，请重试...");
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
            doctorinfoStartchat.setVisibility(View.VISIBLE);


            setZxStatusUi(2);
            beginAudioChat(chatObjAccId,AVChatType.VIDEO);
            doctorName = null != baseinfoBean.getDoctor_name() ? baseinfoBean.getDoctor_name().trim() : "未知";
            spzxingRightTopTime.setText("正在与" + doctorName + "医生咨询，此次通话将在 " + getTimeText(syTime) + " 后结束");
        }
    }

    /***************************初始化音频通讯环境*************************/
    public AVChatNotifyOption initVedioEnviroment()
    {
        AVChatManager.getInstance().enableRtc();//打开Rtc模块
        //打开视频模块
        AVChatManager.getInstance().enableVideo();
        //创建视频采集模块并且设置到系统中
        if (videoCapturer == null)
        {
            videoCapturer = AVChatVideoCapturerFactory.createCameraCapturer();
            videoCapturer.setAutoFocus(true);
            AVChatManager.getInstance().setupVideoCapturer(videoCapturer);
        }
        AVChatManager.getInstance().setupRemoteVideoRender(chatObjAccId, spzxingRightOthervedio, false, AVChatVideoScalingType.SCALE_ASPECT_BALANCED);

        AVChatNotifyOption notifyOption = new AVChatNotifyOption();
        notifyOption.webRTCCompat = false;//是否兼容WebRTC模式
        notifyOption.extendMessage = orderId; //附加字段
        notifyOption.forceKeepCalling = true;//为true则离线持续呼叫
        return notifyOption;
    }

    /********************************拨打视频******************************/
    public void beginAudioChat(String otherSideAccId, final AVChatType aVChatType)
    {
        AVChatManager.getInstance().call2(otherSideAccId,aVChatType,initVedioEnviroment(),new AVChatCallback<AVChatData>()
        {
            public void onSuccess(AVChatData data)
            {
                avChatData = data; //发起会话成功
                //设置本地预览画布
                AVChatManager.getInstance().setupLocalVideoRender(spzxingRightMevedio, false, AVChatVideoScalingType.SCALE_ASPECT_FIT);
                //开始视频预览
                AVChatManager.getInstance().startVideoPreview();
                spzxingRightBottomLjstatus.setText("成功发起视频咨询请求，请稍等...");
            }

            public void onFailed(int code)
            {
                setZxStatusUi(1);
                destroyVedioEnviroment();
                spzxingRightBottomLjstatus.setText("发起视频咨询请求失败，请重试...");
            }

            public void onException(Throwable exception)
            {
                setZxStatusUi(1);
                destroyVedioEnviroment();
                spzxingRightBottomLjstatus.setText("发起视频咨询请求失败，请重试...");
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
                        spzxingRightTopTime.setText("正在与" + doctorName + "医生咨询，此次通话将在 " + getTimeText(syTime) + " 后结束");
                        if(syTime < 0)
                        {
                            isNoTime = true;
                            showToast("咨询时限用完了！如需继续咨询，请购买咨询时间后再继续咨询，谢谢使用！");
                            finishAutoCalculationTime();
                            if(avChatData != null)
                            {
                                finishVedioChat(avChatData);
                            }
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
        timer.cancel();

    }

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
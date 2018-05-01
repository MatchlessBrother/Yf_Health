package ufhealth.integratedmachine.client.ui.zxzx.view;

import rx.Observable;
import rx.Subscriber;
import android.view.View;
import android.widget.Button;
import android.graphics.Color;
import android.content.Intent;
import android.widget.TextView;
import android.widget.ImageView;
import rx.schedulers.Schedulers;
import java.util.concurrent.TimeUnit;
import android.widget.RelativeLayout;
import android.graphics.BitmapFactory;
import ufhealth.integratedmachine.client.R;
import com.hwangjr.rxbus.annotation.Subscribe;
import rx.android.schedulers.AndroidSchedulers;
import ufhealth.integratedmachine.client.base.BaseAct;
import com.yuan.devlibrary._12_______Utils.QRCodeTools;
import ufhealth.integratedmachine.client.bean.zxzx.Billinfo;
import ufhealth.integratedmachine.client.ui.main.view.MainAct;
import ufhealth.integratedmachine.client.ui.zxzx.view_v.PayAct_V;
import ufhealth.integratedmachine.client.ui.zxzx.presenter.PayPresenter;

public class PayAct extends BaseAct implements PayAct_V,View.OnClickListener
{
    private String TYPE = "";
    private RelativeLayout payQrcAll;
    private ImageView payQrcImg;
    private TextView payQrcTop;
    private TextView payQrcDown;
    private RelativeLayout paySinAll;
    private TextView payStatus;
    private ImageView payStatusImg;
    private TextView payStatusNote;
    private Button payStatusBtn;
    private TextView payValue;
    private TextView payValueReceiver;
    private TextView payValueOrdernum;
    private TextView payValueTime;
    private TextView payType;
    private Billinfo billinfo;
    private PayPresenter payPresenter;

    protected int setLayoutResID()
    {
        return R.layout.activity_pay;
    }

    protected void initWidgets(View rootView)
    {
        super.initWidgets(rootView);
        setTitleContent("扫码支付");
        TYPE = getIntent().getStringExtra("type");
        billinfo = getIntent().getParcelableExtra("bill");
        payQrcAll = (RelativeLayout)rootView.findViewById(R.id.pay_qrc_all);
        payQrcImg = (ImageView) rootView.findViewById(R.id.pay_qrc_img);
        payQrcTop = (TextView) rootView.findViewById(R.id.pay_qrc_top);
        payQrcDown = (TextView) rootView.findViewById(R.id.pay_qrc_down);
        paySinAll = (RelativeLayout) rootView.findViewById(R.id.pay_sin_all);
        payStatus = (TextView) rootView.findViewById(R.id.pay_status);
        payStatusImg = (ImageView) rootView.findViewById(R.id.pay_status_img);
        payStatusNote = (TextView) rootView.findViewById(R.id.pay_status_note);
        payStatusBtn = (Button) rootView.findViewById(R.id.pay_status_btn);
        payValue = (TextView) rootView.findViewById(R.id.pay_value);
        payType = (TextView)rootView.findViewById(R.id.pay_type);
        payValueReceiver = (TextView) rootView.findViewById(R.id.pay_value_receiver);
        payValueOrdernum = (TextView) rootView.findViewById(R.id.pay_value_ordernum);
        payValueTime = (TextView) rootView.findViewById(R.id.pay_value_time);

        payQrcImg.setImageBitmap(QRCodeTools.createQRCodeBitmap(billinfo.getPayQrcodeUrl(),340,"UTF-8","H", "3", Color.BLACK,Color.WHITE,null, BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher),0.2f));
        switch (TYPE)
        {
            case ZxzxAct.SPZX:payType.setText("优服健康-视频咨询订单");break;
            case ZxzxAct.YYZX:payType.setText("优服健康-语音咨询订单");break;
            case ZxzxAct.MYYZ:payType.setText("优服健康-名医咨询订单");break;
            case ZxzxAct.KSZX:payType.setText("优服健康-快速咨询订单");break;
            case ZxzxAct.BGJD:payType.setText("优服健康-报告解读订单");break;
            case ZxzxAct.TWZX:payType.setText("优服健康-图文咨询订单");break;
        }
        payValue.setText("¥" + (null != billinfo.getTotalPrice() ? billinfo.getTotalPrice().trim() : "0"));
        payValueTime.setText("下单时间：" + (null != billinfo.getCreateTime() ? billinfo.getCreateTime().trim() : " 未知"));
        payValueOrdernum.setText("订单编号：" + (null != billinfo.getPayOrderNumber() ? billinfo.getPayOrderNumber().trim() : "未知"));
        payStatusBtn.setOnClickListener(this);
    }

    protected void initDatas()
    {
        payPresenter = new PayPresenter();
        payPresenter.attachContextAndViewLayer(this,this);
    }

    protected void initLogic()
    {
        payPresenter.queryPayResult(billinfo.getPayOrderNumber().trim());
    }

    public void onClick(View view)
    {
        super.onClick(view);
        switch(view.getId())
        {
            case R.id.pay_status_btn:break;
        }
    }

    protected void onDestroy()
    {
        payPresenter.detachContextAndViewLayout();
        super.onDestroy();
    }

    public void PaymentSuccess()
    {
        payQrcAll.setVisibility(View.GONE);
        paySinAll.setVisibility(View.VISIBLE);
        payStatus.setText("支付成功");
        payStatus.setTextColor(Color.argb(255,60,180,47));
        payStatusImg.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.pay_success));
        showToast("订单支付成功，请在订单管理中发起咨询请求，谢谢！");
        payStatusNote.setText("订单支付成功，请在订单管理中发起咨询请求，谢谢！");
        Observable.just("").delay(3000, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<String>()
        {
            public void onCompleted(){}
            public void onNext(String s)
            {
                Intent intent = new Intent(PayAct.this, MainAct.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
            public void onError(Throwable e){}
        });
    }

    public void PaymentFailure()
    {
        //因为没有支付失败这种情况，，
        //So暂不做相关界面与逻辑处理。
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
}

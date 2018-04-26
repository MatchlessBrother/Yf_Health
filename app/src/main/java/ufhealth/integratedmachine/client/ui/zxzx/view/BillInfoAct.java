package ufhealth.integratedmachine.client.ui.zxzx.view;

import android.view.View;
import ufhealth.integratedmachine.client.R;
import com.hwangjr.rxbus.annotation.Subscribe;
import ufhealth.integratedmachine.client.base.BaseAct;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_View;

public class BillInfoAct extends BaseAct implements BaseMvp_View,View.OnClickListener
{
    public String TYPE;
    public  static  final  String  SPZX = "spzx";
    public  static  final  String  YYZX = "yyzx";
    public  static  final  String  MYYZ = "myyz";

    protected int setLayoutResID()
    {
        return R.layout.activity_billinfo;
    }

    protected void initWidgets(View rootView)
    {
        super.initWidgets(rootView);
        setTitleContent("订单信息");
    }

    protected void initDatas()
    {

    }

    protected void initLogic()
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

    public void onClick(View view)
    {
        super.onClick(view);
    }
}
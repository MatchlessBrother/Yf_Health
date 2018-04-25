package ufhealth.integratedmachine.client.ui.zxzx.view;

import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import ufhealth.integratedmachine.client.R;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.yuan.devlibrary._12_______Utils.CheckBoxRadioBtnModifyTools;

import de.hdodenhof.circleimageview.CircleImageView;
import ufhealth.integratedmachine.client.base.BaseAct;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_View;
import ufhealth.integratedmachine.client.ui.zxzx.presenter.ChooseMultiDoctorPresenter;

public class ChooseMultiDoctorAct extends BaseAct implements BaseMvp_View,View.OnClickListener
{
    private CheckBox choosemultidoctorMfcb;
    private TextView choosemultidoctorName;
    private TextView choosemultidoctorNote;
    private CircleImageView choosemultidoctorImg;
    private CircleImageView choosemultidoctorRecyclerview;
    private ChooseMultiDoctorPresenter chooseMultiDoctorPresenter;

    public String TYPE;
    public  static  final  String  KSZX = "kszx";
    public  static  final  String  BGJD = "bgjd";

    protected int setLayoutResID()
    {
        return R.layout.activity_choosemultidoctor;
    }

    protected void initWidgets(View rootView)
    {
        super.initWidgets(rootView);
        setTitleContent("医生列表");
        choosemultidoctorImg = rootView.findViewById(R.id.choosemultidoctor_img);
        choosemultidoctorMfcb = rootView.findViewById(R.id.choosemultidoctor_mfcb);
        choosemultidoctorName = rootView.findViewById(R.id.choosemultidoctor_name);
        choosemultidoctorNote = rootView.findViewById(R.id.choosemultidoctor_note);
        choosemultidoctorRecyclerview = rootView.findViewById(R.id.choosemultidoctor_recyclerview);
        CheckBoxRadioBtnModifyTools.setHavedDrawbleView(this,choosemultidoctorMfcb,R.drawable.checkbox_blue_no,60,60,3);
    }

    protected void initDatas()
    {
        TYPE = getIntent().getStringExtra("type");
        chooseMultiDoctorPresenter = new ChooseMultiDoctorPresenter();
        chooseMultiDoctorPresenter.isAttachContextAndViewLayer();
        //调用接口获取初始化数据
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
        chooseMultiDoctorPresenter.detachContextAndViewLayout();
        super.onClick(view);
    }
}
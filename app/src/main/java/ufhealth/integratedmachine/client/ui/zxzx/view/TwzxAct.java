package ufhealth.integratedmachine.client.ui.zxzx.view;

import android.view.View;
import java.util.LinkedList;
import ufhealth.integratedmachine.client.R;
import com.hwangjr.rxbus.annotation.Subscribe;
import ufhealth.integratedmachine.client.base.BasePhotoAct;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_View;

public class TwzxAct extends BasePhotoAct implements BaseMvp_View,View.OnClickListener
{
    protected int setLayoutResID()
    {
        return R.layout.activity_twzx;
    }

    protected void initWidgets(View rootView)
    {
        super.initWidgets(rootView);
        setTitleContent("图文咨询");
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

    @Override
    public void setOnNewImgPathListener(LinkedList<String> bitmapPaths)
    {

    }
}
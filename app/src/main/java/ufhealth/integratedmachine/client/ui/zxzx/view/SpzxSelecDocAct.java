package ufhealth.integratedmachine.client.ui.zxzx.view;

import android.view.View;
import ufhealth.integratedmachine.client.R;
import android.support.v7.widget.RecyclerView;
import com.hwangjr.rxbus.annotation.Subscribe;
import ufhealth.integratedmachine.client.base.BaseAct;
import ufhealth.integratedmachine.client.ui.zxzx.view_v.SpzxSelecDocAct_V;
import ufhealth.integratedmachine.client.ui.zxzx.presenter.SpzxSelecDocPresenter;

public class SpzxSelecDocAct extends BaseAct implements SpzxSelecDocAct_V,View.OnClickListener
{
    private SpzxSelecDocPresenter presenter;
    private RecyclerView spzxselecdocRecyclerview;

    protected int setLayoutResID()
    {
        return R.layout.activity_spzxselecdoc;
    }

    protected void initWidgets(View rootView)
    {
        super.initWidgets(rootView);
        setTitleContent("选择医生");
        presenter = new SpzxSelecDocPresenter(this);
        spzxselecdocRecyclerview = findViewById(R.id.spzxselecdoc_recyclerview);
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

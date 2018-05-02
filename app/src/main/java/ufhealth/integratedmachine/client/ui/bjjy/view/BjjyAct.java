package ufhealth.integratedmachine.client.ui.bjjy.view;

import android.view.View;
import android.content.Intent;
import android.widget.RelativeLayout;
import ufhealth.integratedmachine.client.R;
import com.hwangjr.rxbus.annotation.Subscribe;
import ufhealth.integratedmachine.client.base.BaseAct;
import ufhealth.integratedmachine.client.ui.bjjy.view_v.BjjyAct_V;
import ufhealth.integratedmachine.client.ui.bjjy.presenter.BjjyPresenter;

public class BjjyAct extends BaseAct implements BjjyAct_V,View.OnClickListener
{
    private RelativeLayout bjjyBghAll;
    private RelativeLayout bjjyDbqhAll;
    private RelativeLayout bjjyDqjcbgAll;
    private RelativeLayout bjjyDbjzkAll;
    private RelativeLayout bjjyYyjcAll;
    private RelativeLayout bjjyQcphAll;
    private BjjyPresenter bjjyPresenter;

    protected int setLayoutResID()
    {
        return R.layout.activity_bjjy;
    }

    protected void initWidgets(View rootView)
    {
        super.initWidgets(rootView);
        setTitleContent("便捷就医");
        bjjyBghAll = (RelativeLayout) findViewById(R.id.bjjy_bgh_all);
        bjjyDbqhAll = (RelativeLayout) findViewById(R.id.bjjy_dbqh_all);
        bjjyDqjcbgAll = (RelativeLayout) findViewById(R.id.bjjy_dqjcbg_all);
        bjjyDbjzkAll = (RelativeLayout) findViewById(R.id.bjjy_dbjzk_all);
        bjjyYyjcAll = (RelativeLayout) findViewById(R.id.bjjy_yyjc_all);
        bjjyQcphAll = (RelativeLayout) findViewById(R.id.bjjy_qcph_all);
        bjjyBghAll.setOnClickListener(this);
        bjjyDbqhAll.setOnClickListener(this);
        bjjyDqjcbgAll.setOnClickListener(this);
        bjjyDbjzkAll.setOnClickListener(this);
        bjjyYyjcAll.setOnClickListener(this);
        bjjyQcphAll.setOnClickListener(this);
    }

    protected void initDatas()
    {
        bjjyPresenter = new BjjyPresenter();
        bjjyPresenter.attachContextAndViewLayer(this,this);
    }

    protected void initLogic()
    {

    }

    public void onClick(View view)
    {
        super.onClick(view);
        switch (view.getId())
        {
            case R.id.bjjy_bgh_all:
            {
                Intent intent = new Intent(this,BjjyBghAct.class);
                startActivity(intent);
                break;
            }
            case R.id.bjjy_dbjzk_all:
            {
                Intent intent = new Intent(this,BjjyDbjzkAct.class);
                startActivity(intent);
                break;
            }
            case R.id.bjjy_dbqh_all:
            case R.id.bjjy_dqjcbg_all:
            case R.id.bjjy_yyjc_all:
            case R.id.bjjy_qcph_all:showToast("功能暂未开放，敬请期待...");break;
        }
    }

    protected void onDestroy()
    {
        bjjyPresenter.detachContextAndViewLayout();
        super.onDestroy();
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
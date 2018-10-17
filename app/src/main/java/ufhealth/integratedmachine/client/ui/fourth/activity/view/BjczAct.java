package ufhealth.integratedmachine.client.ui.fourth.activity.view;

import android.view.View;
import java.util.LinkedList;
import ufhealth.integratedmachine.client.R;
import ufhealth.integratedmachine.client.base.BasePhotoAct;
import ufhealth.integratedmachine.client.ui.fourth.activity.view_v.BjczAct_V;
import ufhealth.integratedmachine.client.ui.fourth.activity.presenter.BjczPresenter;

public class BjczAct extends BasePhotoAct implements BjczAct_V,View.OnClickListener
{
    private BjczPresenter mBjczPresenter;

    protected int setLayoutResID()
    {
        return R.layout.activity_bjcz;
    }

    protected void initWidgets(View rootView)
    {
        super.initWidgets(rootView);
        setTitleContent("报警处置");
    }

    protected void initDatas()
    {
        mBjczPresenter = new BjczPresenter();
        bindBaseMvpPresenter(mBjczPresenter);
    }

    protected void initLogic()
    {

    }

    public void onClick(View view)
    {
        super.onClick(view);
        switch (view.getId())
        {

        }
    }

    protected void setOnNewImgPathListener(LinkedList<String> bitmapPaths)
    {

    }
}
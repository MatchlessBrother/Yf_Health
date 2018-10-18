package ufhealth.integratedmachine.client.ui.fourth.activity.view;

import java.util.List;
import android.view.View;
import java.util.ArrayList;
import java.util.LinkedList;
import android.widget.Button;
import android.widget.EditText;
import ufhealth.integratedmachine.client.R;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import ufhealth.integratedmachine.client.base.BasePhotoAct;
import ufhealth.integratedmachine.client.adapter.fourth.BjczImgAdapter;
import ufhealth.integratedmachine.client.ui.fourth.activity.view_v.BjczAct_V;
import ufhealth.integratedmachine.client.ui.fourth.activity.presenter.BjczPresenter;

public class BjczAct extends BasePhotoAct implements BjczAct_V,View.OnClickListener
{
    private Button bjczBtn;
    private EditText bjczEt;
    private BjczPresenter mBjczPresenter;
    private RecyclerView bjczRecyclerview;
    private BjczImgAdapter mBjczImgAdapter;

    protected int setLayoutResID()
    {
        return R.layout.activity_bjcz;
    }

    protected void initWidgets(View rootView)
    {
        super.initWidgets(rootView);
        setTitleContent("报警处置");
        bjczEt = (EditText)rootView.findViewById(R.id.bjcz_et);
        bjczBtn = (Button)rootView.findViewById(R.id.bjcz_btn);
        List dataList = new ArrayList<String>();dataList.add("");
        dataList.add("");
        dataList.add("");
        dataList.add("");
        mBjczImgAdapter =  new BjczImgAdapter(mActivity,dataList);
        bjczRecyclerview = (RecyclerView)rootView.findViewById(R.id.bjcz_recyclerview);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mActivity,3);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        bjczRecyclerview.setLayoutManager(gridLayoutManager);
        bjczRecyclerview.setAdapter(mBjczImgAdapter);
    }

    protected void initDatas()
    {
        mBjczPresenter = new BjczPresenter();
        bindBaseMvpPresenter(mBjczPresenter);
    }

    protected void initLogic()
    {
        bjczBtn.setOnClickListener(this);
    }

    public void onClick(View view)
    {
        super.onClick(view);
        switch (view.getId())
        {
            case R.id.bjcz_btn:
            {
                break;
            }
        }
    }

    protected void setOnNewImgPathListener(LinkedList<String> bitmapPaths)
    {

    }
}
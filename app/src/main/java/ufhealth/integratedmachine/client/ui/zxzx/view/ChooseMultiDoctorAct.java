package ufhealth.integratedmachine.client.ui.zxzx.view;

import android.view.View;
import java.util.ArrayList;
import android.content.Intent;
import android.widget.CheckBox;
import android.widget.TextView;
import ufhealth.integratedmachine.client.R;
import com.hwangjr.rxbus.annotation.Subscribe;
import android.support.v7.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import ufhealth.integratedmachine.client.base.BaseAct;
import ufhealth.integratedmachine.client.bean.zxzx.DoctorInfo;
import com.yuan.devlibrary._12_______Utils.CheckBoxRadioBtnModifyTools;
import ufhealth.integratedmachine.client.adapter.zxzx.DoctorInfoAdapter;
import ufhealth.integratedmachine.client.ui.zxzx.view_v.ChooseMultiDoctor_V;
import ufhealth.integratedmachine.client.ui.zxzx.presenter.ChooseMultiDoctorPresenter;

public class ChooseMultiDoctorAct extends BaseAct implements ChooseMultiDoctor_V
{
    public String TYPE;
    private CheckBox choosemultidoctorMfcb;
    private TextView choosemultidoctorName;
    private TextView choosemultidoctorNote;
    private CircleImageView choosemultidoctorImg;
    private DoctorInfoAdapter doctorInfoAdapter;
    private RecyclerView choosemultidoctorRecyclerview;
    private SwipeRefreshLayout chooseMultidoctorSwipeLayout;
    private ChooseMultiDoctorPresenter chooseMultiDoctorPresenter;

    protected int setLayoutResID()
    {
        return R.layout.activity_choosemultidoctor;
    }

    protected void initWidgets(View rootView)
    {
        super.initWidgets(rootView);
        setTitleContent("医生列表");
        getTitleMoreSelector().setText("确定");
        TYPE = getIntent().getStringExtra("type");
        getTitleMoreSelector().setBackgroundResource(R.color.transparent);
        choosemultidoctorImg = rootView.findViewById(R.id.choosemultidoctor_img);
        choosemultidoctorMfcb = rootView.findViewById(R.id.choosemultidoctor_mfcb);
        choosemultidoctorName = rootView.findViewById(R.id.choosemultidoctor_name);
        choosemultidoctorNote = rootView.findViewById(R.id.choosemultidoctor_note);
        chooseMultidoctorSwipeLayout = rootView.findViewById(R.id.choosemultidoctor_swipelayout);
        choosemultidoctorRecyclerview = rootView.findViewById(R.id.choosemultidoctor_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        choosemultidoctorRecyclerview.setLayoutManager(linearLayoutManager);
        doctorInfoAdapter = DoctorInfoAdapter.getAdapter(this,new ArrayList<DoctorInfo.ContentBean>(),DoctorInfoAdapter.MULTISELECT,TYPE);
        choosemultidoctorRecyclerview.setAdapter(doctorInfoAdapter);
        CheckBoxRadioBtnModifyTools.setHavedDrawbleView(this,choosemultidoctorMfcb,R.drawable.checkbox_blue_no,60,60,3);

        chooseMultidoctorSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            public void onRefresh()
            {
                chooseMultiDoctorPresenter.refreshDatas();
            }
        });

        doctorInfoAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener()
        {
            public void onLoadMoreRequested()
            {
                chooseMultiDoctorPresenter.loadMoreDatas();
            }
        }, choosemultidoctorRecyclerview);
    }

    protected void initDatas()
    {
        chooseMultiDoctorPresenter = new ChooseMultiDoctorPresenter();
        chooseMultiDoctorPresenter.attachContextAndViewLayer(this,this);
    }

    protected void initLogic()
    {
        chooseMultiDoctorPresenter.refreshDatas();
    }

    public void finishRefresh()
    {
        chooseMultidoctorSwipeLayout.setRefreshing(false);

    }

    public void finishLoadMore()
    {
        doctorInfoAdapter.loadMoreComplete();

    }

    public void refreshDatas(DoctorInfo doctorsInfo)
    {
        setTitleMoreSelectorVisibility(View.VISIBLE);
        choosemultidoctorMfcb.setVisibility(View.VISIBLE);
        doctorInfoAdapter.setNewData(doctorsInfo.getContent());
        if(doctorsInfo.getContent().size() < doctorsInfo.getSize())
            doctorInfoAdapter.setEnableLoadMore(false);
        else
            doctorInfoAdapter.setEnableLoadMore(true);
    }

    public void loadMoreDatas(DoctorInfo doctorsInfo)
    {
        doctorInfoAdapter.addData(doctorsInfo.getContent());
        doctorInfoAdapter.notifyDataSetChanged();
        if(doctorsInfo.getContent().size() < doctorsInfo.getSize())
            doctorInfoAdapter.setEnableLoadMore(false);
        else
            doctorInfoAdapter.setEnableLoadMore(true);
    }

    public void onTitleMoreClick()
    {
        super.onTitleMoreClick();
        Intent intent = new Intent(this,BillsInfoAct.class);
        ArrayList<String> doctorsId = new ArrayList<>();
        for(DoctorInfo.ContentBean contentBean : doctorInfoAdapter.getData())
            if(contentBean.isSelected())
                doctorsId.add(contentBean.getDoctor_id()+"");
        intent.putStringArrayListExtra("doctorsId",doctorsId);
        intent.putExtra("isIncludeFreeDoctor",choosemultidoctorMfcb.isChecked());
        startActivity(intent);
    }

    protected void onDestroy()
    {
        chooseMultiDoctorPresenter.detachContextAndViewLayout();
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
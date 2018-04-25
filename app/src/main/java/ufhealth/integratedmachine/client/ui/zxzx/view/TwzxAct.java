package ufhealth.integratedmachine.client.ui.zxzx.view;

import java.util.List;
import android.view.View;
import java.util.LinkedList;
import android.widget.Button;
import android.graphics.Color;
import android.widget.TextView;
import ufhealth.integratedmachine.client.R;
import com.hwangjr.rxbus.annotation.Subscribe;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.GridLayoutManager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yuan.devlibrary._11___Widget.promptBox.BaseDialog;
import com.yuan.devlibrary._12_______Utils.PromptBoxTools;

import ufhealth.integratedmachine.client.base.BasePhotoAct;
import ufhealth.integratedmachine.client.widget.CountEditText;
import ufhealth.integratedmachine.client.ui.zxzx.view_v.TwzxAct_V;
import ufhealth.integratedmachine.client.ui.zxzx.presenter.TwzxPresenter;
import ufhealth.integratedmachine.client.adapter.zxzx.TwzxPictureAdapter;

public class TwzxAct extends BasePhotoAct implements TwzxAct_V,View.OnClickListener
{
    private Button twzxTjwtBtn;
    private TextView twzxNameTv;
    private Integer maxPictureNum;
    private CountEditText twzxContentEt;
    private TwzxPresenter twzxPresenter;
    private RecyclerView twzxRecyclerview;
    private TwzxPictureAdapter twzxPictureAdapter;

    protected int setLayoutResID()
    {
        return R.layout.activity_twzx;
    }

    protected void initWidgets(View rootView)
    {
        super.initWidgets(rootView);
        setTitleContent("图文咨询");
        maxPictureNum = 5;
        twzxNameTv = findViewById(R.id.twzx_name_tv);
        twzxTjwtBtn = findViewById(R.id.twzx_tjwt_btn);
        twzxContentEt = findViewById(R.id.twzx_content_et);
        twzxRecyclerview = findViewById(R.id.twzx_recyclerview);


        twzxNameTv.setText(null != getBaseApp().getUserInfo().getName() ? "问诊人：" + getBaseApp().getUserInfo().getName().trim() : "问诊人：未知");
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,maxPictureNum);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        twzxRecyclerview.setLayoutManager(gridLayoutManager);
        List<String> imgPaths = new LinkedList<>();imgPaths.add("");
        twzxPictureAdapter = new TwzxPictureAdapter(this,maxPictureNum,imgPaths);
        twzxRecyclerview.setAdapter(twzxPictureAdapter);


        twzxTjwtBtn.setOnClickListener(this);
        twzxPictureAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener()
        {
            public void onItemClick(BaseQuickAdapter adapter, View view, int position)
            {
                if(null != twzxPictureAdapter.getData().get(position) && twzxPictureAdapter.getData().get(position).trim().equals(""))
                {
                    showSelectPhotoDialog(Color.argb(255,0,147,221),false,maxPictureNum + 1 - twzxPictureAdapter.getData().size(),false,
                            Color.argb(255,255,255,255),Color.argb(255,0,147,221));
                }
            }
        });

        twzxPictureAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener()
        {
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, final int position)
            {
                if(null != twzxPictureAdapter.getData().get(position) && !twzxPictureAdapter.getData().get(position).trim().equals(""))
                {
                    BaseDialog dialog = showPromptDialog("删除提示：", "确定要删除该张图片吗？", "我再想想", "是的", true,
                                        new View.OnClickListener()
                                        {
                                            public void onClick(View view)
                                            {
                                                twzxPictureAdapter.getData().remove(position);
                                                setOnNewImgPathListener(new LinkedList());
                                            }
                                        }
                                        , null, null);
                }
                return true;
            }
        });
    }

    protected void initDatas()
    {
        twzxPresenter = new TwzxPresenter();
        twzxPresenter.attachContextAndViewLayer(this,this);
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
        switch(view.getId())
        {
            case R.id.twzx_tjwt_btn:break;
        }
    }

    @Override
    protected void onDestroy()
    {
        twzxPresenter.detachContextAndViewLayout();
        super.onDestroy();
    }

    @Override
    public void setOnNewImgPathListener(LinkedList<String> bitmapPaths)
    {
        for(String imgPath : bitmapPaths)
            twzxPictureAdapter.getData().add(0,imgPath);
        while(twzxPictureAdapter.getData().size() > maxPictureNum)
            twzxPictureAdapter.getData().remove((int)maxPictureNum);
        if(!twzxPictureAdapter.getData().contains("") && twzxPictureAdapter.getData().size() < maxPictureNum)
            twzxPictureAdapter.getData().add("");
        twzxPictureAdapter.notifyDataSetChanged();
    }
}
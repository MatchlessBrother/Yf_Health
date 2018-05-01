package ufhealth.integratedmachine.client.ui.zxzx.view;

import java.util.List;
import android.view.View;
import java.util.ArrayList;
import java.util.LinkedList;
import android.widget.Button;
import android.graphics.Color;
import android.content.Intent;
import android.widget.TextView;
import android.util.TypedValue;
import ufhealth.integratedmachine.client.R;
import com.hwangjr.rxbus.annotation.Subscribe;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.GridLayoutManager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import ufhealth.integratedmachine.client.base.BasePhotoAct;
import com.yuan.devlibrary._11___Widget.promptBox.BaseDialog;
import ufhealth.integratedmachine.client.widget.CountEditText;
import ufhealth.integratedmachine.client.ui.zxzx.view_v.TwzxAct_V;
import ufhealth.integratedmachine.client.ui.zxzx.presenter.TwzxPresenter;
import ufhealth.integratedmachine.client.adapter.zxzx.TwzxPictureAdapter;

public class TwzxAct extends BasePhotoAct implements TwzxAct_V,View.OnClickListener
{
    public String TYPE;
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
        twzxNameTv = rootView.findViewById(R.id.twzx_name_tv);
        twzxTjwtBtn = rootView.findViewById(R.id.twzx_tjwt_btn);
        twzxContentEt = rootView.findViewById(R.id.twzx_content_et);
        twzxRecyclerview = rootView.findViewById(R.id.twzx_recyclerview);

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
                    showSelectPhotoDialog(26f, TypedValue.COMPLEX_UNIT_SP,Color.argb(255,0,147,221), false,
                            maxPictureNum + 1 - twzxPictureAdapter.getData().size(),false,
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
        TYPE = getIntent().getStringExtra("type").trim();
        twzxPresenter.attachContextAndViewLayer(this,this);
    }

    protected void initLogic()
    {

    }

    public void onClick(View view)
    {
        super.onClick(view);
        switch(view.getId())
        {
            case R.id.twzx_tjwt_btn:
            {
               /*if(TYPE.equals(ZxzxAct.KSZX))//快速咨询
                {
                    twzxPresenter.uploadDatas(null,null);break;
                }
                else if(TYPE.equals(ZxzxAct.BGJD))//报告解读
                {
                    twzxPresenter.uploadDatas(null,null);break;
                }*/
                //临时跳转代码，正确的逻辑是上面的代码
                Intent intent = new Intent(this,ChooseMultiDoctorAct.class);
                ArrayList lsArrayList = new ArrayList();
                intent.putExtra("type",ZxzxAct.KSZX);
                lsArrayList.addAll(twzxPictureAdapter.getData());
                intent.putStringArrayListExtra("imgsPath",lsArrayList);
                intent.putExtra("questions",twzxContentEt.getText().trim());
                startActivity(intent);
            }
        }
    }

    public void commitImgsSuccess()
    {
     /*   if(TYPE.equals(ZxzxAct.KSZX))//快速咨询
        {
            //保存图片网络地址到本地以便后面上传，并跳转选择医生界面
            Intent intent = new Intent(this,ChooseMultiDoctorAct.class);
            intent.putExtra("type",ZxzxAct.KSZX);
            startActivity(intent);
        }
        else if(TYPE.equals(ZxzxAct.BGJD))//报告解读
        {
            //保存图片网络地址到本地以便后面上传，并跳转选择医生界面
            Intent intent = new Intent(this,ChooseMultiDoctorAct.class);
            intent.putExtra("type",ZxzxAct.BGJD);
            startActivity(intent);
        }*/
    }

    protected void onDestroy()
    {
        twzxPresenter.detachContextAndViewLayout();
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
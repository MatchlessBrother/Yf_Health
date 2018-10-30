package ufhealth.integratedmachine.client.ui.bjcz.activity.view;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import android.view.View;
import java.util.ArrayList;
import java.util.LinkedList;
import android.widget.Button;
import android.content.Intent;
import android.graphics.Color;
import android.widget.EditText;
import android.util.TypedValue;
import java.util.IdentityHashMap;
import ufhealth.integratedmachine.client.R;
import android.support.v7.widget.RecyclerView;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yuan.devlibrary._12_______Utils.PromptBoxUtils;
import ufhealth.integratedmachine.client.base.BasePhotoAct;
import ufhealth.integratedmachine.client.bean.bjcz.BjczUploadImgInfo;
import ufhealth.integratedmachine.client.adapter.bjcz.BjczImgAdapter;
import ufhealth.integratedmachine.client.ui.bjcz.activity.view_v.BjczAct_V;
import ufhealth.integratedmachine.client.ui.bjcz.activity.presenter.BjczPresenter;

public class BjczAct extends BasePhotoAct implements BjczAct_V,View.OnClickListener
{
    private String mAlarmId;
    private Button mBjczBtn;
    private EditText mBjczEt;
    private BjczPresenter mBjczPresenter;
    private RecyclerView mBjczRecyclerview;
    private BjczImgAdapter mBjczImgAdapter;

    protected int setLayoutResID()
    {
        return R.layout.activity_bjcz;
    }

    protected void initWidgets(View rootView)
    {
        super.initWidgets(rootView);
        setTitleContent("报警处置");
        mAlarmId = getIntent().getStringExtra("alarmid");
        mBjczEt = (EditText)rootView.findViewById(R.id.bjcz_et);
        mBjczBtn = (Button)rootView.findViewById(R.id.bjcz_btn);
        List dataList = new ArrayList<String>();dataList.add("");
        mBjczImgAdapter =  new BjczImgAdapter(mActivity,dataList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mActivity,3);
        mBjczRecyclerview = (RecyclerView)rootView.findViewById(R.id.bjcz_recyclerview);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mBjczRecyclerview.setLayoutManager(gridLayoutManager);
        mBjczRecyclerview.setNestedScrollingEnabled(false);
        mBjczRecyclerview.setFocusableInTouchMode(false);
        mBjczRecyclerview.setAdapter(mBjczImgAdapter);
    }

    protected void initDatas()
    {
        mBjczPresenter = new BjczPresenter();
        bindBaseMvpPresenter(mBjczPresenter);
    }

    protected void initLogic()
    {
        mBjczBtn.setOnClickListener(this);
        mBjczImgAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener()
        {
            public void onItemClick(BaseQuickAdapter adapter, View view, int position)
            {
                if(null != mBjczImgAdapter.getData().get(position) && "".equals(mBjczImgAdapter.getData().get(position).trim()))
                {
                    showSelectPicturesDialog(30f, TypedValue.COMPLEX_UNIT_SP,R.color.colorPrimary);
                }
                else if(null != mBjczImgAdapter.getData().get(position) && !"".equals(mBjczImgAdapter.getData().get(position).trim()))
                {
                    Intent intent = new Intent(BjczAct.this,BjczPreviewPhotoAct.class);
                    intent.putExtra("imgPath",mBjczImgAdapter.getData().get(position).trim());
                    startActivity(intent);
                }
            }
        });

        mBjczImgAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener()
        {
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, final int position)
            {
                if(null != mBjczImgAdapter.getData().get(position) && "".equals(mBjczImgAdapter.getData().get(position).trim()))
                {
                    showSelectPicturesDialog(30f, TypedValue.COMPLEX_UNIT_SP,R.color.colorPrimary);
                }
                else if(null != mBjczImgAdapter.getData().get(position) && !"".equals(mBjczImgAdapter.getData().get(position).trim()))
                {
                     PromptBoxUtils.showPromptDialog(BjczAct.this,"删除提示：",Color.parseColor("#FF333333"),30,TypedValue.COMPLEX_UNIT_SP, new ColorDrawable(0xffffffff), View.VISIBLE,
                             "确定要删除这张照片吗？",Color.parseColor("#FF666666"), 26, TypedValue.COMPLEX_UNIT_SP, new ColorDrawable(0xffffffff),
                             "我想想", Color.parseColor("#FF3B6DB9"),28, TypedValue.COMPLEX_UNIT_SP, new ColorDrawable(0xffffffff), View.VISIBLE,
                             "是的！", Color.parseColor("#FFFF0000"), 28, TypedValue.COMPLEX_UNIT_SP, new ColorDrawable(0xffffffff), View.VISIBLE,
                             true, new View.OnClickListener()
                             {
                                 public void onClick(View v)
                                 {
                                     mBjczImgAdapter.remove(position);
                                     if(mBjczImgAdapter.getData().size() < 9)
                                     {
                                         for(int index = mBjczImgAdapter.getData().size() - 1;index >= 0;index--)
                                         {
                                             if(null != mBjczImgAdapter.getData().get(index) && "".equals(mBjczImgAdapter.getData().get(index).trim()))
                                             {
                                                 break;
                                             }
                                             if(index == 0)
                                             {
                                                 mBjczImgAdapter.getData().add("");
                                             }
                                         }
                                     }
                                     mBjczImgAdapter.notifyDataSetChanged();
                                 }
                             },null,null);
                }
                return false;
            }
        });
    }

    public void onClick(View view)
    {
        super.onClick(view);
        switch (view.getId())
        {
            case R.id.bjcz_btn:
            {
                if(mBjczImgAdapter.getData().size() > 0 && !"".equals(mBjczImgAdapter.getData().get(0).trim()))
                    mBjczPresenter.disposeAlarmImage(mBjczImgAdapter.getData());
                else
                    mBjczPresenter.disposeAlarm(mAlarmId,mBjczEt.getText().toString().trim(),new HashMap<String, String>());
                break;
            }
        }
    }

    public void failOfUploadDatas()
    {
        showToast("上传数据失败，请稍后再试！");

    }

    public void successOfUploadDatas()
    {
        showToast("上传数据成功！");
        finish();
    }

    public void failOfUploadImgDatas()
    {
        showToast("上传图片失败，请稍后重试！");

    }

    protected void setOnNewImgPathListener(LinkedList<String> bitmapPaths)
    {
        if(null != bitmapPaths && bitmapPaths.size() > 0 && null != bitmapPaths.get(0) && !"".equals(bitmapPaths.get(0).trim()))
        {
            mBjczImgAdapter.addData(0,bitmapPaths.get(0).trim());
            if(mBjczImgAdapter.getData().size() > 9)
            {
                for(int index = mBjczImgAdapter.getData().size() - 1;index >= 0;index--)
                {
                    if(null != mBjczImgAdapter.getData().get(index) && "".equals(mBjczImgAdapter.getData().get(index).trim()))
                    {
                        mBjczImgAdapter.getData().remove(index);
                        break;
                    }
                }
            }
            mBjczImgAdapter.notifyDataSetChanged();
        }
    }

    public void successOfUploadImgDatas(List<BjczUploadImgInfo> bjczUploadImgInfos)
    {
        Map<String,String> map = new IdentityHashMap<>();
        for(int index = 0;index < bjczUploadImgInfos.size();index++)
        {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("imagePaths");
            map.put(stringBuffer.toString(),null != bjczUploadImgInfos.get(index).getPath() ? bjczUploadImgInfos.get(index).getPath().trim() : "");

        }
        mBjczPresenter.disposeAlarm(mAlarmId,mBjczEt.getText().toString().trim(),map);
    }
}
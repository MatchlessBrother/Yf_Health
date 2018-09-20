package ufhealth.integratedmachine.client.ui.main.view;

import android.view.View;
import java.util.ArrayList;
import java.util.LinkedList;
import android.widget.Button;
import android.graphics.Color;
import android.util.TypedValue;
import ufhealth.integratedmachine.client.R;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.GridLayoutManager;
import ufhealth.integratedmachine.client.base.BasePhotoAct;
import ufhealth.integratedmachine.client.adapter.PicturesAdapter;

public class PicturesAct extends BasePhotoAct implements View.OnClickListener
{
    private Button btn;
    private PicturesAdapter adapter;
    private RecyclerView recyclerview;

    protected int setLayoutResID()
    {
        return R.layout.activity_picture;
    }

    protected void initWidgets(View rootView)
    {
        super.initWidgets(rootView);
        btn = (Button) findViewById(R.id.btn);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
    }

    protected void initDatas()
    {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(gridLayoutManager);
        adapter = new PicturesAdapter(this,new ArrayList<String>());
        recyclerview.setAdapter(adapter);
    }

    protected void initLogic()
    {
        btn.setOnClickListener(this);
    }

    public void onClick(View view)
    {
        super.onClick(view);
        switch (view.getId())
        {
            case R.id.btn:
            {
                showSelectPicturesDialog(18f, TypedValue.COMPLEX_UNIT_DIP, Color.argb(255,255,0,0));
                break;
            }
        }
    }

    protected void setOnNewImgPathListener(LinkedList<String> bitmapPaths)
    {
        adapter.setNewData(bitmapPaths);
    }
}
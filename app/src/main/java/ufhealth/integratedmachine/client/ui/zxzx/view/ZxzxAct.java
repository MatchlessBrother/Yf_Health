package ufhealth.integratedmachine.client.ui.zxzx.view;

import java.util.List;
import android.view.View;
import java.util.ArrayList;
import android.view.Gravity;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import ufhealth.integratedmachine.client.R;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import com.hwangjr.rxbus.annotation.Subscribe;
import android.support.v7.widget.GridLayoutManager;
import ufhealth.integratedmachine.client.base.BaseAct;
import com.chad.library.adapter.base.BaseQuickAdapter;
import ufhealth.integratedmachine.client.bean.zxzx.HotDepartment;
import ufhealth.integratedmachine.client.ui.zxzx.view_v.ZxzxAct_V;
import ufhealth.integratedmachine.client.ui.zxzx.presenter.ZxzxPresenter;
import ufhealth.integratedmachine.client.adapter.zxzx.ZxzxHotDepartmentAdapter;

public class ZxzxAct extends BaseAct implements ZxzxAct_V,View.OnClickListener
{
    private View noDataView;
    private View noWifiView;
    private TextView zxzxRmksAll;
    private Button zxzxSearchBtn;
    private EditText zxzxSearchEt;
    private LinearLayout zxzxSearchAll;
    private RelativeLayout zxzxSpzxAll;
    private RelativeLayout zxzxMyyzAll;
    private RelativeLayout zxzxYyzxAll;
    private RelativeLayout zxzxKszxAll;
    private RelativeLayout zxzxBgjdAll;
    private ZxzxPresenter zxzxPresenter;
    private DrawerLayout zxzxDrawerlayout;
    private RecyclerView zxzxHotDepartmentRecyclerView;
    private ZxzxHotDepartmentAdapter zxzxHotDepartmentAdapter;
    public      static      final     String    TWZX = "twzx";
    public      static      final     String    SPZX = "spzx";
    public      static      final     String    YYZX = "ypzx";
    public      static      final     String    MYYZ = "myyz";
    public      static      final     String    KSZX = "kszx";
    public      static      final     String    BGJD = "bgjd";
    public      static      final     String    RMKS = "rmks";
    public    static    final    String    SEARCH  = "search";

    protected int setLayoutResID()
    {
        return R.layout.activity_zxzx;
    }

    protected void initWidgets(View rootView)
    {
        super.initWidgets(rootView);
        setTitleContent("在线咨询");
        noDataView = LayoutInflater.from(this).inflate(R.layout.recyclerview_nodata_small,null);
        noWifiView = LayoutInflater.from(this).inflate(R.layout.recyclerview_nowifi_small,null);
        zxzxHotDepartmentRecyclerView = rootView.findViewById(R.id.zxzx_hotdepartment_recyclerview);
        zxzxDrawerlayout = rootView.findViewById(R.id.zxzx_drawerlayout);
        zxzxSearchBtn = rootView.findViewById(R.id.zxzx_search_btn);
        zxzxSearchAll = rootView.findViewById(R.id.zxzx_search_all);
        zxzxSearchEt = rootView.findViewById(R.id.zxzx_search_et);
        zxzxSpzxAll = rootView.findViewById(R.id.zxzx_spzx_all);
        zxzxMyyzAll = rootView.findViewById(R.id.zxzx_myyz_all);
        zxzxYyzxAll = rootView.findViewById(R.id.zxzx_yyzx_all);
        zxzxKszxAll = rootView.findViewById(R.id.zxzx_kszx_all);
        zxzxBgjdAll = rootView.findViewById(R.id.zxzx_bgjd_all);
        zxzxRmksAll = rootView.findViewById(R.id.zxzx_rmks_all);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        zxzxHotDepartmentRecyclerView.setLayoutManager(gridLayoutManager);
        zxzxHotDepartmentAdapter = new ZxzxHotDepartmentAdapter(this,new ArrayList<HotDepartment>());
        zxzxHotDepartmentRecyclerView.setAdapter(zxzxHotDepartmentAdapter);
        zxzxHotDepartmentAdapter.setEmptyView(noDataView);

        noDataView.findViewById(R.id.recyclerview_refreshbtn).setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                zxzxPresenter.getHotDepartments();
            }
        });

        noWifiView.findViewById(R.id.recyclerview_refreshbtn).setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                zxzxPresenter.getHotDepartments();
            }
        });

        zxzxHotDepartmentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener()
        {
            public void onItemClick(BaseQuickAdapter adapter, View view, int position)
            {
                if(zxzxDrawerlayout.isShown())
                    zxzxDrawerlayout.closeDrawers();
                Intent intent = new Intent(ZxzxAct.this,ChooseDoctorAct.class);
                intent.putExtra(RMKS,((HotDepartment)adapter.getData().get(position)));
                intent.putExtra("type",RMKS);
                startActivity(intent);
            }
        });

        zxzxSpzxAll.setOnClickListener(this);
        zxzxYyzxAll.setOnClickListener(this);
        zxzxKszxAll.setOnClickListener(this);
        zxzxMyyzAll.setOnClickListener(this);
        zxzxBgjdAll.setOnClickListener(this);
        zxzxRmksAll.setOnClickListener(this);
        zxzxSearchBtn.setOnClickListener(this);
    }

    protected void initDatas()
    {
        zxzxPresenter = new ZxzxPresenter();
        zxzxPresenter.attachContextAndViewLayer(this,this);
    }

    protected void initLogic()
    {
        zxzxPresenter.getHotDepartments();

    }

    public void onClick(View view)
    {
        super.onClick(view);
        switch (view.getId())
        {
            case R.id.zxzx_search_btn:
            {
                Intent intent = new Intent(this,ChooseDoctorAct.class);
                intent.putExtra(SEARCH,zxzxSearchEt.getText().toString().trim());
                intent.putExtra("type",SEARCH);
                startActivity(intent);
                break;
            }

            case R.id.zxzx_spzx_all:
            {
                Intent intent = new Intent(this,ChooseDoctorAct.class);
                intent.putExtra("type",SPZX);
                startActivity(intent);
                break;
            }
            case R.id.zxzx_yyzx_all:
            {
                Intent intent = new Intent(this,ChooseDoctorAct.class);
                intent.putExtra("type",YYZX);
                startActivity(intent);
                break;
            }
            case R.id.zxzx_myyz_all:
            {
                Intent intent = new Intent(this,ChooseDoctorAct.class);
                intent.putExtra("type",MYYZ);
                startActivity(intent);
                break;
            }
            case R.id.zxzx_kszx_all:
            {
                Intent intent = new Intent(this,TwzxAct.class);
                intent.putExtra("type",KSZX);
                startActivity(intent);
                break;
            }

            case R.id.zxzx_bgjd_all:
            {
                Intent intent = new Intent(this,TwzxAct.class);
                intent.putExtra("type",BGJD);
                startActivity(intent);
                break;
            }
            case R.id.zxzx_rmks_all:
            {
                zxzxDrawerlayout.openDrawer(Gravity.RIGHT);break;
            }
        }
    }

    protected void onDestroy()
    {
        zxzxPresenter.detachContextAndViewLayout();
        super.onDestroy();
    }

    public void setHotDepartmentsException()
    {
        zxzxHotDepartmentAdapter.setEmptyView(noWifiView);

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

    public void setHotDepartments(List<HotDepartment> hotDepartments)
    {
        zxzxHotDepartmentAdapter.setNewData(hotDepartments);
        zxzxHotDepartmentAdapter.setEmptyView(noDataView);
    }
}
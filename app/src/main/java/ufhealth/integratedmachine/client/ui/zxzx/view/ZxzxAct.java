package ufhealth.integratedmachine.client.ui.zxzx.view;

import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import ufhealth.integratedmachine.client.R;
import com.hwangjr.rxbus.annotation.Subscribe;
import android.support.v4.widget.DrawerLayout;
import ufhealth.integratedmachine.client.base.BaseAct;
import ufhealth.integratedmachine.client.ui.zxzx.view_v.ZxzxAct_V;

public class ZxzxAct extends BaseAct implements ZxzxAct_V,View.OnClickListener
{
    private TextView zxzxRmksAll;
    private EditText zxzxSearchEt;
    private LinearLayout zxzxSearchAll;
    private RelativeLayout zxzxSpzxAll;
    private RelativeLayout zxzxMyyzAll;
    private RelativeLayout zxzxYyzxAll;
    private RelativeLayout zxzxKszxAll;
    private RelativeLayout zxzxBgjdAll;
    private DrawerLayout zxzxDrawerlayout;

    protected int setLayoutResID()
    {
        return R.layout.activity_zxzx;
    }

    protected void initWidgets(View rootView)
    {
        super.initWidgets(rootView);
        setTitleContent("在线咨询");
        zxzxDrawerlayout = rootView.findViewById(R.id.zxzx_drawerlayout);
        zxzxSearchAll = rootView.findViewById(R.id.zxzx_search_all);
        zxzxSearchEt = rootView.findViewById(R.id.zxzx_search_et);
        zxzxSpzxAll = rootView.findViewById(R.id.zxzx_spzx_all);
        zxzxMyyzAll = rootView.findViewById(R.id.zxzx_myyz_all);
        zxzxYyzxAll = rootView.findViewById(R.id.zxzx_yyzx_all);
        zxzxKszxAll = rootView.findViewById(R.id.zxzx_kszx_all);
        zxzxBgjdAll = rootView.findViewById(R.id.zxzx_bgjd_all);
        zxzxRmksAll = rootView.findViewById(R.id.zxzx_rmks_all);
        zxzxSpzxAll.setOnClickListener(this);
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
        switch (view.getId())
        {
            case R.id.zxzx_spzx_all:startActivity(new Intent(this,TwzxAct.class));break;
        }
    }
}
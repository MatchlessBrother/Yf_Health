package ufhealth.integratedmachine.client.ui.main.fragment.view;

import android.view.View;
import android.graphics.Color;
import android.widget.TextView;
import ufhealth.integratedmachine.client.R;
import android.graphics.drawable.ColorDrawable;
import ufhealth.integratedmachine.client.base.BaseFrag;
import com.yuan.devlibrary._11___Widget.promptBox.BasePopupWindow;
import ufhealth.integratedmachine.client.ui.main.fragment.presenter.MainHzPresenter;

public class MainHzFrag extends BaseFrag implements View.OnClickListener
{
    private TextView mMainhzfragBj;
    private TextView mMainhzfragYj;
    private TextView mMainhzfragSj;
    private TextView mMainhzfragSwcz;
    private TextView mMainhzfragTscz;
    private MainHzPresenter mMainHzPresenter;

    protected int setLayoutResID()
    {
        return R.layout.fragment_main_hz;
    }

    protected void initWidgets(View rootView)
    {
        super.initWidgets(rootView);
        setTitleBack(R.mipmap.usericon);
        mMainhzfragBj = (TextView)rootView.findViewById(R.id.mainhzfrag_bj);
        mMainhzfragYj = (TextView)rootView.findViewById(R.id.mainhzfrag_yj);
        mMainhzfragSj = (TextView)rootView.findViewById(R.id.mainhzfrag_sj);
        mMainhzfragSwcz = (TextView)rootView.findViewById(R.id.mainhzfrag_swcz);
        mMainhzfragTscz = (TextView)rootView.findViewById(R.id.mainhzfrag_tscz);
    }

    protected void initDatas()
    {
        mMainHzPresenter = new MainHzPresenter();
        bindBaseMvpPresenter(mMainHzPresenter);
    }

    protected void initLogic()
    {
        mMainhzfragBj.setOnClickListener(this);
        mMainhzfragYj.setOnClickListener(this);
        mMainhzfragSj.setOnClickListener(this);
        mMainhzfragSwcz.setOnClickListener(this);
        mMainhzfragTscz.setOnClickListener(this);
    }

    public void onClick(View view)
    {
        super.onClick(view);
        switch (view.getId())
        {
            case R.id.mainhzfrag_bj:
            {
                break;
            }
            case R.id.mainhzfrag_yj:
            {
                break;
            }
            case R.id.mainhzfrag_sj:
            {
                break;
            }
            case R.id.mainhzfrag_swcz:
            {
                break;
            }
            case R.id.mainhzfrag_tscz:
            {
                break;
            }
        }
    }

    protected void onTitleBackClick()
    {
        BasePopupWindow basePopupWindow = new BasePopupWindow(mActivity);
        basePopupWindow.setContentView(null);
        basePopupWindow.setOutsideTouchable(true);
        basePopupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("CC000000")));
        if(isUseDefaultTitleLine())
            basePopupWindow.showAsDropDown(mTitleBackBtn,12,6);
    }
}
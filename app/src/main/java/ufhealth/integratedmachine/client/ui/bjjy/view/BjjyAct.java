package ufhealth.integratedmachine.client.ui.bjjy.view;

import android.view.View;
import android.view.KeyEvent;
import android.graphics.Color;
import com.just.agentweb.AgentWeb;
import android.webkit.WebSettings;
import android.widget.LinearLayout;
import android.view.LayoutInflater;
import com.just.agentweb.DefaultWebClient;
import ufhealth.integratedmachine.client.R;
import com.hwangjr.rxbus.annotation.Subscribe;
import ufhealth.integratedmachine.client.base.BaseAct;
import ufhealth.integratedmachine.client.ui.bjjy.view_v.BjjyAct_V;

public class BjjyAct extends BaseAct implements BjjyAct_V,View.OnClickListener
{
    private AgentWeb agentWeb;
    private LinearLayout bjjyAllLl;

    protected int setLayoutResID()
    {
        return R.layout.activity_bjjy;
    }

    @Override
    protected void onResume()
    {
        agentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    protected void initWidgets(View rootView)
    {
        super.initWidgets(rootView);
        setTitleContent("便捷就医");
        bjjyAllLl = rootView.findViewById(R.id.bjjy_all_ll);
        agentWeb = AgentWeb.with(this).setAgentWebParent(bjjyAllLl, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator(Color.argb(255,68,240,0),2)
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.DERECT)
                .setMainFrameErrorView(LayoutInflater.from(this).inflate(R.layout.webview_error,null))
                .createAgentWeb()
                .ready()
                .go("http://192.168.199.167/jkxw/web/index.html");
        agentWeb.getAgentWebSettings().getWebSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
    }

    protected void initDatas()
    {

    }

    protected void initLogic()
    {

    }

    @Override
    protected void onPause()
    {
        agentWeb.getWebLifeCycle().onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy()
    {
        agentWeb.getWebLifeCycle().onDestroy();
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (agentWeb.handleKeyEvent(keyCode, event))
            return true;
        return super.onKeyDown(keyCode, event);
    }

    public void onClick(View view)
    {
        super.onClick(view);
    }
}
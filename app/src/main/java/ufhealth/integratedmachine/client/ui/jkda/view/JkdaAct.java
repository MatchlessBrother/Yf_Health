package ufhealth.integratedmachine.client.ui.jkda.view;

import android.view.View;
import android.view.KeyEvent;
import android.graphics.Color;
import com.just.agentweb.AgentWeb;
import android.webkit.WebSettings;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.annotation.SuppressLint;
import com.just.agentweb.DefaultWebClient;
import ufhealth.integratedmachine.client.R;
import com.hwangjr.rxbus.annotation.Subscribe;
import ufhealth.integratedmachine.client.base.BaseAct;
import ufhealth.integratedmachine.client.ProvideActionForWebiew;
import ufhealth.integratedmachine.client.ui.jkda.view_v.JkdaAct_V;
import ufhealth.integratedmachine.client.ui.jkda.presenter.JkdaPresenter;

@SuppressLint("SetJavaScriptEnabled")
public class JkdaAct extends BaseAct implements JkdaAct_V,View.OnClickListener
{
    private AgentWeb agentWeb;
    private LinearLayout jkdaAllLl;
    private JkdaPresenter jkdaPresenter;

    protected int setLayoutResID()
    {
        return R.layout.activity_jkda;
    }

    @SuppressLint("JavascriptInterface")
    protected void initWidgets(View rootView)
    {
        super.initWidgets(rootView);
        setTitleContent("健康档案");
        jkdaAllLl = (LinearLayout)rootView.findViewById(R.id.jkda_all_ll);
        agentWeb = AgentWeb.with(this).setAgentWebParent(jkdaAllLl, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator(Color.argb(255,68,240,0),2)
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.DERECT)
                .setMainFrameErrorView(LayoutInflater.from(this).inflate(R.layout.webview_error,null))
                .createAgentWeb()
                .ready()
                .go("file:///android_asset/web/health_records/index.html");
        agentWeb.getAgentWebSettings().getWebSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        agentWeb.getWebCreator().getWebView().addJavascriptInterface(new ProvideActionForWebiew(this), "androidjs");
    }

    protected void initDatas()
    {
        jkdaPresenter = new JkdaPresenter();
        jkdaPresenter.attachContextAndViewLayer(this,this);
    }

    protected void initLogic()
    {

    }

    protected void onResume()
    {
        agentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    protected void onPause()
    {
        agentWeb.getWebLifeCycle().onPause();
        super.onPause();
    }

    protected void onDestroy()
    {
        jkdaPresenter.detachContextAndViewLayout();
        agentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }

    public void onClick(View view)
    {
        super.onClick(view);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (agentWeb.handleKeyEvent(keyCode, event))
            return true;
        return super.onKeyDown(keyCode, event);
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
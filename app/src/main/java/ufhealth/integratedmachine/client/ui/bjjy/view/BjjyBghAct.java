package ufhealth.integratedmachine.client.ui.bjjy.view;

import android.view.View;
import android.view.KeyEvent;
import android.graphics.Color;
import android.webkit.WebSettings;
import com.just.agentweb.AgentWeb;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.annotation.SuppressLint;
import com.just.agentweb.DefaultWebClient;
import ufhealth.integratedmachine.client.R;
import com.hwangjr.rxbus.annotation.Subscribe;
import ufhealth.integratedmachine.client.base.BaseAct;
import ufhealth.integratedmachine.client.ProvideActionForWebiew;
import ufhealth.integratedmachine.client.ui.bjjy.view_v.BjjyBghAct_V;
import ufhealth.integratedmachine.client.ui.bjjy.presenter.BjjyBghPresenter;

@SuppressLint("SetJavaScriptEnabled")
public class BjjyBghAct extends BaseAct implements BjjyBghAct_V,View.OnClickListener
{
    private AgentWeb agentWeb;
    private LinearLayout bjjybghAllLl;
    private BjjyBghPresenter bjjyBghPresenter;

    protected int setLayoutResID()
    {
        return R.layout.activity_bjjybgh;
    }

    @SuppressLint("JavascriptInterface")
    protected void initWidgets(View rootView)
    {
        super.initWidgets(rootView);
        setTitleContent("帮挂号");
        bjjybghAllLl = rootView.findViewById(R.id.bjjybgh_all_ll);
        agentWeb = AgentWeb.with(this).setAgentWebParent(bjjybghAllLl, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator(Color.argb(255,68,240,0),2)
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.DERECT)
                .setMainFrameErrorView(LayoutInflater.from(this).inflate(R.layout.webview_error,null))
                .createAgentWeb()
                .ready()
                .go("file:///android_asset/web/yygh/index.html");
        agentWeb.getAgentWebSettings().getWebSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        agentWeb.getWebCreator().getWebView().addJavascriptInterface(new ProvideActionForWebiew(this), "androidjs");
    }

    protected void initDatas()
    {
        bjjyBghPresenter = new BjjyBghPresenter();
        bjjyBghPresenter.attachContextAndViewLayer(this,this);
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
        bjjyBghPresenter.detachContextAndViewLayout();
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
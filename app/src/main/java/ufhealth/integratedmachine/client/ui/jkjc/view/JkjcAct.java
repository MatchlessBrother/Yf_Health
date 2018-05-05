package ufhealth.integratedmachine.client.ui.jkjc.view;

import android.view.View;
import android.graphics.Color;
import android.webkit.WebSettings;
import com.just.agentweb.AgentWeb;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.content.res.AssetManager;
import com.just.agentweb.DefaultWebClient;
import ufhealth.integratedmachine.client.R;
import com.hwangjr.rxbus.annotation.Subscribe;
import ufhealth.integratedmachine.client.base.BaseAct;
import ufhealth.integratedmachine.client.ProvideActionForWebiew;
import ufhealth.integratedmachine.client.ui.jkjc.view_v.JkjcAct_V;
import ufhealth.integratedmachine.client.ui.jkjc.presenter.JkjcPresenter;

public class JkjcAct extends BaseAct implements JkjcAct_V,View.OnClickListener
{
    private AgentWeb agentWeb;
    private LinearLayout jkjcAllLl;
    private JkjcPresenter jkjcPresenter;
    private AssetManager assetManager;

    protected int setLayoutResID()
    {
        return R.layout.activity_jkjc;
    }

    protected void initWidgets(View rootView)
    {
        super.initWidgets(rootView);
        setTitleContent("健康检测");
        jkjcAllLl = (LinearLayout)rootView.findViewById(R.id.jkjc_all_ll);
        agentWeb = AgentWeb.with(this).setAgentWebParent(jkjcAllLl, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator(Color.argb(255,68,240,0),2)
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.DERECT)
                .setMainFrameErrorView(LayoutInflater.from(this).inflate(R.layout.webview_error,null))
                .createAgentWeb()
                .ready()
                .go("file:///android_asset/web/jkjc/index.html");
        //.go("http://f206p96248.imwork.net:13209/web/member/order-bjjy-list.html");
        agentWeb.getAgentWebSettings().getWebSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        agentWeb.getWebCreator().getWebView().addJavascriptInterface(new ProvideActionForWebiew(this), "androidjs");
    }

    protected void initDatas()
    {
        jkjcPresenter = new JkjcPresenter();
        jkjcPresenter.attachContextAndViewLayer(this,this);
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
        jkjcPresenter.detachContextAndViewLayout();
        agentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }

    public void onClick(View view)
    {
        super.onClick(view);

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
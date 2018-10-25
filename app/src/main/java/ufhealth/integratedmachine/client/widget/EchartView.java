package ufhealth.integratedmachine.client.widget;

import android.webkit.WebView;
import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import com.github.abel533.echarts.json.GsonOption;

public class EchartView extends WebView
{
    private static final String TAG = EchartView.class.getSimpleName();

    public EchartView(Context context)
    {
        this(context, null);
    }

    public EchartView(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public EchartView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init()
    {
        WebSettings webSettings = getSettings();
        webSettings.setSupportZoom(false);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        loadUrl("file:///android_asset/echarts.html");
    }

    /**
     * java调用js的loadEcharts方法刷新echart
     * 不能在第一时间就用此方法来显示图表，因为第一时间html的标签还未加载完成，不能获取到标签值
     * @param option
     */
    public void refreshEchartsViewWithOption(GsonOption option)
    {
        if (option == null)return;
        String optionString = option.toString();
        String call = "javascript:loadEcharts('" + optionString + "')";
        loadUrl(call);
    }
}
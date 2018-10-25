package ufhealth.integratedmachine.client.util;

import com.github.abel533.echarts.series.Line;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.axis.CategoryAxis;

public class EchartViewUtils
{
    public static GsonOption getLineChartOptions(Object[] xAxis, Object[] yAxis)
    {
        GsonOption option = new GsonOption();
        option.grid().x(46).y(20).x2(12).y2(26).borderWidth(0);
        option.tooltip().trigger(Trigger.axis);

        ValueAxis valueAxis = new ValueAxis();
        option.yAxis(valueAxis);

        CategoryAxis categorxAxis = new CategoryAxis();
        categorxAxis.axisLine().onZero(false);
        categorxAxis.boundaryGap(true);
        categorxAxis.data(xAxis);
        option.xAxis(categorxAxis);

        Line line = new Line();
        line.smooth(false).name("销量").data(yAxis).itemStyle().normal().lineStyle().shadowColor("rgba(0,0,0,0.4)");
        option.series(line);
        return option;
    }
}
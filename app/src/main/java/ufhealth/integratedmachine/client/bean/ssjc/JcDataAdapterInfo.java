package ufhealth.integratedmachine.client.bean.ssjc;

import java.util.List;
import android.widget.TextView;
import android.graphics.drawable.GradientDrawable;

public class JcDataAdapterInfo
{
    private String id;
    private String wh;
    private String wz;
    private String qy;
    private String ssz;
    private String lsbj;
    private String sjtx;
    private String jczl;
    private String bgColorCode;
    private String sjtxColorCode;
    private boolean isDivideLine;
    private GradientDrawable topBackgroundDrawable;
    private List<TextView> levelNameRuleDescriptions;
    private GradientDrawable bottomBackgroundDrawable;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWh() {
        return wh;
    }

    public void setWh(String wh) {
        this.wh = wh;
    }

    public String getWz() {
        return wz;
    }

    public void setWz(String wz) {
        this.wz = wz;
    }

    public String getQy() {
        return qy;
    }

    public void setQy(String qy) {
        this.qy = qy;
    }

    public String getSsz() {
        return ssz;
    }

    public void setSsz(String ssz) {
        this.ssz = ssz;
    }

    public String getLsbj() {
        return lsbj;
    }

    public void setLsbj(String lsbj) {
        this.lsbj = lsbj;
    }

    public String getSjtx() {
        return sjtx;
    }

    public void setSjtx(String sjtx) {
        this.sjtx = sjtx;
    }

    public String getJczl() {
        return jczl;
    }

    public void setJczl(String jczl) {
        this.jczl = jczl;
    }

    public String getBgColorCode() {
        return bgColorCode;
    }

    public void setBgColorCode(String bgColorCode) {
        this.bgColorCode = bgColorCode;
    }

    public String getSjtxColorCode() {
        return sjtxColorCode;
    }

    public void setSjtxColorCode(String sjtxColorCode) {
        this.sjtxColorCode = sjtxColorCode;
    }

    public boolean isDivideLine() {
        return isDivideLine;
    }

    public void setDivideLine(boolean divideLine) {
        isDivideLine = divideLine;
    }

    public GradientDrawable getTopBackgroundDrawable() {
        return topBackgroundDrawable;
    }

    public void setTopBackgroundDrawable(GradientDrawable topBackgroundDrawable) {
        this.topBackgroundDrawable = topBackgroundDrawable;
    }

    public List<TextView> getLevelNameRuleDescriptions() {
        return levelNameRuleDescriptions;
    }

    public void setLevelNameRuleDescriptions(List<TextView> levelNameRuleDescriptions) {
        this.levelNameRuleDescriptions = levelNameRuleDescriptions;
    }

    public GradientDrawable getBottomBackgroundDrawable() {
        return bottomBackgroundDrawable;
    }

    public void setBottomBackgroundDrawable(GradientDrawable bottomBackgroundDrawable) {
        this.bottomBackgroundDrawable = bottomBackgroundDrawable;
    }
}
package ufhealth.integratedmachine.client.ui.base;

public class BaseMvp_Presenter<V extends BaseMvp_View>
{
    /***************************MVP中抽象的V层*************************/
    private V mViewLayer;

    /*************************获取MVP中抽象的V层***********************/
    public V getViewLayer()
    {
        return mViewLayer;
    }

    /****************使MVP中的P层与MVP中抽象的V层断开关联**************/
    public void detachViewLayer()
    {
        mViewLayer = null;
    }

    /***************判定MVP中的P层与MVP中抽象的V层是否关联*************/
    public boolean isAttachViewLayer()
    {
        return mViewLayer != null;
    }

    /****************使MVP中的P层与MVP中抽象的V层建立关联**************/
    public void attachViewLayer(V viewLayer)
    {
        mViewLayer = viewLayer;
    }
}
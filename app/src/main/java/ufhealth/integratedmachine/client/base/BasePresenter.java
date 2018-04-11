package ufhealth.integratedmachine.client.base;

public class BasePresenter<V>
{
    public V mViewLayout;

    public V getViewLayout()
    {
        return mViewLayout;
    }

    public void setmViewLayout(V viewLayout)
    {
        mViewLayout = viewLayout;
    }
}
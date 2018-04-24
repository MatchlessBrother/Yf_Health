package ufhealth.integratedmachine.client.ui.base;

import com.yuan.devlibrary._11___Widget.promptBox.BaseProgressDialog;

public abstract class BaseMvp_LocalCallBack<V>
{
    private BaseProgressDialog mProgressDialog;
    private BaseMvp_Presenter mBaseMvpPresenter;

    /********数据请求开始*********/
    public void onStart()
    {
        if(mBaseMvpPresenter.isAttachContextAndViewLayer())
            mProgressDialog = mBaseMvpPresenter.getViewLayer().showLoadingDialog();
    }

    /**当请求数据结束时，无论请求结果是成功，失败或是抛出异常都*/
    /**会执行此方法给用户做处理通常隐藏“正在加载”的等待控件***/
    public void onFinish()
    {
        if(mBaseMvpPresenter.isAttachContextAndViewLayer())
            mBaseMvpPresenter.getViewLayer().dismissLoadingDialog(mProgressDialog);
    }

    /********数据请求失败*********/
    public void onFailure(String msg)
    {
        if(mBaseMvpPresenter.isAttachContextAndViewLayer())
            mBaseMvpPresenter.getViewLayer().showToast(msg);
    }

    /**请求数据失败,指在请求网络接口时，出现无法联网****/
    /**缺少权限,内存泄露等原因导致无法连接到请求数据源**/
    public void onError(String msg)
    {
        if(mBaseMvpPresenter.isAttachContextAndViewLayer())
            mBaseMvpPresenter.getViewLayer().showToast(msg);
    }

    /********数据请求成功*********/
    public abstract void onSuccess(V data);

    public BaseMvp_LocalCallBack(BaseMvp_Presenter baseMvpPresenter)
    {
        mBaseMvpPresenter = baseMvpPresenter;
    }
}
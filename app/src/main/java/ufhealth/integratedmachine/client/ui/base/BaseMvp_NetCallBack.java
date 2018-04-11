package ufhealth.integratedmachine.client.ui.base;

public interface BaseMvp_NetCallBack<V>
{
    /********数据请求开始*********/
    void onStart();

    /********数据请求成功*********/
    void onSuccess(V data);

    /********数据请求失败*********/
    void onFailure(String msg);

    /**请求数据失败,指在请求网络接口时，出现无法联网****/
    /**缺少权限,内存泄露等原因导致无法连接到请求数据源**/
    void onError(String msg);

    /**当请求数据结束时，无论请求结果是成功，失败或是抛出异常都*/
    /**会执行此方法给用户做处理通常隐藏“正在加载”的等待控件***/
    void onFinish();
}
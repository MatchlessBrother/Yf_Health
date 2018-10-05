package ufhealth.integratedmachine.client.network;

public class NetFlags
{
    public static final String RequestFail = "0";//失败
    public static final String RequestSuccess = "1";//成功
    public static final String RequestNoToken = "-1";//Token不存在
    public static final String RequestNoAuthorize = "-3";//无权限访问
    public static final String RequestTokenInvalid = "-2";//Token已失效
    public static final String RequestOtherProblems = "-4";//其他网络问题
}

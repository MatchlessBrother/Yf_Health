package ufhealth.integratedmachine.client.ui.bjcz.activity.view_v;

import java.util.List;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_View;
import ufhealth.integratedmachine.client.bean.bjcz.BjczUploadImgInfo;

public interface BjczAct_V extends BaseMvp_View
{
    void failOfUploadDatas();
    void failOfUploadImgDatas();
    void successOfUploadDatas();
    void successOfUploadImgDatas(List<BjczUploadImgInfo> bjczUploadImgInfos);
}
package ufhealth.integratedmachine.client.ui.main.view_v;

import ufhealth.integratedmachine.client.bean.main.UserInfo;
import ufhealth.integratedmachine.client.ui.base.BaseMvp_View;

public interface MainAct_V extends BaseMvp_View
{
      void logOut();
      void clickZxzx();
      void clickBjjy();
      void clickTjfw();
      void clickYyfw();
      void clickJkjc();
      void clickJkda();
      void clickMain_slide_img();
      void clickMain_slide_name();
      void clickMain_slide_grzl();
      void clickMain_slide_xxtz();
      void clickMain_slide_wddd();
      void clickMain_slide_wdda();
      void clickMain_slide_gybj();
      void getVerifiedCodeSuccess();
      void clickMain_slide_xxtz_num();
      void bindIdCardSuccess(String phone);
      void logged(UserInfo.UserInfoBean userInfo);
      void setWifiMacAddress(String wifiMacAddress);
      void showBindIdCardDialog(String name,String idCard);
}
package ufhealth.integratedmachine.client.ui.main.view;

import android.view.View;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import android.widget.LinearLayout;
import ufhealth.integratedmachine.client.R;
import com.hwangjr.rxbus.annotation.Subscribe;
import de.hdodenhof.circleimageview.CircleImageView;
import ufhealth.integratedmachine.client.base.BaseAct;
import ufhealth.integratedmachine.client.bean.main.UserInfo;
import ufhealth.integratedmachine.client.ui.main.view_v.UserInfoAct_V;
import ufhealth.integratedmachine.client.ui.main.presenter.UserInfoPresenter;

public class UserInfoAct extends BaseAct implements UserInfoAct_V,View.OnClickListener
{
    private CircleImageView userinfoImg;
    private TextView userinfoName;
    private TextView userinfoIdcard;
    private TextView userinfoGender;
    private TextView userinfoBirthday;
    private TextView userinfoPhonenum;
    private LinearLayout userinfoJzkglAll;
    private LinearLayout userinfoCydzglAll;
    private UserInfo.UserInfoBean userInfo;
    private UserInfoPresenter userInfoPresenter;

    protected int setLayoutResID()
    {
        return R.layout.activity_userinfos;
    }

    protected void initWidgets(View rootView)
    {
        super.initWidgets(rootView);
        setTitleContent("个人资料");
        userinfoImg = (CircleImageView) findViewById(R.id.userinfo_img);
        userinfoName = (TextView) findViewById(R.id.userinfo_name);
        userinfoIdcard = (TextView) findViewById(R.id.userinfo_idcard);
        userinfoGender = (TextView) findViewById(R.id.userinfo_gender);
        userinfoBirthday = (TextView) findViewById(R.id.userinfo_birthday);
        userinfoPhonenum = (TextView) findViewById(R.id.userinfo_phonenum);
        userinfoJzkglAll = (LinearLayout) findViewById(R.id.userinfo_jzkgl_all);
        userinfoCydzglAll = (LinearLayout) findViewById(R.id.userinfo_cydzgl_all);
        userinfoJzkglAll.setOnClickListener(this);
        userinfoCydzglAll.setOnClickListener(this);
    }

    protected void initDatas()
    {
        if(getBaseApp().getIsLogged())
            userInfo = getBaseApp().getUserInfo();
        userInfoPresenter = new UserInfoPresenter();
        userInfoPresenter.attachContextAndViewLayer(this,this);
    }

    protected void initLogic()
    {
        if(null != userInfo)
        {
            Glide.with(this).load(null != userInfo.getAvatar() ? userInfo.getAvatar().trim() : "").
                    placeholder(R.mipmap.defaultimage).error(R.mipmap.defaultimage).into(userinfoImg);
            userinfoName.setText(null != userInfo.getName() ? userInfo.getName().trim() : "未知");
            userinfoIdcard.setText("身份证号：" + (null != userInfo.getPapersNumber() ? userInfo.getPapersNumber().trim() : "未知"));
            userinfoGender.setText("所属性别：" + (userInfo.getGender() == 1 ? "男" : "女"));
            userinfoBirthday.setText("出生日期：" + (null != userInfo.getBirthday() ? userInfo.getBirthday().trim() : "未知"));
            userinfoPhonenum.setText("身份证号：" + (null != userInfo.getMobilePhone() ? userInfo.getMobilePhone().trim() : "未知"));
        }
    }

    protected void onDestroy()
    {
        userInfoPresenter.detachContextAndViewLayout();
        super.onDestroy();
    }

    public void onClick(View view)
    {
        super.onClick(view);
        switch(view.getId())
        {
            case R.id.userinfo_jzkgl_all:
            case R.id.userinfo_cydzgl_all:showToast("功能暂未开放，敬请期待...");break;
        }
    }

    @Subscribe
    public void receiveCountDownFinish(Boolean isFinish)
    {
        super.receiveCountDownFinish(isFinish);

    }

    @Subscribe
    public void receiveCountDownTime(Long countDownTime)
    {
        super.receiveCountDownTime(countDownTime);

    }
}
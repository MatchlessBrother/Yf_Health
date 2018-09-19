package ufhealth.integratedmachine.client.util;

import android.view.View;
import android.view.Window;
import android.view.Gravity;
import android.widget.Button;
import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;
import android.view.WindowManager;
import android.view.LayoutInflater;
import android.support.annotation.NonNull;
import ufhealth.integratedmachine.client.R;
import com.yuan.devlibrary._12_______Utils.PromptBoxUtils;
import com.yuan.devlibrary._11___Widget.promptBox.BaseDialog;

public class BindingCellPhoneDialog extends BaseDialog
{
    private BaseDialog mPromptDialog;
    private static BindingCellPhoneDialog mDialog;

    private BindingCellPhoneDialog(@NonNull Context context)
    {
        super(context);
    }

    public synchronized static BindingCellPhoneDialog getInstance(Context context)
    {
        if(null == mDialog)
            mDialog = new BindingCellPhoneDialog(context);
        return mDialog;
    }

    public void showDialog(String nameAndIdCard,boolean isCanceledOnTouchOutside, OnClickOutsideListener onClickOutsideListener, final OnClickSureListener onClickSureListener)
    {
        mPromptDialog = new BaseDialog(getContext());
        mPromptDialog.setCanceledOnTouchOutside(isCanceledOnTouchOutside);
        mPromptDialog.show();

        View view = LayoutInflater.from(getContext()).inflate(R.layout.inflater_bindingcellphone, null);
        mPromptDialog.setContentView(view);
        TextView bindingcellphoneNameIdcard = view.findViewById(R.id.bindingcellphone_name_idcard);
        final EditText bindingcellphonePhone = view.findViewById(R.id.bindingcellphone_phone);
        final EditText bindingcellphoneCode = view.findViewById(R.id.bindingcellphone_code);
        Button bindingcellphoneCodeBtn = view.findViewById(R.id.bindingcellphone_code_btn);
        Button bindingcellphoneBtn = view.findViewById(R.id.bindingcellphone_btn);
        bindingcellphoneNameIdcard.setText(nameAndIdCard != null ? nameAndIdCard.trim() : "未知");

        bindingcellphoneCodeBtn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                if(!bindingcellphonePhone.getText().toString().trim().equals(""))
                    PromptBoxUtils.showToast(getContext(),"请输入有效的手机号之后再获取验证码");
                else
                {
                    //调用获取手机验证码接口并根据逻辑进行倒计时操作
                    onClickSureListener.onClickGetCode(bindingcellphonePhone.getText().toString().trim());
                }
            }
        });

        bindingcellphoneBtn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                if(!bindingcellphonePhone.getText().toString().trim().equals("") && !bindingcellphoneCode.getText().toString().trim().equals("") && null != onClickSureListener)
                {
                    //验证手机验证码是否正确
                    onClickSureListener.onClickSure(bindingcellphonePhone.getText().toString().trim(),bindingcellphoneCode.getText().toString().trim());
                }
                else
                    PromptBoxUtils.showToast(getContext(),"请输入有效的手机号与验证码，否则不能无法进行绑定操作！");
            }
        });


        if(null != onClickOutsideListener)
            mPromptDialog.setOnClickOutsideListener(onClickOutsideListener);

        Window window = mPromptDialog.getWindow();
        window.getDecorView().setPadding(0,0,0,0);
        window.getDecorView().setBackgroundResource(R.color.transparent);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = 660;
        params.height = 700;
        params.gravity = Gravity.CENTER;
        window.setAttributes(params);
    }

    /*******进行倒计时60秒******/
    public void CountDownAction()
    {

    }

    public void dismissDialog()
    {
        if(null != mPromptDialog && mPromptDialog.isShowing())
            mPromptDialog.dismiss();
    }

    public interface OnClickSureListener
    {
        void onClickGetCode(String phoneNum);
        void onClickSure(String phoneNum,String verificatedCode);
    }
}
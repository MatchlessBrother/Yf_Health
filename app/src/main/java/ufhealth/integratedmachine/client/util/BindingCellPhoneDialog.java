package ufhealth.integratedmachine.client.util;

import java.util.Timer;

import android.app.Activity;
import android.view.View;
import java.util.TimerTask;
import android.view.Window;
import android.view.Gravity;
import android.widget.Button;
import android.graphics.Color;
import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;
import android.view.WindowManager;
import android.view.LayoutInflater;
import android.support.annotation.NonNull;
import ufhealth.integratedmachine.client.R;
import com.yuan.devlibrary._12_______Utils.PromptBoxTools;
import com.yuan.devlibrary._11___Widget.promptBox.BaseDialog;

public class BindingCellPhoneDialog extends BaseDialog
{
    private int time;
    private Timer timer;
    private Context context;
    private TimerTask timerTask;
    private BaseDialog mPromptDialog;
    private Button bindingcellphoneBtn;
    private EditText bindingcellphoneCode;
    private Button bindingcellphoneCodeBtn;
    private EditText bindingcellphonePhone;
    private static BindingCellPhoneDialog mDialog;

    private BindingCellPhoneDialog(@NonNull Context context)
    {
        super(context);
        time = 60;
        this.context = context;
    }

    public synchronized static BindingCellPhoneDialog getInstance(Context context)
    {
        if(null == mDialog)
            mDialog = new BindingCellPhoneDialog(context);
        return mDialog;
    }

    public void showDialog(final Context context,final String name,final String idCard,boolean isCanceledOnTouchOutside, OnClickOutsideListener onClickOutsideListener, final OnClickSureListener onClickSureListener)
    {
        mPromptDialog = new BaseDialog(context);
        mPromptDialog.setCanceledOnTouchOutside(isCanceledOnTouchOutside);
        mPromptDialog.setView(new EditText(context));
        mPromptDialog.show();

        View view = LayoutInflater.from(context).inflate(R.layout.inflater_bindingcellphone, null);
        mPromptDialog.setContentView(view);
        TextView bindingcellphoneNameIdcard = view.findViewById(R.id.bindingcellphone_name_idcard);
        bindingcellphonePhone = view.findViewById(R.id.bindingcellphone_phone);
        bindingcellphoneCode = view.findViewById(R.id.bindingcellphone_code);
        bindingcellphoneCodeBtn = view.findViewById(R.id.bindingcellphone_code_btn);
        bindingcellphoneBtn = view.findViewById(R.id.bindingcellphone_btn);
        String nameAndIdCard = name + "（" + idCard + "）";
        bindingcellphoneNameIdcard.setText(nameAndIdCard != null ? nameAndIdCard.trim() : "未知");

        bindingcellphoneCodeBtn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                if("".equals(bindingcellphonePhone.getText().toString().trim()))
                    PromptBoxTools.showToast(getContext(),"请输入有效的手机号之后再获取验证码");
                else
                {
                    time = 60;
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
                    onClickSureListener.onClickSure(idCard,bindingcellphonePhone.getText().toString().trim(),bindingcellphoneCode.getText().toString().trim());
                }
                else
                    PromptBoxTools.showToast(getContext(),"请输入有效的手机号与验证码，否则不能无法进行绑定操作！");
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

    public void startCountDownTime()
    {
        bindingcellphoneCodeBtn.setEnabled(false);
        bindingcellphoneCodeBtn.setBackgroundColor(Color.argb(255,221,221,221));
        timer = new Timer();
        timerTask = new TimerTask()
        {
            public void run()
            {
                time--;
                ((Activity)context).runOnUiThread(new Runnable()
                {
                    public void run()
                    {
                        bindingcellphoneCodeBtn.setText(time + "");
                    }
                });
                if(time == 0)
                {
                    timer.cancel();
                    ((Activity)context).runOnUiThread(new Runnable()
                    {
                        public void run()
                        {
                            bindingcellphoneCodeBtn.setEnabled(true);
                            bindingcellphoneCodeBtn.setText("获取验证码");
                            bindingcellphoneCodeBtn.setBackgroundColor(Color.argb(255,0,147,221));
                        }
                    });
                }
            }
        };
        timer.schedule(timerTask,1000,1000);
    }

    public void dismissDialog()
    {
        if(null != mPromptDialog && mPromptDialog.isShowing())
            mPromptDialog.dismiss();
    }

    public interface OnClickSureListener
    {
        void onClickGetCode(String phoneNum);
        void onClickSure(String idCard,String phoneNum,String verificatedCode);
    }
}
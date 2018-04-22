package ufhealth.integratedmachine.client.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.yuan.devlibrary._11___Widget.promptBox.BaseDialog;
import ufhealth.integratedmachine.client.R;

public class RatingDoctorDialog extends BaseDialog
{
    private BaseDialog mPromptDialog;
    private static RatingDoctorDialog mDialog;

    private RatingDoctorDialog(@NonNull Context context)
    {
        super(context);
    }

    public synchronized static RatingDoctorDialog getInstance(Context context)
    {
        if(null == mDialog)
            mDialog = new RatingDoctorDialog(context);
        return mDialog;
    }

    public void showDialog(String name, String position, String department, String hospitalName, boolean isCanceledOnTouchOutside, OnClickOutsideListener onClickOutsideListener, View.OnClickListener onClickCommitListener)
    {
        mPromptDialog = new BaseDialog(getContext());
        mPromptDialog.setCanceledOnTouchOutside(isCanceledOnTouchOutside);
        mPromptDialog.show();

        View view = LayoutInflater.from(getContext()).inflate(R.layout.inflater_ratingdoctordialog, null);
        mPromptDialog.setContentView(view);

        /*********************************************内容初始化***********************************************/

        if(null != onClickOutsideListener)
            mPromptDialog.setOnClickOutsideListener(onClickOutsideListener);

        Window window = mPromptDialog.getWindow();
        window.getDecorView().setPadding(0,0,0,0);
        window.getDecorView().setBackgroundResource(R.color.transparent);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = 880;
        params.height = 840;
        params.gravity = Gravity.CENTER;
        window.setAttributes(params);
    }
}
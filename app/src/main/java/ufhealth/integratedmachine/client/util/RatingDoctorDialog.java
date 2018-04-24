package ufhealth.integratedmachine.client.util;

import android.view.View;
import android.view.Window;
import android.view.Gravity;
import android.app.Activity;
import android.widget.Button;
import android.widget.TextView;
import android.content.Context;
import android.view.WindowManager;
import android.view.LayoutInflater;
import com.hedgehog.ratingbar.RatingBar;
import android.support.annotation.NonNull;
import ufhealth.integratedmachine.client.R;
import de.hdodenhof.circleimageview.CircleImageView;
import ufhealth.integratedmachine.client.base.BaseAct;
import ufhealth.integratedmachine.client.base.BasePhotoAct;
import com.yuan.devlibrary._11___Widget.promptBox.BaseDialog;
import ufhealth.integratedmachine.client.widget.CountEditText;

public class RatingDoctorDialog extends BaseDialog
{
    private float zydStart;
    private float fwtdStart;
    private float hfsdStart;
    private float xtkyxStart;
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

    public void showDialog(Activity act,String imgPath, String name, String position, String department, String hospitalName, boolean isCanceledOnTouchOutside, OnClickOutsideListener onClickOutsideListener, final OnClickCommitListener onClickCommitListener)
    {
        mPromptDialog = new BaseDialog(getContext());
        mPromptDialog.setCanceledOnTouchOutside(isCanceledOnTouchOutside);
        mPromptDialog.show();

        View view = LayoutInflater.from(getContext()).inflate(R.layout.inflater_ratingdoctordialog, null);
        mPromptDialog.setContentView(view);
        CircleImageView dialogRatingdoctorImg = view.findViewById(R.id.dialog_ratingdoctor_img);
        TextView dialogRatingdoctorName = view.findViewById(R.id.dialog_ratingdoctor_name);
        TextView dialogRatingdoctorPosition = view.findViewById(R.id.dialog_ratingdoctor_position);
        TextView dialogRatingdoctorHospitalname = view.findViewById(R.id.dialog_ratingdoctor_hospitalname);
        TextView dialogRatingdoctorDepartmentname = view.findViewById(R.id.dialog_ratingdoctor_departmentname);
        final RatingBar dialogRatingdoctorFwtd = view.findViewById(R.id.dialog_ratingdoctor_fwtd);
        final TextView dialogRatingdoctorFwtdNote = view.findViewById(R.id.dialog_ratingdoctor_fwtd_note);
        RatingBar dialogRatingdoctorZyd = view.findViewById(R.id.dialog_ratingdoctor_zyd);
        final TextView dialogRatingdoctorZydNote = view.findViewById(R.id.dialog_ratingdoctor_zyd_note);
        RatingBar dialogRatingdoctorHfsd = view.findViewById(R.id.dialog_ratingdoctor_hfsd);
        final TextView dialogRatingdoctorHfsdNote = view.findViewById(R.id.dialog_ratingdoctor_hfsd_note);
        RatingBar dialogRatingdoctorXtkyx = view.findViewById(R.id.dialog_ratingdoctor_xtkyx);
        final TextView dialogRatingdoctorXtkyxNote = view.findViewById(R.id.dialog_ratingdoctor_xtkyx_note);
        final CountEditText dialogRatingdoctorEt = view.findViewById(R.id.dialog_ratingdoctor_et);
        Button dialogRatingdoctorBtn = view.findViewById(R.id.dialog_ratingdoctor_btn);

       if(act instanceof BaseAct)
           ((BaseAct)act).useGlideLoadImg(dialogRatingdoctorImg,imgPath);
       else
           ((BasePhotoAct)act).useGlideLoadImg(dialogRatingdoctorImg,imgPath);

        zydStart = 0f;
        fwtdStart = 0f;
        hfsdStart = 0f;
        xtkyxStart = 0f;
        dialogRatingdoctorName.setText(name != null ? name.trim() : "未命名");
        dialogRatingdoctorPosition.setText(position != null ? position.trim() : "医师");
        dialogRatingdoctorDepartmentname.setText(department != null ? department.trim() : "未知科室");
        dialogRatingdoctorHospitalname.setText(hospitalName != null ? hospitalName.trim() : "未知医院");

        dialogRatingdoctorFwtd.setOnRatingChangeListener(new RatingBar.OnRatingChangeListener()
        {
            public void onRatingChange(float RatingCount)
            {
                fwtdStart = RatingCount;
                if(fwtdStart > 0f && fwtdStart <= 1f)
                    dialogRatingdoctorFwtdNote.setText("（非常差）");
                else if(fwtdStart > 1f && fwtdStart <= 2f)
                    dialogRatingdoctorFwtdNote.setText("（差）");
                else if(fwtdStart > 2f && fwtdStart <= 3f)
                    dialogRatingdoctorFwtdNote.setText("（一般）");
                else if(fwtdStart > 3f && fwtdStart <= 4f)
                    dialogRatingdoctorFwtdNote.setText("（好）");
                else if(fwtdStart > 4f )
                    dialogRatingdoctorFwtdNote.setText("（非常好）");
            }
        });

        dialogRatingdoctorZyd.setOnRatingChangeListener(new RatingBar.OnRatingChangeListener()
        {
            public void onRatingChange(float RatingCount)
            {
                zydStart = RatingCount;
                if(zydStart > 0f && zydStart <= 1f)
                    dialogRatingdoctorZydNote.setText("（非常不专业）");
                else if(zydStart > 1f && zydStart <= 2f)
                    dialogRatingdoctorZydNote.setText("（不专业）");
                else if(zydStart > 2f && zydStart <= 3f)
                    dialogRatingdoctorZydNote.setText("（一般）");
                else if(zydStart > 3f && zydStart <= 4f)
                    dialogRatingdoctorZydNote.setText("（专业）");
                else if(zydStart > 4f )
                    dialogRatingdoctorZydNote.setText("（非常专业）");
            }
        });

        dialogRatingdoctorHfsd.setOnRatingChangeListener(new RatingBar.OnRatingChangeListener()
        {
            public void onRatingChange(float RatingCount)
            {
                hfsdStart = RatingCount;
                if(hfsdStart > 0f && hfsdStart <= 1f)
                    dialogRatingdoctorHfsdNote.setText("（非常慢）");
                else if(hfsdStart > 1f && hfsdStart <= 2f)
                    dialogRatingdoctorHfsdNote.setText("（慢）");
                else if(hfsdStart > 2f && hfsdStart <= 3f)
                    dialogRatingdoctorHfsdNote.setText("（一般）");
                else if(hfsdStart > 3f && hfsdStart <= 4f)
                    dialogRatingdoctorHfsdNote.setText("（快）");
                else if(hfsdStart > 4f )
                    dialogRatingdoctorHfsdNote.setText("（非常快）");
            }
        });

        dialogRatingdoctorXtkyx.setOnRatingChangeListener(new RatingBar.OnRatingChangeListener()
        {
            public void onRatingChange(float RatingCount)
            {
                xtkyxStart = RatingCount;
                if(xtkyxStart > 0f && xtkyxStart <= 1f)
                    dialogRatingdoctorXtkyxNote.setText("（非常难用）");
                else if(xtkyxStart > 1f && xtkyxStart <= 2f)
                    dialogRatingdoctorXtkyxNote.setText("（不好用）");
                else if(xtkyxStart > 2f && xtkyxStart <= 3f)
                    dialogRatingdoctorXtkyxNote.setText("（一般）");
                else if(xtkyxStart > 3f && xtkyxStart <= 4f)
                    dialogRatingdoctorXtkyxNote.setText("（好用）");
                else if(xtkyxStart > 4f )
                    dialogRatingdoctorXtkyxNote.setText("（很好用）");
            }
        });

        if(null != onClickOutsideListener)
            mPromptDialog.setOnClickOutsideListener(onClickOutsideListener);
        dialogRatingdoctorBtn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                if(null != onClickCommitListener)
                {
                    onClickCommitListener.onClickCommit(fwtdStart,zydStart,hfsdStart,xtkyxStart,dialogRatingdoctorEt.getText().toString().trim());
                }
            }
        });

        Window window = mPromptDialog.getWindow();
        window.getDecorView().setPadding(0,0,0,0);
        window.getDecorView().setBackgroundResource(R.color.transparent);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = 880;
        params.height = 840;
        params.gravity = Gravity.CENTER;
        window.setAttributes(params);
    }

    public void dismissDialog()
    {
        if(null != mPromptDialog && mPromptDialog.isShowing())
            mPromptDialog.dismiss();
    }

    public interface OnClickCommitListener
    {
        void onClickCommit(float fwtdStart,float zydStart,float hfsdStart,float xtkyxStart,String ratingContent);
    }
}
package com.yuan.devlibrary._11___Widget.indicator;

import android.view.View;

import com.nineoldandroids.animation.ValueAnimator;

public class LineScalePulseOutIndicator extends LineScaleIndicator
{
    public void createAnimation()
    {
        long[] delays=new long[]{500,250,0,250,500};
        for (int i = 0; i < 5; i++)
        {
            final int index=i;
            scaleAnim=ValueAnimator.ofFloat(1,0.3f,1);
            scaleAnim.setDuration(900);
            scaleAnim.setRepeatCount(-1);
            scaleAnim.setStartDelay(delays[i]);
            scaleAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
            {
                public void onAnimationUpdate(ValueAnimator animation)
                {
                    scaleYFloats[index] = (float) animation.getAnimatedValue();
                    postInvalidate();
                }
            });
            scaleAnim.start();
        }
    }

    private ValueAnimator scaleAnim = null;
    public void showView()
    {
        if(scaleAnim == null)
            createAnimation();
        getTarget().setVisibility(View.VISIBLE);
        scaleAnim.start();
    }

    public void hideViewInvisible()
    {
        if(scaleAnim == null)
            createAnimation();
        getTarget().setVisibility(View.INVISIBLE);
        scaleAnim.cancel();
    }

    public void hideViewGone()
    {
        if(scaleAnim == null)
            createAnimation();
        getTarget().setVisibility(View.GONE);
        scaleAnim.cancel();
    }
}
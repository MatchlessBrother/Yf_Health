package com.yuan.devlibrary._11___Widget.indicator;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

import com.nineoldandroids.animation.ValueAnimator;

public class LineScalePartyIndicator extends BaseIndicatorController
{
    public static final float SCALE=1.0f;
    float[] scaleFloats=new float[]{SCALE, SCALE, SCALE, SCALE, SCALE,};

    public void draw(Canvas canvas, Paint paint)
    {
        float translateX=getWidth()/9;
        float translateY=getHeight()/2;
        for (int i = 0; i < 4; i++)
        {
            canvas.save();
            canvas.translate((2 + i * 2) * translateX - translateX / 2, translateY);
            canvas.scale(scaleFloats[i], scaleFloats[i]);
            RectF rectF=new RectF(-translateX/2,-getHeight()/2.5f,translateX/2,getHeight()/2.5f);
            canvas.drawRoundRect(rectF,5,5,paint);
            canvas.restore();
        }
    }

    public void createAnimation()
    {
        long[] durations=new long[]{1260, 430, 1010, 730};
        long[] delays=new long[]{770, 290, 280, 740};
        for (int i = 0; i < 4; i++)
        {
            final int index=i;
            scaleAnim=ValueAnimator.ofFloat(1,0.4f,1);
            scaleAnim.setDuration(durations[i]);
            scaleAnim.setRepeatCount(-1);
            scaleAnim.setStartDelay(delays[i]);
            scaleAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
            {
                public void onAnimationUpdate(ValueAnimator animation)
                {
                    scaleFloats[index] = (float) animation.getAnimatedValue();
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
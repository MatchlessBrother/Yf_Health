package ufhealth.integratedmachine.client.util;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;

public abstract class CountDownUtil
{
    /**Millis since epoch when alarm should stop.*/
    private long mStopTimeInFuture;
    private long mMillisInFuture;

    /**boolean representing if the timer was cancelled*/
    private boolean mCancelled = false;

    /**********@param secondInFuture The number of millis in the future from the call**************/
    /*********to {@link #start()} until the countdown is done and {@link #onFinish()} is called.*/
    public CountDownUtil(long secondInFuture)
    {
        mMillisInFuture = secondInFuture;
    }

    public void setmMillisInFuture(long mMillisInFuture)
    {
        this.mMillisInFuture = mMillisInFuture;
    }

    /*******Cancel the countdown.*********/
    public synchronized final void cancel()
    {
        mCancelled = true;
        mHandler.removeMessages(MSG);
    }

    /************Start the countdown.*************/
    public synchronized final CountDownUtil start()
    {
        mCancelled = false;
        if (mMillisInFuture <= 0)
        {
            onFinish();
            return this;
        }
        mStopTimeInFuture = (SystemClock.elapsedRealtime()/1000) + mMillisInFuture;
        mHandler.sendMessage(mHandler.obtainMessage(MSG));
        return this;
    }

    /*********handles counting down*********/
    private static final int MSG = 1;
    private Handler mHandler = new Handler()
    {
        public void handleMessage(Message msg)
        {
            synchronized (CountDownUtil.this)
            {
                if (mCancelled)
                    return;

                final long millisLeft = mStopTimeInFuture - (SystemClock.elapsedRealtime()/1000);
                if (millisLeft < 0)
                    onFinish();
                else
                {
                    onTick(millisLeft);
                    sendMessageDelayed(obtainMessage(MSG), 1000);
                }
            }
        }
    };

    /**Callback fired when the time is up.**/
    public abstract void onFinish();

    /***************Callback fired on regular interval****************/
    /**@param millisUntilFinished The amount of time until finished.*/
    public abstract void onTick(long millisUntilFinished);
}
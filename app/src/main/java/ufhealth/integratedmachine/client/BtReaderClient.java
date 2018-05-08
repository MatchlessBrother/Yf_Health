package ufhealth.integratedmachine.client;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.SystemClock;
import com.invs.Blt2;
import com.invs.Blt4;
import com.invs.IClientCallBack;
import com.invs.InvsIdCard;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class BtReaderClient implements IClientCallBack {
    private Context mContext = null;
    private Blt2 mBlt2 = null;
    private Blt4 mBlt4 = null;
    public InvsIdCard mInvsIdCard = null;
    public boolean mBle = true;
    protected IClientCallBack mCallback = null;
    protected Timer mTimer = null;
    protected TimerTask mTimerTask = null;
    protected boolean mYibu = false;
    protected boolean mConn = false;
    protected boolean mbConState = false;
    private final BroadcastReceiver mBltReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if("invs.blt1".equals(action)) {
                boolean succ = intent.getBooleanExtra("tag", false);
                if(BtReaderClient.this.mCallback != null) {
                    try {
                        BtReaderClient.this.mCallback.onBtState(succ);
                    } catch (Exception var6) {
                        ;
                    }
                }
            }

        }
    };

    public BtReaderClient(Context context) {
        this.mContext = context;
        this.regRecv();
        this.mBlt2 = new Blt2(this.mContext);
        this.mBlt2.setCallBack(this);
        this.mBle = false;
        if(this.mContext.getPackageManager().hasSystemFeature("android.hardware.bluetooth_le")) {
            this.mBlt4 = new Blt4(this.mContext);
            this.mBlt4.setCallBack(this);
        }

    }



    public boolean setKey(byte[] key) {
        return this.mBle?this.mBlt4.setKey(key):this.mBlt2.setKey(key);
    }

    public byte[] getKey() {
        return this.mBle?this.mBlt4.getKey():this.mBlt2.getKey();
    }

    public int readBat() {
        return this.mBle?this.mBlt4.readBat():this.mBlt2.readBat();
    }

    public boolean testDev() {
        return this.mBle?this.mBlt4.testDev():this.mBlt2.testDev();
    }

    public InvsIdCard readCard() {
        return this.mBle?this.mBlt4.readCard():this.mBlt2.readCard();
    }

    public Map readCert() {
        return this.mBle?this.mBlt4.readCert():this.mBlt2.readCert();
    }

    public String readPhoto() {
        return this.mBle?this.mBlt4.readPhoto():this.mBlt2.readPhoto();
    }

    public void scanDevice(boolean ble, boolean enable) {
        if(ble && this.mBlt4 != null) {
            this.mBlt4.scanDevice(enable);
        } else {
            this.mBlt2.scanDevice(enable);
        }

    }

    void setParam(byte[] param) {
        if(this.mBle && this.mBlt4 != null) {
            this.mBlt4.setParam(param);
        } else {
            this.mBlt2.setParam(param);
        }

    }

    public void setCallBack(IClientCallBack callback) {
        this.mCallback = callback;
    }

    void regRecv() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("invs.blt1");
        this.mContext.registerReceiver(this.mBltReceiver, intentFilter);
    }

    public int findCardCmd() {
        return this.mBle?this.mBlt4.findCardCmd():this.mBlt2.findCardCmd();
    }

    public int SelCardCmd() {
        return this.mBle?this.mBlt4.SelCardCmd():this.mBlt2.SelCardCmd();
    }

    public boolean wakeupCmd() {
        return this.mBle?this.mBlt4.wakeupCmd():this.mBlt2.wakeupCmd();
    }

    public int readAppCmd() {
        return this.mBle?this.mBlt4.readAppCmd():this.mBlt2.readAppCmd();
    }

    public int readCardCmd()
    {
        int iRet;
        if(this.mBle) {
            iRet = this.mBlt4.readCardCmd();
            this.mInvsIdCard = this.mBlt4.mInvsIdCard;
        } else {
            iRet = this.mBlt2.readCardCmd();
            this.mInvsIdCard = this.mBlt2.mInvsIdCard;
        }

        return iRet;
    }

    public void sendMsg(boolean succ) {
        Intent intent = new Intent();
        intent.setAction("invs.blt1");
        intent.putExtra("cmd", 1);
        intent.putExtra("tag", succ);

        try {
            this.mContext.sendBroadcast(intent);
        } catch (Exception var4) {
            ;
        }

    }

    public boolean connectBt(String addr) {
        this.mbConState = false;
        boolean result = false;
        if(addr != null && addr != "" && BluetoothAdapter.checkBluetoothAddress(addr)) {
            if(addr.indexOf("00:0E:0B") == 0) {
                this.mBle = false;
                result = this.mBlt2.connectBt(addr);
            } else if(addr.indexOf("00:0E:0E") == 0) {
                this.mBle = false;
                result = this.mBlt2.connectBt(addr);
            }
        } else {
            result = false;
        }

        if(this.mYibu) {
            return true;
        } else {
            int iCount = 0;

            do {
                SystemClock.sleep(150L);
                if(this.mbConState) {
                    SystemClock.sleep(80L);
                    return result;
                }

                ++iCount;
            } while(iCount <= 100);

            SystemClock.sleep(80L);
            return false;
        }
    }

    public boolean disconnectBt() {
        this.mbConState = false;
        if(this.mBle) {
            this.mBlt4.disconnectBt();
        } else {
            this.mBlt2.disconnectBt();
        }

        if(this.mYibu) {
            return true;
        } else {
            int iCount = 0;

            do {
                SystemClock.sleep(150L);
                if(this.mbConState) {
                    SystemClock.sleep(80L);
                    return true;
                }

                ++iCount;
            } while(iCount <= 60);

            SystemClock.sleep(80L);
            return true;
        }
    }

    public void onBtState(boolean is_connect)
    {
        if(this.mYibu)
        {
            this.sendMsg(is_connect);
        }
        else
        {
            this.mConn = is_connect;
            this.mbConState = true;
            if(this.mCallback != null)
            {
                this.mTimer = new Timer(true);
                this.mTimerTask = new TimerTask()
                {
                    public void run()
                    {
                        if(null != mTimerTask)
                            BtReaderClient.this.mTimerTask.cancel();
                        if(null != mTimer)
                        BtReaderClient.this.mTimer.cancel();
                        BtReaderClient.this.mTimer = null;
                        BtReaderClient.this.mTimerTask = null;
                        if(BtReaderClient.this.mCallback != null)
                        {
                            BtReaderClient.this.sendMsg(BtReaderClient.this.mConn);
                        }
                    }
                };
                this.mTimer.schedule(this.mTimerTask, 20L);
            }
        }

    }

    public BroadcastReceiver getmBltReceiver()
    {
        return this.mBltReceiver;
    }
}

package madbarsoft.com.computershortquestionforitjob.utility;

import android.os.Handler;

public class DelaTimer {

    // Delay mechanism

    public interface DelayCallback{
        void afterDelay();
    }

    public static void delay(int secs, final DelayCallback delayCallback){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                delayCallback.afterDelay();
            }
        }, secs * 1000); // afterDelay will be executed after (secs*1000) milliseconds.
    }





}


// for user

//    int secs = 2; // Delay in seconds
//
//                    DelaTimer.delay(secs, new DelaTimer.DelayCallback() {
//@Override
//public void afterDelay() {
//        // Do something after delay
//
//        }
//        });




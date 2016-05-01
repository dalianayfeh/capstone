package com.example.dalia.progressbar;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.util.Log;


public class MainActivity extends AppCompatActivity {

    protected static final int TIMER_RUNTIME = 10000;

    protected boolean mbActive;
    protected ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = (ProgressBar)findViewById(R.id.barrinha);

        final Thread timerThread = new Thread() {
            public void run() {
                mbActive = true;
                try {
                    int waited = 0;
                    while(mbActive && (waited < TIMER_RUNTIME)) {
                        sleep(200);
                        if(mbActive) {
                            waited += 200;
                            updateProgess(waited);
                        }
                    }
                } catch(InterruptedException e) {
                    //error
                } finally {
                    onContinue();
                }
            }
        };
        timerThread.start();
    }

    public void updateProgess(final int timePassed) {
        if(null != mProgressBar) {
            final int progress = mProgressBar.getMax() * timePassed / TIMER_RUNTIME;
            mProgressBar.setProgress(progress);
        }
    }

    public void onContinue() {
        Log.d("messageFinal", "Loading");
    }


}



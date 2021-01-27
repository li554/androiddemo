package com.example.lifecycles;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.widget.Chronometer;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public class MyChronometer extends Chronometer implements LifecycleObserver {

    private long elapseTime;

    public MyChronometer(Context context) {
        super(context);
    }
    public MyChronometer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyChronometer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    private void pauseMeter(){
        elapseTime = SystemClock.elapsedRealtime()-getBase();
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private void resumeMeter(){
        setBase(SystemClock.elapsedRealtime()-elapseTime);
    }

}

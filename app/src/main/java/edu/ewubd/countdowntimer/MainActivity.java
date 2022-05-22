package edu.ewubd.countdowntimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int decreases_timer = 100;
    private Handler mHandler;

    private int id = 2019160068;
    private int reset_id = 2019160068;

    private TextView Timer;
    private Button Start, Reset, Pause;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHandler = new Handler();

        Start = findViewById(R.id.btn_start);
        Reset = findViewById(R.id.btn_reset);
        Pause = findViewById(R.id.btn_pause);

        Timer = findViewById(R.id.tv_counter_show);

        Start.setOnClickListener(v->startRepeatingTask());
        Reset.setOnClickListener(v->reset());
        Pause.setOnClickListener(v->stopRepeatingTask());

        findViewById(R.id.btn_exit).setOnClickListener(v->finish());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopRepeatingTask();
    }

    Runnable mStatusChecker = new Runnable() {
        @Override
        public void run() {
            try {
                updateStatus();
            } finally {
                mHandler.postDelayed(mStatusChecker, decreases_timer);
            }
        }
    };

    void startRepeatingTask() {
        mStatusChecker.run();
    }

    void stopRepeatingTask() {
        mHandler.removeCallbacks(mStatusChecker);
    }

    void updateStatus(){
        if(id == 0)
            stopRepeatingTask();
        else
            id = id-1;

        Timer.setText(String.valueOf(id));
    }

    void reset(){
        id = reset_id;
        stopRepeatingTask();
        Timer.setText(String.valueOf(id));
    }
}
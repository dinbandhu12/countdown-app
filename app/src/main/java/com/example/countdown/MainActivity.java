package com.example.countdown;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editTextDateTime;
    private Button startCountdownButton;
    private ListView countdownListView;
    private List<CountdownTimer> countdownTimers = new ArrayList<>();
    private CoundownTimerAdapter countdownAdapter;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextDateTime = findViewById(R.id.editTextDateTime);
        startCountdownButton = findViewById(R.id.startCountdownButton);
        countdownListView = findViewById(R.id.countdownListView);

        countdownAdapter = new CoundownTimerAdapter(this, countdownTimers);
        countdownListView.setAdapter(countdownAdapter);

        startCountdownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String targetDateTime = editTextDateTime.getText().toString();
                CountdownTimer countdownTimer = new CountdownTimer("Countdown", targetDateTime);
                countdownTimers.add(countdownTimer);
                countdownAdapter.notifyDataSetChanged();
                startCountdownButton(countdownTimer);
            }
        });
    }
    private void startCountdownButton(CountdownTimer countdownTimer) {
        final TextView timeRemainingTextView = new TextView(this);
        countdownListView.addFooterView(timeRemainingTextView);

        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        final Date targetDate;
        try {
            targetDate = simpleDateFormat.parse(countdownTimer.getTargetDateTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return;
        }

        final Runnable updateTimeRunnable = new Runnable() {
            @Override
            public void run() {
                long currentTImeMillis = System.currentTimeMillis();
                long tagetTiemeMillis = targetDate.getTime();
                long timeRemainingMillis = tagetTiemeMillis - currentTImeMillis;

                if(timeRemainingMillis <= 0) {
//                    timeRemainingTextView.setText("Countdown Complete");
                    countdownTimer.remove(countdownTimer);
                    countdownAdapter.notifyDataSetChanged();
                    handler.removeCallbacks(this);  // stop the countdown when it's complete
                } else {
                    long days = timeRemainingMillis / (1000 * 60 * 60 * 24);
                    long hours = (timeRemainingMillis % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
                    long minutes = (timeRemainingMillis % (1000 * 60 * 60)) / (1000 * 60);
                    long seconds = (timeRemainingMillis % (1000 * 60)) / 1000;

                    String formattedTimeRemaining = String.format("%d days, %d hours, %d minutes, %d seconds", days, hours, minutes, seconds);
                    timeRemainingTextView.setText(formattedTimeRemaining);

                    // schedule the runnable to update the countdown every second
                    handler.postDelayed(this, 1000);
                }
            }
        };
        // start the countdown by calling the runnable for the first time
        handler.post(updateTimeRunnable);
    }
//    implement methods to edit and delete countdown timers
}
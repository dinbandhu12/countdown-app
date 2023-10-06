package com.example.countdown;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class CountdownTimer {
    private String title;
    private String targetDateTime;

    public CountdownTimer(String title, String targetDateTime) {
        this.title = title;
        this.targetDateTime = targetDateTime;
    }

    public String getTitle() {
        return title;
    }

    public String getTimeRemaining() {
        // implement logic to calculate the time remaining

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        try {
            Date targetDate = simpleDateFormat.parse(targetDateTime);
            long currentTimeMillis = System.currentTimeMillis();
            assert targetDate != null;
            long targetTimeMillis = targetDate.getTime();
            long timeRemainingMillis = targetTimeMillis - currentTimeMillis;

            if(timeRemainingMillis <= 0) {
                return "Countdown Complete";    // handle the case when the countdown is complete
            }

            long days = timeRemainingMillis / (1000 * 60 * 60 * 24);
//            long hours = (timeRemainingMillis / (1000 * 60 * 60)) % 24;
            long hours = (timeRemainingMillis % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
            long minutes = (timeRemainingMillis % (1000 * 60 * 60)) / (1000 * 60);
            long seconds = (timeRemainingMillis % (1000 * 60)) / 1000;

            String formattedTimeRemaining = String.format("%d days, %d hours, %d minutes, %d seconds", days, hours, minutes, seconds);
            return formattedTimeRemaining;
        } catch (ParseException e) {
            e.printStackTrace();
            return "Invalid Date";
        }
    }

    public String getTargetDateTime() {
        return targetDateTime;
    }

    public void remove(CountdownTimer countdownTimer) {

    }

//    add getter and setter methods

//    add a method to calculate the time remaining
}

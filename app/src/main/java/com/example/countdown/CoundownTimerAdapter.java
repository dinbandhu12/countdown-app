package com.example.countdown;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CoundownTimerAdapter extends ArrayAdapter<CountdownTimer> {

    private Context context;
    private List<CountdownTimer> countdownTimers;

    public CoundownTimerAdapter(Context context, List<CountdownTimer> countdownTimers) {
        super(context, 0, countdownTimers);
//        super();
        this.context = context;
        this.countdownTimers = countdownTimers;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the countdown timer at the specified position
        CountdownTimer countdownTimer = getItem(position);
        // check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_countdown, parent, false);
        }

        // find the textview in the list item layout
        TextView titleTextView = convertView.findViewById(R.id.titleTextView);
        TextView timeRemainingTextView = convertView.findViewById(R.id.timeRemainingTextView);

        // set the text for the textview
        titleTextView.setText(countdownTimer.getTitle());
        timeRemainingTextView.setText(countdownTimer.getTimeRemaining());

        return convertView;
    }

}

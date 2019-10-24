package com.example.tulips;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class currentRecitingDetail extends AppCompatActivity {
    String eventID;
    String eventTitle;
    Toolbar toolbar;
    String eventDescription;
    TextView descriptionHolder;
    ScrollView scrollView;
    TextView eventNameHolder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_reciting_detail);
        toolbar = findViewById(R.id.toolbar);
        scrollView = findViewById(R.id.scrollView);
        LinearLayout linearLayout = (LinearLayout) scrollView.getChildAt(0);
        CardView cardView = (CardView) linearLayout.getChildAt(0);
        descriptionHolder = (TextView) cardView.getChildAt(0);
        eventNameHolder = findViewById(R.id.textView7);

        toolbar.setTitle("Description");
        Intent intent = getIntent();
        eventID = intent.getStringExtra(FragmentTimeline.EXTRA_EVENT_ID);
        eventTitle = intent.getStringExtra(FragmentTimeline.EXTRA_EVENT_TITLE);
        eventDescription = intent.getStringExtra(FragmentTimeline.EXTRA_EVENT_DESCRIPTION);
        descriptionHolder.setText(eventDescription);
        eventNameHolder.setText(eventTitle);


    }



}

package com.example.tulips;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;



public class currentRecitingVerses extends AppCompatActivity  {
    private card_fragment card_fragment;
    private no_card_fragment no_card_fragment;
    String eventID;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_reciting_verses);
        Intent intent = getIntent();
        eventID = intent.getStringExtra(FragmentTimeline.EXTRA_EVENT_ID);

    }

    @Override
    protected void onStart() {
        super.onStart();
//        Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show();





    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, eventID, Toast.LENGTH_SHORT).show();

    }
}

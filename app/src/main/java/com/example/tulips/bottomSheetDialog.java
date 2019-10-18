package com.example.tulips;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class bottomSheetDialog extends BottomSheetDialogFragment {
    private bottomSheetListener mListener;

    TextView religiousEventTextView;
    TextView nonReligiousEventTextView;
    TextView allEventTextView;

    TextView HallEventTextView;
    TextView HomeEventTextView;
    TextView anywhereEventTextView;

    TextView casualEventTextView;
    TextView semiformalEventTextView;
    TextView anyWearEventTextView;


    Button religiousButton;
    Button nonReligiousButton;
    Button allEventsButton;

    Button HallEventButton;
    Button HomeEventButton;
    Button anywhereButton;

    Button casualButton;
    Button semiformalButton;
    Button anyWearButton;


    Button cancelButton;
    Button applyButton;

    View v;

    int religious = 3;      // Should be 1 if religious event is selected; 2 if non religious 3 if any event
    int location = 3;       // Should be 1 if indoor event is selected; 2 if outdoor 3 if anywhere event
    int dress = 3;          // Should be 1 if formal event is selected; 2 if informal 3 if anyWear event


    bottomSheetDialog(int religious, int location, int dress){
        this.religious = religious;
        this.location = location;
        this.dress = dress;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.bottom_sheet_layout, container, false);
        religiousEventTextView = v.findViewById(R.id.religiousTextView);
        nonReligiousEventTextView = v.findViewById(R.id.nonreligiousTextView);
        allEventTextView = v.findViewById(R.id.allEventTextView);

        HallEventTextView = v.findViewById(R.id.HallEventTextView);
        HomeEventTextView = v.findViewById(R.id.HomeEventTextView);
        anywhereEventTextView = v.findViewById(R.id.allEventsTextView2);

        casualEventTextView = v.findViewById(R.id.casualTextView);
        semiformalEventTextView = v.findViewById(R.id.semiformalTextView);
        anyWearEventTextView = v.findViewById(R.id.anyWearTextView);

        religiousButton = v.findViewById(R.id.religiousButton);
        nonReligiousButton = v.findViewById(R.id.nonReligiousButton);
        allEventsButton = v.findViewById(R.id.allEventsButton);

        HallEventButton = v.findViewById(R.id.HallButton);
        HomeEventButton = v.findViewById(R.id.HomeButton);
        anywhereButton = v.findViewById(R.id.anywhereEventsButton);

        casualButton = v.findViewById(R.id.casualButton);
        semiformalButton = v.findViewById(R.id.semiformalButton);
        anyWearButton = v.findViewById(R.id.anyWearButton);

        cancelButton = v.findViewById(R.id.cancel);
        applyButton = v.findViewById(R.id.apply);

        if(religious==1){
            religiousButton.setBackgroundResource(R.drawable.ic_check_yellow_24dp);
            nonReligiousButton.setBackgroundResource(R.color.peachyPeach);
            allEventsButton.setBackgroundResource(R.color.peachyPeach);
        }else if(religious==2){
            nonReligiousButton.setBackgroundResource(R.drawable.ic_check_yellow_24dp);
            religiousButton.setBackgroundResource(R.color.peachyPeach);
            allEventsButton.setBackgroundResource(R.color.peachyPeach);
        }else{
            allEventsButton.setBackgroundResource(R.drawable.ic_check_yellow_24dp);
            nonReligiousButton.setBackgroundResource(R.color.peachyPeach);
            religiousButton.setBackgroundResource(R.color.peachyPeach);
        }

        if(location==1){
            HallEventButton.setBackgroundResource(R.drawable.ic_check_yellow_24dp);
            HomeEventButton.setBackgroundResource(R.color.peachyPeach);
            anywhereButton.setBackgroundResource(R.color.peachyPeach);
        }else if(location==2){
            HomeEventButton.setBackgroundResource(R.drawable.ic_check_yellow_24dp);
            HallEventButton.setBackgroundResource(R.color.peachyPeach);
            anywhereButton.setBackgroundResource(R.color.peachyPeach);
        }else{
            anywhereButton.setBackgroundResource(R.drawable.ic_check_yellow_24dp);
            HallEventButton.setBackgroundResource(R.color.peachyPeach);
            HomeEventButton.setBackgroundResource(R.color.peachyPeach);
        }

        if(dress==1){
            casualButton.setBackgroundResource(R.drawable.ic_check_yellow_24dp);
            semiformalButton.setBackgroundResource(R.color.peachyPeach);
            anyWearButton.setBackgroundResource(R.color.peachyPeach);

        }else if(dress==2){
            semiformalButton.setBackgroundResource(R.drawable.ic_check_yellow_24dp);
            casualButton.setBackgroundResource(R.color.peachyPeach);
            anyWearButton.setBackgroundResource(R.color.peachyPeach);

        }else{
            anyWearButton.setBackgroundResource(R.drawable.ic_check_yellow_24dp);
            casualButton.setBackgroundResource(R.color.peachyPeach);
            semiformalButton.setBackgroundResource(R.color.peachyPeach);

        }


        religiousEventTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                religious = 1;
                religiousButton.setBackgroundResource(R.drawable.ic_check_yellow_24dp);
                nonReligiousButton.setBackgroundResource(R.color.peachyPeach);
                allEventsButton.setBackgroundResource(R.color.peachyPeach);

            }
        });


        nonReligiousEventTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                religious = 2;
                nonReligiousButton.setBackgroundResource(R.drawable.ic_check_yellow_24dp);
                religiousButton.setBackgroundResource(R.color.peachyPeach);
                allEventsButton.setBackgroundResource(R.color.peachyPeach);
            }
        });


        allEventTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                religious = 3;
                allEventsButton.setBackgroundResource(R.drawable.ic_check_yellow_24dp);
                nonReligiousButton.setBackgroundResource(R.color.peachyPeach);
                religiousButton.setBackgroundResource(R.color.peachyPeach);
            }
        });






        HallEventTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                location = 1;
                HallEventButton.setBackgroundResource(R.drawable.ic_check_yellow_24dp);
                HomeEventButton.setBackgroundResource(R.color.peachyPeach);
                anywhereButton.setBackgroundResource(R.color.peachyPeach);

            }
        });


        HomeEventTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                location = 2;
                HomeEventButton.setBackgroundResource(R.drawable.ic_check_yellow_24dp);
                HallEventButton.setBackgroundResource(R.color.peachyPeach);
                anywhereButton.setBackgroundResource(R.color.peachyPeach);
            }
        });


        anywhereEventTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                location = 3;
                anywhereButton.setBackgroundResource(R.drawable.ic_check_yellow_24dp);
                HallEventButton.setBackgroundResource(R.color.peachyPeach);
                HomeEventButton.setBackgroundResource(R.color.peachyPeach);
            }
        });






        casualEventTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dress = 1;
                casualButton.setBackgroundResource(R.drawable.ic_check_yellow_24dp);
                semiformalButton.setBackgroundResource(R.color.peachyPeach);
                anyWearButton.setBackgroundResource(R.color.peachyPeach);

            }
        });


        semiformalEventTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dress = 2;
                semiformalButton.setBackgroundResource(R.drawable.ic_check_yellow_24dp);
                casualButton.setBackgroundResource(R.color.peachyPeach);
                anyWearButton.setBackgroundResource(R.color.peachyPeach);
            }
        });


        anyWearEventTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dress = 3;
                anyWearButton.setBackgroundResource(R.drawable.ic_check_yellow_24dp);
                casualButton.setBackgroundResource(R.color.peachyPeach);
                semiformalButton.setBackgroundResource(R.color.peachyPeach);
            }
        });




        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });


        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.applyFilters(religious,location,dress);
                dismiss();
            }
        });

        return v;

    }


    public interface bottomSheetListener{
        void applyFilters(int religious, int location, int dress);
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mListener = (bottomSheetListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
            +" must implement bottomSheetListener");
        }
    }
}

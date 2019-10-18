package com.example.tulips;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


public class FragmentHome extends Fragment {
    TextView textView;
    Fragment currentEventFragment;
    boolean eventDataExists = false;



    FragmentHome(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home,container,false);
        textView = v.findViewById(R.id.textView11);

        if(eventDataExists){
            if(textView.getVisibility()!=View.GONE){
                textView.setVisibility(View.GONE);
                FragmentTransaction ft = getChildFragmentManager().beginTransaction();
                currentEventFragment = new current_event_card_Fragment();
                ft.replace(R.id.current_event_fragment_container,currentEventFragment).commit();
            }

        }







        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(eventDataExists){
            downloadFinished();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


//        getSupportFragmentManager().beginTransaction().replace(R.id.current_event_fragment_container, currentEventFragment).commit();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);







    }

    public void downloadFinished(){
        textView.setVisibility(View.GONE);
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        currentEventFragment = new current_event_card_Fragment();
        ft.replace(R.id.current_event_fragment_container,currentEventFragment).commit();

    }
}

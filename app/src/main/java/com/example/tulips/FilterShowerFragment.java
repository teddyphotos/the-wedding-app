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
import androidx.fragment.app.Fragment;


public class FilterShowerFragment extends Fragment {
    private FilterShowerFragmentListener listener;
    TextView textView;
    Button button;
    View v;
    String filters;


    public interface FilterShowerFragmentListener{
        void onResetButtonClicked();
    }

    FilterShowerFragment(String filtesApplied){
        this.filters = filtesApplied;

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.filter_shower_fragment,container,false);

        button = v.findViewById(R.id.resetButton);
        textView = v.findViewById(R.id.myTextView);
        textView.setText("Filters : "+filters);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onResetButtonClicked();
            }
        });
        return v;
    }


    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void onResume() {
        super.onResume();

    }



    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if(context instanceof FilterShowerFragmentListener){
            listener = (FilterShowerFragmentListener) context;
        }else{
            throw new RuntimeException(context.toString()
            +" must implement FilterShowerFRagmentListener");
        }

    }


}

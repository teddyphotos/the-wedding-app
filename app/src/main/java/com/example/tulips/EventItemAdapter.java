package com.example.tulips;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import java.util.ArrayList;


public class EventItemAdapter extends RecyclerView.Adapter<EventItemAdapter.EventItemViewHolder> {
    private ArrayList<EventItem> eventItemArrayList;
    private OnVerseClickListener mVerseListener;
    private OnDetailClickListener mDetailListener;

    public interface OnVerseClickListener {
        void onVerseClicked(int position);

    }
    public interface OnDetailClickListener{
        void onDetailClicked(int position);
    }

    public void setOnVerseClickListener(OnVerseClickListener listener){
        mVerseListener = listener;
    }

    public void setOnDetailClickListener(OnDetailClickListener listener){
        mDetailListener = listener;
    }


    public static class EventItemViewHolder extends RecyclerView.ViewHolder{
        public TextView titleTextView;
        public TextView religiousTextView;
        public TextView descriptionTextView;
        public TextView locationTextView;
        public TextView scheduledTimeTextView;
        public TextView statusTextView;
        public TextView verseTextView;
        public TextView detailTextView;


        public EventItemViewHolder(@NonNull View itemView, final OnVerseClickListener listener, final OnDetailClickListener listener2) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.textView2);
            religiousTextView = itemView.findViewById(R.id.religiousTextView);
            descriptionTextView = itemView.findViewById(R.id.textView3);
            locationTextView = itemView.findViewById(R.id.textView4);
            scheduledTimeTextView = itemView.findViewById(R.id.textView5);
            statusTextView = itemView.findViewById(R.id.textView6);
            verseTextView = itemView.findViewById(R.id.textViewVerse);
            detailTextView = itemView.findViewById(R.id.textViewDetail);


            verseTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener!=null){
                        int position = getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            listener.onVerseClicked(position);
                        }
                    }

                }
            });

            detailTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener2!=null){
                        int position = getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            listener2.onDetailClicked(position);
                        }
                    }

                }
            });


//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if(listener != null){
//                        int position = getAdapterPosition();
//                        if(position != RecyclerView.NO_POSITION){
//                            listener.onVerseClicked(position);
//                        }
//                    }
//                }
//            });


        }
    }


//    public static class RecitefulEventViewHolder extends RecyclerView.ViewHolder{
//        public TextView titleTextView;
//        public TextView religiousTextView;
//        public TextView descriptionTextView;
//        public TextView locationTextView;
//        public TextView scheduledTimeTextView;
//        public TextView statusTextView;
//
//
//        public RecitefulEventViewHolder(@NonNull View itemView) {
//            super(itemView);
//            titleTextView = itemView.findViewById(R.id.textView2);
//            religiousTextView = itemView.findViewById(R.id.religiousTextView);
//            descriptionTextView = itemView.findViewById(R.id.textView3);
//            locationTextView = itemView.findViewById(R.id.textView4);
//            scheduledTimeTextView = itemView.findViewById(R.id.textView5);
//            statusTextView = itemView.findViewById(R.id.textView6);
//
//
//        }
//    }

    public EventItemAdapter(ArrayList<EventItem> eventItemArrayList){
        this.eventItemArrayList = eventItemArrayList;

    }

    @NonNull
    @Override
    public EventItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(viewType==0){
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_card_fragment, parent, false);
            EventItemViewHolder evh = new EventItemViewHolder(v, mVerseListener, mDetailListener);
            return evh;

        }else{
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.reciteful_event_card_fragment, parent, false);
            EventItemViewHolder evh = new EventItemViewHolder(v, mVerseListener, mDetailListener);
            return evh;
        }


    }

    @Override
    public void onBindViewHolder(@NonNull EventItemViewHolder holder, int position) {
        EventItem currentItem = eventItemArrayList.get(position);
        holder.titleTextView.setText(currentItem.getTitle());
        holder.statusTextView.setText(currentItem.getDress());
        holder.descriptionTextView.setText(currentItem.getDescription());
        holder.religiousTextView.setText(currentItem.getRelegious());
        holder.scheduledTimeTextView.setText(currentItem.getScheduledTime());
        holder.locationTextView.setText(currentItem.getLocation());



    }

    @Override
    public int getItemCount() {
        return eventItemArrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(eventItemArrayList.get(position).isRecietful()){
            return 1;
        }else{
            return 0;
        }
    }
}

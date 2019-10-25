package com.example.tulips;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jsibbold.zoomage.ZoomageView;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {
    private ArrayList<ExampleItem> mExampleList;
    private OnImageClickListener mListener;


    public interface OnImageClickListener{
        void onImageClicked(int position);
    }

    public void setOnImageClickListener(OnImageClickListener listener){
        this.mListener = listener;

    }


    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item, parent, false);
        ExampleViewHolder viewHolder = new ExampleViewHolder(v, mListener);
        return viewHolder;

    }

    public ExampleAdapter(ArrayList<ExampleItem> exampleList){
        this.mExampleList = exampleList;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        ExampleItem currentItem = mExampleList.get(position);
//        holder.mImageView.setImageResource(currentItem.getmImageBitmap());
        holder.mTextView1.setText(currentItem.getmText1());
        holder.mImageView.setImageBitmap(currentItem.getmImageBitmap());
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImageView;
        public TextView mTextView1;

        public ExampleViewHolder(@NonNull View itemView, final OnImageClickListener listener) {
            super(itemView);

            mImageView = itemView.findViewById(R.id.imageView2);
            mTextView1 = itemView.findViewById(R.id.textView);
            mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener!=null){
                        int position = getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            listener.onImageClicked(position);
                        }
                    }
                }
            });

        }
    }



}

package com.example.tulips;

public class ExampleItem {
    private int mImageResource;
    private String mText1;

    public ExampleItem(int mImageResource, String mText1){
        this.mImageResource = mImageResource;
        this.mText1 = mText1;
    }

    public int getmImageResource(){
        return mImageResource;
    }
    public String getmText1(){
        return mText1;
    }
}

package com.example.tulips;

import android.graphics.Bitmap;

public class ExampleItem {
    private Bitmap mImageResource;
    private String mText1;

    public ExampleItem(Bitmap mImageResource, String mText1){
        this.mImageResource = mImageResource;
        this.mText1 = mText1;

    }

    public Bitmap getmImageBitmap(){
        return mImageResource;
    }

    public String getmText1(){
        return mText1;
    }

}

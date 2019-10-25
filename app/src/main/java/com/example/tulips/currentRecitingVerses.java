package com.example.tulips;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class currentRecitingVerses extends AppCompatActivity  {
    private card_fragment card_fragment;
    private no_card_fragment no_card_fragment;
    String eventID;
    String eventTitle;
    Toolbar toolbar;
    JSONObject imageData;
    ArrayList<ExampleItem> versesList;
    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    Button nextButton;
    Button previousButton;
    int position = 0;
    public static final String EXTRA_IMAGE_ID = "com.example.tulips.timeline.extra_imageID";

//    String imageString = "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";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_reciting_verses);
        Intent intent = getIntent();
        eventID = intent.getStringExtra(FragmentTimeline.EXTRA_EVENT_ID);
        eventTitle = intent.getStringExtra(FragmentTimeline.EXTRA_EVENT_TITLE);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(eventTitle);
        versesList = new ArrayList<>();
        nextButton = findViewById(R.id.nextButton);
        previousButton = findViewById(R.id.previousButton);


        loadImageData();
        createExampleList();
        buildRecyclerView();

        if(versesList.size()==0){
            nextButton.setEnabled(false);
            previousButton.setEnabled(false);
        }

        LinearSnapHelper snapHelper = new LinearSnapHelper() {
            @Override
            public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int velocityX, int velocityY) {
                View centerView = findSnapView(layoutManager);
                if (centerView == null)
                    return RecyclerView.NO_POSITION;

                int position = layoutManager.getPosition(centerView);
                int targetPosition = -1;
                if (layoutManager.canScrollHorizontally()) {
                    if (velocityX < 0) {
                        targetPosition = position - 1;
                    } else {
                        targetPosition = position + 1;
                    }
                }

                if (layoutManager.canScrollVertically()) {
                    if (velocityY < 0) {
                        targetPosition = position - 1;
                    } else {
                        targetPosition = position + 1;
                    }
                }

                final int firstItem = 0;
                final int lastItem = layoutManager.getItemCount() - 1;
                targetPosition = Math.min(lastItem, Math.max(targetPosition, firstItem));
                return targetPosition;
            }
        };
        snapHelper.attachToRecyclerView(mRecyclerView);



        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position<versesList.size()){
                    RecyclerView.SmoothScroller smoothScroller = new LinearSmoothScroller(currentRecitingVerses.this) {
                        @Override protected int getVerticalSnapPreference() {
                            return LinearSmoothScroller.SNAP_TO_START;
                        }
                    };
                    smoothScroller.setTargetPosition(++position);
                    mLayoutManager.startSmoothScroll(smoothScroller);
                }

            }
        });

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position>0){
                    RecyclerView.SmoothScroller smoothScroller = new LinearSmoothScroller(currentRecitingVerses.this) {
                        @Override protected int getVerticalSnapPreference() {
                            return LinearSmoothScroller.SNAP_TO_START;
                        }
                    };
                    smoothScroller.setTargetPosition(--position);
                    mLayoutManager.startSmoothScroll(smoothScroller);                 }

            }
        });

    }


    private void createExampleList() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] imageBytes = baos.toByteArray();

        try {
            JSONArray jsonArray = imageData.getJSONArray("events");

            for(int i = 0; i < jsonArray.length();i++){
                JSONObject event =  jsonArray.getJSONObject(i);
                String eventId = event.getString("eventId");

                if(eventID.equals(eventId)){

                    String imageString = event.getString("image");
                    imageBytes = Base64.decode(imageString, Base64.DEFAULT);
                    Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
//                    imageView.setImageBitmap(decodedImage);
                    String description = "";

                    try {
                        if(event.has("description")){
                            description = event.getString("description");
                        }else{

                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    versesList.add(new ExampleItem(decodedImage, description));

                }
            }

        } catch (JSONException e) {
            e.printStackTrace();

        }

    }



    private void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mAdapter = new ExampleAdapter(versesList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnImageClickListener(new ExampleAdapter.OnImageClickListener() {
            @Override
            public void onImageClicked(int position) {
                Intent intent = new Intent(currentRecitingVerses.this, fullScreenImageView.class);
                Bitmap imageBitmap = versesList.get(position).getmImageBitmap();

                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                imageBitmap.compress(Bitmap.CompressFormat.PNG, 50, bs);
                intent.putExtra(EXTRA_IMAGE_ID, bs.toByteArray());
                startActivity(intent);
            }
        });
    }



    @Override
    protected void onStart() {
        super.onStart();
//        Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show();





    }

    @Override
    protected void onResume() {
        super.onResume();

    }


    public void loadImageData(){

            FileInputStream fis = null;
            try {
                fis = openFileInput(MainActivity.IMAGE_FILE_NAME);
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader br = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder();
                String imageString;

                while((imageString=br.readLine())!=null){
                    sb.append(imageString).append("\n");
                }

                String finalImageString = sb.toString();
                System.out.println(finalImageString);
//                Toast.makeText(this, "Image File Locked and Loaded", Toast.LENGTH_SHORT).show();
                imageData = new JSONObject(finalImageString);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
//                System.out.println("COULDN'T CONVERT IMAGE STRING TO JSON");
            } finally {
                if(fis!=null){
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }



    }
}

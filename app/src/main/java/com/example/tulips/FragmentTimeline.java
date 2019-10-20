package com.example.tulips;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FragmentTimeline extends Fragment implements FilterShowerFragment.FilterShowerFragmentListener{
    private RecyclerView mRecyclerView;
    private EventItemAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<EventItem> allEventItems;
    ArrayList<EventItem> thisDayItems;
    Button day1Button;
    Button day2Button;
    Button day3Button;
    View v;
    FilterShowerFragment filterShowerFragment;
    JSONObject eventDetails;
    private static final String FILE_NAME = "eventData.txt";
    boolean eventDataExists = false;
    boolean imageDataExists = false;
    public static final String EXTRA_EVENT_ID = "com.example.tulips.timeline.extra_eventID";
    public static final String EXTRA_EVENT_TITLE = "com.example.tulips.timeline.extra_eventTitle";





    String day = "30 Oct";
    int religious = 3;
    int location = 3;
    int dress = 3;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         v = inflater.inflate(R.layout.fragment_timeline, container, false);
         allEventItems = new ArrayList<>();
         thisDayItems = new ArrayList<>();

         loadData();

         createExampleList();
         buildRecyclerView();

        day1Button = v.findViewById(R.id.day1);
        day2Button = v.findViewById(R.id.day2);
        day3Button = v.findViewById(R.id.day3);

        FloatingActionButton floatingActionButton = v.findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog bottomSheetDialog = new bottomSheetDialog(religious,location,dress);
                bottomSheetDialog.show(getFragmentManager(), "exampleBotttomSheet");


            }
        });

        day1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                day = "30 Oct";
                day1Button.setBackgroundResource(R.drawable.rectangular_button_border);
                day2Button.setBackgroundResource(R.color.colorPrimary);
                day3Button.setBackgroundResource(R.color.colorPrimary);
//                mRecyclerView.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        mRecyclerView.smoothScrollToPosition(0);
//                    }
//                });
                day1Selected();



            }
        });

        day2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                day = "31 Oct";
                day2Button.setBackgroundResource(R.drawable.rectangular_button_border);
                day1Button.setBackgroundResource(R.color.colorPrimary);
                day3Button.setBackgroundResource(R.color.colorPrimary);
//                mRecyclerView.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        mRecyclerView.smoothScrollToPosition(mAdapter.getItemCount() - 2);
//                    }
//                });
                day2Selected();
            }
        });

        day3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                day = "1 Nov";
                day3Button.setBackgroundResource(R.drawable.rectangular_button_border);
                day2Button.setBackgroundResource(R.color.colorPrimary);
                day1Button.setBackgroundResource(R.color.colorPrimary);
//                mRecyclerView.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        mRecyclerView.smoothScrollToPosition(mAdapter.getItemCount() - 1);
//                    }
//                });
                day3Selected();

            }
        });

        return v;
    }



    private void createExampleList() {
//        allEventItems.add(new EventItem("Demo Event 1",
//                "Religious",
//                "The haldi ceremony is the one in which a paste of haldi is applied to bride and the groom’s body before their wedding",
//                "Inside",
//                "9 AM - 12:30 PM",
//                "Formal",
//                "1"));
//        allEventItems.add(new EventItem("Demo Event 2",
//                "Religious",
//                "A special lunch consisting of various sweets and delicacies will be served for the guests and bride and groom",
//                "Inside",
//                "12:30 PM - 2:30 PM",
//                "Informal",
//                "1"));
//        allEventItems.add(new EventItem("Demo Event 3",
//                "Religious",
//                "The haldi ceremony is the one in which a paste of haldi is applied to bride and the groom’s body before their wedding",
//                "Outside",
//                "9 AM - 12:30 PM",
//                "Formal",
//                "1"));
//        allEventItems.add(new EventItem("Demo Event 4",
//                "Religious",
//                "A special lunch consisting of various sweets and delicacies will be served for the guests and bride and groom",
//                "Outside",
//                "12:30 PM - 2:30 PM",
//                "Informal",
//                "1"));
//        allEventItems.add(new EventItem("Demo Event 5",
//                "Non Religious",
//                "The haldi ceremony is the one in which a paste of haldi is applied to bride and the groom’s body before their wedding",
//                "Inside",
//                "9 AM - 12:30 PM",
//                "Formal",
//                "1"));
//        allEventItems.add(new EventItem("Demo Event 6",
//                "Non Religious",
//                "A special lunch consisting of various sweets and delicacies will be served for the guests and bride and groom",
//                "Inside",
//                "12:30 PM - 2:30 PM",
//                "Informal",
//                "1"));
//        allEventItems.add(new EventItem("Demo Event 7",
//                "Non Religious",
//                "The haldi ceremony is the one in which a paste of haldi is applied to bride and the groom’s body before their wedding",
//                "Outside",
//                "9 AM - 12:30 PM",
//                "Formal",
//                "1"));
//        allEventItems.add(new EventItem("Demo Event 8",
//                "Non Religious",
//                "A special lunch consisting of various sweets and delicacies will be served for the guests and bride and groom",
//                "Outside",
//                "12:30 PM - 2:30 PM",
//                "Informal",
//                "1"));
//        allEventItems.add(new EventItem("Temple Visit",
//                "Religious",
//                "The bride's parents escort him to the marriage Mandapam where they perform a ritual of washing a leaf-laden branch of the Pipal tree with milk.",
//                "Outside",
//                "6:00 PM - 7:30 PM",
//                "Informal",
//                "2"));
//
//        allEventItems.add(new EventItem("Betrothal Ceremony",
//                "Religious",
//                "Vedic priest chants the appropriate hymns in which the names of the bride, the bridegroom, as well as the names of their 3 generations of ancestors, are cited in presence of all the friends, relatives, and guests.",
//                "Inside",
//                "8:00 PM - 10:30 PM",
//                "Formal",
//                "3"));
    }



    private void buildRecyclerView() {

        for(int i = 0; i < allEventItems.size();i++){
            if(allEventItems.get(i).getDate().equals("30 Oct")){
                thisDayItems.add(allEventItems.get(i));
            }
        }
        mRecyclerView = v.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new EventItemAdapter(thisDayItems);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new EventItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(int position) {
                if(thisDayItems.get(position).isRecietful()){

                    String eventID = thisDayItems.get(position).getEventID();
                    String eventTitle = thisDayItems.get(position).getTitle();
                    Intent i = new Intent(getActivity(), currentRecitingVerses.class);
                    i.putExtra(EXTRA_EVENT_ID, eventID);
                    i.putExtra(EXTRA_EVENT_TITLE, eventTitle);

                    startActivity(i);
                }
            }
        });
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    public void day1Selected(){
        day = "30 Oct";
        applyFilters(religious,location,dress);
    }



    public void day2Selected(){
        day = "31 Oct";
        applyFilters(religious,location,dress);
    }



    public void day3Selected(){
        day = "1 Nov";
        applyFilters(religious,location,dress);
    }





    public void applyFilters(int religious, int venue, int dress){
        this.religious = religious;
        this.location = venue;
        this.dress = dress;
        thisDayItems.clear();
        String mReligious = "";
        String mVenue = "";
        String mDress = "";

        switch (religious){
            case 1:
                mReligious = "Religious";
                break;
            case 2:
                mReligious = "Non Religious";
                break;
            case 3:
                mReligious = "Any";
                break;
        }
        switch (venue){
            case 1:
                mVenue = "Hall";
                break;
            case 2:
                mVenue = "Home";
                break;
            case 3:
                mVenue = "Any";
                break;
        }
        switch (dress){
            case 1:
                mDress = "Casual";
                break;
            case 2:
                mDress = "Semi-Formal";
                break;
            case 3:
                mDress = "Any";
                break;
        }


        for(int i = 0; i < allEventItems.size();i++){
            if(allEventItems.get(i).getDate().equals(day)){
                if(mReligious.equals("Any")){
//                    System.out.println("ITEM ADDED TO LIST "+ allEventItems.get(i).getTitle()+" : NEW LIST SIZE "+thisDayItems.size());
                    thisDayItems.add(allEventItems.get(i));
                }else{
                    if(allEventItems.get(i).getRelegious().equals(mReligious)){
//                        System.out.println("ITEM ADDED TO LIST "+ allEventItems.get(i).getTitle()+" : NEW LIST SIZE "+thisDayItems.size());

                        thisDayItems.add(allEventItems.get(i));

                    }
                }
            }

        }


        for(int i = 0; i < thisDayItems.size();i++){
            System.out.print("LOOKING AT "+thisDayItems.get(i).getTitle()+"\n");
            if(mVenue.equals("Any")){

            }else{
                if(!(thisDayItems.get(i).getVenue().equals(mVenue))){
//                    System.out.println("ITEM REMOVED FROM LIST "+ thisDayItems.get(i).getTitle()+" : NEW LIST SIZE "+(thisDayItems.size()-1));

                    thisDayItems.remove(i);

                    i = -1;
                }

            }
        }


        for(int i = 0; i < thisDayItems.size();i++){
//            System.out.print("LOOKING AT "+thisDayItems.get(i).getTitle()+"\n");
            if(mDress.equals("Any")){

            }else{
                if(!(thisDayItems.get(i).getDress().equals(mDress))){
//                    System.out.println("ITEM REMOVED FROM LIST "+ thisDayItems.get(i).getTitle()+" : NEW LIST SIZE "+(thisDayItems.size()-1));

                    thisDayItems.remove(i);

                    i = -1;
                }

            }
        }

        mAdapter.notifyDataSetChanged();


        if(religious==3 && venue==3 && dress ==3){

            if(filterShowerFragment!=null){
                try {
                    getFragmentManager().beginTransaction().remove(filterShowerFragment).commit();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }else{

            }





        }else{


            if(religious!=3){
                if(venue!=3){
                    if(dress!=3){
                        filterShowerFragment = new FilterShowerFragment(mReligious+", "+mVenue+", "+mDress);
                        getFragmentManager().beginTransaction()
                                .replace(R.id.filterShower, filterShowerFragment).commit();

                    }else{
                        filterShowerFragment = new FilterShowerFragment(mReligious+", "+mVenue);
                        getFragmentManager().beginTransaction()
                                .replace(R.id.filterShower, filterShowerFragment).commit();

                    }
                }else{
                    if(dress!=3){
                        filterShowerFragment = new FilterShowerFragment(mReligious+", "+mDress);
                        getFragmentManager().beginTransaction()
                                .replace(R.id.filterShower, filterShowerFragment).commit();

                    }else{
//                        filterShowerFragment.addFilters(mReligious);
                        filterShowerFragment = new FilterShowerFragment(mReligious);
                        getFragmentManager().beginTransaction()
                                .replace(R.id.filterShower, filterShowerFragment).commit();
//                        filterShowerFragment.textView.setText(mReligious);
                    }

                }
            }else{
                if(venue!=3){
                    if(dress!=3){
                        filterShowerFragment = new FilterShowerFragment(mVenue+", "+mDress);
                        getFragmentManager().beginTransaction()
                                .replace(R.id.filterShower, filterShowerFragment).commit();
                    }else{
                        filterShowerFragment = new FilterShowerFragment(mVenue);
                        getFragmentManager().beginTransaction()
                                .replace(R.id.filterShower, filterShowerFragment).commit();

                    }
                }else{
                    if(dress!=3){
                        filterShowerFragment = new FilterShowerFragment(mDress);
                        getFragmentManager().beginTransaction()
                                .replace(R.id.filterShower, filterShowerFragment).commit();
                    }else{

                    }

                }

            }

        }

    }




    @Override
    public void onResetButtonClicked() {
//        Toast.makeText(getContext(), "Hurrahhh", Toast.LENGTH_SHORT).show();
    }


    public void loadData(){

        if(eventDataExists){
            FileInputStream fis = null;

            try {

                fis = getActivity().openFileInput(FILE_NAME);
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader br = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder();
                String eventString;

                while((eventString=br.readLine())!=null){
                    sb.append(eventString).append("\n");
                }

                String finalEventString = sb.toString();
//                System.out.println(finalEventString);
//                Toast.makeText(getContext(), "File Locked and Loaded", Toast.LENGTH_SHORT).show();
                eventDetails = new JSONObject(finalEventString);
//                System.out.println("CONVERSION SUCCESSFUL");

                try {
                    JSONArray jsonArray = eventDetails.getJSONArray("events");
                    for(int i = 0; i < jsonArray.length();i++){

                        JSONObject event =  jsonArray.getJSONObject(i);
                        String eventId = event.getString("_id");
                        String title = event.getString("name");
                        boolean isReligious = event.getBoolean("isReligious");
                        String description = event.getString("description");
                        String venue = event.getString("venue");
                        String location = event.getString("location");
                        String dressCode = event.getString("dressCode");
                        boolean reciteful = event.getBoolean("reciteful");
                        String date = event.getString("day");
                        String time = event.getString("time");

                        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                        SimpleDateFormat outputFormat = new SimpleDateFormat("d MMM");
                        Date datex = null;
                        String formattedDate = null;
                        try {
                            datex = inputFormat.parse(date);
                            formattedDate = outputFormat.format(datex);
//                            System.out.println(formattedDate);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        String mReligious = null;
                        if(isReligious){
                            mReligious = "Religious";
                        }else{
                            mReligious = "Non Religious";
                        }


                        allEventItems.add(new EventItem(eventId, title, mReligious, description, venue, time, dressCode, formattedDate, location, reciteful));






                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
//                System.out.println("COULDN'T CONVERT STRING TO JSON");
            } finally {
                if(fis!=null){
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }else{
//            System.out.println("Fragment TimeLine couldn't find the file in internal storage");
        }




    }

}

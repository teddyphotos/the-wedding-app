package com.example.tulips;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity implements bottomSheetDialog.bottomSheetListener, FilterShowerFragment.FilterShowerFragmentListener{   //implements NavigationView.OnNavigationItemSelectedListener
//    private DrawerLayout drawer;
    Toolbar toolbar;
    Fragment selectedFragment = null;
    FragmentTimeline fragmentTimeline;

    private RequestQueue mQueue;

    boolean eventDataExists = false;
    boolean imageDataExists = false;
    boolean eventDataDownloaded = false;
    boolean imageDataDownloaded = false;
    GetInitialData getInitialData;
    GetImageData getImageData;

    String initialUrl = "https://dp-wedding-app.herokuapp.com/getevents";
    String imageDataURL = "https://dp-wedding-app.herokuapp.com/geteventdetails";
    FragmentHome fragmentHome;
    public static final String FILE_NAME = "eventData.txt";
    public static final String IMAGE_FILE_NAME = "imageData.txt";
    JSONObject eventDetails;
    JSONObject imageData;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String EVENT_DATA_STATE = "wasEventDownloaded";
    public static final String IMAGE_DATA_STATE = "wasImageDownloaded";

    private BottomNavigationView bottomNavigationView;






    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {


            int scheduleSelected = 0;
            switch(menuItem.getItemId()){
                case R.id.nav_home:
                    selectedFragment = fragmentHome;
//                    toolbar.setTitle("Home");
//                    toolbar.setVisibility(View.VISIBLE);

                    break;
                case R.id.nav_about:
                    selectedFragment = new FragmentAbout();
//                    toolbar.setTitle("About");
//                    toolbar.setVisibility(View.VISIBLE);

                    break;
                case R.id.nav_schedule:
                    scheduleSelected = 1;
                    break;
            }
            if(scheduleSelected==0){
                if(eventDataDownloaded && imageDataDownloaded){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();
                }else{
                    Toast.makeText(MainActivity.this, "Please Wait...", Toast.LENGTH_SHORT).show();
                }



            }else{
                if(eventDataDownloaded && imageDataDownloaded){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            fragmentTimeline).commit();
                }else{
                    Toast.makeText(MainActivity.this, "Please Wait...", Toast.LENGTH_SHORT).show();
                }

            }

            if(menuItem.getItemId()==R.id.nav_home || menuItem.getItemId()==R.id.nav_about){

                if(eventDataDownloaded && imageDataDownloaded){
                    switch (menuItem.getItemId()){
                        case R.id.nav_home:
                            toolbar.setTitle("Home");
                            toolbar.setVisibility(View.VISIBLE);
                            break;
                        case R.id.nav_about:
                            toolbar.setTitle("About");
                            toolbar.setVisibility(View.VISIBLE);
                            break;
                    }

                }
            }else{
                if(eventDataDownloaded && imageDataDownloaded){
                    toolbar.setTitle("");
                    toolbar.setVisibility(View.GONE);
                }

            }
            return true;
        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentHome = new FragmentHome();
        fragmentTimeline = new FragmentTimeline();
        bottomNavigationView = findViewById(R.id.bottom_navigation);


        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragmentHome).commit();

//        bottomNavigationView.setClickable(false);
        bottomNavigationView.getMenu().getItem(0).setEnabled(false);
        bottomNavigationView.getMenu().getItem(1).setEnabled(false);
        bottomNavigationView.getMenu().getItem(2).setEnabled(false);



        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Home");

        mQueue = Volley.newRequestQueue(this);
        getInitialData = new GetInitialData();
        getImageData = new GetImageData();

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        boolean haveData = sharedPreferences.getBoolean(EVENT_DATA_STATE, false);
        boolean haveImageData = sharedPreferences.getBoolean(IMAGE_DATA_STATE, false);

        if(haveData && haveImageData){

//            Toast.makeText(this, "We Got Both Data !", Toast.LENGTH_SHORT).show();
            fragmentHome.eventDataExists = true;
            fragmentTimeline.eventDataExists = true;
            eventDataExists = true;
            fragmentHome.imageDataExists = true;
            fragmentTimeline.imageDataExists = true;
            imageDataExists = true;
            loadData();
            loadImageData();
            imageDataDownloaded  =true;
            eventDataDownloaded = true;
//            bottomNavigationView.setClickable(true);                                              NEEDS CHECKING
            bottomNavigationView.getMenu().getItem(0).setEnabled(true);
            bottomNavigationView.getMenu().getItem(1).setEnabled(true);
            bottomNavigationView.getMenu().getItem(2).setEnabled(true);
        }else{
            if(isNetworkAvailable()){
                getInitialData.execute();
                getImageData.execute();
            }else{
//                fragmentHome.updateTextView(("There is no internet Connection"));
            }


        }






    }


    public void loadData(){

        if(eventDataExists){
            FileInputStream fis = null;

            try {

                fis = openFileInput(FILE_NAME);
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader br = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder();
                String eventString;

                while((eventString=br.readLine())!=null){
                    sb.append(eventString).append("\n");
                }

                String finalEventString = sb.toString();
//                System.out.println(finalEventString);
//                Toast.makeText(this, "File Locked and Loaded", Toast.LENGTH_SHORT).show();
                eventDetails = new JSONObject(finalEventString);
                eventDataDownloaded = true;
                changeBottomNavigationBarEnable();




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
//            System.out.println("Main Activity couldn't find the file in internal storage");
        }




    }

    public void changeBottomNavigationBarEnable(){
        if(imageDataDownloaded && eventDataDownloaded){
//            bottomNavigationView.setClickable(true);                                              NEEDS CHECKING
            bottomNavigationView.getMenu().getItem(0).setEnabled(true);
            bottomNavigationView.getMenu().getItem(1).setEnabled(true);
            bottomNavigationView.getMenu().getItem(2).setEnabled(true);
        }

    }


    @Override
    protected void onResume() {
        super.onResume();
        makePreperations();

    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    public void makePreperations(){
        if(!imageDataDownloaded || !eventDataDownloaded){
            if(isNetworkAvailable()) {
                fragmentHome.updateTextView("Downloading Wedding Schedule\nPlease Wait");
                if(getInitialData.getStatus()==AsyncTask.Status.RUNNING){

                }else{
                    GetInitialData getData = new GetInitialData();
                    getData.execute();
                }
                if(getImageData.getStatus()==AsyncTask.Status.RUNNING){

                }else{
                    GetImageData getData = new GetImageData();
                    getData.execute();
                }

            }

        }

    }


    public void loadImageData(){

        if(imageDataExists){
            FileInputStream fis = null;

            try {

                fis = openFileInput(IMAGE_FILE_NAME);
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader br = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder();
                String imageString;

                while((imageString=br.readLine())!=null){
                    sb.append(imageString).append("\n");
                }

                String finalImageString = sb.toString();
//                System.out.println(finalImageString);
//                Toast.makeText(this, "Image File Locked and Loaded", Toast.LENGTH_SHORT).show();
                imageData = new JSONObject(finalImageString);
                imageDataDownloaded = true;

                changeBottomNavigationBarEnable();
                fragmentHome.eventDataExists = true;
                fragmentHome.imageDataExists = true;

                if(fragmentHome.textView!=null){
                    if(fragmentHome.textView.getVisibility()!=View.GONE){
                        fragmentHome.downloadFinished();
                    }

                }

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

        }else{
//            System.out.println("Main Activity couldn't find the image file in internal storage");
        }

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus)
            makePreperations();

    }

    @Override
    public void applyFilters(int religious, int location, int dress) {
        fragmentTimeline.applyFilters(religious,location,dress);
    }

    @Override
    public void onResetButtonClicked() {
//        Toast.makeText(this, "Reset Clicked", Toast.LENGTH_SHORT).show();
        fragmentTimeline.applyFilters(3,3,3);

    }

    public class GetImageData extends AsyncTask<String, String, String>{
        @Override
        protected String doInBackground(String... strings) {
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, imageDataURL, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
//                            Toast.makeText(MainActivity.this, "Finished Fetching Image Data", Toast.LENGTH_SHORT).show();
                            Log.d("SUCCESSFULLL","JSON FILE FETCHED SUCCESSFULLY");
                            String mydata = response.toString();
//                            System.out.println(mydata);

                            FileOutputStream fos = null;

                            try {

                                fos = openFileOutput(IMAGE_FILE_NAME, MODE_PRIVATE);
                                fos.write(mydata.getBytes());
                                Log.d("IMAGE DATA FILE WRITTEN","Image file has been written to internal storage");
                                imageDataExists = true;
                                if(fragmentTimeline!=null){
                                    fragmentTimeline.imageDataExists = true;
                                }
                                if(fragmentHome!=null){
                                    fragmentHome.imageDataExists = true;
                                }

                                saveImageData(true);
                                loadImageData();



                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }finally {
                                if (fos != null){
                                    try {
                                        fos.close();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }




                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                            Toast.makeText(MainActivity.this, "Error Downloading Images", Toast.LENGTH_SHORT).show();
                            Log.d("ERRORRRR","THERE WAS AN ERROR DOWNLOADING IMAGE");
                        }
            });
            mQueue.add(request);
            return null;
        }
    }



    public class GetInitialData extends AsyncTask<String, String, String>{
        @Override
        protected String doInBackground(String... strings) {
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, initialUrl, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
//                            Toast.makeText(MainActivity.this, "Finished Fetching Data", Toast.LENGTH_SHORT).show();
                            Log.d("SUCCESSFULLL","JSON FILE FETCHED SUCCESSFULLY");
                            String mydata = response.toString();
//                            System.out.println(mydata);
//                            fragmentHome.downloadFinished();

                            FileOutputStream fos = null;

                            try {

                                fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
                                fos.write(mydata.getBytes());
                                Log.d("EVENT FILE WRITTEN","Event file has been written to internal storage");
                                eventDataExists = true;
                                if(fragmentTimeline!=null){
                                    fragmentTimeline.eventDataExists = true;
                                }
                                if(fragmentHome!=null){
                                    fragmentHome.eventDataExists = true;
                                }

                                saveData(true);
                                loadData();



                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }finally {
                                if (fos != null){
                                    try {
                                        fos.close();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                            Toast.makeText(MainActivity.this, "Error downloading Data", Toast.LENGTH_SHORT).show();
                            Log.d("ERRORRRR","THERE WAS AN ERROR");
                            fragmentHome.textView.setText("There is no internet Connection");

                        }
                    });
            mQueue.add(request);
            return null;
        }
    }

    private void saveImageData(boolean state){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(IMAGE_DATA_STATE, state);
        editor.apply();
    }




    private void saveData(boolean state) {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(EVENT_DATA_STATE, state);
        editor.apply();
    }





}

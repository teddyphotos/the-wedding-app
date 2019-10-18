package com.example.tulips;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
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
    String initialUrl = "https://dp-wedding-app.herokuapp.com/getevents";
    FragmentHome fragmentHome;
    private static final String FILE_NAME = "eventData.txt";
    JSONObject eventDetails;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String EVENT_DATA_STATE = "wasEventDownloaded";






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
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        selectedFragment).commit();

            }else{
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        fragmentTimeline).commit();

            }

            if(menuItem.getItemId()==R.id.nav_home || menuItem.getItemId()==R.id.nav_about){
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

            }else{
                toolbar.setTitle("");
                toolbar.setVisibility(View.GONE);
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

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragmentHome).commit();



        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Home");

        mQueue = Volley.newRequestQueue(this);
        GetInitialData getInitialData = new GetInitialData();
        Toast.makeText(MainActivity.this, "Starting Data Fetch", Toast.LENGTH_SHORT).show();

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        boolean haveData = sharedPreferences.getBoolean(EVENT_DATA_STATE, false);

        if(haveData){

            Toast.makeText(this, "We Got Data !", Toast.LENGTH_SHORT).show();
            fragmentHome.eventDataExists = true;
            fragmentTimeline.eventDataExists = true;
            eventDataExists = true;
            loadData();
        }else{
            getInitialData.execute();
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
                System.out.println(finalEventString);
                Toast.makeText(this, "File Locked and Loaded", Toast.LENGTH_SHORT).show();
                eventDetails = new JSONObject(finalEventString);



            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
                System.out.println("COULDN'T CONVERT STRING TO JSON");
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
            System.out.println("Main Activity couldn't find the file in internal storage");
        }




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



    public class GetInitialData extends AsyncTask<String, String, String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);




        }
        @Override
        protected String doInBackground(String... strings) {
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, initialUrl, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Toast.makeText(MainActivity.this, "Finished Fetching Data", Toast.LENGTH_SHORT).show();
                            Log.d("SUCCESSFULLL","JSON FILE FETCHED SUCCESSFULLY");
                            String mydata = response.toString();
                            System.out.println(mydata);
                            fragmentHome.downloadFinished();

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
                            Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                            Log.d("ERRORRRR","THERE WAS AN ERROR");
                            fragmentHome.textView.setText("There is no internet Connection");

                        }
                    });
            mQueue.add(request);
            return null;
        }
    }

    private void saveData(boolean state) {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(EVENT_DATA_STATE, state);
        editor.apply();


    }


}

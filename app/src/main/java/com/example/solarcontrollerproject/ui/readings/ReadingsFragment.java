package com.example.solarcontrollerproject.ui.readings;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.solarcontrollerproject.HistoryActivity;
import com.example.solarcontrollerproject.R;
import com.example.solarcontrollerproject.ReadingsStructure;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class ReadingsFragment extends Fragment{

    private ReadingsViewModel readingsViewModel;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    ReadingsStructure mData;
    private TextView longitude;
    private TextView latitude;
    private TextView elevation;
    private TextView azimuth;
    private TextView timezone;
    private TextView timestamp;
    private Button history;
    private TextView currentRead;
    private TextView aveRead;
    private TextView totalRead;
    private static DecimalFormat df2;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        readingsViewModel =
                ViewModelProviders.of(this).get(ReadingsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_readings, container, false);
        longitude = root.findViewById(R.id.tv_longitude);
        latitude = root.findViewById(R.id.tv_latitude);
        elevation = root.findViewById(R.id.tv_elevation);
        azimuth = root.findViewById(R.id.tv_azimuth);
        timezone = root.findViewById(R.id.tv_timezone);
        timestamp = root.findViewById(R.id.tv_timestamp);
        currentRead = root.findViewById(R.id.tv_currentRead);
        aveRead = root.findViewById(R.id.tv_aveRead);
        totalRead = root.findViewById(R.id.tv_totalRead);
        history = root.findViewById(R.id.bt_history);

       // barchart = root.findViewById(R.id.firebasebar_chart);


        getDatabase();
        reterieveData();

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getActivity().getApplicationContext(), HistoryActivity.class);
                startActivity(intent1);
            }
        });

        return root;
    }

    private void getDatabase(){
        // TODO: Find the reference form the database.
        database = FirebaseDatabase.getInstance();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String path = "userdata/" + mAuth.getUid();  // read from the user account.
        myRef = database.getReference(path);
    }
    private void reterieveData(){
        // TODO: Get the data on a single node.
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                df2 = new DecimalFormat("#.##");
                ReadingsStructure ds = dataSnapshot.getValue(ReadingsStructure.class);
                longitude.setText("Longitude: "+ ds.getLongitude());
                latitude.setText("Latitude: "+ds.getLatitude());
                elevation.setText("Elevation: " + ds.getElevation()+ "\u00B0");
                azimuth.setText("Azimuth: " + ds.getAzimuth()+ "\u00B0");
                timezone.setText("Timezone: " + ds.getTimezone());
                timestamp.setText("Timestamp: " +convertTimestamp(ds.getTimestamp()));
                currentRead.setText("Current Reading: " + df2.format(ds.getCurrentHarvest()) + "kWh");
                aveRead.setText("Average Reading: " + df2.format(ds.getAverageHarvest()) + "kWh");
                totalRead.setText("Total Reading: " + df2.format(ds.getTotalHarvest()) + "kWh");
            }

            private String convertTimestamp(String timestamp){

                long yourSeconds = Long.valueOf(timestamp);
                Date mDate = new Date(yourSeconds * 1000);
                DateFormat df = new SimpleDateFormat("dd MMM yyyy hh:mm:ss a");
                return df.format(mDate);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    ReadingsStructure ds = dataSnapshot.getValue(ReadingsStructure.class);
                    longitude.setText("Longitude: "+ ds.getLongitude());
                    latitude.setText("Latitude: "+ds.getLatitude());
                    elevation.setText("Elevation: " + ds.getElevation());
                    azimuth.setText("Azimuth: " + ds.getAzimuth());
                    timestamp.setText("Timestamp: " +convertTimestamp(ds.getTimestamp()));
                currentRead.setText("Current Reading: " + df2.format(ds.getCurrentHarvest()) + "kWh");
                aveRead.setText("Average Reading: " + df2.format(ds.getAverageHarvest()) + "kWh");
                totalRead.setText("Total Reading: " + df2.format(ds.getTotalHarvest()) + "kWh");



            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        // TODO: Get the whole data array on a reference.
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<ReadingsStructure> arraylist= new ArrayList<ReadingsStructure>();

                // TODO: Now data is retrieved, needs to process data.
                if (dataSnapshot != null && dataSnapshot.getValue() != null) {

                    // iterate all the items in the dataSnapshot
                    for (DataSnapshot a : dataSnapshot.getChildren()) {
                        ReadingsStructure dataStructure = new ReadingsStructure();
                        dataStructure.setLongitude(a.getValue(ReadingsStructure.class).getLongitude());
                        dataStructure.setLatitude(a.getValue(ReadingsStructure.class).getLatitude());
                        dataStructure.setElevation(a.getValue(ReadingsStructure.class).getElevation());
                        dataStructure.setTimezone(a.getValue(ReadingsStructure.class).getTimezone());
                        dataStructure.setAzimuth(a.getValue(ReadingsStructure.class).getAzimuth());
                        dataStructure.setCurrentHarvest(a.getValue(ReadingsStructure.class).getCurrentHarvest());
                        dataStructure.setAverageHarvest(a.getValue(ReadingsStructure.class).getAverageHarvest());
                        dataStructure.setTotalHarvest(a.getValue(ReadingsStructure.class).getTotalHarvest());
                        dataStructure.setTimestamp(a.getValue(ReadingsStructure.class).getTimestamp());

                        arraylist.add(dataStructure);  // now all the data is in arraylist.
                        Log.d("MapleLeaf", "dataStructure " + dataStructure.getTimestamp());
                    }
                }else
                {
                    //Toast.makeText(getApplicationContext(),"Data unavailable", Toast.LENGTH_LONG).show();
                }

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Getting data failed, log a message
                Log.d("MapleLeaf", "Data Loading Canceled/Failed.", databaseError.toException());
            }
        });
    }



}
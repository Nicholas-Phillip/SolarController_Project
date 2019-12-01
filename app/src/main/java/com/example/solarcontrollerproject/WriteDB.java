package com.example.solarcontrollerproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WriteDB extends AppCompatActivity {
    private Button save;
    private EditText longitude;
    private EditText latitude;
    private EditText elevation;
    private EditText azimuth;
    private EditText timezone1;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    ReadingsStructure mData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_db);
        findAllViews();
        getDatabase();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeData(longitude.getText(),latitude.getText(),elevation.getText(),azimuth.getText(),timezone1.getText());
            }
        });
    }

    // Find all the views for this activity.
    private void findAllViews(){
        save = findViewById(R.id.save);
        longitude = findViewById(R.id.et_longitude);
        latitude = findViewById(R.id.et_latitude);
        elevation = findViewById(R.id.et_elevation);
        azimuth = findViewById(R.id.et_azimuth);
        timezone1=findViewById(R.id.et_timezone);
    }

    private void writeData(Editable longitude, Editable latitude, Editable elevation, Editable azimuth, Editable timezone1) {

        ReadingsStructure mData = createData(longitude, latitude, elevation, azimuth,timezone1);
        // Select one of the following methods to update the data.
        // 1. To set the value of data
        // myRef.setValue(mData);
        // 2. To create a new node on database.
        //  myRef.push().setValue(mData);
        // TODO: Write the data to the database.
        // 3. To create a new node on database and detect if the writing is successful.
        myRef.push().setValue(mData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(), "Value was set. ", Toast.LENGTH_LONG).show();
                //gotoRead();  // after write the data, read it from another screen.
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Writing failed", Toast.LENGTH_LONG).show();
            }
        });
    }

    private ReadingsStructure createData(Editable longitude, Editable latitude, Editable elevation, Editable azimuth,Editable timezone1){
        // TODO: Get the timestamp
        Long time = System.currentTimeMillis()/1000;
        String timestamp = time.toString();
         double currentHarvest = (int)(Math.random()*((100-45)+1))+45;
         double averageHarvest = (int)(Math.random()*((100-45)+1))+45;
         double totalHarvest = (int)(Math.random()*((100-45)+1))+45;

        return new ReadingsStructure(String.valueOf(longitude),
                String.valueOf(latitude),
                String.valueOf(elevation),
                String.valueOf(azimuth),
                String.valueOf(timezone1),
                currentHarvest,
                averageHarvest,
                totalHarvest,
                timestamp);
    }
    private void getDatabase(){
        // TODO: Find the reference form the database.
        database = FirebaseDatabase.getInstance();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        String path = "userdata/" + mAuth.getUid();  // Write to the user account.
        myRef = database.getReference(path);

    }
}

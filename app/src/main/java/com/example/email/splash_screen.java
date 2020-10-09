package com.example.email;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

public class splash_screen extends AppCompatActivity {
    DatabaseReference databaseReference;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        fAuth = FirebaseAuth.getInstance();
        FirebaseUser user = fAuth.getCurrentUser();
        if (user == null) {
            startActivity(new Intent(getApplicationContext(), register.class));
        } else {

            Log.e("Test", "Step 1");
            DatabaseReference mPostReference = FirebaseDatabase.getInstance().getReference("users");
            Log.e("Test", "Step 1.1 - " + user.getUid());
            mPostReference.child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Log.e("Test", "Step 2");
                    //loginData data = (loginData) dataSnapshot.getValue();
                    if (dataSnapshot != null && dataSnapshot.hasChildren() &&
                            dataSnapshot.child("user_Type") != null && dataSnapshot.child("user_Type").getValue() != null) {
                        String name = dataSnapshot.child("user_Type").getValue().toString();
                        Log.e("Test", name);
                        Toast.makeText(getApplicationContext(), name, Toast.LENGTH_SHORT).show();

                        if (name.equals("Doctor")) {
                            startActivity(new Intent(getApplicationContext(), doctor.class));
                        } else if (name.equals("Laboratory")) {
                            startActivity(new Intent(getApplicationContext(), laboratory.class));
                        } else if (name.equals("Patient")) {
                            startActivity(new Intent(getApplicationContext(), patient.class));
                        }
                    } else {
                        startActivity(new Intent(getApplicationContext(), login.class));
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.e("Database error:", error.getMessage());
                }
            });
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //startActivity(new Intent(getApplicationContext(),register.class));
            finish();}
        },8000);
    }
}
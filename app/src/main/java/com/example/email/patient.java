package com.example.email;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class patient extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient);


        //bottomnavigation
        BottomNavigationView bottomnav=findViewById(R.id.bottom_navigation);
        bottomnav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new patient_home()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.home:
                            selectedFragment = new patient_home();
                            break;
                        case R.id.appointment:
                            selectedFragment = new patient_appointment();
                            break;
                        case R.id.profil:
                            selectedFragment = new profile();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                    return true;

                }
            };

    //logout
    public void logout(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),login.class));
        finish();

    }
    //dialogbox
    public void  onBackPressed(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(patient.this);
        builder.setMessage("Do you really want to close the app ?");
        builder.setCancelable(true);
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
package com.example.email;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class register extends AppCompatActivity {
    TextView loginhere;
    EditText fullname,email,password,number;
    Button register;
    ProgressBar progressBar;
    FirebaseAuth fAuth;
    Spinner spinner;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        try {


        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        fullname=findViewById(R.id.fullname);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        number=findViewById(R.id.number);
        register=findViewById(R.id.login);
        loginhere=findViewById(R.id.loginhere);
        spinner=findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.abc,R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        fAuth=FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.progressBar);
//        if (fAuth.getCurrentUser() != null){
//
//            FirebaseUser user = fAuth.getCurrentUser();
//            Log.e("Test", "Step 1");
//            DatabaseReference mPostReference = FirebaseDatabase.getInstance().getReference("users");
//            Log.e("Test", "Step 1.1 - " + user.getUid());
//            mPostReference.child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    Log.e("Test", "Step 2");
//                    //loginData data = (loginData) dataSnapshot.getValue();
//                    if(dataSnapshot != null && dataSnapshot.hasChildren() &&
//                            dataSnapshot.child("user_Type") != null && dataSnapshot.child("user_Type").getValue() != null)
//                    {
//                        String name = dataSnapshot.child("user_Type").getValue().toString();
//                        Log.e("Test", name);
//                        Toast.makeText(getApplicationContext(), name, Toast.LENGTH_SHORT).show();
//
//                        if (name.equals("Doctor")) {
//                            startActivity(new Intent(getApplicationContext(), doctor.class));
//                        } else if (name.equals("Laboratory")) {
//                            startActivity(new Intent(getApplicationContext(), laboratory.class));
//                        } else if (name.equals("Patient")) {
//                            startActivity(new Intent(getApplicationContext(), patient.class));
//                        }
//                    }
//                    else
//                    {
//                        startActivity(new Intent(getApplicationContext(), login.class));
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//                    Log.e("Database error:", error.getMessage());
//                }
//            });
//
////            startActivity(new Intent(getApplicationContext(), doctor.class));
//            finish();
//            Log.e("Test", "Step 3");
//        }

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String item = spinner.getSelectedItem().toString();
                String name=fullname.getText().toString().trim();
                final String Email=email.getText().toString().trim();
                String Password=password.getText().toString().trim();
                String no=number.getText().toString().trim();

                if (TextUtils.isEmpty(name)){
                    fullname.setError("Name is Required");
                    return;
                }
                if (TextUtils.isEmpty(Email)){
                    email.setError("Email is Required");
                    return;
                }
                if (TextUtils.isEmpty(Password)){
                    password.setError("Password is Required");
                    return;
                }

                if (TextUtils.isEmpty(no)){
                    number.setError("Number is Required");
                    return;
                }
                if (Password.length() < 6){
                    password.setError("password must be >=6 characters");
                    return;
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches())
                {
                    Toast.makeText(getApplicationContext(),"Enter Valid Email address",Toast.LENGTH_LONG).show();
                }
                progressBar.setVisibility(View.VISIBLE);
//                // Database
//                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//                databaseReference.push().getKey();
//                final String id = user.getUid();
//                loginData loginData = new loginData(id, Email, item);
//                databaseReference.child(id).setValue(loginData);

                //register tha users in firebase

                fAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(register.this,"User Created",Toast.LENGTH_SHORT).show();

                        FirebaseUser user = task.getResult().getUser();
                        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
                        loginData loginData = new loginData(user.getUid(), Email, item);
                        mDatabase.child("users").child(user.getUid()).setValue(loginData);

                        startActivity(new Intent(getApplicationContext(), login.class));
                    }else {
                        Toast.makeText(register.this, "Error !" +task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }
                    }
                });
            }
        });

        loginhere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),login.class));
            }
        });
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
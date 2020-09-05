package com.example.email;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

public class register extends AppCompatActivity {
    TextView loginhere;
    EditText fullname,email,password,number;
    Button register;
    ProgressBar progressBar;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        fullname=findViewById(R.id.fullname);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        number=findViewById(R.id.number);
        register=findViewById(R.id.login);
        loginhere=findViewById(R.id.loginhere);

        fAuth=FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.progressBar);
        if (fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), doctor.class));
            finish();
        }

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Email=email.getText().toString().trim();
                String Password=password.getText().toString().trim();
                if (TextUtils.isEmpty(Email)){
                    email.setError("Email is Required");
                    return;
                }
                if (TextUtils.isEmpty(Password)){
                    password.setError("Password is Required");
                    return;
                }
                if (Password.length() < 6){
                    password.setError("password must be >=6 characters");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                //register tha users in firebase

                fAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(register.this,"User Created",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), doctor.class));
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
}
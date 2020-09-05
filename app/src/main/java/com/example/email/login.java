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

public class login extends AppCompatActivity {
    TextView new_account;
    EditText email,password;
    Button login;
    ProgressBar progressBar;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        new_account=findViewById(R.id.new_account);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        login=findViewById(R.id.login);
        progressBar=findViewById(R.id.progressBar);
        fAuth=FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
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

                //authenticated the user

                fAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            Toast.makeText(login.this,"Login is Successfully",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), doctor.class));
                        }else {
                            Toast.makeText(login.this, "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                        }

                });

            }
        });

        new_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),register.class));
            }
        });
    }
}
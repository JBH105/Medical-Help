package com.example.email;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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
                } else if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches())
                {
                    Toast.makeText(getApplicationContext(),"Enter Valid Email address",Toast.LENGTH_LONG).show();
                }
                progressBar.setVisibility(View.VISIBLE);

                //authenticated the user

                fAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){

                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            final String userid = user.getUid();

                            DatabaseReference mPostReference = FirebaseDatabase.getInstance().getReference("users");
                            mPostReference.child(userid).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {

                                    //loginData data = (loginData) dataSnapshot.getValue();
                                    String name = dataSnapshot.child("user_Type").getValue().toString();
                                    Log.e("Test",  name );
                                    Toast.makeText(getApplicationContext(), name, Toast.LENGTH_SHORT).show();
                                    if (name.equals("Doctor")){
                                        startActivity(new Intent(getApplicationContext(),doctor.class));
                                    }else if (name.equals("Laboratory")){
                                        startActivity(new Intent(getApplicationContext(),laboratory.class));
                                    }else if (name.equals("Patient")){
                                        startActivity(new Intent(getApplicationContext(),patient.class));
                                    }
                                    else {
                                        Toast.makeText(getApplicationContext(),"Select User Type",Toast.LENGTH_LONG).show();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
//                          startActivity(new Intent(getApplicationContext(), doctor.class));
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

    public static class database_patient {
        String id;
        String aname;
        String aemail;
        String anumber;
        String aaddress;
        String astreet;
        String acity;
        String azip;
        String adate;

        public database_patient(){

        }

        public database_patient(String id, String aname, String aemail, String anumber, String aaddress, String astreet, String acity, String azip, String adate) {
            this.id = id;
            this.aname = aname;
            this.aemail = aemail;
            this.anumber = anumber;
            this.aaddress = aaddress;
            this.astreet = astreet;
            this.acity = acity;
            this.azip = azip;
            this.adate = adate;
        }

        public String getAzip() {
            return azip;
        }

        public String getAdate() {
            return adate;
        }

        public database_patient(String id, String aname, String aemail, String anumber, String aaddress, String astreet, String acity) {
            this.id = id;
            this.aname = aname;
            this.aemail = aemail;
            this.anumber = anumber;
            this.aaddress = aaddress;
            this.astreet = astreet;
            this.acity = acity;
        }

        public String getId() {
            return id;
        }

        public String getAname() {
            return aname;
        }

        public String getAemail() {
            return aemail;
        }

        public String getAnumber() {
            return anumber;
        }

        public String getAaddress() {
            return aaddress;
        }

        public String getAstreet() {
            return astreet;
        }

        public String getAcity() {
            return acity;
        }
    }
}
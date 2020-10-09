package com.example.email;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class patient_profil extends AppCompatActivity {
    TextView textname,textemail,texttype;
    private DatabaseReference firebaseDatabase;
    private FirebaseUser user;
    private  String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_profil);

        user= FirebaseAuth.getInstance().getCurrentUser();
        firebaseDatabase =FirebaseDatabase.getInstance().getReference().child("users");//.child(loginData.getUser_Type());
        userID = user.getUid();

        textemail=findViewById(R.id.textemail);
        texttype=findViewById(R.id.texttype);
        textname=findViewById(R.id.textname);

        firebaseDatabase.child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap userprofil = (HashMap) snapshot.getValue();
                if (userprofil !=null){

                    String Email = (String) userprofil.get("email");
                    String number = (String) userprofil.get("number");
                    String name= (String) userprofil.get("name");

                    Log.i("kkl",name+" "+Email+"  "+number);

                    textemail.setText(Email);
                    texttype.setText(number);
                    textname.setText(name);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"Wrong",Toast.LENGTH_LONG).show();
            }
        });

    }
}

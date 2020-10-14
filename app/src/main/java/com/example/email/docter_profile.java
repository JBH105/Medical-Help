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
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class docter_profile extends Fragment {
    TextView textname,textemail,textnumber;
    private DatabaseReference firebaseDatabase;
    private FirebaseUser user;
    private  String userID;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.docter_profile, container, false);

        user= FirebaseAuth.getInstance().getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance().getReference().child("users");//.child(loginData.getUser_Type());
        userID = user.getUid();

        textemail=view.findViewById(R.id.textemail);
        textnumber=view.findViewById(R.id.textnumber);
        textname=view.findViewById(R.id.textname);

        firebaseDatabase.child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap userprofil = (HashMap) snapshot.getValue();
                if (userprofil !=null){

                    String Email = (String) userprofil.get("email");
                    String Number = (String) userprofil.get("number");
                    String Name= (String) userprofil.get("name");

                    Log.i("kkl",Name+" "+Email+"  "+Number);

                    textemail.setText(Email);
                    textnumber.setText(Number);
                    textname.setText(Name);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),"Wrong",Toast.LENGTH_LONG).show();
            }
        });
        return view;

    }
}

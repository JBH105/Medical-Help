package com.example.email;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class patient_appointment_details extends AppCompatActivity {
    TextView name,email,number,date;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    ListView listView;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_appointment_details);

        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        number=findViewById(R.id.number);
        date=findViewById(R.id.date);
        listView=findViewById(R.id.listView);

        firebaseAuth= FirebaseAuth.getInstance();
        databaseReference =FirebaseDatabase.getInstance().getReference().child("users_appointment");
        FirebaseUser user = firebaseAuth.getCurrentUser();

        arrayAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);

        databaseReference.child(user.getUid()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String value = dataSnapshot.getValue().toString();
                value = value.replace("{", "");
                value = value.replace("}", "");
//                Log.i("abc",dataSnapshot.toString());


                try{

                    Map<String, String> responseMap = splitToMap(value, ", ", "=");

                    String str="\nName: "+responseMap.get("aname")
                            +"\n"+"Email: "+responseMap.get("aemail")
                            +"\n"+ "Contact No.: "+responseMap.get("anumber")
                            +"\n"+ "Genger: "+responseMap.get("agender")
                            +"\n"+""+responseMap.get("adoctor")

                            +"\n"+ "Appointment_Date: "+responseMap.get("adate")
                            +"\n"+"Select Slot: "+responseMap.get("aslot")
                            +"\n";
                    arrayList.add(str);

                }catch (Exception e) {e.printStackTrace();
                    arrayList.add(e.getMessage());
                }

                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                arrayAdapter.notifyDataSetChanged();
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

            public Map<String, String> splitToMap(String source, String entriesSeparator, String keyValueSeparator) {
                Map<String, String> map = new HashMap<String, String>();
                String[] entries = source.split(entriesSeparator);
                for (String entry : entries) {
                    if (!TextUtils.isEmpty(entry) && entry.contains(keyValueSeparator)) {
                        String[] keyValue = entry.split(keyValueSeparator);
                        map.put(keyValue[0], keyValue[1]);
                    }
                }
                return map;
            }
        });

//        databaseReference.child(user.getUid()).addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//                String value = snapshot.getValue().toString();
//                arrayList.add(value);
//                arrayAdapter.notifyDataSetChanged();
//
//                Log.i("abc",value);
//
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
    }
}
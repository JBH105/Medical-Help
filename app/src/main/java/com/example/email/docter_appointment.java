package com.example.email;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class docter_appointment extends Fragment {
    ArrayList<String> myArrayList =new ArrayList<>();
    ListView mylistView;
    DatabaseReference mRef;
    ArrayAdapter<String> arrayAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View view= inflater.inflate(R.layout.docter_appointment,container,false);


        mRef= FirebaseDatabase.getInstance().getReference("database_patient");
        arrayAdapter= new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,myArrayList);

        mylistView=view.findViewById(R.id.listView);
        mylistView.setAdapter(arrayAdapter);

        mylistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                Intent intent = new Intent(getApplicationContext(), list_call.class);
//                intent.putExtra("DocInfo", myArrayList.get(position));
//                startActivity(intent);
                Toast.makeText(getContext(), myArrayList.get(position), Toast.LENGTH_SHORT).show();
            }
        });



        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String value = dataSnapshot.getValue().toString();
                value = value.replace("{", "");
                value = value.replace("}", "");


                try{

                    Map<String, String> responseMap = splitToMap(value, ", ", "=");

                    String str="\nName: "+responseMap.get("aname")
                            +"\n"+"Email: "+responseMap.get("aemail")
                            +"\n"+ "Address: "+responseMap.get("aaddress")
                            +"\n"+"City: "+responseMap.get("acity")
                            +"\n"+"Zipcode: "+responseMap.get("azip")
                            +"\n"+ "Contact No.: "+responseMap.get("anumber")
                            +"\n"+ "Appointment_Date: "+responseMap.get("adate")
                            +"\n";
                    myArrayList.add(str);

                }catch (Exception e) {e.printStackTrace();
                    myArrayList.add(e.getMessage());
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


        return view;
    }
}

package com.example.email;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class choose_doctor extends AppCompatActivity {

    ImageView bake;
    DatabaseReference databaseReference;
    FirebaseAuth auth;
    ArrayList<String> myArrayList =new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_doctor);
        bake=findViewById(R.id.back);

        bake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),patient_appointment.class));
            }
        });

        databaseReference = FirebaseDatabase.getInstance().getReference();

        ListView listView = findViewById(R.id.listView);
        arrayAdapter= new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,myArrayList);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getApplicationContext(), myArrayList.get(position), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.putExtra("abc",myArrayList.get(position));
                setResult(1,intent);
                finish();
            }
        });

        databaseReference.child("users").orderByChild("user_Type").equalTo("Doctor").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String value = dataSnapshot.getValue().toString();
                value = value.replace("{", "");
                value = value.replace("}", "");

                Log.i("abc",dataSnapshot.toString());


                try{

                    Map<String, String> responseMap = splitToMap(value, ", ", "=");

                    String str="\nDr.Name: "+responseMap.get("name")
                            +"\n"+"Hospital Address: "+responseMap.get("buildno")
                            +" "+ responseMap.get("lanno")
                            +" "+responseMap.get("cityno")
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

    }

    //dialogbox
    public void  onBackPressed(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(choose_doctor.this);
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
package com.example.email;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ListAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

import static android.R.layout.activity_list_item;

public class patient_appointment extends Fragment {
    EditText name,email,number,address,street,city,zipcode,date;
    TextView choose_doctor;
    Button sumbit;
    ConstraintLayout patient_appointmentConstraint;
    LinearLayout choose_doctorLinear;
    RadioGroup radioGroup;
    DatabaseReference databaseReference;
    FirebaseAuth auth;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view=  inflater.inflate(R.layout.patient_appointment,container,false);
        auth=FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        name=view.findViewById(R.id.name);
        email=view.findViewById(R.id.email);
        number=view.findViewById(R.id.number);
//        address=view.findViewById(R.id.address);
//        street=view.findViewById(R.id.street);
//        city=view.findViewById(R.id.city);
//        zipcode=view.findViewById(R.id.zipcode);
        date=view.findViewById(R.id.date);
        radioGroup=view.findViewById(R.id.radioGroup);
        sumbit=view.findViewById(R.id.submit);
        choose_doctor=view.findViewById(R.id.choose_doctor);
        choose_doctorLinear=view.findViewById(R.id.choose_doctorLinear);
        patient_appointmentConstraint=view.findViewById(R.id.patient_appointmentConstraint);

        radioGroup.clearCheck();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
//                if (null != rb && checkedId > -1) {
////                    Toast.makeText(MainActivity.this, rb.getText(), Toast.LENGTH_SHORT).show();
//                }
//
              }
        });

        choose_doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getContext(),choose_doctor.class));

                Log.i("kkl", "lll");
                patient_appointmentConstraint.setVisibility(View.GONE);
                choose_doctorLinear.setVisibility(View.VISIBLE);
            }
        });

        // Choose Doctor
        ListView listView = view.findViewById(R.id.listView);
        final ArrayList<String> arrayList = new ArrayList<>();
//        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,arrayList);
//        listView.setAdapter(arrayAdapter);

        databaseReference.child("users").orderByChild("user_Type").equalTo("Doctor").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String value = dataSnapshot.getValue().toString();
                value = value.replace("{", "");
                value = value.replace("}", "");
                Log.i("abc",dataSnapshot.toString());


                try{

                    Map<String, String> responseMap = splitToMap(value, ", ", "=");

                    String str="\nName: "+responseMap.get("name")
                            +"\n"+"Email: "+responseMap.get("email")
//                            +"\n"+ "Address: "+responseMap.get("aaddress")
//                            +"\n"+"City: "+responseMap.get("acity")
//                            +"\n"+"Zipcode: "+responseMap.get("azip")
                            +"\n"+ "Contact No.: "+responseMap.get("number")
//                            +"\n"+ "Appointment_Date: "+responseMap.get("adate")
                            +"\n";
                    arrayList.add(str);

                }catch (Exception e) {e.printStackTrace();
                    arrayList.add(e.getMessage());
                }

//                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                arrayAdapter.notifyDataSetChanged();
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

        //Log.i("1234", arrayList.toString());

        sumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = name.getText().toString().trim();
                String Email = email.getText().toString().trim();
                String Number = number.getText().toString().trim();
//                String Address = address.getText().toString().trim();
//                String Street = street.getText().toString().trim();
//                String City = city.getText().toString().trim();
//                String Zipcode = zipcode.getText().toString().trim();
                String Date = date.getText().toString().trim();
//                String Radiogroup = radioGroup.getText().toString().trim();

                if (TextUtils.isEmpty(Name)){
                    name.setError("Enter name");
                    return;
                }
                if (TextUtils.isEmpty(Email)){
                    email.setError("Enter email");
                    return;
                }
                if (TextUtils.isEmpty(Number) && Number.length()<10){
                    number.setError("Enter Number");
                    return;
                }

//                if (TextUtils.isEmpty(Address)){
//                    address.setError("Enter address");
//                    return;
//                }
//                if (TextUtils.isEmpty(Street)){
//                    street.setError("Enter street");
//                    return;
//                }
//                if (TextUtils.isEmpty(City)){
//                    city.setError("Enter city");
//                    return;
//                }
//                if (TextUtils.isEmpty(Zipcode)&& Zipcode.length()<6){
//                    zipcode.setError("Enter zipcode");
//                    return;
//                }
                if (TextUtils.isEmpty(Date)){
                    date.setError("Enter date");
                    return;
                }else if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches())
                {
                    Toast.makeText(getContext(),"Enter Valid Email address",Toast.LENGTH_LONG).show();
                    return;
                }



                FirebaseUser user = auth.getCurrentUser();
                String id = databaseReference.push().getKey();
                database_patient database = new database_patient(Name, Email, Number, Date);
                databaseReference.child("users_appointment").child(user.getUid()).child(id).setValue(database);

                Toast.makeText(getContext(),"confirm appointment",Toast.LENGTH_LONG).show();

                name.setText("");
                email.setText("");
                number.setText("");
                date.setText("");

            }
        });
   return  view;
    }
}
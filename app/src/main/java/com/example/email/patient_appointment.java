package com.example.email;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;

public class patient_appointment extends Fragment {
    EditText name,email,number,address,street,city,zipcode;
    TextView choose_doctor,date;
    Button sumbit;
    RadioGroup radioGroup;
    DatabaseReference databaseReference;
    FirebaseAuth auth;
//    ArrayList<String> myArrayList =new ArrayList<>();
//    ArrayAdapter<String> arrayAdapter;

    DatePickerDialog datePickerDialog;


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

        choose_doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), com.example.email.choose_doctor.class);
                startActivityForResult(intent,1);
            }
        });
        //DatePickerDialog

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                final  int year = calendar.get(Calendar.YEAR);
                final  int month = calendar.get(Calendar.MONTH);
                final  int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {

                        date.setText(day+"-"+(month+1)+"-"+year);
                    }
                },year,month,dayOfMonth);
                datePickerDialog.show();
            }
        });

        // Choose Doctor
//        ListView listView = view.findViewById(R.id.listView);
//        arrayAdapter= new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,myArrayList);
//        listView.setAdapter(arrayAdapter);
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                Intent intent = new Intent(getContext(), laboratory.class);
//                intent.putExtra("abc", myArrayList.get(position));
//                startActivity(intent);
//                Toast.makeText(getContext(), myArrayList.get(position), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        databaseReference.child("users").orderByChild("user_Type").equalTo("Doctor").addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                String value = dataSnapshot.getValue().toString();
//                value = value.replace("{", "");
//                value = value.replace("}", "");
//
//                Log.i("abc",dataSnapshot.toString());
//
//
//                try{
//
//                    Map<String, String> responseMap = splitToMap(value, ", ", "=");
//
//                    String str="\nDr.Name: "+responseMap.get("name")
//                            +"\n"+"Hospital Address: "+responseMap.get("buildno")
////                            +"\n"+ " "+responseMap.get("areano")
//                            +" "+ responseMap.get("lanno")
//                            +" "+responseMap.get("cityno")
//                            +"\n";
//                    myArrayList.add(str);
//
//                }catch (Exception e) {e.printStackTrace();
//                    myArrayList.add(e.getMessage());
//                }
//
//                arrayAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                arrayAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//
//            public Map<String, String> splitToMap(String source, String entriesSeparator, String keyValueSeparator) {
//                Map<String, String> map = new HashMap<String, String>();
//                String[] entries = source.split(entriesSeparator);
//                for (String entry : entries) {
//                    if (!TextUtils.isEmpty(entry) && entry.contains(keyValueSeparator)) {
//                        String[] keyValue = entry.split(keyValueSeparator);
//                        map.put(keyValue[0], keyValue[1]);
//                    }
//                }
//                return map;
//            }
//        });

        sumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = name.getText().toString().trim();
                String Email = email.getText().toString().trim();
                String Number = number.getText().toString().trim();
                String Date = date.getText().toString().trim();


                if (TextUtils.isEmpty(Name)){
                    name.setError("Enter name");
                    return;
                }
                if (TextUtils.isEmpty(Email)){
                    email.setError("Enter email");
                    return;
                }
                if (TextUtils.isEmpty(Number) || Number.length()<10){
                    number.setError("Enter Number");
                    return;
                }

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1){
            choose_doctor.setText(data.getStringExtra("abc"));
        }
    }
}
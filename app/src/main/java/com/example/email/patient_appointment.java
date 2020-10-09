package com.example.email;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class patient_appointment extends Fragment {
    EditText name,email,number,address,street,city,zipcode,date;
    Button sumbit;
    RadioGroup radioGroup;
    DatabaseReference databaseReference;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.patient_appointment,container,false);

        databaseReference = FirebaseDatabase.getInstance().getReference("database_patient");
        name=view.findViewById(R.id.name);
        email=view.findViewById(R.id.email);
        number=view.findViewById(R.id.number);
        address=view.findViewById(R.id.address);
        street=view.findViewById(R.id.street);
        city=view.findViewById(R.id.city);
        zipcode=view.findViewById(R.id.zipcode);
        date=view.findViewById(R.id.date);
        radioGroup=view.findViewById(R.id.radioGroup);
        sumbit=view.findViewById(R.id.submit);

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

        sumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = name.getText().toString().trim();
                String Email = email.getText().toString().trim();
                String Number = number.getText().toString().trim();
                String Address = address.getText().toString().trim();
                String Street = street.getText().toString().trim();
                String City = city.getText().toString().trim();
                String Zipcode = zipcode.getText().toString().trim();
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

                if (TextUtils.isEmpty(Address)){
                    address.setError("Enter address");
                    return;
                }
                if (TextUtils.isEmpty(Street)){
                    street.setError("Enter street");
                    return;
                }
                if (TextUtils.isEmpty(City)){
                    city.setError("Enter city");
                    return;
                }
                if (TextUtils.isEmpty(Zipcode)&& Zipcode.length()<6){
                    zipcode.setError("Enter zipcode");
                    return;
                }
                if (TextUtils.isEmpty(Date)){
                    date.setError("Enter date");
                    return;
                }else if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches())
                {
                    Toast.makeText(getContext(),"Enter Valid Email address",Toast.LENGTH_LONG).show();
                }

                String id = databaseReference.push().getKey();
                database_patient database = new database_patient(id, Name, Email, Number, Address, Street, City, Zipcode, Date);
                databaseReference.child(id).setValue(database);

                Toast.makeText(getContext(),"confirm appointment",Toast.LENGTH_LONG).show();

            }
        });
   return  view;
    }
}
package com.example.email;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class appointment_docter extends AppCompatActivity {
    EditText name,email,number,address,street,city,zipcode,date;
    Button sumbit;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appointment_docter);

        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        number=findViewById(R.id.number);
        address=findViewById(R.id.address);
        street=findViewById(R.id.street);
        city=findViewById(R.id.city);
        zipcode=findViewById(R.id.zipcode);
        date=findViewById(R.id.date);
        radioGroup=findViewById(R.id.radioGroup);
        sumbit=findViewById(R.id.submit);

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


            }
        });
    }
}
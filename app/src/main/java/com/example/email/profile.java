package com.example.email;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class profile extends Fragment {
    TextView profile,appointment,logout;
    ImageView imageView;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.profile,container,false);

        profile=view.findViewById(R.id.profile);
        appointment=view.findViewById(R.id.appointment);
        logout=view.findViewById(R.id.logout);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),patient_profil.class));
            }
        });
        return  view;
    }
}

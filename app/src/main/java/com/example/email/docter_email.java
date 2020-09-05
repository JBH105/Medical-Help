package com.example.email;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class docter_email extends Fragment {
    WebView web;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.docter_email,container,false);

        web= view.findViewById(R.id.web);
        web.setWebChromeClient(new WebChromeClient());
        web.loadUrl("https://mail.google.com/mail/u/0/#inbox");
        return  view;
    }
}

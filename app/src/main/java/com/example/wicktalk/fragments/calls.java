package com.example.wicktalk.fragments;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.wicktalk.R;


public class calls extends Fragment {
Button all,missed;

    public calls() {
        // Required empty public constructor
    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for
        // this fragment
        View view= inflater.inflate(R.layout.fragment_calls, container, false);
        all =view.findViewById(R.id.allcalls);
        missed=view.findViewById(R.id.missedcalls);
        all = (Button) view.findViewById(R.id.allcalls);
        all.setBackgroundColor(Color.BLUE);
        all.setTextColor(Color.WHITE);
all.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        all.setBackgroundColor(Color.BLUE);
        all.setTextColor(Color.WHITE);
        missed.setBackgroundColor(Color.WHITE);
        missed.setTextColor(Color.BLACK);
        AllCalls fragment2 = new AllCalls();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framforcalls, fragment2);
        fragmentTransaction.commit();
    }
});

        missed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                all.setBackgroundColor(Color.WHITE);
                missed.setBackgroundColor(Color.BLUE);
                missed.setTextColor(Color.WHITE);
                all.setTextColor(Color.BLACK);
                MissedCalls fragment3 = new MissedCalls();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.framforcalls, fragment3);
                fragmentTransaction.commit();
            }
        });
clickfuntion();
return view;

    }

    private void clickfuntion() {
                AllCalls fragment2 = new AllCalls();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.framforcalls, fragment2);
                fragmentTransaction.commit();
            }


}
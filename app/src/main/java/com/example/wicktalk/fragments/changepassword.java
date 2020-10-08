package com.example.wicktalk.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.wicktalk.R;
import com.example.wicktalk.fragments.AllCalls;
import com.example.wicktalk.fragments.setting;

public class changepassword extends Fragment {
    ImageView closs;

    public changepassword() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_changepassword, container, false);
        closs = view.findViewById(R.id.closs);
        closs.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                setting fragment = new setting();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.changepass, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        return view;

    }
}
package com.example.wicktalk.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wicktalk.R;
import com.example.wicktalk.Adapters.viewPagerAdapter;
import com.google.android.material.tabs.TabLayout;


public class chats extends Fragment {
    private ViewPager viewPager1;
    private TabLayout tabLayout1;


    public chats() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chats, container, false);
        viewPager1=view.findViewById(R.id.myViewpager);
        tabLayout1=view.findViewById(R.id.tabLayout);
        setUpViewPager(viewPager1);
        tabLayout1.setupWithViewPager(viewPager1);
        return view;
    }
    private void setUpViewPager(ViewPager viewPager){
        viewPagerAdapter viewpageradapter = new viewPagerAdapter(getChildFragmentManager());
        viewpageradapter.addFragment(new all(),"All");
        viewpageradapter.addFragment(new group(),"Groups");
        viewPager.setAdapter(viewpageradapter);
    }

    }

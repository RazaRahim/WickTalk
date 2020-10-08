package com.example.wicktalk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.wicktalk.fragments.calls;
import com.example.wicktalk.fragments.chats;
import com.example.wicktalk.fragments.contacts;
import com.example.wicktalk.fragments.setting;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);


bottomNavigationView.setOnNavigationItemSelectedListener(navlistner);

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new chats())
                    .commit();

        }
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navlistner = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment slectedFragment = null;
            switch (item.getItemId()){
                case R.id.chat:
                    slectedFragment =new chats();
                    break;
                case R.id.contacts:
                    slectedFragment =new contacts();
                    break;
                case R.id.calls:
                    slectedFragment =new calls();
                    break;
                case R.id.setting:
                    slectedFragment =new setting();
                    break;

            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,slectedFragment).commit();
            return true;
        }
    };
}
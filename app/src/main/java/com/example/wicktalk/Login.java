package com.example.wicktalk;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

public class Login extends AppCompatActivity {

    EditText username,password;
    Button login,signup;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

username =findViewById(R.id.userName);
password =findViewById(R.id.userPass);
login =findViewById(R.id.loginbtn);
signup=findViewById(R.id.sign_up);
progressBar =findViewById(R.id.progressBar11);

progressBar.setVisibility(View.INVISIBLE);

login.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

            Intent intent = new Intent(Login.this,MainActivity.class);
            startActivity(intent);
        }

});
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Login.this,signup.class);
                startActivity(intent);
            }

        });




    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Closing Activity")
                .setMessage("Are you sure you want to close this activity?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finishAffinity();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }

}

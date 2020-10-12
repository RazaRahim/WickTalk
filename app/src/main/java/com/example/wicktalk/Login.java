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

import com.quickblox.auth.session.QBSettings;
import com.quickblox.core.QBEntityCallback;
import com.quickblox.core.exception.QBResponseException;
import com.quickblox.core.model.QBEntity;
import com.quickblox.users.QBUsers;
import com.quickblox.users.model.QBUser;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

public class Login extends AppCompatActivity {


    EditText username,password;
    Button login,register;
    ProgressBar progressBar;

    static final String APP_ID = "86500";
    static final String AUTH_KEY = "PThnUVzhxFvDJLn";
    static final String AUTH_SECRET = "ySZGbsemJjQ-nJT";
    static final String ACCOUNT_KEY = "Xj_6xeWRfavuJz1pAw5J";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initializeFramework();

username =findViewById(R.id.userName);
password =findViewById(R.id.userPass);
login =findViewById(R.id.loginbtn);
register=findViewById(R.id.sign_up);
progressBar =findViewById(R.id.progressBar11);
progressBar.setVisibility(View.INVISIBLE);

register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,signup.class);
                startActivity(intent);
            }

        });

login.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        progressBar.setVisibility(View.VISIBLE);
        String user = username.getText().toString();
        String pass = password.getText().toString();

        QBUser qbUser = new QBUser(user,pass);
        QBUsers.signIn(qbUser).performAsync(new QBEntityCallback<QBUser>() {
            @Override
            public void onSuccess(QBUser qbUser, Bundle bundle) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(Login.this, "Successfully Login", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Login.this,MainActivity.class);
               intent.putExtra("user",user);
               intent.putExtra("password",pass);
                startActivity(intent);
                finish();
            }

            @Override
            public void onError(QBResponseException e) {
                Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);

            }
        });

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


    private void initializeFramework() {
        QBSettings.getInstance().init(getApplicationContext(),APP_ID,AUTH_KEY,AUTH_SECRET);
        QBSettings.getInstance().setAccountKey(ACCOUNT_KEY);
    }

}

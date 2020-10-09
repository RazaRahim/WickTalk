package com.example.wicktalk;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.api.Response;
import com.quickblox.auth.QBAuth;
import com.quickblox.auth.session.QBSession;
import com.quickblox.core.QBEntityCallback;
import com.quickblox.core.exception.QBResponseException;
import com.quickblox.users.QBUsers;
import com.quickblox.users.model.QBUser;

public class signup extends AppCompatActivity {
    Button Login,register;
EditText username, Name,email,country,password, Confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        registersession();

        Login=findViewById(R.id.login);
        register=findViewById(R.id.login_btn);
        username=findViewById(R.id.username);
        Name =findViewById(R.id.fullname);
        email=findViewById(R.id.email);
        country=findViewById(R.id.country);
        password=findViewById(R.id.pass);
        Confirm =findViewById(R.id.confirmpass);

register.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String user = username.getText().toString();
        String full = Name.getText().toString();
        String Email = email.getText().toString();
        String Country = country.getText().toString();
        String pass = password.getText().toString();

        if(TextUtils.isEmpty(pass) || pass.length() < 8)
        {
            password.setError("You must have 8 characters in your password");
            return;
        }
        String confirm = Confirm.getText().toString();

        QBUser qbUser = new QBUser(user,pass);
        QBUsers.signUp(qbUser).performAsync(new QBEntityCallback<QBUser>() {
            @Override
            public void onSuccess(QBUser qbUser, Bundle bundle) {
                Toast.makeText(signup.this, "Sign Up successfully", Toast.LENGTH_SHORT).show();

//                finish();
            }

            @Override
            public void onError(QBResponseException e) {
                Toast.makeText(signup.this, ""  +e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
});


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signup.this,Login.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void registersession() {
        QBAuth.createSession().performAsync(new QBEntityCallback<QBSession>() {
            @Override
            public void onSuccess(QBSession qbSession, Bundle bundle) {

            }

            @Override
            public void onError(QBResponseException e) {
                Log.e("Error",e.getMessage());
            }
        });
    }


}
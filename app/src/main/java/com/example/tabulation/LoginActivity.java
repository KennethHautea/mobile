package com.example.tabulation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText adminUsername,adminpassword;
    Button btnSingin;
    String username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        adminUsername = findViewById(R.id.etUserAdmin);
        adminpassword = findViewById(R.id.etAdminPassword);
        btnSingin = findViewById(R.id.btnSignin);

        btnSingin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btnSignin:
                        int success = 2;
                    username = adminUsername.getText().toString();
                    password = adminpassword.getText().toString();
                    if (username.equals("")&&password.equals("")){
                        adminUsername.setError("This field is required");
                        adminpassword.setError("This field is required");
                        success--;

                    }
                    if (success==2){
                        if (username.equals("admin") && password.equals("admin")){
                            Toast.makeText(LoginActivity.this,"Admin Login in",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this,MainActivity.class));
                        }else{
                            adminUsername.setError("Invalid Credentials");
                        }
                    }
                    break;
                }
            }
        });

    }
}

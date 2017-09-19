package com.example.santosh.lab43;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Register extends AppCompatActivity {

    EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = (EditText) findViewById(R.id.Username);
        password = (EditText) findViewById(R.id.editText9);
    }

    public void register(View v)
    {

        Intent loginPage = new Intent(Register.this, MainActivity.class);
        loginPage.putExtra("username",username.getText().toString());
        loginPage.putExtra("password",password.getText().toString());
        startActivity(loginPage);
    }
}

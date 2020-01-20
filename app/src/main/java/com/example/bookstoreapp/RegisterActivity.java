package com.example.bookstoreapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText mEmail;
    EditText mName;
    EditText mPassword;
    Button mRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mRegister = (Button) findViewById(R.id.register_btn_reg);
        mName = (EditText) findViewById(R.id.name_edit_txt_reg);
        mEmail = (EditText) findViewById(R.id.email_edit_txt_reg);
        mPassword = (EditText) findViewById(R.id.password_edit_txt_reg);

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email,name,password;
                email = mEmail.getText().toString();
                name = mName.getText().toString();
                password = mPassword.getText().toString();
                Toast.makeText(RegisterActivity.this, "Register successfull!!", Toast.LENGTH_SHORT).show();
                goToMain();
            }
        });
    }

    private void goToMain() {
        Intent main_intent = new Intent(RegisterActivity.this,MainActivity.class);
        startActivity(main_intent);
    }
}

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
    EditText mAddress;
    EditText mPincode;
    EditText mPhone;
    Button mRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mRegister = (Button) findViewById(R.id.register_btn_reg);
        mName = (EditText) findViewById(R.id.name_edit_txt_reg);
        mEmail = (EditText) findViewById(R.id.email_edit_txt_reg);
        mPassword = (EditText) findViewById(R.id.password_edit_txt_reg);
        mAddress = (EditText) findViewById(R.id.address_edit_txt_reg);
        mPincode = (EditText) findViewById(R.id.pincode_edit_txt_reg);
        mPhone = (EditText) findViewById(R.id.phone_edit_txt_reg);

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email,name,password,address,phone,pincode;
                email = mEmail.getText().toString();
                name = mName.getText().toString();
                password = mPassword.getText().toString();
                address = mAddress.getText().toString();
                phone = mPhone.getText().toString();
                pincode = mPincode.getText().toString();

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

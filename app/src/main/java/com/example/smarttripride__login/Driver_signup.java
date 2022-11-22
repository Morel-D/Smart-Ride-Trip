package com.example.smarttripride__login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Driver_signup extends AppCompatActivity {

    FirebaseAuth auth;
    DatabaseReference reference;
    EditText user_name, email, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_signup);

        Button button = (Button) findViewById(R.id.D_signUp_btn);
        auth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference().child("SmartRide/Driver");



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastMsg();
                InsertData();
            }
        });

    }
    public void SetSuccess(){
        Intent intent = new Intent(this, Driver_Dashboard_Map.class);
        startActivity(intent);
    }

    public void ToastMsg(){
        user_name = (EditText) findViewById(R.id.user_name);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        EditText password = (EditText) findViewById(R.id.password);
        final Loading loading = new Loading(Driver_signup.this);


        String str_name = user_name.getText().toString();
        String str_email = email.getText().toString();
        String str_password = password.getText().toString();
        String str_phone = phone.getText().toString();

        Context context = getApplicationContext();
        CharSequence charSequence = "Please fill in the information below";
        int duration = Toast.LENGTH_SHORT;


        if (str_name.isEmpty() || str_email.isEmpty() || str_password.isEmpty() || str_phone.isEmpty()) {
            Toast.makeText(context, charSequence, duration).show();
        } else {
            loading.startLoadingDialog();
            SetSuccess();
        }

    }



    public void  registerDriver(String str_email, String str_password){
        auth.createUserWithEmailAndPassword(str_email, str_password).addOnCompleteListener(Driver_signup.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){

                    Toast.makeText(Driver_signup.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                    SetSuccess();
                    finish();
                }else {
                    Toast.makeText(Driver_signup.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Driver_signup.this, Driver_signup.class));
                    finish();
                }
            }
        });
    }

    public void InsertData(){
        String str_name = user_name.getText().toString();
        String str_email = email.getText().toString();
        String str_phone = phone.getText().toString();

        ModuleClass moduleClass = new ModuleClass(str_name, str_email, str_phone);
        reference.push().setValue(moduleClass);
    }





}
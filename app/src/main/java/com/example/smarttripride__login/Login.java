package com.example.smarttripride__login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();

        EditText  txt_email = (EditText) findViewById(R.id.log_email);
        EditText txt_password = (EditText) findViewById(R.id.log_password);
        Button button = (Button) findViewById(R.id.login_btn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txt_email.getText().toString();
                String password = txt_password.getText().toString();
                Loading loading = new Loading(Login.this);

                if (email.isEmpty() || password.isEmpty()){
                    Toast.makeText(Login.this, "Please fill in the information below", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    loading.startLoadingDialog();
                    Onlogin(email, password);
                }
            }
        });

    }

    private void Onlogin(String email, String password) {
        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Login.this, Client_Dasboard_Map.class));
                finish();
            }
        });
    }
}
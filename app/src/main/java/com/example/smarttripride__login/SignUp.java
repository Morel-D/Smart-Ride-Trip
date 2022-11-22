package com.example.smarttripride__login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;

public class SignUp extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Button button = (Button) findViewById(R.id.btn_driver);
        Button button1 = (Button) findViewById(R.id.client_btn);
        Button button2 = (Button) findViewById(R.id.btn_login);
        ImageView imageView = findViewById(R.id.log_image);

         AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
         alphaAnimation.setDuration(1000);
         alphaAnimation.setRepeatMode(Animation.REVERSE);
         imageView.startAnimation(alphaAnimation);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                driverClick();
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clientClick();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });


    }

    private void login() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    public void driverClick(){
        Intent intent = new Intent(this, Driver_signup.class);
        startActivity(intent);
    }

    public void clientClick(){
        Intent intent2 = new Intent(this, Client_SignUp.class);
        startActivity(intent2);
    }


}
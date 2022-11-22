package com.example.smarttripride__login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.ImageView;

public class Success_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_page);

        ImageView imageView = findViewById(R.id.success_check);
        Button button = (Button) findViewById(R.id.btn_success);

       // AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
       // alphaAnimation.setDuration(1000);
       // alphaAnimation.setRepeatCount(1);
       // alphaAnimation.setRepeatMode(Animation.REVERSE);
       // imageView.startAnimation(alphaAnimation);
//
        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.bonce);
        imageView.startAnimation(animation);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Loading loading = new Loading(Success_page.this);
                loading.startLoadingDialog();
                NewPage();
                finish();
            }
        });

    }

    public void NewPage(){
      Intent intent = new Intent(this, Client_Dasboard_Map.class);
        startActivity(intent);
    }

}
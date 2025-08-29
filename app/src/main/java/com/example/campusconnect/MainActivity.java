package com.example.campusconnect;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ✅ Always show welcome page first (no auto-skip)
        setContentView(R.layout.activity_main);

        // 🎬 Load Animations
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        Animation slideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up);
        Animation bounce = AnimationUtils.loadAnimation(this, R.anim.bounce);

        // Apply animations
        findViewById(R.id.logoSection).startAnimation(fadeIn);
        findViewById(R.id.buttonSection).startAnimation(slideUp);

        // Buttons
        Button loginBtn = findViewById(R.id.loginBtn);
        Button registerBtn = findViewById(R.id.registerBtn);

        // Bounce buttons individually
        loginBtn.startAnimation(bounce);
        registerBtn.startAnimation(bounce);

        // ✅ Login button → LoginActivity with slide effect
        loginBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in_slide_bottom, R.anim.fade_out_scale);
        });

        // ✅ Register button → RegisterActivity with slide effect
        registerBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in_slide_bottom, R.anim.fade_out_scale);
        });
    }
}

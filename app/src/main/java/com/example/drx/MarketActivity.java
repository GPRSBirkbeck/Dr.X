package com.example.drx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MarketActivity extends AppCompatActivity {
    TextView mWelcomeText;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        String userEmail = currentUser.getEmail();

        mWelcomeText = findViewById(R.id.welcome_text_home_page);
        mWelcomeText.setText("You successfully logged in to the app " + userEmail);
    }

    public void addAnItemToMarket(View view){
        Intent intent = new Intent(this, AddItemActivity.class);
        startActivity(intent);
    }
}
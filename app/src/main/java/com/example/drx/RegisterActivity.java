package com.example.drx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "RegisterActivity";
    //From built in auth tutorial: build an authenticcaiton object
    private FirebaseAuth mAuth;

    //UI references
    private EditText mPassword, mEmail;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //get an instance of that firebase auth object
        mAuth = FirebaseAuth.getInstance();
        mEmail = (EditText) findViewById(R.id.email_input_register);

        mPassword = (EditText) findViewById(R.id.password_input_register);
        registerButton = (Button) findViewById(R.id.register_button_register_page);
        //register method for register button
        registerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String password = mPassword.getText().toString();
                String emailText = mEmail.getText().toString();
                if(!emailText.equals("") && !mPassword.getText().toString().equals("")){
                    mAuth.createUserWithEmailAndPassword(emailText,password);
                    goToHomePage();
                }
                else if (emailText.equals("") ||emailText.equals(null)){
                    toastMessage("you didnt enter your email");
                }
                else if(mPassword.getText().toString().equals("") || mPassword.getText().toString().equals(null)){
                    toastMessage("you didnt enter your password");
                }
                else{
                    toastMessage("you didnt enter an email or a password");
                }
            }
        });
    }

    //When initializing your Activity, check to see if the user is currently signed in.
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getInstance().getCurrentUser();
        if(currentUser != null){
            updateUI(currentUser);
        }
    }


    public void updateUI(FirebaseUser currentUser){
        //displayUserNameWelcome.setVisibility(View.VISIBLE);
        //displayUserNameWelcome.setText("Welcome back " + currentUser.getDisplayName());
    }

    public void toastMessage(String toastString){
        Toast.makeText(this, toastString, Toast.LENGTH_SHORT).show();
    }

    public void openLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    public void goToHomePage() {
        Intent intent = new Intent(this, MarketActivity.class);
        //FirebaseUser currentUser = mAuth.getInstance().getCurrentUser();
        startActivity(intent);
    }
}
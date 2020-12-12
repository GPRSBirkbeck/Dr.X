package com.example.drx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText mEmail, mPassword;
    private Button signInButton, registerButton;
    private static final String TAG = "signinactivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        mEmail = (EditText) findViewById(R.id.email_input);
        mPassword = (EditText) findViewById(R.id.password_input);
        signInButton = (Button) findViewById(R.id.sign_in);
        registerButton = (Button) findViewById(R.id.register_button_login_page);


        //sign in method for register button
        signInButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();
                if(!email.equals("") && !password.equals("")) {
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d(TAG, "signInWithEmail:success");
                                        Log.d(TAG, "onComplete: ");
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        //updateUI(user);
                                        goToHomePage(user);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                                        Toast.makeText(LoginActivity.this, "Authentication failed. Please provide a valid password and email",
                                                Toast.LENGTH_SHORT).show();
                                        updateUI(null);
                                        // [START_EXCLUDE]
                                        // [END_EXCLUDE]
                                    }

                                    // [START_EXCLUDE]
                                    if (!task.isSuccessful()) {
                                        //mBinding.status.setText(R.string.auth_failed);
                                    }
                                    //hideProgressBar();
                                    // [END_EXCLUDE]
                                }
                            });
                }

                else if (email.equals("")){
                    toastMessage("you didn't enter your email");
                }
                else if(password.equals("")){
                    toastMessage("you didn't enter your password");
                }
                else{
                    toastMessage("you didn't enter an email or a password");
                }
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    public void updateUI(FirebaseUser currentUser){
        //displayUserNameWelcome.setVisibility(View.VISIBLE);
        //displayUserNameWelcome.setText("Welcome back " + currentUser);
    }

    public void toastMessage(String toastString){
        Toast.makeText(this, toastString, Toast.LENGTH_SHORT).show();

    }
    public void goToHomePage(FirebaseUser user) {
        Intent intent = new Intent(this, MarketActivity.class);

        String email = user.getEmail();
        intent.putExtra(Intent.EXTRA_EMAIL, email);
        startActivity(intent);
    }

    public void goToRegister(View view){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

}
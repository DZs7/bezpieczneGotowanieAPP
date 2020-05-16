package com.example.bezpiecznegotowanie.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bezpiecznegotowanie.R;
import com.example.bezpiecznegotowanie.search.FindAction;
import com.google.firebase.auth.FirebaseAuth;

public class LoginController extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;
    private EditText mEmail;
    private EditText mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEmail = findViewById(R.id.email_input);
        mPassword = findViewById(R.id.password_input);
        mEmail.setText("");
        mPassword.setText("");
        mFirebaseAuth = FirebaseAuth.getInstance();
    }

    public void startAppWithoutSigning(View v){
        Intent intent = new Intent(this, FindAction.class);
        startActivity(intent);
    }

    public void register(View v){

        String email = mEmail.getText().toString();
        String password = mPassword.getText().toString();

        if(email.isEmpty() || password.isEmpty()) {
            displayToast(getString(R.string.missing_email_password_message));
            return;
        }
        mFirebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, authTaskResult -> {
                    if(authTaskResult.isSuccessful()) {
                        displayToast("Your account has been successfully registered\nPlease sign in to proceed");
                    }
                    else{
                        displayToast("Registration Failed: " + authTaskResult.getException().getMessage());
                    }

                });

    }

    public void signIn(View v){

        String email = mEmail.getText().toString();
        String password = mPassword.getText().toString();

        if(email.isEmpty() || password.isEmpty()) {
            displayToast(getString(R.string.missing_email_password_message));
            return;
        }

        mFirebaseAuth.signInWithEmailAndPassword(email, password).
                addOnCompleteListener(this, authTaskResult -> {
                    if(authTaskResult.isSuccessful()){
                        Intent intent = new Intent(LoginController.this, FindAction.class);
                        startActivity(intent);
                    }
                    else{
                        displayToast(authTaskResult.getException().getMessage());
                    }

                });


    }

    public void displayToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}

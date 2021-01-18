package com.rajasalmantariq.fasttrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    FirebaseAuth mAuth;

    TextView email, pass;
    Button btn;
    TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        setFirebase();

        setViews();

        setOnClickListeners();
    }

    void setFirebase(){
        mAuth=FirebaseAuth.getInstance();
    }

    void setViews(){
        email=findViewById(R.id.loginEmail);
        pass=findViewById(R.id.loginPass);
        btn=findViewById(R.id.loginBtn);
        register=findViewById(R.id.loginReg);
    }

    void setOnClickListeners(){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Todo: add checks on uname and pwd.

                loginUser();

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });
    }

    void loginUser(){
        mAuth.signInWithEmailAndPassword(email.getText().toString(), pass.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Log.d("login", "onComplete: signInWithEmailAndPassword:success");
                            startActivity(new Intent(Login.this, LoggedInActivity.class));
                        }
                        else
                            Toast.makeText(Login.this, "Registration failed...", Toast.LENGTH_SHORT).show();

                    }
                });
    }
}
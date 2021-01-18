package com.rajasalmantariq.fasttrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class LoggedInActivity extends AppCompatActivity {

    FirebaseAuth mAuth;

    Button logoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        setViews();

        setListeners();

        setFirebase();
    }

    private void setListeners() {
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity(new Intent(LoggedInActivity.this, Login.class));
            }
        });
    }

    private void setFirebase() {
        mAuth=FirebaseAuth.getInstance();
    }

    private void setViews() {
        logoutBtn=findViewById(R.id.logoutBtn);
    }
}
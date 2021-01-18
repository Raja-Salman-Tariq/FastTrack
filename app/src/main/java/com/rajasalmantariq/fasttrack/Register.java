package com.rajasalmantariq.fasttrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class Register extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseDatabase db;
    DatabaseReference dbRef;

    TextView email, pass;
    CheckBox musafir, sahulatk;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        setFirebase();
        setViews();
        setOnClickListeners();
    }

    void setViews(){
        email=findViewById(R.id.regEmail);
        pass=findViewById(R.id.regPass);
        btn=findViewById(R.id.regBtn);

        musafir=findViewById(R.id.musafir);
        sahulatk=findViewById(R.id.sahulatkar);
    }

    void setFirebase(){
        mAuth=null;
        mAuth=FirebaseAuth.getInstance();
        db=FirebaseDatabase.getInstance();
        dbRef=db.getReference("users");
    }

    void setOnClickListeners(){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Todo: add checks on uname and pwd.

                createUser();
            }
        });
    }

    void createUser(){
        userCreation();
//        updateDb();
    }

    void userCreation(){
        mAuth.createUserWithEmailAndPassword(email.getText().toString(), pass.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("reg", "onComplete: createUserWithEmailAndPassword:success");
                            updateDb();
                            startActivity(new Intent(Register.this, LoggedInActivity.class));
                        }
                        else {
                            Toast.makeText(Register.this, "Registration failed...", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Register.this, "Registration failed: "+e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    void updateDb(){
        String uid= null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            uid = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
        }
        else{
            uid = mAuth.getCurrentUser().getUid();
        }
        if (musafir.isChecked()){
            dbRef.child("musafir").child(uid).setValue(true);
        }

        if (sahulatk.isChecked()){
            dbRef.child("sahulatkar").child(uid).setValue(true);
        }
    }
}
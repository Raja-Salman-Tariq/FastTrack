package com.rajasalmantariq.fasttrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setFirebase();

        ifSignedInOrNot();
    }

    void setFirebase(){
        mAuth=null;
        mAuth=FirebaseAuth.getInstance();

        mUser=null;
        mUser=mAuth.getCurrentUser();
    }

    void ifSignedInOrNot(){
        if (mUser==null){
            startActivity(new Intent(MainActivity.this, Login.class));
        }
        else{
            Log.d("main", "onCreate: Logged in as user w email:"+
                    mUser.getEmail()+" and id "+mUser.getUid());

            startActivity(new Intent(MainActivity.this, LoggedInActivity.class));
        }
    }
}
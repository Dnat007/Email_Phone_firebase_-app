package com.example.emailphonefirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Sixth extends AppCompatActivity {
    Button b1;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sixth);

        b1 = (Button)findViewById(R.id.Logoutall);
        firebaseAuth  = FirebaseAuth.getInstance();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                Intent out =new Intent(Sixth.this,MainActivity.class);
                startActivity(out);
                finish();
            }
        });

    }
}
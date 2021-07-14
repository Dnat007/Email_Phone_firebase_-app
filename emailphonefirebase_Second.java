package com.example.emailphonefirebase;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class Second extends AppCompatActivity
{
    EditText e1,e2;
    Button b1,b2;
    ProgressBar p1;
    FirebaseAuth firebaseAuth;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        e1 = (EditText)findViewById(R.id.editTextTextPersonName);
        e2 = (EditText)findViewById(R.id.editTextTextPersonName2);
        b1 = (Button)findViewById(R.id.button);
        b2 = (Button)findViewById(R.id.Button2);
        p1 = (ProgressBar)findViewById(R.id.progressBar);
        e2.setInputType(InputType.TYPE_CLASS_TEXT |InputType.TYPE_TEXT_VARIATION_PASSWORD);

        firebaseAuth = FirebaseAuth.getInstance();

        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String s1 = e1.getText().toString();
                String s2 = e2.getText().toString();
                if(s1.isEmpty())
                {
                    e1.setError("fill the username ");
                    return;
                }
                else
                {
                    if(s2.isEmpty())
                    {
                        e2.setError("fill password");
                        return;
                    }
                }
                p1.setVisibility(View.VISIBLE);

                firebaseAuth.signInWithEmailAndPassword(s1,s2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {

                        if(task.isSuccessful())
                        {
                            Toast.makeText(Second.this , "login done",Toast.LENGTH_SHORT).show();
                            p1.setVisibility(View.INVISIBLE);
                            Intent j2 = new Intent(Second.this,Sixth.class);
                            startActivity(j2);
                            finish();
                        }
                        else
                        {
                            Toast.makeText(Second.this , "Mismatch",Toast.LENGTH_SHORT).show();
                            Intent j2 = new Intent(Second.this,Third.class);
                            startActivity(j2);
                            finish();

                        }
                    }
                });
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j1 = new Intent(Second.this,Sixth.class);
                startActivity(j1);
                finish();

            }
        });


    }
}
package com.example.emailphonefirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public class Fifth extends AppCompatActivity
{
    EditText e1;
    Button b1;
    FirebaseAuth firebaseAuth;
    String phone;
    String otp;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);

        e1 = (EditText)findViewById(R.id.editTextTextPersonName6);
        b1 = (Button)findViewById(R.id.button5);
        firebaseAuth = FirebaseAuth.getInstance();
        phone = getIntent().getStringExtra("mobile".toString());

        genotp();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(e1.getText().toString().isEmpty())
                {
                    Toast.makeText(Fifth.this,"PLz fill",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if (e1.getText().toString().length() != 6)
                    {
                        Toast.makeText(Fifth.this, "give valid otp", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(otp, e1.getText().toString());
                        signInwithPhoneAuthCredential(credential);
                    }
                }
            }
        });
    }
    private void genotp()
    {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phone,
                60,
                TimeUnit.SECONDS,
                this,
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onCodeSent(@NonNull @NotNull String s, @NonNull @NotNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(s, forceResendingToken);
                        otp = s;
                    }

                    @Override
                    public void onVerificationCompleted(@NonNull @NotNull PhoneAuthCredential phoneAuthCredential) {
                        signInwithPhoneAuthCredential(phoneAuthCredential);
                    }

                    @Override
                    public void onVerificationFailed(@NonNull @NotNull FirebaseException e) {
                        Toast.makeText(Fifth.this,"mismatch",Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }
    private void signInwithPhoneAuthCredential(PhoneAuthCredential credential)
    {
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {

                if(task.isSuccessful())
                {
                    Toast.makeText(Fifth.this,"database updated",Toast.LENGTH_SHORT).show();
                    Intent k = new Intent(Fifth.this,Sixth.class);
                    startActivity(k);
                    finish();
                }
                else
                {
                    Toast.makeText(Fifth.this,"database failed",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
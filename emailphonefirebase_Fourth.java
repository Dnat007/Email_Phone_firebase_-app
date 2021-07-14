package com.example.emailphonefirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hbb20.CountryCodePicker;

public class Fourth extends AppCompatActivity {
    CountryCodePicker ccp;
    Button b1;
    EditText e1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        b1 = (Button)findViewById(R.id.devil);
        e1 = (EditText)findViewById(R.id.editTextTextPersonName5);
        ccp = (CountryCodePicker)findViewById(R.id.ccp);
        ccp.registerCarrierNumberEditText(e1);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iw = new Intent(Fourth.this,Fifth.class);
                iw.putExtra("mobile",ccp.getFullNumberWithPlus().trim());
                startActivity(iw);
            }
        });
    }
}
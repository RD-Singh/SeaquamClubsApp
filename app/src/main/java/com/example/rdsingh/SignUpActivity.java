package com.example.rdsingh;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.rdsingh.seaquamclubsapp.R;


public class SignUpActivity extends AppCompatActivity {



    TextView t;
    public Button signUpBtn;
    public void init3(){


        signUpBtn = (Button)findViewById(R.id.submitRegBtn);

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(SignUpActivity.this, MainActivity.class));
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        t = (TextView) findViewById(R.id.studentIdTF);
        Typeface myCustomFont = Typeface.createFromAsset(getAssets(), "fonts/atr.ttf");
        t.setTypeface(myCustomFont);

        t = (TextView) findViewById(R.id.emailTF);
        Typeface myCustomFont2 = Typeface.createFromAsset(getAssets(), "fonts/atr.ttf");
        t.setTypeface(myCustomFont2);

        t = (TextView) findViewById(R.id.fNameTF);
        Typeface myCustomFont3 = Typeface.createFromAsset(getAssets(), "fonts/atr.ttf");
        t.setTypeface(myCustomFont3);

        t = (TextView) findViewById(R.id.lNameTF);
        Typeface myCustomFont4 = Typeface.createFromAsset(getAssets(), "fonts/atr.ttf");
        t.setTypeface(myCustomFont4);

        t = (TextView) findViewById(R.id.pswdTF);
        Typeface myCustomFont5 = Typeface.createFromAsset(getAssets(), "fonts/atr.ttf");
        t.setTypeface(myCustomFont5);

        t = (TextView) findViewById(R.id.rePswdTF);
        Typeface myCustomFont6 = Typeface.createFromAsset(getAssets(), "fonts/atr.ttf");
        t.setTypeface(myCustomFont6);

        t = (TextView) findViewById(R.id.gradeTF);
        Typeface myCustomFont7 = Typeface.createFromAsset(getAssets(), "fonts/atr.ttf");
        t.setTypeface(myCustomFont7);

        t = (TextView) findViewById(R.id.submitRegBtn);
        Typeface myCustomFont8 = Typeface.createFromAsset(getAssets(), "fonts/atr.ttf");
        t.setTypeface(myCustomFont8);


        init3();

    }
}



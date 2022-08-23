package com.flaviumircia.android_application_for_help_during_the_pandemic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appscss.R;

public class MainActivity extends AppCompatActivity {
    private Button buttonLogin;
    private Button buttonSignup;
    private EditText email;
    private EditText password;
    private TextView eroare;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonSignup = (Button) findViewById(R.id.buttonSignup);
        email = (EditText) findViewById(R.id.emailAdress);
        password = (EditText) findViewById(R.id.password);
        eroare = (TextView) findViewById(R.id.eroare);


        Context coext=getApplicationContext();
        CharSequence credentials="Email sau parola gresita";
        int duration=Toast.LENGTH_LONG;
        View.OnClickListener listener2 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getText().toString().equals("") && password.getText().toString().equals(""))
                    openActivity2();
                else {
                    Toast.makeText(getBaseContext(), "Email sau parola gresita", Toast.LENGTH_LONG).show();
                }


            }
        };
        View.OnClickListener listener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignUpActivity();
            }
        };
        buttonSignup.setOnClickListener(listener);
        buttonLogin.setOnClickListener(listener2);

    }
    public void openActivity2(){
        Intent intent=new Intent(this,meniu_map.class);
        startActivity(intent);
    }
    public void openSignUpActivity(){
        Intent intent2=new Intent(this, sign_up.class);
        startActivity(intent2);
    }

}
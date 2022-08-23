package com.flaviumircia.android_application_for_help_during_the_pandemic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.appscss.R;
import com.flaviumircia.android_application_for_help_during_the_pandemic.data_model.UserModel;
import com.flaviumircia.android_application_for_help_during_the_pandemic.misc.EditTextToModel;

public class MainActivity extends AppCompatActivity {
    private Button buttonLogin;
    private Button buttonSignup;
    private EditText email;
    private EditText password;
    private UserModel userModel;
    private EditTextToModel editTextToModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonSignup = (Button) findViewById(R.id.buttonSignup);
        email = (EditText) findViewById(R.id.emailAdress);
        password = (EditText) findViewById(R.id.password);

        //transforming edittext to string
        editTextToModel=new EditTextToModel(email,password);
        userModel=editTextToModel.getUserModel();


        buttonLogin.setOnClickListener(view->{
                if(userModel.getEmail().equals("flaviumircia15@gmail.com") && userModel.getPassword().equals("testlogin"))
                    startActivity(new Intent(this, MeniuMap.class));
                else if(userModel.getEmail().equalsIgnoreCase(""))
                    email.setError("Email field can NOT be empty!");
                else if(userModel.getPassword().equalsIgnoreCase(""))
                    password.setError("Password field can NOT be empty!");
        });

        buttonSignup.setOnClickListener(view->{
            startActivity(new Intent(this,SignUp.class));
        });
    }

}
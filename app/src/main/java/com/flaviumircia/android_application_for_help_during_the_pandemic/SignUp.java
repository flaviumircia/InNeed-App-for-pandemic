package com.flaviumircia.android_application_for_help_during_the_pandemic;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.appscss.R;

import java.io.BufferedReader;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class SignUp extends AppCompatActivity{

    private static final String TAG ="";
    private EditText passwordReg;
    private EditText passwordRegConfirm;
    private Button regButton;
    private TextView eroareSignUp;
    private String userid;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_screen);
        EditText emailAdressReg = (EditText) findViewById(R.id.emailAddressReg);
        passwordReg = (EditText) findViewById(R.id.passwordReg);
        passwordRegConfirm = (EditText) findViewById(R.id.passwordRegConfirm);
        regButton = (Button) findViewById(R.id.regButton);
    }
}
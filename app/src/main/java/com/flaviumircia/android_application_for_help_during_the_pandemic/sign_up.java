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

public class sign_up extends AppCompatActivity{

    private static final String TAG ="";
    private EditText passwordReg;
   private EditText passwordRegConfirm;
      private Button regButton;
    private TextView eroareSignUp;
    String FILENAME = "hello_file.txt";
    private String userid;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_screen);
       EditText emailAdressReg=(EditText) findViewById(R.id.emailAddressReg);
        passwordReg=(EditText) findViewById(R.id.passwordReg);
        passwordRegConfirm=(EditText) findViewById(R.id.passwordRegConfirm);
        regButton=(Button) findViewById(R.id.regButton);
        View.OnClickListener confirm=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(passwordReg.getText().toString().equals("123456")) {
                    String string = "hello world!";
                    FileOutputStream fos = null;
                    try {
                        fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    try {
                        fos.write(string.getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Log.d(TAG, "Email ul este:"+emailAdressReg.getText().toString() );
                        Log.d(TAG, "Parola este:"+passwordReg.getText().toString());

                }else {
                    Log.d(TAG, "Nu a intrat in IF");
                }
                try {
                    citireFisier(FILENAME);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
            regButton.setOnClickListener(confirm);
    }
public void citireFisier(String FILENAME) throws IOException {
        File file = new File(FILENAME);
        StringBuilder text=new StringBuilder();
        try{
    BufferedReader br= new BufferedReader(new FileReader(file));
    String line;
    while((line=br.readLine())!=null){
        Log.d(TAG, line+"\n");
    }br.close();}   catch (IOException e){
            Log.e(TAG, "citireFisier: Eroare" );
        }

}
}
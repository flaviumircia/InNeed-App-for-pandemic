package com.flaviumircia.android_application_for_help_during_the_pandemic.misc;

import android.widget.EditText;

import com.flaviumircia.android_application_for_help_during_the_pandemic.data_model.UserModel;

import java.util.Locale;

public class EditTextToModel {
    private UserModel userModel;
    private String email,password;

    public EditTextToModel(EditText email, EditText password) {
        this.convertToString(email,password);
        this.userModel=new UserModel(this.email,this.password);
    }

    public void convertToString(EditText email,EditText password){

         this.email=email.getText().toString().trim().toLowerCase(Locale.ROOT);
         this.password=password.getText().toString();
    }

    public UserModel getUserModel() {
        return userModel;
    }
}

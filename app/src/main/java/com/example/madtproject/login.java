package com.example.madtproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class login extends AppCompatActivity {
    TextInputEditText uname,password;
    Button login;
    DBManager dbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        uname = findViewById(R.id.et_uname_login);
        password = findViewById(R.id.et_pwd_login);
        login = findViewById(R.id.login);
        dbManager = new DBManager(getApplicationContext());
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbManager.open();
                String pwd_db = "";
                String pwd = "";
                String username = uname.getText().toString();
                try{
                    String query = "Select * From LOGIN where UNAME = '"+username+"'";
                    pwd = password.getText().toString();
                    pwd_db = dbManager.getPassword(query);
                }
                catch (Exception e){
                    Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
                }
                if(pwd.equals(pwd_db)){
                    Toast.makeText(getApplicationContext(),"You are good to go",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Enter correct credentials",Toast.LENGTH_SHORT).show();
                }
                dbManager.close();
            }
        });
    }
}

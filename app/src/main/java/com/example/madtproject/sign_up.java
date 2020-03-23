package com.example.madtproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Button;

public class sign_up extends AppCompatActivity {
    Button b1;
    EditText e1,e2;
    DBManager dbManager;
    String uname,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();
        dbManager = new DBManager(getApplicationContext());
        b1 = findViewById(R.id.sign_up_up);
        e1 = findViewById(R.id.et_uname);
        e2 = findViewById(R.id.password_et);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbManager.open();
                if (e1.getText().toString().trim().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please provide username", Toast.LENGTH_SHORT).show();
                } else{
                    try {
                        if (e2.getText().toString().trim().length() != 0) {
                            uname = e1.getText().toString().trim();
                            password = e2.getText().toString().trim();
                            String query = "Select * From LOGIN where uname = '"+uname+"'";
                            if(dbManager.check(query)){
                                Toast.makeText(getApplicationContext(), "Already Exists!", Toast.LENGTH_SHORT).show();
                            }else{
                                dbManager.insert(uname, password);
                                Toast.makeText(getApplicationContext(), "Added successfully!", Toast.LENGTH_SHORT).show();
                                dbManager.close();
                                Intent intent = new Intent(getApplicationContext(),login.class);
                                startActivity(intent);
                                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Please provide a password!", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                }
                dbManager.close();
            }
        });
    }
}

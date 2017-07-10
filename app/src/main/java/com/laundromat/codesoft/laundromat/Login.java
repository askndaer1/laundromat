package com.laundromat.codesoft.laundromat;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.laundromat.codesoft.laundromat.Laundromast.*;

public class Login extends Activity {

    Button Login_Register_BTN,Login_Login_BTN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Login_Register_BTN = (Button) findViewById(R.id.Login_Register_BTN);

        Login_Register_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this,LaundromatList.class);
                startActivity(i);
            }
        });



        Login_Login_BTN = (Button) findViewById(R.id.Login_Login_BTN);

        Login_Login_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}

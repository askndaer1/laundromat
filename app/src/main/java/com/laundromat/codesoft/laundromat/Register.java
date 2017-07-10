package com.laundromat.codesoft.laundromat;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends Activity {

    EditText  Regester_Email_EB,Register_Password_EB;
    Button Register_Regester_BTN;

    ProgressDialog progressDialog;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        Regester_Email_EB = (EditText) findViewById(R.id.Regester_Email_EB);
        Register_Password_EB = (EditText) findViewById(R.id.Register_Password_EB);
        Register_Regester_BTN = (Button) findViewById(R.id.Register_Regester_BTN);


        mAuth = FirebaseAuth.getInstance();


        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wiat");

        Register_Regester_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                RegisterUser(Regester_Email_EB.getText().toString(),Register_Password_EB.getText().toString());

            }
        });




    }


    private void RegisterUser(String Email,String Password){

        mAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
           if(task.isSuccessful()){
               Toast.makeText(Register.this, "Register Successfull", Toast.LENGTH_SHORT).show();
               Intent i = new Intent(Register.this,Login.class);
               startActivity(i);
           }else{

               Toast.makeText(Register.this, "Error", Toast.LENGTH_SHORT).show();
           }
                progressDialog.hide();
            }
        });
    }
}

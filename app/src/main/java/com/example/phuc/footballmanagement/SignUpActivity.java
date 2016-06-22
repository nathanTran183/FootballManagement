package com.example.phuc.footballmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phuc.footballmanagement.Model.District;
import com.example.phuc.footballmanagement.Model.User;

import java.util.List;

public class SignUpActivity extends AppCompatActivity {
    EditText edtEmail,edtpassword, edtusername,edtPhone,edtRePass;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signuplayout);
        db = new DatabaseHelper(getApplicationContext());
        edtEmail = (EditText) findViewById(R.id.txtEmailSignup);
        edtpassword = (EditText) findViewById(R.id.txtPasswordSignUp);
        edtusername = (EditText) findViewById(R.id.txtUsernameSignUp);
        edtPhone = (EditText) findViewById(R.id.txtPhoneSignUp);
        edtRePass = (EditText) findViewById(R.id.txtRePassSignup);
        Button btnSignUp = (Button) findViewById(R.id.btnSignUp);



        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString();
                String password = edtpassword.getText().toString();
                String username = edtusername.getText().toString();
                String phone = edtPhone.getText().toString();
                String rePass = edtRePass.getText().toString();
                if("".equals(email)||"".equals(password)||"".equals(username)||"".equals(phone)||"".equals(edtRePass))
                    Toast.makeText(SignUpActivity.this, "Please do not let anyfield blanked!", Toast.LENGTH_SHORT).show();
                else if(!validateEmail(email)){
                    Toast.makeText(SignUpActivity.this, "Wrong email format", Toast.LENGTH_SHORT).show();
                } else if(!validatePassword(password)){
                    Toast.makeText(SignUpActivity.this, "Password format: Uppercase,lowercase,number,- or _ and length is from 6 to 15!", Toast.LENGTH_SHORT).show();
                }else if(!validatePassword(rePass)){
                    Toast.makeText(SignUpActivity.this, "Re-type Password format: Uppercase,lowercase,number,- or _ and length is from 6 to 15!", Toast.LENGTH_SHORT).show();
                }else if(!password.equals(rePass)){
                    Toast.makeText(SignUpActivity.this, "Password and Re-type Password doesn't match!", Toast.LENGTH_SHORT).show();
                }else if(db.checkUserExisted(username)){
                    Toast.makeText(SignUpActivity.this, "Username existed!", Toast.LENGTH_SHORT).show();
                }else {
                    db.insertUser(new User(username,password, email, phone));
                    User user = db.getUserLogined(username,password);
                    Toast.makeText(SignUpActivity.this, "Insert success!", Toast.LENGTH_SHORT).show();
                    Intent in = new Intent(SignUpActivity.this, LoginActivity.class);
                    in.putExtra("USER",user);
                    startActivity(in);
                }
            }
        });

        TextView txtLogin = (TextView) findViewById(R.id.lblLogin);
        txtLogin.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent in = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(in);
                return false;
            }
        });


    }
    public static boolean validateEmail(String email) {
        if (email == null) return false;
        return email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[a-z0-9]+)*(\\.[a-z]{2,6})$");
    }

    public static boolean validatePassword(String password) {
        if (password == null) return false;
        return password.matches("^[a-zA-z0-9_-]{6,15}$");
    }
}

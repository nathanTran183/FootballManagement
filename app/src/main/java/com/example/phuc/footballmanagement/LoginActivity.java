package com.example.phuc.footballmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phuc.footballmanagement.Model.User;

public class LoginActivity extends AppCompatActivity {
    EditText edtUsername;
    EditText edtPassword;
    TextView tevSignUp,tevForgot;
    DatabaseHelper db;
    String temp = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginlayout);
        db = new DatabaseHelper(getApplicationContext());
        edtUsername = (EditText) findViewById(R.id.txtUsername);
        edtPassword = (EditText) findViewById(R.id.txtPassword);
        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        tevSignUp = (TextView) findViewById(R.id.lblSignUp);
        tevForgot = (TextView) findViewById(R.id.lblForgot);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = edtUsername.getText().toString();
                final String password = edtPassword.getText().toString();
                if ("".equals(username) || "".equals(password)) {
                    Toast.makeText(LoginActivity.this, "Please fill in your username and password", Toast.LENGTH_SHORT).show();
                } else if (!validatePassword(password)) {
                    Toast.makeText(LoginActivity.this, "Password format: Uppercase,lowercase,number,- or _ and length is from 6 to 15!", Toast.LENGTH_SHORT).show();
                } else {
                    User user = db.getUserLogined(username, password);
                    if (user != null) {
                        Toast.makeText(getApplicationContext(), "Login successfully!", Toast.LENGTH_SHORT).show();
                        Intent in = new Intent(LoginActivity.this,MainActivity.class);
                        in.putExtra("USER", user);
                        startActivity(in);
                    } else {
                        Toast.makeText(getApplicationContext(), "No account was signed in with this username!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        tevSignUp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //chuyen trang ko co du lieu
                Intent in = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(in);
                //chuyen trang co du lieu
                String name = "Phuong";
                in.putExtra("name", name);
                return false;
            }
        });
        tevForgot.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
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

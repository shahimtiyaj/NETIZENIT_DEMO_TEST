package com.netizen.netizenittest.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.netizen.netizenittest.MainActivity;
import com.netizen.netizenittest.R;

public class AdminLoginActivity extends AppCompatActivity {
    Button btn_login;
    Button btn_NewUser;
    private EditText inputUser_Name;
    private EditText inputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_login_layout);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_NewUser = (Button) findViewById(R.id.btn_NewUser);

        inputUser_Name = (EditText) findViewById(R.id.input_userName);
        inputPassword = (EditText) findViewById(R.id.input_password);

        // Login button Click Event
        btn_login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String user_name = inputUser_Name.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();

                startActivity(new Intent(AdminLoginActivity.this, MainActivity.class));

                // Check for empty data in the form
                if (!user_name.isEmpty() && !password.isEmpty()) {
                    // login user
                    if (user_name.equals("netizen") && password.equals("123")) {
                        sucessFullToast();
                        startActivity(new Intent(AdminLoginActivity.this, MainActivity.class));
                    } else {
                        Toast.makeText(AdminLoginActivity.this, "Wrong information!", Toast.LENGTH_SHORT).show();
                    }
                } else if (inputUser_Name.getText().toString().length() == 0) {
                    inputUser_Name.setError("Please enter user name");
                    inputUser_Name.requestFocus();
                } else if (inputPassword.getText().toString().length() == 0) {
                    inputPassword.setError("Please enter password");
                    inputPassword.requestFocus();
                }
            }
        });

        //  Reg button Click Event
        btn_NewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(AdminLoginActivity.this, StudentRegActivity.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void sucessFullToast() {
        Context context = getApplicationContext();
        LayoutInflater inflater = getLayoutInflater();
        View customToastroot = inflater.inflate(R.layout.red_toast, null);
        Toast customtoast = new Toast(context);
        customtoast.setView(customToastroot);
        customtoast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 0);
        customtoast.setDuration(Toast.LENGTH_LONG);
        customtoast.show();
    }


    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        showExit();
    }

    public void showExit() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(AdminLoginActivity.this);
        builder.setMessage("Are you sure you want to exit from Netizen IT Test App?")
                .setTitle("Exit")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        builder.show();
    }
}


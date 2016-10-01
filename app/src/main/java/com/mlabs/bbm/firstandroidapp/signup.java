package com.mlabs.bbm.firstandroidapp;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Pattern;


public class signup extends AppCompatActivity implements android.view.View.OnClickListener {

    TextView fname;
    TextView lname;
    TextView uname;
    TextView email;
    TextView password;
    TextView confirmpass;
    Button create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        fname = (TextView) findViewById(R.id.fname);
        lname = (TextView) findViewById(R.id.lname);
        uname = (TextView) findViewById(R.id.uname);
        email = (TextView) findViewById(R.id.email);
        password = (TextView) findViewById(R.id.password);
        confirmpass = (TextView) findViewById(R.id.confirmpass);
        create = (Button) findViewById(R.id.button3);

        create.setOnClickListener(this);

    }
    public void onClick(View view) {
        if (view == findViewById(R.id.button3)){
            if (Pattern.compile("^\\w+.*\\w*@[a-zA-Z_]+?\\.[0-9a-zA-Z]{2,}(\\.[0-9a-zA-Z]{2,})?(\\.[0-9a-zA-Z]{2,})?$").matcher(email.getText()).matches())
            {
                if (password.getText().toString().equals(confirmpass.getText().toString())){
                    if (password.getText().toString().length() >= 8){
                        Account acct = new Account();
                        Calendar c = Calendar.getInstance();
                        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                        String formattedDate = df.format(c.getTime());
                        if (Pattern.compile("^\\w+.*\\w*@[a-zA-Z_]+?\\.[0-9a-zA-Z]{2,}$").matcher(uname.getText()).matches()) {
                            Toast.makeText(getBaseContext(), "Username should not be in Email format.", Toast.LENGTH_SHORT).show();
                        }else {

                            AccountRepo repo = new AccountRepo(this);
                            acct.acct_firstname = fname.getText().toString();
                            acct.acct_lastname = lname.getText().toString();
                            acct.acct_username = uname.getText().toString();
                            acct.acct_email = email.getText().toString();
                            acct.acct_password = password.getText().toString();
                            acct.acct_datecreated = formattedDate;
                            if(repo.isExisting(acct) == false){
                                int a = 0;
                                a = repo.createAccount(acct);
                                Toast.makeText(this, "Account Registered!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(signup.this, MainActivity.class);


                                startActivity(intent);}
                            else{
                                Toast.makeText(getBaseContext(), "Username/Email already exists!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    else  {
                        Toast.makeText(this, "Password must contain 8 or more characters.", Toast.LENGTH_SHORT).show();
                    }
                }
                else   {
                    Toast.makeText(this, "Password does not match.", Toast.LENGTH_SHORT).show();
                }
            }
            else   {
                Toast.makeText(this, "Incorrect e-mail format.", Toast.LENGTH_SHORT).show();
            }
        }
    }
    protected  void onPause() {
        super.onPause();
        finish();
    }
}
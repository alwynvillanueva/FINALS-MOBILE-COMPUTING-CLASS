package com.mlabs.bbm.firstandroidapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.view.View.OnClickListener;
import java.util.regex.Pattern;

import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText Email;
    EditText Password;
    Button LogIn;
    Button Show;
    TextView SignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Email = (EditText) findViewById(R.id.editText);
        Password = (EditText) findViewById(R.id.editText2);
        LogIn = (Button) findViewById(R.id.button);
        Show = (Button) findViewById(R.id.show);
        SignUp = (TextView) findViewById(R.id.button2);

        LogIn.setOnClickListener(new OnClickListener() {
                                     @Override
                                     public void onClick(View v) {
                                         //if (Pattern.compile("^\\w+.*\\w*@[a-zA-Z_]+?\\.[0-9a-zA-Z]{2,}$").matcher(Email.getText()).matches() && Password.length() >= 8){
                                         AccountRepo repo = new AccountRepo(getApplicationContext());
                                         boolean res = false;
                                         res = repo.validateLogin(Email.getText().toString(), Password.getText().toString());
                                         if(res == true){
                                             //this is for avoiding back button or no history
                                             finish();
                                             Intent intent = new Intent(MainActivity.this, blankAct.class);
                                             startActivity(intent);}
                                         else  {
                                             Toast.makeText(getBaseContext(), "Username/Email or password is incorrect.",Toast.LENGTH_SHORT).show();
                                         }
                                         //} else {
                                         //    Toast.makeText(getBaseContext(), "Email or Password is Incorrect", Toast.LENGTH_SHORT).show();
                                         //}
                                     }
                                 }
        );

        Show.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionevent) {

                final int cursor = Password.getSelectionStart();

                int event = motionevent.getAction();

                // if (event == motionevent.ACTION_DOWN) {
                //    Password.setTransformationMethod(null);

                //}
                //  if (event == motionevent.ACTION_UP) {
                //    Password.setTransformationMethod(new PasswordTransformationMethod());
                //}

                switch (motionevent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.d("login","ACTION_DOWN");
                        Password.setTransformationMethod(null);
                        Password.setSelection(cursor);
                        break;

                    case MotionEvent.ACTION_UP:
                        Log.d("login","ACTION_UP");
                        Password.setTransformationMethod(new PasswordTransformationMethod());
                        Password.setSelection(cursor);
                        break;
                }
                return true;
            }
        });

        SignUp.setOnClickListener(new OnClickListener() {
                                      @Override
                                      public void onClick(View v) {

                                          Intent intent = new Intent(MainActivity.this, signup.class);
                                          startActivity(intent);
                                          Email.setText("");
                                          Password.setText("");
                                      }


                                  }
        );
    }


}


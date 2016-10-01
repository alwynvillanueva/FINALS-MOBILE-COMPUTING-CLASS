package com.mlabs.bbm.firstandroidapp;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by androidstudio on 10/09/16.
 */
public class blankAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blank);

        final ImageView img;


        img = (ImageView)findViewById(R.id.imageView);


        img.setOnTouchListener(new View.OnTouchListener() {
            float x;
            float y;
            float x1;
            float y1;
            float x2;
            float y2;
            String Direction1="";
            String Direction2="";
            String Quadrant="";

            @Override
            public boolean onTouch(View v, MotionEvent e) {
                TextView tx = (TextView) findViewById(R.id.textview1);
                TextView ty = (TextView) findViewById(R.id.textview2);
                TextView tv3 = (TextView) findViewById(R.id.textview3);
                TextView tv4 = (TextView) findViewById(R.id.textview4);
                TextView tv5 = (TextView) findViewById(R.id.textview5);
                TextView tv6 = (TextView) findViewById(R.id.textview6);
                switch (e.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x=e.getX();
                        y=e.getY();
                        tx.setText(" ");

                        ty.setText(" ");
                        tv3.setText(" ");
                        tv4.setText(" ");
                        tv5.setText(" ");
                        tv6.setText(" ");
                        return true;
                    case MotionEvent.ACTION_UP:
                        x1=e.getX();
                        y1=e.getY();
                        if (x<x1){

                            Direction1 = "Left To Right";

                        }else{
                            Direction1="Right To Left";
                        }
                        if (y<y1){

                            Direction2 = "Top to Bottom";

                        }else
                        {
                            Direction2="Bottom to Top";
                        }
                        if (x1>(img.getMeasuredWidth()/2)  && y1>(img.getMeasuredHeight()/2)){
                            Quadrant="Quadrant 4";
                        }
                        else if(x1<(img.getMeasuredWidth()/2) && y1>(img.getMeasuredHeight()/2)){
                            Quadrant="Quadrant 3";
                        }
                        else if(x1>(img.getMeasuredWidth()/2) && y1<(img.getMeasuredHeight()/2)){
                            Quadrant="Quadrant 1";
                        }
                        else if(x1<(img.getMeasuredWidth()/2) && y1<(img.getMeasuredHeight()/2)){
                            Quadrant="Quadrant 2";
                        }
                        if (x1==(img.getMeasuredWidth()/2) || y1==(img.getMeasuredHeight()/2)){
                            Quadrant="No Quadrant";
                        }
                        if(x>x1){
                            x2= x-x1;
                        }
                        else if(x<x1){
                            x2=x1-x;
                        }
                        if(y>y1){
                            y2= y-y1;
                        }
                        else if(y<y1){
                            y2=y1-y;
                        }
                        if (x==x1){
                            x2=0;
                        }
                        if (y==y1){
                            y2=0;
                        }
                        if(x2 < 20 && y2 <20){
                            Direction1="No swipe from left or right";
                            Direction2="No swipe from top or bottom";

                        }

                        tx.setText("x1="+x+" to x2="+x1);

                        ty.setText("y1="+y+" to y2="+y1);
                        tv3.setText("Difference of x= "+(x2));
                        tv4.setText("Difference of y= "+(y2));
                        tv5.setText("Direction = "+Direction1 +" and "+ Direction2 );
                        tv6.setText("Quadrant: "+ Quadrant );

                }
                return  false;
            }

        });


    }

}

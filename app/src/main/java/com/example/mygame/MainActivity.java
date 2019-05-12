package com.example.mygame;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;


public class MainActivity extends AppCompatActivity {
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        StatClass stat = new StatClass(this);
        ImageView title = new ImageView(this);
        title.setImageResource(R.drawable.logo);
        RelativeLayout.LayoutParams logo;
        if(stat.height<stat.width){
            logo = new RelativeLayout.LayoutParams(stat.width-stat.width/5, stat.height/2);
            logo.topMargin = stat.height/2-stat.height/4-stat.height/8;
            logo.leftMargin = stat.width/2-(stat.width-stat.width/5)/2;}
        else {
            logo = new RelativeLayout.LayoutParams(stat.width-stat.width/20, stat.height/3);
            logo.topMargin = stat.height/2-stat.height/6-stat.height/10;
            logo.leftMargin = stat.width/2-(stat.width-stat.width/20)/2;
        }

        title.setLayoutParams(logo);

        Button zero = new Button(this);
        zero.setId(12);
        zero.setBackgroundResource(R.drawable.but);
        zero.setText("play");
        zero.setTextSize(15);
        zero.setTextColor(Color.WHITE);
        RelativeLayout.LayoutParams b1;
        if(stat.height<stat.width){
            b1 = new RelativeLayout.LayoutParams(stat.width/2, stat.height/8);
            b1.topMargin = stat.height/2-stat.height/16+stat.height/8;}
        else {
            b1 = new RelativeLayout.LayoutParams(stat.width/2, stat.height/15);
            b1.topMargin = stat.height/2-stat.height/30+stat.height/16;
        }
        b1.leftMargin = stat.width/2-stat.width/4;
        zero.setLayoutParams(b1);


        zero.setOnClickListener(new View.OnClickListener(){
            @Override
            @RequiresApi(api = Build.VERSION_CODES.M)
            public void onClick(View v){
                Intent i = new Intent(MainActivity.this, Game.class);
                startActivity(i);
            }
        });
        addContentView(zero,b1);
        addContentView(title, logo);

    }
}
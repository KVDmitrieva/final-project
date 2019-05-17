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

import static com.example.mygame.DrawThread.dungeon;
import static com.example.mygame.DrawThread.enemies;
import static com.example.mygame.StatClass.score;


public class MainActivity extends AppCompatActivity {
    StatClass stat;
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

       stat = new StatClass(this);
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
        zero.setText(R.string.play);
        zero.setTextSize(15);
        zero.setTextColor(Color.WHITE);

        final Button scored = new Button(this);
        scored.setId(13);
        scored.setBackgroundResource(R.drawable.but);
        scored.setText(R.string.stat);
        scored.setTextSize(15);
        scored.setTextColor(Color.WHITE);


        RelativeLayout.LayoutParams b1, b2;
        if(stat.height<stat.width){
            b1 = new RelativeLayout.LayoutParams(stat.width/2, stat.height/8);
            b1.topMargin = stat.height/2-stat.height/16+stat.height/8;
            b2 = new RelativeLayout.LayoutParams(stat.width/2, stat.height/8);
            b2.topMargin = stat.height/2-stat.height/16+stat.height/4+50;
        }
        else {
            b1 = new RelativeLayout.LayoutParams(stat.width/2, stat.height/15);
            b1.topMargin = stat.height/2-stat.height/30+stat.height/16;
            b2 = new RelativeLayout.LayoutParams(stat.width/2, stat.height/15);
            b2.topMargin = stat.height/2-stat.height/30+stat.height/8+50;
        }
        b1.leftMargin = stat.width/2-stat.width/4;
        b2.leftMargin = stat.width/2-stat.width/4;


        zero.setLayoutParams(b1);
        scored.setLayoutParams(b2);


        zero.setOnClickListener(new View.OnClickListener(){
            @Override
            @RequiresApi(api = Build.VERSION_CODES.M)
            public void onClick(View v){
                score = 0;
                enemies = 0;
                dungeon = 0;
              Intent i = new Intent(MainActivity.this, Game.class);
                startActivity(i);
            }
        });

        scored.setOnClickListener(new View.OnClickListener(){
            @Override
            @RequiresApi(api = Build.VERSION_CODES.M)
            public void onClick(View v){
                Intent c = new Intent(MainActivity.this, Statistic.class);
                startActivity(c);
            }
        });

        addContentView(scored, b2);
        addContentView(zero,b1);
        addContentView(title, logo);

    }
}
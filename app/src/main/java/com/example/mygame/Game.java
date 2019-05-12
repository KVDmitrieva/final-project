package com.example.mygame;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import static com.example.mygame.DrawThread.a;
import static com.example.mygame.DrawThread.canceled;
import static com.example.mygame.DrawThread.dir;
import static com.example.mygame.DrawThread.intersect;
import static com.example.mygame.DrawView.s;

import com.erz.joysticklibrary.JoyStick;
import com.erz.joysticklibrary.JoyStick.JoyStickListener;


public class Game extends AppCompatActivity implements JoyStickListener{
    Button zero;
    DrawView j;
    FrameLayout frame;
    RelativeLayout rel;
    Context context;
    AlertDialog.Builder ad;
    AlertDialog adTrueDialog;
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        context = this;



       // FirebaseDatabase database = FirebaseDatabase.getInstance();
       // DatabaseReference myRef = database.getReference("message");

       // myRef.setValue("Hello, World!");


        StatClass stat = new StatClass(this);
        frame = new FrameLayout(this);
        rel = new RelativeLayout(this);


        ImageView title = new ImageView(this);
        title.setImageResource(R.drawable.gameover);
        RelativeLayout.LayoutParams logo;
        title.setVisibility(View.INVISIBLE);
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


        zero = new Button(this);
        zero.setId(0);
        zero.setBackgroundResource(R.drawable.exit);
        RelativeLayout.LayoutParams b1;
        int size;
        if(stat.width<stat.height) size = stat.width/15;
        else size = stat.height/15;
            b1 = new RelativeLayout.LayoutParams(size, size);
            b1.leftMargin = stat.width - size - 10;
            b1.topMargin = 20;


        zero.setLayoutParams(b1);
        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Game.this,MainActivity.class);
                startActivity(i);
            }
        });

        j = new DrawView(this);

        JoyStick joystick = new JoyStick(this);
        joystick.setBackgroundColor(Color.TRANSPARENT);
        joystick.setPadColor(Color.TRANSPARENT);
        joystick.setButtonColor(Color.TRANSPARENT);
        RelativeLayout.LayoutParams joys = new RelativeLayout.LayoutParams(stat.width/2, 500);
        if(stat.width>stat.height)
        joys.topMargin = 2*(stat.height/4);
        else joys.topMargin = 3*(stat.height/4);
        joys.leftMargin = 0;
        joystick.setLayoutParams(joys);
        joystick.setListener(this);
        joystick.setType(JoyStick.TYPE_4_AXIS);

        JoyStick joystick2 = new JoyStick(this);
        joystick2.setBackgroundColor(Color.TRANSPARENT);
        joystick2.setPadColor(Color.TRANSPARENT);
        joystick2.setButtonColor(Color.TRANSPARENT);
        RelativeLayout.LayoutParams joys2 = new RelativeLayout.LayoutParams(stat.width/2, 500);
        if(stat.width>stat.height)
            joys2.topMargin = 2*(stat.height/4);
        else joys2.topMargin = 3*(stat.height/4);
        joys2.leftMargin = stat.width/2;
        joystick2.setLayoutParams(joys2);
        joystick2.setListener(this);
        joystick2.setType(JoyStick.TYPE_4_AXIS);

        String message = getString(R.string.mess);
        String button1String = getString(R.string.y);
        String button2String = getString(R.string.n);

        ad = new AlertDialog.Builder(context);
        ad.setMessage(message);

        ad.setNegativeButton(button2String, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                canceled = true;
                adTrueDialog.cancel();
            }
        });
        ad.setPositiveButton(button1String, new  DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int arg1) {
                canceled = false;
                intersect = 0;
                a=0;

                adTrueDialog.cancel();
            }
        });




        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        rel.setLayoutParams(params);
        frame.addView(j);
        frame.addView(joystick);
        frame.addView(joystick2);
        frame.addView(zero);
        frame.addView(rel);
        frame.setId(567);
        setContentView(frame);

    }

    @Override
    public void onMove(JoyStick joyStick, double angle, double power, int direction) {
        joyStick.setButtonColor(Color.parseColor("#55ffffff"));
        //joyStick.setButtonColor(getColor(R.color.joystick));
        dir = joyStick.getDirection();
        if (dir==-1) joyStick.setButtonColor(Color.TRANSPARENT);
        if(intersect==1){
            intersect =0;
           // ad.show();
            adTrueDialog = ad.show();
        }


    }

    @Override
    public void onTap() {

    }

    @Override
    public void onDoubleTap() {

    }

}

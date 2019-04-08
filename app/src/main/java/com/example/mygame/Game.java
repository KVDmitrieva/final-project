package com.example.mygame;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import static com.example.mygame.DrawView.s;
public class Game extends AppCompatActivity {

    DrawView j;
    FrameLayout frame;
    RelativeLayout rel;
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        j = new DrawView(this);

        frame = new FrameLayout(this);
        rel = new RelativeLayout(this);




        //first button
        Button zero = new Button(this);
        zero.setText("0");
        zero.setId(0);
        RelativeLayout.LayoutParams b1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        b1.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        zero.setLayoutParams(b1);

        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s =0;
            }
        });

        //second button
        Button one = new Button(this);
        one.setText("1");
        one.setId(1);
        RelativeLayout.LayoutParams b2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        b2.addRule(RelativeLayout.ALIGN_PARENT_END, RelativeLayout.TRUE);
        one.setLayoutParams(b2);

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s =1;
            }
        });


        //third button
        Button two = new Button(this);
        two.setText("2");
        two.setId(2);
        RelativeLayout.LayoutParams b3 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        b3.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        two.setLayoutParams(b3);

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s =2;
            }
        });


        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        rel.setLayoutParams(params);
        rel.addView(zero);
        rel.addView(one);
        rel.addView(two);
        frame.addView(j);
        frame.addView(rel);

        setContentView(frame);
    }
}

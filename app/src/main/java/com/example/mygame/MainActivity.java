package com.example.mygame;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button but = (Button)findViewById(R.id.log);

            but.setOnClickListener(new View.OnClickListener(){
            @Override
            @RequiresApi(api = Build.VERSION_CODES.M)
            public void onClick(View v){
                Intent i = new Intent(MainActivity.this, Game.class);
                startActivity(i);
            }
        });


    }
}
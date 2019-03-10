package com.example.mygame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Game extends AppCompatActivity {

    DrawView j;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        j = new DrawView(this);
        setContentView(j);
    }
}

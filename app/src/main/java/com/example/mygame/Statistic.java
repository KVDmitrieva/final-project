package com.example.mygame;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static com.example.mygame.DrawThread.dungeon;
import static com.example.mygame.DrawThread.enemies;
import static java.lang.Thread.currentThread;

public class Statistic extends AppCompatActivity {

   // DBHelper dbHelper;
    //SQLiteDatabase database;
    //Cursor cursor;



    @SuppressLint("ResourceType")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            this.requestWindowFeature(Window.FEATURE_NO_TITLE);
            getSupportActionBar().hide();
            setContentView(R.layout.statistic);

            StatClass stat = new StatClass(this);
            int width = stat.width;
            int height = stat.height;
         /*   dbHelper = new DBHelper(this);
            database = dbHelper.getReadableDatabase();
            final ContentValues contentValues = new ContentValues();

       // Cursor cursor = database.query(DBHelper.TABLE_STATISTIC, null,new int[]{"MAX(DBHelper.KEY_SCORE)"} , null, null, null, null);
       // cursor = database.rawQuery("SELECT MAX(DBHelper.KEY_SCORE) FROM DBHelper.TABLE_STATISTIC", null);
        cursor.moveToFirst();
       // String maxScore = cursor.getString(0);

        //cursor = database.rawQuery("SELECT MAX(DBHelper.KEY_LEVEL) FROM DBHelper.TABLE_STATISTIC", null);
        cursor.moveToFirst();
        //String maxLevels = cursor.getString(0);

        //cursor = database.rawQuery("SELECT MAX(DBHelper.KEY_ENEMIES) FROM DBHelper.TABLE_STATISTIC", null);
        cursor.moveToFirst();
        //String maxEnemy = cursor.getString(0);

*/




        Button back = findViewById(R.id.back);
            TextView highScore = findViewById(R.id.score);
            TextView maxLevel = findViewById(R.id.level);
            TextView maxEnemies = findViewById(R.id.enemies);

           // highScore.setText(R.string.score+" "+maxScore);
            //maxLevel.setText(R.string.level+" "+maxLevels);
            //maxEnemies.setText(R.string.enemies+" "+maxEnemy);

            LinearLayout linearLayout = findViewById(R.id.linear);
            RelativeLayout.LayoutParams b1;
            if(stat.height<stat.width){
                b1 = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 3*height/4);
                b1.topMargin = 0;
                b1.leftMargin = 0;}
            else {
                b1 = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, height/2);
                b1.topMargin = 0;
                b1.leftMargin = 0;
            }


            back.setOnClickListener(new View.OnClickListener(){
                @Override
                @RequiresApi(api = Build.VERSION_CODES.M)
                public void onClick(View v){
                    Intent c = new Intent(Statistic.this, MainActivity.class);
                    startActivity(c);
                }
            });



        }
    }

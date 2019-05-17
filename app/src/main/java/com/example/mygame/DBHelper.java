package com.example.mygame;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.mygame.DrawThread.dungeon;
import static com.example.mygame.DrawThread.enemies;
import static com.example.mygame.StatClass.score;

public class DBHelper extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "statisticDB";
    public static final String TABLE_STATISTIC = "statistic";

    public static final String KEY_ID = "_id";
    public static final String KEY_SCORE = "score";
    public static final String KEY_LEVEL = "level";
    public static final String KEY_ENEMIES = "enemy";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_STATISTIC + "(" + KEY_ID
                + " integer primary key," + KEY_SCORE + " int," + KEY_LEVEL + " int," + KEY_ENEMIES+"int"+")");
        final ContentValues contentValues = new ContentValues();
       // contentValues.put(DBHelper.KEY_SCORE, score);
        //contentValues.put(DBHelper.KEY_LEVEL, dungeon);
        //contentValues.put(DBHelper.KEY_ENEMIES, enemies);
        //db.insert(DBHelper.TABLE_STATISTIC, null, contentValues);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_STATISTIC);

        onCreate(db);

    }
}
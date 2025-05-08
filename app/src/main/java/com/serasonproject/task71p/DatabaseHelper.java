package com.serasonproject.task71p;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "LostFoundDb";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "item";
    public static final String COL1 = "iId";
    public static final String COL2 = "iType";
    public static final String COL3 = "iName";
    public static final String COL4 = "iPhone";
    public static final String COL5 = "iDesc";
    public static final String COL6 = "iDate";
    public static final String COL7 = "iLoc";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + "("
            + COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL2 + " TEXT(5) NOT NULL, "
            + COL3 + " TEXT(50) NOT NULL, "
            + COL4 + " TEXT(10) NOT NULL, "
            + COL5 + " TEXT(100) NOT NULL, "
            + COL6 + " TEXT(8), "
            + COL7 + " TEXT);");

        insertSampleData(sqLiteDatabase); // CAN REMOVE, IF YOU WISH. METHOD DIRECTLY BELOW.
    }

    public void insertSampleData(SQLiteDatabase sqLiteDatabase) {
        // test data hardcoded for initial testing
        ContentValues vals = new ContentValues();
        vals.put(COL2, "Lost");
        vals.put(COL3, "White ragdoll cat");
        vals.put(COL4, "0401123456");
        vals.put(COL5, "Large, white ragdoll cat, answers to Jojo. Ran away from home in Brunswick last Thursday.");
        vals.put(COL6, "20250501");
        vals.put(COL7, "Sydney Rd, Brunswick");
        sqLiteDatabase.insert(TABLE_NAME, null, vals);

        vals = new ContentValues();
        vals.put(COL2, "Found");
        vals.put(COL3, "Volvo keyring with keys");
        vals.put(COL4, "0390112222");
        vals.put(COL5, "Found collection of 25 keys on a Volvo keyring on the ground at petrol station.");
        vals.put(COL6, "20250420");
        vals.put(COL7, "7-Eleven, Moonee Ponds");
        sqLiteDatabase.insert(TABLE_NAME, null, vals);

        vals = new ContentValues();
        vals.put(COL2, "Found");
        vals.put(COL3, "Fjallraven Kanken backpack (purple)");
        vals.put(COL4, "0431243568");
        vals.put(COL5, "I found a purple Fjallraven backpack under a tree in Fitzroy Gardens on the west side.");
        vals.put(COL6, "20250502");
        vals.put(COL7, "Fitzroy Gardens, Melbourne");
        sqLiteDatabase.insert(TABLE_NAME, null, vals);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}

package com.serasonproject.task71p;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DbConnection {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public DbConnection(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void addItem(LostFoundItem newItem) {
        open();

        ContentValues vals = new ContentValues();

        vals.put(DatabaseHelper.COL2, newItem.getItemType());
        vals.put(DatabaseHelper.COL3, newItem.getItemName());
        vals.put(DatabaseHelper.COL4, newItem.getItemPhone());
        vals.put(DatabaseHelper.COL5, newItem.getItemDesc());
        vals.put(DatabaseHelper.COL6, newItem.getiDate());
        vals.put(DatabaseHelper.COL7, newItem.getiLoc());

        db.insert(DatabaseHelper.TABLE_NAME, null, vals);
        close();
    }

    public LostFoundItem selectItem(int tIdentifier) {
        open();

        Cursor c = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME + " WHERE " + DatabaseHelper.COL1 + " = ?", new String[]{String.valueOf(tIdentifier)});
        LostFoundItem lfItem = new LostFoundItem();
        if(c.moveToFirst()) {
            lfItem.setItemId(c.getInt(0));
            lfItem.setItemType(c.getString(1));
            lfItem.setItemName(c.getString(2));
            lfItem.setItemPhone(c.getString(3));
            lfItem.setItemDesc(c.getString(4));
            lfItem.setiDate(c.getString(5));
            lfItem.setiLoc(c.getString(6));
        }
        c.close();

        close();
        return lfItem;
    }

    public void deleteItem(LostFoundItem i) {
        open();

        db.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper.COL1 + " = ?", new String[]{String.valueOf(i.getItemId())});
        close();
    }

    public List<LostFoundItem> getAllItems() {
        open();

        List<LostFoundItem> iList = new ArrayList<>();

        Cursor curs = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME + " ORDER BY iDate", null);
        if(curs.moveToFirst()) {
            do {
                LostFoundItem i = new LostFoundItem();
                i.setItemId(curs.getInt(0));
                i.setItemType(curs.getString(1));
                i.setItemName(curs.getString(2));
                i.setItemPhone(curs.getString(3));
                i.setItemDesc(curs.getString(4));
                i.setiDate(curs.getString(5));
                i.setiLoc(curs.getString(6));

                iList.add(i);
            } while (curs.moveToNext());
        }
        curs.close();

        close();
        return iList;
    }
}

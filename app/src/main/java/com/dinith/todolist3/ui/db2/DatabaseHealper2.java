package com.dinith.todolist3.ui.db2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHealper2 extends SQLiteOpenHelper {

    //database name
    public static final String DATABASE_NAME = "databasedin2";
    //database version
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "tbl_notes2";
    public static final String Description = "Description";

    public DatabaseHealper2(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query;
        //creating table
        query = "CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY, Title TEXT, Description TEXT , color_cd TEXT)";
        db.execSQL(query);
    }

    //upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //add the new note
    public void addNotes(String title, String des,String color_code) {
        SQLiteDatabase sqLiteDatabase = this .getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Title", title);
        values.put("Description", des);
        values.put("color_cd", color_code);
        //inserting new row
        sqLiteDatabase.insert(TABLE_NAME, null , values);
        //close database connection
        sqLiteDatabase.close();
    }

    //get the all notes
    public ArrayList<NoteModel2> getNotes() {
        ArrayList<NoteModel2> arrayList = new ArrayList<>();

        // select all query
        String select_query= "SELECT *FROM " + TABLE_NAME;

        SQLiteDatabase db = this .getWritableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                NoteModel2 noteModel = new NoteModel2();
                noteModel.setID2(cursor.getString(0));
                noteModel.setTitle2(cursor.getString(1));
                noteModel.setDes2(cursor.getString(2));
                noteModel.setcolor_cd2(cursor.getString(3));
                arrayList.add(noteModel);
            }while (cursor.moveToNext());
        }
        return arrayList;
    }

    //delete the note
    public void delete(String ID) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //deleting row
        sqLiteDatabase.delete(TABLE_NAME, "ID=" + ID,null);
        sqLiteDatabase.close();
    }

    public void deletefrm(String name) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //deleting row
       // sqLiteDatabase.delete(TABLE_NAME, "ID=" + ID,null);
        sqLiteDatabase.delete(TABLE_NAME, "Description=" + name, null);

        sqLiteDatabase.close();
    }

    public void deleteRow(String value)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME+ " WHERE "+Description+"='"+value+"'");
        db.close();
    }

    public void updateeRow(String value)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE " + TABLE_NAME+ " SET "+Description+"='"+value+"'");
        db.close();
    }



    //update the note
    public void updateNote(String title, String des, String ID) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values =  new ContentValues();
        values.put("Title", title);
        values.put("Description", des);
        //updating row
        sqLiteDatabase.update(TABLE_NAME, values, "ID=" + ID, null);
        sqLiteDatabase.close();
    }

    public ArrayList<NoteModel2> searchNotes(String value) {
        ArrayList<NoteModel2> arrayList = new ArrayList<>();

        // select all query
        String select_query= "SELECT *FROM " + TABLE_NAME+" WHERE Title LIKE '"+value+"'\n" +
                "   OR Title GLOB '*[^a-zA-Z][mM][iI][xX]*'";

        SQLiteDatabase db = this .getWritableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                NoteModel2 noteModel = new NoteModel2();
                noteModel.setID2(cursor.getString(0));
                noteModel.setTitle2(cursor.getString(1));
                noteModel.setDes2(cursor.getString(2));
                noteModel.setcolor_cd2(cursor.getString(3));
                arrayList.add(noteModel);
            }while (cursor.moveToNext());
        }
        return arrayList;
    }
}
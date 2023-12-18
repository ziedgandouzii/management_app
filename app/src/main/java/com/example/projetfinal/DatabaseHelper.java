package com.example.projetfinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

class DatabaseHelper extends SQLiteOpenHelper {

    public static final String databaseName = "Compte.db";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "Compte.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {
        // Define the table structure with adaptable column names
        MyDatabase.execSQL("CREATE TABLE IF NOT EXISTS Compte (id INTEGER PRIMARY KEY AUTOINCREMENT, login TEXT, password TEXT, role TEXT)");
        Log.d("DatabaseHelper", "Table created successfully");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("DROP TABLE IF EXISTS Compte");
        onCreate(MyDB);
    }

    public Boolean insertData(String login, String password, String role) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("login", login);
        contentValues.put("password", password);
        contentValues.put("role", role);
        long result = MyDatabase.insert("Compte", null, contentValues);
        return result != -1;
    }

    public Boolean checkLogin(String login) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("SELECT * FROM Compte WHERE login = ?", new String[]{login});
        return cursor.getCount() > 0;
    }

    public Boolean checkLoginPassword(String login, String password) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("SELECT * FROM Compte WHERE login = ? AND password = ?", new String[]{login, password});
        return cursor.getCount() > 0;
    }
}

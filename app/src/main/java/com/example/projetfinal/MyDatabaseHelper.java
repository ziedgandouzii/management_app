package com.example.projetfinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.Nullable;

class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "productlibrary.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "produit";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_LIBELLE = "libelle";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_PHOTO = "photo";
    private static final String COLUMN_PRIX = "prix";
    private static final String COLUMN_QUANTITE = "quantite";

    MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_LIBELLE + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT, " +
                COLUMN_PHOTO + " TEXT, " +
                COLUMN_PRIX + " REAL, " +
                COLUMN_QUANTITE + " INTEGER);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addProduct(String libelle, String description, String photo, double prix, int quantite){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_LIBELLE, libelle);
        cv.put(COLUMN_DESCRIPTION, description);
        cv.put(COLUMN_PHOTO, photo);
        cv.put(COLUMN_PRIX, prix);
        cv.put(COLUMN_QUANTITE, quantite);

        long result = db.insert(TABLE_NAME, null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData(String product_id, String libelle, String description, String photo, double prix, int quantite){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_LIBELLE, libelle);
        cv.put(COLUMN_DESCRIPTION, description);
        cv.put(COLUMN_PHOTO, photo);
        cv.put(COLUMN_PRIX, prix);
        cv.put(COLUMN_QUANTITE, quantite);

        long result = db.update(TABLE_NAME, cv, "id=?", new String[]{product_id});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteOneProduct(String product_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "id=?", new String[]{product_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }
}

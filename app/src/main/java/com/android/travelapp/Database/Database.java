package com.android.travelapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.android.travelapp.Model.touristModel;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    private static final int Database_version = 1;
    private static final String Database_name = "travel_app_test";
    private static final String Table_tourist = "Tourist";
    private static final String Column_id = "Id";
    private static final String Column_name = "Name";
    private static final String Column_price = "Price";
    private static final String Column_img = "Img";
    private static final String Column_describe = "Describe";
    private static final String Column_location = "Location";
    public ArrayList<touristModel> listTourist;

    public Database(Context context) {
        super(context, Database_name, null, Database_version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String Create_tourist_table = "CREATE TABLE " +
                Table_tourist + "(" + Column_id +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Column_name + " VARCHAR(100), " +
                Column_price + " INTEGER, " +
                Column_img + " BLOB, " +
                Column_describe + " VARCHAR(200), " +
                Column_location + " VARCHAR(200))";
        sqLiteDatabase.execSQL(Create_tourist_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop Table if exists " + Table_tourist);
        onCreate(sqLiteDatabase);
    }
    public ArrayList<touristModel> listTourist(){
        String sql = "select * from " + Table_tourist;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<touristModel> touristModelList = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name_tour = cursor.getString(1);
                int price_tour = cursor.getInt(2);
                byte[] img_tour = cursor.getBlob(3);
                String desc_tour = cursor.getString(4);
                String location = cursor.getString(5);
                touristModelList.add(new touristModel(id, name_tour, price_tour, img_tour, desc_tour, location));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return touristModelList;
    }

    public Boolean addTouristData(touristModel touristModel){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Column_name, touristModel.getAl_name_tour());
        contentValues.put(Column_price, touristModel.getAl_price_tour());
        contentValues.put(Column_img, touristModel.getAl_img_tour());
        contentValues.put(Column_describe,touristModel.getAl_desc_tour());
        contentValues.put(Column_location, touristModel.getAl_location());
        long result = database.insert(Table_tourist, null, contentValues);
        return result != -1;
    }
}

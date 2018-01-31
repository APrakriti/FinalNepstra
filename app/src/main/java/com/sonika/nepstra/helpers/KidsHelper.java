package com.sonika.nepstra.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.sonika.nepstra.pojo.Kids_pojo;
import com.sonika.nepstra.pojo.Mens_pojo;

import java.util.ArrayList;

/**
 * Created by sonika on 10/17/2017.
 */

public class KidsHelper extends SQLiteOpenHelper {
    static int DATABASE_VERSION = 1;
    static String DATABASE_NAME = "kids";

    String KIDS_TABLE = "CREATE TABLE if not exists `kids`  (\n" +
            "                       `id` INTEGER PRIMARY KEY ,\n" +
            "                       `c_id` INTEGER,\n" +
            "                       `i_id` INTEGER,\n" +
            "                       `name` TEXT,\n" +
            "                       `price` TEXT,\n" +
            "                       `imageone` TEXT\n" +
            "                       `desc` TEXT\n" +
            "                      );";

    public KidsHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        getWritableDatabase().execSQL(KIDS_TABLE);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public  void  deleteKids()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from kids");
    }
    public void insertkids(ContentValues cv) {
        getWritableDatabase().insert("kids", "", cv);
        Log.e("poker", "yes");

    }

    public ArrayList<Kids_pojo> getkids() {
        Log.e("getOrderMessage", "vaeraxa");
        String sql = "select * from kids";
        ArrayList<Kids_pojo> list = new ArrayList<Kids_pojo>();
        Cursor cursor = getWritableDatabase().rawQuery(sql, null);
        while (cursor.moveToNext()) {
            Log.e("movettonext", "lolo");
            // Do Somehing here
            Kids_pojo orderinfo = new Kids_pojo();
            orderinfo.kidsid = cursor.getInt(cursor.getColumnIndex("id"));
            orderinfo.kidscid = cursor.getInt(cursor.getColumnIndex("c_id"));
            orderinfo.kidsi_id = cursor.getInt(cursor.getColumnIndex("i_id"));
            orderinfo.kidsname= cursor.getString(cursor.getColumnIndex("name"));
            orderinfo.kidsprice = cursor.getString(cursor.getColumnIndex("price"));
            orderinfo.kidsimage = cursor.getString(cursor.getColumnIndex("imageone"));
            orderinfo.kidsdesc = cursor.getString(cursor.getColumnIndex("desc"));
            list.add(orderinfo);
        }
        cursor.close();
        return list;
    }
}


package com.sonika.nepstra.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.sonika.nepstra.pojo.Jwellery_pojo;
import com.sonika.nepstra.pojo.Mens_pojo;

import java.util.ArrayList;

/**
 * Created by sonika on 10/17/2017.
 */

public class JwelleryHelper extends SQLiteOpenHelper {
    static int DATABASE_VERSION = 1;
    static String DATABASE_NAME = "jwellery";

    String Jwellery_TABLE = "CREATE TABLE if not exists `jwellery`  (\n" +
            "                       `id` INTEGER PRIMARY KEY ,\n" +
            "                       `c_id` INTEGER,\n" +
            "                       `i_id` INTEGER,\n" +
            "                       `name` TEXT,\n" +
            "                       `price` TEXT,\n" +
            "                       `desc` TEXT,\n" +
            "                       `imageone` TEXT\n" +
            "                      );";

    public JwelleryHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        getWritableDatabase().execSQL(Jwellery_TABLE);
    }
    public  void  deleteJwellery()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from jwellery");
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void insertjwellery(ContentValues cv) {
        getWritableDatabase().insert("jwellery", "", cv);
        Log.e("poker", "yes");

    }

    public ArrayList<Jwellery_pojo> getjwellery() {
        Log.e("getOrderMessage", "vaeraxa");
        String sql = "select * from jwellery";
        ArrayList<Jwellery_pojo> list = new ArrayList<Jwellery_pojo>();
        Cursor cursor = getWritableDatabase().rawQuery(sql, null);
        while (cursor.moveToNext()) {
            Log.e("movettonext", "lolo");
            // Do Somehing here
            Jwellery_pojo orderinfo = new Jwellery_pojo();
            orderinfo.jwelleryid = cursor.getInt(cursor.getColumnIndex("id"));
            orderinfo.jwellerycid = cursor.getInt(cursor.getColumnIndex("c_id"));
            orderinfo.jwelleryi_id = cursor.getInt(cursor.getColumnIndex("i_id"));
            orderinfo.jwelleryname = cursor.getString(cursor.getColumnIndex("name"));
            orderinfo.jwelleryprice = cursor.getString(cursor.getColumnIndex("price"));
            orderinfo.jwelleryimage = cursor.getString(cursor.getColumnIndex("imageone"));
            orderinfo.jwellerydesc = cursor.getString(cursor.getColumnIndex("desc"));
            list.add(orderinfo);
        }
        cursor.close();
        return list;
    }
}




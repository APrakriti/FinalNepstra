package com.sonika.nepstra.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.sonika.nepstra.pojo.ArtAndCraft_pojo;
import com.sonika.nepstra.pojo.Mens_pojo;

import java.util.ArrayList;

/**
 * Created by sonika on 10/17/2017.
 */

public class MensHelper extends SQLiteOpenHelper {
    static int DATABASE_VERSION = 1;
    static String DATABASE_NAME = "mens";

    String MEN_TABLE = "CREATE TABLE if not exists `mens`  (\n" +
            "                       `id` INTEGER PRIMARY KEY ,\n" +
            "                       `c_id` INTEGER,\n" +
            "                       `i_id` INTEGER,\n" +
            "                       `name` TEXT,\n" +
            "                       `price` TEXT,\n" +
            "                       `imageone` TEXT\n" +
            "                       `desc` TEXT\n" +
            "                      );";

    public MensHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        getWritableDatabase().execSQL(MEN_TABLE);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public  void  deleteMen()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from mens");
    }
    public void insertmen(ContentValues cv) {
        getWritableDatabase().insert("mens", "", cv);
        Log.e("poker", "yes");

    }

    public ArrayList<Mens_pojo> getmen() {
        Log.e("getOrderMessage", "vaeraxa");
        String sql = "select * from mens";
        ArrayList<Mens_pojo> list = new ArrayList<Mens_pojo>();
        Cursor cursor = getWritableDatabase().rawQuery(sql, null);
        while (cursor.moveToNext()) {
            Log.e("movettonext", "lolo");
            // Do Somehing here
            Mens_pojo orderinfo = new Mens_pojo();
            orderinfo.menid = cursor.getInt(cursor.getColumnIndex("id"));
            orderinfo.mencid = cursor.getInt(cursor.getColumnIndex("c_id"));
            orderinfo.men_i_id = cursor.getInt(cursor.getColumnIndex("i_id"));
            orderinfo.menname = cursor.getString(cursor.getColumnIndex("name"));
            orderinfo.menprice = cursor.getString(cursor.getColumnIndex("price"));
            orderinfo.menimage = cursor.getString(cursor.getColumnIndex("imageone"));
            orderinfo.menDesc = cursor.getString(cursor.getColumnIndex("desc"));
            list.add(orderinfo);
        }
        cursor.close();
        return list;
    }
}


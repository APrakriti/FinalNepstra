package com.sonika.nepstra.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.sonika.nepstra.pojo.Newarrivals_pojo;
import com.sonika.nepstra.pojo.Sports_pojo;

import java.util.ArrayList;

/**
 * Created by sonika on 10/18/2017.
 */

public class SportsHelper extends SQLiteOpenHelper {
    static int DATABASE_VERSION = 1;
    static String DATABASE_NAME = "sports";

    String SPORTS_TABLE = "CREATE TABLE if not exists `sports`  (\n" +
            "                       `id` INTEGER PRIMARY KEY ,\n" +
            "                       `c_id` INTEGER,\n" +
            "                       `i_id` INTEGER,\n" +
            "                       `name` TEXT,\n" +
            "                       `price` TEXT,\n" +
            "                       `desc` TEXT,\n" +
            "                       `imageone` TEXT\n" +
            "                      );";

    public SportsHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        getWritableDatabase().execSQL(SPORTS_TABLE);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    } public void insertsports(ContentValues cv) {
        getWritableDatabase().insert("sports", "", cv);
        Log.e("sportsinsertvaeraxa", "yes");

    }
    public  void  deleteSports()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from sports");
    }
    public ArrayList<Sports_pojo> getsports() {
        Log.e("getsportMessage", "vaeraxa");
        String sql = "select * from sports";
        ArrayList<Sports_pojo> list = new ArrayList<Sports_pojo>();
        Cursor cursor = getWritableDatabase().rawQuery(sql, null);
        while (cursor.moveToNext()) {
            Log.e("movettonext", "lolo");
            // Do Somehing here
            Sports_pojo sports = new Sports_pojo();
            sports.sportsid = cursor.getInt(cursor.getColumnIndex("id"));
            sports.sportscid = cursor.getInt(cursor.getColumnIndex("c_id"));
            sports.img_id = cursor.getInt(cursor.getColumnIndex("i_id"));
            sports.sportsname = cursor.getString(cursor.getColumnIndex("name"));
            sports.sportsprice = cursor.getString(cursor.getColumnIndex("price"));
            sports.sportsimage = cursor.getString(cursor.getColumnIndex("imageone"));
            sports.sportsdesc = cursor.getString(cursor.getColumnIndex("desc"));
            list.add(sports);
        }
        cursor.close();
        return list;
    }
}


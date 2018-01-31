package com.sonika.nepstra.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.sonika.nepstra.pojo.Newarrivals_pojo;
import com.sonika.nepstra.pojo.WomenPoducts_pojo;

import java.util.ArrayList;

/**
 * Created by sonika on 10/16/2017.
 */

public class NewArrivalsHelper extends SQLiteOpenHelper{
    static int DATABASE_VERSION = 1;
    static String DATABASE_NAME = "newarrival";

    String ARRIVAL_TABLE = "CREATE TABLE if not exists `newarrival`  (\n" +
            "                       `id` INTEGER PRIMARY KEY ,\n" +
            "                       `c_id` INTEGER,\n" +
            "                       `i_id` INTEGER,\n" +
            "                       `name` TEXT,\n" +
            "                       `price` TEXT,\n" +
            "                       `desc` TEXT,\n" +
            "                       `imageone` TEXT\n" +
            "                      );";

    public NewArrivalsHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        getWritableDatabase().execSQL(ARRIVAL_TABLE);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    } public void insertarrivals(ContentValues cv) {
        getWritableDatabase().insert("newarrival", "", cv);
        Log.e("insertvaeraxa", "yes");

    }

    public  void  deleteArrival()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from newarrival");
    }
    public ArrayList<Newarrivals_pojo> getarrivals() {
        Log.e("getOrderMessage", "vaeraxa");
        String sql = "select * from newarrival";
        ArrayList<Newarrivals_pojo> list = new ArrayList<Newarrivals_pojo>();
        Cursor cursor = getWritableDatabase().rawQuery(sql, null);
        while (cursor.moveToNext()) {
            Log.e("movettonext", "lolo");
            // Do Somehing here
            Newarrivals_pojo arrivals = new Newarrivals_pojo();
            arrivals.newid = cursor.getInt(cursor.getColumnIndex("id"));
            arrivals.newcid = cursor.getInt(cursor.getColumnIndex("c_id"));
            arrivals.img_id = cursor.getInt(cursor.getColumnIndex("i_id"));
            arrivals.newname = cursor.getString(cursor.getColumnIndex("name"));
            arrivals.newprice = cursor.getString(cursor.getColumnIndex("price"));
            arrivals.newdesc = cursor.getString(cursor.getColumnIndex("desc"));
            arrivals.newimage = cursor.getString(cursor.getColumnIndex("imageone"));
            list.add(arrivals);
        }
        cursor.close();
        return list;
    }
}

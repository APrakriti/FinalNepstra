package com.sonika.nepstra.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.sonika.nepstra.Navigations.ArtAndCraft;
import com.sonika.nepstra.pojo.ArtAndCraft_pojo;
import com.sonika.nepstra.pojo.WomenPoducts_pojo;

import java.util.ArrayList;

/**
 * Created by sonika on 10/17/2017.
 */

public class ArtAndCraftHelper extends SQLiteOpenHelper {
    static int DATABASE_VERSION = 1;
    static String DATABASE_NAME = "artandcraft";

    String ART_TABLE = "CREATE TABLE if not exists `artandcraft`  (\n" +
            "                       `id` INTEGER PRIMARY KEY ,\n" +
            "                       `c_id` INTEGER,\n" +
            "                       `i_id` INTEGER,\n" +
            "                       `name` TEXT,\n" +
            "                       `price` TEXT,\n" +
            "                       `desc` TEXT,\n" +
            "                       `imageone` TEXT\n" +
            "                      );";

    public ArtAndCraftHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        getWritableDatabase().execSQL(ART_TABLE);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public  void  deleteArtAndCraft()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from artandcraft");
    }
    public void insertwartandcraft(ContentValues cv) {
        getWritableDatabase().insert("artandcraft", "", cv);
        Log.e("poker", "yes");

    }

    public ArrayList<ArtAndCraft_pojo> getartandcraft() {
        Log.e("getOrderMessage", "vaeraxa");
        String sql = "select * from artandcraft";
        ArrayList<ArtAndCraft_pojo> list = new ArrayList<ArtAndCraft_pojo>();
        Cursor cursor = getWritableDatabase().rawQuery(sql, null);
        while (cursor.moveToNext()) {
            Log.e("movettonext", "lolo");
            // Do Somehing here
            ArtAndCraft_pojo orderinfo = new ArtAndCraft_pojo();
            orderinfo.artid = cursor.getInt(cursor.getColumnIndex("id"));
            orderinfo.artcid = cursor.getInt(cursor.getColumnIndex("c_id"));
            orderinfo.artimgid = cursor.getInt(cursor.getColumnIndex("i_id"));
            orderinfo.artname = cursor.getString(cursor.getColumnIndex("name"));
            orderinfo.artprice = cursor.getString(cursor.getColumnIndex("price"));
            orderinfo.artdesc = cursor.getString(cursor.getColumnIndex("desc"));
            orderinfo.artimage = cursor.getString(cursor.getColumnIndex("imageone"));
            list.add(orderinfo);
        }
        cursor.close();
        return list;
    }
}

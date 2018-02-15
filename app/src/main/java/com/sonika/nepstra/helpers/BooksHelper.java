package com.sonika.nepstra.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.sonika.nepstra.Navigations.ArtAndCraft;
import com.sonika.nepstra.pojo.ArtAndCraft_pojo;
import com.sonika.nepstra.pojo.Books_Pojo;
import com.sonika.nepstra.pojo.WomenPoducts_pojo;

import java.util.ArrayList;

/**
 * Created by sonika on 10/17/2017.
 */

public class BooksHelper extends SQLiteOpenHelper {
    static int DATABASE_VERSION = 1;
    static String DATABASE_NAME = "books";

    String BOOKS_TABLE = "CREATE TABLE if not exists `books`  (\n" +
            "                       `id` INTEGER PRIMARY KEY ,\n" +
            "                       `c_id` INTEGER,\n" +
            "                       `i_id` INTEGER,\n" +
            "                       `name` TEXT,\n" +
            "                       `price` TEXT,\n" +
            "                       `desc` TEXT,\n" +
            "                       `imageone` TEXT\n" +
            "                      );";

    public BooksHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        getWritableDatabase().execSQL(BOOKS_TABLE);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public  void  deleteBooks()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from books");
    }
    public void insertbooks(ContentValues cv) {
        getWritableDatabase().insert("books", "", cv);
        Log.e("poker", "yes");

    }

    public ArrayList<Books_Pojo> getbooks() {
        Log.e("getOrderMessage", "vaeraxa");
        String sql = "select * from books";
        ArrayList<Books_Pojo> list = new ArrayList<Books_Pojo>();
        Cursor cursor = getWritableDatabase().rawQuery(sql, null);
        while (cursor.moveToNext()) {
            Log.e("movettonext", "lolo");
            // Do Somehing here
            Books_Pojo orderinfo = new Books_Pojo();
            orderinfo.bookid= cursor.getInt(cursor.getColumnIndex("id"));
            orderinfo.bookcid = cursor.getInt(cursor.getColumnIndex("c_id"));
            orderinfo.bookimgid = cursor.getInt(cursor.getColumnIndex("i_id"));
            orderinfo.bookname = cursor.getString(cursor.getColumnIndex("name"));
            orderinfo.bookprice = cursor.getString(cursor.getColumnIndex("price"));
            orderinfo.bookdesc = cursor.getString(cursor.getColumnIndex("desc"));
            orderinfo.bookimage = cursor.getString(cursor.getColumnIndex("imageone"));
            list.add(orderinfo);
        }
        cursor.close();
        return list;
    }
}

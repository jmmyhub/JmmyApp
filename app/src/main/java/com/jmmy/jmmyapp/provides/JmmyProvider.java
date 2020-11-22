package com.jmmy.jmmyapp.provides;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import androidx.annotation.Nullable;

/**
 * Created by jmmy on 2017/12/15.
 */

public class JmmyProvider extends ContentProvider {
private Databasehelper databasehelper;
private SQLiteDatabase database;

    @Override
    public boolean onCreate() {
        databasehelper = new Databasehelper(getContext(), "contact_test.db");
        database = databasehelper.getWritableDatabase();
        database.execSQL(ContactMetaData.MyTableData.SQL_CREATE_TABLE);
        return true;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        database.insert(ContactMetaData.MyTableData.TABLE_NAME, null, contentValues);
        return null;
    }

    @Override
    public int delete(Uri uri, String s,  String[] strings) {
        return 0;
    }

    @Override
    public Cursor query(Uri uri, String[] strings, String s,  String[] strings1, String s1) {
        return null;
    }

    @Override
    public String getType( Uri uri) {
        return null;
    }

    @Override
    public int update(Uri uri,  ContentValues contentValues, String s,  String[] strings) {
        return 0;
    }

    public class Databasehelper extends SQLiteOpenHelper {

        public Databasehelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        public Databasehelper(Context context, String name, int version) {
            this(context, name, null, version);
        }

        public Databasehelper(Context context, String name){
            this(context, name, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(ContactMetaData.MyTableData.SQL_CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(ContactMetaData.MyTableData.SQL_CREATE_TABLE);
            onCreate(db);
        }
    }
}

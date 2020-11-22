package com.jmmy.jmmyapp.provides;

import android.net.Uri;
import android.provider.BaseColumns;

public class ContactMetaData {
    public static final String AUTHORITY = "com.jmmy.jmmyapp";

    public static final class MyTableData implements BaseColumns {
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/contact_info");
        public static final String TABLE_NAME = "contact_info";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PHONE_NUMBER = "phone_number";
        public static final String SQL_CREATE_TABLE = "create table if not exists " + TABLE_NAME
                + " (_id integer primary key autoincrement," + COLUMN_NAME
                + " text not null," + COLUMN_PHONE_NUMBER + " text not null)";
    }
}

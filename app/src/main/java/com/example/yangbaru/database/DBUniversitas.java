package com.example.yangbaru.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class DBUniversitas extends SQLiteOpenHelper {
    //mengatur nama tabel
    public static abstract class MyColumns implements BaseColumns{
       public static final String NamaTabel = "Universitas";
        public static final String Kode = "Kode";
        public static final String Nama = "Nama";
        public static final String Akreditas = "Akreditas";
        public static final String Jenis = "Jenis_Kampus";
        public static final String TanggalLahir = "Tanggal_Lahir";
        public static final String Alamat = "Alamat";
    }

    private static final String NamaDatabase = "kampus.db";

    private static final int VersiDatabase = 1;

    //Query yang digunakan untuk membuat Tabel
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE "
            +MyColumns.NamaTabel+ "("+MyColumns.Kode +" TEXT PRIMARY KEY, "
            +MyColumns.Nama+" TEXT NOT NULL," +MyColumns.Akreditas + " TEXT NOT NULL,"
            +MyColumns.Jenis +" TEXT NOT NULL, "+MyColumns.TanggalLahir
            + " TEXT NOT NULL, "+MyColumns.Alamat+" TEXT NOT NULL)";

    //Query yang digunakan untuk mengupgrade Tabel
    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS "
            +MyColumns.NamaTabel;



    public DBUniversitas(Context context) {

        super(context, NamaDatabase, null, VersiDatabase);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL(SQL_DELETE_ENTRIES);
    }
}

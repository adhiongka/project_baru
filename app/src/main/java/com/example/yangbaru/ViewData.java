package com.example.yangbaru;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yangbaru.database.DBUniversitas;

import java.util.ArrayList;

public class ViewData extends AppCompatActivity {
    private DBUniversitas MyDatabase;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList NamaList;
    private ArrayList AkreditasList;
    private ArrayList Kode;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_data);
        getSupportActionBar().setTitle("Daftar Universitas ");
        NamaList = new ArrayList<>();
        AkreditasList = new ArrayList<>();
        Kode = new ArrayList<>();
        MyDatabase = new DBUniversitas(getBaseContext());
        recyclerView = findViewById(R.id.recycler);
        getData();
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new RecyclerViewAdapter(NamaList, AkreditasList, Kode);
        recyclerView.setAdapter(adapter);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.line));
        recyclerView.addItemDecoration(itemDecoration);
    }

    @SuppressLint("Recycler")
    protected void getData(){
        SQLiteDatabase ReadData = MyDatabase.getReadableDatabase();
        Cursor cursor = ReadData.rawQuery("SELECT * FROM "+ DBUniversitas.MyColumns.NamaTabel,null);

        cursor.moveToFirst();

        for (int count = 0; count < cursor.getCount(); count++) {
            cursor.moveToPosition(count);
            Kode.add(cursor.getString(0));
            NamaList.add(cursor.getString(1));
            AkreditasList.add(cursor.getString(2));
        }
    }

}

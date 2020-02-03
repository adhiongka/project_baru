package com.example.yangbaru;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yangbaru.database.DBUniversitas;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.Calendar;

public class ViewData extends AppCompatActivity {
    private DBUniversitas MyDatabase;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList NamaList;
    private ArrayList AkreditasList;
    private ArrayList KodeList;

    SliderView sliderMyuniv;
    TextView greetText;
    ImageView img;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utama);
        getSupportActionBar().setTitle("Daftar Universitas ");
        NamaList = new ArrayList<>();
        AkreditasList = new ArrayList<>();
        KodeList = new ArrayList<>();
        MyDatabase = new DBUniversitas(getBaseContext());

        sliderMyuniv = findViewById(R.id.imageSlider);
        greetText = findViewById(R.id.greeting_text);

        recyclerView = findViewById(R.id.rv_univ);

//        recyclerView = findViewById(R.id.recycler);
        getData();
        layoutManager = new LinearLayoutManager(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                ViewData.this, LinearLayoutManager.HORIZONTAL, false
        );
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        adapter = new RecyclerViewAdapter(NamaList, AkreditasList, KodeList);
        recyclerView.setAdapter(adapter);

        final ImgAdapter sliderImgAdapter = new ImgAdapter(this);
        sliderImgAdapter.setCount(4);
        sliderMyuniv.setSliderAdapter(sliderImgAdapter);
        sliderMyuniv.setIndicatorAnimation(IndicatorAnimations.WORM);
        sliderMyuniv.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderMyuniv.setIndicatorSelectedColor(Color.WHITE);
        sliderMyuniv.setIndicatorUnselectedColor(Color.GRAY);
        sliderMyuniv.startAutoCycle();

        sliderMyuniv.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int position) {
                sliderMyuniv.setCurrentPagePosition(position);
            }
        });

        greeting();

//        DividerItemDecoration itemDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
//        itemDecoration.setDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.line));
//        recyclerView.addItemDecoration(itemDecoration);
    }

    private void greeting() {
        Calendar calendar = Calendar.getInstance();
        int timeOfDay = calendar.get(Calendar.HOUR_OF_DAY);

        if (timeOfDay >= 0 && timeOfDay < 12) {
            greetText.setText(getString(R.string.salam_pagi));
        } else if (timeOfDay >= 12 && timeOfDay < 15) {
            greetText.setText(getString(R.string.salam_siang));
        } else if (timeOfDay >= 15 && timeOfDay < 18) {
            greetText.setText(getString(R.string.salam_sore));
        } else if (timeOfDay >= 18 && timeOfDay < 24) {
            greetText.setText(getString(R.string.salam_malam));
        }
    }

    @SuppressLint("Recycler")
    protected void getData(){
        SQLiteDatabase ReadData = MyDatabase.getReadableDatabase();
        Cursor cursor = ReadData.rawQuery("SELECT * FROM "+ DBUniversitas.MyColumns.NamaTabel,null);

        cursor.moveToFirst();

        for (int count = 0; count < cursor.getCount(); count++) {
            cursor.moveToPosition(count);
            KodeList.add(cursor.getString(0));
            NamaList.add(cursor.getString(1));
            AkreditasList.add(cursor.getString(2));
        }
    }

}

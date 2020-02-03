package com.example.yangbaru;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DataKampuss extends AppCompatActivity {
    TextView c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_kampus);

        c = (TextView)findViewById(R.id.textView1);

        Intent i = getIntent();
        String a = i.getStringExtra("id");

        c.setText(a);

    }
}
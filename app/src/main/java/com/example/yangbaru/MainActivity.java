package com.example.yangbaru;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.yangbaru.database.DBUniversitas;

public class MainActivity extends AppCompatActivity {

    private EditText Kode, Nama, TanggalLahir, Alamat;
    private Spinner Akreditas, Status;
    private RadioButton NEGERI, SWASTA;

    //bariabel menyiman input dari user
    private String setKode, setNama, setTanggalLahir, setAlamat, setAkreditas, setStatus, setJenisKampus;
    //variabel untuk inisialisasi database
    private DBUniversitas dbUniversitas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button simpan = findViewById(R.id.save);
        Kode = findViewById(R.id.kode);
        Nama = findViewById(R.id.nama);
        TanggalLahir = findViewById(R.id.date);
        NEGERI = findViewById(R.id.negeri);
        SWASTA =  findViewById(R.id.belumnegeri);
        Akreditas = findViewById(R.id.akred);
        Status = findViewById(R.id.d_stat);
        Alamat = findViewById(R.id.alamat);

        Button lihat = findViewById(R.id.lihat);

        lihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pin = new Intent(MainActivity.this, ViewData.class);
                startActivity(pin);
            }
        });

        //Inisialisasi dan Mendapatkan Konteks dari DBUniversitas

        dbUniversitas = new DBUniversitas(getBaseContext());

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setData();
                saveData();
                Toast.makeText(getApplicationContext(), "data pada database "
                        + dbUniversitas.getDatabaseName()
                        + " berhasil Disimpan", Toast.LENGTH_SHORT) .show();
                clearData();
            }
        });

//        DBUniversitas dbUniversitas = new DBUniversitas(getBaseContext());
//        Toast.makeText(getApplication(), "Nama Database yang Dibuat : "
//                + dbUniversitas.getDatabaseName() + "Beserta Tabelnya", Toast.LENGTH_SHORT) .show();

//        Button tombolPindah = findViewById(R.id.pindah);

//        tombolPindah.setOnClickListener(new view.onClickListener() {
//            @Override
//            public void onCl
//        });
    }
//statement untuk mendapatkan data
    private void setData() {
        setKode = Kode.getText().toString();
        setNama = Nama.getText().toString();
        setAkreditas = Akreditas.getSelectedItem().toString();
        setStatus = Status.getSelectedItem().toString();
        if(NEGERI.isChecked()){
            setJenisKampus = NEGERI.getText().toString();
        }else if (SWASTA.isChecked()) {
            setJenisKampus = SWASTA.getText().toString();
        }
        setTanggalLahir = TanggalLahir.getText().toString();
        setAlamat = Alamat.getText().toString();
    }
//    private ContentValues values;
//statemanet untuk mnyimpan data pada database
    private void saveData(){
        SQLiteDatabase create = dbUniversitas.getReadableDatabase();
        //membuat mamp baru yang berisi nama kolom dan data yang ingin di masukan
      ContentValues values = new ContentValues();
      values.put(DBUniversitas.MyColumns.Kode, setKode);
      values.put(DBUniversitas.MyColumns.Nama, setNama);
      values.put(DBUniversitas.MyColumns.Akreditas, setAkreditas);
      values.put(DBUniversitas.MyColumns.Status, setStatus);
      values.put(DBUniversitas.MyColumns.Jenis, setJenisKampus);
      values.put(DBUniversitas.MyColumns.TanggalLahir, setTanggalLahir);
      values.put(DBUniversitas.MyColumns.Alamat, setAlamat);
      //Menambahkan Baris Baru, Berupa Data yang diinputkan
        create.insert(DBUniversitas.MyColumns.NamaTabel, null, values);
    }
    private void clearData(){
        Kode.setText("");
        Nama.setText("");
        TanggalLahir.setText("");
        Alamat.setText("");
    }
}

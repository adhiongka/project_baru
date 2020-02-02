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

import com.example.yangbaru.database.DBMahasiswa;

public class MainActivity extends AppCompatActivity {

    private EditText NIM, Nama, TanggalLahir, Alamat;
    private Spinner Jurusan;
    private RadioButton MALE, FEMALE;

    //bariabel menyiman input dari user
    private String setNIM, setNama, setTanggalLahir, setAlamat, setJurusan, setJenisKelamin;
    //variabel untuk inisialisasi database
    private DBMahasiswa dbMahasiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button simpan = findViewById(R.id.save);
        NIM = findViewById(R.id.nim);
        Nama = findViewById(R.id.nama);
        TanggalLahir = findViewById(R.id.date);
        MALE = findViewById(R.id.male);
        FEMALE =  findViewById(R.id.female);
        Jurusan = findViewById(R.id.jurusan);
        Alamat = findViewById(R.id.alamat);

        Button lihat = findViewById(R.id.lihat);

        lihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pin = new Intent(MainActivity.this, RecyclerViewAdapter.class);
                startActivity(pin);
            }
        });

        //Inisialisasi dan Mendapatkan Konteks dari DBMahasiswa

        dbMahasiswa = new DBMahasiswa(getBaseContext());

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setData();
                saveData();
                Toast.makeText(getApplicationContext(), "data pada database"
                        + dbMahasiswa.getDatabaseName()
                        + "berhasil Disimpan", Toast.LENGTH_SHORT) .show();
                clearData();
            }
        });

//        DBMahasiswa dbMahasiswa = new DBMahasiswa(getBaseContext());
//        Toast.makeText(getApplication(), "Nama Database yang Dibuat : "
//                + dbMahasiswa.getDatabaseName() + "Beserta Tabelnya", Toast.LENGTH_SHORT) .show();

//        Button tombolPindah = findViewById(R.id.pindah);

//        tombolPindah.setOnClickListener(new view.onClickListener() {
//            @Override
//            public void onCl
//        });
    }
//statement untuk mendapatkan data
    private void setData() {
        setNIM = NIM.getText().toString();
        setNama = Nama.getText().toString();
        setJurusan = Jurusan.getSelectedItem().toString();
        if(MALE.isChecked()){
            setJenisKelamin = MALE.getText().toString();
        }else if (FEMALE.isChecked()) {
            setJenisKelamin = FEMALE.getText().toString();
        }
        setTanggalLahir = TanggalLahir.getText().toString();
        setAlamat = Alamat.getText().toString();
    }
//    private ContentValues values;
//statemanet untuk mnyimpan data pada database
    private void saveData(){
        SQLiteDatabase create = dbMahasiswa.getReadableDatabase();
        //membuat mamp baru yang berisi nama kolom dan data yang ingin di masukan
      ContentValues values = new ContentValues();
      values.put(DBMahasiswa.MyColumns.NIM, setNIM);
      values.put(DBMahasiswa.MyColumns.Nama, setNama);
      values.put(DBMahasiswa.MyColumns.Jurusan, setJurusan);
      values.put(DBMahasiswa.MyColumns.JenisKelamin, setJenisKelamin);
      values.put(DBMahasiswa.MyColumns.TanggalLahir, setTanggalLahir);
      values.put(DBMahasiswa.MyColumns.Alamat, setAlamat);
      //Menambahkan Baris Baru, Berupa Data yang diinputkan
        create.insert(DBMahasiswa.MyColumns.NamaTabel, null, values);
    }
    private void clearData(){
        NIM.setText("");
        Nama.setText("");
        TanggalLahir.setText("");
        Alamat.setText("");
    }
}

package com.example.yangbaru;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList namaList;
    private ArrayList jurusanList;
    private ArrayList nimList;

    RecyclerViewAdapter(ArrayList namaList, ArrayList jurusanList, ArrayList nimList){

        this.namaList = namaList;
        this.jurusanList = jurusanList;
        this.nimList = nimList;
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView Nama, Jurusan, Nim;
        ViewHolder(View itemView) {
            super(itemView);
            Nama = itemView.findViewById(R.id.nama);
            Jurusan = itemView.findViewById(R.id.jurusan);
            Nim = itemView.findViewById(R.id.nim);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View V = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_design, parent, false);
        return new ViewHolder(V);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") final int position){
        final String Nama = (String) namaList.get(position);
        final String Jurusan = (String) jurusanList.get(position);
        final String Nim = (String) nimList.get(position);

        holder.Nama.setText(Nama);
        holder.Jurusan.setText(Jurusan);
        holder.Nim.setText(Nim);
    }

    @Override
    public int getItemCount() {
        return nimList.size();
    }

}

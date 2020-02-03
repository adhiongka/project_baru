package com.example.yangbaru;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList namaList;
    private ArrayList akreditasList;
    private ArrayList kodeList;

    RecyclerViewAdapter(ArrayList namaList, ArrayList akreditasList, ArrayList kodeList){

        this.namaList = namaList;
        this.akreditasList = akreditasList;
        this.kodeList = kodeList;
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        private CardView cvItem;
        private TextView Nama, Akreditas, Kode;
//        ImageView ivlogo;
        ViewHolder(View itemView) {
            super(itemView);
           cvItem = itemView.findViewById(R.id.cv_item);
            Nama = itemView.findViewById(R.id.nama_kampus);
            Akreditas = itemView.findViewById(R.id.akred);
            Kode = itemView.findViewById(R.id.status);
//            ivlogo = itemView.findViewById(R.id.logo);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View V = LayoutInflater.from(parent.getContext()).inflate(R.layout.daftar_kampus, parent, false);
        return new ViewHolder(V);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") final int position){
        final String Nama = (String) namaList.get(position);
        final String Akreditas = (String) akreditasList.get(position);
        final String Kode = (String) kodeList.get(position);

        holder.Nama.setText(Nama);
        holder.Akreditas.setText(Akreditas);
        holder.Kode.setText(Kode);
    }

    @Override
    public int getItemCount() {
        return kodeList.size();
    }

}

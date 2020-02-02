package com.example.yangbaru;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        private TextView Nama, Akreditas, Kode;
        ViewHolder(View itemView) {
            super(itemView);
            Nama = itemView.findViewById(R.id.nama);
            Akreditas = itemView.findViewById(R.id.akreditas);
            Kode = itemView.findViewById(R.id.kode);
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

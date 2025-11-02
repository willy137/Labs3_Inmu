package com.inmueble.tpinmueblelab3.ui.inmueble;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;


import com.inmueble.tpinmueblelab3.R;
import com.inmueble.tpinmueblelab3.ui.modelos.Inmueble;

import java.util.List;

public class InmuebleAdapter extends RecyclerView.Adapter<InmuebleAdapter.InmuebleViewHolder> {

     private List<Inmueble> inmus;

     private Context context;

    public InmuebleAdapter(List<Inmueble> inmus,Context context) {
        this.inmus = inmus;
        this.context=context;
    }

    @NonNull
    @Override
    public InmuebleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.inmueble_card,parent,false);
        return new InmuebleViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull InmuebleAdapter.InmuebleViewHolder holder, int position) {
        String url="https://inmobiliariaulp-amb5hwfqaraweyga.canadacentral-01.azurewebsites.net/";
        Inmueble inmu=inmus.get(position);
        holder.dire.setText(inmu.getDireccion());
        holder.tipo.setText(inmu.getTipo());
        holder.precio.setText(String.valueOf(inmu.getValor()));
        Glide.with(context)
                .load(url+inmu.getImagen())
                .placeholder(R.drawable.casa)
                .error(R.drawable.inmo)
                .into(holder.img);
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle=new Bundle();
                bundle.putSerializable("inmueble",inmu);
                Navigation.findNavController((Activity) view.getContext(),R.id.nav_host_fragment_content_main)
                        .navigate(R.id.nav_detinmu,bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return inmus.size();
    }
    public class InmuebleViewHolder extends RecyclerView.ViewHolder {
        private TextView dire;
        private TextView tipo;
        private TextView precio;
        private ImageView img;
        private CardView cv;

        public InmuebleViewHolder(@NonNull View itemView) {
            super(itemView);
            this.dire = itemView.findViewById(R.id.tvDire);
            this.tipo = itemView.findViewById(R.id.tvTipo);
            this.precio = itemView.findViewById(R.id.tvPrecio);
            this.img = itemView.findViewById(R.id.ivFotoInmu);
            this.cv=itemView.findViewById(R.id.cvCard);
        }

    }
}

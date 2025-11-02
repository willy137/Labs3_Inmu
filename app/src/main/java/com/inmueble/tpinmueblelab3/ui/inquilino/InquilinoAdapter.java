package com.inmueble.tpinmueblelab3.ui.inquilino;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.inmueble.tpinmueblelab3.R;
import com.inmueble.tpinmueblelab3.ui.modelos.Inmueble;

import java.util.List;

public class InquilinoAdapter extends RecyclerView.Adapter<InquilinoAdapter.InquilinoViewHolder> {

    private List<Inmueble> inmus;

    private Context context;

    public InquilinoAdapter(List<Inmueble> inmus,Context context){
        this.inmus = inmus;
        this.context=context;
    }

    @NonNull
    @Override
    public InquilinoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.contrato_card,parent,false);
        return new InquilinoViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull InquilinoAdapter.InquilinoViewHolder holder, int position) {
        String url="https://inmobiliariaulp-amb5hwfqaraweyga.canadacentral-01.azurewebsites.net/";
        Inmueble inmu=inmus.get(position);
        holder.dire.setText(inmu.getDireccion());
        Glide.with(context)
                .load(url+inmu.getImagen())
                .placeholder(R.drawable.casa)
                .error(R.drawable.inmo)
                .into(holder.img);
        holder.ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle=new Bundle();
                bundle.putInt("inmueble",inmu.getIdInmueble());
                Navigation.findNavController((Activity) view.getContext(),R.id.nav_host_fragment_content_main)
                        .navigate(R.id.nav_detalleinquilino,bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return inmus.size();
    }

    public class InquilinoViewHolder extends RecyclerView.ViewHolder {
        private TextView dire;
        private ImageView img;
        private Button ver;
        public InquilinoViewHolder(@NonNull View itemView) {
            super(itemView);
            this.dire = itemView.findViewById(R.id.tvDireccion);
            this.img = itemView.findViewById(R.id.ivFotoI);
            this.ver=itemView.findViewById(R.id.btVer);
        }

    }

}

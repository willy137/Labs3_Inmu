package com.inmueble.tpinmueblelab3.ui.pagos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.inmueble.tpinmueblelab3.R;
import com.inmueble.tpinmueblelab3.ui.modelos.Pago;

import java.util.List;

public class PagosAdapter extends RecyclerView.Adapter<PagosAdapter.PagosViewHolder> {

    private List<Pago> pagos;

    private Context context;

    public PagosAdapter(List<Pago> pago, Context context) {
        this.pagos = pago;
        this.context=context;
    }

    @NonNull
    @Override
    public PagosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.detallepago_card,parent,false);
        return new PagosViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull PagosAdapter.PagosViewHolder holder, int position) {
        Pago pago=pagos.get(position);
        holder.codigo.setText(pago.getIdPago()+"");
        holder.numPago.setText((position+1)+"");
        holder.codContrato.setText(pago.getIdContrato()+"");
        holder.importe.setText(pago.getMonto()+"");
        holder.fechaPago.setText(pago.getFechaPago());
    }

    @Override
    public int getItemCount() {
        return pagos.size();
    }

    public class PagosViewHolder extends RecyclerView.ViewHolder {
        private TextView codigo;
        private TextView numPago;
        private TextView codContrato;
        private TextView importe;
        private TextView fechaPago;
        public PagosViewHolder(@NonNull View itemView) {
            super(itemView);
            this.codigo=itemView.findViewById(R.id.tvCodigopago);
            this.numPago=itemView.findViewById(R.id.tvNumPago);
            this.codContrato=itemView.findViewById(R.id.tvCodContrato);
            this.importe=itemView.findViewById(R.id.tvImportePago);
            this.fechaPago=itemView.findViewById(R.id.tvFechaPago);
        }

    }


}

package com.gokay.bitirmeprojesi.messaging;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gokay.bitirmeprojesi.R;
import com.gokay.bitirmeprojesi.duyuru.Veli;

import org.w3c.dom.Text;

import java.util.List;

public class VeliAdapter extends RecyclerView.Adapter<VeliAdapter.VeliViewHolder> {

    private Context mCtx;
    private List<Veli> veliList;

    public VeliAdapter(Context mCtx,List<Veli> veliList){
        this.mCtx=mCtx;
        this.veliList=veliList;
    }

    @Override
    public VeliViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mCtx);
        View view=inflater.inflate(R.layout.single_veli_layout,null);
        VeliViewHolder vHolder=new VeliViewHolder(view);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(VeliViewHolder holder, int position) {
        Veli veli=veliList.get(position);

        holder.veliAdi.setText(veli.getVeli_adi());
        holder.veliEmail.setText(veli.getVeli_email());
    }

    @Override
    public int getItemCount() {
        return veliList.size();
    }

    class VeliViewHolder extends RecyclerView.ViewHolder{


        TextView veliAdi,veliEmail;

        public VeliViewHolder(View itemView) {
            super(itemView);

            veliEmail=itemView.findViewById(R.id.veli_email);
            veliAdi=itemView.findViewById(R.id.veli_name);
        }
    }
}

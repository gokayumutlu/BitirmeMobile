package com.gokay.bitirmeprojesi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gokay.bitirmeprojesi.m.Ilac;

import java.util.ArrayList;

public class ListAdapt extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private ArrayList<Ilac> alist=new ArrayList<Ilac>();

    public ListAdapt(Context context){
        mContext=context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return alist.size();
    }

    @Override
    public Object getItem(int i) {
        return alist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        RelativeLayout itemView;
        if (view == null) {
            itemView = (RelativeLayout) mLayoutInflater.inflate(R.layout.ilac_list, viewGroup, false);

        } else {
            itemView = (RelativeLayout) view;
        }

        TextView ilacAditv=itemView.findViewById(R.id.listIlacAdi);
        TextView ilacDoztv=itemView.findViewById(R.id.listIlacDoz);
        TextView ilacDesctv=itemView.findViewById(R.id.listIlacDesc);

        String ilacadi=alist.get(i).getIlacAdi();
        ilacAditv.setText(ilacadi);
        String ilacDoz=alist.get(i).getDoz();
        ilacDoztv.setText(ilacDoz);
        String ilacDesc=alist.get(i).getDesc();
        ilacDesctv.setText(ilacDesc);
        return itemView;
    }

    public void update(ArrayList<Ilac> ilaclist){
        alist=ilaclist;
    }
}

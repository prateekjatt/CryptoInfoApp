package com.quantastudio.cryptoinfo;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private final JSONObject[] localdataset;
    private final String currency;

    public CustomAdapter(JSONObject[] localdataset,String currency) {
        this.localdataset = localdataset;
        this.currency = currency;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dynamiclistlayout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            holder.getname().setText(localdataset[position].getString("name"));
            holder.getrank().setText(localdataset[position].getString("market_cap_rank"));
            holder.getprice().setText(localdataset[position].getString("current_price")+" "+currency);
            if(localdataset[position].getString("price_change_percentage_24h").startsWith("-"))
            {
                holder.getchange().setText(localdataset[position]
                        .getString("price_change_percentage_24h"));
                holder.getchange().setTextColor(Color.parseColor("#FFE91E1E"));
            }
            else {
                holder.getchange().setText(String.valueOf("+"+localdataset[position]
                        .getString("price_change_percentage_24h")));
                holder.getchange().setTextColor(Color.parseColor("#FF23C32A"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return localdataset.length;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textView_name;
        private TextView textView_rank;
        private TextView textView_price;
        private TextView textView_change;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_name = itemView.findViewById(R.id.textview_name);
            textView_rank = itemView.findViewById(R.id.textview_rank);
            textView_price = itemView.findViewById(R.id.textview_price);
            textView_change = itemView.findViewById(R.id.textview_change);
        }
        public TextView getname(){ return textView_name; }
        public TextView getrank(){ return textView_rank; }
        public TextView getprice(){ return textView_price; }
        public TextView getchange(){ return textView_change; }
    }
}

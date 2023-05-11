package com.example.apphai.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apphai.IClickMonAnHome;
import com.example.apphai.R;
import com.example.apphai.model.MonAn;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private  Context mContext;
    private  List<MonAn> mMonAn;
    private int row_index = -1;
    private IClickMonAnHome iClickMonAnHome;
    public void setData(List<MonAn> list){
        this.mMonAn= list;
        notifyDataSetChanged();
    }
    public HomeAdapter(Context mContext,IClickMonAnHome iClickMonAnHome) {
        this.mContext = mContext;
        this.iClickMonAnHome=iClickMonAnHome;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgMonAn;
        private TextView tvtName,tvtPrice;
        private LinearLayout lnLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgMonAn= itemView.findViewById(R.id.img_rcv_trangchu);
            tvtName= itemView.findViewById(R.id.tvt_rcv_trangchu);
            tvtPrice= itemView.findViewById(R.id.tvt_price);
            lnLayout = itemView.findViewById(R.id.ln_itemhome);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View heroView = inflater.inflate(R.layout.item_trangchu, parent, false);
        return new ViewHolder(heroView);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.ViewHolder holder, int position) {
        MonAn monAn = mMonAn.get(position);
        if (monAn==null){
            return;
        }
        switch (monAn.getResourceId()){

            case 1:
                holder.imgMonAn.setImageResource(R.drawable.slide1);
                break;
            case 2:
                holder.imgMonAn.setImageResource(R.drawable.slide4);
                break;
            case 3:
                holder.imgMonAn.setImageResource(R.drawable.slide3);
                break;
            case 4:
                holder.imgMonAn.setImageResource(R.drawable.slide2);
                break;
            case 5:
                holder.imgMonAn.setImageResource(R.drawable.ga_rang_muoi);
                break;

        }
        holder.tvtName.setText(monAn.getName());
        holder.tvtPrice.setText(monAn.getPrice()+"VNĐ");
        holder.lnLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickMonAnHome.OnClickMonAnHome(monAn);
            }
        });

    }

    @Override
    public int getItemCount() {
        if(mMonAn!=null) {
            return mMonAn.size();
        }
        return 0;
    }
}

package com.example.apphai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apphai.R;
import com.example.apphai.model.MonAn;

import java.util.List;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.ViewHolder> {
private Context mContext;
private List<MonAn> mMonAn;
private int row_index = -1;

public void setData(List<MonAn> list){
        this.mMonAn= list;
        notifyDataSetChanged();
        }
public GioHangAdapter(Context mContext) {
        this.mContext = mContext;


        }

public static class ViewHolder extends RecyclerView.ViewHolder {
    private ImageView imgMonAn;
    private TextView tvtName,tvtPrice;


    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        imgMonAn= itemView.findViewById(R.id.img_rcv_trangchu);
        tvtName= itemView.findViewById(R.id.tvt_rcv_trangchu);
        tvtPrice= itemView.findViewById(R.id.tvt_price);

    }
}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View heroView = inflater.inflate(R.layout.item_gio_hang, parent, false);
        return new ViewHolder(heroView);
    }


    @Override
    public void onBindViewHolder(@NonNull GioHangAdapter.ViewHolder holder, int position) {
        MonAn monAn = mMonAn.get(position);
        if (monAn==null){
            return;
        }
        holder.imgMonAn.setImageResource(monAn.getResourceId());
        holder.tvtName.setText(monAn.getName());
        holder.tvtPrice.setText(monAn.getPrice()+"VNĐ");

    }

    @Override
    public int getItemCount() {
        if(mMonAn!=null) {
            return mMonAn.size();
        }
        return 0;
    }
}

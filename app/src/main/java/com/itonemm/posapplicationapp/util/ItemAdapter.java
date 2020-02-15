package com.itonemm.posapplicationapp.util;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.itonemm.posapplicationapp.DAO.CategoryDAO;
import com.itonemm.posapplicationapp.DAO.ColorDAO;
import com.itonemm.posapplicationapp.DAO.ItemModel;
import com.itonemm.posapplicationapp.Model.ColorModel;
import com.itonemm.posapplicationapp.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.core.graphics.ColorUtils;
import androidx.recyclerview.widget.RecyclerView;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemHolder> {
    ArrayList<ItemModel> itemModels=new ArrayList<ItemModel>();
    Context context;

    public ItemAdapter(ArrayList<ItemModel> itemModels, Context context) {
        this.itemModels = itemModels;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.itemdata,parent,false);

        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        holder.sr.setText(itemModels.get(position).Id+".");
        holder.itemname.setText(itemModels.get(position).Name);
        ColorDAO dao=new ColorDAO(context);
        final ColorModel temp=dao.getModelById(itemModels.get(position).ColorId);
        final int colorcode= Color.argb(255,temp.ColorRed,temp.ColorGreen,temp.ColorBlue);
        holder.coloritem.setBackgroundColor(colorcode);
        CategoryDAO categoryDAO=new CategoryDAO(context);
        holder.catgoryname.setText(categoryDAO.getModelById(itemModels.get(position).CategoryId).Name);



    }

    @Override
    public int getItemCount() {
        return itemModels.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        TextView sr,itemname,catgoryname,coloritem;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            sr=itemView.findViewById(R.id.sr);
            itemname = itemView.findViewById(R.id.itemname);
            catgoryname=itemView.findViewById(R.id.categoryname);
            coloritem=itemView.findViewById(R.id.coloritem);
        }
    }
}

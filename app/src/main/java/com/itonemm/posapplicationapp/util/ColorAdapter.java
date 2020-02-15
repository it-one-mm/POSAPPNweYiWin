package com.itonemm.posapplicationapp.util;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.itonemm.posapplicationapp.BrandFragment;
import com.itonemm.posapplicationapp.CategoryFragment;
import com.itonemm.posapplicationapp.ColorFragment;
import com.itonemm.posapplicationapp.ItemFragment;
import com.itonemm.posapplicationapp.Model.ColorModel;
import com.itonemm.posapplicationapp.R;
import com.itonemm.posapplicationapp.UnitFragment;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ColorAdapter  extends RecyclerView.Adapter<ColorAdapter.ColorHolder> {

    ArrayList<ColorModel> colorModels=new ArrayList<ColorModel>();

    String fragname;
    Context context;

    public ColorAdapter(ArrayList<ColorModel> colorModels, String frgname, Context context) {
        this.colorModels = colorModels;
        this.fragname=frgname;
        this.context=context;
    }

    @NonNull
    @Override
    public ColorHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.coloritem,parent,false);
        return new ColorHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ColorHolder holder, final int position) {

        final ColorModel temp=colorModels.get(position);
        final int colorcode= Color.argb(255,temp.ColorRed,temp.ColorGreen,temp.ColorBlue);
        holder.coloritem.setBackgroundColor(colorcode);
        holder.coloritem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if(fragname.equals(context.getResources().getString(R.string.category_frag))){
                  CategoryFragment.txtcolor.setBackgroundColor(colorcode);
                  CategoryFragment.colorid=temp.ColorId;
              }
                if(fragname.equals(context.getResources().getString(R.string.brand_frag))){
                    BrandFragment.txtcolor.setBackgroundColor(colorcode);
                    BrandFragment.colorid=temp.ColorId;
                }
                if(fragname.equals(context.getResources().getString(R.string.unit_frag))){
                    UnitFragment.txtcolor.setBackgroundColor(colorcode);
                    UnitFragment.colorid=temp.ColorId;
                }
                if(fragname.equals(context.getResources().getString(R.string.item_frag))){

                    ItemFragment.selectedColorId=temp.ColorId;
                    holder.checkcolor.setVisibility(View.VISIBLE);
                    ColorFragment.removeCheck(position);

                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return colorModels.size();
    }

    public class ColorHolder extends RecyclerView.ViewHolder{
        TextView coloritem;
        ImageView checkcolor;

        public ColorHolder(@NonNull View itemView) {
            super(itemView);
            coloritem=itemView.findViewById(R.id.coloritem);
            checkcolor=itemView.findViewById(R.id.checkcolor);
        }
    }
}

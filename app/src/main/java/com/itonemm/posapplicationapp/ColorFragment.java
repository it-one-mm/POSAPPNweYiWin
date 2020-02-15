package com.itonemm.posapplicationapp;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itonemm.posapplicationapp.DAO.ColorDAO;
import com.itonemm.posapplicationapp.util.ColorAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class ColorFragment extends Fragment {


    public ColorFragment() {
        // Required empty public constructor
    }


    static RecyclerView rcColorData;
    static GridLayoutManager gm;
    static ColorAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_color, container, false);
        rcColorData=view.findViewById(R.id.colordata);
        loadColorData();
        return view;
        }

    public void loadColorData()
    {
        ColorDAO colorDAO=new ColorDAO(getContext());
         adapter=new ColorAdapter(colorDAO.getModels(),getResources().getString(R.string.item_frag),getContext());
        rcColorData.setAdapter(adapter);
         gm=new GridLayoutManager(getContext(),3);
        rcColorData.setLayoutManager(gm);

    }

    public static void removeCheck(int currentpos){

        for(int i=0;i<adapter.getItemCount();i++)
        {
            if(currentpos!=i)
            {
                View view=gm.findViewByPosition(i);
                view.findViewById(R.id.checkcolor).setVisibility(View.GONE);
            }
        }
    }

}

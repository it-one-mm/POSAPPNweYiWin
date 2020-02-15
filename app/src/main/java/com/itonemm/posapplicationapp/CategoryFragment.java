package com.itonemm.posapplicationapp;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.itonemm.posapplicationapp.DAO.CategoryDAO;
import com.itonemm.posapplicationapp.DAO.ColorDAO;
import com.itonemm.posapplicationapp.Model.CategoryModel;
import com.itonemm.posapplicationapp.util.ColorAdapter;
import com.itonemm.posapplicationapp.util.RecyclerAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment {


    public CategoryFragment() {
        // Required empty public constructor
    }

    RecyclerView rcView,rcColorData;
    CategoryDAO categoryDAO;
    EditText edtname;
    public static TextView txtcolor;
    public  static int colorid=1;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView= inflater.inflate(R.layout.fragment_category, container, false);
       rcView=myView.findViewById(R.id.rcview);
       rcColorData=myView.findViewById(R.id.colordata);
       txtcolor=myView.findViewById(R.id.selectedcolor);
        categoryDAO=new CategoryDAO(getContext());
        Button btnsave=myView.findViewById(R.id.saveitem);
         edtname=myView.findViewById(R.id.name);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(edtname.getText().toString().trim()))
                {
                    String name=edtname.getText().toString().trim();
                    CategoryModel model=new CategoryModel();
                    model.Name=name;
                    model.ColorId=colorid; //
                    categoryDAO.insertModel(model);
                    loadData();
                    clearData();
                    Toast.makeText(getContext(),"Save OK!",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getContext(),"Please fill",Toast.LENGTH_LONG).show();
                }
            }
        });
        loadData();
        loadColorData();
        return  myView;
    }

    public void loadData()
    {

        RecyclerAdapter adapter=new RecyclerAdapter(categoryDAO.getModels().toArray(),getResources().getString(R.string.category_frag),getContext());
        rcView.setAdapter(adapter);
        LinearLayoutManager lm=new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rcView.setLayoutManager(lm);
    }

    public void clearData()
    {
        edtname.setText("");
    }

    public void loadColorData()
    {
        ColorDAO colorDAO=new ColorDAO(getContext());
        ColorAdapter adapter=new ColorAdapter(colorDAO.getModels(),getResources().getString(R.string.category_frag),getContext());
        rcColorData.setAdapter(adapter);
        GridLayoutManager gm=new GridLayoutManager(getContext(),3);
        rcColorData.setLayoutManager(gm);

    }


}

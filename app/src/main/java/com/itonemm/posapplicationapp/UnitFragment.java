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

import com.itonemm.posapplicationapp.DAO.UnitDAO;
import com.itonemm.posapplicationapp.DAO.ColorDAO;
import com.itonemm.posapplicationapp.Model.UnitModel;
import com.itonemm.posapplicationapp.util.ColorAdapter;
import com.itonemm.posapplicationapp.util.RecyclerAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class UnitFragment extends Fragment implements View.OnClickListener{
    RecyclerView rcView,rcColorData;
    UnitDAO UnitDAO;
    EditText edtname;
    public static TextView txtcolor;
    public  static int colorid=1;
    View myView;
    Button btnsave;

    public UnitFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_unit, container, false);
        initUI();
        loadData();
        loadColorData();;

        btnsave.setOnClickListener(this);
        return myView;
    }
    public void initUI()
    {
        edtname=myView.findViewById(R.id.name);
        rcView=myView.findViewById(R.id.rcview);
        rcColorData=myView.findViewById(R.id.colordata);
        txtcolor=myView.findViewById(R.id.selectedcolor);
        UnitDAO=new UnitDAO(getContext());
        btnsave=myView.findViewById(R.id.saveitem);
    }

    public void loadData()
    {

        RecyclerAdapter adapter=new RecyclerAdapter(UnitDAO.getModels().toArray(),getResources().getString(R.string.unit_frag),getContext());
        rcView.setAdapter(adapter);
        LinearLayoutManager lm=new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false);
        rcView.setLayoutManager(lm);
    }

    public void clearData()
    {
        edtname.setText("");
    }

    public void loadColorData()
    {
        ColorDAO colorDAO=new ColorDAO(getContext());
        ColorAdapter adapter=new ColorAdapter(colorDAO.getModels(),getResources().getString(R.string.unit_frag),getContext());
        rcColorData.setAdapter(adapter);
        GridLayoutManager gm=new GridLayoutManager(getContext(),3);
        rcColorData.setLayoutManager(gm);

    }

    @Override
    public void onClick(View v) {
        if(!TextUtils.isEmpty(edtname.getText().toString().trim()))
        {
            String name=edtname.getText().toString().trim();
            UnitModel model=new UnitModel();
            model.Name=name;
            model.ColorId=colorid; //
            UnitDAO.insertModel(model);
            loadData();
            clearData();
            Toast.makeText(getContext(),"Save OK!",Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(getContext(),"Please fill",Toast.LENGTH_LONG).show();
        }
    }
}

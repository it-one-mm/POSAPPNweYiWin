package com.itonemm.posapplicationapp;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.itonemm.posapplicationapp.DAO.BrandDAO;
import com.itonemm.posapplicationapp.DAO.CategoryDAO;
import com.itonemm.posapplicationapp.DAO.ItemDAO;
import com.itonemm.posapplicationapp.DAO.ItemModel;
import com.itonemm.posapplicationapp.DAO.UnitDAO;
import com.itonemm.posapplicationapp.Model.BrandModel;
import com.itonemm.posapplicationapp.Model.CategoryModel;
import com.itonemm.posapplicationapp.Model.UnitModel;
import com.itonemm.posapplicationapp.util.ItemAdapter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ItemFragment extends Fragment implements View.OnClickListener {


    public ItemFragment() {
        // Required empty public constructor
    }

    public static int selectedColorId=1;
    View view;
    Button btnsave,btncancel;
    EditText edtname,edtoriginalprice,edtsaleprice;
    RadioButton rbtusecolor,rbtuseimage;
    Spinner spcategory,spbrand,spunit;
    CategoryDAO categoryDAO;
    BrandDAO brandDAO;
    UnitDAO unitDAO;
    ItemDAO itemDAO;
    ArrayList<CategoryModel> categoryModels=new ArrayList<>();
    ArrayList<BrandModel> brandModels=new ArrayList<BrandModel>();
    ArrayList<UnitModel> unitModels=new ArrayList<UnitModel>();
    RecyclerView rcView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
    view=inflater.inflate(R.layout.fragment_item, container, false);
    initUI();
    loadSpinnerData();
    loadData();
    btnsave.setOnClickListener(this);
    rbtusecolor.setOnClickListener(this);
    rbtuseimage.setOnClickListener(this);
    return  view;
    }

    public  void initUI()
    {
        edtname=view.findViewById(R.id.name);
        edtoriginalprice=view.findViewById(R.id.oprice);
        edtsaleprice=view.findViewById(R.id.sprice);
        btnsave=view.findViewById(R.id.saveitem);
        btncancel=view.findViewById(R.id.cancel);
        rbtusecolor=view.findViewById(R.id.rbtusecolor);;
        rbtuseimage=view.findViewById(R.id.rbtuseimage);
        spcategory=view.findViewById(R.id.category);
        spbrand=view.findViewById(R.id.spbrand);
        spunit=view.findViewById(R.id.unit);
        rcView=view.findViewById(R.id.itemlist);
        categoryDAO=new CategoryDAO(getContext());
        unitDAO=new UnitDAO(getContext());
        brandDAO=new BrandDAO(getContext());
        itemDAO=new ItemDAO(getContext());
        rbtusecolor.setChecked(true);

        setFragment(new ColorFragment());

    }

    public void loadSpinnerData()
    {
        categoryModels=categoryDAO.getModels();
        ArrayList<String> catnames=new ArrayList<String>();
        for(int i=0;i<categoryModels.size();i++)
        {

            catnames.add(categoryModels.get(i).Name);
        }
        ArrayAdapter<String> catad=new ArrayAdapter<>(getContext(),android.R.layout.simple_dropdown_item_1line,catnames);
        spcategory.setAdapter(catad);
        brandModels=brandDAO.getModels();
        ArrayList<String> brandnames=new ArrayList<String>();
       for(int i=0;i<brandModels.size();i++)
       {
           brandnames.add(brandModels.get(i).Name);
       }
       ArrayAdapter<String> brandad=new ArrayAdapter<>(getContext(),android.R.layout.simple_dropdown_item_1line,brandnames);

        spbrand.setAdapter(brandad);
       unitModels=unitDAO.getModels();
        ArrayList<String> untnames=new ArrayList<String>();
        for(int i=0;i<unitModels.size();i++)
        {
           untnames.add(unitModels.get(i).Name);
        }
        ArrayAdapter<String> unitad=new ArrayAdapter<>(getContext(),android.R.layout.simple_dropdown_item_1line,untnames);
        spunit.setAdapter(unitad);
    }

    @Override
    public void onClick(View v) {


        Button btnselected=(Button)v;

        if(btnselected.getId()==R.id.saveitem) {
            ItemModel model = new ItemModel();
            model.Name = edtname.getText().toString().trim();
            model.BrandId = brandModels.get(spbrand.getSelectedItemPosition()).Id;
            model.CategoryId = categoryModels.get(spcategory.getSelectedItemPosition()).Id;
            model.UnitId = unitModels.get(spunit.getSelectedItemPosition()).Id;
            model.OPrice = Integer.parseInt(edtoriginalprice.getText().toString().trim());
            model.SPrice = Integer.parseInt(edtsaleprice.getText().toString().trim());
            model.ColorId = selectedColorId;
            model.PicturePath = ImageFragment.imageUri.toString();
            itemDAO.saveModel(model);
            loadData();
            Toast.makeText(getContext(), "Save OK", Toast.LENGTH_LONG).show();
            clearData();
        }
        if(btnselected.getId()==R.id.rbtusecolor);
        {
            setFragment(new ColorFragment());

        }
        if(btnselected.getId()==R.id.rbtuseimage)
        {
            setFragment(new ImageFragment());
        }
    }


    public void setFragment(Fragment f)
    {
        FragmentManager fm=getFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.displaydata,f);
        ft.commit();
    }

    public void clearData()
    {
        edtname.setText("");
        edtsaleprice.setText("");
        edtoriginalprice.setText("");
    }
    public void loadData()
    {

        ItemAdapter adapter=new ItemAdapter(itemDAO.getModels(),getContext());
        rcView.setAdapter(adapter);
        LinearLayoutManager lm=new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rcView.setLayoutManager(lm);
    }
}

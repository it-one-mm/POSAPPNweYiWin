package com.itonemm.posapplicationapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.itonemm.posapplicationapp.DAO.ColorDAO;
import com.itonemm.posapplicationapp.Model.ColorModel;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setFragment(new CategoryFragment());

    }

    public void setFragment(Fragment fragment)
    {
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.mainframe,fragment);
        ft.commit();

    }

}

package com.itonemm.posapplicationapp;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.itonemm.posapplicationapp.DAO.ColorDAO;
import com.itonemm.posapplicationapp.Model.ColorModel;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;

import java.util.Random;

public class NavActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ColorDAO colorDAO=new ColorDAO(getApplicationContext());
        if(colorDAO.getModels().size()<=0)
        {
            Random r=new Random();
            for(int i=0;i<12;i++)
            {
                ColorModel temp=new ColorModel();
                temp.ColorRed=r.nextInt(256);
                temp.ColorGreen=r.nextInt(256);
                temp.ColorBlue=r.nextInt(256);
                colorDAO.saveModel(temp);
            }
        }

        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        final NavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
       /* mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_category, R.id.nav_brand, R.id.nav_unit,
                R.id.nav_item)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);*/

       navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
           @Override
           public boolean onNavigationItemSelected(@NonNull MenuItem item) {

               if(item.getItemId()==R.id.nav_category)
               {
                   setFragment(new CategoryFragment());
               }
               if(item.getItemId()==R.id.nav_brand)
               {
                  setFragment(new BrandFragment());
               }

               if(item.getItemId()==R.id.nav_unit)
               {
                   setFragment(new UnitFragment());
               }

               if(item.getItemId()==R.id.nav_item)
               {
                   setFragment(new ItemFragment());
               }
               drawer.closeDrawer(Gravity.LEFT);
               return true;
           }
       });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav, menu);
        return true;
    }


        public void setFragment(Fragment f)
        {
            FragmentManager fm=getSupportFragmentManager();
            FragmentTransaction ft=fm.beginTransaction();
            ft.replace(R.id.nav_host_fragment,f);
            ft.commit();
        }

}

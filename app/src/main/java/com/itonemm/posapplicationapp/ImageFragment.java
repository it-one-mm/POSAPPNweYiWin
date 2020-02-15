package com.itonemm.posapplicationapp;


import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class ImageFragment extends Fragment {


    private static final int CAMER_CODE = 124;
    private static final int PERMISSION_CAMER =123;
    private static final int PERMISSION_GALLERY = 125;

    public ImageFragment() {
        // Required empty public constructor
    }

    View v;
    Button btntakepic,btnchoosepic;
    ImageView img;
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         v= inflater.inflate(R.layout.fragment_image, container, false);
        btntakepic=v.findViewById(R.id.takephoto);
        context=getContext();
        btnchoosepic=v.findViewById(R.id.choosepicture);
        img=v.findViewById(R.id.itemimage);
        btntakepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkPermissions(btntakepic.getId());
            }
        });
        btnchoosepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               checkPermissions(btnchoosepic.getId());
            }
        });
         return  v;
    }
    Context context;

    public void checkPermissions(int id)
    {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
        {
            if ( context.checkSelfPermission(Manifest.permission.CAMERA)== PackageManager.
                    PERMISSION_GRANTED
            && context.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED
            && context.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED)
            {
                if(R.id.takephoto==id) {
                    takephoto();
                }
              else
                {
                    choosepic();
                }
            }
            else
            {
                if(shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) &&
                        shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE) &&
                        shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                    Toast.makeText(getContext(),"Permission Required",Toast.LENGTH_LONG).show();
                }
                if(R.id.takephoto==id) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_CAMER);
                }
                else
                {
                    requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_GALLERY);

                }
                }
        }
    }

    private void choosepic() {
        Intent gallery=new Intent();
        gallery.setType("image/*");
        gallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(gallery,"Select Photo"),PERMISSION_GALLERY);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode==PERMISSION_CAMER)
        {
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED && grantResults[1]==PackageManager.PERMISSION_GRANTED && grantResults[2]==PackageManager.PERMISSION_GRANTED)
            {
                takephoto();
            }
        }
        if(requestCode==PERMISSION_GALLERY)
        {
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED && grantResults[1]==PackageManager.PERMISSION_GRANTED && grantResults[2]==PackageManager.PERMISSION_GRANTED)
            {
               choosepic();
            }
        }

    }

    public  static Uri imageUri;
    private void takephoto() {
        ContentValues values=new ContentValues();
        Date date=new Date();
        SimpleDateFormat formatter=new SimpleDateFormat("ddMMyyyyhhmmss");
        values.put(MediaStore.Images.Media.TITLE,formatter.format(date));
        values.put(MediaStore.Video.Media.DESCRIPTION,context.getResources().getString(R.string.app_name));
        imageUri=context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
        startActivityForResult(intent,CAMER_CODE);
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==CAMER_CODE)
        {
            {
                img.setImageURI(imageUri);
            }
        }
        if(requestCode==PERMISSION_GALLERY)
        {
            img.setImageURI(data.getData());
            imageUri=data.getData();
        }
    }
}

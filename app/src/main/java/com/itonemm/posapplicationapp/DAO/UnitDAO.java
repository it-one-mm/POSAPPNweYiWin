package com.itonemm.posapplicationapp.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.itonemm.posapplicationapp.Model.UnitModel;

import java.util.ArrayList;

public class UnitDAO {
    public static String
            TBName="Unit",
            ColName="Name",
            ColId="Id",
            ColColorId="ColorId",
            ColDisable="Disable";

    Context context;
    DBHelper dbHelper;
    SQLiteDatabase db;
    public UnitDAO(Context context) {
        this.context=context;
        dbHelper=new DBHelper(context);
        db=dbHelper.getReadableDatabase();
    }
    public ArrayList<UnitModel> getModels()
    {
        ArrayList<UnitModel> categoryModels=new ArrayList<UnitModel>();
        Cursor cursor=db.rawQuery("select * from "+TBName,null);
        while (cursor.moveToNext())
        {
            UnitModel temp=new UnitModel();
            temp.Id=cursor.getInt(cursor.getColumnIndex(ColId));
            temp.Name=cursor.getString(cursor.getColumnIndex(ColName));
            temp.ColorId=cursor.getInt(cursor.getColumnIndex(ColColorId));
            temp.Disable=cursor.getInt(cursor.getColumnIndex(ColDisable));
            categoryModels.add(temp);
        }
        return categoryModels;
    }

    public long insertModel(UnitModel model)
    {
        db=dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(ColName,model.Name);
        values.put(ColColorId,model.ColorId);
        values.put(ColDisable,0); // 0 means active
        long id=db.insert(TBName,null,values);
        return  id;
    }

}

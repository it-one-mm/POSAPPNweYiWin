package com.itonemm.posapplicationapp.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.itonemm.posapplicationapp.Model.CategoryModel;

import java.util.ArrayList;

public class CategoryDAO {
    public static String
            TBName="Category",
            ColName="Name",
            ColId="Id",
            ColColorId="ColorId",
            ColDisable="Disable";

    Context context;
    DBHelper dbHelper;
    SQLiteDatabase db;
    public CategoryDAO(Context context) {
        this.context=context;
        dbHelper=new DBHelper(context);
        db=dbHelper.getReadableDatabase();
    }
    public ArrayList<CategoryModel> getModels()
    {
        ArrayList<CategoryModel> categoryModels=new ArrayList<CategoryModel>();
        Cursor cursor=db.rawQuery("select * from "+TBName,null);
        while (cursor.moveToNext())
        {
            CategoryModel temp=new CategoryModel();
            temp.Id=cursor.getInt(cursor.getColumnIndex(ColId));
            temp.Name=cursor.getString(cursor.getColumnIndex(ColName));
            temp.ColorId=cursor.getInt(cursor.getColumnIndex(ColColorId));
            temp.Disable=cursor.getInt(cursor.getColumnIndex(ColDisable));
            categoryModels.add(temp);
        }
        return categoryModels;
    }

    public CategoryModel getModelById(int id)
    {

        Cursor cursor=db.rawQuery("select * from "+TBName+ " where "+ColId+"=?",new String[]{String.valueOf(id)});
        CategoryModel temp=new CategoryModel();
        while (cursor.moveToNext())
        {

            temp.Id=cursor.getInt(cursor.getColumnIndex(ColId));
            temp.Name=cursor.getString(cursor.getColumnIndex(ColName));
            temp.ColorId=cursor.getInt(cursor.getColumnIndex(ColColorId));
            temp.Disable=cursor.getInt(cursor.getColumnIndex(ColDisable));

        }
        return temp;
    }
    public long insertModel(CategoryModel model)
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

package com.itonemm.posapplicationapp.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;



import java.util.ArrayList;


public class ItemDAO {

    public static String ColId = "Id",
            ColName = "Name",
            ColCatId = "CategoryId",
            ColBrandId = "BrandId",
            ColUnitId = "UnitId",
            ColOPrice = "OPrice",
            ColSPrice = "SPrice",
            ColColorId = "ColorId",
            ColPicturePath = "PicturePath",
            ColDisable = "Disable",
            TBName = "Item";
    DBHelper dbHelper;
    SQLiteDatabase db;

    public ItemDAO(Context context) {
        dbHelper=new DBHelper(context);
        db=dbHelper.getReadableDatabase();
    }
    public long saveModel(ItemModel model)
    {
        db=dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(ColName,model.Name);
        values.put(ColColorId,model.ColorId);
        values.put(ColBrandId,model.BrandId);
        values.put(ColCatId,model.getCategoryId());
        values.put(ColUnitId,model.getUnitId());
        values.put(ColColorId,model.getColorId());
        values.put(ColPicturePath,model.getPicturePath());
        values.put(ColOPrice,model.getOPrice());
        values.put(ColSPrice,model.getSPrice());
        values.put(ColDisable,0);// 0 means active // 1 disable
        return db.insert(TBName,null,values);

    }

    public ArrayList<ItemModel> getModels()
    {
        db=dbHelper.getReadableDatabase();
        ArrayList<ItemModel> models=new ArrayList<ItemModel>();
        Cursor cursor=db.rawQuery("select * from "+ TBName+" ORDER BY "+ColId+" DESC ",null);
        while (cursor.moveToNext())
        {
            models.add(
                    new ItemModel(
                            cursor.getInt(cursor.getColumnIndex(ColId)),
                            cursor.getInt(cursor.getColumnIndex(ColCatId)),
                            cursor.getInt(cursor.getColumnIndex(ColBrandId)),
                            cursor.getInt(cursor.getColumnIndex(ColUnitId)),
                            cursor.getInt(cursor.getColumnIndex(ColColorId)),
                            cursor.getInt(cursor.getColumnIndex(ColDisable)),
                            cursor.getInt(cursor.getColumnIndex(ColOPrice)),
                            cursor.getInt(cursor.getColumnIndex(ColSPrice)),
                            cursor.getString(cursor.getColumnIndex(ColName)),
                                    cursor.getString(cursor.getColumnIndex(ColPicturePath))

                    ));
        }
        return  models;
    }


}



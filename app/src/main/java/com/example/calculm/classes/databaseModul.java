package com.example.calculm.classes;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class databaseModul extends SQLiteOpenHelper {

    public databaseModul(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE module(nom TEXT PRIMARY KEY ,coef INTEGER , moy REAL )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS module");
        onCreate(sqLiteDatabase);
    }

    public void insert(String n, int c, float m){
        SQLiteDatabase d = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nom",n);cv.put("coef",c);cv.put("moy",m);
        d.insert("module",null,cv);

        d.close();

    }
    @SuppressLint("Range")
    public ArrayList<String> getMod(){
        SQLiteDatabase d = this.getReadableDatabase();
        Cursor cur =d.rawQuery("SELECT nom,coef,moy FROM module ",null);
        ArrayList<String> L = new ArrayList<String>();
        cur.moveToFirst();
        while (cur.isAfterLast()==false){
            String nom;
            int coef;
            float moy;
            nom=cur.getString(cur.getColumnIndex("nom"));
            coef=cur.getInt(cur.getColumnIndex("coef"));
            moy=cur.getFloat(cur.getColumnIndex("moy"));
            L.add(String.valueOf(nom)+""+coef+""+moy);
            cur.moveToNext();
        }
        d.close();
        return L;
    }
    Cursor readdata(){
       String query ="SELECT nom,coef,moy FROM module";
        SQLiteDatabase d = this.getReadableDatabase();
        Cursor cur = null;
        if(d!= null){
           cur= d.rawQuery(query,null);

        }
        return cur;

    }
    public void deletrow(String nom){
        SQLiteDatabase d=this.getWritableDatabase();
         d.delete("module","nom=?",new String[]{ nom });

    }
    public void dlt(String nom){
        SQLiteDatabase d=this.getWritableDatabase();
        d.delete("module","nom!=?",new String[]{ nom });

    }
    void dele(String n){
        SQLiteDatabase d=this.getReadableDatabase();
        String requet=("DELETE FROM module WHERE nom="+n);
        d.rawQuery(requet,null);

    }
    void updatedata(String n, int c,float m){
        SQLiteDatabase d=this.getWritableDatabase();
        ContentValues cv= new ContentValues();
        cv.put("nom",n);cv.put("coef",c);cv.put("moy",m);
        d.update("module",cv,"nom=?",new String[]{ n });
    }


}

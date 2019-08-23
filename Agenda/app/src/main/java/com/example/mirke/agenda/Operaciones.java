package com.example.mirke.agenda;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class Operaciones {
    DataBase dataBase;
    Contacto contacto;

    public Operaciones(DataBase dataBase, Contacto contacto) {
        this.dataBase = dataBase;
        this.contacto = contacto;
    }

    public void addContacto (){
        ContentValues values = new ContentValues();
        values.put("nomcontact", contacto.getNombre());
        values.put("numcontact", contacto.getNumero());
        values.put("dircontact", contacto.getDireccion());
        SQLiteDatabase db = dataBase.getWritableDatabase();
        db.insert("Contact", null, values);
    }
    public void delContacto (){
        SQLiteDatabase db = dataBase.getWritableDatabase();
        db.delete("Contact",  "numcontact = ?", new String[] {String.valueOf(contacto.getNumero())} );
    }

    public void modificarContacto(int id){
        ContentValues values = new ContentValues();
        values.put("nomcontact", contacto.getNombre());
        values.put("numcontact", contacto.getNumero());
        values.put("dircontact", contacto.getDireccion());
        SQLiteDatabase db = dataBase.getWritableDatabase();
        db.update("Contact", values,  "idcontact = ?", new String[] { String.valueOf(id)});
    }

    public ArrayList<Contacto> listContacto(){
        String sql = "select * from Contact";
        SQLiteDatabase db = dataBase.getReadableDatabase();
        ArrayList<Contacto> storeContacto = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                String nombre = cursor.getString(1);
                String numero = cursor.getString(2);
                String direccion = cursor.getString(3);
                storeContacto.add(new Contacto(nombre, numero, direccion));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return storeContacto;
    }

    public int getID(Contacto contacto){

        String sql = "select idcontact from Contact where numcontact = "+contacto.getNumero();
        SQLiteDatabase db = dataBase.getReadableDatabase();
        int id=0;
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                id = cursor.getInt(0);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return id;
    }
}

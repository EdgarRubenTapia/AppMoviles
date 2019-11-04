package com.example.pfsqlite.DAOS;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.pfsqlite.ConexionSQLiteHelper;
import com.example.pfsqlite.Utilidades.Utilidades;
import com.example.pfsqlite.entidades.Contactos;

import java.util.ArrayList;

public class DAOS_CONTACTOS {

    SQLiteDatabase db;
    Context cont;

    public DAOS_CONTACTOS(Context cont) {
        db = new ConexionSQLiteHelper(cont,"db_contacto",null,1).getWritableDatabase();
        this.cont = cont;
    }

    public ArrayList<Contactos> consultarContactos(String usuario){

        ArrayList<Contactos> listContactos= new ArrayList<>();

        usuario = "%"+usuario+"%";
        String[] parametros = {usuario};
        Contactos n=null;

        Cursor cursor = db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_CONTACTO+" WHERE "+Utilidades.CAMPO_USUARIO+" LIKE ?",parametros);

        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                int s = Integer.parseInt(cursor.getString(0)); //id
                String s1 = cursor.getString(1); //usuario
                String s2 = cursor.getString(2); //email
                String s3 = cursor.getString(3); //telefono
                String s4 = cursor.getString(4); //fecha de nacimiento
                n = new Contactos(s,s1,s2,s3,s4);
                listContactos.add(n);
            } while(cursor.moveToNext());
        }
        cursor.close();

        return listContactos;
    }


    public ArrayList<Contactos> consultarContactos(){

        ArrayList<Contactos> listContactos= new ArrayList<>();
        String[] parametros = {};
        Contactos n=null;

        Cursor cursor = db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_CONTACTO,parametros);

        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                int s = Integer.parseInt(cursor.getString(0)); //id
                String s1 = cursor.getString(1); //usuario
                String s2 = cursor.getString(2); //email
                String s3 = cursor.getString(3); //telefono
                String s4 = cursor.getString(4); //fecha de nacimiento
                n = new Contactos(s,s1,s2,s3,s4);
                listContactos.add(n);
            } while(cursor.moveToNext());
        }
        cursor.close();

        return listContactos;
    }

    public boolean insertContacto(Contactos contacto)
    {


        try{
            String insert = "INSERT INTO "+ Utilidades.TABLA_CONTACTO +" ("+Utilidades.CAMPO_USUARIO+","+Utilidades.CAMPO_EMAIL+","+Utilidades.CAMPO_TELEFONO+","+Utilidades.CAMPO_FECHA+") VALUES "+
                    "('"+contacto.getUsuario()+"','"+contacto.getEmail()+"','"+contacto.getTelefono()+"','"+contacto.getFecNac()+"')";
            db.execSQL(insert);
            Toast.makeText(cont,"Guardado corrrectamente",Toast.LENGTH_SHORT).show();

            return true;
        }catch (Exception e){
            Toast.makeText(cont,"Error al insertar",Toast.LENGTH_SHORT).show();

            return false;
        }
    }


    public boolean actualizarContacto(Contactos contacto){
        String [] parametros = {contacto.getId()+""};
        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_USUARIO,contacto.getUsuario());
        values.put(Utilidades.CAMPO_EMAIL,contacto.getEmail());
        values.put(Utilidades.CAMPO_TELEFONO,contacto.getTelefono());
        values.put(Utilidades.CAMPO_FECHA,contacto.getFecNac());

        db.update(Utilidades.TABLA_CONTACTO,values,Utilidades.CAMPO_ID+"=? ",parametros);
        db.close();
        return true;
    }

    public boolean eliminarContacto(Contactos contacto){
        String [] parametros = {contacto.getId()+""};
        db.delete(Utilidades.TABLA_CONTACTO,Utilidades.CAMPO_ID+"=?",parametros);
        db.close();
        return true;
    }
}

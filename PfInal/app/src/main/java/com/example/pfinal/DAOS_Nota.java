package com.example.pfinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.pfinal.entidades.notas;
import com.example.pfinal.utilidades.Utilidades;

import java.util.ArrayList;
import java.util.Date;

public class DAOS_Nota {

   SQLiteDatabase db;
    Context cont;

    public DAOS_Nota(Context cont) {
        db = new ConexionSQLiteHelper(cont,"db_nota",null,1).getWritableDatabase();
        this.cont=cont;
    }

    public ArrayList<notas> consultarNotas(){

        ArrayList<notas> listNotas= new ArrayList<>();


        String[] parametros = {"1"};
        notas n=null;

            Cursor cursor = db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_NOTA+" WHERE "+Utilidades.CAMPO_TIPO+"=? ",parametros);

            if (cursor.moveToFirst()) {
                //Recorremos el cursor hasta que no haya más registros
                do {
                    int s = Integer.parseInt(cursor.getString(0));
                    String s1 = cursor.getString(1);
                    String s2 = cursor.getString(2);
                    int s3 = Integer.parseInt(cursor.getString(3));
                    String s4 = cursor.getString(4);
                    n = new notas(s,s1,s2,s3,s4);
                    listNotas.add(n);
                } while(cursor.moveToNext());
            }
            cursor.close();

        return listNotas;
    }

    public notas consultarTitulo(String titulo){

        String[] parametros = {titulo};
        String[] campos = {Utilidades.CAMPO_ID_NOTA,Utilidades.CAMPO_TITULO,Utilidades.CAMPO_DESCRIPCION,Utilidades.CAMPO_TIPO,Utilidades.CAMPO_FECHA};

            notas n=null;
            Cursor cursor = db.query(Utilidades.TABLA_NOTA,campos,Utilidades.CAMPO_TITULO+"=?",parametros,null,null,null);

            if(cursor.moveToFirst()){
                int s = Integer.parseInt(cursor.getString(0));
                String s1 = cursor.getString(1);
                String s2 = cursor.getString(2);
                int s3 = Integer.parseInt(cursor.getString(3));
                String s4 = cursor.getString(4);

                n = new notas(s,s1,s2,s3,s4);
            }

            cursor.close();
            return n;


    }

    public boolean insertNota(notas nota){


        try{
            String insert = "INSERT INTO "+ Utilidades.TABLA_NOTA +" ("+Utilidades.CAMPO_TITULO+","+Utilidades.CAMPO_DESCRIPCION+","+Utilidades.CAMPO_TIPO+","+Utilidades.CAMPO_FECHA+") VALUES "+
                    "('"+nota.getTitulo()+"','"+nota.getDescripcion()+"',"+nota.getTipo()+",'"+nota.getFecha_reg()+"')";
            db.execSQL(insert);
            Toast.makeText(cont,"Guardado corrrectamente" + nota.getId(),Toast.LENGTH_LONG).show();

            return true;
        }catch (Exception e){
            Toast.makeText(cont,"Error al insertar",Toast.LENGTH_LONG).show();

            return false;
        }
    }

    public boolean actualizatNota(notas nota){
        String [] parametros = {nota.getTipo()+"",nota.getDescripcion()};
        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_TITULO,nota.getTitulo());
        values.put(Utilidades.CAMPO_DESCRIPCION,nota.getDescripcion());
        values.put(Utilidades.CAMPO_TIPO,nota.getTipo());
        values.put(Utilidades.CAMPO_FECHA,nota.getFecha_reg());
        db.update(Utilidades.TABLA_NOTA,values,Utilidades.CAMPO_TITULO+"=? "+Utilidades.CAMPO_DESCRIPCION+"=?",parametros);
        db.close();
        return true;
    }

    public boolean eliminarNota(notas nota){
        String [] parametros = {nota.getTipo()+"",nota.getDescripcion()};
        db.delete(Utilidades.TABLA_NOTA,Utilidades.CAMPO_TITULO+"=? AND"+Utilidades.CAMPO_DESCRIPCION+"=?",parametros);
        db.close();
        return true;
    }



}

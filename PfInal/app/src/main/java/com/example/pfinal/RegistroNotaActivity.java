package com.example.pfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pfinal.entidades.notas;
import com.example.pfinal.utilidades.Utilidades;

import java.nio.file.Files;
import java.util.Date;

public class RegistroNotaActivity extends AppCompatActivity {

    EditText campoId,campoTitulo, campoDescripcion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_nota);

        campoId = (EditText) findViewById(R.id.txtadd_id);
        campoTitulo = (EditText) findViewById(R.id.txtadd_titulo);
        campoDescripcion = (EditText) findViewById(R.id.txtadd_descripcion);
    }

    public void onClick (View view){
        RegistrarNota();
    }

    private void RegistrarNota() {
        notas n = null;
        long msTime = System.currentTimeMillis();
        Date datetime = new Date(msTime);
        String fecha = datetime.toString();

        n = new notas(1, campoTitulo.getText().toString(), campoDescripcion.getText().toString(),1,fecha);
        if(insertNota(n)){
            Toast.makeText(this,"Nota insertada correctamente",Toast.LENGTH_LONG);
        }
        else{
            Toast.makeText(this,"Error al insertar",Toast.LENGTH_LONG);
        }

    }


    public boolean insertNota(notas nota){
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"db_nota",null,1);
        SQLiteDatabase db = conn.getWritableDatabase();

        try{
            String insert = "INSERT INTO "+ Utilidades.TABLA_NOTA +" ("+Utilidades.CAMPO_TITULO+","+Utilidades.CAMPO_DESCRIPCION+","+Utilidades.CAMPO_TIPO+","+Utilidades.CAMPO_FECHA+") VALUES "+
                    "('"+campoTitulo.getText().toString()+"','"+campoDescripcion.getText().toString()+"',"+nota.getTipo()+",'"+nota.getFecha_reg()+"')";
            db.execSQL(insert);
            Toast.makeText(this,"Guardado corrrectamente" + nota.getId(),Toast.LENGTH_LONG).show();
            conn.close();
            return true;
        }catch (Exception e){
            Toast.makeText(this,"Error al insertar",Toast.LENGTH_LONG).show();
            conn.close();
            return false;
        }
    }


}

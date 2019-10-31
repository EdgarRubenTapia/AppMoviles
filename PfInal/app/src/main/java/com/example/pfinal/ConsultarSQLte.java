package com.example.pfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import com.example.pfinal.entidades.*;
import com.example.pfinal.*;

import com.example.pfinal.utilidades.Utilidades;

public class ConsultarSQLte extends AppCompatActivity {

    EditText campoID,campoTitulo,campoDescripcion,campoTipo,campofecha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_sqlte);

        campoID = (EditText) findViewById(R.id.txtC_ID);
        campoTitulo = (EditText) findViewById(R.id.txtC_titulo);
        campoDescripcion = (EditText) findViewById(R.id.txtC_descripcion);
        campoTipo = (EditText) findViewById(R.id.txtC_Tipo);
        campofecha = (EditText) findViewById(R.id.txtC_fecha);

        Bundle objetoEnviado = getIntent().getExtras();
        notas nota = null;

        if(objetoEnviado!=null){
            nota = (notas) objetoEnviado.getSerializable("nota");
            campoID.setText(nota.getId()+"");
            campoTitulo.setText(nota.getTitulo().toString());
            campoDescripcion.setText(nota.getDescripcion().toString());
            campoTipo.setText(nota.getTipo()+"");
            campofecha.setText(nota.getFecha_reg().toString());
        }

    }

    public void onClick(View view){

        switch (view.getId()) {
            case R.id.btnC_buscar:
                consultarSQLite();
                break;
        }
    }

    private void consultarSQLite(){
        DAOS_Nota d = new DAOS_Nota(getApplicationContext());
        notas n = d.consultarTitulo(campoTitulo.getText().toString());

        if(n!=null){
            campoID.setText(n.getId()+"");
            campoTitulo.setText(n.getTitulo());
            campoDescripcion.setText(n.getDescripcion());
            campoTipo.setText(n.getTipo()+"");
            campofecha.setText(n.getFecha_reg());
        }


    }

    public void consultarTitulo(){
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(getApplicationContext(),"db_nota",null,1);
        SQLiteDatabase db = conn.getReadableDatabase();
        String[] parametros = {campoID.getText().toString()};
        String[] campos = {Utilidades.CAMPO_ID_NOTA,Utilidades.CAMPO_TITULO,Utilidades.CAMPO_DESCRIPCION,Utilidades.CAMPO_TIPO,Utilidades.CAMPO_FECHA};

        notas n=null;

        try{

            Cursor cursor = db.query(Utilidades.TABLA_NOTA,campos,Utilidades.CAMPO_ID_NOTA+"=?",parametros,null,null,null);

            cursor.moveToFirst();
            campoID.setText(cursor.getString(0));
            campoTitulo.setText(cursor.getString(1));
            campoDescripcion.setText(cursor.getString(2));
            campoTipo.setText(cursor.getString(3));
            campofecha.setText(cursor.getString(4));

        }catch (Exception e){
                Toast.makeText(getApplicationContext(),"Error en la consulta",Toast.LENGTH_LONG).show();

        }

    }


    private void consultar() {


    }

    private void limpiar() {
        campoTitulo.setText("");
        campoDescripcion.setText("");
        campoTipo.setText("");
        campofecha.setText("");
    }
}

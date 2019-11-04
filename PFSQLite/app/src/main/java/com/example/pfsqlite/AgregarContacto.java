package com.example.pfsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pfsqlite.DAOS.DAOS_CONTACTOS;
import com.example.pfsqlite.entidades.Contactos;

public class AgregarContacto extends AppCompatActivity {

    EditText campoUsuario;
    EditText campoEmail;
    EditText campoTelefono;
    EditText campoFecha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_contacto);

        campoUsuario = (EditText) findViewById(R.id.txtadd_usuario);
        campoEmail = (EditText) findViewById(R.id.txtadd_email);
        campoTelefono = (EditText) findViewById(R.id.txtadd_telefono);
        campoFecha = (EditText) findViewById(R.id.txtadd_fecha);

    }

    public void OnClick(View view){
        DAOS_CONTACTOS D = new DAOS_CONTACTOS(this);
        Contactos c = null;
        c = new Contactos(1,campoUsuario.getText().toString(),campoEmail.getText().toString(),campoTelefono.getText().toString(),campoFecha.getText().toString());
        D.insertContacto(c);
        finish();

    }
}

package com.example.pfsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pfsqlite.DAOS.DAOS_CONTACTOS;
import com.example.pfsqlite.entidades.Contactos;

public class MostrarContacto extends AppCompatActivity {

    EditText campoUsuario;
    EditText campoEmail;
    EditText campoTelefono;
    EditText campoFecha;

    private int ID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_contacto);

        campoUsuario = (EditText) findViewById(R.id.txtM_usuario);
        campoEmail = (EditText) findViewById(R.id.txtM_email);
        campoTelefono = (EditText) findViewById(R.id.txtM_telefono);
        campoFecha = (EditText) findViewById(R.id.txtM_fecha);

        Bundle objetoEnviado = getIntent().getExtras();
        Contactos cont=null;

        if(objetoEnviado!=null){
            cont = (Contactos) objetoEnviado.getSerializable("contacto");
            campoUsuario.setText(cont.getUsuario().toString());
            campoEmail.setText(cont.getEmail().toString());
            campoTelefono.setText(cont.getTelefono().toString());
            campoFecha.setText(cont.getFecNac().toString());
            this.ID= cont.getId();
        }

    }

    public void OnClick(View view){
        switch (view.getId()){
            case R.id.btn_Modificar:
                Contactos c = null;
                DAOS_CONTACTOS D = new DAOS_CONTACTOS(this);
                c = new Contactos(ID,campoUsuario.getText().toString(),campoEmail.getText().toString(),campoTelefono.getText().toString(),campoFecha.getText().toString());
                D.actualizarContacto(c);
                Toast.makeText(this,"Cambios guardados",Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.btn_Eliminar:
                DAOS_CONTACTOS D2 = new DAOS_CONTACTOS(this);
                c = new Contactos(ID,campoUsuario.getText().toString(),campoEmail.getText().toString(),campoTelefono.getText().toString(),campoFecha.getText().toString());
                D2.eliminarContacto(c);
                Toast.makeText(this,"Contacto eliminado",Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }
}

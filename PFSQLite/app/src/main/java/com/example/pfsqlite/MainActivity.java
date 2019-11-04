package com.example.pfsqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;

import com.example.pfsqlite.DAOS.DAOS_CONTACTOS;
import com.example.pfsqlite.entidades.*;

public class MainActivity extends AppCompatActivity {

    ArrayList<Contactos> listContactos;
    RecyclerView recycler;
    EditText campoUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        campoUsuario = (EditText) findViewById(R.id.txt_usuario);
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"db_contacto",null,1);
        recycler = (RecyclerView) findViewById(R.id.recyclerId);
    }

    public void OnClick(View view){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Intent myIntent = null;

        switch (view.getId()){
            case R.id.btn_buscar:
                recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
                listContactos = new ArrayList<Contactos>();

                DAOS_CONTACTOS D = new DAOS_CONTACTOS(this);
                listContactos = D.consultarContactos(campoUsuario.getText().toString());

                AdapterContacto adapterNotas = new AdapterContacto(listContactos);
                recycler.setAdapter(adapterNotas);
                adapterNotas.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(),"Seleccionar : "+listContactos.get(recycler.getChildAdapterPosition(v)).getUsuario(),Toast.LENGTH_SHORT).show();
                        Contactos cont = listContactos.get(recycler.getChildAdapterPosition(v));
                        Intent inten = new Intent(getApplicationContext(),MostrarContacto.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("contacto",cont);
                        inten.putExtras(bundle);
                        startActivity(inten);
                    }
                });

                break;
            case R.id.btn_agregar:
                myIntent = new Intent(MainActivity.this,AgregarContacto.class);
                break;

        }
        transaction.commit();
        if (myIntent!=null) {
            startActivity(myIntent);
        }
    }

}

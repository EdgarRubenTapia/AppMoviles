package com.example.pfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import com.example.pfinal.entidades.*;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements FragmentAddNota.OnFragmentInteractionListener{

    ArrayList<notas> listNotas;

    FragmentAddNota fragmentNota;
    RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"db_nota",null,1);
        recycler = (RecyclerView) findViewById(R.id.recyclerId);


    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void onClick(View view){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Intent myIntent = null;

        switch (view.getId()){
            case R.id.btn_1:
                recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
                listNotas = new ArrayList<notas>();

                DAOS_Nota D = new DAOS_Nota(this);
                listNotas = D.consultarNotas();

                AdapterNotas adapterNotas = new AdapterNotas(listNotas);
                recycler.setAdapter(adapterNotas);


                break;
            case R.id.btn_2:
                myIntent = new Intent(MainActivity.this,RegistroNotaActivity.class);
                break;
            case R.id.btn_3:
                myIntent = new Intent(MainActivity.this,ConsultarSQLte.class);
                break;
        }
        transaction.commit();
        if (myIntent!=null) {
            startActivity(myIntent);
        }

    }

    public void enviarObjeto(){
        notas nota = new notas(1,"Edgar","Soy pro",1,"Hoy es miercoles");

        Intent inten = new Intent(this,ConsultarSQLte.class);
        Bundle bundle = new Bundle();

        bundle.putSerializable("notas",nota);
        startActivity(inten);
    }
}

package com.example.pfinal.utilidades;

public class Utilidades {

    //Constantes campos tabla nota

    public static final String TABLA_NOTA = "nota";
    public static final String CAMPO_ID_NOTA = "id";
    public static final String CAMPO_TITULO = "titulo";
    public static final String CAMPO_DESCRIPCION = "descripcion";
    public static final String CAMPO_TIPO = "tipo";
    public static final String CAMPO_FECHA = "fecha";



    public static final String CREATE_TABLA_NOTA = "CREATE TABLE "+
            ""+TABLA_NOTA+" ("+CAMPO_ID_NOTA+" "+
            " INTEGER PRIMARY KEY, "+CAMPO_TITULO+" TEXT,"+CAMPO_DESCRIPCION+" TEXT, "+CAMPO_TIPO+" INTEGER, "+
            CAMPO_FECHA +" TEXT)";
}

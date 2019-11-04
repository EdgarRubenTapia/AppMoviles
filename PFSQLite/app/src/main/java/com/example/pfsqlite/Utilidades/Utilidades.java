package com.example.pfsqlite.Utilidades;

public class Utilidades {

    //Constantes campos tabla contacto

    public static final String TABLA_CONTACTO = "contacto";
    public static final String CAMPO_ID = "id";
    public static final String CAMPO_USUARIO = "usuario";
    public static final String CAMPO_EMAIL = "email";
    public static final String CAMPO_TELEFONO = "telefono";
    public static final String CAMPO_FECHA = "fecNac";



    public static final String CREATE_TABLA_CONTACTO = "CREATE TABLE "+
            ""+TABLA_CONTACTO+" ("+CAMPO_ID+" "+
            " INTEGER PRIMARY KEY, "+CAMPO_USUARIO+" TEXT,"+CAMPO_EMAIL+" TEXT, "+CAMPO_TELEFONO+" INTEGER, "+
            CAMPO_FECHA +" TEXT)";
}

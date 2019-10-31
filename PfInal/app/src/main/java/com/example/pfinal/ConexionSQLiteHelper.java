package com.example.pfinal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.pfinal.utilidades.Utilidades;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {



    public ConexionSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Utilidades.CREATE_TABLA_NOTA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int VersionAntigua, int VersionNueva) {
        db.execSQL("DROP TABLE IF EXISTS nota");
        onCreate(db);
    }


}

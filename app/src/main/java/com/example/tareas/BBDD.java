package com.example.tareas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.logging.Logger;

public class BBDD extends SQLiteOpenHelper {

    private static final Logger LOG = Logger.getLogger(AddingEvent.class.getName());
    private String sqlCreate = "CREATE TABLE EVENTOS (nombre TEXT, fecha TEXT )";

    public BBDD(Context contexto, String nombre, SQLiteDatabase.CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }

    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {
        db.execSQL("DROP TABLE IF EXISTS EVENTOS");
        db.execSQL(sqlCreate);
    }
}

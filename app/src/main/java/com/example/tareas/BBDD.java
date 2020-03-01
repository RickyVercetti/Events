package com.example.tareas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class BBDD extends SQLiteOpenHelper {

    String sqlCreate = "CREATE TABLE EVENTOS (nombre TEXT, fecha LONG )";

    public BBDD(Context contexto, String nombre, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(contexto, nombre, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(sqlCreate);
        if (db == null)
        {
            db.execSQL("INSERT INTO EVENTOS (nombre,fecha) VALUES ('Hola y Adios', 997362986)");
            //db.execSQL("INSERT INTO EVENTOS (nombre,fecha) VALUES ('Ricardo','30','01-02-2017')");
        }
        SQLiteStatement sql = db.compileStatement("SELECT * FROM BBDD.EVENTOS");
        sql.execute();
        System.out.println(sql.toString());
    }

    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva)
    {
        db.execSQL("DROP TABLE IF EXISTS puntuaciones");
        db.execSQL(sqlCreate);
    }


}

package com.example.mycomics.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.mycomics.beans.ProfilActifBean;
import com.example.mycomics.beans.ProfilBean;

import java.util.ArrayList;
import java.util.List;

//*********************** Convention noms SQLITE : UPPERCASE et undersores

public class DataBaseHelper extends SQLiteOpenHelper {

    /* -------------------------------------- */
    // refactor - introduce constant pour les requetes SQL
    /* -------------------------------------- */
    //TABLE PROFILS
    public static final String PROFILS = "PROFILS";
    public static final String COLUMN_PROFIL_ID = "PROFIL_ID";
    public static final String COLUMN_PROFIL_NOM = "PROFIL_NOM";
    //TABLE PROFIL_ACTIF
    public static final String PROFIL_ACTIF = "PROFIL_ACTIF";
    public static final String COLUMN_PROFIL_ACTIF_ID = "PROFIL_ACTIF_ID";


    /* -------------------------------------- */
    // Constructeur
    /* -------------------------------------- */
    // Car SQLiteHelper en a besoin
    public DataBaseHelper(@Nullable Context context) {
        super(context, "mycomics.db", null, 1);
    }

    //Oncreate appelée à la première fois que l'application a besoin de la BDD, crée les tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Table PROFILS
        db.execSQL("CREATE TABLE " + PROFILS + " (" + COLUMN_PROFIL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_PROFIL_NOM + " TEXT)");
        //Table PROFIL_ACTIF
        db.execSQL("CREATE TABLE  " + PROFIL_ACTIF + "  (" + COLUMN_PROFIL_ACTIF_ID + " INTEGER PRIMARY KEY, " + COLUMN_PROFIL_ID + " INTEGER)");
        db.execSQL("INSERT INTO " + PROFIL_ACTIF + " ("+ COLUMN_PROFIL_ACTIF_ID + ", " + COLUMN_PROFIL_ID+ ") VALUES (1,-1)");

    }

    //onUpgrade appelé si la version de la BDD change. Pour éviter conflits
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + PROFILS); // pour chaque table
        onCreate(db);
    }

    /* -------------------------------------- */
    // INSERT INTO PROFILS
    /* -------------------------------------- */
    public boolean insertIntoProfils(ProfilBean profilBean){
        SQLiteDatabase db = this.getWritableDatabase(); // accès écriture BDD
        ContentValues cv = new ContentValues(); //stocke des paires clé-valeur
        cv.put(COLUMN_PROFIL_NOM, profilBean.getProfil_nom()); //ajout dans la pile
        long insert = db.insert(PROFILS, null, cv); //insertion en base
        if (insert == -1){ // Test si insertion ok
            return false;
        } else {
            return true;
        }
    }

    /* -------------------------------------- */
    // SELECT * FROM PROFILS
    /* -------------------------------------- */
    public List<ProfilBean> selectAllFromProfils(){
        List<ProfilBean> returnList = new ArrayList<>();
        String requete = "SELECT * FROM " + PROFILS;
        SQLiteDatabase db = this.getReadableDatabase(); // accès lecture BDD
        Cursor cursor = db.rawQuery(requete, null); //cursor = résultat de la requête
        if (cursor.moveToFirst()) { // true si il y a des résultats
            do { // pour chaque tuple
                int profil_id = cursor.getInt(0);
                String profil_nom = cursor.getString(1);
                ProfilBean profilBean = new ProfilBean(profil_id, profil_nom);
                returnList.add(profilBean);
            } while (cursor.moveToNext()); //on passe au tuple suivant
        } else {
            // pas de résultats on ne fait rien
        }
        // fermeture db et cursor
        cursor.close();
        db.close();
        return returnList;
    }
    /* -------------------------------------- */
    // SELECT * FROM PROFIL_ACTIF, une seule entrée
    /* -------------------------------------- */
    public ProfilActifBean selectAllFromProfilActif(){
        ProfilActifBean profilActifBean = null;
//        String requete = "SELECT * FROM " + PROFIL_ACTIF + " WHERE " + COLUMN_PROFIL_ACTIF_ID + " = 0";
        String requete = "SELECT * FROM " + PROFIL_ACTIF;
        SQLiteDatabase db = this.getReadableDatabase(); // accès lecture BDD
        Cursor cursor = db.rawQuery(requete, null); //cursor = résultat de la requête
        if (cursor.moveToFirst()) { // true si il y a un résultat
            int profil_id = cursor.getInt(0);
            profilActifBean = new ProfilActifBean(profil_id);
        } else {
            // pas de résultats on ne fait rien
        }
        // fermeture db et cursor
        cursor.close();
        db.close();
        return profilActifBean;
    }
    /* -------------------------------------- */
    // SELECT * FROM PROFILS WHERE PROFIL_ID = PROFIL_ACTIF_ID
    /* -------------------------------------- */
    public ProfilBean selectFromProfilProfilActif(){
        ProfilBean profilBean = null;
        String requete = "SELECT " + PROFILS + "." + COLUMN_PROFIL_ID + ", "+ PROFILS + "." + COLUMN_PROFIL_NOM + " FROM " + PROFILS + " JOIN " + PROFIL_ACTIF + " ON " + PROFILS + "." + COLUMN_PROFIL_ID + " = " + PROFIL_ACTIF + "." + COLUMN_PROFIL_ID + " WHERE " + PROFILS + "." + COLUMN_PROFIL_ID + " = " + PROFIL_ACTIF + "." + COLUMN_PROFIL_ID + " LIMIT 1";
        SQLiteDatabase db = this.getReadableDatabase(); // accès lecture BDD
        Cursor cursor = db.rawQuery(requete, null); //cursor = résultat de la requête
        if (cursor.moveToFirst()) { // true si il y a des résultats
            int profil_id = cursor.getInt(0);
            String profil_nom = cursor.getString(1);
            profilBean = new ProfilBean(profil_id, profil_nom);
        } else {
            // pas de résultats on ne fait rien
        }
        // fermeture db et cursor
        cursor.close();
        db.close();
        return profilBean;
    }
    /* -------------------------------------- */
    // UPDATE PROFIL_ACTIF SET PROFIL_ID = x
    /* -------------------------------------- */
    public boolean updateProfilActif(long nouvelId){
        SQLiteDatabase db = this.getWritableDatabase(); // accès lecture BDD
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_PROFIL_ID, nouvelId);
        long update =  db.update(PROFIL_ACTIF,cv,null,null);
        if (update == -1){ // Test si insertion ok
            System.out.println("update request : non");
            return false;
        } else {
            System.out.println("update request : OK");
            return true;
        }
    }

}

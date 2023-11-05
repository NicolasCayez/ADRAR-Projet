package com.example.mycomics.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.mycomics.beans.AuteurBean;
import com.example.mycomics.beans.AuteurPseudoBean;
import com.example.mycomics.beans.EditeurBean;
import com.example.mycomics.beans.EditeurNomBean;
import com.example.mycomics.beans.ProfilActifBean;
import com.example.mycomics.beans.ProfilBean;
import com.example.mycomics.beans.ProfilNomBean;
import com.example.mycomics.beans.SerieBean;
import com.example.mycomics.beans.SerieNomBean;

import java.util.ArrayList;
import java.util.List;

//*********************** Convention noms SQLITE : UPPERCASE et undersores

public class DataBaseHelper extends SQLiteOpenHelper {

    /* -------------------------------------- */
    // Constantes via refactor - introduce constant pour les requetes SQL
    /* -------------------------------------- */
    // Table PROFILS
    public static final String PROFILS = "PROFILS";
    public static final String COLUMN_PROFIL_ID = "PROFIL_ID";
    public static final String COLUMN_PROFIL_NOM = "PROFIL_NOM";
    // Table PROFIL_ACTIF
    public static final String PROFIL_ACTIF = "PROFIL_ACTIF";
    public static final String COLUMN_PROFIL_ACTIF_ID = "PROFIL_ACTIF_ID";
    // Table EDITEURS
    public static final String EDITEURS = "EDITEURS";
    public static final String COLUMN_EDITEUR_ID = "EDITEUR_ID";
    public static final String COLUMN_EDITEUR_NOM = "EDITEUR_NOM";
    // Table SERIES
    public static final String SERIES = "SERIES";
    public static final String COLUMN_SERIE_ID = "SERIE_ID";
    public static final String COLUMN_SERIE_NOM = "SERIE_NOM";
    // Table AUTEURS
    public static final String AUTEURS = "AUTEURS";
    public static final String COLUMN_AUTEUR_ID = "AUTEUR_ID";
    public static final String COLUMN_AUTEUR_NOM = "AUTEUR_NOM";
    public static final String COLUMN_AUTEUR_PRENOM = "AUTEUR_PRENOM";
    public static final String COLUMN_AUTEUR_PSEUDO = "AUTEUR_PSEUDO";
    public static final String COLUMN_AUTEUR_PHOTO = "AUTEUR_PHOTO";
    // Table TOMES
    public static final String TOMES = "TOMES";
    public static final String COLUMN_TOME_ID = "TOME_ID";
    public static final String COLUMN_TOME_NUMERO = "TOME_NUMERO";
    public static final String COLUMN_TOME_TITRE = "TOME_TITRE";
    public static final String COLUMN_TOME_PRIX_ACHAT = "TOME_PRIX_ACHAT";
    public static final String COLUMN_TOME_VALEUR_CONNUE = "TOME_VALEUR_CONNUE";
    public static final String COLUMN_TOME_DATE_EDITION = "TOME_DATE_EDITION";
    public static final String COLUMN_TOME_ISBN = "TOME_ISBN";
    public static final String COLUMN_TOME_IMAGE = "TOME_IMAGE";
    public static final String COLUMN_TOME_DEDICACE = "TOME_DEDICACE";
    public static final String COLUMN_TOME_EDITION_SPECIALE = "TOME_EDITION_SPECIALE";
    public static final String COLUMN_TOME_EDITION_SPECIALE_LIBELLE = "TOME_EDITION_SPECIALE_LIBELLE";
    public static final String DETENIR = "DETENIR";
    public static final String EDITER = "EDITER";
    public static final String ECRIRE = "ECRIRE";


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
        db.execSQL("CREATE TABLE " + PROFILS + " (" +
                COLUMN_PROFIL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PROFIL_NOM + " TEXT NOT NULL)");

        //Table PROFIL_ACTIF initialisée
        db.execSQL("CREATE TABLE  " + PROFIL_ACTIF + "  (" +
                COLUMN_PROFIL_ACTIF_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_PROFIL_ID + " INTEGER)");

        db.execSQL("INSERT INTO " + PROFIL_ACTIF + " ("+
                COLUMN_PROFIL_ACTIF_ID + ", " +
                COLUMN_PROFIL_ID+ ") " +
                "VALUES (1,-1)");

        //Table EDITEURS
        db.execSQL("CREATE TABLE " + EDITEURS + " (" +
                COLUMN_EDITEUR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_EDITEUR_NOM + " TEXT NOT NULL)");

        //Table SERIES
        db.execSQL("CREATE TABLE " + SERIES + " (" +
                COLUMN_SERIE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_SERIE_NOM + " TEXT NOT NULL)");

        //Table AUTEURS
        db.execSQL("CREATE TABLE " + AUTEURS + " (" +
                COLUMN_AUTEUR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_AUTEUR_NOM + " TEXT, " +
                COLUMN_AUTEUR_PRENOM + " TEXT, " +
                COLUMN_AUTEUR_PSEUDO + " TEXT NOT NULL, " +
                COLUMN_AUTEUR_PHOTO + " TEXT)");

        //Table TOMES
        db.execSQL("CREATE TABLE " + TOMES + " (" +
                COLUMN_TOME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TOME_NUMERO + " INTEGER NOT NULL, " +
                COLUMN_TOME_TITRE + " TEXT NOT NULL, " +
                COLUMN_TOME_PRIX_ACHAT + " DECIMAL(5,2), " +
                COLUMN_TOME_VALEUR_CONNUE + " DECIMAL(5,2), " +
                COLUMN_TOME_DATE_EDITION + " DATE, " +
                COLUMN_TOME_ISBN + " TEXT NOT NULL, " +
                COLUMN_TOME_IMAGE + " TEXT, " +
                COLUMN_TOME_DEDICACE + " BOOLEAN, " +
                COLUMN_TOME_EDITION_SPECIALE + " BOOLEAN, " +
                COLUMN_TOME_EDITION_SPECIALE_LIBELLE + " TEXT, " +
                COLUMN_SERIE_ID + " INTEGER," +
                " FOREIGN KEY(" + COLUMN_SERIE_ID + ") REFERENCES " + SERIES + "(" + COLUMN_SERIE_ID + "))");

        //Table d'association DETENIR
        db.execSQL("CREATE TABLE " + DETENIR + " (" +
                COLUMN_TOME_ID + " INTEGER, " +
                COLUMN_PROFIL_ID + " INTEGER, " +
                "PRIMARY KEY (" + COLUMN_TOME_ID + ", " + COLUMN_PROFIL_ID + "), " +
                "FOREIGN KEY(" + COLUMN_TOME_ID + ") REFERENCES " + TOMES + "(" + COLUMN_TOME_ID + ")," +
                "FOREIGN KEY(" + COLUMN_PROFIL_ID + ") REFERENCES " + PROFILS + "(" + COLUMN_PROFIL_ID + "))");

        //Table d'association EDITER
        db.execSQL("CREATE TABLE " + EDITER + " (" +
                COLUMN_TOME_ID + " INTEGER, " +
                COLUMN_EDITEUR_ID + " INTEGER, " +
                "PRIMARY KEY (" + COLUMN_TOME_ID + ", " + COLUMN_EDITEUR_ID + "), " +
                "FOREIGN KEY(" + COLUMN_TOME_ID + ") REFERENCES " + TOMES + "(" + COLUMN_TOME_ID + ")," +
                "FOREIGN KEY(" + COLUMN_EDITEUR_ID + ") REFERENCES " + EDITEURS + "(" + COLUMN_EDITEUR_ID + "))");

        //Table d'association ECRIRE
        db.execSQL("CREATE TABLE " + ECRIRE + " (" +
                COLUMN_TOME_ID + " INTEGER, " +
                COLUMN_AUTEUR_ID + " INTEGER, " +
                "PRIMARY KEY (" + COLUMN_TOME_ID + ", " + COLUMN_AUTEUR_ID + "), " +
                "FOREIGN KEY(" + COLUMN_TOME_ID + ") REFERENCES " + TOMES + "(" + COLUMN_TOME_ID + ")," +
                "FOREIGN KEY(" + COLUMN_AUTEUR_ID + ") REFERENCES " + AUTEURS + "(" + COLUMN_AUTEUR_ID + "))");

    }

    //onUpgrade appelé si la version de la BDD change. Pour éviter conflits
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + PROFILS); // pour chaque table
        onCreate(db);
    }


/* ************************************** */
// REQUETES UPDATE
/* ************************************** */

    /* -------------------------------------- */
    // UPDATE PROFIL_ACTIF SET PROFIL_ID = x
    /* -------------------------------------- */
    public boolean updateProfilActif(DataBaseHelper dataBaseHelper, long nouvelId){
        SQLiteDatabase db = this.getWritableDatabase(); // accès lecture BDD
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_PROFIL_ID, nouvelId);
        long update =  db.update(PROFIL_ACTIF,cv,null,null);
        if (update == -1){ // Test si insertion ok
            System.out.println("update request : non");
            return false;
        } else {
            System.out.println("update request : OK");
            System.out.println("Id profil actif: " + dataBaseHelper.selectAllFromProfilActif().toString());
            return true;
        }
    }

/* ************************************** */
// REQUETES INSERT INTO
/* ************************************** */

    /* -------------------------------------- */
    // INSERT INTO PROFILS NOM SEUL
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
    // INSERT INTO EDITEURS NOM SEUL
    /* -------------------------------------- */
    public boolean insertIntoEditeurs(EditeurBean editeurBean){
        SQLiteDatabase db = this.getWritableDatabase(); // accès écriture BDD
        ContentValues cv = new ContentValues(); //stocke des paires clé-valeur
        cv.put(COLUMN_EDITEUR_NOM, editeurBean.getEditeur_nom()); //ajout dans la pile
        long insert = db.insert(EDITEURS, null, cv); //insertion en base
        if (insert == -1){ // Test si insertion ok
            return false;
        } else {
            return true;
        }
    }

    /* -------------------------------------- */
    // INSERT INTO AUTEURS PSEUDO SEUL
    /* -------------------------------------- */
    public boolean insertIntoAuteurs(AuteurBean auteurBean){
        SQLiteDatabase db = this.getWritableDatabase(); // accès écriture BDD
        ContentValues cv = new ContentValues(); //stocke des paires clé-valeur
        cv.put(COLUMN_AUTEUR_PSEUDO, auteurBean.getAuteur_pseudo()); //ajout dans la pile
        long insert = db.insert(AUTEURS, null, cv); //insertion en base
        if (insert == -1){ // Test si insertion ok
            return false;
        } else {
            return true;
        }
    }

    /* -------------------------------------- */
    // INSERT INTO SERIES NOM SEUL
    /* -------------------------------------- */
    public boolean insertIntoSeries(SerieBean serieBean){
        SQLiteDatabase db = this.getWritableDatabase(); // accès écriture BDD
        ContentValues cv = new ContentValues(); //stocke des paires clé-valeur
        cv.put(COLUMN_SERIE_NOM, serieBean.getSerie_nom()); //ajout dans la pile
        long insert = db.insert(SERIES, null, cv); //insertion en base
        if (insert == -1){ // Test si insertion ok
            return false;
        } else {
            return true;
        }
    }


/* ************************************** */
// REQUETES SELECT
/* ************************************** */

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
    // SELECT * FROM PROFILS (PROFILNOMBEAN)
    /* -------------------------------------- */
    public List<ProfilNomBean> selectAllFromProfilsNomSeul(){
        List<ProfilNomBean> returnList = new ArrayList<>();
        String requete = "SELECT * FROM " + PROFILS + " ORDER BY " + COLUMN_PROFIL_NOM;;
        SQLiteDatabase db = this.getReadableDatabase(); // accès lecture BDD
        Cursor cursor = db.rawQuery(requete, null); //cursor = résultat de la requête
        if (cursor.moveToFirst()) { // true si il y a des résultats
            do { // pour chaque tuple
                int profil_id = cursor.getInt(0);
                String profil_nom = cursor.getString(1);
                ProfilNomBean profilNomBean = new ProfilNomBean(profil_id, profil_nom);
                returnList.add(profilNomBean);
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
    // SELECT COLUMN_PROFIL_NOM FROM PROFILS
    /* -------------------------------------- */
    public List<String> selectNomFromProfils(){
        List<String> returnList = new ArrayList<>();
        String requete = "SELECT " + COLUMN_PROFIL_NOM + " FROM " + PROFILS;
        SQLiteDatabase db = this.getReadableDatabase(); // accès lecture BDD
        Cursor cursor = db.rawQuery(requete, null); //cursor = résultat de la requête
        if (cursor.moveToFirst()) { // true si il y a des résultats
            do { // pour chaque tuple
                String profil_nom = cursor.getString(0);
                returnList.add(profil_nom);
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
        String requete = "SELECT " + PROFILS + "." + COLUMN_PROFIL_ID + ", "+ PROFILS + "." + COLUMN_PROFIL_NOM + " FROM " + PROFILS + " INNER JOIN " + PROFIL_ACTIF + " ON " + PROFILS + "." + COLUMN_PROFIL_ID + " = " + PROFIL_ACTIF + "." + COLUMN_PROFIL_ID + " WHERE " + PROFILS + "." + COLUMN_PROFIL_ID + " = " + PROFIL_ACTIF + "." + COLUMN_PROFIL_ID;
        SQLiteDatabase db = this.getReadableDatabase(); // accès lecture BDD
        Cursor cursor = db.rawQuery(requete, null); //cursor = résultat de la requête
        if (cursor.moveToFirst()) { // true si il y a des résultats
            int profil_id = cursor.getInt(0);
            String profil_nom = cursor.getString(1);
            profilBean = new ProfilBean(profil_id, profil_nom);
            System.out.println("select : " + profilBean.getProfil_nom());
        } else {
            // pas de résultats on ne fait rien
        }
        // fermeture db et cursor
        cursor.close();
        db.close();
        return profilBean;
    }

    /* -------------------------------------- */
    // SELECT * FROM EDITEURS
    /* -------------------------------------- */
    public List<EditeurBean> selectAllFromEditeurs(){
        List<EditeurBean> returnList = new ArrayList<>();
        String requete = "SELECT * FROM " + EDITEURS;
        SQLiteDatabase db = this.getReadableDatabase(); // accès lecture BDD
        Cursor cursor = db.rawQuery(requete, null); //cursor = résultat de la requête
        if (cursor.moveToFirst()) { // true si il y a des résultats
            do { // pour chaque tuple
                int editeur_id = cursor.getInt(0);
                String editeur_nom = cursor.getString(1);
                EditeurBean editeurBean = new EditeurBean(editeur_id, editeur_nom);
                returnList.add(editeurBean);
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
    // SELECT * FROM EDITEURS (EDITEURNOMBEAN)
    /* -------------------------------------- */
    public List<EditeurNomBean> selectAllFromEditeursNomSeul(){
        List<EditeurNomBean> returnList = new ArrayList<>();
        String requete = "SELECT * FROM " + EDITEURS + " ORDER BY " + COLUMN_EDITEUR_NOM;
        SQLiteDatabase db = this.getReadableDatabase(); // accès lecture BDD
        Cursor cursor = db.rawQuery(requete, null); //cursor = résultat de la requête
        if (cursor.moveToFirst()) { // true si il y a des résultats
            do { // pour chaque tuple
                int editeur_id = cursor.getInt(0);
                String editeur_nom = cursor.getString(1);
                EditeurNomBean editeurNomBean = new EditeurNomBean(editeur_id, editeur_nom);
                returnList.add(editeurNomBean);
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
    // SELECT * FROM AUTEURS
    /* -------------------------------------- */
    public List<AuteurBean> selectAllFromAuteurs(){
        List<AuteurBean> returnList = new ArrayList<>();
        String requete = "SELECT * FROM " + AUTEURS;
        SQLiteDatabase db = this.getReadableDatabase(); // accès lecture BDD
        Cursor cursor = db.rawQuery(requete, null); //cursor = résultat de la requête
        if (cursor.moveToFirst()) { // true si il y a des résultats
            do { // pour chaque tuple
                int auteur_id = cursor.getInt(0);
                String auteur_nom = cursor.getString(1);
                String auteur_prenom = cursor.getString(2);
                String auteur_pseudo = cursor.getString(3);
                String auteur_photo = cursor.getString(4);
                AuteurBean auteurBean = new AuteurBean(auteur_id, auteur_nom, auteur_prenom, auteur_pseudo, auteur_photo);
                returnList.add(auteurBean);
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
    // SELECT * FROM AUTEURS (AUTEURPSEUDOBEAN)
    /* -------------------------------------- */
    public List<AuteurPseudoBean> selectAllFromAuteursPseudoSeul(){
        List<AuteurPseudoBean> returnList = new ArrayList<>();
        String requete = "SELECT * FROM " + AUTEURS + " ORDER BY " + COLUMN_AUTEUR_PSEUDO;
        SQLiteDatabase db = this.getReadableDatabase(); // accès lecture BDD
        Cursor cursor = db.rawQuery(requete, null); //cursor = résultat de la requête
        if (cursor.moveToFirst()) { // true si il y a des résultats
            do { // pour chaque tuple
                int auteur_id = cursor.getInt(0);
                String auteur_nom = cursor.getString(1);
                String auteur_prenom = cursor.getString(2);
                String auteur_pseudo = cursor.getString(3);
                String auteur_photo = cursor.getString(4);
                AuteurPseudoBean auteurPseudoBean = new AuteurPseudoBean(auteur_id, auteur_nom, auteur_prenom, auteur_pseudo, auteur_photo);
                returnList.add(auteurPseudoBean);
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
    // SELECT * FROM SERIES
    /* -------------------------------------- */
    public List<SerieBean> selectAllFromSeries(){
        List<SerieBean> returnList = new ArrayList<>();
        String requete = "SELECT * FROM " + SERIES;
        SQLiteDatabase db = this.getReadableDatabase(); // accès lecture BDD
        Cursor cursor = db.rawQuery(requete, null); //cursor = résultat de la requête
        if (cursor.moveToFirst()) { // true si il y a des résultats
            do { // pour chaque tuple
                int serie_id = cursor.getInt(0);
                String serie_nom = cursor.getString(1);
                SerieBean serieBean = new SerieBean(serie_id, serie_nom);
                returnList.add(serieBean);
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
    // SELECT * FROM SERIES (SERIENOMBEAN)
    /* -------------------------------------- */
    public List<SerieNomBean> selectAllFromSeriesNomSeul(){
        List<SerieNomBean> returnList = new ArrayList<>();
        String requete = "SELECT * FROM " + SERIES + " ORDER BY " + COLUMN_SERIE_NOM;
        SQLiteDatabase db = this.getReadableDatabase(); // accès lecture BDD
        Cursor cursor = db.rawQuery(requete, null); //cursor = résultat de la requête
        if (cursor.moveToFirst()) { // true si il y a des résultats
            do { // pour chaque tuple
                int serie_id = cursor.getInt(0);
                String serie_nom = cursor.getString(1);
                SerieNomBean serieNomBean = new SerieNomBean(serie_id, serie_nom);
                returnList.add(serieNomBean);
            } while (cursor.moveToNext()); //on passe au tuple suivant
        } else {
            // pas de résultats on ne fait rien
        }
        // fermeture db et cursor
        cursor.close();
        db.close();
        return returnList;
    }
}

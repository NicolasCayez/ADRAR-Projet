package com.example.mycomics.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.mycomics.beans.AuteurBean;
import com.example.mycomics.beans.EditeurBean;
import com.example.mycomics.beans.ProfilActifBean;
import com.example.mycomics.beans.ProfilBean;
import com.example.mycomics.beans.SerieBean;
import com.example.mycomics.beans.TomeBean;
import com.example.mycomics.beans.TomeSerieBean;

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
    // Table TOMES
    public static final String TOMES = "TOMES";
    public static final String COLUMN_TOME_ID = "TOME_ID";
    public static final String COLUMN_TOME_NUMERO = "TOME_NUMERO";
    public static final String COLUMN_TOME_TITRE = "TOME_TITRE";
    public static final String COLUMN_TOME_PRIX_EDITEUR = "TOME_PRIX_EDITEUR";
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
    // Constructeur - Crée la base si inexistante
    /* -------------------------------------- */
    // = CREATE DATABASE IF NOT EXISTS mycomics
    public DataBaseHelper(@Nullable Context context) {
        super(context, "mycomics.db", null, 1);
    }

    //Methode Oncreate appelée si la base de donnée est inexistante
        @Override
    public void onCreate(SQLiteDatabase db) {
        //Table PROFILS (profil_id, profil_nom)
        db.execSQL("CREATE TABLE " + PROFILS + " (" + COLUMN_PROFIL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_PROFIL_NOM + " TEXT NOT NULL)");
        db.execSQL("INSERT INTO " + PROFILS + " ("+ COLUMN_PROFIL_NOM+ ") " + "VALUES (\"Profil 1\")");


            //Table PROFIL_ACTIF 1 ligne initialisée (profil_actif_id, profil_id)
        db.execSQL("CREATE TABLE  " + PROFIL_ACTIF + "  (" + COLUMN_PROFIL_ACTIF_ID + " INTEGER PRIMARY KEY, " + COLUMN_PROFIL_ID + " INTEGER)");
        db.execSQL("INSERT INTO " + PROFIL_ACTIF + " ("+ COLUMN_PROFIL_ACTIF_ID + ", " + COLUMN_PROFIL_ID+ ") " + "VALUES (1,1)");

        //Table EDITEURS (editeur_id, editeur_nom)
        db.execSQL("CREATE TABLE " + EDITEURS + " (" + COLUMN_EDITEUR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_EDITEUR_NOM + " TEXT NOT NULL)");

        //Table SERIES (serie_id, serie_nom)
        db.execSQL("CREATE TABLE " + SERIES + " (" + COLUMN_SERIE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_SERIE_NOM + " TEXT NOT NULL)");

        //Table AUTEURS (auteur_id, auteur_pseudo, auteur_prenom, auteur_nom)
        db.execSQL("CREATE TABLE " + AUTEURS + " (" + COLUMN_AUTEUR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_AUTEUR_PSEUDO + " TEXT NOT NULL, " +
                COLUMN_AUTEUR_PRENOM + " TEXT, " + COLUMN_AUTEUR_NOM + " TEXT)");

        //Table TOMES (tome_id, tome_titre, tome_isbn, tome_numero, tome_image, tome_prix_editeur, tome_valeur_connue,
        //              tome_dedicace, tome_edition_speciale, tome_edition_speciale_libelle, serie_id)
        db.execSQL("CREATE TABLE " + TOMES + " (" +
                COLUMN_TOME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TOME_TITRE + " TEXT NOT NULL, " +
                COLUMN_TOME_NUMERO + " INTEGER, " +
                COLUMN_TOME_ISBN + " TEXT, " +
                COLUMN_TOME_IMAGE + " TEXT, " +
                COLUMN_TOME_PRIX_EDITEUR + " DECIMAL(5,2), " +
                COLUMN_TOME_VALEUR_CONNUE + " DECIMAL(5,2), " +
                COLUMN_TOME_DATE_EDITION + " TEXT, " +
                COLUMN_TOME_DEDICACE + " BOOLEAN, " +
                COLUMN_TOME_EDITION_SPECIALE + " BOOLEAN, " +
                COLUMN_TOME_EDITION_SPECIALE_LIBELLE + " TEXT, " +
                COLUMN_SERIE_ID + " INTEGER," +
                " FOREIGN KEY(" + COLUMN_SERIE_ID + ") REFERENCES " + SERIES + "(" + COLUMN_SERIE_ID + "))");

        //Table d'association DETENIR (tome_id, profil_id)
        db.execSQL("CREATE TABLE " + DETENIR + " (" +
                COLUMN_TOME_ID + " INTEGER, " +
                COLUMN_PROFIL_ID + " INTEGER, " +
                "PRIMARY KEY (" + COLUMN_TOME_ID + ", " + COLUMN_PROFIL_ID + "), " +
                "FOREIGN KEY(" + COLUMN_TOME_ID + ") REFERENCES " + TOMES + "(" + COLUMN_TOME_ID + ")," +
                "FOREIGN KEY(" + COLUMN_PROFIL_ID + ") REFERENCES " + PROFILS + "(" + COLUMN_PROFIL_ID + "))");

        //Table d'association EDITER (tome_id, editeur_id)
        db.execSQL("CREATE TABLE " + EDITER + " (" +
                COLUMN_TOME_ID + " INTEGER, " +
                COLUMN_EDITEUR_ID + " INTEGER, " +
                "PRIMARY KEY (" + COLUMN_TOME_ID + ", " + COLUMN_EDITEUR_ID + "), " +
                "FOREIGN KEY(" + COLUMN_TOME_ID + ") REFERENCES " + TOMES + "(" + COLUMN_TOME_ID + ")," +
                "FOREIGN KEY(" + COLUMN_EDITEUR_ID + ") REFERENCES " + EDITEURS + "(" + COLUMN_EDITEUR_ID + "))");

        //Table d'association ECRIRE (tome_id, auteur_id)
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
        db.execSQL("DROP TABLE " + PROFIL_ACTIF); // pour chaque table
        db.execSQL("DROP TABLE " + EDITEURS); // pour chaque table
        db.execSQL("DROP TABLE " + SERIES); // pour chaque table
        db.execSQL("DROP TABLE " + AUTEURS); // pour chaque table
        db.execSQL("DROP TABLE " + TOMES); // pour chaque table
        db.execSQL("DROP TABLE " + DETENIR); // pour chaque table
        db.execSQL("DROP TABLE " + EDITER); // pour chaque table
        db.execSQL("DROP TABLE " + ECRIRE); // pour chaque table
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

    /* -------------------------------------- */
    // UPDATE PROFIL_ACTIF SET PROFIL_ID = x
    /* -------------------------------------- */
    public boolean updateProfil(DataBaseHelper dataBaseHelper, ProfilBean profil){
        SQLiteDatabase db = this.getWritableDatabase(); // accès lecture BDD
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_PROFIL_NOM, profil.getProfil_nom());
        long update =  db.update(PROFILS,cv,"PROFIL_ID = ?",new String[]{String.valueOf(profil.getProfil_id())});
        if (update == -1){ // Test si insertion ok
            System.out.println("update request : non");
            return false;
        } else {
            System.out.println("update request : OK");
            System.out.println("Id profil actif: " + dataBaseHelper.selectAllFromProfilActif().toString());
            return true;
        }
    }

    /* -------------------------------------- */
    // UPDATE TOME WHERE TOME_ID = tome_id
    /* -------------------------------------- */
    public boolean updateTome(DataBaseHelper dataBaseHelper, TomeBean tome){
        SQLiteDatabase db = this.getWritableDatabase(); // accès lecture BDD
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TOME_TITRE, tome.getTome_titre());
        cv.put(COLUMN_TOME_NUMERO, tome.getTome_numero());
        cv.put(COLUMN_TOME_ISBN, tome.getTome_isbn());
        cv.put(COLUMN_TOME_PRIX_EDITEUR, tome.getTome_prix_editeur());
        cv.put(COLUMN_TOME_VALEUR_CONNUE, tome.getTome_valeur_connue());
        cv.put(COLUMN_TOME_DATE_EDITION, tome.getTome_date_edition());
        cv.put(COLUMN_TOME_IMAGE, tome.getTome_image());
        cv.put(COLUMN_TOME_DEDICACE, tome.isTome_dedicace());
        cv.put(COLUMN_TOME_EDITION_SPECIALE, tome.isTome_edition_speciale());
        cv.put(COLUMN_TOME_EDITION_SPECIALE_LIBELLE, tome.getTome_edition_speciale_libelle());
        cv.put(COLUMN_SERIE_ID, tome.getSerie_id());
        long update =  db.update(TOMES,cv,"TOME_ID = ?",new String[]{String.valueOf(tome.getTome_id())});
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

    /* -------------------------------------- */
    // INSERT INTO TOMES TITRE SEUL
    /* -------------------------------------- */
    public boolean insertIntoTomes(TomeBean tomeBean){
        //Table Tomes
        SQLiteDatabase db = this.getWritableDatabase(); // accès écriture BDD
        ContentValues cv = new ContentValues(); //stocke des paires clé-valeur
        cv.put(COLUMN_TOME_TITRE, tomeBean.getTome_titre()); //ajout dans la pile
        long insert = db.insert(TOMES, null, cv); //insertion en base
        if (insert == -1){ // Test si insertion ok
            db.close();
            return false;
        } else {
            db.close();
            return true;
        }
    }

    /* -------------------------------------- */
    // INSERT INTO DETENIR
    /* -------------------------------------- */
//     = INSERT INTO DETENIR (tome_id, profil_id) VALUES (Tome.tome_titre, selectAllformProfilActif.getprofil.id())
    public boolean insertIntoDetenir(TomeBean tomeBean){
        int profil_id = this.selectAllFromProfilActif().getProfil_id();
        SQLiteDatabase db = this.getWritableDatabase(); // accès écriture BDD
        ContentValues cv = new ContentValues(); //stocke des paires clé-valeur
        cv.put(COLUMN_TOME_ID, tomeBean.getTome_id()); //ajout dans la pile
        cv.put(COLUMN_PROFIL_ID, profil_id);
        long insert = db.insert(DETENIR, null, cv); //insertion en base
        if (insert == -1){ // Test si insertion ok
            db.close();
            return false;
        } else {
            db.close();
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
                AuteurBean auteurBean = new AuteurBean(auteur_id, auteur_nom, auteur_prenom, auteur_pseudo);
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
    // SELECT * FROM TOMES
    /* -------------------------------------- */
    public ArrayList<TomeBean> selectAllFromTomes(){
        ArrayList<TomeBean> returnList = new ArrayList<>();
        String requete = "SELECT * FROM " + TOMES;

        SQLiteDatabase db = this.getReadableDatabase(); // accès lecture BDD
        Cursor cursor = db.rawQuery(requete, null); //cursor = résultat de la requête

        if (cursor.moveToFirst()) { // true si il y a des résultats
            do { // pour chaque tuple
                int tome_id = cursor.getInt(0);
                String tome_titre = cursor.getString(1);
                int tome_numero = cursor.getInt(2);
                String tome_isbn = cursor.getString(3);
                double tome_prix_achat = cursor.getDouble(4);
                double tome_valeur_connue = cursor.getDouble(5);
                String tome_date_edition = cursor.getString(6);
                String tome_image = cursor.getString(7);
                boolean tome_dedicace = cursor.getInt(8) == 1 ? true: false;
                boolean tome_edition_speciale = cursor.getInt(9) == 1 ? true: false;
                String tome_edition_speciale_libelle = cursor.getString(10);
                int serie_id = cursor.getInt(11);

                TomeBean tomeBean = new TomeBean(tome_id, tome_titre, tome_numero, tome_isbn, tome_image,
                        tome_prix_achat, tome_valeur_connue, tome_date_edition, tome_dedicace,
                        tome_edition_speciale, tome_edition_speciale_libelle, serie_id);
                returnList.add(tomeBean);
            } while (cursor.moveToNext()); //on passe au tuple suivant
        }
        else {
            // pas de résultats on ne fait rien
        }
        // fermeture base de données et cursor
        cursor.close();
        db.close();
        return returnList; // résultat de la requête
    }

    /* -------------------------------------- */
    // SELECT * FROM TOMES JOIN DETENIR ON TOMES.TOME_ID = DETENIR.TOME_ID
    // WHERE DETENIR.PROFIL_ID = selectAllFromProfilActif().getProfil_id();    /* -------------------------------------- */
    public List<TomeSerieBean> selectAllFromTomesEtNomSerie(){
        List<TomeSerieBean> returnList = new ArrayList<>();
        String requete = "SELECT * FROM " + TOMES +
                " INNER JOIN " + DETENIR + " ON " + TOMES + "." + COLUMN_TOME_ID + " = " + DETENIR + "." + COLUMN_TOME_ID +
                " INNER JOIN " + PROFIL_ACTIF + " ON " + PROFIL_ACTIF + "." + COLUMN_PROFIL_ID + " = " + DETENIR + "." + COLUMN_PROFIL_ID +
                " WHERE " + DETENIR + "." + COLUMN_PROFIL_ID + " = \"" + selectAllFromProfilActif().getProfil_id() +
                "\" AND " + PROFIL_ACTIF + "." + COLUMN_PROFIL_ID + " = 1";
//        String requete = "SELECT * FROM " + TOMES + " ORDER BY " + COLUMN_TOME_TITRE;
        SQLiteDatabase db = this.getReadableDatabase(); // accès lecture BDD
        Cursor cursor = db.rawQuery(requete, null); //cursor = résultat de la requête
        if (cursor.moveToFirst()) { // true si il y a des résultats
            do { // pour chaque tuple
                int tome_id = cursor.getInt(0);
                String tome_titre = cursor.getString(1);
                int tome_numero = cursor.getInt(2);
                int serie_id = cursor.getInt(11);
                TomeSerieBean tomeSerieBean = new TomeSerieBean(tome_id, tome_titre, tome_numero, null,
                        null, 0, 0, null, false,
                        false, null, serie_id, "test");
                returnList.add(tomeSerieBean);
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
    // "SELECT COLUMN_TOME_ID FROM TOMES WHERE COLUMN_TOME_TITRE = + tomeBean.getTome_titre() +
    // ORDER BY COLUMN_TOME_ID DESC LIMIT 1"
    /* -------------------------------------- */
    public TomeBean selectTOMEIDFromTomesDernierAjout(TomeBean tomeBean){
        String requete = "SELECT " + COLUMN_TOME_ID + " FROM " + TOMES +
                " WHERE " + COLUMN_TOME_TITRE + " = \"" + tomeBean.getTome_titre() +
                "\" ORDER BY " + COLUMN_TOME_ID +" DESC LIMIT 1";
        SQLiteDatabase db = this.getReadableDatabase(); // accès lecture BDD
        TomeBean result = new TomeBean();
        Cursor cursor = db.rawQuery(requete, null); //cursor = résultat de la requête
        if (cursor.moveToFirst()) { // true si il y a des résultats
            result.setTome_id(cursor.getInt(0));
        } else {
            // pas de résultats on ne fait rien
        }
        // fermeture db et cursor
        cursor.close();
        db.close();
        return result;
    }

    /* -------------------------------------- */
    // SELECT COUNT (*) FROM DETENIR WHERE COLUMN_TOME_ID = selectTOMEIDFromTomesDernierAjout(tomeBean)
    /* -------------------------------------- */

    public boolean selectFromDetenirDernierTomeOk(TomeBean tomeBean){
        SQLiteDatabase db = this.getWritableDatabase(); // accès écriture BDD
        ContentValues cv = new ContentValues(); //stocke des paires clé-valeur

//        SELECT COUNT (*) FROM DETENIR WHERE COLUMN_TOME_ID = selectFromTomesDernierAjout(tomeBean)
        String requete = "SELECT COUNT(*) FROM " + DETENIR + " WHERE " + COLUMN_TOME_ID + " = " + selectTOMEIDFromTomesDernierAjout(tomeBean);
        Cursor cursor = db.rawQuery(requete, null); //cursor = résultat de la requête
        int nbResult = 0;
        if (cursor.moveToFirst()) { // true si il y a des résultats
            nbResult = cursor.getInt(0);
        } else {
            // pas de résultats on ne fait rien
        }
        // fermeture db et cursor
        cursor.close();
        db.close();
        if (nbResult == 1) {
            db.close();
            return true;
        } else {
            db.close();
            return false;
        }
    }

    /* -------------------------------------- */
    // SELECT * FROM TOMES WHERE TOME_ID = tome_id
    /* -------------------------------------- */
    public TomeBean selectTome(int tome_id_voulu){
        TomeBean tomeBean = new TomeBean();
        String requete = "SELECT * FROM " + TOMES +
                " JOIN " + DETENIR +
                " ON " + TOMES + "." + COLUMN_TOME_ID + " = " + DETENIR + "." + COLUMN_TOME_ID +
                " WHERE " + DETENIR + "." + COLUMN_PROFIL_ID  + "=" + selectAllFromProfilActif() +
                " AND " + TOMES + "." + COLUMN_TOME_ID + " = " + tome_id_voulu + " LIMIT 1";
        SQLiteDatabase db = this.getReadableDatabase(); // accès lecture BDD
        Cursor cursor = db.rawQuery(requete, null); //cursor = résultat de la requête
        if (cursor.moveToFirst()) { // true si il y a des résultats
            do { // pour chaque tuple
                int tome_id = cursor.getInt(0);
                String tome_titre = cursor.getString(1);
                int tome_numero = cursor.getInt(2);
                String tome_isbn = cursor.getString(3);
                String tome_image = cursor.getString(4);
                double tome_prix_achat = cursor.getDouble(5);
                double tome_valeur_connue = cursor.getDouble(6);
                String tome_date_edition = cursor.getString(7);
                boolean tome_dedicace = cursor.getInt(8) == 1 ? true: false;
                boolean tome_edition_speciale = cursor.getInt(9) == 1 ? true: false;
                String tome_edition_speciale_libelle = cursor.getString(10);
                int serie_id = cursor.getInt(11);
                tomeBean = new TomeBean(tome_id, tome_titre, tome_numero, tome_isbn,  tome_image, tome_prix_achat, tome_valeur_connue, tome_date_edition, tome_dedicace, tome_edition_speciale, tome_edition_speciale_libelle, serie_id);
            } while (cursor.moveToNext()); //on passe au tuple suivant
        } else {
            // pas de résultats on ne fait rien
        }
        // fermeture db et cursor
        cursor.close();
        db.close();
        return tomeBean;
    }
}

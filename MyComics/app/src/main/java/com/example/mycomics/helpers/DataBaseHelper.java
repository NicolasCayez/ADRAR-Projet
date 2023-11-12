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
import com.example.mycomics.fragments.ReglagesFragment;

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
                COLUMN_EDITEUR_ID + " INTEGER," +
                " FOREIGN KEY(" + COLUMN_SERIE_ID + ") REFERENCES " + SERIES + "(" + COLUMN_SERIE_ID + ")" +
                " FOREIGN KEY(" + COLUMN_EDITEUR_ID + ") REFERENCES " + EDITEURS + "(" + COLUMN_EDITEUR_ID + "))");

        //Table d'association DETENIR (tome_id, profil_id)
        db.execSQL("CREATE TABLE " + DETENIR + " (" +
                COLUMN_TOME_ID + " INTEGER, " +
                COLUMN_PROFIL_ID + " INTEGER, " +
                "PRIMARY KEY (" + COLUMN_TOME_ID + ", " + COLUMN_PROFIL_ID + "), " +
                "FOREIGN KEY(" + COLUMN_TOME_ID + ") REFERENCES " + TOMES + "(" + COLUMN_TOME_ID + ")," +
                "FOREIGN KEY(" + COLUMN_PROFIL_ID + ") REFERENCES " + PROFILS + "(" + COLUMN_PROFIL_ID + "))");


        //Table d'association ECRIRE (tome_id, auteur_id)
        db.execSQL("CREATE TABLE " + ECRIRE + " (" +
                COLUMN_TOME_ID + " INTEGER, " +
                COLUMN_AUTEUR_ID + " INTEGER, " +
                "PRIMARY KEY(" + COLUMN_TOME_ID + ", " + COLUMN_AUTEUR_ID + "), " +
                "FOREIGN KEY(" + COLUMN_TOME_ID + ") REFERENCES " + TOMES + "(" + COLUMN_TOME_ID + ")," +
                "FOREIGN KEY(" + COLUMN_AUTEUR_ID + ") REFERENCES " + AUTEURS + "(" + COLUMN_AUTEUR_ID + "))");


        /* -------------------------------------- */
        // Entrées de Test
        /* -------------------------------------- */
            // AUTEURS
            db.execSQL("INSERT INTO AUTEURS(AUTEUR_PSEUDO) VALUES(\"Buchet\");\n"); //auteur_id 1
            db.execSQL("INSERT INTO AUTEURS(AUTEUR_PSEUDO) VALUES(\"Crisse\");\n"); //auteur_id 2
            db.execSQL("INSERT INTO AUTEURS(AUTEUR_PSEUDO) VALUES(\"Duval\");\n"); //auteur_id 3
            db.execSQL("INSERT INTO AUTEURS(AUTEUR_PSEUDO) VALUES(\"Eiichiro Oda\");\n"); //auteur_id 4
            db.execSQL("INSERT INTO AUTEURS(AUTEUR_PSEUDO) VALUES(\"Goscinny\");\n"); //auteur_id 5
            db.execSQL("INSERT INTO AUTEURS(AUTEUR_PSEUDO) VALUES(\"Gotlib\");\n"); //auteur_id 6
            db.execSQL("INSERT INTO AUTEURS(AUTEUR_PSEUDO) VALUES(\"Hugo Pratt\");\n"); //auteur_id 7
            db.execSQL("INSERT INTO AUTEURS(AUTEUR_PSEUDO) VALUES(\"Uderzo\");\n"); //auteur_id 8

            // EDITEURS
            db.execSQL("INSERT INTO EDITEURS(EDITEUR_NOM) VALUES(\"Casterman\");\n"); //editeur_id 1
            db.execSQL("INSERT INTO EDITEURS(EDITEUR_NOM) VALUES(\"Dargaud\");\n"); //editeur_id 2
            db.execSQL("INSERT INTO EDITEURS(EDITEUR_NOM) VALUES(\"Delcourt\");\n"); //editeur_id 3
            db.execSQL("INSERT INTO EDITEURS(EDITEUR_NOM) VALUES(\"Glenat\");\n"); //editeur_id 4
            db.execSQL("INSERT INTO EDITEURS(EDITEUR_NOM) VALUES(\"Pilote\");\n"); //editeur_id 5
            db.execSQL("INSERT INTO EDITEURS(EDITEUR_NOM) VALUES(\"Soleil Editions\");\n"); //editeur_id 6

            // SERIES
            db.execSQL("INSERT INTO SERIES(SERIE_NOM) VALUES(\"Asterix\");\n"); //serie_id 1
            db.execSQL("INSERT INTO SERIES(SERIE_NOM) VALUES(\"Carmen Mc Callum\");\n"); //serie_id 2
            db.execSQL("INSERT INTO SERIES(SERIE_NOM) VALUES(\"Clifton\");\n"); //serie_id 3
            db.execSQL("INSERT INTO SERIES(SERIE_NOM) VALUES(\"Kookaburra\");\n"); //serie_id 4
            db.execSQL("INSERT INTO SERIES(SERIE_NOM) VALUES(\"Lanfeust de Troy\");\n"); //serie_id 5
            db.execSQL("INSERT INTO SERIES(SERIE_NOM) VALUES(\"Lanfeust des étoiles\");\n"); //serie_id 6
            db.execSQL("INSERT INTO SERIES(SERIE_NOM) VALUES(\"Lucky Luke\");\n"); //serie_id 7
            db.execSQL("INSERT INTO SERIES(SERIE_NOM) VALUES(\"One Piece\");\n"); //serie_id 8
            db.execSQL("INSERT INTO SERIES(SERIE_NOM) VALUES(\"Sillage\");\n"); //serie_id 9
            db.execSQL("INSERT INTO SERIES(SERIE_NOM) VALUES(\"Trolls de Troy\");\n"); //serie_id 10

            // TOMES et DETENIR
            db.execSQL("INSERT INTO TOMES(TOME_TITRE, TOME_NUMERO, SERIE_ID) VALUES(\"Asterix Le Gaulois\", 1, 1);\n"); //tome_id 1
            db.execSQL("INSERT INTO DETENIR(TOME_ID, PROFIL_ID) VALUES(1, 1);\n");
            db.execSQL("INSERT INTO TOMES(TOME_TITRE, TOME_NUMERO, SERIE_ID) VALUES(\"La serpe d'or\", 2, 1);\n"); //tome_id 2
            db.execSQL("INSERT INTO DETENIR(TOME_ID, PROFIL_ID) VALUES(2, 1);\n");
            db.execSQL("INSERT INTO TOMES(TOME_TITRE, TOME_NUMERO, SERIE_ID) VALUES(\"Asterix et les Goths\", 3, 1);\n"); //tome_id 3
            db.execSQL("INSERT INTO DETENIR(TOME_ID, PROFIL_ID) VALUES(3, 1);\n");
            db.execSQL("INSERT INTO TOMES(TOME_TITRE, TOME_NUMERO, SERIE_ID) VALUES(\"Asterix Gladiateur\", 4, 1);\n"); //tome_id 4
            db.execSQL("INSERT INTO DETENIR(TOME_ID, PROFIL_ID) VALUES(4, 1);\n");
            db.execSQL("INSERT INTO TOMES(TOME_TITRE, TOME_NUMERO, SERIE_ID) VALUES(\"Le tour de Gaule d'Asterix\", 5, 1);\n"); //tome_id 5
            db.execSQL("INSERT INTO DETENIR(TOME_ID, PROFIL_ID) VALUES(5, 1);\n");
            db.execSQL("INSERT INTO TOMES(TOME_TITRE, TOME_NUMERO, SERIE_ID) VALUES(\"Asterix et Cléopâtre\", 6, 1);\n"); //tome_id 6
            db.execSQL("INSERT INTO DETENIR(TOME_ID, PROFIL_ID) VALUES(6, 1);\n");
            db.execSQL("INSERT INTO TOMES(TOME_TITRE, TOME_NUMERO, SERIE_ID) VALUES(\"Le combat des chefs\", 7, 1);\n"); //tome_id 7
            db.execSQL("INSERT INTO DETENIR(TOME_ID, PROFIL_ID) VALUES(7, 1);\n");
            db.execSQL("INSERT INTO TOMES(TOME_TITRE, TOME_NUMERO, SERIE_ID) VALUES(\"Asterix Chez les bretons\", 8, 1);\n"); //tome_id 8
            db.execSQL("INSERT INTO DETENIR(TOME_ID, PROFIL_ID) VALUES(8, 1);\n");
            db.execSQL("INSERT INTO TOMES(TOME_TITRE, TOME_NUMERO, SERIE_ID) VALUES(\"Asterix et les Normands\", 9, 1);\n"); //tome_id 9
            db.execSQL("INSERT INTO DETENIR(TOME_ID, PROFIL_ID) VALUES(9, 1);\n");
            db.execSQL("INSERT INTO TOMES(TOME_TITRE, TOME_NUMERO, SERIE_ID) VALUES(\"Asterix légionnaire\", 10, 1);\n"); //tome_id 10
            db.execSQL("INSERT INTO DETENIR(TOME_ID, PROFIL_ID) VALUES(10, 1);\n");

            // ECRIRE
            db.execSQL("INSERT INTO ECRIRE(TOME_ID, AUTEUR_ID) VALUES(1, 5);\n");
            db.execSQL("INSERT INTO ECRIRE(TOME_ID, AUTEUR_ID) VALUES(2, 5);\n");
            db.execSQL("INSERT INTO ECRIRE(TOME_ID, AUTEUR_ID) VALUES(3, 5);\n");
            db.execSQL("INSERT INTO ECRIRE(TOME_ID, AUTEUR_ID) VALUES(4, 5);\n");
            db.execSQL("INSERT INTO ECRIRE(TOME_ID, AUTEUR_ID) VALUES(5, 5);\n");
            db.execSQL("INSERT INTO ECRIRE(TOME_ID, AUTEUR_ID) VALUES(6, 5);\n");
            db.execSQL("INSERT INTO ECRIRE(TOME_ID, AUTEUR_ID) VALUES(7, 5);\n");
            db.execSQL("INSERT INTO ECRIRE(TOME_ID, AUTEUR_ID) VALUES(8, 5);\n");
            db.execSQL("INSERT INTO ECRIRE(TOME_ID, AUTEUR_ID) VALUES(9, 5);\n");
            db.execSQL("INSERT INTO ECRIRE(TOME_ID, AUTEUR_ID) VALUES(10, 5);\n");
            db.execSQL("INSERT INTO ECRIRE(TOME_ID, AUTEUR_ID) VALUES(1, 8);\n");
            db.execSQL("INSERT INTO ECRIRE(TOME_ID, AUTEUR_ID) VALUES(2, 8);\n");
            db.execSQL("INSERT INTO ECRIRE(TOME_ID, AUTEUR_ID) VALUES(3, 8);\n");
            db.execSQL("INSERT INTO ECRIRE(TOME_ID, AUTEUR_ID) VALUES(4, 8);\n");
            db.execSQL("INSERT INTO ECRIRE(TOME_ID, AUTEUR_ID) VALUES(5, 8);\n");
            db.execSQL("INSERT INTO ECRIRE(TOME_ID, AUTEUR_ID) VALUES(6, 8);\n");
            db.execSQL("INSERT INTO ECRIRE(TOME_ID, AUTEUR_ID) VALUES(7, 8);\n");
            db.execSQL("INSERT INTO ECRIRE(TOME_ID, AUTEUR_ID) VALUES(8, 8);\n");
            db.execSQL("INSERT INTO ECRIRE(TOME_ID, AUTEUR_ID) VALUES(9, 8);\n");
            db.execSQL("INSERT INTO ECRIRE(TOME_ID, AUTEUR_ID) VALUES(10, 8);\n");



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
        db.execSQL("DROP TABLE " + ECRIRE); // pour chaque table
        onCreate(db);
    }


/* ********************************************************************************************** */
// REQUETES UPDATE
/* ********************************************************************************************** */

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
    // UPDATE PROFIL SET PROFIL_nom = profil_nom WHERE PROFIL_ID = profil_id
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

    /* -------------------------------------- */
    // UPDATE TOMES SET SERIE_ID = serie_id WHERE TOME_ID = tome_id
    /* -------------------------------------- */
    public boolean updateSerieTome(DataBaseHelper dataBaseHelper, SerieBean serieBean, Integer tome_id){
        SQLiteDatabase db = this.getWritableDatabase(); // accès lecture BDD
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_SERIE_ID, serieBean.getSerie_id());
        long update = db.update(TOMES,cv,"TOME_ID = ?",new String[]{String.valueOf(tome_id)});
        if (update == -1){ // Test si insertion ok
            System.out.println("update request : non");
            return false;
        } else {
            System.out.println("update request : OK");
            return true;
        }
    }

    /* -------------------------------------- */
    // UPDATE TOMES SET EDITEUR_ID = editeur_id WHERE TOME_ID = tome_id
    /* -------------------------------------- */
    public boolean updateEditeurTome(DataBaseHelper dataBaseHelper, EditeurBean editeurBean, Integer tome_id){
        SQLiteDatabase db = this.getWritableDatabase(); // accès lecture BDD
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_EDITEUR_ID, editeurBean.getEditeur_id());
        long update = db.update(TOMES,cv,"TOME_ID = ?",new String[]{String.valueOf(tome_id)});
        if (update == -1){ // Test si insertion ok
            System.out.println("update request : non");
            return false;
        } else {
            System.out.println("update request : OK");
            return true;
        }
    }

/* ********************************************************************************************** */
// REQUETES INSERT INTO
/* ********************************************************************************************** */

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
    // INSERT INTO ECRIRE (tome_id, auteur_id)
    /* -------------------------------------- */
    public boolean insertIntoEcrire(int tome_id, int auteur_id){
        SQLiteDatabase db = this.getWritableDatabase(); // accès écriture BDD
        ContentValues cv = new ContentValues(); //stocke des paires clé-valeur
        cv.put(COLUMN_TOME_ID, tome_id); //ajout dans la pile
        cv.put(COLUMN_AUTEUR_ID, auteur_id); //ajout dans la pile
        long insert = db.insert(ECRIRE, null, cv); //insertion en base
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


/* ********************************************************************************************** */
// REQUETES SELECT
/* ********************************************************************************************** */

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
    // SELECT COUNT (*) FROM PROFILS
    // WHERE COLUMN_PROFIL_NOM = profil_nom
    /* -------------------------------------- */
    public boolean verifDoublonProfil(String profil_nom){
        SQLiteDatabase db = this.getWritableDatabase(); // accès écriture BDD
        ContentValues cv = new ContentValues(); //stocke des paires clé-valeur
        String requete = "SELECT COUNT(*) FROM " + PROFILS + " WHERE " + COLUMN_PROFIL_NOM + " = \"" + profil_nom + "\"";
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
    // SELECT * FROM PROFILS
    // WHERE PROFIL_ID = PROFIL_ACTIF_ID
    /* -------------------------------------- */
    public ProfilBean selectAllFromProfilSelonProfilActif(){
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
    // SELECT * FROM EDITEURS
    // WHERE EDITEUR_ID = editeur_id
    /* -------------------------------------- */
    public EditeurBean selectEditeurSelonEditeurId(int editeur_id_voulu){
        EditeurBean editeurBean = new EditeurBean();
        String requete = "SELECT * FROM " + EDITEURS +
                " WHERE " + EDITEURS + "." + COLUMN_EDITEUR_ID + " = \"" + editeur_id_voulu + "\" LIMIT 1";
        SQLiteDatabase db = this.getReadableDatabase(); // accès lecture BDD
        Cursor cursor = db.rawQuery(requete, null); //cursor = résultat de la requête
        if (cursor.moveToFirst()) { // true si il y a des résultats
            do { // pour chaque tuple
                int editeur_id = cursor.getInt(0);
                String editeur_nom = cursor.getString(1);
                editeurBean = new EditeurBean(editeur_id, editeur_nom);
            } while (cursor.moveToNext()); //on passe au tuple suivant
        } else {
            // pas de résultats on ne fait rien
        }
        // fermeture db et cursor
        cursor.close();
        db.close();
        return editeurBean;
    }

    /* -------------------------------------- */
    // SELECT COUNT (*) FROM EDITEUR
    // WHERE COLUMN_EDITEUR_NOM = editeur_nom
    /* -------------------------------------- */
    public boolean verifDoublonEditeur(String editeur_nom){
        SQLiteDatabase db = this.getWritableDatabase(); // accès écriture BDD
        ContentValues cv = new ContentValues(); //stocke des paires clé-valeur
        String requete = "SELECT COUNT(*) FROM " + EDITEURS + " WHERE " + COLUMN_EDITEUR_NOM + " = \"" + editeur_nom + "\"";
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
    // SELECT * FROM EDITEURS
    // INNER JOIN EDITER ON EDITER.EDITEUR_ID = EDITEUR.EDITEUR_ID
    // WHERE EDITER.TOME_ID = tome_id LIMIT 1
    // 1 seul résultat
    /* -------------------------------------- */
        public EditeurBean selectEditeurSelonEditeurNom(String nom){
        EditeurBean editeurBean = null;
        String requete = "SELECT * FROM " + EDITEURS +
                " WHERE " + EDITEURS + "." + COLUMN_EDITEUR_NOM + " = \"" + nom + "\" LIMIT 1";
        SQLiteDatabase db = this.getReadableDatabase(); // accès lecture BDD
        Cursor cursor = db.rawQuery(requete, null); //cursor = résultat de la requête
        if (cursor.moveToFirst()) { // true si il y a des résultats
            int editeur_id = cursor.getInt(0);
            String editeur_nom = cursor.getString(1);
            editeurBean = new EditeurBean(editeur_id, editeur_nom);
        } else {
            // pas de résultats
        editeurBean = new EditeurBean(-1, "error");
        }
        // fermeture db et cursor
        cursor.close();
        db.close();
        return editeurBean;
    }

    /* -------------------------------------- */
    // SELECT * FROM EDITEURS
    // WHERE EDITEURS.EDITEUR_NOM = editeur_nom LIMIT 1
    // 1 seul résultat
    /* -------------------------------------- */
    public EditeurBean selectEditeurSelonTomeId(int tome_id){
        EditeurBean editeurBean;
        String requete = "SELECT * FROM " + EDITEURS +
                " INNER JOIN " + TOMES + " ON " + EDITEURS + "." + COLUMN_EDITEUR_ID + " = " + TOMES + "." + COLUMN_EDITEUR_ID +
                " WHERE " + TOMES + "." + COLUMN_TOME_ID + " = \"" + tome_id + "\" LIMIT 1";
        SQLiteDatabase db = this.getReadableDatabase(); // accès lecture BDD
        Cursor cursor = db.rawQuery(requete, null); //cursor = résultat de la requête
        if (cursor.moveToFirst()) { // true si il y a des résultats
            int editeur_id = cursor.getInt(0);
            String editeur_nom = cursor.getString(1);
            editeurBean = new EditeurBean(editeur_id, editeur_nom);
        } else {
            // pas de résultats
            editeurBean = new EditeurBean(-1, "Pas d'éditeur enregistré");
        }
        // fermeture db et cursor
        cursor.close();
        db.close();
        return editeurBean;
    }

    /* -------------------------------------- */
    // "SELECT COLUMN_EDITEUR_ID FROM EDITEURS
    // WHERE COLUMN_EDITEUR_NOM = + editeurBean.getEditeur_nom() +
    // ORDER BY COLUMN_EDITEUR DESC LIMIT 1"
    /* -------------------------------------- */
    public EditeurBean selectFromEditeursDernierAjout(EditeurBean editeurBean){
        String requete = "SELECT " + COLUMN_EDITEUR_ID + " FROM " + EDITEURS +
                " WHERE " + COLUMN_EDITEUR_NOM + " = \"" + editeurBean.getEditeur_nom() +
                "\" ORDER BY " + COLUMN_EDITEUR_ID +" DESC LIMIT 1";
        SQLiteDatabase db = this.getReadableDatabase(); // accès lecture BDD
        EditeurBean result = new EditeurBean();
        Cursor cursor = db.rawQuery(requete, null); //cursor = résultat de la requête
        if (cursor.moveToFirst()) { // true si il y a des résultats
            result.setEditeur_id(cursor.getInt(0));
            result.setEditeur_nom(cursor.getString(1));
        } else {
            // pas de résultats on ne fait rien
        }
        // fermeture db et cursor
        cursor.close();
        db.close();
        return result;
    }

    /* -------------------------------------- */
    // SELECT * FROM TOMES
    // INNER JOIN ECRIRE ON ECRIRE.TOME_ID= TOME.TOME_ID
    // WHERE ECRIRE.AUTEUR_ID = auteur_id
    /* -------------------------------------- */
    public List<EditeurBean> selectAllFromEditeursSelonAuteurId(int auteur_id){
        List<EditeurBean> returnList = new ArrayList<>();
        String requete = "SELECT * FROM " + EDITEURS +
                " INNER JOIN " + TOMES + " ON " + TOMES + "." + COLUMN_EDITEUR_ID + " = " + EDITEURS + "." + COLUMN_EDITEUR_ID +
                " INNER JOIN " + ECRIRE + " ON " + ECRIRE + "." + COLUMN_TOME_ID + " = " + TOMES + "." + COLUMN_TOME_ID +
                " WHERE " + ECRIRE + "." + COLUMN_AUTEUR_ID + " = \"" + auteur_id + "\"";
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
                String auteur_pseudo = cursor.getString(1);
                String auteur_prenom = cursor.getString(2);
                String auteur_nom = cursor.getString(3);
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
    // SELECT * FROM AUTEURS
    // WHERE AUTEUR_ID = auteur_id
    /* -------------------------------------- */
    public AuteurBean selectAuteurSelonAuteurId(int auteur_id_voulu){
        AuteurBean auteurBean = new AuteurBean();
        String requete = "SELECT * FROM " + AUTEURS +
                " WHERE " + AUTEURS + "." + COLUMN_AUTEUR_ID + " = \"" + auteur_id_voulu + "\" LIMIT 1";
        SQLiteDatabase db = this.getReadableDatabase(); // accès lecture BDD
        Cursor cursor = db.rawQuery(requete, null); //cursor = résultat de la requête
        if (cursor.moveToFirst()) { // true si il y a des résultats
            do { // pour chaque tuple
                int auteur_id = cursor.getInt(0);
                String auteur_pseudo = cursor.getString(1);
                String auteur_prenom = cursor.getString(2);
                String auteur_nom = cursor.getString(3);
                auteurBean = new AuteurBean(auteur_id, auteur_pseudo, auteur_prenom, auteur_nom);
            } while (cursor.moveToNext()); //on passe au tuple suivant
        } else {
            // pas de résultats on ne fait rien
        }
        // fermeture db et cursor
        cursor.close();
        db.close();
        return auteurBean;
    }

    /* -------------------------------------- */
    // SELECT COUNT (*) FROM AUTEURS
    // WHERE COLUMN_AUTEUR_PSEUDO = auteur_pseudo
    /* -------------------------------------- */
    public boolean verifDoublonAuteur(String auteur_pseudo){
        SQLiteDatabase db = this.getWritableDatabase(); // accès écriture BDD
        ContentValues cv = new ContentValues(); //stocke des paires clé-valeur
        String requete = "SELECT COUNT(*) FROM " + AUTEURS + " WHERE " + COLUMN_AUTEUR_PSEUDO + " = \"" + auteur_pseudo + "\"";
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
    // SELECT * FROM AUTEURS
    // INNER JOIN ECRIRE ON AUTEUR.AUTEUR_ID = ECRIRE.AUTEUR_ID
    // WHERE ECRIRE.TOME_ID = tome_id
    /* -------------------------------------- */
    public List<AuteurBean> selectAllFromAuteursSelonTomeId(int tome_id){
        List<AuteurBean> returnList = new ArrayList<>();
        AuteurBean auteurBean = null;
        String requete = "SELECT * FROM " + AUTEURS +
                " INNER JOIN " + ECRIRE +
                " ON " + AUTEURS + "." + COLUMN_AUTEUR_ID + " = " + ECRIRE + "." + COLUMN_AUTEUR_ID +
                " WHERE " + ECRIRE + "." + COLUMN_TOME_ID + " = \"" + tome_id + "\"";
        SQLiteDatabase db = this.getReadableDatabase(); // accès lecture BDD
        Cursor cursor = db.rawQuery(requete, null); //cursor = résultat de la requête
        if (cursor.moveToFirst()) { // true si il y a des résultats
            do { // pour chaque tuple
                int auteur_id = cursor.getInt(0);
                String auteur_pseudo = cursor.getString(1);
                auteurBean = new AuteurBean(auteur_id, auteur_pseudo);
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
    // SELECT * FROM AUTEURS
    // INNER JOIN ECRIRE ON AUTEUR.AUTEUR_ID = ECRIRE.AUTEUR_ID
    // WHERE ECRIRE.TOME_ID IN
    //                  (SELECT * FROM TOMES
    //                   INNER JOIN ECRIRE ON TOMES.TOME_ID = ECRIRE.TOME_ID
    //                   WHERE ECRIRE.AUTEUR_ID = auteur_id)
    // EXCEPT
    // SELECT * FROM AUTEURS
    // WHERE AUTEURS.AUTEUR_ID = auteur_id
    /* -------------------------------------- */
    public List<AuteurBean> selectAllFromAuteursPartenaires(int auteur_id_voulu){
        List<AuteurBean> returnList = new ArrayList<>();

        String requete = "SELECT DISTINCT A1.* FROM " + AUTEURS + " A1 INNER JOIN " + ECRIRE + " E1 ON A1." + COLUMN_AUTEUR_ID + " = E1." + COLUMN_AUTEUR_ID + " INNER JOIN " + ECRIRE + " E2 ON E1." + COLUMN_TOME_ID + " = E2." + COLUMN_TOME_ID + " INNER JOIN " + AUTEURS + " A2 ON E2." + COLUMN_AUTEUR_ID + "= A2." + COLUMN_AUTEUR_ID + " WHERE A1." + COLUMN_AUTEUR_ID + " <> A2." + COLUMN_AUTEUR_ID + " AND A2." + COLUMN_AUTEUR_ID + " =\"" + auteur_id_voulu + "\"";

        SQLiteDatabase db = this.getReadableDatabase(); // accès lecture BDD
        Cursor cursor = db.rawQuery(requete, null); //cursor = résultat de la requête
        if (cursor.moveToFirst()) { // true si il y a des résultats
            do { // pour chaque tuple
                int auteur_id = cursor.getInt(0);
                String auteur_nom = cursor.getString(1);
                String auteur_prenom = cursor.getString(2);
                String auteur_pseudo = cursor.getString(3);
                AuteurBean auteurBean = new AuteurBean(auteur_id, auteur_pseudo, auteur_prenom, auteur_nom);
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
    // "SELECT COLUMN_TOME_ID FROM TOMES
    // WHERE COLUMN_TOME_TITRE = tomeBean.getTome_titre()
    // ORDER BY COLUMN_TOME_ID DESC LIMIT 1"
    /* -------------------------------------- */
    public AuteurBean selectFromAuteursDernierAjout(AuteurBean auteurBean){
        String requete = "SELECT " + COLUMN_AUTEUR_ID + " FROM " + AUTEURS +
                " WHERE " + COLUMN_AUTEUR_PSEUDO + " = \"" + auteurBean.getAuteur_pseudo() +
                "\" ORDER BY " + COLUMN_AUTEUR_ID +" DESC LIMIT 1";
        SQLiteDatabase db = this.getReadableDatabase(); // accès lecture BDD
        AuteurBean result = new AuteurBean();
        Cursor cursor = db.rawQuery(requete, null); //cursor = résultat de la requête
        if (cursor.moveToFirst()) { // true si il y a des résultats
            result.setAuteur_id(cursor.getInt(0));
        } else {
            // pas de résultats on ne fait rien
        }
        // fermeture db et cursor
        cursor.close();
        db.close();
        return result;
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
    // SELECT * FROM SERIES
    // WHERE SERIE_ID = serie_id
    /* -------------------------------------- */
    public SerieBean selectAllFromSeriesSelonSerieId(int serie_id_voulu){
        SerieBean serieBean = new SerieBean();
        String requete = "SELECT * FROM " + SERIES +
                " WHERE " + SERIES + "." + COLUMN_SERIE_ID + " = \"" + serie_id_voulu + "\" LIMIT 1";
        SQLiteDatabase db = this.getReadableDatabase(); // accès lecture BDD
        Cursor cursor = db.rawQuery(requete, null); //cursor = résultat de la requête
        if (cursor.moveToFirst()) { // true si il y a des résultats
            do { // pour chaque tuple
                int serie_id = cursor.getInt(0);
                String serie_nom = cursor.getString(1);
                serieBean = new SerieBean(serie_id, serie_nom);
            } while (cursor.moveToNext()); //on passe au tuple suivant
        } else {
            // pas de résultats on ne fait rien
        }
        // fermeture db et cursor
        cursor.close();
        db.close();
        return serieBean;
    }

    /* -------------------------------------- */
    // SELECT * FROM SERIES
    // INNER JOIN TOMES ON SERIES.SERIE_ID = TOMES.SERIE_ID
    // WHERE TOMES.TOME_ID = tome_id
    // LIMIT 1
    // 1 seul résultat
    /* -------------------------------------- */
    public SerieBean selectSerieSelonTomeId(int tome_id){
        SerieBean serieBean;
        String requete = "SELECT * FROM " + SERIES +
                " INNER JOIN " + TOMES + " ON " + SERIES + "." + COLUMN_SERIE_ID + " = " + TOMES + "." + COLUMN_SERIE_ID +
                " WHERE " + TOMES + "." + COLUMN_TOME_ID + " = \"" + tome_id + "\" LIMIT 1";
        SQLiteDatabase db = this.getReadableDatabase(); // accès lecture BDD
        Cursor cursor = db.rawQuery(requete, null); //cursor = résultat de la requête
        if (cursor.moveToFirst()) { // true si il y a des résultats
            int serie_id = cursor.getInt(0);
            String serie_nom = cursor.getString(1);
            serieBean = new SerieBean(serie_id, serie_nom);
        } else {
            // pas de résultats
            serieBean = new SerieBean(-1, "Pas de série enregistrée");
        }
        // fermeture db et cursor
        cursor.close();
        db.close();
        return serieBean;
    }

    /* -------------------------------------- */
    // SELECT COUNT (*) FROM SERIES
    // WHERE COLUMN_SERIE_NOM = serie_nom
    /* -------------------------------------- */
    public boolean verifDoublonSerie(String serie_nom){
        SQLiteDatabase db = this.getWritableDatabase(); // accès écriture BDD
        ContentValues cv = new ContentValues(); //stocke des paires clé-valeur
        String requete = "SELECT COUNT(*) FROM " + SERIES + " WHERE " + COLUMN_SERIE_NOM + " = \"" + serie_nom + "\"";
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
    // SELECT * FROM SERIES
    // INNER JOIN TOME ON SERIES.SERIE_ID = TOMES.SERIE_ID
    // WHERE TOMES.TOME_ID = tome_id LIMIT 1
    // 1 seul résultat
    /* -------------------------------------- */
    public EditeurBean selectAllFromSeriesSelonTomeId(int tome_id){
        EditeurBean editeurBean = null;
        String requete = "SELECT * FROM " + SERIES +
                " INNER JOIN " + TOMES + " ON " + SERIES + "." + COLUMN_SERIE_ID + " = " + TOMES + "." + COLUMN_SERIE_ID +
                " WHERE " + TOMES + "." + COLUMN_TOME_ID + " = \"" + tome_id + "\" LIMIT 1";
        SQLiteDatabase db = this.getReadableDatabase(); // accès lecture BDD
        Cursor cursor = db.rawQuery(requete, null); //cursor = résultat de la requête
        if (cursor.moveToFirst()) { // true si il y a des résultats
            int editeur_id = cursor.getInt(0);
            String editeur_nom = cursor.getString(1);
            editeurBean = new EditeurBean(editeur_id, editeur_nom);
        } else {
            // pas de résultats on ne fait rien
        }
        // fermeture db et cursor
        cursor.close();
        db.close();
        return editeurBean;
    }

    /* -------------------------------------- */
    // "SELECT COLUMN_SERIE_ID FROM SERIES
    // WHERE COLUMN_SERIE_NOM = + serieBean.getSerie_nom() +
    // ORDER BY COLUMN_SERIE_ID DESC LIMIT 1"
    /* -------------------------------------- */
    public SerieBean selectFromSeriesDernierAjout(SerieBean serieBean){
        String requete = "SELECT " + COLUMN_SERIE_ID + " FROM " + EDITEURS +
                " WHERE " + COLUMN_SERIE_NOM + " = \"" + serieBean.getSerie_nom() +
                "\" ORDER BY " + COLUMN_SERIE_ID +" DESC LIMIT 1";
        SQLiteDatabase db = this.getReadableDatabase(); // accès lecture BDD
        SerieBean result = new SerieBean();
        Cursor cursor = db.rawQuery(requete, null); //cursor = résultat de la requête
        if (cursor.moveToFirst()) { // true si il y a des résultats
            result.setSerie_id(cursor.getInt(0));
            result.setSerie_nom(cursor.getString(1));
        } else {
            // pas de résultats on ne fait rien
        }
        // fermeture db et cursor
        cursor.close();
        db.close();
        return result;
    }

    /* -------------------------------------- */
    // SELECT DISTINCT * FROM SERIES
    // INNER JOIN TOMES ON SERIES.SERIE_ID = TOMES.SERIE_ID
    // INNER JOIN ECRIRE ON ECRIRE.TOME_ID= TOME.TOME_ID
    // WHERE ECRIRE.AUTEUR_ID = auteur_id
    /* -------------------------------------- */
    public List<SerieBean> selectAllFromSeriesSelonAuteurId(int auteur_id){
        List<SerieBean> returnList = new ArrayList<>();
        String requete = "SELECT DISTINCT * FROM " + SERIES +
                " INNER JOIN " + TOMES + " ON " + SERIES + "." + COLUMN_SERIE_ID + " = " + TOMES + "." + COLUMN_SERIE_ID +
                " INNER JOIN " + ECRIRE + " ON " + TOMES + "." + COLUMN_TOME_ID + " = " + ECRIRE + "." + COLUMN_TOME_ID +
                " WHERE " + ECRIRE + "." + COLUMN_AUTEUR_ID + " = \"" + auteur_id + "\" GROUP BY " + COLUMN_SERIE_NOM;
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
                Integer tome_id = cursor.getInt(0);
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
                Integer serie_id = cursor.getInt(11);
                Integer editeur_id = cursor.getInt(12);

                TomeBean tomeBean = new TomeBean(tome_id, tome_titre, tome_numero, tome_isbn, tome_image,
                        tome_prix_achat, tome_valeur_connue, tome_date_edition, tome_dedicace,
                        tome_edition_speciale, tome_edition_speciale_libelle, serie_id, editeur_id);
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
    // SELECT COUNT (*) FROM TOMES
    // WHERE COLUMN_TOME_TITRE = tome_titre
    /* -------------------------------------- */
    public boolean verifDoublonTome(String tome_titre){
        SQLiteDatabase db = this.getWritableDatabase(); // accès écriture BDD
        ContentValues cv = new ContentValues(); //stocke des paires clé-valeur
        String requete = "SELECT COUNT(*) FROM " + TOMES + " WHERE " + COLUMN_TOME_TITRE + " = \"" + tome_titre + "\"";
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
    // SELECT * FROM TOMES
    // JOIN DETENIR ON TOMES.TOME_ID = DETENIR.TOME_ID
    // WHERE DETENIR.PROFIL_ID = selectAllFromProfilActif().getProfil_id();
    public List<TomeSerieBean> selectAllFromTomesEtSerieSelonProfilId(){
        List<TomeSerieBean> returnList = new ArrayList<>();
        String requete = "SELECT " + TOMES + ".*, " + SERIES + "." + COLUMN_SERIE_NOM + " FROM " + TOMES + ", " + SERIES +
                " INNER JOIN " + DETENIR + " ON " + TOMES + "." + COLUMN_TOME_ID + " = " + DETENIR + "." + COLUMN_TOME_ID +
                " INNER JOIN " + PROFIL_ACTIF + " ON " + PROFIL_ACTIF + "." + COLUMN_PROFIL_ID + " = " + DETENIR + "." + COLUMN_PROFIL_ID +
                " WHERE " + TOMES + "." + COLUMN_SERIE_ID + " = " + SERIES + "." + COLUMN_SERIE_ID +
                " AND " + DETENIR + "." + COLUMN_PROFIL_ID + " = \"" + selectAllFromProfilActif().getProfil_id() +
                "\" AND " + PROFIL_ACTIF + "." + COLUMN_PROFIL_ID + " = 1";
//        String requete = "SELECT * FROM " + TOMES + " ORDER BY " + COLUMN_TOME_TITRE;
        SQLiteDatabase db = this.getReadableDatabase(); // accès lecture BDD
        Cursor cursor = db.rawQuery(requete, null); //cursor = résultat de la requête
        if (cursor.moveToFirst()) { // true si il y a des résultats
            do { // pour chaque tuple
                Integer tome_id = cursor.getInt(0);
                String tome_titre = cursor.getString(1);
                int tome_numero = cursor.getInt(2);
                Integer serie_id = cursor.getInt(11);
                Integer editeur_id = cursor.getInt(12);
                String serie_nom = cursor.getString(13);
                TomeSerieBean tomeSerieBean = new TomeSerieBean(tome_id, tome_titre, tome_numero, null,
                        null, 0, 0, null, false,
                        false, null, serie_id, editeur_id, serie_nom);
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
    // SELECT * FROM TOMES
    // INNER JOIN ECRIRE ON ECRIRE.TOME_ID= TOME.TOME_ID
    // WHERE ECRIRE.AUTEUR_ID = auteur_id
    /* -------------------------------------- */
    public List<TomeSerieBean> selectAllFromTomesEtSerieSelonAuteurId(int auteur_id){
        List<TomeSerieBean> returnList = new ArrayList<>();
        String requete = "SELECT * FROM " + TOMES +
                " INNER JOIN " + ECRIRE +
                " ON " + TOMES + "." + COLUMN_TOME_ID + " = " + ECRIRE + "." + COLUMN_TOME_ID +
                " WHERE " + ECRIRE + "." + COLUMN_AUTEUR_ID + " = \"" + auteur_id + "\"";
        SQLiteDatabase db = this.getReadableDatabase(); // accès lecture BDD
        Cursor cursor = db.rawQuery(requete, null); //cursor = résultat de la requête
        if (cursor.moveToFirst()) { // true si il y a des résultats
            do { // pour chaque tuple
                Integer tome_id = cursor.getInt(0);
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
                Integer serie_id = cursor.getInt(11);
                Integer editeur_id = cursor.getInt(12);

                TomeSerieBean tomeSerieBean = new TomeSerieBean(tome_id, tome_titre, tome_numero, tome_isbn, tome_image,
                        tome_prix_achat, tome_valeur_connue, tome_date_edition, tome_dedicace,
                        tome_edition_speciale, tome_edition_speciale_libelle, serie_id, editeur_id);
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
    // SELECT * FROM TOMES
    // INNER JOIN ECRIRE ON ECRIRE.TOME_ID= TOME.TOME_ID
    // WHERE ECRIRE.AUTEUR_ID = auteur_id
    /* -------------------------------------- */
    public List<TomeBean> selectAllFromTomesSelonAuteurIdSansSerie(int auteur_id){
        List<TomeBean> returnList = new ArrayList<>();
        String requete = "SELECT * FROM " + TOMES +
                " INNER JOIN " + ECRIRE +
                " ON " + TOMES + "." + COLUMN_TOME_ID + " = " + ECRIRE + "." + COLUMN_TOME_ID +
                " WHERE " + ECRIRE + "." + COLUMN_AUTEUR_ID + " = \"" + auteur_id +
                "\" AND " + TOMES + "." + COLUMN_SERIE_ID + " IS NULL";
        SQLiteDatabase db = this.getReadableDatabase(); // accès lecture BDD
        Cursor cursor = db.rawQuery(requete, null); //cursor = résultat de la requête
        if (cursor.moveToFirst()) { // true si il y a des résultats
            do { // pour chaque tuple
                Integer tome_id = cursor.getInt(0);
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
                Integer serie_id = cursor.getInt(11);
                Integer editeur_id = cursor.getInt(12);

                TomeSerieBean tomeSerieBean = new TomeSerieBean(tome_id, tome_titre, tome_numero, tome_isbn, tome_image,
                        tome_prix_achat, tome_valeur_connue, tome_date_edition, tome_dedicace,
                        tome_edition_speciale, tome_edition_speciale_libelle, serie_id, editeur_id);
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
    // "SELECT COLUMN_TOME_ID FROM TOMES
    // WHERE COLUMN_TOME_TITRE = + tomeBean.getTome_titre() +
    // ORDER BY COLUMN_TOME_ID DESC LIMIT 1"
    /* -------------------------------------- */
    public TomeBean selectFromTomesDernierAjout(TomeBean tomeBean){
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
    // SELECT COUNT(*) FROM DETENIR
    // WHERE COLUMN_TOME_ID = selectTOMEIDFromTomesDernierAjout(tomeBean)
    /* -------------------------------------- */
    public boolean selectFromDetenirDernierTomeOk(TomeBean tomeBean){
        SQLiteDatabase db = this.getWritableDatabase(); // accès écriture BDD
        ContentValues cv = new ContentValues(); //stocke des paires clé-valeur

        String requete = "SELECT COUNT(*) FROM " + DETENIR + " WHERE " + COLUMN_TOME_ID + " = \"" + selectFromTomesDernierAjout(tomeBean) + "\"";
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
    // SELECT COUNT (*) FROM ECRIRE
    // WHERE COLUMN_TOME_ID = tome_id
    // AND COLUMN_AUTEUR_ID = auteur_id
    /* -------------------------------------- */
    public boolean verifDoublonTomeAuteur(int tome_id, int auteur_id){
        SQLiteDatabase db = this.getWritableDatabase(); // accès écriture BDD
        ContentValues cv = new ContentValues(); //stocke des paires clé-valeur
        String requete = "SELECT COUNT(*) FROM " + ECRIRE +
                " WHERE " + COLUMN_TOME_ID + " = \"" + tome_id +
                "\" AND " + COLUMN_AUTEUR_ID + " = \"" + auteur_id + "\"";
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
    // SELECT * FROM TOMES
    // WHERE TOME_ID = tome_id
    /* -------------------------------------- */
    public TomeBean selectTomeSelonTome_id(int tome_id_voulu){
        TomeBean tomeBean = new TomeBean();
        String requete = "SELECT * FROM " + TOMES +
                " JOIN " + DETENIR +
                " ON " + TOMES + "." + COLUMN_TOME_ID + " = " + DETENIR + "." + COLUMN_TOME_ID +
                " WHERE " + DETENIR + "." + COLUMN_PROFIL_ID  + " = \"" + selectAllFromProfilActif() +
                "\" AND " + TOMES + "." + COLUMN_TOME_ID + " = \"" + tome_id_voulu + "\" LIMIT 1";
        SQLiteDatabase db = this.getReadableDatabase(); // accès lecture BDD
        Cursor cursor = db.rawQuery(requete, null); //cursor = résultat de la requête
        if (cursor.moveToFirst()) { // true si il y a des résultats
            do { // pour chaque tuple
                Integer tome_id = cursor.getInt(0);
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
                Integer serie_id = cursor.getInt(11);
                Integer editeur_id = cursor.getInt(12);
                tomeBean = new TomeBean(tome_id, tome_titre, tome_numero, tome_isbn,  tome_image, tome_prix_achat, tome_valeur_connue, tome_date_edition, tome_dedicace, tome_edition_speciale, tome_edition_speciale_libelle, serie_id, editeur_id);
            } while (cursor.moveToNext()); //on passe au tuple suivant
        } else {
            // pas de résultats on ne fait rien
        }
        // fermeture db et cursor
        cursor.close();
        db.close();
        return tomeBean;
    }

    /* -------------------------------------- */
    // SELECT * FROM TOMES
    // WHERE TOME_ID = tome_id
    /* -------------------------------------- */
    public List<TomeBean> selectAllFromTomesSelonSerieId(int serie_id_voulu){
        List<TomeBean> returnList = new ArrayList<>();
        TomeBean tomeBean = new TomeBean();
        String requete = "SELECT * FROM " + TOMES +
                " JOIN " + DETENIR +
                " ON " + TOMES + "." + COLUMN_TOME_ID + " = " + DETENIR + "." + COLUMN_TOME_ID +
                " WHERE " + DETENIR + "." + COLUMN_PROFIL_ID  + " = \"" + selectAllFromProfilActif() +
                "\" AND " + TOMES + "." + COLUMN_SERIE_ID + " = \"" + serie_id_voulu + "\"";
        SQLiteDatabase db = this.getReadableDatabase(); // accès lecture BDD
        Cursor cursor = db.rawQuery(requete, null); //cursor = résultat de la requête
        if (cursor.moveToFirst()) { // true si il y a des résultats
            do { // pour chaque tuple
                Integer tome_id = cursor.getInt(0);
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
                Integer serie_id = cursor.getInt(11);
                Integer editeur_id = cursor.getInt(12);
                tomeBean = new TomeBean(tome_id, tome_titre, tome_numero, tome_isbn,  tome_image, tome_prix_achat, tome_valeur_connue, tome_date_edition, tome_dedicace, tome_edition_speciale, tome_edition_speciale_libelle, serie_id, editeur_id);
                returnList.add(tomeBean);
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

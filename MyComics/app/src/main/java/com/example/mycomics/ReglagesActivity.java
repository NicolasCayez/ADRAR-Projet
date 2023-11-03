package com.example.mycomics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mycomics.beans.PopupBean;
import com.example.mycomics.beans.PopupListBean;
import com.example.mycomics.beans.ProfilBean;
import com.example.mycomics.helpers.DataBaseHelper;

public class ReglagesActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    /* -------------------------------------- */
    // Référence vers les éléments de la page
    /* -------------------------------------- */
    //menu Hamburger
    ImageView ivLogoMyComics, ivHamburgLines;
    LinearLayout btnMenuCollection, btnMenuSeries, btnMenuTomes, btnMenuAuteurs, btnMenuEditeurs;
    //Page réglages
    Spinner spinProfil;
    ImageView btnAddProfil, btnDeleteProfil;
    Button btnPopupValider, btnPopupAnnuler;
    EditText etPopupText;
//--------------------------------------------------------------Test a remplacer quand utilisation BDD
    String test;
    ProfilBean profilBeantest = null;

    /* -------------------------------------- */
    // Variable BDD
    /* -------------------------------------- */
    ListView lvPopupListe;
    DataBaseHelper dataBaseHelper;
    ArrayAdapter profilsArrayAdapter;



    /* -------------------------------------- */
    // instance activity
    /* -------------------------------------- */
    private ReglagesActivity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reglages);
        this.activity = this;

        /* -------------------------------------- */
        // Activation fragmentManager
        /* -------------------------------------- */
        FragmentManager fragmentManager = getSupportFragmentManager();

        /* -------------------------------------- */
        // findViewbyId
        /* -------------------------------------- */
        //menu Hamburger
        ivLogoMyComics = findViewById(R.id.ivLogoMyComics);
        ivHamburgLines = findViewById(R.id.ivHamburgLines);
        btnMenuCollection = findViewById(R.id.btnMenuCollection);
        btnMenuSeries = findViewById(R.id.btnMenuSeries);
        btnMenuTomes = findViewById(R.id.btnMenuTomes);
        btnMenuAuteurs = findViewById(R.id.btnMenuAuteurs);
        btnMenuEditeurs = findViewById(R.id.btnMenuEditeurs);
        // Page réglages
        btnAddProfil = findViewById(R.id.btnProfilAdd);
        btnDeleteProfil = findViewById(R.id.btnProfilDelete);
        btnPopupValider = findViewById(R.id.btnPopupValider);
        btnPopupAnnuler = findViewById(R.id.btnPopupAnnuler);
        etPopupText = findViewById(R.id.etPopupText);

        lvPopupListe = findViewById(R.id.lvPopupListe);
        dataBaseHelper = new DataBaseHelper(ReglagesActivity.this);


        /* -------------------------------------- */
        // Clic sur le logo
        /* -------------------------------------- */
        ivLogoMyComics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReglagesActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        /* -------------------------------------- */
        // Clic Menu Hamburger
        /* -------------------------------------- */
        ivHamburgLines.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (findViewById(R.id.fragViewMenu).getVisibility() == View.GONE) {

                    findViewById(R.id.fragViewMenu).setVisibility(View.VISIBLE);
                } else {
                    findViewById(R.id.fragViewMenu).setVisibility(View.GONE);
                }
            }
        }));

        /* -------------------------------------- */
        // Clic Bouton menu Collection
        /* -------------------------------------- */
        btnMenuCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReglagesActivity.this, CollectionActivity.class);
                startActivity(intent);
            }
        });

        /* -------------------------------------- */
        // Clic Bouton menu Series
        /* -------------------------------------- */
        btnMenuSeries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReglagesActivity.this, SeriesActivity.class);
                startActivity(intent);
            }
        });

        /* -------------------------------------- */
        // Clic Bouton menu Tomes
        /* -------------------------------------- */
        btnMenuTomes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReglagesActivity.this, TomesActivity.class);
                startActivity(intent);
            }
        });

        /* -------------------------------------- */
        // Clic Bouton menu Auteurs
        /* -------------------------------------- */
        btnMenuAuteurs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReglagesActivity.this, AuteursActivity.class);
                startActivity(intent);
            }
        });

        /* -------------------------------------- */
        // Clic Bouton menu Editeurs
        /* -------------------------------------- */
        btnMenuEditeurs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReglagesActivity.this, EditeursActivity.class);
                startActivity(intent);
            }
        });

        /* -------------------------------------- */
        // Remplissage liste profils
        /* -------------------------------------- */
        dataBaseHelper = new DataBaseHelper(ReglagesActivity.this);
//        afficherListeProfils(); //spinner


        /* -------------------------------------- */
        // Clic sur bouton AddProfil
        /* -------------------------------------- */
        btnAddProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Création Popup
                PopupBean popupBean = new PopupBean(activity);
                popupBean.setTitre("Entrez un nom de profil");
                popupBean.setHint("Nom de profil");
                System.out.println("clic ajouter profil");
                //Clic bouton Valider
//                afficherListeProfils();
                popupBean.getBtnPopupValider().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        System.out.println("clic Valider nouveau profil");
                        ProfilBean profilBean;
                        try {
                            profilBean = new ProfilBean(-1, popupBean.getEtPopupText().getText().toString());
                        } catch (Exception e) {
                            Toast.makeText(ReglagesActivity.this, "Erreur création profil", Toast.LENGTH_SHORT).show();
                            profilBean = new ProfilBean(-1, "error" );
                        }
                        popupBean.dismiss(); // Fermeture Popup
                        //Appel DataBaseHelper
                        dataBaseHelper = new DataBaseHelper(ReglagesActivity.this);

                        boolean success = dataBaseHelper.insertIntoProfils(profilBean);
                        Toast.makeText(ReglagesActivity.this, "Création profil= " + success, Toast.LENGTH_SHORT).show();
//                        afficherListeProfils();
                    }
                });
                popupBean.build();

            }
        });

        /* -------------------------------------- */
        // Clic sur profil actif pour avoir la liste
        /* -------------------------------------- */
        findViewById(R.id.tvProfilActif).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Création Popup
                PopupListBean popupListBean = new PopupListBean(ReglagesActivity.this);
                popupListBean.setTitre("Choisissez un profil dans la liste");
                System.out.println("clic clic");
                System.out.println(profilsArrayAdapter);
                ListView listView = (ListView) popupListBean.findViewById(R.id.lvPopupListe);
                profilsArrayAdapter = new ArrayAdapter<>(ReglagesActivity.this, android.R.layout.simple_list_item_1, dataBaseHelper.selectAllFromProfils());
                listView.setAdapter(profilsArrayAdapter);

                //Clic bouton Valider
                popupListBean.getLvPopupListe().setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ProfilBean profilBean;
                        try {
                            profilBean = new ProfilBean(-1, popupListBean.getLvPopupListe().getItemAtPosition(position).toString());
                        } catch (Exception e) {
                            Toast.makeText(ReglagesActivity.this, "Erreur création profil", Toast.LENGTH_SHORT).show();
                            profilBean = new ProfilBean(-1, "error" );
                        }
                        popupListBean.dismiss(); // Fermeture Popup
                        //Appel DataBaseHelper
                        dataBaseHelper = new DataBaseHelper(ReglagesActivity.this);
                    }
                });
                popupListBean.Build();
            }
        });

    }

    /* -------------------------------------- */
    // Methode d'affichage de profils dans le popup
    /* -------------------------------------- */
    //textview et popup
    private void afficherProfilsListePopup() {
        profilsArrayAdapter = new ArrayAdapter<>(ReglagesActivity.this, android.R.layout.simple_list_item_1, dataBaseHelper.selectAllFromProfils());
        lvPopupListe.setAdapter(profilsArrayAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }




}
package com.example.mycomics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.DialogInterface;
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
import android.widget.TextView;

import com.example.mycomics.adapters.ProfilsListAdapter;
import com.example.mycomics.popups.PopupAddBean;
import com.example.mycomics.popups.PopupListBean;
import com.example.mycomics.beans.ProfilBean;
import com.example.mycomics.beans.ProfilNomBean;
import com.example.mycomics.helpers.DataBaseHelper;

public class ReglagesActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    /* -------------------------------------- */
    // Référence vers les éléments de la page
    /* -------------------------------------- */
    //menu Hamburger
    ImageView ivLogoMyComics, ivHamburgLines;
    LinearLayout btnMenuCollection, btnMenuSeries, btnMenuTomes, btnMenuAuteurs, btnMenuEditeurs;
    //Page réglages
    ImageView btnAddProfil, btnDeleteProfil;
    Button btnPopupValider, btnPopupAnnuler;
    TextView tvProfilActif;
    EditText etPopupText;

    /* -------------------------------------- */
    // Variable BDD
    /* -------------------------------------- */
    ListView lvPopupListe;
    DataBaseHelper dataBaseHelper;
    ArrayAdapter profilsArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reglages);

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

        tvProfilActif = findViewById(R.id.tvProfilActif);
        lvPopupListe = findViewById(R.id.lvPopupListe);
        dataBaseHelper = new DataBaseHelper(ReglagesActivity.this);

        /* -------------------------------------- */
        // Initialisation affichage
        /* -------------------------------------- */
        afficherProfilActif();

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
        // Clic sur bouton AddProfil
        /* -------------------------------------- */
        btnAddProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Création Popup
                PopupAddBean popupAddBean = new PopupAddBean(ReglagesActivity.this);
                popupAddBean.setTitre("Entrez un nom de profil");
                popupAddBean.setHint("Nom de profil");
                popupAddBean.getBtnPopupValider().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ProfilNomBean profilBean;
                        try {
                            profilBean = new ProfilNomBean(-1, popupAddBean.getEtPopupText().getText().toString());
                        } catch (Exception e) {
//                            Toast.makeText(ReglagesActivity.this, "Erreur création profil", Toast.LENGTH_SHORT).show();
                            profilBean = new ProfilNomBean(-1, "error" );
                        }
                        popupAddBean.dismiss(); // Fermeture Popup
                        //Appel DataBaseHelper
                        dataBaseHelper = new DataBaseHelper(ReglagesActivity.this);

                        boolean success = dataBaseHelper.insertIntoProfils(profilBean);
//                        afficherListeProfils();
                    }
                });
                popupAddBean.build();
                afficherProfilActif();
            }

        });

        /* -------------------------------------- */
        // Clic sur profil actif pour avoir la liste
        /* -------------------------------------- */
        tvProfilActif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Création Popup
                PopupListBean popupListBean = new PopupListBean(ReglagesActivity.this);
                popupListBean.setTitre("Choisissez un profil dans la liste");
                ListView listView = (ListView) popupListBean.findViewById(R.id.lvPopupListe);
//                profilsArrayAdapter = new ArrayAdapter<>(ReglagesActivity.this, android.R.layout.simple_list_item_1, dataBaseHelper.selectAllFromProfilsNomSeul());
                profilsArrayAdapter = new ProfilsListAdapter(ReglagesActivity.this , R.layout.listview_template , dataBaseHelper.selectAllFromProfilsNomSeul());
                listView.setAdapter(profilsArrayAdapter);
                //Clic Profil choisi pour modification
                popupListBean.getLvPopupListe().setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ProfilBean profilBean;
                        try {
                            profilBean = (ProfilBean) popupListBean.getLvPopupListe().getItemAtPosition(position);
                            dataBaseHelper.updateProfilActif(dataBaseHelper, ((ProfilBean) popupListBean.getLvPopupListe().getItemAtPosition(position)).getProfil_id());
                        } catch (Exception e) {
                            profilBean = new ProfilBean(-1, "error" );
                        }
                        popupListBean.dismiss(); // Fermeture Popup
                        //Appel DataBaseHelper
                        dataBaseHelper = new DataBaseHelper(ReglagesActivity.this);
                    }
                });
                popupListBean.Build();
                popupListBean.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        afficherProfilActif();
                    }
                });
            }
        });
    }

    /* -------------------------------------- */
    // Methode d'affichage dans le TextViev
    /* -------------------------------------- */
    private void afficherProfilActif() {
        try {
            System.out.println("test: " + dataBaseHelper.selectFromProfilProfilActif().getProfil_nom());
            tvProfilActif.setText(dataBaseHelper.selectFromProfilProfilActif().getProfil_nom());
        } catch (Exception e) {

        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }




}
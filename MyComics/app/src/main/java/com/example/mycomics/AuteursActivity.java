package com.example.mycomics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.mycomics.adapters.AuteursListAdapter;
import com.example.mycomics.beans.AuteurBean;
import com.example.mycomics.beans.AuteurPseudoBean;
import com.example.mycomics.helpers.DataBaseHelper;
import com.example.mycomics.popups.PopupAddBean;

public class AuteursActivity extends AppCompatActivity {
    /* -------------------------------------- */
    // Référence vers les éléments de la page
    /* -------------------------------------- */
    //menu Hamburger
    ImageView ivLogoMyComics, ivHamburgLines;
    LinearLayout btnMenuCollection, btnMenuSeries, btnMenuTomes, btnMenuAuteurs, btnMenuEditeurs;
    //Page Auteurs
    ImageView btnAddAuteurs;
    ListView lvAuteursListe;

    /* -------------------------------------- */
    // Variable BDD
    /* -------------------------------------- */
    DataBaseHelper dataBaseHelper;
    ArrayAdapter auteursArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auteurs);
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
        // Page Editeurs
        btnAddAuteurs = findViewById(R.id.btnAddAuteurs);
        lvAuteursListe = findViewById(R.id.lvAuteursListe);

        dataBaseHelper = new DataBaseHelper(AuteursActivity.this);

        /* -------------------------------------- */
        // Initialisation affichage
        /* -------------------------------------- */
        afficherListeAuteurs();

        /* -------------------------------------- */
        // Clic sur le logo
        /* -------------------------------------- */
        findViewById(R.id.ivLogoMyComics).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AuteursActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        /* -------------------------------------- */
        // Clic Menu Hamburger
        /* -------------------------------------- */
        findViewById(R.id.ivHamburgLines).setOnClickListener((new View.OnClickListener() {
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
        findViewById(R.id.btnMenuCollection).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AuteursActivity.this, CollectionActivity.class);
                startActivity(intent);
            }
        });

        /* -------------------------------------- */
        // Clic Bouton menu Series
        /* -------------------------------------- */
        findViewById(R.id.btnMenuSeries).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AuteursActivity.this, SeriesActivity.class);
                startActivity(intent);
            }
        });

        /* -------------------------------------- */
        // Clic Bouton menu Tomes
        /* -------------------------------------- */
        findViewById(R.id.btnMenuTomes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AuteursActivity.this, TomesActivity.class);
                startActivity(intent);
            }
        });

        /* -------------------------------------- */
        // Clic Bouton menu Editeurs
        /* -------------------------------------- */
        findViewById(R.id.btnMenuEditeurs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AuteursActivity.this, EditeursActivity.class);
                startActivity(intent);
            }
        });

        /* -------------------------------------- */
        // Clic Bouton menu Reglages
        /* -------------------------------------- */
        findViewById(R.id.btnMenuReglages).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AuteursActivity.this, ReglagesActivity.class);
                startActivity(intent);
            }
        });
        /* -------------------------------------- */
        // clic searchBar
        /* -------------------------------------- */
        SearchView searchView = findViewById(R.id.svSearch);
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Active le clic sur toute la zone de la searchBar
                searchView.setIconified(false);
            }
        });
        /* -------------------------------------- */
        // Clic Ajout Auteur
        /* -------------------------------------- */
        findViewById(R.id.btnAddAuteurs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AuteursActivity.this, AuteursDetailActivity.class);
                startActivity(intent);
            }
        });
        /* -------------------------------------- */
        // Clic Bouton ajout auteur
        /* -------------------------------------- */
        btnAddAuteurs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Création Popup
                PopupAddBean popupAddBean = new PopupAddBean(AuteursActivity.this);
                popupAddBean.setTitre("Entrez le Pseudonyme (ou le Nom) de l'auteur");
                popupAddBean.setHint("Pseudonyme de l'auteur");
                popupAddBean.getBtnPopupValider().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AuteurBean auteurBean;
                        try {
                            auteurBean = new AuteurBean(-1, popupAddBean.getEtPopupText().getText().toString());
                        } catch (Exception e) {
//                            Toast.makeText(ReglagesActivity.this, "Erreur création auteur", Toast.LENGTH_SHORT).show();
                            auteurBean = new AuteurBean(-1, "error" );
                        }
                        popupAddBean.dismiss(); // Fermeture Popup
                        //Appel DataBaseHelper
                        dataBaseHelper = new DataBaseHelper(AuteursActivity.this);

                        boolean success = dataBaseHelper.insertIntoAuteurs(auteurBean);
                        afficherListeAuteurs();
                    }
                });
                popupAddBean.build();
                afficherListeAuteurs();
            }
        });

        /* -------------------------------------- */
        // Clic Element liste Auteur
        /* -------------------------------------- */
        lvAuteursListe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AuteurBean auteurBean;
                try {
                    auteurBean = (AuteurBean) lvAuteursListe.getItemAtPosition(position);
                } catch (Exception e) {
                    auteurBean = new AuteurPseudoBean(-1,"error","error","error","error");
                }
                Intent intent = new Intent(AuteursActivity.this, AuteursDetailActivity.class);
                intent.putExtra("auteur_id",auteurBean.getAuteur_id());
                intent.putExtra("auteur_nom",auteurBean.getAuteur_nom());
                intent.putExtra("auteur_prenom",auteurBean.getAuteur_prenom());
                intent.putExtra("auteur_pseudo",auteurBean.getAuteur_pseudo());
                intent.putExtra("auteur_photo",auteurBean.getAuteur_photo());
                startActivity(intent);
            }
        });

    }

    private void afficherListeAuteurs(){
        ListView listView = (ListView) lvAuteursListe;
        auteursArrayAdapter = new AuteursListAdapter(AuteursActivity.this , R.layout.listview_template , dataBaseHelper.selectAllFromAuteursPseudoSeul());
        listView.setAdapter(auteursArrayAdapter);
    }
}
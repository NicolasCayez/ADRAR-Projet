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

import com.example.mycomics.adapters.EditeursListAdapter;
import com.example.mycomics.beans.EditeurBean;
import com.example.mycomics.popups.PopupAddBean;
import com.example.mycomics.helpers.DataBaseHelper;

public class EditeursActivity extends AppCompatActivity {
    /* -------------------------------------- */
    // Référence vers les éléments de la page
    /* -------------------------------------- */
    //menu Hamburger
    ImageView ivLogoMyComics, ivHamburgLines;
    LinearLayout btnMenuCollection, btnMenuSeries, btnMenuTomes, btnMenuAuteurs, btnMenuEditeurs;
    //Page Editeurs
    ImageView btnAddEditeurs;
    ListView lvEditeursListe;

    /* -------------------------------------- */
    // Variable BDD
    /* -------------------------------------- */
    DataBaseHelper dataBaseHelper;
    ArrayAdapter editeursArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editeurs);
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
        btnAddEditeurs = findViewById(R.id.btnAddEditeurs);
        lvEditeursListe = findViewById(R.id.lvEditeursListe);

        dataBaseHelper = new DataBaseHelper(EditeursActivity.this);

        /* -------------------------------------- */
        // Initialisation affichage
        /* -------------------------------------- */
        afficherListeEditeurs();

        /* -------------------------------------- */
        // Clic sur le logo
        /* -------------------------------------- */
        findViewById(R.id.ivLogoMyComics).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditeursActivity.this, MainActivity.class);
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
                Intent intent = new Intent(EditeursActivity.this, CollectionActivity.class);
                startActivity(intent);
            }
        });

        /* -------------------------------------- */
        // Clic Bouton menu Series
        /* -------------------------------------- */
        findViewById(R.id.btnMenuSeries).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditeursActivity.this, SeriesActivity.class);
                startActivity(intent);
            }
        });

        /* -------------------------------------- */
        // Clic Bouton menu Tomes
        /* -------------------------------------- */
        findViewById(R.id.btnMenuTomes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditeursActivity.this, TomesActivity.class);
                startActivity(intent);
            }
        });

        /* -------------------------------------- */
        // Clic Bouton menu Auteurs
        /* -------------------------------------- */
        findViewById(R.id.btnMenuAuteurs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditeursActivity.this, AuteursActivity.class);
                startActivity(intent);
            }
        });

        /* -------------------------------------- */
        // Clic Bouton menu Reglages
        /* -------------------------------------- */
        findViewById(R.id.btnMenuReglages).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditeursActivity.this, ReglagesActivity.class);
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
        // Clic Bouton ajout éditeur
        /* -------------------------------------- */
        btnAddEditeurs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Création Popup
                PopupAddBean popupAddBean = new PopupAddBean(EditeursActivity.this);
                popupAddBean.setTitre("Entrez le nom de l'éditeur");
                popupAddBean.setHint("Nom de l'éditeur");
                popupAddBean.getBtnPopupValider().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditeurBean editeurBean;
                        try {
                            editeurBean = new EditeurBean(-1, popupAddBean.getEtPopupText().getText().toString());
                        } catch (Exception e) {
//                            Toast.makeText(ReglagesActivity.this, "Erreur création éditeur", Toast.LENGTH_SHORT).show();
                            editeurBean = new EditeurBean(-1, "error" );
                        }
                        popupAddBean.dismiss(); // Fermeture Popup
                        //Appel DataBaseHelper
                        dataBaseHelper = new DataBaseHelper(EditeursActivity.this);

                        boolean success = dataBaseHelper.insertIntoEditeurs(editeurBean);
                        afficherListeEditeurs();
                    }
                });
                popupAddBean.build();
                afficherListeEditeurs();
            }
        });

        /* -------------------------------------- */
        // Clic Element liste Editeur
        /* -------------------------------------- */
        lvEditeursListe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EditeurBean editeurBean;
                try {
                    editeurBean = (EditeurBean) lvEditeursListe.getItemAtPosition(position);
                } catch (Exception e) {
                    editeurBean = new EditeurBean(-1,"error");
                }
                Intent intent = new Intent(EditeursActivity.this, EditeursDetailActivity.class);
                intent.putExtra("editeur_id",editeurBean.getEditeur_id());
                intent.putExtra("editeur_nom",editeurBean.getEditeur_nom());
                startActivity(intent);
            }
        });
    }

    private void afficherListeEditeurs(){
        ListView listView = (ListView) lvEditeursListe;
        editeursArrayAdapter = new EditeursListAdapter(EditeursActivity.this , R.layout.listview_template , dataBaseHelper.selectAllFromEditeursNomSeul());
        listView.setAdapter(editeursArrayAdapter);
    }
}
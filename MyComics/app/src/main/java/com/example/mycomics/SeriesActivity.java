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
import com.example.mycomics.adapters.SeriesListAdapter;
import com.example.mycomics.beans.EditeurBean;
import com.example.mycomics.beans.SerieBean;
import com.example.mycomics.helpers.DataBaseHelper;
import com.example.mycomics.popups.PopupAddBean;

public class SeriesActivity extends AppCompatActivity {
    /* -------------------------------------- */
    // Référence vers les éléments de la page
    /* -------------------------------------- */
    //menu Hamburger
    ImageView ivLogoMyComics, ivHamburgLines;
    LinearLayout btnMenuCollection, btnMenuSeries, btnMenuTomes, btnMenuAuteurs, btnMenuEditeurs;
    //Page Series
    ImageView btnAddSeries;
    ListView lvSeriesListe;

    /* -------------------------------------- */
    // Variable BDD
    /* -------------------------------------- */
    DataBaseHelper dataBaseHelper;
    ArrayAdapter seriesArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series);
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
        btnAddSeries = findViewById(R.id.btnAddSeries);
        lvSeriesListe = findViewById(R.id.lvSeriesListe);

        dataBaseHelper = new DataBaseHelper(SeriesActivity.this);

        /* -------------------------------------- */
        // Initialisation affichage
        /* -------------------------------------- */
        afficherListeSeries();

        /* -------------------------------------- */
        // Clic sur le logo
        /* -------------------------------------- */
        findViewById(R.id.ivLogoMyComics).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SeriesActivity.this, MainActivity.class);
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
                Intent intent = new Intent(SeriesActivity.this, CollectionActivity.class);
                startActivity(intent);
            }
        });

        /* -------------------------------------- */
        // Clic Bouton menu Tomes
        /* -------------------------------------- */
        findViewById(R.id.btnMenuTomes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SeriesActivity.this, TomesActivity.class);
                startActivity(intent);
            }
        });

        /* -------------------------------------- */
        // Clic Bouton menu Auteurs
        /* -------------------------------------- */
        findViewById(R.id.btnMenuAuteurs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SeriesActivity.this, AuteursActivity.class);
                startActivity(intent);
            }
        });

        /* -------------------------------------- */
        // Clic Bouton menu Editeurs
        /* -------------------------------------- */
        findViewById(R.id.btnMenuEditeurs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SeriesActivity.this, EditeursActivity.class);
                startActivity(intent);
            }
        });

        /* -------------------------------------- */
        // Clic Bouton menu Reglages
        /* -------------------------------------- */
        findViewById(R.id.btnMenuReglages).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SeriesActivity.this, ReglagesActivity.class);
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
        // Clic Bouton ajout série
        /* -------------------------------------- */
        btnAddSeries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Création Popup
                PopupAddBean popupAddBean = new PopupAddBean(SeriesActivity.this);
                popupAddBean.setTitre("Entrez le nom de la série");
                popupAddBean.setHint("Nom de la série");
                popupAddBean.getBtnPopupValider().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SerieBean serieBean;
                        try {
                            serieBean = new SerieBean(-1, popupAddBean.getEtPopupText().getText().toString());
                        } catch (Exception e) {
//                            Toast.makeText(ReglagesActivity.this, "Erreur création série", Toast.LENGTH_SHORT).show();
                            serieBean = new SerieBean(-1, "error" );
                        }
                        popupAddBean.dismiss(); // Fermeture Popup
                        //Appel DataBaseHelper
                        dataBaseHelper = new DataBaseHelper(SeriesActivity.this);

                        boolean success = dataBaseHelper.insertIntoSeries(serieBean);
                        afficherListeSeries();
                    }
                });
                popupAddBean.build();
                afficherListeSeries();
            }
        });

        /* -------------------------------------- */
        // Clic Element liste Serie
        /* -------------------------------------- */
        lvSeriesListe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SerieBean serieBean;
                try {
                    serieBean = (SerieBean) lvSeriesListe.getItemAtPosition(position);
                } catch (Exception e) {
                    serieBean = new SerieBean(-1,"error");
                }
                Intent intent = new Intent(SeriesActivity.this, SeriesDetailActivity.class);
                intent.putExtra("serie_id",serieBean.getSerie_id());
                intent.putExtra("serie_nom",serieBean.getSerie_nom());
                startActivity(intent);
            }
        });
    }

    private void afficherListeSeries(){
        ListView listView = (ListView) lvSeriesListe;
        seriesArrayAdapter = new SeriesListAdapter(SeriesActivity.this , R.layout.listview_template , dataBaseHelper.selectAllFromSeriesNomSeul());
        listView.setAdapter(seriesArrayAdapter);
    }
}
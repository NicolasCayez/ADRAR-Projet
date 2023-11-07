package com.example.mycomics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import com.example.mycomics.adapters.SeriesListAdapter;
import com.example.mycomics.beans.SerieBean;
import com.example.mycomics.databinding.ActivitySeriesBinding;
import com.example.mycomics.helpers.DataBaseHelper;
import com.example.mycomics.popups.PopupAddDialog;

public class SeriesActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivitySeriesBinding binding = null;
    /* -------------------------------------- */
    // Référence vers les éléments de la page
    /* -------------------------------------- */
    //menu Hamburger
    LinearLayout btnMenuCollection, btnMenuSeries, btnMenuTomes, btnMenuAuteurs, btnMenuEditeurs;
    //Page Series

    /* -------------------------------------- */
    // Variable BDD
    /* -------------------------------------- */
    DataBaseHelper dataBaseHelper;
    ArrayAdapter seriesArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_series);
        binding = ActivitySeriesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        /* -------------------------------------- */
        // Activation fragmentManager
        /* -------------------------------------- */
        FragmentManager fragmentManager = getSupportFragmentManager();

        /* -------------------------------------- */
        // findViewbyId
        /* -------------------------------------- */
        //menu Hamburger
        btnMenuCollection = findViewById(R.id.btnMenuCollection);
        btnMenuSeries = findViewById(R.id.btnMenuSeries);
        btnMenuTomes = findViewById(R.id.btnMenuTomes);
        btnMenuAuteurs = findViewById(R.id.btnMenuAuteurs);
        btnMenuEditeurs = findViewById(R.id.btnMenuEditeurs);
        /* -------------------------------------- */
        // Initialisation Base de données
        /* -------------------------------------- */
        dataBaseHelper = new DataBaseHelper(SeriesActivity.this);

        /* -------------------------------------- */
        // Initialisation affichage
        /* -------------------------------------- */
        afficherListeSeries();

        /* -------------------------------------- */
        // Clic sur le logo
        /* -------------------------------------- */
        binding.tbMenu.ivLogoMyComics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SeriesActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        /* -------------------------------------- */
        // Clic Menu Hamburger
        /* -------------------------------------- */
        binding.tbMenu.ivHamburgLines.setOnClickListener((new View.OnClickListener() {
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
        binding.sbSearch.svSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Active le clic sur toute la zone de la searchBar
                binding.sbSearch.svSearch.setIconified(false);
            }
        });
        /* -------------------------------------- */
        // Clic Bouton ajout série
        /* -------------------------------------- */
        binding.btnAddSeries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Création Popup
                PopupAddDialog popupAddDialog = new PopupAddDialog(SeriesActivity.this);
                popupAddDialog.setTitre("Entrez le nom de la série");
                popupAddDialog.setHint("Nom de la série");
                popupAddDialog.getBtnPopupValider().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SerieBean serieBean;
                        try {
                            serieBean = new SerieBean(-1, popupAddDialog.getEtPopupText().getText().toString());
                        } catch (Exception e) {
//                            Toast.makeText(ReglagesActivity.this, "Erreur création série", Toast.LENGTH_SHORT).show();
                            serieBean = new SerieBean(-1, "error" );
                        }
                        popupAddDialog.dismiss(); // Fermeture Popup
                        //Appel DataBaseHelper
                        dataBaseHelper = new DataBaseHelper(SeriesActivity.this);

                        boolean success = dataBaseHelper.insertIntoSeries(serieBean);
                        afficherListeSeries();
                    }
                });
                popupAddDialog.build();
                afficherListeSeries();
            }
        });

        /* -------------------------------------- */
        // Clic Element liste Serie
        /* -------------------------------------- */
        binding.lvSeriesListe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SerieBean serieBean;
                try {
                    serieBean = (SerieBean) binding.lvSeriesListe.getItemAtPosition(position);
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
        seriesArrayAdapter = new SeriesListAdapter(SeriesActivity.this , R.layout.listview_template , dataBaseHelper.selectAllFromSeriesNomSeul());
        binding.lvSeriesListe.setAdapter(seriesArrayAdapter);
    }

    @Override
    public void onClick(View v) {

    }
}
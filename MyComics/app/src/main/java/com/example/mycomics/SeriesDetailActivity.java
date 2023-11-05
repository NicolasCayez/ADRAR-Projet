package com.example.mycomics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mycomics.helpers.DataBaseHelper;

public class SeriesDetailActivity extends AppCompatActivity {
    /* -------------------------------------- */
    // Référence vers les éléments de la page
    /* -------------------------------------- */
    //menu Hamburger
    ImageView ivLogoMyComics, ivHamburgLines;
    LinearLayout btnMenuCollection, btnMenuSeries, btnMenuTomes, btnMenuAuteurs, btnMenuEditeurs;
    //Page Detail Editeur
    TextView tvSerieDetailNom;

    /* -------------------------------------- */
    // Variable BDD
    /* -------------------------------------- */
    DataBaseHelper dataBaseHelper;
    ArrayAdapter seriesArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series_detail);
        /* -------------------------------------- */
        // Récupération données
        /* -------------------------------------- */
        Intent intentAvecDonnees = getIntent();
        // id : intentAvecDonnees.getIntExtra("serie_id",0)
        // nom : intentAvecDonnees.getStringExtra("serie_nom")
        /* -------------------------------------- */
        // findViewById
        /* -------------------------------------- */
        tvSerieDetailNom = findViewById(R.id.tvSerieDetailNom);
        /* -------------------------------------- */
        // Activation fragmentManager
        /* -------------------------------------- */
        FragmentManager fragmentManager = getSupportFragmentManager();


        /* -------------------------------------- */
        // Clic sur le logo
        /* -------------------------------------- */
        findViewById(R.id.ivLogoMyComics).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SeriesDetailActivity.this, MainActivity.class);
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
                Intent intent = new Intent(SeriesDetailActivity.this, CollectionActivity.class);
                startActivity(intent);
            }
        });

        /* -------------------------------------- */
        // Clic Bouton menu Series
        /* -------------------------------------- */
        findViewById(R.id.btnMenuSeries).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SeriesDetailActivity.this, SeriesActivity.class);
                startActivity(intent);
            }
        });

        /* -------------------------------------- */
        // Clic Bouton menu Tomes
        /* -------------------------------------- */
        findViewById(R.id.btnMenuTomes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SeriesDetailActivity.this, TomesActivity.class);
                startActivity(intent);
            }
        });

        /* -------------------------------------- */
        // Clic Bouton menu Auteurs
        /* -------------------------------------- */
        findViewById(R.id.btnMenuAuteurs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SeriesDetailActivity.this, AuteursActivity.class);
                startActivity(intent);
            }
        });

        /* -------------------------------------- */
        // Clic Bouton menu Editeurs
        /* -------------------------------------- */
        findViewById(R.id.btnMenuEditeurs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SeriesDetailActivity.this, EditeursActivity.class);
                startActivity(intent);
            }
        });

        /* -------------------------------------- */
        // Clic Bouton menu Reglages
        /* -------------------------------------- */
        findViewById(R.id.btnMenuReglages).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SeriesDetailActivity.this, ReglagesActivity.class);
                startActivity(intent);
            }
        });

        /* -------------------------------------- */
        // Initialisation Nom fiche
        /* -------------------------------------- */
        tvSerieDetailNom.setText(intentAvecDonnees.getStringExtra("serie_nom"));
        /* -------------------------------------- */
        // Clic Liste Détail Tomes *************************************************** A revoir avec BDD
        /* -------------------------------------- */
//        findViewById(R.id.lvSeriesDetailTomes).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(SeriesDetailActivity.this, TomesDetailActivity.class);
//                startActivity(intent);
//            }
//        });

        /* -------------------------------------- */
        // Clic Liste Détail Editeurs *************************************************** A revoir avec BDD
        /* -------------------------------------- */
//        findViewById(R.id.lvSeriesDetailEditeurs).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(SeriesDetailActivity.this, EditeursDetailActivity.class);
//                startActivity(intent);
//            }
//        });

        /* -------------------------------------- */
        // Clic Liste Détail Auteurs *************************************************** A revoir avec BDD
        /* -------------------------------------- */
//        findViewById(R.id.lvSeriesDetailAuteurs).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(SeriesDetailActivity.this, AuteursDetailActivity.class);
//                startActivity(intent);
//            }
//        });
    }
}
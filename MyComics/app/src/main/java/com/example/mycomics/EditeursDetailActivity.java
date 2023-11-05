package com.example.mycomics;

import android.content.Intent;
import android.os.Bundle;

import com.example.mycomics.helpers.DataBaseHelper;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.FragmentManager;

public class EditeursDetailActivity extends AppCompatActivity {
    /* -------------------------------------- */
    // Référence vers les éléments de la page
    /* -------------------------------------- */
    //menu Hamburger
    ImageView ivLogoMyComics, ivHamburgLines;
    LinearLayout btnMenuCollection, btnMenuSeries, btnMenuTomes, btnMenuAuteurs, btnMenuEditeurs;
    //Page Detail Editeur
    TextView tvEditeurDetailNom;

    /* -------------------------------------- */
    // Variable BDD
    /* -------------------------------------- */
    DataBaseHelper dataBaseHelper;
    ArrayAdapter editeursArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editeurs_detail);
        /* -------------------------------------- */
        // Récupération données
        /* -------------------------------------- */
        Intent intentAvecDonnees = getIntent();
        // id : intentAvecDonnees.getIntExtra("editeur_id",0)
        // nom : intentAvecDonnees.getStringExtra("editeur_nom")
        /* -------------------------------------- */
        // findViewById
        /* -------------------------------------- */
        tvEditeurDetailNom = findViewById(R.id.tvEditeurDetailNom);

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
                Intent intent = new Intent(EditeursDetailActivity.this, MainActivity.class);
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
                Intent intent = new Intent(EditeursDetailActivity.this, CollectionActivity.class);
                startActivity(intent);
            }
        });

        /* -------------------------------------- */
        // Clic Bouton menu Series
        /* -------------------------------------- */
        findViewById(R.id.btnMenuSeries).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditeursDetailActivity.this, SeriesActivity.class);
                startActivity(intent);
            }
        });

        /* -------------------------------------- */
        // Clic Bouton menu Tomes
        /* -------------------------------------- */
        findViewById(R.id.btnMenuTomes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditeursDetailActivity.this, TomesActivity.class);
                startActivity(intent);
            }
        });

        /* -------------------------------------- */
        // Clic Bouton menu Auteurs
        /* -------------------------------------- */
        findViewById(R.id.btnMenuAuteurs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditeursDetailActivity.this, AuteursActivity.class);
                startActivity(intent);
            }
        });

        /* -------------------------------------- */
        // Clic Bouton menu Editeurs
        /* -------------------------------------- */
        findViewById(R.id.btnMenuEditeurs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditeursDetailActivity.this, EditeursActivity.class);
                startActivity(intent);
            }
        });

        /* -------------------------------------- */
        // Clic Bouton menu Reglages
        /* -------------------------------------- */
        findViewById(R.id.btnMenuReglages).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditeursDetailActivity.this, ReglagesActivity.class);
                startActivity(intent);
            }
        });

        /* -------------------------------------- */
        // Initialisation Nom fiche
        /* -------------------------------------- */
        tvEditeurDetailNom.setText(intentAvecDonnees.getStringExtra("editeur_nom"));
        /* -------------------------------------- */
        // Clic Liste Détail Séries *************************************************** A revoir avec BDD
        /* -------------------------------------- */
//        findViewById(R.id.lvEditeursDetailSéries).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(EditeursDetailActivity.this, SeriesDetailActivity.class);
//                startActivity(intent);
//            }
//        });

        /* -------------------------------------- */
        // Clic Liste Détail Tomes *************************************************** A revoir avec BDD
        /* -------------------------------------- */
//        findViewById(R.id.lvEditeursDetailTomes).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(EditeursDetailActivity.this, TomesDetailActivity.class);
//                startActivity(intent);
//            }
//        });

        /* -------------------------------------- */
        // Clic Liste Détail Auteurs *************************************************** A revoir avec BDD
        /* -------------------------------------- */
//        findViewById(R.id.lvEditeursDetailAuteurs).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(EditeursDetailActivity.this, AuteursDetailActivity.class);
//                startActivity(intent);
//            }
//        });

    }

}
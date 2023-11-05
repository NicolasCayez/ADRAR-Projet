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

public class AuteursDetailActivity extends AppCompatActivity {
    /* -------------------------------------- */
    // Référence vers les éléments de la page
    /* -------------------------------------- */
    //menu Hamburger
    ImageView ivLogoMyComics, ivHamburgLines;
    LinearLayout btnMenuCollection, btnMenuSeries, btnMenuTomes, btnMenuAuteurs, btnMenuEditeurs;
    //Page Detail Auteur
    TextView tvAuteurDetailPseudo;

    /* -------------------------------------- */
    // Variable BDD
    /* -------------------------------------- */
    DataBaseHelper dataBaseHelper;
    ArrayAdapter auteursArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auteurs_detail);
        /* -------------------------------------- */
        // Récupération données
        /* -------------------------------------- */
        Intent intentAvecDonnees = getIntent();
        // id : intentAvecDonnees.getIntExtra("auteur_id",0)
        // nom : intentAvecDonnees.getStringExtra("auteur_nom")
        // prenom : intentAvecDonnees.getStringExtra("auteur_prenom")
        // pseudo : intentAvecDonnees.getStringExtra("auteur_pseudo")
        // photo : intentAvecDonnees.getStringExtra("auteur_photo")

        /* -------------------------------------- */
        // findViewById
        /* -------------------------------------- */
        tvAuteurDetailPseudo = findViewById(R.id.tvAuteurDetailPseudo);

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
                Intent intent = new Intent(AuteursDetailActivity.this, MainActivity.class);
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
                Intent intent = new Intent(AuteursDetailActivity.this, CollectionActivity.class);
                startActivity(intent);
            }
        });

        /* -------------------------------------- */
        // Clic Bouton menu Series
        /* -------------------------------------- */
        findViewById(R.id.btnMenuSeries).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AuteursDetailActivity.this, SeriesActivity.class);
                startActivity(intent);
            }
        });

        /* -------------------------------------- */
        // Clic Bouton menu Tomes
        /* -------------------------------------- */
        findViewById(R.id.btnMenuTomes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AuteursDetailActivity.this, TomesActivity.class);
                startActivity(intent);
            }
        });

        /* -------------------------------------- */
        // Clic Bouton menu Auteurs
        /* -------------------------------------- */
        findViewById(R.id.btnMenuAuteurs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AuteursDetailActivity.this, AuteursActivity.class);
                startActivity(intent);
            }
        });

        /* -------------------------------------- */
        // Clic Bouton menu Editeurs
        /* -------------------------------------- */
        findViewById(R.id.btnMenuEditeurs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AuteursDetailActivity.this, EditeursActivity.class);
                startActivity(intent);
            }
        });

        /* -------------------------------------- */
        // Clic Bouton menu Reglages
        /* -------------------------------------- */
        findViewById(R.id.btnMenuReglages).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AuteursDetailActivity.this, ReglagesActivity.class);
                startActivity(intent);
            }
        });

        /* -------------------------------------- */
        // Initialisation Nom fiche
        /* -------------------------------------- */
        tvAuteurDetailPseudo.setText(intentAvecDonnees.getStringExtra("auteur_pseudo"));

        /* -------------------------------------- */
        // Clic Liste Détail Séries *************************************************** A revoir avec BDD
        /* -------------------------------------- */
//        findViewById(R.id.lvAuteursDetailSeries).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(AuteursDetailActivity.this, SeriesDetailActivity.class);
//                startActivity(intent);
//            }
//        });

        /* -------------------------------------- */
        // Clic Liste Détail Tomes *************************************************** A revoir avec BDD
        /* -------------------------------------- */
//        findViewById(R.id.lvAuteursDetailTomes).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(AuteursDetailActivity.this, TomesDetailActivity.class);
//                startActivity(intent);
//            }
//        });

        /* -------------------------------------- */
        // Clic Liste Détail Editeurs *************************************************** A revoir avec BDD
        /* -------------------------------------- */
//        findViewById(R.id.lvAuteursDetailEditeurs).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(AuteursDetailActivity.this, EditeursDetailActivity.class);
//                startActivity(intent);
//            }
//        });

        /* -------------------------------------- */
        // Clic Liste Détail Auteurs *************************************************** A revoir avec BDD
        /* -------------------------------------- */
//        findViewById(R.id.lvAuteursDetailAuteurs).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(AuteursDetailActivity.this, AuteursDetailActivity.class);
//                startActivity(intent);
//            }
//        });

    }
}
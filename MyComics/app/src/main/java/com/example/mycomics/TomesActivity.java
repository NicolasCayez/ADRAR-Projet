package com.example.mycomics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import com.example.mycomics.adapters.TomesListAdapter;
import com.example.mycomics.beans.TomeBean;
import com.example.mycomics.databinding.ActivityTomesBinding;
import com.example.mycomics.helpers.DataBaseHelper;
import com.example.mycomics.popups.PopupAddDialog;

public class TomesActivity extends AppCompatActivity implements View.OnClickListener {
//    private ActivityTomesBinding binding = null;

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
    ArrayAdapter tomesArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_tomes);
//        binding = ActivityTomesBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
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
        dataBaseHelper = new DataBaseHelper(this);

        /* -------------------------------------- */
        // Initialisation affichage
        /* -------------------------------------- */
//        afficherListeTomes();
        /* -------------------------------------- */
        // Clic sur le logo
        /* -------------------------------------- */
//        binding.tbMenu.ivLogoMyComics.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(TomesActivity.this, MainActivity.class);
//                startActivity(intent);
//            }
//        });

        /* -------------------------------------- */
        // Clic Menu Hamburger
        /* -------------------------------------- */
//        binding.tbMenu.ivHamburgLines.setOnClickListener((new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (findViewById(R.id.fragViewMenu).getVisibility() == View.GONE) {
//
//                    findViewById(R.id.fragViewMenu).setVisibility(View.VISIBLE);
//                } else {
//                    findViewById(R.id.fragViewMenu).setVisibility(View.GONE);
//                }
//            }
//        }));

        /* -------------------------------------- */
        // Clic Bouton menu Collection
        /* -------------------------------------- */
        findViewById(R.id.btnMenuCollection).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TomesActivity.this, CollectionActivity.class);
                startActivity(intent);
            }
        });

        /* -------------------------------------- */
        // Clic Bouton menu Series
        /* -------------------------------------- */
        findViewById(R.id.btnMenuSeries).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TomesActivity.this, SeriesActivity.class);
                startActivity(intent);
            }
        });

        /* -------------------------------------- */
        // Clic Bouton menu Auteurs
        /* -------------------------------------- */
        findViewById(R.id.btnMenuAuteurs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(TomesActivity.this, AuteursActivity.class);
//                startActivity(intent);
            }
        });

        /* -------------------------------------- */
        // Clic Bouton menu Editeurs
        /* -------------------------------------- */
        findViewById(R.id.btnMenuEditeurs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(TomesActivity.this, EditeursActivitypourfonctionbdd.class);
//                startActivity(intent);
            }
        });

        /* -------------------------------------- */
        // Clic Bouton menu Reglages
        /* -------------------------------------- */
        findViewById(R.id.btnMenuReglages).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(TomesActivity.this, ReglagesActivity.class);
//                startActivity(intent);
            }
        });
        /* -------------------------------------- */
        // clic searchBar
        /* -------------------------------------- */
//        binding.sbSearch.svSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Active le clic sur toute la zone de la searchBar
//                binding.sbSearch.svSearch.setIconified(false);
//            }
//        });
        /* -------------------------------------- */
        // Clic Bouton ajout série
        /* -------------------------------------- */
//        binding.btnAddTomes.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //Création Popup
//                PopupAddDialog popupAddDialog = new PopupAddDialog(TomesActivity.this);
//                popupAddDialog.setTitre("Entrez le nom du tome");
//                popupAddDialog.setHint("Nom du tome");
//                popupAddDialog.getBtnPopupValider().setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        TomeBean tomeBean;
//                        try {
//                            tomeBean = new TomeBean(-1, popupAddDialog.getEtPopupText().getText().toString());
//                        } catch (Exception e) {
////                            Toast.makeText(ReglagesActivity.this, "Erreur création série", Toast.LENGTH_SHORT).show();
//                            tomeBean = new TomeBean(-1, "error" );
//                        }
//                        popupAddDialog.dismiss(); // Fermeture Popup
//                        //Appel DataBaseHelper
//                        dataBaseHelper = new DataBaseHelper(TomesActivity.this);
//
//                        boolean success = dataBaseHelper.insertIntoTomes(tomeBean);
//                        afficherListeTomes();
//                    }
//                });
//                popupAddDialog.build();
//                afficherListeTomes();
//            }
//        });

        /* -------------------------------------- */
        // Clic Element liste Serie
        /* -------------------------------------- */
//        binding.lvTomesListe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                TomeBean tomeBean;
//                try {
//                    tomeBean = (TomeBean) binding.lvTomesListe.getItemAtPosition(position);
//                } catch (Exception e) {
//                    tomeBean = new TomeBean(-1,"error");
//                }
//                Intent intent = new Intent(TomesActivity.this, TomesDetailActivity.class);
//                intent.putExtra("tome_id",tomeBean.getTome_id());
//                intent.putExtra("tome_titre",tomeBean.getTome_titre());
//                startActivity(intent);
//            }
//        });
    }

//    private void afficherListeTomes(){
//        tomesArrayAdapter = new TomesListAdapter(TomesActivity.this , R.layout.listview_template , dataBaseHelper.selectAllFromTomesTitreSeul());
//        binding.lvTomesListe.setAdapter(tomesArrayAdapter);
//    }

    @Override
    public void onClick(View v) {

    }
}
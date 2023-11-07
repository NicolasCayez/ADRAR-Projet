package com.example.mycomics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.ListFormatter;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.mycomics.adapters.ProfilsListAdapter;
import com.example.mycomics.databinding.ActivityReglagesBinding;
import com.example.mycomics.popups.PopupMenuDialog;
import com.example.mycomics.popups.PopupAddDialog;
import com.example.mycomics.popups.PopupListDialog;
import com.example.mycomics.beans.ProfilBean;
import com.example.mycomics.beans.ProfilNomBean;
import com.example.mycomics.helpers.DataBaseHelper;

public class ReglagesActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private ActivityReglagesBinding binding = null;

    /* -------------------------------------- */
    // Référence vers les éléments de la page
    /* -------------------------------------- */
    //menu Hamburger
    LinearLayout btnMenuCollection, btnMenuSeries, btnMenuTomes, btnMenuAuteurs, btnMenuEditeurs;

    /* -------------------------------------- */
    // Variable BDD
    /* -------------------------------------- */
    DataBaseHelper dataBaseHelper;
    ArrayAdapter profilsArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_reglages);
        binding = ActivityReglagesBinding.inflate(getLayoutInflater());
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
        dataBaseHelper = new DataBaseHelper(this);

        /* -------------------------------------- */
        // Initialisation affichage
        /* -------------------------------------- */
        afficherProfilActif();

        /* -------------------------------------- */
        // Clic sur le logo
        /* -------------------------------------- */
        binding.tbMenu.ivLogoMyComics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReglagesActivity.this, MainActivity.class);
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
        binding.btnAddProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Création Popup
                PopupAddDialog popupAddDialog = new PopupAddDialog(ReglagesActivity.this);
                popupAddDialog.setTitre("Entrez un nom de profil");
                popupAddDialog.setHint("Nom de profil");
                popupAddDialog.getBtnPopupValider().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ProfilNomBean profilBean;
                        try {
                            profilBean = new ProfilNomBean(-1, popupAddDialog.getEtPopupText().getText().toString());
                        } catch (Exception e) {
//                            Toast.makeText(ReglagesActivity.this, "Erreur création profil", Toast.LENGTH_SHORT).show();
                            profilBean = new ProfilNomBean(-1, "error" );
                        }
                        popupAddDialog.dismiss(); // Fermeture Popup
                        //Appel DataBaseHelper
                        dataBaseHelper = new DataBaseHelper(ReglagesActivity.this);

                        boolean success = dataBaseHelper.insertIntoProfils(profilBean);
//                        afficherListeProfils();
                    }
                });
                popupAddDialog.build();
                afficherProfilActif();
            }

        });
/************************************************************ TEST MENU POPUP
        /* -------------------------------------- */
        // Clic sur bouton DeleteProfil
        /* -------------------------------------- */
        binding.btnDeleteProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Création Popup
                PopupMenuDialog popupMenuDialog = new PopupMenuDialog(ReglagesActivity.this);
                Window window = popupMenuDialog.getWindow();
                WindowManager.LayoutParams wlp = window.getAttributes();
                wlp.gravity = Gravity.TOP;
                window.setLayout(wlp.MATCH_PARENT, wlp.WRAP_CONTENT);
                //clic listeners
                popupMenuDialog.build();
            }

        });

        /* -------------------------------------- */
        // Clic sur profil actif pour avoir la liste
        /* -------------------------------------- */
        binding.tvProfilActif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Création Popup
                PopupListDialog popupListDialog = new PopupListDialog(ReglagesActivity.this);
                popupListDialog.setTitre("Choisissez un profil dans la liste");
                ListView listView = (ListView) popupListDialog.findViewById(R.id.lvPopupListe);
//                profilsArrayAdapter = new ArrayAdapter<>(ReglagesActivity.this, android.R.layout.simple_list_item_1, dataBaseHelper.selectAllFromProfilsNomSeul());
                profilsArrayAdapter = new ProfilsListAdapter(ReglagesActivity.this , R.layout.listview_template , dataBaseHelper.selectAllFromProfilsNomSeul());
                listView.setAdapter(profilsArrayAdapter);
                //Clic Profil choisi pour modification
                popupListDialog.getLvPopupListe().setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ProfilBean profilBean;
                        try {
                            profilBean = (ProfilBean) popupListDialog.getLvPopupListe().getItemAtPosition(position);
                            dataBaseHelper.updateProfilActif(dataBaseHelper, ((ProfilBean) popupListDialog.getLvPopupListe().getItemAtPosition(position)).getProfil_id());
                        } catch (Exception e) {
                            profilBean = new ProfilBean(-1, "error" );
                        }
                        popupListDialog.dismiss(); // Fermeture Popup
                        //Appel DataBaseHelper
                        dataBaseHelper = new DataBaseHelper(ReglagesActivity.this);
                    }
                });
                popupListDialog.Build();
                popupListDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
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
            binding.tvProfilActif.setText(dataBaseHelper.selectFromProfilProfilActif().getProfil_nom());
        } catch (Exception e) {

        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    @Override
    public void onClick(View v) {

    }
}
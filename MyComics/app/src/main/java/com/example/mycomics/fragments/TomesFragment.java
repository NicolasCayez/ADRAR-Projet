package com.example.mycomics.fragments;

import static androidx.navigation.fragment.FragmentKt.findNavController;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.mycomics.R;
import com.example.mycomics.adapters.TomesSerieListAdapter;
import com.example.mycomics.beans.TomeBean;
import com.example.mycomics.beans.TomeSerieBean;
import com.example.mycomics.databinding.FragmentTomesBinding;
import com.example.mycomics.helpers.DataBaseHelper;
import com.example.mycomics.popups.PopupTextDialog;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TomesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TomesFragment extends Fragment {
    FragmentTomesBinding binding;

    /* -------------------------------------- */
    // Variable BDD
    /* -------------------------------------- */
    DataBaseHelper dataBaseHelper;
    ArrayAdapter tomesArrayAdapter;
    ArrayAdapter tomesSerieArrayAdapter;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TomesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TomesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TomesFragment newInstance(String param1, String param2) {
        TomesFragment fragment = new TomesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        /* -------------------------------------- */
        // Initialisation Base de données
        /* -------------------------------------- */
        dataBaseHelper = new DataBaseHelper(getActivity());


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTomesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /* -------------------------------------- */
        // Initialisation affichage
        /* -------------------------------------- */
        afficherListeTomes();
        binding.sbSearch.svSearch.setQueryHint("Filtrer ou rechercher");
        /* -------------------------------------- */
        // Clic Bouton Chercher
        /* -------------------------------------- */
        binding.sbSearch.btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("filtre", binding.sbSearch.svSearch.getQuery().toString());
                findNavController(TomesFragment.this).navigate(R.id.searchResultFragment, bundle);
            }
        });
        /* -------------------------------------- */
        // saisie searchBar
        /* -------------------------------------- */
        binding.sbSearch.svSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                afficherListeTomes();
                return false;
            }
        });
        /* -------------------------------------- */
        // Clic Bouton ajout Tome
        /* -------------------------------------- */
        binding.btnTomesAddTome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Création Popup
                PopupTextDialog popupTextDialog = new PopupTextDialog(getActivity());
                popupTextDialog.setTitre("Entrez le nom du tome");
                popupTextDialog.setHint("Nom du tome");
                popupTextDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                popupTextDialog.getBtnPopupValider().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TomeBean tomeBean;
                        try {
                            tomeBean = new TomeBean(-1, popupTextDialog.getEtPopupText().getText().toString());
                        } catch (Exception e) {
                            tomeBean = new TomeBean(-1, "error" );
                        }
                        popupTextDialog.dismiss(); // Fermeture Popup
                        //Appel DataBaseHelper
                        if (dataBaseHelper.verifDoublonTome(tomeBean.getTome_titre())) {
                            // Tome déjà existant
                            Toast.makeText(TomesFragment.super.getContext(), "Tome déjà existant, enregistrement annulé", Toast.LENGTH_LONG).show();
                            popupTextDialog.dismiss(); // Fermeture Popup
                        } else {
                            // on enregiste
                            boolean successInsertTomes = dataBaseHelper.insertIntoTomes(tomeBean);
                            boolean successInsertDetenir = dataBaseHelper.insertIntoDetenir(dataBaseHelper.selectDernierTomeAjoute(tomeBean));
                            Toast.makeText(getActivity(), "Tome créé", Toast.LENGTH_SHORT).show();
                            popupTextDialog.dismiss(); // Fermeture Popup
                        }
                        afficherListeTomes();
                    }
                });

                popupTextDialog.getEtPopupText().setOnKeyListener(new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                        if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                                (keyCode == KeyEvent.KEYCODE_ENTER)) {
                            // Perform action on key press
                            TomeBean tomeBean;
                            try {
                                tomeBean = new TomeBean(-1, popupTextDialog.getEtPopupText().getText().toString());
                            } catch (Exception e) {
                                tomeBean = new TomeBean(-1, "error" );
                            }
                            popupTextDialog.dismiss(); // Fermeture Popup
                            //Appel DataBaseHelper
                            if (dataBaseHelper.verifDoublonTome(tomeBean.getTome_titre())) {
                                // Tome déjà existant
                                Toast.makeText(TomesFragment.super.getContext(), "Tome déjà existant, enregistrement annulé", Toast.LENGTH_LONG).show();
                                popupTextDialog.dismiss(); // Fermeture Popup
                            } else {
                                // on enregiste
                                boolean successInsertTomes = dataBaseHelper.insertIntoTomes(tomeBean);
                                boolean successInsertDetenir = dataBaseHelper.insertIntoDetenir(dataBaseHelper.selectDernierTomeAjoute(tomeBean));
                                Toast.makeText(getActivity(), "Tome créé", Toast.LENGTH_SHORT).show();
                                popupTextDialog.dismiss(); // Fermeture Popup
                            }
                            afficherListeTomes();
                            return true;
                        }
                        return false;
                    }
                });

                popupTextDialog.getBtnPopupAnnuler().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupTextDialog.dismiss(); // Fermeture Popup
                    }
                });

                popupTextDialog.build();
                afficherListeTomes();
            }
        });

        /* -------------------------------------- */
        // Clic Element liste Tomes
        /* -------------------------------------- */
        binding.lvTomesListeTomes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TomeSerieBean tomeSerieBean;
                try {
                    tomeSerieBean = (TomeSerieBean) binding.lvTomesListeTomes.getItemAtPosition(position);
                } catch (Exception e) {
                    tomeSerieBean = new TomeSerieBean(-1,"error");
                }
                Bundle bundle = new Bundle();
                bundle.putInt("tome_id", tomeSerieBean.getTome_id());
                bundle.putInt("tome_numero", tomeSerieBean.getTome_id());
                bundle.putString("tome_titre", tomeSerieBean.getTome_titre());
                bundle.putDouble("tome_prix_editeur", tomeSerieBean.getTome_prix_editeur());
                bundle.putDouble("tome_valeur_connue", tomeSerieBean.getTome_valeur_connue());
                bundle.putString("tome_date_edition", tomeSerieBean.getTome_date_edition());
                bundle.putString("tome_isbn", tomeSerieBean.getTome_isbn());
                bundle.putString("tome_image", tomeSerieBean.getTome_image());
                bundle.putBoolean("tome_dedicace", tomeSerieBean.isTome_dedicace());
                bundle.putBoolean("tome_edition_speciale", tomeSerieBean.isTome_edition_speciale());
                bundle.putString("tome_edition_speciale_libelle", tomeSerieBean.getTome_edition_speciale_libelle());
                bundle.putInt("serie_id", tomeSerieBean.getSerie_id());
                bundle.putString("serie_nom", tomeSerieBean.getSerie_nom());
                bundle.putInt("editeur_id", tomeSerieBean.getEditeur_id());

                findNavController(TomesFragment.this).navigate(R.id.action_tomes_to_tomeDetail, bundle);

            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    private void afficherListeTomes(){
        if (binding.sbSearch.svSearch.getQuery().toString().length() > 0) {
            tomesSerieArrayAdapter = new TomesSerieListAdapter(getActivity() , R.layout.listview_row_3col, dataBaseHelper.listeTomesEtSerieSelonProfilIdFiltre(binding.sbSearch.svSearch.getQuery().toString()));
        } else {
            tomesSerieArrayAdapter = new TomesSerieListAdapter(getActivity() , R.layout.listview_row_3col, dataBaseHelper.listeTomesEtSerieSelonProfilId());
        }
        binding.lvTomesListeTomes.setAdapter(tomesSerieArrayAdapter);
    }
}
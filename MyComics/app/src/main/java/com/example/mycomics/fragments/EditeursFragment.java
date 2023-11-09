package com.example.mycomics.fragments;

import static androidx.navigation.fragment.FragmentKt.findNavController;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.mycomics.R;
import com.example.mycomics.adapters.EditeursListAdapter;
import com.example.mycomics.beans.EditeurBean;
import com.example.mycomics.databinding.FragmentEditeursBinding;
import com.example.mycomics.helpers.DataBaseHelper;
import com.example.mycomics.popups.PopupAddDialog;

public class EditeursFragment extends Fragment {
    FragmentEditeursBinding binding;

    /* -------------------------------------- */
    // Variable BDD
    /* -------------------------------------- */
    DataBaseHelper dataBaseHelper;
    ArrayAdapter editeursArrayAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EditeursFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditeursFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditeursFragment newInstance(String param1, String param2) {
        EditeursFragment fragment = new EditeursFragment();
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
        binding = FragmentEditeursBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /* -------------------------------------- */
        // Initialisation affichage
        /* -------------------------------------- */
        afficherListeEditeurs();
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
        // Clic Bouton ajout éditeur
        /* -------------------------------------- */
        binding.btnEditeursAddEditeur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Création Popup
                PopupAddDialog popupAddDialog = new PopupAddDialog(getActivity());
                popupAddDialog.setTitre("Entrez le nom de l'éditeur");
                popupAddDialog.setHint("Nom de l'éditeur");
                popupAddDialog.getBtnPopupValider().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditeurBean editeurBean;
                        try {
                            editeurBean = new EditeurBean(-1, popupAddDialog.getEtPopupText().getText().toString());
                        } catch (Exception e) {
//                            Toast.makeText(ReglagesActivity.this, "Erreur création éditeur", Toast.LENGTH_SHORT).show();
                            editeurBean = new EditeurBean(-1, "error" );
                        }
                        popupAddDialog.dismiss(); // Fermeture Popup
                        //Appel DataBaseHelper
                        dataBaseHelper = new DataBaseHelper(getActivity());

                        boolean success = dataBaseHelper.insertIntoEditeurs(editeurBean);
                        afficherListeEditeurs();
                    }
                });
                popupAddDialog.build();
                afficherListeEditeurs();
            }
        });

        /* -------------------------------------- */
        // Clic Element liste Editeur
        /* -------------------------------------- */
        binding.lvEditeursListeEditeurs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EditeurBean editeurBean;
                try {
                    editeurBean = (EditeurBean) binding.lvEditeursListeEditeurs.getItemAtPosition(position);
                } catch (Exception e) {
                    editeurBean = new EditeurBean(-1,"error");
                }
                Bundle bundle = new Bundle();
                bundle.putInt("editeur_id", editeurBean.getEditeur_id());
                bundle.putString("editeur_nom", editeurBean.getEditeur_nom());
                findNavController(EditeursFragment.this).navigate(R.id.action_editeurs_to_editeurDetail, bundle);

            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
    private void afficherListeEditeurs(){
        editeursArrayAdapter = new EditeursListAdapter(getActivity() , R.layout.listview_row_1col, dataBaseHelper.selectAllFromEditeurs());
        binding.lvEditeursListeEditeurs.setAdapter(editeursArrayAdapter);
    }
}
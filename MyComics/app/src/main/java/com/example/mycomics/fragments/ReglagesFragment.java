package com.example.mycomics.fragments;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mycomics.R;
import com.example.mycomics.adapters.ProfilsListAdapter;
import com.example.mycomics.beans.ProfilActifBean;
import com.example.mycomics.beans.ProfilBean;
import com.example.mycomics.beans.TomeBean;
import com.example.mycomics.databinding.FragmentReglagesBinding;
import com.example.mycomics.helpers.DataBaseHelper;
import com.example.mycomics.popups.PopupAddDialog;
import com.example.mycomics.popups.PopupListDialog;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReglagesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReglagesFragment extends Fragment {
    FragmentReglagesBinding binding;
    /* -------------------------------------- */
    // Variable BDD
    /* -------------------------------------- */
    DataBaseHelper dataBaseHelper;
    ArrayAdapter profilsArrayAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ReglagesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReglagesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReglagesFragment newInstance(String param1, String param2) {
        ReglagesFragment fragment = new ReglagesFragment();
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
    private void afficherProfilActif() {
        try {
            System.out.println("test: " + dataBaseHelper.selectFromProfilProfilActif().getProfil_nom());
            binding.tvProfilActif.setText(dataBaseHelper.selectFromProfilProfilActif().getProfil_nom());
        } catch (Exception e) {

        }

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentReglagesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /* -------------------------------------- */
        // Initialisation affichage
        /* -------------------------------------- */
        afficherProfilActif();

        /* -------------------------------------- */
        // Clic Enregistrer Profil
        /* -------------------------------------- */
        binding.btnSaveProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Création Popup
                PopupAddDialog popupAddDialog = new PopupAddDialog(getActivity());
                popupAddDialog.setTitre("Entrez le nouveau nom de profil");
                popupAddDialog.setHint("Nom de profil");
                popupAddDialog.getBtnPopupValider().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ProfilBean profilBean;
                        try {
                            profilBean = new ProfilBean(dataBaseHelper.selectFromProfilProfilActif().getProfil_id(), popupAddDialog.getEtPopupText().getText().toString());
                        } catch (Exception e) {
//                            Toast.makeText(ReglagesActivity.this, "Erreur création profil", Toast.LENGTH_SHORT).show();
                            profilBean = dataBaseHelper.selectFromProfilProfilActif();
                        }
                        popupAddDialog.dismiss(); // Fermeture Popup
                        //Appel DataBaseHelper
                        dataBaseHelper = new DataBaseHelper(getActivity());
                        boolean updateOk = dataBaseHelper.updateProfil(dataBaseHelper, profilBean);
                        if (updateOk) {
                            Toast.makeText(getActivity(),"Profil modifié avec succès" , Toast.LENGTH_SHORT);
                        } else {
                            Toast.makeText(getActivity(),"Erreur modification profil" , Toast.LENGTH_SHORT);
                        }
                        afficherProfilActif();

                    }

                });
                popupAddDialog.build();
                afficherProfilActif();



            }
        });

        /* -------------------------------------- */
        // Clic sur bouton AddProfil
        /* -------------------------------------- */
        binding.btnAddProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Création Popup
                PopupAddDialog popupAddDialog = new PopupAddDialog(getActivity());
                popupAddDialog.setTitre("Entrez un nom de profil");
                popupAddDialog.setHint("Nom de profil");
                popupAddDialog.getBtnPopupValider().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ProfilBean profilBean;
                        try {
                            profilBean = new ProfilBean(-1, popupAddDialog.getEtPopupText().getText().toString());
                        } catch (Exception e) {
//                            Toast.makeText(ReglagesActivity.this, "Erreur création profil", Toast.LENGTH_SHORT).show();
                            profilBean = new ProfilBean(-1, "error" );
                        }
                        popupAddDialog.dismiss(); // Fermeture Popup
                        //Appel DataBaseHelper
                        dataBaseHelper = new DataBaseHelper(getActivity());
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
//                //Création Popup
//                PopupMenuDialog popupMenuDialog = new PopupMenuDialog(ReglagesActivity.this);
//                Window window = popupMenuDialog.getWindow();
//                WindowManager.LayoutParams wlp = window.getAttributes();
//                wlp.gravity = Gravity.TOP;
//                window.setLayout(wlp.MATCH_PARENT, wlp.WRAP_CONTENT);
//                //clic listeners
//                popupMenuDialog.build();

//                Intent intent = new Intent(ReglagesActivity.this, MainActivity2.class);
//                startActivity(intent);


            }

        });



        /* -------------------------------------- */
        // Clic sur profil actif pour avoir la liste
        /* -------------------------------------- */
        binding.tvProfilActif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Création Popup
                PopupListDialog popupListDialog = new PopupListDialog(getActivity());
                popupListDialog.setTitre("Choisissez un profil dans la liste");
                ListView listView = (ListView) popupListDialog.findViewById(R.id.lvPopupList);
                profilsArrayAdapter = new ProfilsListAdapter(getActivity() , R.layout.listview_row_1col, dataBaseHelper.selectAllFromProfils());
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
//                        dataBaseHelper = new DataBaseHelper(getActivity());
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
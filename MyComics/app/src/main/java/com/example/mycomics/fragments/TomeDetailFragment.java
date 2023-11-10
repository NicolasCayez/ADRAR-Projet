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
import com.example.mycomics.adapters.AuteursListAdapter;
import com.example.mycomics.adapters.EditeursListAdapter;
import com.example.mycomics.beans.AuteurBean;
import com.example.mycomics.beans.EditeurBean;
import com.example.mycomics.beans.TomeBean;
import com.example.mycomics.databinding.FragmentTomeDetailBinding;
import com.example.mycomics.helpers.DataBaseHelper;
import com.example.mycomics.popups.PopupAddDialog;
import com.example.mycomics.popups.PopupAddListDialog;
import com.example.mycomics.popups.PopupListDialog;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TomeDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TomeDetailFragment extends Fragment {
    FragmentTomeDetailBinding binding;

    /* -------------------------------------- */
    // Variable BDD
    /* -------------------------------------- */
    DataBaseHelper dataBaseHelper;
    ArrayAdapter tomesArrayAdapter;
    ArrayAdapter auteursArrayAdapter;
    ArrayAdapter editeursArrayAdapter;
    ArrayAdapter seriesArrayAdapter;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TomeDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TomeDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TomeDetailFragment newInstance(String param1, String param2) {
        TomeDetailFragment fragment = new TomeDetailFragment();
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
        binding = FragmentTomeDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /* -------------------------------------- */
        // Récupération données
        /* -------------------------------------- */
        Integer tome_id = getArguments().getInt("tome_id");
        Integer tome_numero = getArguments().getInt("tome_numero");
        String tome_titre = getArguments().getString("tome_titre");
        double tome_prix_editeur = getArguments().getDouble("tome_prix_editeur");
        double tome_valeur_connue = getArguments().getDouble("tome_valeur_connue");
        String tome_date_edition = getArguments().getString("tome_date_edition");
        String tome_isbn = getArguments().getString("tome_isbn");
        String tome_image = getArguments().getString("tome_image");
        boolean tome_dedicace = getArguments().getBoolean("tome_dedicace");
        boolean tome_edition_speciale = getArguments().getBoolean("tome_edition_speciale");
        String tome_edition_speciale_libelle = getArguments().getString("tome_edition_speciale_libelle");
        Integer serie_id = getArguments().getInt("serie_id");
        /* -------------------------------------- */
        // Initialisation affichage
        /* -------------------------------------- */
        afficherDetailTome(tome_id);
        /* -------------------------------------- */
        // Initialisation Nom fiche
        /* -------------------------------------- */
        binding.etDetailTomeTitre.setText(tome_titre);
        /* -------------------------------------- */
        // Clic Enregistrer Tome
        /* -------------------------------------- */
        binding.btnDetailTomeSaveTome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TomeBean tomeModif = new TomeBean(
                        tome_id,
                        binding.etDetailTomeTitre.getText().toString(),
                        Integer.parseInt(binding.etDetailTomeNumero.getText().toString()),
                        binding.etDetailTomeISBN.getText().toString(),
                        tome_image,
                        Double.parseDouble(binding.etDetailTomePrixEditeur.getText().toString()),
                        Double.parseDouble(binding.etDetailTomeValeurActuelle.getText().toString()),
                        binding.etDetailTomeDateEdition.getText().toString(),
                        binding.chkDetailTomeDedicace.isChecked(),
                        binding.chkDetailTomeEditionSpeciale.isChecked(),
                        binding.etDetailTomeEditionSpecialeLibelle.getText().toString(),
                        serie_id);
                boolean updateOk = dataBaseHelper.updateTome(dataBaseHelper, tomeModif);
                if (updateOk) {
                    Toast.makeText(getActivity(),"Tome modifié avec succès" , Toast.LENGTH_SHORT);
                } else {
                    Toast.makeText(getActivity(),"Erreur modification tome" , Toast.LENGTH_SHORT);

                }
            }
        });

        /* -------------------------------------- */
        // Clic sur bouton AddAuteur
        /* -------------------------------------- */
        binding.btnDetailTomeAddAuteur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Création Popup
                PopupAddListDialog popupAddListDialog = new PopupAddListDialog(getActivity());
                popupAddListDialog.setTitre("Choisissez un Auteur dans la liste ou créez-en un nouveau");
                popupAddListDialog.setHint("Pseudo nouvel auteur");
                ListView listView = popupAddListDialog.findViewById(R.id.lvPopupList);
                auteursArrayAdapter = new AuteursListAdapter(getActivity() , R.layout.listview_row_1col, dataBaseHelper.selectAllFromAuteurs());
                listView.setAdapter(auteursArrayAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        AuteurBean auteurBean = null;
                        try {
                            auteurBean = (AuteurBean) popupAddListDialog.getLvPopupList().getItemAtPosition(position);
                        } catch (Exception e) {
                        }
                        popupAddListDialog.dismiss(); // Fermeture Popup
                        //Appel DataBaseHelper
                        dataBaseHelper = new DataBaseHelper(getActivity());
                        if (dataBaseHelper.verifDoubonTomeAuteur(tome_id, auteurBean.getAuteur_id())) {
                            // Editeur déjà existant
                            Toast.makeText(TomeDetailFragment.super.getContext(), "Auteur " + auteurBean.getAuteur_pseudo() + " déjà existant", Toast.LENGTH_SHORT).show();
                        } else {
                            // on enregiste
                            boolean successInsertDetenir = dataBaseHelper.insertIntoEcrire(tome_id, auteurBean.getAuteur_id());
                        }

                        afficherDetailTome(tome_id);
                    }
                });

                popupAddListDialog.getBtnPopupValider().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AuteurBean auteurBean;
                        if (popupAddListDialog.getEtPopupText().getText().length() > 0) {
                            try {
                                auteurBean = new AuteurBean(-1, popupAddListDialog.getEtPopupText().getText().toString());
                            } catch (Exception e) {
    //                            Toast.makeText(ReglagesActivity.this, "Erreur création profil", Toast.LENGTH_SHORT).show();
                                auteurBean = new AuteurBean(-1, "error" );
                            }
                            popupAddListDialog.dismiss(); // Fermeture Popup
                            //Appel DataBaseHelper
                            dataBaseHelper = new DataBaseHelper(getActivity());
                            boolean successInsertAuteur = dataBaseHelper.insertIntoAuteurs(auteurBean);
                            boolean successInsertEcrire = dataBaseHelper.insertIntoEcrire(tome_id, dataBaseHelper.selectAUTEURIDFromAuteursDernierAjout(auteurBean).getAuteur_id());
    //                        afficherDetailTome(tome_id)
                        }
                    }
                });

                popupAddListDialog.Build();
                popupAddListDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        afficherDetailTome(tome_id);
                    }
                });
            }
        });

        /* -------------------------------------- */
        // Clic sur l'éditeur pour avoir la liste
        /* -------------------------------------- */
        binding.tvDetailTomeEditeur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Création Popup
                PopupListDialog popupListDialog = new PopupListDialog(getActivity());
                popupListDialog.setTitre("Choisissez un profil dans la liste");
                ListView listView = (ListView) popupListDialog.findViewById(R.id.lvPopupList);
                editeursArrayAdapter = new EditeursListAdapter(getActivity() , R.layout.listview_row_1col, dataBaseHelper.selectAllFromEditeurs());
                listView.setAdapter(editeursArrayAdapter);
                //Clic Profil choisi pour modification
                popupListDialog.getLvPopupListe().setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        EditeurBean editeurBean;
                        try {
                            editeurBean = (EditeurBean) popupListDialog.getLvPopupListe().getItemAtPosition(position);
                            dataBaseHelper.updateEditeurTome(dataBaseHelper, ((EditeurBean) popupListDialog.getLvPopupListe().getItemAtPosition(position)).getEditeur_id(), tome_id);
                        } catch (Exception e) {
                            editeurBean = new EditeurBean(-1, "error" );
                        }
                        popupListDialog.dismiss(); // Fermeture Popup
                        //Appel DataBaseHelper
                        dataBaseHelper = new DataBaseHelper(getActivity());
                    }
                });
                popupListDialog.Build();
                popupListDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        afficherDetailTome(tome_id);
                    }
                });
            }
        });
        /* -------------------------------------- */
        // Clic nom Editeur pour modifier
        /* -------------------------------------- */
        binding.btnDetailTomeAddEditeur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Création Popup
                PopupListDialog popupListDialog = new PopupListDialog(getActivity());
                popupListDialog.setTitre("Choisissez un profil dans la liste");
                ListView listView = (ListView) popupListDialog.findViewById(R.id.lvPopupList);
                editeursArrayAdapter = new EditeursListAdapter(getActivity() , R.layout.listview_row_1col, dataBaseHelper.selectAllFromEditeurs());
                listView.setAdapter(editeursArrayAdapter);
                //Clic Profil choisi pour modification
                popupListDialog.getLvPopupListe().setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        EditeurBean editeurBean;
                        try {
                            editeurBean = (EditeurBean) popupListDialog.getLvPopupListe().getItemAtPosition(position);
                            dataBaseHelper.insertIntoEditer(tome_id, editeurBean.getEditeur_id());
                        } catch (Exception e) {
                            editeurBean = new EditeurBean(-1, "error" );
                        }
                        popupListDialog.dismiss(); // Fermeture Popup
                        if (dataBaseHelper.selectFromEditerTomeExistant(tome_id)){
                            // tome déja dans la table éditer
                            dataBaseHelper.updateEditeurTome(dataBaseHelper, ((EditeurBean) popupListDialog.getLvPopupListe().getItemAtPosition(position)).getEditeur_id(), tome_id);
                        } else {
                            // on ne touche à rien
                        }
                        //Appel DataBaseHelper
//                        dataBaseHelper = new DataBaseHelper(getActivity());
                    }
                });
                popupListDialog.Build();
                popupListDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        afficherDetailTome(tome_id);
                    }
                });

            }
        });
        /* -------------------------------------- */
        // Clic sur bouton AddEditeur
        /* -------------------------------------- */
        binding.btnDetailTomeAddEditeur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Création Popup
                PopupAddDialog popupAddDialog = new PopupAddDialog(getActivity());
                popupAddDialog.setTitre("Entrez un nom de profil");
                popupAddDialog.setHint("Nom de profil");
                popupAddDialog.getBtnPopupValider().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditeurBean editeurBean;
                        try {
                            editeurBean = new EditeurBean(-1, popupAddDialog.getEtPopupText().getText().toString());
                        } catch (Exception e) {
//                            Toast.makeText(ReglagesActivity.this, "Erreur création profil", Toast.LENGTH_SHORT).show();
                            editeurBean = new EditeurBean(-1, "error" );
                        }
                        popupAddDialog.dismiss(); // Fermeture Popup
                        //Appel DataBaseHelper
                        dataBaseHelper = new DataBaseHelper(getActivity());
                        if (dataBaseHelper.verifDoubonEditeur(binding.tvDetailTomeEditeur.getText().toString())) {
                            // Editeur déjà existant
                            Toast.makeText(TomeDetailFragment.super.getContext(), "Editeur " + binding.tvDetailTomeEditeur.getText().toString() + " déjà existant", Toast.LENGTH_SHORT).show();
                        } else {
                            // on enregiste
                            boolean success = dataBaseHelper.insertIntoEditeurs(editeurBean);
                        }
//                        afficherListeProfils();
                    }
                });
                popupAddDialog.build();
                afficherDetailTome(tome_id);
            }
        });

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    private void afficherDetailTome(int tome_id){
        TomeBean tome = dataBaseHelper.selectTome(tome_id);
        binding.etDetailTomeTitre.setText(tome.getTome_titre());
        binding.etDetailTomeNumero.setText(String.valueOf(tome.getTome_numero()));
        binding.etDetailTomeISBN.setText(String.valueOf(tome.getTome_isbn()));
        binding.etDetailTomePrixEditeur.setText(String.valueOf(tome.getTome_prix_editeur()));
        binding.etDetailTomeValeurActuelle.setText(String.valueOf(tome.getTome_valeur_connue()));
        binding.etDetailTomeDateEdition.setText(String.valueOf(tome.getTome_date_edition()));
        binding.chkDetailTomeDedicace.setChecked(Boolean.valueOf(tome.isTome_dedicace()));
        binding.chkDetailTomeEditionSpeciale.setChecked(Boolean.valueOf(tome.isTome_edition_speciale()));
        binding.etDetailTomeEditionSpecialeLibelle.setText(tome.getTome_edition_speciale_libelle());
        auteursArrayAdapter = new AuteursListAdapter(getActivity(), R.layout.listview_row_1col, dataBaseHelper.selectAllFromAuteursTomeId(tome_id));
        binding.lvDetailTomeAuteurs.setAdapter(auteursArrayAdapter);
        binding.tvDetailTomeEditeur.setText(dataBaseHelper.selectEditeurTomeId(tome_id).getEditeur_nom());

    }

}
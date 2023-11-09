package com.example.mycomics.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.mycomics.beans.TomeBean;
import com.example.mycomics.databinding.FragmentTomeDetailBinding;
import com.example.mycomics.helpers.DataBaseHelper;

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
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    private void afficherDetailTome(int id_tome){
        TomeBean tome = dataBaseHelper.selectTome(id_tome);
        binding.etDetailTomeTitre.setText(tome.getTome_titre());
        binding.etDetailTomeNumero.setText(String.valueOf(tome.getTome_numero()));
        binding.etDetailTomeISBN.setText(String.valueOf(tome.getTome_isbn()));
        binding.etDetailTomePrixEditeur.setText(String.valueOf(tome.getTome_prix_editeur()));
        binding.etDetailTomeValeurActuelle.setText(String.valueOf(tome.getTome_valeur_connue()));
        binding.etDetailTomeDateEdition.setText(String.valueOf(tome.getTome_date_edition()));
        binding.chkDetailTomeDedicace.setChecked(Boolean.valueOf(tome.isTome_dedicace()));
        binding.chkDetailTomeEditionSpeciale.setChecked(Boolean.valueOf(tome.isTome_edition_speciale()));
        binding.etDetailTomeEditionSpecialeLibelle.setText(tome.getTome_edition_speciale_libelle());
    }

}
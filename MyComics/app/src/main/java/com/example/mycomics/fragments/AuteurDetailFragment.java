package com.example.mycomics.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.mycomics.R;
import com.example.mycomics.databinding.FragmentAuteurDetailBinding;
import com.example.mycomics.databinding.FragmentAuteursBinding;
import com.example.mycomics.helpers.DataBaseHelper;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AuteurDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AuteurDetailFragment extends Fragment {
    FragmentAuteurDetailBinding binding;

    /* -------------------------------------- */
    // Variable BDD
    /* -------------------------------------- */
    DataBaseHelper dataBaseHelper;
    ArrayAdapter auteursArrayAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AuteurDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AuteurDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AuteurDetailFragment newInstance(String param1, String param2) {
        AuteurDetailFragment fragment = new AuteurDetailFragment();
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
        binding = FragmentAuteurDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /* -------------------------------------- */
        // Récupération données
        /* -------------------------------------- */
        int auteur_id = getArguments().getInt("auteur_id");
        String auteur_pseudo = getArguments().getString("auteur_pseudo");
        String auteur_nom = getArguments().getString("auteur_nom");
        String auteur_prenom = getArguments().getString("auteur_prenom");


        /* -------------------------------------- */
        // Initialisation Nom fiche
        /* -------------------------------------- */
        binding.tvDetailAuteurPseudo.setText(auteur_pseudo);

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

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
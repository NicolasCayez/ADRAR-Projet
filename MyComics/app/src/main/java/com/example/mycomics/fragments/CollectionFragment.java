package com.example.mycomics.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.mycomics.R;
import com.example.mycomics.databinding.FragmentCollectionBinding;
import com.example.mycomics.helpers.DataBaseHelper;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CollectionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CollectionFragment extends Fragment {
    FragmentCollectionBinding binding;
    /* -------------------------------------- */
    // Variable BDD
    /* -------------------------------------- */
    DataBaseHelper dataBaseHelper;
    ArrayAdapter CollectionArrayAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CollectionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CollectionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CollectionFragment newInstance(String param1, String param2) {
        CollectionFragment fragment = new CollectionFragment();
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
        binding = FragmentCollectionBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /* -------------------------------------- */
        // Initialisation affichage ****************************************************
        /* -------------------------------------- */
//        afficherPageCollection();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
    private void afficherPageCollection(){

    }
}
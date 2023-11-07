package com.example.mycomics.popups;

import android.app.Dialog;
import android.content.Context;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.mycomics.R;

public class PopupListDialog extends Dialog {
    /* -------------------------------------- */
    // Référence vers les éléments du popup
    /* -------------------------------------- */
    private String titre;
    private TextView tvPopupListTitle;
    private ListView lvPopupListe;
    private Button btnPopupListAnnuler;

    /* -------------------------------------- */
    // Constructor
    /* -------------------------------------- */

    public PopupListDialog(@NonNull Context context) {
        super(context, androidx.appcompat.R.style.Theme_AppCompat_Dialog);
        setContentView(R.layout.dialog_popup_list);
        this.titre = "Mon titre";
        this.tvPopupListTitle = findViewById(R.id.tvPopupListTitle);
        this.lvPopupListe = findViewById(R.id.lvPopupListe);
        this.btnPopupListAnnuler = findViewById(R.id.btnPopupListAnnuler);
    }

    /* -------------------------------------- */
    // Get/Set
    /* -------------------------------------- */

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public TextView getTvPopupListTitle() {
        return tvPopupListTitle;
    }

    public void setTvPopupListTitle(TextView tvPopupListTitle) {
        this.tvPopupListTitle = tvPopupListTitle;
    }

    public ListView getLvPopupListe() {
        return lvPopupListe;
    }

    public void setLvPopupListe(ListView lvPopupListe) {
        this.lvPopupListe = lvPopupListe;
    }

    public Button getBtnPopupListAnnuler() {
        return btnPopupListAnnuler;
    }

    public void setBtnPopupListAnnuler(Button btnPopupListAnnuler) {
        this.btnPopupListAnnuler = btnPopupListAnnuler;
    }

    /* -------------------------------------- */
    // Méthode Build
    /* -------------------------------------- */
    public void Build(){
        show();
        tvPopupListTitle.setText(titre);
    }
}

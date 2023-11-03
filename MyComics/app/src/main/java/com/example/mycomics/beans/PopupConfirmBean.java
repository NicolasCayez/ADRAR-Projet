package com.example.mycomics.beans;

import android.app.Dialog;
import android.content.Context;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.mycomics.R;

public class PopupConfirmBean extends Dialog {
    /* -------------------------------------- */
    // Référence vers les éléments du popup
    /* -------------------------------------- */
    private String titre;
    private TextView tvPopupConfirmTitle;
    private Button btnPopupConfirmValider, btnPopupConfirmAnnuler;

    /* -------------------------------------- */
    // Constructor
    /* -------------------------------------- */
    public PopupConfirmBean(@NonNull Context context) {
        super(context, androidx.appcompat.R.style.Theme_AppCompat_Dialog);
        setContentView(R.layout.popup_confirm_template);
        this.titre = "Mon titre";
        this.tvPopupConfirmTitle = findViewById(R.id.tvPopupConfirmTitle);
        this.btnPopupConfirmValider = findViewById(R.id.btnPopupConfirmValider);
        this.btnPopupConfirmAnnuler = findViewById(R.id.btnPopupConfirmAnnuler);
    }

    /* -------------------------------------- */
    // Get/Set
    /* -------------------------------------- */
    public void setTitre(String titre) {
        this.titre = titre;
    }
    public Button getBtnPopupValider() {
        return btnPopupConfirmValider;
    }
    public Button getBtnPopupAnnuler() {
        return btnPopupConfirmAnnuler;
    }

    /* -------------------------------------- */
    // Méthode build
    /* -------------------------------------- */
    public void build(){
        show();
        tvPopupConfirmTitle.setText(titre);
    }
}

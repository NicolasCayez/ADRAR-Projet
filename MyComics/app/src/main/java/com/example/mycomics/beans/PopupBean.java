package com.example.mycomics.beans;

import android.app.Dialog;
import android.content.Context;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.mycomics.R;
import com.example.mycomics.ReglagesActivity;


public class PopupBean extends Dialog {
    /* -------------------------------------- */
    // Référence vers les éléments du popup
    /* -------------------------------------- */
    private String titre;
    private String hint;
    private TextView tvPopupTitle;
    private EditText etPopupText;
    private Button btnPopupValider, btnPopupAnnuler;

    /* -------------------------------------- */
    // Constructor
    /* -------------------------------------- */
    public PopupBean(@NonNull Context context) {
        super(context, androidx.appcompat.R.style.Theme_AppCompat_Dialog);
        setContentView(R.layout.popup_template);
        this.titre = "Mon titre";
        this.hint = "texte";
        this.tvPopupTitle = findViewById(R.id.tvPopupTitle);
        this.etPopupText = findViewById(R.id.etPopupText);
        this.btnPopupValider = findViewById(R.id.btnPopupValider);
        this.btnPopupAnnuler = findViewById(R.id.btnPopupAnnuler);
    }

    /* -------------------------------------- */
    // Get/Set
    /* -------------------------------------- */
    public void setTitre(String titre) {
        this.titre = titre;
    }
    public void setHint(String hint) {
        this.hint = hint;
    }
    public EditText getEtPopupText() {
        return etPopupText;
    }
    public Button getBtnPopupValider() {
        return btnPopupValider;
    }
    public Button getBtnPopupAnnuler() {
        return btnPopupAnnuler;
    }

    /* -------------------------------------- */
    // Méthode build
    /* -------------------------------------- */
    public void build(){
        show();
        tvPopupTitle.setText(titre);
        etPopupText.setHint(hint);
    }
}

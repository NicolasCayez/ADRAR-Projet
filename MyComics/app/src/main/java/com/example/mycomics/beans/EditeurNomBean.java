package com.example.mycomics.beans;

public class EditeurNomBean extends EditeurBean {
    /* -------------------------------------- */
    // Constructor
    /* -------------------------------------- */
    public EditeurNomBean(int editeur_id, String editeur_nom) {
        super(editeur_id, editeur_nom);
    }
    public EditeurNomBean() {
    }
    /* -------------------------------------- */
    // Get/Set
    /* -------------------------------------- */
    @Override
    public int getEditeur_id() {
        return super.getEditeur_id();
    }
    @Override
    public void setEditeur_id(int editeur_id) {
        super.setEditeur_id(editeur_id);
    }
    @Override
    public String getEditeur_nom() {
        return super.getEditeur_nom();
    }
    @Override
    public void setEditeur_nom(String editeur_nom) {
        super.setEditeur_nom(editeur_nom);
    }

    /* -------------------------------------- */
    // Tostring
    /* -------------------------------------- */
    @Override
    public String toString() {
        return getEditeur_nom();
    }

}

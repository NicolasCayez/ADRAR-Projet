package com.example.mycomics.beans;

public class ProfilNomBean extends ProfilBean {

    /* -------------------------------------- */
    // Constructor
    /* -------------------------------------- */
    public ProfilNomBean(int profil_id, String profil_nom) {
        super(profil_id, profil_nom);
    }
    public ProfilNomBean() {
    }

    /* -------------------------------------- */
    // Get/Set
    /* -------------------------------------- */

    @Override
    public int getProfil_id() {
        return super.getProfil_id();
    }

    @Override
    public void setProfil_id(int profil_id) {
        super.setProfil_id(profil_id);
    }

    @Override
    public String getProfil_nom() {
        return super.getProfil_nom();
    }

    @Override
    public void setProfil_nom(String profil_nom) {
        super.setProfil_nom(profil_nom);
    }

    /* -------------------------------------- */
    // Tostring
    /* -------------------------------------- */
    @Override
    public String toString() {
        return getProfil_nom();
    }
}

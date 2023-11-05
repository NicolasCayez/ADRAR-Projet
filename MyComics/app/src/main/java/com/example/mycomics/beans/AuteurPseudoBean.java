package com.example.mycomics.beans;

public class AuteurPseudoBean extends AuteurBean{
    /* -------------------------------------- */
    // Constructor
    /* -------------------------------------- */

    public AuteurPseudoBean(int auteur_id, String auteur_nom, String auteur_prenom, String auteur_pseudo, String auteur_photo) {
        super(auteur_id, auteur_nom, auteur_prenom, auteur_pseudo, auteur_photo);
    }
    public AuteurPseudoBean(int auteur_id, String auteur_pseudo) {
        super(auteur_id, auteur_pseudo);
    }
    public AuteurPseudoBean() {
    }
    /* -------------------------------------- */
    // Get/Set
    /* -------------------------------------- */
    @Override
    public int getAuteur_id() {
        return super.getAuteur_id();
    }
    @Override
    public void setAuteur_id(int auteur_id) {
        super.setAuteur_id(auteur_id);
    }
    @Override
    public String getAuteur_nom() {
        return super.getAuteur_nom();
    }
    @Override
    public void setAuteur_nom(String auteur_nom) {
        super.setAuteur_nom(auteur_nom);
    }
    @Override
    public String getAuteur_prenom() {
        return super.getAuteur_prenom();
    }
    @Override
    public void setAuteur_prenom(String auteur_prenom) {
        super.setAuteur_prenom(auteur_prenom);
    }
    @Override
    public String getAuteur_pseudo() {
        return super.getAuteur_pseudo();
    }
    @Override
    public void setAuteur_pseudo(String auteur_pseudo) {
        super.setAuteur_pseudo(auteur_pseudo);
    }
    @Override
    public String getAuteur_photo() {
        return super.getAuteur_photo();
    }
    @Override
    public void setAuteur_photo(String auteur_photo) {
        super.setAuteur_photo(auteur_photo);
    }
    /* -------------------------------------- */
    // Tostring
    /* -------------------------------------- */
    @Override
    public String toString() {
        return getAuteur_pseudo();
    }

}

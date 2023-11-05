package com.example.mycomics.beans;

import java.util.Date;

public class TomeTitreBean extends TomeBean {
    /* -------------------------------------- */
    // Constructor
    /* -------------------------------------- */

    public TomeTitreBean(int tome_id, String tome_titre, String tome_isbn, int tome_numero, String tome_image, double tome_prix_achat, double tome_valeur_connue, String tome_date_edition, boolean tome_dedicace, boolean tome_edition_speciale, String tome_edition_speciale_libelle, int serie_id) {
        super(tome_id, tome_titre, tome_isbn, tome_numero, tome_image, tome_prix_achat, tome_valeur_connue, tome_date_edition, tome_dedicace, tome_edition_speciale, tome_edition_speciale_libelle, serie_id);
    }

    public TomeTitreBean() {
    }
    /* -------------------------------------- */
    // Get/Set
    /* -------------------------------------- */
    @Override
    public int getTome_id() {
        return super.getTome_id();
    }
    @Override
    public void setTome_id(int tome_id) {
        super.setTome_id(tome_id);
    }
    @Override
    public int getTome_numero() {
        return super.getTome_numero();
    }
    @Override
    public void setTome_numero(int tome_numero) {
        super.setTome_numero(tome_numero);
    }
    @Override
    public int getSerie_id() {
        return super.getSerie_id();
    }
    @Override
    public void setSerie_id(int serie_id) {
        super.setSerie_id(serie_id);
    }
    @Override
    public String getTome_titre() {
        return super.getTome_titre();
    }
    @Override
    public void setTome_titre(String tome_titre) {
        super.setTome_titre(tome_titre);
    }
    @Override
    public String getTome_isbn() {
        return super.getTome_isbn();
    }
    @Override
    public void setTome_isbn(String tome_isbn) {
        super.setTome_isbn(tome_isbn);
    }
    @Override
    public String getTome_image() {
        return super.getTome_image();
    }
    @Override
    public void setTome_image(String tome_image) {
        super.setTome_image(tome_image);
    }
    @Override
    public String getTome_edition_speciale_libelle() {
        return super.getTome_edition_speciale_libelle();
    }
    @Override
    public void setTome_edition_speciale_libelle(String tome_edition_speciale_libelle) {
        super.setTome_edition_speciale_libelle(tome_edition_speciale_libelle);
    }
    @Override
    public double getTome_prix_achat() {
        return super.getTome_prix_achat();
    }
    @Override
    public void setTome_prix_achat(double tome_prix_achat) {
        super.setTome_prix_achat(tome_prix_achat);
    }
    @Override
    public double getTome_valeur_connue() {
        return super.getTome_valeur_connue();
    }
    @Override
    public void setTome_valeur_connue(double tome_valeur_connue) {
        super.setTome_valeur_connue(tome_valeur_connue);
    }
    @Override
    public String getTome_date_edition() {
        return super.getTome_date_edition();
    }
    @Override
    public void setTome_date_edition(String tome_date_edition) {
        super.setTome_date_edition(tome_date_edition);
    }
    @Override
    public boolean isTome_dedicace() {
        return super.isTome_dedicace();
    }
    @Override
    public void setTome_dedicace(boolean tome_dedicace) {
        super.setTome_dedicace(tome_dedicace);
    }
    @Override
    public boolean isTome_edition_speciale() {
        return super.isTome_edition_speciale();
    }
    @Override
    public void setTome_edition_speciale(boolean tome_edition_speciale) {
        super.setTome_edition_speciale(tome_edition_speciale);
    }

    /* -------------------------------------- */
    // Tostring
    /* -------------------------------------- */
    @Override
    public String toString() {
        return getTome_titre();
    }
}

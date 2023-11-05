package com.example.mycomics.beans;

import java.util.Date;

public class TomeBean {

    private int tome_id, tome_numero, serie_id;
    private String tome_titre, tome_isbn, tome_image, tome_date_edition, tome_edition_speciale_libelle;
    private double tome_prix_achat, tome_valeur_connue;
    private boolean tome_dedicace, tome_edition_speciale;

    /* -------------------------------------- */
    // Constructor
    /* -------------------------------------- */
    public TomeBean(int tome_id, String tome_titre, String tome_isbn, int tome_numero, String tome_image, double tome_prix_achat, double tome_valeur_connue, String tome_date_edition, boolean tome_dedicace, boolean tome_edition_speciale, String tome_edition_speciale_libelle, int serie_id) {
        this.tome_id = tome_id;
        this.tome_titre = tome_titre;
        this.tome_isbn = tome_isbn;
        this.tome_numero = tome_numero;
        this.tome_image = tome_image;
        this.tome_prix_achat = tome_prix_achat;
        this.tome_valeur_connue = tome_valeur_connue;
        this.tome_date_edition = tome_date_edition;
        this.tome_dedicace = tome_dedicace;
        this.tome_edition_speciale = tome_edition_speciale;
        this.tome_edition_speciale_libelle = tome_edition_speciale_libelle;
        this.serie_id = serie_id;
    }
    public TomeBean(int tome_id, String tome_titre) {
        this.tome_id = tome_id;
        this.tome_titre = tome_titre;
    }
    public TomeBean() {
    }

    /* -------------------------------------- */
    // Get/Set
    /* -------------------------------------- */
    public int getTome_id() {
        return tome_id;
    }
    public void setTome_id(int tome_id) {
        this.tome_id = tome_id;
    }
    public int getTome_numero() {
        return tome_numero;
    }
    public void setTome_numero(int tome_numero) {
        this.tome_numero = tome_numero;
    }
    public int getSerie_id() {
        return serie_id;
    }
    public void setSerie_id(int serie_id) {
        this.serie_id = serie_id;
    }
    public String getTome_titre() {
        return tome_titre;
    }
    public void setTome_titre(String tome_titre) {
        this.tome_titre = tome_titre;
    }
    public String getTome_isbn() {
        return tome_isbn;
    }
    public void setTome_isbn(String tome_isbn) {
        this.tome_isbn = tome_isbn;
    }
    public String getTome_image() {
        return tome_image;
    }
    public void setTome_image(String tome_image) {
        this.tome_image = tome_image;
    }
    public String getTome_edition_speciale_libelle() {
        return tome_edition_speciale_libelle;
    }
    public void setTome_edition_speciale_libelle(String tome_edition_speciale_libelle) {
        this.tome_edition_speciale_libelle = tome_edition_speciale_libelle;
    }
    public double getTome_prix_achat() {
        return tome_prix_achat;
    }
    public void setTome_prix_achat(double tome_prix_achat) {
        this.tome_prix_achat = tome_prix_achat;
    }
    public double getTome_valeur_connue() {
        return tome_valeur_connue;
    }
    public void setTome_valeur_connue(double tome_valeur_connue) {
        this.tome_valeur_connue = tome_valeur_connue;
    }
    public String getTome_date_edition() {
        return tome_date_edition;
    }
    public void setTome_date_edition(String tome_date_edition) {
        this.tome_date_edition = tome_date_edition;
    }
    public boolean isTome_dedicace() {
        return tome_dedicace;
    }
    public void setTome_dedicace(boolean tome_dedicace) {
        this.tome_dedicace = tome_dedicace;
    }
    public boolean isTome_edition_speciale() {
        return tome_edition_speciale;
    }
    public void setTome_edition_speciale(boolean tome_edition_speciale) {
        this.tome_edition_speciale = tome_edition_speciale;
    }

    /* -------------------------------------- */
    // ToString
    /* -------------------------------------- */
    @Override
    public String toString() {
        return "TomeBean{" +
                "tome_id=" + tome_id +
                ", tome_numero=" + tome_numero +
                ", serie_id=" + serie_id +
                ", tome_titre='" + tome_titre + '\'' +
                ", tome_isbn='" + tome_isbn + '\'' +
                ", tome_image='" + tome_image + '\'' +
                ", tome_edition_speciale_libelle='" + tome_edition_speciale_libelle + '\'' +
                ", tome_prix_achat=" + tome_prix_achat +
                ", tome_valeur_connue=" + tome_valeur_connue +
                ", tome_date_edition=" + tome_date_edition +
                ", tome_dedicace=" + tome_dedicace +
                ", tome_edition_speciale=" + tome_edition_speciale +
                '}';
    }
}

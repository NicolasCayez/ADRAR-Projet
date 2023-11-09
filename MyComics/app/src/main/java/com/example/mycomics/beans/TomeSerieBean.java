package com.example.mycomics.beans;

public class TomeSerieBean extends TomeBean {
        private String serie_nom;
    /* -------------------------------------- */
    // Constructeur
    /* -------------------------------------- */
    public TomeSerieBean(int tome_id, String tome_titre, int tome_numero, String tome_isbn, String tome_image, double tome_prix_editeur, double tome_valeur_connue, String tome_date_edition, boolean tome_dedicace, boolean tome_edition_speciale, String tome_edition_speciale_libelle, int serie_id, String serie_nom) {
        super(tome_id, tome_titre, tome_numero, tome_isbn, tome_image, tome_prix_editeur, tome_valeur_connue, tome_date_edition, tome_dedicace, tome_edition_speciale, tome_edition_speciale_libelle, serie_id);
        this.serie_nom = serie_nom;
    }

    public TomeSerieBean(int tome_id, String tome_titre) {
        super(tome_id, tome_titre);
    }

    /* -------------------------------------- */
    // Get/set
    /* -------------------------------------- */
    public String getSerie_nom() {
        return serie_nom;
    }

    public void setSerie_nom(String serie_nom) {
        this.serie_nom = serie_nom;
    }

    public TomeSerieBean(int tome_id, String tome_titre, int tome_numero, String tome_isbn, String tome_image, double tome_prix_editeur, double tome_valeur_connue, String tome_date_edition, boolean tome_dedicace, boolean tome_edition_speciale, String tome_edition_speciale_libelle, int serie_id) {
        super(tome_id, tome_titre, tome_numero, tome_isbn, tome_image, tome_prix_editeur, tome_valeur_connue, tome_date_edition, tome_dedicace, tome_edition_speciale, tome_edition_speciale_libelle, serie_id);
    }

    @Override
    public String toString() {
        return "TomeSerieBean{" +
                "serie_nom='" + serie_nom + '\'' +
                '}';
    }
}

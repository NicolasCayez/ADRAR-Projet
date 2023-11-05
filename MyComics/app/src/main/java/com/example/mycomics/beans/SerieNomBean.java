package com.example.mycomics.beans;

public class SerieNomBean extends SerieBean{
    /* -------------------------------------- */
    // Constructor
    /* -------------------------------------- */
    public SerieNomBean(int serie_id, String serie_nom) {
        super(serie_id, serie_nom);
    }
    public SerieNomBean() {
    }
    /* -------------------------------------- */
    // Get/Set
    /* -------------------------------------- */
    @Override
    public int getSerie_id() {
        return super.getSerie_id();
    }
    @Override
    public void setSerie_id(int serie_id) {
        super.setSerie_id(serie_id);
    }
    @Override
    public String getSerie_nom() {
        return super.getSerie_nom();
    }
    @Override
    public void setSerie_nom(String serie_nom) {
        super.setSerie_nom(serie_nom);
    }
    /* -------------------------------------- */
    // Tostring
    /* -------------------------------------- */
    @Override
    public String toString() {
        return getSerie_nom();
    }
}

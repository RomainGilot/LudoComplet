package fr.siomd.ludo.entity;

/**
 * Classe carte
 */
public class Carte {

    /**
     * Attribut String : Figure
     */
    private String figure;
    /**
     * Attribut String : Couleur
     */
    private String couleur;

    /**
     * Accesseurs
     * @return couleur
     */
    public String getCouleur() {
        return couleur;
    }

    /**
     * Accesseurs
     * @return figure
     */
    public String getFigure() {
        return figure;
    }

    /**
     * Constructeur
     * @param pCouleur
     * @param pFigure
     */
    //Constructeur
    public Carte(String pCouleur, String pFigure) {
        couleur = pCouleur;
        figure = pFigure;
    }

    /**
     * Accesseur
     * @return laValeur
     */
    public int getValeur()  {
        int laValeur;
        switch (figure) {
            case "As": laValeur = 14; break;
            case "Roi": laValeur = 13; break;
            case "Dame": laValeur = 12; break;
            case "Valet": laValeur = 11; break;
            default: laValeur = Integer.parseInt(figure); break;
        }
        return laValeur;
    }


    /**
     * Accesseur
     * @return Figure de couleur
     */
    public String getNom(){
        return String.format("%s de %s", getFigure(), getCouleur());
    }

    /**
     * Accesseur
     * @return nom
     */
    public String getNomImg(){
        String nom;
        switch(couleur) {
            case "Carreau": nom = "ca" + getValeur(); break;
            case "Coeur": nom = "co" + getValeur(); break;
            case "Trèfle": nom = "t" + getValeur(); break;
            case "Pique": nom = "p" + getValeur(); break;
            default: nom = "Erreur"; break;
        }
        return nom;
    }

    /**
     * Méthode
     * @param pCouleur
     * @return
     */
    public boolean isAtout(String pCouleur) {
        return (couleur.equals(pCouleur));
    }
}

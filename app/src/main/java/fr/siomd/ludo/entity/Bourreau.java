package fr.siomd.ludo.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Bourreau {
    // Attributs
    public static final int MAX_REBUT = 8;
    private Juge leJuge;
    private Mot leMot;
    private String wordSearch;
    private String actualWord;
    private ArrayList<Character> lettreRebut = new ArrayList<Character>();

    // Constructeur
    public Bourreau(Juge unJuge) {
        leJuge = unJuge;
        demarrer();
    }

    // Accesseurs
    public boolean isGagne() {
        if (Objects.equals(wordSearch, actualWord)){
            leJuge.ajouterScore(leMot.getNbPoints());
        }
        return Objects.equals(wordSearch, actualWord);
    }

    public boolean isPerdu() {
        return lettreRebut.size() == MAX_REBUT;
    }

    public String getLeMotEnCours() {
        return actualWord;
    }

    public String getLesLettresAuRebut () {
        String toutesLesLettres = "";
        for (Character laLettre : lettreRebut) {
            toutesLesLettres =  toutesLesLettres+" "+laLettre;
        }
        return toutesLesLettres;
    }

    // Méthodes
    public void demarrer() {
        leMot =  leJuge.donnerMot();
        wordSearch = leMot.getContenu().toUpperCase();
        lettreRebut.clear();
        actualWord = "";
        int longueurMot = wordSearch.length();
        for (int i = 0; i < longueurMot; i++) {
            actualWord = actualWord + "_";
        }
    }

    public void executer(char uneLettre) {
        List<Integer> placeLettre = new ArrayList<Integer>();
        int index = -1;

        while ((index = wordSearch.indexOf(uneLettre, index + 1)) != -1) {
            placeLettre.add(index);
            char[] motArray = actualWord.toCharArray();
            motArray[index] = uneLettre;
            actualWord = String.valueOf(motArray);
        }
        if (placeLettre.size() == wordSearch.length()) {
            leJuge.ajouterScore(leMot.getNbPoints());
        } else if (placeLettre.isEmpty()) {
            lettreRebut.add(uneLettre);
        }
    }
}
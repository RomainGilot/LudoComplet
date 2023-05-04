package fr.siomd.ludo.entity;

import java.util.ArrayList;
import java.util.Random;

import fr.siomd.ludo.R;
import fr.siomd.ludo.dataaccess.DicoXml;
import android.util.Log;

public class Juge {
    // Attribut
    private Random leHasard = new Random();
    private int score = 0;
    private ArrayList<Theme> lesThemes;
    private int numeroThemeSelectionne;

    // Constructeur
    public Juge(ArrayList<Theme> lesThemes)   {
        this.lesThemes = lesThemes;
        setNumeroThemeSelectionne(-1);
        lireThemes();
    }

    // Setter
    public void setNumeroThemeSelectionne(int unNumeroTheme) {
        if (unNumeroTheme == -1){
            numeroThemeSelectionne = leHasard.nextInt(2);
        } else if (unNumeroTheme == 0) {
            numeroThemeSelectionne = unNumeroTheme;
        } else if (unNumeroTheme == 1) {
            numeroThemeSelectionne = unNumeroTheme;
        }
    }

    // Accesseurs
    public ArrayList<String> getLesNomsThemes() {
        ArrayList<String> nomTheme = new ArrayList<String>();
        for (Theme unTheme : lesThemes) {
            nomTheme.add(unTheme.getNom());
        }
        return nomTheme;
    }

    public int getScore() {
        return score;
    }

    public int getNumeroThemeSelectionne() {
        return this.numeroThemeSelectionne;
    }

    // Méthodes
    public Mot donnerMot()  {
        Theme unTheme = lesThemes.get(this.numeroThemeSelectionne);
        ArrayList<Mot> lesMots = unTheme.getLesMots();
        int idMotRandom = leHasard.nextInt(4);
        Mot leMot = lesMots.get(idMotRandom);
        return leMot;
    }

    private void lireThemes() {
        Theme unTheme = lesThemes.get(numeroThemeSelectionne);
    }
    public void ajouterScore(int unNbPoints) {
        score = score + unNbPoints;
    }
}


package fr.siomd.ludo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.preference.PreferenceManager;

import fr.siomd.ludo.databinding.ActivityBatailleBinding;
import fr.siomd.ludo.entity.Carte;

import java.util.Locale;
import java.util.Random;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import android.view.View;
import android.widget.Toast;

public class BatailleActivity extends AppCompatActivity {

    private static final String TAG = "Bataille";
    private static final int NB_COUPS = 26;

    private String[] tbCouleurs = {"Coeur", "Carreau", "Pique", "Trèfle"};
    private String[] tbFigures = {"As", "Roi", "Carreau", "Valet", "2", "3", "4", "5", "6", "7", "8", "9", "10" };

    private Carte[] tbJeu = new Carte[NB_COUPS * 2];

    private Carte[] tbJoueurUn = new Carte [NB_COUPS];
    private Carte[] tbJoueurDeux = new Carte[NB_COUPS];

    private int numCoup = 0;
    private int indiceAtout = 0;
    private String atout = tbCouleurs [indiceAtout];
    private int scoreUn = 0;
    private int scoreDeux = 0;

    private int nbPointsCoup = 0;
    private int numJoueurGagnantCoup = 0;
    private int nbReponsesCorrectes = 0;

    private ActivityBatailleBinding ui;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        ui = ActivityBatailleBinding.inflate(getLayoutInflater());
        setContentView(ui.getRoot());

        int indCarte = 0;
        for(String couleur : tbCouleurs) {
            for(String figure : tbFigures) {
                tbJeu[indCarte] = new Carte(couleur, figure);
                indCarte++;
            }
        }
        afficherJeu();
        demarrerJeux();
    }
    private void afficherJeu(){
        for(Carte uneCarte : tbJeu) {
            Log.i(TAG, "carte = " + uneCarte.getNom());
        }
    }
    private void demarrerJeux() {
        Carte carteTempo;
        Random leHasard = new Random();
        for(int i = 0; i < tbJeu.length; i++) {
            int indHasard = leHasard.nextInt(tbJeu.length - 1);
            carteTempo = tbJeu[indHasard];
            tbJeu[indHasard] = tbJeu[i];
            tbJeu[i] = carteTempo;
        }

        int indJoueur = 0;
        for(int i = 0; i < tbJeu.length; i+=2) {
            tbJoueurUn[indJoueur] = tbJeu[i];
            tbJoueurDeux[indJoueur] = tbJeu[i + 1];
            indJoueur++;
        }

        scoreUn = 0;
        scoreDeux = 0;
        numCoup = -1;
        nbPointsCoup = 0;
        numJoueurGagnantCoup = 0;
        nbReponsesCorrectes = 0;

        ui.imgCarte1.setImageResource(R.drawable.back);
        ui.imgCarte2.setImageResource(R.drawable.back);
        ui.tvInfosCarte1.setText(" ");
        ui.tvInfosCarte2.setText(" ");
        ui.tvScore1.setText(String.format(Locale.getDefault(), "%d point", scoreUn));
        ui.tvScore2.setText(String.format(Locale.getDefault(), "%d point", scoreDeux));

        ui.etNbPoints.setText("");

        ui.etNbPoints.setEnabled(false);

        ui.tvNbRepCorrectes.setText(String.format(Locale.getDefault(), "%d / %d", 0, NB_COUPS));

        ui.btJoueur1.setEnabled(false);
        ui.btJoueur2.setEnabled(false);
        ui.btAucunJoueur.setEnabled(false);

        ui.btAbout.setEnabled(true);
    }

    public void onClickbtNouveauJeu(View view) {

        ui.btJouer.setEnabled(true);
        demarrerJeux();
    }

    public void onClickbtAbout(View view) {
        Context contexte = getApplicationContext();
        indiceAtout = (indiceAtout +1 ) % 4;
        atout = tbCouleurs[indiceAtout];
        switch (indiceAtout) {
            case 0: {
                ui.btAbout.setBackground(ContextCompat.getDrawable(contexte, R.drawable.coeur));
                break;
            }
            case 1: {
                ui.btAbout.setBackground(ContextCompat.getDrawable(contexte, R.drawable.carreau));
                break;
            }

            case 2: {
                ui.btAbout.setBackground(ContextCompat.getDrawable(contexte, R.drawable.pic));
                break;
            }

            case 3: {
                ui.btAbout.setBackground(ContextCompat.getDrawable(contexte, R.drawable.trefle));
                break;
            }
        }
    }

    public void onClickbtJouer(View view) {
        if (numCoup == -1) {
            ui.btAbout.setEnabled(false);
            ui.etNbPoints.setEnabled(true);
            ui.btJoueur1.setEnabled(true);
            ui.btJoueur2.setEnabled(true);
            ui.btAucunJoueur.setEnabled(true);
            numCoup = 0;
        }
        if(numCoup < 2) {
            nbPointsCoup = tbJoueurUn[numCoup].getValeur() + tbJoueurDeux[numCoup].getValeur();
            if(tbJoueurUn[numCoup].isAtout(atout) == tbJoueurDeux[numCoup].isAtout(atout)) {
                if(tbJoueurUn[numCoup].getValeur() > tbJoueurDeux[numCoup].getValeur()) {
                    scoreUn = scoreUn + nbPointsCoup;
                    numJoueurGagnantCoup = 1;
                } else {
                    if(tbJoueurDeux[numCoup].getValeur() > tbJoueurUn[numCoup].getValeur()) {
                        scoreDeux = scoreDeux + nbPointsCoup;
                        numJoueurGagnantCoup = 2;
                    } else {
                        numJoueurGagnantCoup = 3;
                    }
                }
            } else {
                if(tbJoueurUn[numCoup].isAtout(atout)) {
                    scoreUn = scoreUn + nbPointsCoup;
                    numJoueurGagnantCoup = 1;
                } else {
                    scoreDeux = scoreDeux + nbPointsCoup;
                    numJoueurGagnantCoup = 2;
                }
            }

            ui.imgCarte1.setImageResource((getCarteResource(tbJoueurUn[numCoup].getNomImg())));
            ui.imgCarte2.setImageResource((getCarteResource(tbJoueurDeux[numCoup].getNomImg())));
            ui.tvInfosCarte1.setText(String.format(Locale.getDefault(), "%s : %d points", tbJoueurUn[numCoup].getNom(), tbJoueurUn[numCoup].getValeur()));
            ui.tvInfosCarte2.setText(String.format(Locale.getDefault(), "%s : %d points", tbJoueurDeux[numCoup].getNom(), tbJoueurDeux[numCoup].getValeur()));
            numCoup++;
            ui.etNbPoints.setText("");
        } else {
            String message = "";
            if(scoreUn > scoreDeux) {
                message = String.format(Locale.getDefault(), "Le joueur Un a gagné avec %d point contre %d", scoreUn, scoreDeux);
            } else {
                if(scoreDeux > scoreUn) {
                    message = String.format(Locale.getDefault(), "Le joueur Deux a gagné avec %d point contre %d", scoreDeux, scoreUn);
                } else {
                    message = String.format(Locale.getDefault(), "Les 2 joueurs sont à égalité avec %d points chacun", scoreUn);

                }
            }
            ui.tvResultat.setText(message);
            // mettre à jour le score du joueur dans les préférences
            // récupérer le stockage des préférences
            SharedPreferences prefs =
                    PreferenceManager.getDefaultSharedPreferences(getBaseContext());
            // début de la transaction
            SharedPreferences.Editor editeur = prefs.edit();
            // modification du score
            editeur.putString("prefs_bataille_score", Integer.toString(nbReponsesCorrectes) + "/ " + NB_COUPS);
            // fin de la transaction
            editeur.commit();

            Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
            toast.show();
            Log.i("Tristan", "msg= " + message);
        }
    }



    private int getCarteResource(String nomImgCarte) {
        int resId = getResources().getIdentifier(nomImgCarte, "drawable", "fr.siotf.ludo");
        if(resId != 0) {
            return resId;
        }
        return R.drawable.back;
    }


    private void traitementReponse(int numClickReponse) {
        ui.tvScore1.setText(String.format(Locale.getDefault(), "%d points", scoreUn));
        ui.tvScore2.setText(String.format(Locale.getDefault(), "%d points", scoreDeux));

        String repJoueur = "";
        int nbRepCorrectes = 0;
        if(numClickReponse == numJoueurGagnantCoup) {
            repJoueur = String.format(Locale.getDefault(), "OUI, Joueur%d !", numJoueurGagnantCoup);
            nbRepCorrectes++;
        } else {
            repJoueur = String.format(Locale.getDefault(), "NON, c'est Joueur%d !", numJoueurGagnantCoup);
        }

        String strNbPoints = ui.etNbPoints.getText().toString();
        int nbPoints = 0;
        if(!TextUtils.isEmpty((strNbPoints))) {
            nbPoints = Integer.parseInt(strNbPoints);
        }
        String repNbPoints = "";
        if(nbPoints == nbPointsCoup) {
            repNbPoints = String.format(Locale.getDefault(), "OUI, %d points !", nbPointsCoup);
            nbRepCorrectes++;
        } else {
            repNbPoints = String.format(Locale.getDefault(), "NON, c'est %d points !", nbPointsCoup);
        }

        if(nbRepCorrectes == 2) {
            nbReponsesCorrectes++;
            ui.tvNbRepCorrectes.setText(String.format(Locale.getDefault(), "%d / %d", nbReponsesCorrectes, NB_COUPS));
        }

        if(nbRepCorrectes == 26) {
            demarrerJeux();
        }

        Toast toast = Toast.makeText(getApplicationContext(), repNbPoints + "  " + repJoueur, Toast.LENGTH_LONG);
        toast.show();
    }

    public void onClickbtJoueur1(View view) {
        traitementReponse(1);
        if(nbReponsesCorrectes == 3) {
            ui.btJouer.setEnabled(false);
            ui.btJoueur1.setEnabled(false);
            ui.btJoueur2.setEnabled(false);
            ui.btAucunJoueur.setEnabled(false);
            ui.imgCarte1.setImageResource(R.drawable.back);
            ui.imgCarte2.setImageResource(R.drawable.back);

        }

    }

    public void onClickbtJoueur2(View view) {
        traitementReponse(2);
        if(nbReponsesCorrectes == 3) {
            ui.btJouer.setEnabled(false);
            ui.btJoueur1.setEnabled(false);
            ui.btJoueur2.setEnabled(false);
            ui.btAucunJoueur.setEnabled(false);
            ui.imgCarte1.setImageResource(R.drawable.back);
            ui.imgCarte2.setImageResource(R.drawable.back);
        }

    }
    public void onClickbtAucunJoueur(View view) {
        traitementReponse(3);
    }
}





package fr.siomd.ludo;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import fr.siomd.ludo.dataaccess.DicoXml;
import fr.siomd.ludo.databinding.ActivityPenduBinding;
import fr.siomd.ludo.entity.Bourreau;
import fr.siomd.ludo.entity.Juge;

public class PenduActivity extends AppCompatActivity {
    private ActivityPenduBinding ui;
    private Juge leJuge;
    private Bourreau leBourreau;
    private int idBackground = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ui = ActivityPenduBinding.inflate(getLayoutInflater());
        setContentView(ui.getRoot());

        leJuge = new Juge(DicoXml.getLesthemes(getResources().getXml(R.xml.dico)));
        leBourreau = new Bourreau(leJuge);
        ui.buttonRejouer.setVisibility(View.INVISIBLE);
        ui.word.setText(leBourreau.getLeMotEnCours());
        ui.warning.setVisibility(View.INVISIBLE);
        ui.layout.setBackground(getResources().getDrawable(R.color.white));
        ui.nbPoint.setVisibility(View.INVISIBLE);
        ui.winAlert.setVisibility(View.INVISIBLE);
    }

    public void onClickbtEnvoyer(View laVue){
        String oldMotEnCours = leBourreau.getLeMotEnCours();
        String text = String.valueOf(ui.historiqueMot.getText());
        int longueurText = text.length();
        if (longueurText == 1){
            leBourreau.executer(text.charAt(0));
        }else{
            ui.warning.setVisibility(View.VISIBLE);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ui.warning.setVisibility(View.INVISIBLE);

                }
            }, 2000);
        }

        String newMotEnCours = leBourreau.getLeMotEnCours();
        String rebutTest = leBourreau.getLesLettresAuRebut();

        if (newMotEnCours == oldMotEnCours && longueurText == 1){
            Log.e("Les lettres au rebut :", rebutTest);
            ui.tvRebu.setText(leBourreau.getLesLettresAuRebut());
            idBackground ++;
            switch (idBackground){
                case 1:
                    ui.layout.setBackground(getResources().getDrawable(R.drawable.pendu1));
                    break;
                case 2:
                    ui.layout.setBackground(getResources().getDrawable(R.drawable.pendu2));
                    break;
                case 3:
                    ui.layout.setBackground(getResources().getDrawable(R.drawable.pendu3));
                    break;
                case 4:
                    ui.layout.setBackground(getResources().getDrawable(R.drawable.pendu4));
                    break;
                case 5:
                    ui.layout.setBackground(getResources().getDrawable(R.drawable.pendu5));
                    break;
                case 6:
                    ui.layout.setBackground(getResources().getDrawable(R.drawable.pendu6));
                    break;
            }
        }

        if (newMotEnCours != oldMotEnCours && longueurText == 1){
            ui.word.setText(leBourreau.getLeMotEnCours());
        }

        ui.historiqueMot.setText("");

        if (leBourreau.isGagne()){
            ui.layout.setBackground(getResources().getDrawable(R.drawable.win));
            ui.logoClipboard.setVisibility(View.INVISIBLE);
            ui.tvRebu.setVisibility(View.INVISIBLE);
            ui.btnSendValue.setVisibility(View.INVISIBLE);
            ui.historiqueMot.setVisibility(View.INVISIBLE);
            ui.buttonRejouer.setVisibility(View.VISIBLE);
            ui.word.setVisibility(View.INVISIBLE);

        }
        if (leBourreau.isPerdu()){
            ui.layout.setBackground(getResources().getDrawable(R.drawable.lost));
            ui.word.setVisibility(View.INVISIBLE);
            ui.logoClipboard.setVisibility(View.INVISIBLE);
            ui.tvRebu.setVisibility(View.INVISIBLE);
            ui.btnSendValue.setVisibility(View.INVISIBLE);
            ui.historiqueMot.setVisibility(View.INVISIBLE);
            ui.buttonRejouer.setVisibility(View.VISIBLE);
        }
    }

    public void onClickRestart(View laVue) {
        idBackground = 0;
        leJuge = new Juge(DicoXml.getLesthemes(getResources().getXml(R.xml.dico)));
        leBourreau = new Bourreau(leJuge);

        ui.word.setText(leBourreau.getLeMotEnCours());
        ui.layout.setBackground(getResources().getDrawable(R.color.white));
        ui.word.setVisibility(View.VISIBLE);
        ui.logoClipboard.setVisibility(View.VISIBLE);
        ui.tvRebu.setVisibility(View.VISIBLE);
        ui.tvRebu.setText("");
        ui.btnSendValue.setVisibility(View.VISIBLE);
        ui.historiqueMot.setVisibility(View.VISIBLE);
        ui.nbPoint.setVisibility(View.INVISIBLE);
        ui.winAlert.setVisibility(View.INVISIBLE);
        ui.buttonRejouer.setVisibility(View.INVISIBLE);
    }

}
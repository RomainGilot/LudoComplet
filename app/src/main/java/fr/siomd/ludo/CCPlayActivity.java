package fr.siomd.ludo;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import java.util.Random;
import java.util.Locale;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import fr.siomd.ludo.databinding.ActivityMainBinding;

import java.util.Random;

public class CCPlayActivity extends AppCompatActivity {


    private int result = 0;
    private int score = 0;
    private int life = 3;
    private ActivityMainBinding ui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ui = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(ui.getRoot());
        setContentView(R.layout.activity_ccplay);

        Button rejouer = findViewById(R.id.buttonRejouer);
        rejouer.setVisibility(View.INVISIBLE);

        EditText editText = findViewById(R.id.resultat);
        editText.setVisibility(View.INVISIBLE);

        Button btn = findViewById(R.id.buttonOk);
        btn.setVisibility(View.INVISIBLE);
    }


    public void creerCalcul() {
        TextView nb1 = findViewById(R.id.nb1);
        TextView ope = findViewById(R.id.operateur);
        TextView nb2 = findViewById(R.id.nb2);

        Random random = new Random();
        int number1 = random.nextInt(11);  // Génère un nombre aléatoire entre 0 et 20 inclus
        int number2 = random.nextInt(11);  // Génère un autre nombre aléatoire entre 0 et 20 inclus

        int operatorIndex = random.nextInt(3); // Génère un nombre aléatoire entre 0 et 2 inclus pour sélectionner l'opérateur arithmétique
        char operateur;

        switch (operatorIndex) {
            case 0:
                operateur = '+';
                result = number1 + number2;
                break;
            case 1:
                operateur = '-';
                result = number1 - number2;
                break;
            case 2:
                operateur = 'x';
                result = number1 * number2;
                break;
            default:
                operateur = '+';
                result = number1 + number2;
        }

        nb1.setText("" + number1);
        ope.setText("" + operateur);
        nb2.setText("" + number2);
    }

    public void afficherPartie() {
        EditText editText = findViewById(R.id.resultat);
        Button btn = findViewById(R.id.buttonOk);

        editText.setVisibility(View.INVISIBLE);
        btn.setVisibility(View.INVISIBLE);

        TextView nb1 = findViewById(R.id.nb1);
        nb1.setVisibility(View.INVISIBLE);

        TextView ope = findViewById(R.id.operateur);
        ope.setVisibility(View.INVISIBLE);

        TextView nb2 = findViewById(R.id.nb2);
        nb2.setVisibility(View.INVISIBLE);

        TextView message = findViewById(R.id.message);
        message.setText("C'est terminé !");

        TextView result = findViewById(R.id.resultatScore);
        result.setVisibility(View.VISIBLE);
        result.setText(score + " / 10");

        Button rejouer = findViewById(R.id.buttonRejouer);
        rejouer.setVisibility(View.VISIBLE);

        life = 3;
    }
    public void onBeginClick(View view) {
        result = 0;
        score = 0;
        Button button = (Button) view;
        button.setVisibility(View.INVISIBLE);

        EditText editText = findViewById(R.id.resultat);
        editText.setVisibility(View.VISIBLE);

        TextView textScore = findViewById(R.id.textScore);
        textScore.setVisibility(View.INVISIBLE);
        textScore.setText(score + "/10");


        Button btn = findViewById(R.id.buttonOk);
        btn.setVisibility(View.VISIBLE);

        TextView chrono = findViewById(R.id.chrono);
        chrono.setVisibility(View.INVISIBLE);

        TextView nb1 = findViewById(R.id.nb1);
        nb1.setVisibility(View.INVISIBLE);

        TextView ope = findViewById(R.id.operateur);
        ope.setVisibility(View.INVISIBLE);

        TextView nb2 = findViewById(R.id.nb2);
        nb2.setVisibility(View.INVISIBLE);

        TextView health = findViewById(R.id.texthealth);
        health.setText(life  + "");

        TextView resultScore = findViewById(R.id.resultatScore);
        resultScore.setVisibility(View.INVISIBLE);
        TextView message = findViewById(R.id.message);
        message.setText("C'est parti !");
        message.postDelayed(new Runnable() {
            @Override
            public void run() {
                message.setText("Calculez !");
            }
        }, 3000);

        TextView textCountDown = findViewById(R.id.textView6);
        final int[] countdownValues = {3, 2, 1};
        final int[] delayTimes = {0, 1000, 2000};

        Runnable countdownRunnable = new Runnable() {
            int countdownIndex = 0;

            @Override
            public void run() {
                if (countdownIndex < countdownValues.length) {
                    textCountDown.setVisibility(View.VISIBLE);
                    textCountDown.setText(Integer.toString(countdownValues[countdownIndex]));
                    countdownIndex++;
                    textCountDown.postDelayed(this, delayTimes[countdownIndex-1]);
                } else {
                    textCountDown.setVisibility(View.INVISIBLE);
                    textScore.setVisibility(View.VISIBLE);
                    chrono.setVisibility(View.VISIBLE);
                    nb1.setVisibility(View.VISIBLE);
                    ope.setVisibility(View.VISIBLE);
                    nb2.setVisibility(View.VISIBLE);

                    new CountDownTimer(60000, 1000) {  // Crée un nouveau minuteur de 1 minute, avec des ticks toutes les 1000 millisecondes (soit 1 seconde)
                        public void onTick(long millisUntilFinished) {  // Appelé chaque tick, avec le nombre de millisecondes restantes jusqu'à la fin du minuteur
                            long secondsLeft = millisUntilFinished / 1000;  // Calcule le nombre de secondes restantes en divisant les millisecondes restantes par 1000
                            chrono.setText(secondsLeft + "");

                            if(secondsLeft == 0) {
                                afficherPartie();
                            }
                        }

                        public void onFinish() {  // Appelé lorsque le minuteur est terminé
                            System.out.println("Le minuteur est terminé !");
                        }
                    }.start();  // Démarre le minuteur
                }
            }
        };
        textCountDown.post(countdownRunnable);
        creerCalcul();
    }

    public void onOkClick(View view) {
        EditText editText = findViewById(R.id.resultat);
        editText.setVisibility(View.VISIBLE);
        String userInput = editText.getText().toString();
        int result2 = Integer.parseInt(userInput);
        ImageView bonneReponse = findViewById(R.id.bonnereponse);
        ImageView mauvaiseReponse = findViewById(R.id.mauvaisereponse);
        Button btn = findViewById(R.id.buttonOk);
        TextView textScore = findViewById(R.id.textScore);
        if(result == result2) {
            bonneReponse.setVisibility(View.VISIBLE);
            score += 1;
            textScore.setText(score + "");
            editText.setText("");
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    bonneReponse.setVisibility(View.INVISIBLE);

                    if(score != 10) {
                        creerCalcul();
                    } else {
                        afficherPartie();
                    }
                }
            }, 2000);
        } else {
            life -= 1;
            TextView health = findViewById(R.id.texthealth);
            health.setText(life  + "");

            if(life == 0) {
                afficherPartie();
            }
            mauvaiseReponse.setVisibility(View.VISIBLE);
            editText.setText("");
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mauvaiseReponse.setVisibility(View.INVISIBLE);
                }
            }, 2000);
        }

    }
}
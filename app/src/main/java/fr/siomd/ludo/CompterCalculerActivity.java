package fr.siomd.ludo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CompterCalculerActivity extends AppCompatActivity {

    private Button soundButton;
    private MediaPlayer mediaPlayer;
    private boolean soundOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compter_calculer);

     

    }

    public void onReglesClick(View view) {
        Intent intent = new Intent(this, CCRegleActivity.class);
        startActivity(intent);
    }

    public void onPlayClick(View view) {
        Intent intent = new Intent(this, CCPlayActivity.class);
        startActivity(intent);
    }

}
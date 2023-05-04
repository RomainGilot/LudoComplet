package fr.siomd.ludo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pmenu);
    }

    public void onReglesClick(View view) {
        Intent intent = new Intent(this, PRegleActivity.class);
        startActivity(intent);
    }

    public void onPlayClick(View view) {
        Intent intent = new Intent(this, PenduActivity.class);
        startActivity(intent);
    }
}
package fr.siomd.ludo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PRegleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregle);
    }

    public void onReglesClick(View view) {
        Intent intent = new Intent(this, PMenuActivity.class);
        startActivity(intent);
    }
}
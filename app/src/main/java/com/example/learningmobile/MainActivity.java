package com.example.learningmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnLandlord1 = findViewById(R.id.btnLandlord1);
        Button btnLandlord2 = findViewById(R.id.btnLandlord2);

        btnLandlord1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, FormularyActivity.class);
                intent.putExtra("Landlord", "mae");
                startActivity(intent);

            }
        });

        btnLandlord2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, FormularyActivity.class);
                intent.putExtra("Landlord", "batian");
                startActivity(intent);

            }
        });


    }


}
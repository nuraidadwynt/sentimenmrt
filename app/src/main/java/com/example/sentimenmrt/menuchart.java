package com.example.sentimenmrt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class menuchart extends AppCompatActivity implements View.OnClickListener {
    private CardView realtimeCard,collectedCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuchart);

        //mendefinisikan card
        realtimeCard = (CardView) findViewById(R.id.real);
        collectedCard = (CardView) findViewById(R.id.coll);

        //buat aktifin on click listener
        //realtimeCard.setOnClickListener(this);
        realtimeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Ke klik realtimechart");
                Intent intent = new Intent(menuchart.this, realtimechart.class);
                startActivity(intent);
            }
        });

        collectedCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Ke klik grafik");
                Intent intent = new Intent(menuchart.this, grafik.class);
                startActivity(intent);
            }
        });
        //collectedCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }

//    @Override
//    public void onClick(View v) {
//        Intent i;
//
//        switch (v.getId()) {
//            case R.id.realtimechart:
//                System.out.println("Ke klik realtimechart");
//                i = new Intent(this, realtimechart.class);
//                startActivity(i);
//                break;
//
//            case R.id.barchart:
//                i = new Intent(this, grafik.class);
//                startActivity(i);
//                break;
//        }
//    }
}

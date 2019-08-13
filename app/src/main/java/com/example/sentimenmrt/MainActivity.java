package com.example.sentimenmrt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.GridLayout;
import android.widget.Toast;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private CardView graphCard,tableCard,wordcloudCard,pieCard,aboutCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mendefinisikan card
        graphCard = (CardView) findViewById(R.id.grafik);
        tableCard = (CardView) findViewById(R.id.table);
        wordcloudCard = (CardView) findViewById(R.id.wordcloud);
        pieCard = (CardView) findViewById(R.id.pie);
        aboutCard = (CardView) findViewById(R.id.about);

        //buat aktifin on click listener
        graphCard.setOnClickListener(this);
        tableCard.setOnClickListener(this);
        wordcloudCard.setOnClickListener(this);
        pieCard.setOnClickListener(this);
        aboutCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
         Intent i;

         switch (v.getId()) {
             case R.id.grafik :
                 i = new Intent(this,grafik.class);
                 startActivity(i);
                 break;
             case R.id.table :
                 i = new Intent(this,Table.class);
                 startActivity(i);
                 break;
             case R.id.wordcloud:
                 i = new Intent(this,Wordcloud.class);
                 startActivity(i);
                 break;
             case R.id.pie :
                 i = new Intent(this,Pie.class);
                 startActivity(i);
                 break;
             case R.id.about :
                 i = new Intent(this,About.class);
                 startActivity(i);
                 break;

         }

    }
}

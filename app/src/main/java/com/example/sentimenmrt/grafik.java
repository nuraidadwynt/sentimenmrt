package com.example.sentimenmrt;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class grafik extends AppCompatActivity {
    public static class Sentimen {
        public int negative;
        public int positive;
        public int netral;

        //ini buat construct nya
        public Sentimen() {
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("sentimen");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Sentimen st = dataSnapshot.getValue(Sentimen.class);
                System.out.println(st.negative);

                setContentView(R.layout.activity_grafik);
                BarChart chart = findViewById(R.id.barchart);
                chart.setHighlightFullBarEnabled(true);

                ArrayList positif = new ArrayList();
                positif.add(new BarEntry(0, st.positive));

                ArrayList negatif = new ArrayList();
                negatif.add(new BarEntry(1, st.negative));

                ArrayList netral = new ArrayList();
                negatif.add(new BarEntry(2, st.netral));

                BarDataSet dataSet = new BarDataSet(positif, "Positif ");
                BarDataSet dataSet2 = new BarDataSet(negatif, "Negatif");
                BarDataSet dataSet3 = new BarDataSet(netral, "Netral");

                BarData data = new BarData(dataSet, dataSet2, dataSet3);

                chart.setData(data);

                dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                dataSet3.setColors(Color.GRAY);
                dataSet2.setValueTextSize(20f);
                dataSet2.setValueTextColor(Color.BLACK);
                dataSet.setValueTextSize(20f);
                dataSet.setValueTextColor(Color.BLACK);
                dataSet3.setValueTextSize(20f);
                dataSet3.setValueTextColor(Color.BLACK);
                chart.animateY(1000);
                chart.getXAxis().setEnabled(false);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });



    }
}
package com.example.sentimenmrt;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Pie extends AppCompatActivity {
    public static class Sentimen {

        public int negative;
        public int positive;
        public int netral;

        //ini buat construct nya jgn di apus ya :D
        public Sentimen() {
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get data :
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("sentimen");
        // Read from the database
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Sentimen st = dataSnapshot.getValue(Sentimen.class);
                System.out.println(st.negative);
                setContentView(R.layout.activity_pie);

                PieChart pieChart = findViewById(R.id.piechart);
                pieChart.setHoleRadius(0);

                ArrayList NoOfEmp = new ArrayList();

                //PieEntry as = new PieEntry(NoOfEmp);
                NoOfEmp.add(new PieEntry(st.negative, "negative"));
                NoOfEmp.add(new PieEntry(st.positive, "positive"));
                NoOfEmp.add(new PieEntry(st.netral, "netral"));

                PieDataSet dataSet = new PieDataSet(NoOfEmp, "Sentiment Calcultion Result");

                dataSet.setValueTextSize(30f);
                dataSet.setValueTextColor(Color.WHITE);

                PieData data = new PieData(dataSet);

                pieChart.setData(data);

                dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                pieChart.animateXY(3000, 3000);
                pieChart.setTransparentCircleRadius(0);
                pieChart.setHighlightPerTapEnabled(true);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

    }
}

package com.example.sentimenmrt;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

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

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class realtimechart extends AppCompatActivity {
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
        DatabaseReference ref = database.getReference("sentimen_realtime");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                setContentView(R.layout.activity_realtimechart);
                grafik.Sentimen st = dataSnapshot.getValue(grafik.Sentimen.class);
                System.out.println(st.negative);
                String date = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());

                TextView tanggal = findViewById(R.id.tanggal);
                System.out.println(date);
                System.out.println(tanggal);
                tanggal.setText(date);

                BarChart chart = findViewById(R.id.realtimechart);
                chart.setHighlightFullBarEnabled(true);

                ArrayList positif = new ArrayList();
                positif.add(new BarEntry(0, st.positive));

                ArrayList negatif = new ArrayList();
                negatif.add(new BarEntry(1, st.negative));

                ArrayList netral = new ArrayList();
                netral.add(new BarEntry(2, st.netral));

                BarDataSet dataSet = new BarDataSet(positif, "Positif ");
                BarDataSet dataSet2 = new BarDataSet(negatif, "Negatif");
                BarDataSet dataSet3 = new BarDataSet(netral, "Netral");

                BarData data = new BarData(dataSet, dataSet2, dataSet3);

                chart.setData(data);

                //dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                dataSet.setColors(Color.parseColor("#21a315"));
                dataSet2.setColors(Color.RED);
                dataSet3.setColors(Color.parseColor("#ffdc0c"));

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

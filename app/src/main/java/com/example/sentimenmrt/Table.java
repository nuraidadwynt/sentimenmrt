package com.example.sentimenmrt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Table extends AppCompatActivity {
    private static final String TAG = "Table";
    //vars :
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private ArrayList<String> mTweetsnya = new ArrayList<>();
    private ArrayList<String> mSentimennya = new ArrayList<>();
    private ArrayList<String> mtanggal = new ArrayList<>();
    RecyclerViewAdapter baseAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        Log.d(TAG, "onCreate: Started");

        initRecyclerView();

        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("MRT2");
        Query query=databaseReference.orderByChild("id").limitToLast(10);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i = 0;
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    i++;
                    String ava = ds.child("ava").getValue(String.class);
                    String user = ds.child("user").getValue(String.class);
                    String sentimen = ds.child("sentimen").getValue(String.class);
                    String original_text = ds.child("original_text").getValue(String.class);
                    String tglnya = ds.child("created_at").getValue(String.class);

                    mImageUrls.add(ava);
                    mNames.add('@'+user);
                    mTweetsnya.add(original_text);
                    mSentimennya.add(sentimen);
                    mtanggal.add(tglnya);
                    Log.d(TAG, "onDataChange: "+original_text);
                }
                //aneh anjir :
                baseAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        baseAdapter = new RecyclerViewAdapter(mtanggal,mNames,mImageUrls,mTweetsnya,mSentimennya,this);
        recyclerView.setAdapter(baseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}

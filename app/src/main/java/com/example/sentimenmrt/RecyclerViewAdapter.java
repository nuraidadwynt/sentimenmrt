package com.example.sentimenmrt;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.viewHolder>{
    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> mImagesName = new ArrayList<>();
    private ArrayList<String> mImages = new ArrayList<>();
    private ArrayList<String> mTweets = new ArrayList<>();
    private ArrayList<String> mSentimen = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(ArrayList<String> mImagesName, ArrayList<String> mImages, ArrayList<String> mTweets, ArrayList<String> mSentimen, Context mContext) {
        this.mImagesName = mImagesName;
        this.mImages = mImages;
        this.mTweets = mTweets;
        this.mSentimen = mSentimen;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        viewHolder holder = new viewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: "+mImagesName);
        Glide.with(mContext)
                .asBitmap().load(mImages.get(position)).into(holder.image);
        holder.imagename.setText(mImagesName.get(position));
        holder.tweets.setText(mTweets.get(position));
        holder.sentimennya.setText(mSentimen.get(position));

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on :" + mImagesName.get(position));

                Toast.makeText(mContext,mImagesName.get(position), Toast.LENGTH_SHORT);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mImagesName.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        CircleImageView image;
        TextView imagename, tweets, sentimennya;
        RelativeLayout parentLayout;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            imagename = itemView.findViewById(R.id.imagename);
            tweets = itemView.findViewById(R.id.tweets);
            sentimennya = itemView.findViewById(R.id.sentimen_nya);
            parentLayout = itemView.findViewById(R.id.parentLayout);
        }
    }
}

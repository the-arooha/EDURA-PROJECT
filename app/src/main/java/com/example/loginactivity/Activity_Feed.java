package com.example.loginactivity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Activity_Feed extends Activity {
    public ThreadLocal<RecyclerView> recyclerView;

    {
        if (Build.VERSION_CODES.O <= Build.VERSION.SDK_INT) {
            recyclerView = ThreadLocal.withInitial(() -> (RecyclerView) this.findViewById(R.id.recyclerView));
        }
    }

    ArrayList<ModelFeed> modelFeedArrayList=new ArrayList<>();
    AdapterFeed adapterFeed;

    public void Activity_Feed(ThreadLocal<RecyclerView> recyclerView) {
        this.recyclerView = recyclerView;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        recyclerView.set(findViewById(R.id.recyclerView));

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.get().setLayoutManager(layoutManager);

        adapterFeed=new AdapterFeed(this,modelFeedArrayList);
        recyclerView.get().setAdapter(adapterFeed);

        populateRecyclerView();
    }

    public void populateRecyclerView(){
        ModelFeed modelFeed = new ModelFeed(1,9,2,R.drawable.ic_propic1,R.drawable.img_post1,
                "Richard Thomas","2 hrs","The cars we drive say a lot about us.");
        modelFeedArrayList.add(modelFeed);
        modelFeed = new ModelFeed(2, 26, 6, R.drawable.ic_propic2, 0,
                "Karun Shrestha", "9 hrs", "Don't be Afraid of your fairs.They're not there to scare you.They're there to let you know that something is worth it.");
        modelFeedArrayList.add(modelFeed);
        modelFeed = new ModelFeed(3, 17, 2, R.drawable.ic_propic3, R.drawable.img_post2,
                "Lakshya Ram", "13 hrs", "That Reflection!!!");
        modelFeedArrayList.add(modelFeed);

        adapterFeed.notifyDataSetChanged();
    }
}

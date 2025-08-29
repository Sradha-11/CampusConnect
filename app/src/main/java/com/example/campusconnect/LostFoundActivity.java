package com.example.campusconnect;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class LostFoundActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LostFoundAdapter adapter;
    private List<LostFoundItem> itemList;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_found);

        recyclerView = findViewById(R.id.recyclerViewLostFound);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        itemList = new ArrayList<>();
        adapter = new LostFoundAdapter(this, itemList);
        recyclerView.setAdapter(adapter);

        db = FirebaseFirestore.getInstance();

        fetchLostFoundItems();
    }

    private void fetchLostFoundItems() {
        db.collection("LostFound")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    itemList.clear();
                    for (DocumentSnapshot snapshot : queryDocumentSnapshots) {
                        LostFoundItem item = snapshot.toObject(LostFoundItem.class);
                        if (item != null) {
                            itemList.add(item);
                        }
                    }
                    adapter.notifyDataSetChanged();

                    if (itemList.isEmpty()) {
                        Toast.makeText(this, "No Lost & Found items available", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(e -> {
                    Log.e("FirestoreError", "Error fetching LostFound", e);
                    Toast.makeText(this, "Failed to load data", Toast.LENGTH_SHORT).show();
                });
    }
}

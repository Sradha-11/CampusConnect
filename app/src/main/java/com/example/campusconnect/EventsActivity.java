package com.example.campusconnect;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class EventsActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> eventList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        listView = findViewById(R.id.listViewEvents);
        eventList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, eventList);
        listView.setAdapter(adapter);

        db = FirebaseFirestore.getInstance();

        fetchEvents();
    }

    private void fetchEvents() {
        db.collection("Events")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            eventList.clear();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String name = document.getString("Name");
                                String date = document.getString("Date");
                                String location = document.getString("Location");

                                if (name != null) {
                                    eventList.add(name + "\n" + date + "\n" + location);
                                }
                            }
                            adapter.notifyDataSetChanged();

                            if (eventList.isEmpty()) {
                                Toast.makeText(EventsActivity.this, "No events found", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Log.e("FirestoreError", "Error getting events: ", task.getException());
                        }
                    }
                });
    }
}

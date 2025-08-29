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

public class AnnouncementsActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> announcementList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcements);

        listView = findViewById(R.id.listViewAnnouncements);
        announcementList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, announcementList);
        listView.setAdapter(adapter);

        db = FirebaseFirestore.getInstance();

        fetchAnnouncements();
    }

    private void fetchAnnouncements() {
        db.collection("Announcements")  // must match Firestore collection name
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            announcementList.clear();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String title = document.getString("Title");
                                String description = document.getString("Description");
                                String date = document.getString("Date");

                                if (title != null && description != null) {
                                    announcementList.add(title + "\n" + description + "\n" + date);
                                }
                            }
                            adapter.notifyDataSetChanged();

                            if (announcementList.isEmpty()) {
                                Toast.makeText(AnnouncementsActivity.this, "No announcements found", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Log.e("FirestoreError", "Error getting documents: ", task.getException());
                            Toast.makeText(AnnouncementsActivity.this, "Error fetching data", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}

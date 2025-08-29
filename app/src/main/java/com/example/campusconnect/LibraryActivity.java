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

public class LibraryActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> bookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        listView = findViewById(R.id.listViewLibrary);
        bookList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, bookList);
        listView.setAdapter(adapter);

        db = FirebaseFirestore.getInstance();

        fetchLibraryBooks();
    }

    private void fetchLibraryBooks() {
        db.collection("Library Requests")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            bookList.clear();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String book = document.getString("Book");
                                String status = document.getString("Status");
                                String student = document.getString("Student");

                                if (book != null) {
                                    bookList.add(book + " - " + student + "\nStatus: " + status);
                                }
                            }
                            adapter.notifyDataSetChanged();

                            if (bookList.isEmpty()) {
                                Toast.makeText(LibraryActivity.this, "No requests found", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Log.e("FirestoreError", "Error getting documents: ", task.getException());
                        }
                    }
                });

    }
}

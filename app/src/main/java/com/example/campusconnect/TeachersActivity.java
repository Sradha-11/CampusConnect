package com.example.campusconnect;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class TeachersActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TeacherAdapter adapter;
    private ArrayList<Teacher> teacherList;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers);

        recyclerView = findViewById(R.id.recyclerViewTeachers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        teacherList = new ArrayList<>();
        adapter = new TeacherAdapter(teacherList, this);
        recyclerView.setAdapter(adapter);

        db = FirebaseFirestore.getInstance();

        loadTeachers();
    }

    private void loadTeachers() {
        db.collection("Faculty")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    if (!queryDocumentSnapshots.isEmpty()) {
                        teacherList.clear();
                        for (DocumentSnapshot d : queryDocumentSnapshots) {
                            Teacher t = d.toObject(Teacher.class);
                            if (t != null) {
                                t.setId(d.getId()); // store Firestore document ID
                                teacherList.add(t);
                            }
                        }
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(TeachersActivity.this, "No Faculty Found", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(e ->
                        Toast.makeText(TeachersActivity.this, "Failed: " + e.getMessage(), Toast.LENGTH_SHORT).show()
                );
    }
}

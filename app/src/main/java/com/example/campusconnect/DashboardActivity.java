package com.example.campusconnect;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.auth.FirebaseAuth;

public class DashboardActivity extends AppCompatActivity {

    // 6 tiles are CardViews, Logout is a Button
    private CardView btnAnnouncements, btnEvents, btnLibrary, btnSchedule, btnLostFound, btnTeachers;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Bind views (IDs must match your activity_dashboard.xml exactly)
        btnAnnouncements = findViewById(R.id.btnAnnouncements);
        btnEvents       = findViewById(R.id.btnEvents);
        btnLibrary      = findViewById(R.id.btnLibrary);
        btnSchedule     = findViewById(R.id.btnSchedule);
        btnLostFound    = findViewById(R.id.btnLostFound);
        btnTeachers     = findViewById(R.id.btnTeachers);
        btnLogout       = findViewById(R.id.btnLogout);

        // Null-safety guard (prevents crash if XML was changed)
        if (btnAnnouncements == null || btnEvents == null || btnLibrary == null ||
                btnSchedule == null || btnLostFound == null || btnTeachers == null || btnLogout == null) {
            Toast.makeText(this, "Dashboard layout mismatch. Check IDs.", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        // Navigate to each feature (replace targets with your actual activities)
        btnAnnouncements.setOnClickListener(v ->
                startActivity(new Intent(this, AnnouncementsActivity.class)));

        btnEvents.setOnClickListener(v ->
                startActivity(new Intent(this, EventsActivity.class)));

        btnLibrary.setOnClickListener(v ->
                startActivity(new Intent(this, LibraryActivity.class)));

        btnSchedule.setOnClickListener(v ->
                startActivity(new Intent(this, ScheduleActivity.class)));

        btnLostFound.setOnClickListener(v ->
                startActivity(new Intent(this, LostFoundActivity.class)));

        btnTeachers.setOnClickListener(v ->
                startActivity(new Intent(this, TeachersActivity.class)));

        // Logout → back to LoginActivity (clear back stack)
        btnLogout.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();

            // After logout → go back to Welcome Page (MainActivity)
            Intent intent = new Intent(DashboardActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });

    }
}

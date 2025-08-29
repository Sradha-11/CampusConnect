package com.example.campusconnect;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ScheduleActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ScheduleAdapter adapter;
    private List<Schedule> scheduleList;
    private Spinner daySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        recyclerView = findViewById(R.id.recyclerViewSchedule);
        daySpinner = findViewById(R.id.daySpinner);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Days dropdown
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, days);
        daySpinner.setAdapter(spinnerAdapter);

        // Listener for day change
        daySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedDay = days[i];
                loadScheduleForDay(selectedDay);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
    }

    private void loadScheduleForDay(String day) {
        scheduleList = new ArrayList<>();

        switch (day) {
            case "Monday":
                scheduleList.add(new Schedule("09:10 AM-10:00 AM", "ToC"));
                scheduleList.add(new Schedule("10:00 AM- 12:30 PM", "OS Lab / ML Lab"));
                scheduleList.add(new Schedule("12:30 PM-1:20 PM", "LUNCH"));
                scheduleList.add(new Schedule("1:20 PM-2:10 PM", "AI&ML"));
                scheduleList.add(new Schedule("2:10 PM-3:00 PM", "EES"));
                scheduleList.add(new Schedule("3:00 PM-3:50 PM", "PPT"));
                break;
            case "Tuesday":
                scheduleList.add(new Schedule("9:10 AM-10:00 AM", "AI&ML"));
                scheduleList.add(new Schedule("10:00 AM-10:50", "ToC"));
                scheduleList.add(new Schedule("10:50 AM-11:40 AM", "OS"));
                scheduleList.add(new Schedule("11:40 AM-12:30 PM", "WT"));
                scheduleList.add(new Schedule("12:30 PM-1:20 PM", "LUNCH"));
                scheduleList.add(new Schedule("1:20 PM-3:50 PM", "ToC Lab / SEMINAR"));
                break;
            case "Wednesday":
                scheduleList.add(new Schedule("09:10 AM-10:00 AM", "WT"));
                scheduleList.add(new Schedule("10:00 AM-10:50 AM", "AI&ML"));
                scheduleList.add(new Schedule("10:50 AM-11:40 AM", "ToC"));
                scheduleList.add(new Schedule("11:40 AM-12:30 PM", "OS"));
                scheduleList.add(new Schedule("12:30 PM-1:20 PM", "LUNCH"));
                scheduleList.add(new Schedule("1:20 PM-2:10 PM", "WT"));
                scheduleList.add(new Schedule("2:10 PM-3:00 PM", "EES"));
                scheduleList.add(new Schedule("3:00 PM-3:50 PM", "LIBRARY"));
                break;
            case "Thursday":
                scheduleList.add(new Schedule("9:10 AM-10:00", "ToC"));
                scheduleList.add(new Schedule("10:00 AM-10:50 AM", "ED"));
                scheduleList.add(new Schedule("10:50 AM-11:40 AM", "OS"));
                scheduleList.add(new Schedule("11:40 AM-12:30 PM", "EES"));
                scheduleList.add(new Schedule("12:30 PM-1:20 PM", "LUNCH"));
                scheduleList.add(new Schedule("1:20 PM-3:50 PM", "OS Lab / ML Lab"));
                break;
            case "Friday":
                scheduleList.add(new Schedule("09:10 AM-10:00", "AI&ML"));
                scheduleList.add(new Schedule("10:00 AM-10:50", "WT"));
                scheduleList.add(new Schedule("10:50 AM-11:40 AM", "ED"));
                scheduleList.add(new Schedule("11:40 AM-12:30 PM", "EES"));
                scheduleList.add(new Schedule("12:30 PM-1:20 PM", "LUNCH"));
                scheduleList.add(new Schedule("1:20 PM-3:50 PM", "ToC Lab / SEMINAR"));
                break;
            case "Saturday":
                scheduleList.add(new Schedule("09:10 AM-10:00 AM", "ED"));
                scheduleList.add(new Schedule("10:00 AM-10:50 AM", "AI&ML"));
                scheduleList.add(new Schedule("10:50 AM-11:40 AM", "ToC"));
                scheduleList.add(new Schedule("11:40 AM-12:30 PM", "OS"));
                scheduleList.add(new Schedule("12:30 PM-1:20 PM", "LUNCH"));
                scheduleList.add(new Schedule("1:20 PM-2:10 PM", "ED"));
                scheduleList.add(new Schedule("2:10 PM-3:00 PM", "YOGA"));
                scheduleList.add(new Schedule("3:00 PM-3:50 PM", "WT"));
                break;
        }

        // ✅ Sort by time
        Collections.sort(scheduleList, new Comparator<Schedule>() {
            @Override
            public int compare(Schedule s1, Schedule s2) {
                return s1.getTime().compareTo(s2.getTime());
            }
        });

        adapter = new ScheduleAdapter(scheduleList);
        recyclerView.setAdapter(adapter);
    }
}

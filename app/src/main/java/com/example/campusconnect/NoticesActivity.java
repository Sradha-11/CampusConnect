package com.example.campusconnect;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class NoticesActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    NoticeAdapter adapter;
    List<Notice> noticeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notices);

        recyclerView = findViewById(R.id.recyclerViewNotices);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Dummy data
        noticeList = new ArrayList<>();
        noticeList.add(new Notice("Fee Payment", "Last date for semester fee is 30th Aug"));
        noticeList.add(new Notice("Holiday", "Independence Day Holiday on 15th Aug"));
        noticeList.add(new Notice("Workshop", "AI/ML Workshop registration open"));

        adapter = new NoticeAdapter(noticeList);
        recyclerView.setAdapter(adapter);
    }
}

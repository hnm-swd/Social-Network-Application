package com.example.social_network_friendy;

import android.app.Activity;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NotificationActivity extends Activity {
    private RecyclerView recyclerView;
    private NotificationAdapter adapter;
    private List<NotificationItem> notificationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        notificationList = new ArrayList<>();
        adapter = new NotificationAdapter(notificationList);
        recyclerView.setAdapter(adapter);

        loadNotifications();
    }

    private void loadNotifications() {
        // Thêm dữ liệu mẫu cho các thông báo
        notificationList.add(new NotificationItem("User1 liked your post", "Just now"));
        notificationList.add(new NotificationItem("User2 commented on your photo", "5 minutes ago"));
        notificationList.add(new NotificationItem("User3 sent you a friend request", "1 hour ago"));

        adapter.notifyDataSetChanged();
    }
}

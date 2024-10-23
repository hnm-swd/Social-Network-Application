package com.example.social_network_friendy;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
public class CommentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        // Nhận dữ liệu từ Intent
        Intent intent = getIntent();
        String jobTitle = intent.getStringExtra("jobTitle");

        // Hiển thị tiêu đề công việc trong CommentActivity
//        LinearLayout jobTitleTextView = findViewById(R.id.jobTitleTextView);
//        jobTitleTextView.layout(jobTitle);
    }
}

package com.example.social_network_friendy;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
public class CommentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        Intent intent = getIntent();
        String jobTitle = intent.getStringExtra("jobTitle");
        ArrayList<String> comments = intent.getStringArrayListExtra("comments");

        // Thiết lập các thành phần giao diện của bạn ở đây, ví dụ như RecyclerView cho danh sách bình luận.
    }
}

package com.example.social_network_friendy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PostDetailActivity_MyProfile extends Activity {
    private TextView usernameTextView, contentTextView, timeTextView;
    private ImageView postImageView;
    private EditText commentEditText;
    private Button submitCommentButton;
    private int postPosition; // Lưu vị trí bài viết

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_post_detail);

        // Nhận dữ liệu từ Intent
        String username = getIntent().getStringExtra("username");
        String content = getIntent().getStringExtra("content");
        String time = getIntent().getStringExtra("time");
        int imageResId = getIntent().getIntExtra("imageResId", -1);
        postPosition = getIntent().getIntExtra("postPosition", -1); // Nhận vị trí bài viết

        // Kết nối các view
        usernameTextView = findViewById(R.id.usernameTextView);
//        contentTextView = findViewById(R.id.contentTextView);
//        timeTextView = findViewById(R.id.timeTextView);
//        postImageView = findViewById(R.id.postImageView);
//        commentEditText = findViewById(R.id.commentEditText);
//        submitCommentButton = findViewById(R.id.submitComment);

        // Thiết lập dữ liệu vào view
        usernameTextView.setText(username);
        contentTextView.setText(content);
        timeTextView.setText(time);


        // Xử lý sự kiện nhấn nút gửi bình luận
        submitCommentButton.setOnClickListener(v -> {
            String comment = commentEditText.getText().toString().trim();
            if (!comment.isEmpty()) {
                // Gửi bình luận (ví dụ: hiển thị thông báo)
                sendComment(comment);
                commentEditText.setText(""); // Xóa nội dung bình luận sau khi gửi
            } else {
                Toast.makeText(this, "Please enter a comment.", Toast.LENGTH_SHORT).show();
            }
        });
    }
    // Hàm gửi bình luận và trả về cho MyProfileActivity
    private void sendComment(String comment) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("newComment", comment);
        resultIntent.putExtra("postPosition", postPosition); // Gửi vị trí bài viết
        setResult(Activity.RESULT_OK, resultIntent);
        finish(); // Đóng Activity này và quay lại MyProfileActivity
    }



}

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
    private int postPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String username = getIntent().getStringExtra("username");
        String content = getIntent().getStringExtra("content");
        String time = getIntent().getStringExtra("time");

        postPosition = getIntent().getIntExtra("postPosition", -1);

        usernameTextView = findViewById(R.id.usernameTextView);

        usernameTextView.setText(username);
        contentTextView.setText(content);
        timeTextView.setText(time);

        submitCommentButton.setOnClickListener(v -> {
            String comment = commentEditText.getText().toString().trim();
            if (!comment.isEmpty()) {
                sendComment(comment);
                commentEditText.setText("");
            } else {
                Toast.makeText(this, "Please enter a comment.", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void sendComment(String comment) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("newComment", comment);
        resultIntent.putExtra("postPosition", postPosition);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }
}

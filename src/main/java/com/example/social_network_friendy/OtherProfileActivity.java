package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OtherProfileActivity extends AppCompatActivity {
    private Button followButton;
    private boolean isFollowing = false; // Keep track of follow state
    private RecyclerView postsRecyclerView;
    private Adapter adapter;
    private ArrayList<Post> posts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otherprofile);

        followButton = findViewById(R.id.followButton);
        postsRecyclerView = findViewById(R.id.postsRecyclerView);

        // Set initial button state
        updateFollowButton();

        // Set up OnClickListener for follow button
        followButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle follow state
                isFollowing = !isFollowing;

                // Update button appearance
                updateFollowButton();
            }
        });

        // Initialize posts
        posts = new ArrayList<>();
//        posts.add(new Post("User1", "2 hours ago", "Hello World!", R.drawable.profileimage, R.drawable.postimage, 100, 50));
//        posts.add(new Post("User2", "5 hours ago", "Sample Post", R.drawable.profileimage, R.drawable.postimage, 150, 30));

        // Set up RecyclerView
        adapter = new Adapter(this, posts);
        postsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        postsRecyclerView.setAdapter(adapter);
    }

    private void updateFollowButton() {
        if (isFollowing) {
            // Set to "Đang theo dõi" with white background
            followButton.setText("Đang theo dõi");
            followButton.setBackgroundColor(Color.WHITE);
            followButton.setTextColor(Color.BLACK); // Set text color to black
        } else {
            // Set to "Theo dõi" with black background
            followButton.setText("Theo dõi");
            followButton.setBackgroundColor(Color.BLACK);
            followButton.setTextColor(Color.WHITE); // Set text color to white
        }
    }
}

package com.example.social_network_friendy;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivityScreen extends Activity {


    private RecyclerView recyclerView;
    private PostAdapter postAdapter;
    private List<Post> postList;
    private DatabaseReference postsRef;
    private DatabaseReference usersRef;

    private TextView appTitle;
    private String userId; // To store the retrieved userId

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity);

        // Firebase Database reference
        postsRef = FirebaseDatabase.getInstance().getReference("posts");
        usersRef = FirebaseDatabase.getInstance().getReference("users");

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        // Initialize post list and adapter
        postList = new ArrayList<>();
        postAdapter = new PostAdapter(postList);
        recyclerView.setAdapter(postAdapter);

        userId = getIntent().getStringExtra("userId");
        // Display the username in the TextView
        appTitle = findViewById(R.id.appTitle);
        // Display the username in the app title
        appTitle = findViewById(R.id.appTitle);
        if (userId != null) {
            fetchUsername(userId);
        } else {
            appTitle.setText(R.string.app_name); // Fallback to app name if userId is null
        }

        // Fetch posts from Firebase
        fetchPosts();
    }

    private void fetchUsername(String userId) {
        usersRef.child(userId).child("username").get().addOnCompleteListener(task -> {
            if (task.isSuccessful() && task.getResult().getValue() != null) {
                String username = task.getResult().getValue(String.class);
                appTitle.setText(username);
            } else {
                appTitle.setText(R.string.app_name);
            }
        });
    }

    private void fetchPosts() {
        postsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                postList.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Post post = postSnapshot.getValue(Post.class);
                    if (post != null) { // Ensure post is not null
                        postList.add(post);
                    }
                }
                postAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(MainActivityScreen.this, "Lỗi khi tải dữ liệu: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

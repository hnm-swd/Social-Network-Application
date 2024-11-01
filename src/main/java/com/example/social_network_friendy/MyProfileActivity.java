package com.example.social_network_friendy;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyProfileActivity extends Activity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int COMMENT_REQUEST_CODE = 2;

    private ImageView avatarImageView;
    private TextView usernameTextView;
    private RecyclerView recyclerViewPosts;
    private PostAdapter_MyProfile postAdapter;
    private List<Post_MyProfile> postList;
    private Button editProfileButton;  // Nút "Chỉnh sửa trang cá nhân"

    private TextView bioTextView;
    private TextView birthDateTextView;
    private TextView locationTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myprofile);

        // Kết nối các view
        avatarImageView = findViewById(R.id.avatarImageView);
        usernameTextView = findViewById(R.id.usernameTextView);
        recyclerViewPosts = findViewById(R.id.recyclerViewPosts);
        recyclerViewPosts.setLayoutManager(new LinearLayoutManager(this));
        editProfileButton = findViewById(R.id.editProfileButton); // Kết nối nút "Chỉnh sửa trang cá nhân"

        bioTextView = findViewById(R.id.bioTextView);
        birthDateTextView = findViewById(R.id.birthDateTextView);
        locationTextView = findViewById(R.id.locationTextView);

        loadProfileInfo();  // Tải thông tin từ SharedPreferences

        // Lấy tên người dùng từ SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "Tên app");
        usernameTextView.setText(username);

        // Dữ liệu mẫu cho bài viết
        postList = new ArrayList<>();
        postList.add(new Post_MyProfile(1, username, "Nội dung bài viết 1", "1 giờ trước", R.drawable.img1));
        postList.add(new Post_MyProfile(2, username, "Nội dung bài viết 2", "2 giờ trước", R.drawable.img1));
        postList.add(new Post_MyProfile(3, username, "Nội dung bài viết 3", "3 giờ trước", R.drawable.img1));
        postList.add(new Post_MyProfile(4, username, "Nội dung bài viết 4", "4 giờ trước", R.drawable.img1));
        postList.add(new Post_MyProfile(5, username, "Nội dung bài viết 5", "5 giờ trước", R.drawable.img1));

        // Tạo và thiết lập adapter
        postAdapter = new PostAdapter_MyProfile(postList, this, (post, position) -> openPostDetailActivity(post, position));

        recyclerViewPosts.setAdapter(postAdapter);


        // Cài đặt sự kiện chọn ảnh cho ImageView
        avatarImageView.setOnClickListener(v -> openGallery());

        // Cài đặt sự kiện cho nút "Chỉnh sửa trang cá nhân"
        editProfileButton.setOnClickListener(v -> openEditProfileActivity());
    }

    // Phương thức mở EditProfileActivity
    private void openEditProfileActivity() {
        Intent intent = new Intent(this, EditProfileActivity.class);
        startActivity(intent);
    }



    // Phương thức mở Gallery
    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Chọn ảnh đại diện"), PICK_IMAGE_REQUEST);
    }

    // Nhận kết quả từ Intent
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            avatarImageView.setImageURI(imageUri);
        }
        if (requestCode == COMMENT_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            String newComment = data.getStringExtra("newComment");
            int postPosition = data.getIntExtra("postPosition", -1);

            if (postPosition != -1) {
                Post_MyProfile post = postList.get(postPosition);
                post.addComment(newComment);
                post.setCommentCount(post.getCommentCount() + 1);
                postAdapter.notifyItemChanged(postPosition);
            }
        }
    }

    // Mở PostDetailActivity khi nhấn vào bài viết
    private void openPostDetailActivity(Post_MyProfile post, int position) {
        Intent intent = new Intent(this, PostDetailActivity_MyProfile.class);
        intent.putExtra("username", post.getUsername());
        intent.putExtra("content", post.getContent());
        intent.putExtra("time", post.getTime());
        intent.putExtra("imageResId", post.getImageResId());
        intent.putExtra("postPosition", position);
        startActivityForResult(intent, COMMENT_REQUEST_CODE);
    }

    private void loadProfileInfo() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyProfile", MODE_PRIVATE);

        // Sử dụng giá trị mặc định là chuỗi rỗng "" để tránh lỗi null
        String bio = sharedPreferences.getString("bio", "");
        String birthDate = sharedPreferences.getString("birthDate", "");
        String location = sharedPreferences.getString("location", "");

        // Kiểm tra và hiển thị bio nếu có giá trị
        if (!bio.isEmpty()) {
            bioTextView.setText(bio);
            bioTextView.setVisibility(View.VISIBLE);
        } else {
            bioTextView.setVisibility(View.GONE);
        }

        // Kiểm tra và hiển thị ngày sinh nếu có giá trị
        if (!birthDate.isEmpty()) {
            birthDateTextView.setText(birthDate);
            birthDateTextView.setVisibility(View.VISIBLE);
        } else {
            birthDateTextView.setVisibility(View.GONE);
        }

        // Kiểm tra và hiển thị nơi sống nếu có giá trị
        if (!location.isEmpty()) {
            locationTextView.setText(location);
            locationTextView.setVisibility(View.VISIBLE);
        } else {
            locationTextView.setVisibility(View.GONE);
        }
    }
}
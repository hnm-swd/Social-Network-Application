package com.example.myapplication;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListPostActivity extends AppCompatActivity {
    RecyclerView recyclerViewPosts;
    Adapter mAdapter;
    ArrayList<Post> listPosts = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_listview);  // Đảm bảo file XML này đúng

        // Sửa thành RecyclerView
        recyclerViewPosts = findViewById(R.id.postsRecyclerView);
        recyclerViewPosts.setLayoutManager(new LinearLayoutManager(this));  // Cài đặt LayoutManager

        setPosts();  // Tạo dữ liệu mẫu

        // Khởi tạo adapter và gán nó cho RecyclerView
        mAdapter = new Adapter(this, listPosts);
        recyclerViewPosts.setAdapter(mAdapter);
    }

    // Tạo danh sách các bài post mẫu
    public void setPosts() {
        listPosts.add(new Post("John Doe", "5 mins ago", "This is a post content.", R.drawable.img, R.drawable.img, 10, 5));
        listPosts.add(new Post("Jane Smith", "10 mins ago", "Another post here!", R.drawable.img, R.drawable.img,20, 8));
        // Thêm các bài viết khác nếu cần
    }
}

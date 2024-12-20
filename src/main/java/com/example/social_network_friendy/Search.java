package com.example.social_network_friendy;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Search extends Activity {

    private EditText edtFinding;
    private Button btnSearch;
    private TextView noResultText;
    private RecyclerView lvFinding;
    private USER_FINDING_ADAPTER adapter;

    private ArrayList<String> userList;
    private FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finding);

        // Ánh xạ các thành phần giao diện
        edtFinding = findViewById(R.id.edtfinding);
        btnSearch = findViewById(R.id.timkiem);
        noResultText = findViewById(R.id.noResultText);
        lvFinding = findViewById(R.id.lvfinding);

        // Khởi tạo danh sách và adapter
        userList = new ArrayList<>();
        adapter = new USER_FINDING_ADAPTER(userList, username -> {
            // Sự kiện click trên tên người dùng
            Toast.makeText(Search.this, "Clicked: " + username, Toast.LENGTH_SHORT).show();
        });

        lvFinding.setLayoutManager(new LinearLayoutManager(this));
        lvFinding.setAdapter(adapter);

        // Tham chiếu đến Firebase Realtime Database
        db = FirebaseDatabase.getInstance();

        // Bắt sự kiện khi nhấn nút tìm kiếm
        btnSearch.setOnClickListener(v -> searchUser(edtFinding.getText().toString().trim()));

        // Tìm kiếm tự động khi nhập ký tự
        edtFinding.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchUser(s.toString().trim());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void searchUser(String query) {
        if (query.isEmpty()) {
            userList.clear();
            adapter.notifyDataSetChanged();
            noResultText.setVisibility(View.GONE);
            return;
        }

        // Tham chiếu đến "users" trong Realtime Database
        DatabaseReference ref = db.getReference("users");

        // Tìm kiếm trong Realtime Database theo username
        ref.orderByChild("username").equalTo(query)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        userList.clear();
                        if (snapshot.exists()) {
                            for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                                String username = userSnapshot.child("username").getValue(String.class);
                                if (username != null) {
                                    userList.add(username); // Thêm tên người dùng vào danh sách
                                    Log.d("RealtimeData", "Found username: " + username); // Ghi tên người dùng vào Log
                                    Toast.makeText(Search.this, "Found: " + username, Toast.LENGTH_SHORT).show(); // Hiển thị Toast
                                }
                            }
                            noResultText.setVisibility(View.GONE); // Ẩn thông báo "Không có kết quả"
                        } else {
                            noResultText.setVisibility(View.VISIBLE); // Hiển thị thông báo "Không có kết quả"
                        }
                        adapter.notifyDataSetChanged(); // Cập nhật giao diện danh sách
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        noResultText.setText("Lỗi: " + error.getMessage());
                        noResultText.setVisibility(View.VISIBLE);
                    }
                });
    }
}

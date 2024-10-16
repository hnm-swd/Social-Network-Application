package com.example.social_network_friendy;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivityScreen extends Activity {

    ListView jobListView;
    ArrayList<JobPost> jobPosts;
    //private boolean isLiked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainscreen_layout);
        // Tìm kiếm ImageView chat_button
        ImageView chat_button = findViewById(R.id.chat_button);
        // Thiết lập sự kiện khi nhấn vào biểu tượng bình luận
//        chat_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Chuyển sang CommentActivity
//                Intent intent = new Intent(MainActivityScreen.this, CommentActivity.class);
//                startActivity(intent);
//            }
//        });
        // Tìm icon
//        ImageButton heart_button = findViewById(R.id.heart_button);
//
//        //gan nhan
//        heart_button.setOnClickListener(v ->{
//                if (isLiked){
//                    heart_button.setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_IN); // Đổi về màu đỏ
//                    Toast.makeText(MainActivityScreen.this, "Bỏ thích!", Toast.LENGTH_SHORT).show();
//                } else {
//                    heart_button.setColorFilter(getResources().getColor(R.color.gray), PorterDuff.Mode.SRC_IN); // Đổi sang màu xám
//                    Toast.makeText(MainActivityScreen.this, "Đã thích!", Toast.LENGTH_SHORT).show();
//                    //
//                    isLiked = !isLiked;
//                }
//                }
//        );


        // Initialize ListView
        jobListView = findViewById(R.id.jobListView);

        // Initialize job posts
        jobPosts = new ArrayList<>();
        jobPosts.add(new JobPost("GÓC TUYỂN DỤNG NÈ",
                "Quán mình hiện đang thiếu nhân viên bán hàng (Lưu ý ưu tiên sinh viên).",
                "Sáng: 7h-12h, Chiều: 12h-17h",
                "Lương: 20k/h",
                "Liên hệ qua Zalo: 0921324267"));

        // Setup the adapter
        JobAdapter adapter = new JobAdapter(this, jobPosts);
        jobListView.setAdapter(adapter);
    }
}

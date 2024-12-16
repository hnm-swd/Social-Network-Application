package com.example.social_network_friendy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private List<Post> posts;

    public PostAdapter(List<Post> posts) {
        this.posts = posts;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false);
        return new PostViewHolder(view);
    }

//    @Override
//    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
//        Post post = posts.get(position);
//        holder.titleTextView.setText(post.getId());
//
//        if (post.getImageUri() != null) {
//            holder.postImageView.setImageURI(Uri.parse(post.getImageUri()));
//            holder.postImageView.setVisibility(View.VISIBLE);
//        } else {
//            holder.postImageView.setVisibility(View.GONE);
//        }
//    }
//    @Override
//    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
//        Post post = posts.get(position);
//
//        // Gán nội dung bài viết
//        holder.titleTextView.setText(post.getContent());
//
//        // Hiển thị ảnh với Glide
//        String imageUriString = post.getImageUri();
//        if (imageUriString != null && !imageUriString.isEmpty()) {
//            holder.postImageView.setVisibility(View.VISIBLE);
//            Glide.with(holder.postImageView.getContext())
//                    .load(imageUriString)
//                    .placeholder(R.drawable.ic_launcher_background) // Ảnh tải tạm
//                    .error(R.drawable.ic_launcher_foreground) // Ảnh lỗi
//                    .into(holder.postImageView);
//        } else {
//            holder.postImageView.setVisibility(View.GONE);
//        }
//    }
    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.titleTextView.setText(post.getContent());

        String imageUriString = post.getImageUri();
        if (imageUriString != null && !imageUriString.isEmpty()) {
            holder.postImageView.setVisibility(View.VISIBLE);
            Glide.with(holder.postImageView.getContext())
                    .load(imageUriString)
                    .placeholder(R.drawable.ic_launcher_background) // Ảnh hiển thị khi đang tải
                    .error(R.drawable.x) // Ảnh lỗi
                    .into(holder.postImageView);
        } else {
            holder.postImageView.setVisibility(View.GONE);
        }
    }





    @Override
    public int getItemCount() {
        return posts.size();
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        ImageView postImageView;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.tvContent); // ID của title trong post_item.xml
            postImageView = itemView.findViewById(R.id.imgPost); // ID của ảnh trong post_item.xml
        }
    }
    // PostAdapter.java
    public void updatePosts(List<Post> newPosts) {
        this.posts.clear(); // Xóa danh sách cũ
        this.posts.addAll(newPosts); // Thêm bài viết mới
        notifyDataSetChanged(); // Thông báo adapter để cập nhật giao diện
    }

}

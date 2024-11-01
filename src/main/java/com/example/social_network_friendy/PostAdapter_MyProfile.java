package com.example.social_network_friendy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PostAdapter_MyProfile extends RecyclerView.Adapter<PostAdapter_MyProfile.PostViewHolder> {

    private List<Post_MyProfile> postList; // Danh sách các bài viết
    private Context context;
    private static final int COMMENT_REQUEST_CODE = 100; // Mã request cho comment
    private OnPostClickListener onPostClickListener;
    // Interface cho callback
    public interface OnPostClickListener {
        void onPostClick(Post_MyProfile post, int position);
    }
    // Constructor của PostAdapter
    public PostAdapter_MyProfile(List<Post_MyProfile> postList, Context context, OnPostClickListener listener) {
        this.postList = postList;
        this.context = context;
        this.onPostClickListener = listener; // Gán listener
    }


    // Tạo ViewHolder và inflate layout cho item trong danh sách
    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.post_item, parent, false);
        return new PostViewHolder(view);
    }

    // Gán dữ liệu từ đối tượng Post vào từng view trong item
    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post_MyProfile post = postList.get(position);

        // Thiết lập nội dung từ Post
        holder.tvContent.setText(post.getContent());
        holder.imgPost.setImageResource(post.getImageResId());
        holder.tvUsername.setText(post.getUsername());
        holder.tvTime.setText(post.getTime());
        holder.tvCommentCount.setText(String.valueOf(post.getCommentCount())); // Cập nhật số lượng comment


        // Cập nhật trạng thái like
        updateLikeStatus(holder, post);

        // Xử lý sự kiện khi người dùng nhấn vào icon trái tim
        holder.imgHeart.setOnClickListener(v -> {
            if (post.isLiked()) {
                post.setLiked(false); // Bỏ thích
                post.setLikeCount(post.getLikeCount() - 1); // Giảm số lượt thích
            } else {
                post.setLiked(true); // Đã thích
                post.setLikeCount(post.getLikeCount() + 1); // Tăng số lượt thích
            }

            // Cập nhật lại giao diện
            updateLikeStatus(holder, post);
        });

        // Sử dụng callback để mở chi tiết bài viết
        holder.itemView.setOnClickListener(v -> {
            if (onPostClickListener != null) {
                onPostClickListener.onPostClick(post, position); // Thêm position
            }
        });
    }

    // Cập nhật lại UI khi trạng thái like thay đổi
    private void updateLikeStatus(PostViewHolder holder, Post_MyProfile post) {
        if (post.isLiked()) {
            holder.imgHeart.setImageResource(R.drawable.ic_heart_filled); // Trái tim đỏ
        } else {
            holder.imgHeart.setImageResource(R.drawable.ic_heart); // Trái tim trống
        }
        holder.tvLikeCount.setText(String.valueOf(post.getLikeCount())); // Cập nhật số lượt thích
    }
    // Mở màn hình chi tiết comment và truyền ảnh bài viết
    private void openPostComment(Post_MyProfile post) {
        Intent intent = new Intent(context, PostDetailActivity_MyProfile.class);
        intent.putExtra("postId", post.getId());
        intent.putExtra("imageResId", post.getImageResId()); // Truyền ảnh
        ((Activity) context).startActivityForResult(intent, COMMENT_REQUEST_CODE);
    }

    // Cập nhật danh sách bài viết mới bằng DiffUtil để tối ưu hóa hiệu suất
    public void updatePostList(List<Post_MyProfile> newPostList) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtil.Callback() {
            @Override
            public int getOldListSize() {
                return postList.size();
            }

            @Override
            public int getNewListSize() {
                return newPostList.size();
            }

            @Override
            public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                return postList.get(oldItemPosition).getId() == newPostList.get(newItemPosition).getId(); // Assuming 'id' is a unique identifier
            }

            @Override
            public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                Post_MyProfile oldPost = postList.get(oldItemPosition);
                Post_MyProfile newPost = newPostList.get(newItemPosition);
                return oldPost.equals(newPost); // Override equals method in Post class
            }
        });

        postList.clear();
        postList.addAll(newPostList);
        diffResult.dispatchUpdatesTo(this);
    }

    // Trả về số lượng bài viết trong danh sách
    @Override
    public int getItemCount() {
        return postList.size();
    }

    // Hàm mở chi tiết bài viết
    // Hàm mở chi tiết bài viết
    private void openPostDetail(Post_MyProfile post) {
        Intent intent = new Intent(context, PostDetailActivity_MyProfile.class);
        intent.putExtra("username", post.getUsername());
        intent.putExtra("content", post.getContent());
        intent.putExtra("time", post.getTime());
        intent.putExtra("imageResId", post.getImageResId());
        context.startActivity(intent);
    }

    // ViewHolder để ánh xạ các thành phần trong item layout
    public static class PostViewHolder extends RecyclerView.ViewHolder {
        TextView tvContent, tvUsername, tvTime, tvLikeCount, tvCommentCount;
        ImageView imgPost, imgHeart;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            tvContent = itemView.findViewById(R.id.tvContent); // Nội dung bài viết
            tvUsername = itemView.findViewById(R.id.tvUsername); // Tên người dùng
            tvTime = itemView.findViewById(R.id.tvTime); // Thời gian đăng bài
            imgPost = itemView.findViewById(R.id.imgPost); // Hình ảnh bài viết
            imgHeart = itemView.findViewById(R.id.imgHeart); // Icon trái tim (thích/bỏ thích)
            tvLikeCount = itemView.findViewById(R.id.tvLikeCount); // Số lượt thích
            tvCommentCount = itemView.findViewById(R.id.tvCommentCount); // Số lượng comment
        }
    }
}
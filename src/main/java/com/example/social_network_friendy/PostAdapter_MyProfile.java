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

    private List<Post_MyProfile> postList;
    private Context context;
    private static final int COMMENT_REQUEST_CODE = 100;
    private OnPostClickListener onPostClickListener;
    // Interface cho callback
    public interface OnPostClickListener {
        void onPostClick(Post_MyProfile post, int position);
    }

    public PostAdapter_MyProfile(List<Post_MyProfile> postList, Context context, OnPostClickListener listener) {
        this.postList = postList;
        this.context = context;
        this.onPostClickListener = listener;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.post_item, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post_MyProfile post = postList.get(position);

        holder.tvContent.setText(post.getContent());
        holder.imgPost.setImageResource(post.getImageResId());
        holder.tvUsername.setText(post.getUsername());
        holder.tvTime.setText(post.getTime());
        holder.tvCommentCount.setText(String.valueOf(post.getCommentCount()));

        updateLikeStatus(holder, post);

        holder.imgHeart.setOnClickListener(v -> {
            if (post.isLiked()) {
                post.setLiked(false);
                post.setLikeCount(post.getLikeCount() - 1);
            } else {
                post.setLiked(true);
                post.setLikeCount(post.getLikeCount() + 1);
            }

            updateLikeStatus(holder, post);
        });

        holder.itemView.setOnClickListener(v -> {
            if (onPostClickListener != null) {
                onPostClickListener.onPostClick(post, position); // Thêm position
            }
        });
    }

    private void updateLikeStatus(PostViewHolder holder, Post_MyProfile post) {
        if (post.isLiked()) {
            holder.imgHeart.setImageResource(R.drawable.ic_heart_filled);
        } else {
            holder.imgHeart.setImageResource(R.drawable.ic_heart);
        }
        holder.tvLikeCount.setText(String.valueOf(post.getLikeCount()));
    }

    private void openPostComment(Post_MyProfile post) {
        Intent intent = new Intent(context, PostDetailActivity_MyProfile.class);
        intent.putExtra("postId", post.getId());
        intent.putExtra("imageResId", post.getImageResId());
        ((Activity) context).startActivityForResult(intent, COMMENT_REQUEST_CODE);
    }

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
                return postList.get(oldItemPosition).getId() == newPostList.get(newItemPosition).getId();
            }

            @Override
            public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                Post_MyProfile oldPost = postList.get(oldItemPosition);
                Post_MyProfile newPost = newPostList.get(newItemPosition);
                return oldPost.equals(newPost);
            }
        });

        postList.clear();
        postList.addAll(newPostList);
        diffResult.dispatchUpdatesTo(this);
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder {
        TextView tvContent, tvUsername, tvTime, tvLikeCount, tvCommentCount;
        ImageView imgPost, imgHeart;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            tvContent = itemView.findViewById(R.id.tvContent);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvTime = itemView.findViewById(R.id.tvTime);
            imgPost = itemView.findViewById(R.id.imgPost);
            imgHeart = itemView.findViewById(R.id.imgHeart);
            tvLikeCount = itemView.findViewById(R.id.tvLikeCount);
            tvCommentCount = itemView.findViewById(R.id.tvCommentCount);
        }
    }
}
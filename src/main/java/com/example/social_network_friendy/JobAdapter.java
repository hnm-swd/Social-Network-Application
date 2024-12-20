package com.example.social_network_friendy;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class JobAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<JobPost> jobPosts;

    public JobAdapter(Context context, ArrayList<JobPost> jobPosts) {
        this.context = context;
        this.jobPosts = jobPosts;
    }

    @Override
    public int getCount() {
        return jobPosts.size();
    }

    @Override
    public Object getItem(int position) {
        return jobPosts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.post_item, parent, false);
            holder = new ViewHolder();
            holder.tvUsername = convertView.findViewById(R.id.tvUsername);
            holder.tvContent = convertView.findViewById(R.id.tvContent);
            holder.tvTime = convertView.findViewById(R.id.tvTime);
            holder.imgPost = convertView.findViewById(R.id.imgPost);
            holder.imgHeart = convertView.findViewById(R.id.imgHeart);
            holder.tvLikeCount = convertView.findViewById(R.id.tvLikeCount);
            holder.imgComment = convertView.findViewById(R.id.imgComment);
            holder.tvCommentCount = convertView.findViewById(R.id.tvCommentCount);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        JobPost currentJobPost = (JobPost) getItem(position);
        holder.tvUsername.setText(currentJobPost.getTvContent());
        holder.tvContent.setText(currentJobPost.getTvContent());
        holder.tvTime.setText(currentJobPost.getTvTime());

        // Thiết lập hình ảnh bài viết (nếu có)
        if (currentJobPost.getImgPost() != null) {
            // Giả sử bạn có phương thức để tải hình ảnh từ URL hoặc tài nguyên drawable
            // Ví dụ: Glide hoặc Picasso
            // Glide.with(context).load(currentJobPost.getImgPost()).into(holder.imgPost);
            holder.imgPost.setImageResource(R.drawable.img_profile_avatar); // Thay bằng hình mặc định
        }

        holder.tvLikeCount.setText(String.valueOf(currentJobPost.getTvLikeCount()));
        holder.tvCommentCount.setText(String.valueOf(currentJobPost.getTvCommentCount()));

//        // Thiết lập sự kiện nhấn nút bình luận
//        holder.imgComment.setOnClickListener(v -> {
//            Intent intent = new Intent(context, CommentActivity.class);
//            intent.putExtra("jobContent", currentJobPost.getTvContent());
//            context.startActivity(intent);
//        });

        // Thiết lập sự kiện nhấn nút trái tim
        holder.imgHeart.setOnClickListener(v -> {
            boolean isLiked = currentJobPost.isLiked();
            currentJobPost.setLiked(!isLiked);
            if (currentJobPost.isLiked()) {
                holder.imgHeart.setImageResource(R.drawable.traitymdo);
            } else {
                holder.imgHeart.setImageResource(R.drawable.ic_heart);
            }
            holder.tvLikeCount.setText(String.valueOf(currentJobPost.getTvLikeCount()));
        });

        return convertView;
    }

    static class ViewHolder {
        TextView tvUsername;
        TextView tvContent;
        TextView tvTime;
        ImageView imgPost;
        ImageView imgHeart;
        TextView tvLikeCount;
        ImageView imgComment;
        TextView tvCommentCount;
    }
}

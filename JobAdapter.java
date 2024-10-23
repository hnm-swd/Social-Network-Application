package com.example.social_network_friendy;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.job_post_item, parent, false);
            holder = new ViewHolder();
            holder.jobTitle = convertView.findViewById(R.id.jobTitle);
            holder.jobDescription = convertView.findViewById(R.id.Description);
            holder.jobTime = convertView.findViewById(R.id.jobTime);
            holder.jobSalary = convertView.findViewById(R.id.jobSalary);
            holder.jobContact = convertView.findViewById(R.id.jobContact);
            // Nút trái tim
            holder.heart_button = convertView.findViewById(R.id.heart_button);
            //Nút bình luận
            holder.chat_button = convertView.findViewById(R.id.chat_button);
            convertView.setTag(holder); // Gán holder vào view
        } else {
            holder = (ViewHolder) convertView.getTag(); // Lấy holder từ view
        }

        JobPost currentJobPost = (JobPost) getItem(position);
        holder.jobTitle.setText(currentJobPost.getTitle());
        holder.jobDescription.setText(currentJobPost.getDescription());
        holder.jobTime.setText(currentJobPost.getTime());
        holder.jobSalary.setText(currentJobPost.getSalary());
        holder.jobContact.setText(currentJobPost.getContact());
//Thiết lập nhấn nút bình luận
        holder.chat_button.setOnClickListener(v -> {
                // Khởi động CommentActivity khi người dùng nhấn vào nút bình luận
                Intent intent = new Intent(context, CommentActivity.class);
                intent.putExtra("jobTitle", currentJobPost.getTitle()); // Truyền thông tin job title
                context.startActivity(intent);
        });

        // Thiết lập sự kiện nhấn nút trái tim
        holder.heart_button.setOnClickListener(v -> {
            boolean isLiked = currentJobPost.isLiked();
            // Đổi trạng thái "thích"
            currentJobPost.setLiked(!isLiked);
            // chon mau trai tim
            if (currentJobPost.isLiked()) {
                holder.heart_button.setImageResource(R.drawable.traitymdo); // Đổi thành trái tim đầy đỏ
            } else {
                holder.heart_button.setImageResource(R.drawable.img); // Đổi thành trái tim rỗng màu xám
            }
        });
            return convertView;
    }

    static class ViewHolder {
        TextView jobTitle;
        TextView jobDescription;
        TextView jobTime;
        TextView jobSalary;
        TextView jobContact;
        ImageView chat_button;
        ImageView heart_button;
    }
}


package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.PostViewHolder> {
    Context context;
    ArrayList<Post> posts;

    public Adapter(Context context, ArrayList<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.txtUserName.setText(post.getUserName());
        holder.txtTime.setText(post.getTimePosted());
        holder.txtContent.setText(post.getContent());
        holder.imgProfile.setImageResource(post.getProfileImage());
        holder.imgPost.setImageResource(post.getPostImage());
        holder.txtLikes.setText(post.getLikes() + " likes");
        holder.txtComments.setText(post.getComments() + " comments");
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder {
        TextView txtUserName, txtTime, txtContent, txtLikes, txtComments;
        ImageView imgProfile,imgPost;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            txtUserName = itemView.findViewById(R.id.txt_username);
            txtTime = itemView.findViewById(R.id.txt_time);
            txtContent = itemView.findViewById(R.id.txt_post_content);
            imgProfile = itemView.findViewById(R.id.img_avatar);
            imgPost=itemView.findViewById(R.id.img_post);
            txtLikes = itemView.findViewById(R.id.txt_heart_count);
            txtComments = itemView.findViewById(R.id.txt_comment_count);
        }
    }
}

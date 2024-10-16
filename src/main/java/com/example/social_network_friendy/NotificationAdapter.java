package com.example.social_network_friendy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {
    private List<NotificationItem> notificationList;

    // Constructor
    public NotificationAdapter(List<NotificationItem> notificationList) {
        this.notificationList = notificationList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each notification item
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_notification, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Bind the data to the views
        NotificationItem notification = notificationList.get(position);
        holder.titleTextView.setText(notification.getTitle());
        holder.timeTextView.setText(notification.getTime());
    }

    @Override
    public int getItemCount() {
        return notificationList.size();
    }

    // ViewHolder class to hold references to each item's views
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;
        public TextView timeTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            timeTextView = itemView.findViewById(R.id.timeTextView);
        }
    }
}

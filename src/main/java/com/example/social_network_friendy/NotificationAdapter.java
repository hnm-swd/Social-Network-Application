package com.example.social_network_friendy;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {
    private List<NotificationItem> notificationList;

    public NotificationAdapter(List<NotificationItem> notificationList) {
        this.notificationList = notificationList;
    }

    public NotificationAdapter(List<NotificationItem> notificationList) {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_notification, parent, false);
        return new ViewHolder(view);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NotificationItem notification = notificationList.get(position);
        holder.titleTextView.setText(notification.getTitle());
        holder.timeTextView.setText(notification.getTime());
    }

    @Override
    public int getItemCount() {
        return notificationList.size();
    }

    public void notifyDataSetChanged() {
    }

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


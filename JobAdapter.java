package com.example.social_network_friendy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.job_post_item, parent, false);
        }

        JobPost currentJobPost = (JobPost) getItem(position);

        TextView jobTitle = convertView.findViewById(R.id.jobTitle);
        TextView jobDescription = convertView.findViewById(R.id.Description);
        TextView jobTime = convertView.findViewById(R.id.jobTime);
        TextView jobSalary = convertView.findViewById(R.id.jobSalary);
        TextView jobContact = convertView.findViewById(R.id.jobContact);

        jobTitle.setText(currentJobPost.getTitle());
        jobDescription.setText(currentJobPost.getDescription());
        jobTime.setText(currentJobPost.getTime());
        jobSalary.setText(currentJobPost.getSalary());
        jobContact.setText(currentJobPost.getContact());

        return convertView;
    }
}

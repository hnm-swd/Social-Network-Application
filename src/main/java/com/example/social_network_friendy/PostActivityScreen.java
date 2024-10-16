package com.example.social_network_friendy;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;

public class PostActivityScreen extends Activity {

    private Button btnCancel, btnPost;
    private EditText editTitle, editContent;
    private ImageView imageUpload;
    private TextView textViewUserName;
    private LinearLayout imageContainer;  // For holding multiple uploaded images

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addpost_activity_layout);

        // Initialize the UI components
        btnCancel = findViewById(R.id.btnCancel);
        btnPost = findViewById(R.id.btnPost);
        editTitle = findViewById(R.id.editTitle);
        editContent = findViewById(R.id.editText);
        imageUpload = findViewById(R.id.ImageView);
        textViewUserName = findViewById(R.id.textViewUserName);
        imageContainer = findViewById(R.id.imageContainer);

        // Set the username dynamically (replace with the actual username)
        textViewUserName.setText("app_name");

        // Handle "Cancel" button click
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Close this activity and go back
            }
        });

        // Handle "Post" button click
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String postTitle = editTitle.getText().toString();
                String postContent = editContent.getText().toString();

                if (postTitle.isEmpty() || postContent.isEmpty()) {
                    Toast.makeText(PostActivityScreen.this, "Title or content cannot be empty!", Toast.LENGTH_SHORT).show();
                } else {
                    // Here you would handle the actual posting logic (e.g., sending data to a server)
                    Toast.makeText(PostActivityScreen.this, "Post submitted!", Toast.LENGTH_SHORT).show();

                    // Clear the fields after posting
                    editTitle.setText("");
                    editContent.setText("");
                    imageContainer.removeAllViews(); // Clear uploaded images

                    // Optionally, return to the previous screen
                    finish();
                }
            }
        });

        // Handle image upload button click
        imageUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch an intent to choose an image from the gallery
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 1); // Request code 1 for image selection
            }
        });
    }

    // Handle the result of the image picker
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            // Image selected successfully, handle the result here
            Uri selectedImage = data.getData();

            // Dynamically create an ImageView for the uploaded image
            ImageView uploadedImage = new ImageView(this);
            uploadedImage.setLayoutParams(new LinearLayout.LayoutParams(200, 200));  // Set size for the image
            uploadedImage.setImageURI(selectedImage);  // Display the selected image

            // Create a delete button to remove the image
            ImageButton deleteButton = new ImageButton(this);
            deleteButton.setLayoutParams(new LinearLayout.LayoutParams(50, 50));
            deleteButton.setImageResource(android.R.drawable.ic_delete);  // Set delete icon

            // Create a wrapper for the image and the delete button
            LinearLayout imageWrapper = new LinearLayout(this);
            imageWrapper.setOrientation(LinearLayout.VERTICAL);
            imageWrapper.addView(uploadedImage);
            imageWrapper.addView(deleteButton);

            // Add the imageWrapper to the imageContainer
            imageContainer.addView(imageWrapper);

            // Handle image removal when delete button is clicked
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imageContainer.removeView(imageWrapper);
                }
            });
        }
    }
}

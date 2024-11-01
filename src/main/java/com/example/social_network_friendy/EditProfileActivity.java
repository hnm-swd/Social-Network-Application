package com.example.social_network_friendy;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.Calendar;

public class EditProfileActivity extends Activity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private EditText bioEditText;
    private EditText birthDateEditText;
    private AutoCompleteTextView locationEditText;
    private ImageView avatarImageView;
    private TextView doneTextView;

    private String[] locationSuggestions = {"Đà Nẵng", "Hà Nội", "Hồ Chí Minh", "Nha Trang", "Đà Lạt"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_myprofile);

        bioEditText = findViewById(R.id.bioEditText);
        birthDateEditText = findViewById(R.id.birthDateEditText);
        locationEditText = findViewById(R.id.locationEditText);
        avatarImageView = findViewById(R.id.avatarImageView);
        doneTextView = findViewById(R.id.tvDone);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, locationSuggestions);
        locationEditText.setAdapter(adapter);

        birthDateEditText.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                showDatePickerDialog();
                return true;
            }
            return false;
        });
        avatarImageView.setOnClickListener(v -> openGallery());

        TextView tvCancel = findViewById(R.id.tvCancel);

        loadProfileInfo();

        setFocusChangeListener(bioEditText, "Viết tiểu sử");
        setFocusChangeListener(birthDateEditText, "Ngày tháng năm sinh");
        setFocusChangeListener(locationEditText, "Thêm nơi sống");

        doneTextView.setOnClickListener(v -> {
            String bio = bioEditText.getText().toString().trim();
            String birthDate = birthDateEditText.getText().toString().trim();
            String location = locationEditText.getText().toString().trim();


            saveProfileInfo(bio, birthDate, location);
        });

        tvCancel.setOnClickListener(v -> finish());
    }

    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                    birthDateEditText.setText(selectedDate);
                },
                year, month, day
        );
        datePickerDialog.show();
    }

    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Chọn ảnh đại diện"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            avatarImageView.setImageURI(imageUri);
        }

    }

    private void saveProfileInfo(String bio, String birthDate, String location) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyProfile", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if (!bio.isEmpty()) {
            editor.putString("bio", bio);
        } else {
            editor.remove("bio");
        }

        if (!birthDate.isEmpty()) {
            editor.putString("birthDate", birthDate);
        } else {
            editor.remove("birthDate");
        }

        if (!location.isEmpty()) {
            editor.putString("location", location);
        } else {
            editor.remove("location");
        }

        editor.apply();

        Intent intent = new Intent(EditProfileActivity.this, MyProfileActivity.class);
        startActivity(intent);
        finish();
    }



    private void loadProfileInfo() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyProfile", MODE_PRIVATE);
        String bio = sharedPreferences.getString("bio", "");
        String birthDate = sharedPreferences.getString("birthDate", "");
        String location = sharedPreferences.getString("location", "");


        bioEditText.setText(bio);
        birthDateEditText.setText(birthDate);
        locationEditText.setText(location);
    }


    private void setFocusChangeListener(EditText editText, String hint) {
        editText.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                editText.setHint("");
            } else if (editText.getText().toString().isEmpty()) {
                editText.setHint(hint);
            }
        });
    }
}

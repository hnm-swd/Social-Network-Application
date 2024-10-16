package com.example.social_network_friendy;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivityScreen extends Activity {

    private static final String SHARED_PREF_NAME_USER = "myprefuser";
    private static final String KEY_NAME_USER = "nameuser";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_CONFIRMPASSWORD = "confirmpassword";
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account_activity_layout);

        // Set up login text click listener
        TextView chontextviewdangnhap = findViewById(R.id.textlogin);
        chontextviewdangnhap.setOnClickListener(view -> {
            Intent intentlogin = new Intent(RegisterActivityScreen.this, LoginActivityScreen.class);
            startActivity(intentlogin);
        });

        // Connect to UI elements
        EditText tennguoidung = findViewById(R.id.edtuser);
        EditText emaildangki = findViewById(R.id.edtemail);
        EditText matkhau = findViewById(R.id.edtpassword);
        EditText xacnhanmatkhau = findViewById(R.id.edtconfirmpassword);
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME_USER, MODE_PRIVATE);
        Button chonnutdangki = findViewById(R.id.btncreateaccount);

        // Set up create account button click listener
        chonnutdangki.setOnClickListener(view -> {
            String username = tennguoidung.getText().toString();
            String email = emaildangki.getText().toString();
            String password = matkhau.getText().toString();
            String confirmPassword = xacnhanmatkhau.getText().toString();

            // Check if passwords match
            if (password.equals(confirmPassword)) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_NAME_USER, username);
                editor.putString(KEY_EMAIL, email);
                editor.putString(KEY_PASSWORD, password);
                editor.putString(KEY_CONFIRMPASSWORD, confirmPassword);
                editor.apply();

                Toast.makeText(RegisterActivityScreen.this, "Đã tạo tài khoản thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterActivityScreen.this, LoginActivityScreen.class);
                startActivity(intent);
            } else {
                Toast.makeText(RegisterActivityScreen.this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class Profile extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ffb_profile);
        TextView name=findViewById(R.id.name);
        Button finish=findViewById(R.id.finishbutton);
        String username=getIntent().getStringExtra("username");
        name.setText("Chào mừng "+username);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        }

        );
    }
}

package com.android.registrationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class SecondActivity extends AppCompatActivity {
    private TextView userInfoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        userInfoTextView = findViewById(R.id.userInfoTextView);

        UserDao userDao = new UserDao(this);
        List<User> users = userDao.getAllUsers();

        StringBuilder stringBuilder = new StringBuilder();
        for (User user : users) {
            stringBuilder.append("Name: ").append(user.getName()).append("\n");
            stringBuilder.append("DOB: ").append(user.getDob()).append("\n");
            stringBuilder.append("Email: ").append(user.getEmail()).append("\n\n");
        }

        userInfoTextView.setText(stringBuilder.toString());
    }
}

package com.android.registrationapp;


import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private EditText nameEditText, dobEditText, emailEditText;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEditText = findViewById(R.id.nameEditText);
        dobEditText = findViewById(R.id.dobEditText);
        emailEditText = findViewById(R.id.emailEditText);
        submitButton = findViewById(R.id.submitButton);

        dobEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                String dob = dobEditText.getText().toString();
                String email = emailEditText.getText().toString();

                User user = new User(name, dob, email);

                UserDao userDao = new UserDao(MainActivity.this);
                userDao.addUser(user);

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }

    public void showDatePickerDialog(View v) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                R.style.MyDatePickerStyle,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        dobEditText.setText(String.format("%02d/%02d/%d", dayOfMonth, monthOfYear + 1, year));
                    }
                }, year, month, dayOfMonth);

        datePickerDialog.show();
    }

}

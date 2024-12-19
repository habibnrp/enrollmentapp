package com.example.enrollmentapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class EnrollmentActivity extends AppCompatActivity {
    private ArrayList<String> selectedSubjects = new ArrayList<>();
    private int totalCredits = 0;
    private static final int MAX_CREDITS = 24;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enrollment);

        EditText subjectField = findViewById(R.id.editTextSubject);
        EditText creditField = findViewById(R.id.editTextCredit);
        Button btnAddSubject = findViewById(R.id.btnAddSubject);
        Button btnViewSummary = findViewById(R.id.btnViewSummary);
        Button btnLogout = findViewById(R.id.btnLogout);
        TextView totalCreditsView = findViewById(R.id.textViewTotalCredits);

        btnAddSubject.setOnClickListener(view -> {
            String subject = subjectField.getText().toString().trim();
            int credits;

            try {
                credits = Integer.parseInt(creditField.getText().toString().trim());
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Invalid credits", Toast.LENGTH_SHORT).show();
                return;
            }

            if (subject.isEmpty() || credits <= 0) {
                Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                return;
            }

            if (totalCredits + credits > MAX_CREDITS) {
                Toast.makeText(this, "Credit limit exceeded!", Toast.LENGTH_SHORT).show();
                return;
            }

            selectedSubjects.add(subject + " (" + credits + " credits)");
            totalCredits += credits;
            totalCreditsView.setText("Total Credits: " + totalCredits);
            subjectField.setText("");
            creditField.setText("");
            Toast.makeText(this, "Subject added", Toast.LENGTH_SHORT).show();
        });

        btnViewSummary.setOnClickListener(view -> {
            Intent intent = new Intent(EnrollmentActivity.this, SummaryActivity.class);
            intent.putStringArrayListExtra("subjects", selectedSubjects);
            intent.putExtra("totalCredits", totalCredits);
            startActivity(intent);
        });

        btnLogout.setOnClickListener(view -> {
            Intent intent = new Intent(EnrollmentActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
package com.example.enrollmentapp;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SummaryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        TextView subjectsView = findViewById(R.id.textViewSubjects);
        TextView totalCreditsView = findViewById(R.id.textViewTotalCredits);

        ArrayList<String> subjects = getIntent().getStringArrayListExtra("subjects");
        int totalCredits = getIntent().getIntExtra("totalCredits", 0);

        if (subjects != null && !subjects.isEmpty()) {
            StringBuilder subjectList = new StringBuilder();
            for (String subject : subjects) {
                subjectList.append(subject).append("\n");
            }
            subjectsView.setText(subjectList.toString());
        } else {
            subjectsView.setText("No subjects selected.");
        }

        totalCreditsView.setText("Total Credits: " + totalCredits);
    }
}

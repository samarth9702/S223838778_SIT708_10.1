package com.example.personalearn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class InterestScreen extends AppCompatActivity {

    Button next, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8;
    TextView selectedInterest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_interest_screen);
        next = findViewById(R.id.btn_nxt);
        btn1 = findViewById(R.id.mobileAppDev);
        btn2 = findViewById(R.id.testing);
        btn3 = findViewById(R.id.algorithm);
        btn4 = findViewById(R.id.dataStructure);
        btn5 = findViewById(R.id.webDeveloper);
        btn6 = findViewById(R.id.devOps);
        btn7 = findViewById(R.id.softwareEngineer);
        btn8 = findViewById(R.id.consultant);

        selectedInterest = findViewById(R.id.selectedInterest);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedInterest.setText(btn1.getText());
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedInterest.setText(btn2.getText());
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedInterest.setText(btn3.getText());
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedInterest.setText(btn4.getText());
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedInterest.setText(btn5.getText());
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedInterest.setText(btn6.getText());
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedInterest.setText(btn7.getText());
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedInterest.setText(btn8.getText());
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InterestScreen.this, MainActivity.class);
                intent.putExtra("Interest",selectedInterest.getText().toString());
                startActivity(intent);
                finish();
            }
        });


    }
}
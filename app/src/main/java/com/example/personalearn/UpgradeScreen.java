package com.example.personalearn;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.Arrays;

public class UpgradeScreen extends AppCompatActivity {
    AppCompatButton cardViewPurchaseButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade_screen);
        cardViewPurchaseButton = findViewById(R.id.cardViewPurchaseButton);
        cardViewPurchaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PaymentScreen fragment = new PaymentScreen();

                // Begin the fragment transaction
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                // Replace the current fragment/container with the new fragment
                transaction.replace(R.id.fragment_container, fragment);

                // Add the transaction to the back stack
                transaction.addToBackStack(null);

                // Commit the transaction
                transaction.commit();
            }
        });


    }
}
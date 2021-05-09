package com.andsf.sampleproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.andsf.sampleproject.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

   ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);
    }

    public void addProducts(View view) {
        Intent i = new Intent(MainActivity.this,ProductPage.class);
        startActivity(i);
    }

    public void displayBooks(View view) {
        Toast.makeText(MainActivity.this,"display books",Toast.LENGTH_SHORT).show();
        Intent i = new Intent(MainActivity.this,DisplayBooks.class);
        startActivity(i);
    }
}
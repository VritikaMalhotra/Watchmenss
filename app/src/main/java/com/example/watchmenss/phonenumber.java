package com.example.watchmenss;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class phonenumber extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phonenumber);
    }

    public void OpenFlatAndBlockNumber(View view) {

        startActivity(new Intent(phonenumber.this, flatandblock.class));

    }
}

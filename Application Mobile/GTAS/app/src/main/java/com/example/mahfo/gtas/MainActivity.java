package com.example.mahfo.gtas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public void onCreate(Bundle saveInstanceState) {

        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void student(View view) {
        Intent intent = new Intent(this, Student.class);
        startActivity(intent);
    }

    public void teacher(View view) {
        Intent intent = new Intent(this, Teacher.class);
        startActivity(intent);
    }
}
